package com.putact.websocket;

import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.putact.websocket.EventHandlerT;

public class LongEventMain {
	public static void main(String[] args) throws InterruptedException {
		// Executor that will be used to construct new threads for consumers
		Executor executor = Executors.newCachedThreadPool();

		// The factory for the event
		LongEventFactory factory = new LongEventFactory();

		// Specify the size of the ring buffer, must be power of 2.
		int bufferSize = 1024;

		// Construct the Disruptor
		Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(factory, bufferSize, executor);

		Disruptor<LongEvent> disruptor2 = new Disruptor<LongEvent>(factory,
				bufferSize, executor, ProducerType.SINGLE,
                new YieldingWaitStrategy());
		// Connect the handler
		disruptor.handleEventsWith(new LongEventHandler());
		disruptor.handleEventsWith(new EventHandlerT());

		// Start the Disruptor, starts all threads running
		disruptor.start();

		// Get the ring buffer from the Disruptor to be used for publishing.
		RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

		LongEventProducer producer = new LongEventProducer(ringBuffer);

		ByteBuffer bb = ByteBuffer.allocate(30);
		for (int l = 0; true; l++) {
			// ByteBuffer bb = ByteBuffer.allocate(30);
			bb.putLong(0,  l);
			producer.onData(bb);
			Thread.sleep(1000);
		}
	}

}
