package com.putact.disruptor;

import java.nio.ByteBuffer;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.putact.bean.Stock;
import com.putact.util.NumberHelper;
import com.putact.websocket.WebSocketStock;

public class StockEventMainJava {

	public static void main(String[] args) throws InterruptedException {

		// Executor that will be used to construct new threads for consumers
		Executor executor = Executors.newCachedThreadPool();
		// Specify the size of the ring buffer, must be power of 2.
		int bufferSize = 1024;// Construct the Disruptor
		Disruptor<Stock> disruptor = new Disruptor<>(Stock::new, bufferSize, executor);
		// 可以使用lambda来注册一个EventHandler
		disruptor.handleEventsWith((event, sequence, endOfBatch) -> System.out.println("Event: " + event.getHign()));
		// Start the Disruptor, starts all threads running
		disruptor.start();
		// Get the ring buffer from the Disruptor to be used for publishing.
		RingBuffer<Stock> ringBuffer = disruptor.getRingBuffer();

		StockEventProducer producer = new StockEventProducer(ringBuffer);

		for (int l = 0; true; l++) {
			Stock tock = new Stock();

			// ringBuffer.publishEvent((event, sequence, buffer) ->
			// event.setValue(buffer.getInt(0)), bb);
			producer.onData(tock);

			Thread.sleep(1000);
		}
	}

	public static void sendAllData(CopyOnWriteArraySet<WebSocketStock> webSocketSet) throws InterruptedException {

		// Executor that will be used to construct new threads for consumers
		Executor executor = Executors.newCachedThreadPool();
		// Specify the size of the ring buffer, must be power of 2.
		int bufferSize = 1024;// Construct the Disruptor
		Disruptor<Stock> disruptor = new Disruptor<>(Stock::new, bufferSize, executor);
		// 可以使用lambda来注册一个EventHandler

		StockEventHandler stockHandler = new StockEventHandler();
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
