package com.putact.disruptor;

import java.nio.ByteBuffer;

import com.lmax.disruptor.RingBuffer;
import com.putact.bean.Stock;

public class StockEventProducer {
	private final RingBuffer<Stock> ringBuffer;

	public StockEventProducer(RingBuffer<Stock> ringBuffer) {
		this.ringBuffer = ringBuffer;
	}

	public void onData(Stock bb) {
		long sequence = ringBuffer.next();

		try {
			Stock event = ringBuffer.get(sequence);
			 event.setCurrent(bb.getCurrent());
		} finally {
			ringBuffer.publish(sequence);
		}

	}
}
