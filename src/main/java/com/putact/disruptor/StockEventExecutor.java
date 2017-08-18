package com.putact.disruptor;

import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.putact.bean.Stock;
import com.putact.util.NumberHelper;
import com.putact.websocket.WebSocketStock;

public class StockEventExecutor {

	public StockEventHandler stockHandler = new StockEventHandler();
	private static volatile StockEventExecutor instance = null;

	public void setWebSocketSet(CopyOnWriteArraySet<WebSocketStock> webSocketSet) {
		this.stockHandler.setWebSocketSet(webSocketSet);

	}

	private StockEventExecutor() {
		startTask();
	}

	public static StockEventExecutor getInstance() {
		if (instance == null) {
			synchronized (StockEventExecutor.class) {
				if (instance == null) {
					instance = new StockEventExecutor();
				}
			}
		}
		return instance;
	}

	public void startTask() {

		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					sendAllData();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}).start();

	}

	public void sendAllData() throws InterruptedException {

		Executor executor = Executors.newCachedThreadPool();
		int bufferSize = 1024;// Construct the Disruptor

		Disruptor<Stock> disruptor = new Disruptor<>(Stock::new, bufferSize, executor);

		// Executor that will be used to construct new threads for consumers

		disruptor.handleEventsWith(stockHandler);

		disruptor.start();
		// Get the ring buffer from the Disruptor to be used for publishing.
		RingBuffer<Stock> ringBuffer = disruptor.getRingBuffer();

		StockEventProducer producer = new StockEventProducer(ringBuffer);

		for (int l = 0; true; l++) {
			Stock tock = new Stock();
			double result = Double.parseDouble(tock.getCurrent()) + NumberHelper.getRamdom(100);
			tock.setCurrent(result + "");
			producer.onData(tock);

			Thread.sleep(3000);
		}
	}

	public Stock constructStockData() {
		Stock stock = new Stock();

		return stock;
	}

}
