package com.amb.bus;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.amb.core.Customer;
import com.amb.core.OrderStatusType;

import lombok.Getter;

@Getter
public class BusOrderDto {
	
	private Long busRouteId;
	private Customer customer;
	private List<BusTicketsDto> tickets;
	private String comment;
	
	private BigDecimal totalAmount;
	private OrderStatusType orderStatus =  OrderStatusType.OPENED;
	private String bookingRefId;
	
	public BusOrderDto() {
	}
	
	public BusOrderDto(Long busRouteId, Customer customer, List<BusTicketsDto> tickets, String comment) {
		this.setBusRouteId(busRouteId);
		this.setCustomer(customer);
		this.setTickets(tickets);
		this.setComment(comment);
	}
		
	public Long getBusRouteId() {
		return busRouteId;
	}

	public void setBusRouteId(Long busRouteId) {
		this.busRouteId = busRouteId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<BusTicketsDto> getTickets() {
		return tickets;
	}

	public void setTickets(List<BusTicketsDto> tickets) {
		this.tickets = tickets;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Getter
	public class BusTicketsDto {
		private Customer passenger;
		
		private String ticketNumber;
		
		private Date plannedTravelDate;
	}
}


