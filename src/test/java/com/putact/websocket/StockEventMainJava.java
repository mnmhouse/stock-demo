package com.putact.websocket;

import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.putact.bean.Stock;
import com.putact.disruptor.StockEventHandler;
import com.putact.disruptor.StockEventProducer;
import com.putact.util.NumberHelper;
import com.putact.websocket.WebSocketStock;

public class StockEventMainJava {
	public static int bufferSize = 1024;// Construct the Disruptor

	public static Executor executor = Executors.newCachedThreadPool();
	public static StockEventHandler stockHandler = new StockEventHandler();

	public static void sendAllData(CopyOnWriteArraySet<WebSocketStock> webSocketSet) throws InterruptedException {

		// Executor that will be used to construct new threads for consumers
		Executor executor = Executors.newCachedThreadPool();
		int bufferSize = 1024;// Construct the Disruptor
		Disruptor<Stock> disruptor = null;
		// new Disruptor<>(Stock::new, bufferSize, executor);
		// 可以使用lambda来注册一个EventHandler

		stockHandler.setWebSocketSet(webSocketSet);
		disruptor.handleEventsWith(stockHandler);

		// disruptor.handleEventsWith((event, sequence, endOfBatch) ->
		// System.out.println("Event: " + event.getHign()));
		// Start the Disruptor, starts all threads running
		disruptor.start();
		// Get the ring buffer from the Disruptor to be used for publishing.
		RingBuffer<Stock> ringBuffer = disruptor.getRingBuffer();

		StockEventProducer producer = new StockEventProducer(ringBuffer);

		for (int l = 0; true; l++) {
			Stock tock = new Stock();
			double result = Double.parseDouble(tock.getCurrent()) + NumberHelper.getRamdom(10);
			tock.setCurrent(result + "");
			producer.onData(tock);

			Thread.sleep(1000);
		}
	}

}
