package com.putact.websocket;

import com.lmax.disruptor.EventHandler;

public class EventHandlerT implements EventHandler<LongEvent> {

	public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
		System.out.println("Event2test: " + event.getValue());
	}
}
