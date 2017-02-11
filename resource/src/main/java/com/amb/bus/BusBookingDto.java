//package com.amb.bus;
//
//import java.util.List;
//
//import com.amb.core.Customer;
//import com.amb.core.OrderStatusType;
//
//public class BusBookingDto {
//	
//	private Customer buyer;
//	private OrderStatusType orderStatus =  OrderStatusType.OPENED;
//	private List<BusOrderDto> orders;
//	private String bookingRefId;
//	
//	public BusBookingDto() {
//	}
//	
//	public BusBookingDto(Customer buyer, List<BusOrderDto> orders) {
//		this.buyer = buyer;
//		this.orders = orders;
//	}
//
//	public Customer getBuyer() {
//		return buyer;
//	}
//
//	public OrderStatusType getOrderStatus() {
//		return orderStatus;
//	}
//
//	public List<BusOrderDto> getOrders() {
//		return orders;
//	}
//
//	public void setBuyer(Customer buyer) {
//		this.buyer = buyer;
//	}
//
//	public void setOrderStatus(OrderStatusType orderStatus) {
//		this.orderStatus = orderStatus;
//	}
//
//	public void setOrders(List<BusOrderDto> orders) {
//		this.orders = orders;
//	}
//
//	public String getBookingRefId() {
//		return bookingRefId;
//	}
//
//	public void setBookingRefId(String bookingRefId) {
//		this.bookingRefId = bookingRefId;
//	}
//
//		
//	
//
//}
