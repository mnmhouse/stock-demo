package com.putact.disruptor;

import java.util.concurrent.CopyOnWriteArraySet;

import com.lmax.disruptor.EventHandler;
import com.putact.bean.Stock;
import com.putact.util.JsonHelper;
import com.putact.websocket.WebSocketStock;

public class StockEventHandler implements EventHandler<Stock> {

	private CopyOnWriteArraySet<WebSocketStock> webSocketSet = new CopyOnWriteArraySet<WebSocketStock>();

	public CopyOnWriteArraySet<WebSocketStock> getWebSocketSet() {
		return webSocketSet;
	}

	public void setWebSocketSet(CopyOnWriteArraySet<WebSocketStock> webSocketSet) {
		this.webSocketSet = webSocketSet;
	}

	public void onEvent(Stock event, long sequence, boolean endOfBatch) throws Exception {
		String respose = JsonHelper.toJson(event);

		for (WebSocketStock webSocketStock : webSocketSet) {

			webSocketStock.sendMessage(respose);

		}
		System.out.println("Event: " + event.getHigh52week());
	}
}
