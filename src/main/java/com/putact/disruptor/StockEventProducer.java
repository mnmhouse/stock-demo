package com.putact.disruptor;

import com.lmax.disruptor.RingBuffer;
import com.putact.bean.Stock;

public class StockEventProducer {
	private final RingBuffer<Stock> ringBuffer;

	public StockEventProducer(RingBuffer<Stock> ringBuffer) {
		this.ringBuffer = ringBuffer;
	}

	public void onData(Stock stock) {
		long sequence = ringBuffer.next();

		try {
			Stock event = ringBuffer.get(sequence);
			event.setCurrent(stock.getCurrent());
		} finally {
			ringBuffer.publish(sequence);
		}

	}
}
