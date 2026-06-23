package com.model;

import java.util.List;

public class OrderCommand {
	// 하나의 주문은 여러 개의 상품(상세)를 가질 수 있다.
	private List<OrderItem> orderItem;

	public List<OrderItem> getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(List<OrderItem> orderItem) {
		this.orderItem = orderItem;
	}
}
