package com.amb.bus;

import java.time.LocalDate;
import java.util.Date;

import javax.money.MonetaryAmount;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.amb.core.Customer;
import com.amb.core.AbstractEntity;
import com.amb.core.OrderStatusType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor(force = true)
@AllArgsConstructor(suppressConstructorProperties = true)
@EqualsAndHashCode(callSuper = false)
public class BusOrderItem extends AbstractEntity{
	
//	@JsonIgnore
//	@ManyToOne(fetch=FetchType.LAZY, optional=false)
//	private BusOrder busOrder;
	
//	@OneToOne(cascade = CascadeType.ALL, optional = false)
	@Column(nullable = false)
	private String passengerFirstName;
	
	private String passengerLastName;
	
	private String passengerEmailAddress;
	
	private String passengerContactNumber;
	
	private String ticketNumber;
	
	private LocalDate travelDate;
	
//	@ManyToOne(optional = true)
//	private BusRoute confirmedSchedule;
	
//	@Column(nullable = false)
//	@Enumerated(EnumType.ORDINAL)
//	private OrderStatusType orderStatus;
	
//	public BusOrderItem() {
//		
//	}
//	
//	public BusOrderItem(Customer passenger) {
//		super();
//		this.passenger = passenger;
//	}
//	
//	public BusOrderItem(BusOrder busOrder, Customer passenger, OrderStatusType orderStatus) {
//		super();
//		this.busOrder = busOrder;
//		this.passenger = passenger;
//		this.orderStatus = orderStatus;
//	}
//
//	public BusOrderItem(Customer passenger, Date plannedTravelDate, OrderStatusType orderStatus) {
//		super();
//		this.passenger = passenger;
//		this.plannedTravelDate = plannedTravelDate;
//		this.orderStatus = orderStatus;
//	}
//	
//	// Getters setters
//	
//	public BusOrder getBusOrder() {
//		return busOrder;
//	}
//
//	public Customer getPassenger() {
//		return passenger;
//	}
//
//	public String getTicketNumber() {
//		return ticketNumber;
//	}
//
//	public void setBusOrder(BusOrder busOrder) {
//		this.busOrder = busOrder;
//	}
//
//	public void setPassenger(Customer passenger) {
//		this.passenger = passenger;
//	}
//
//	public void setTicketNumber(String ticketNumber) {
//		this.ticketNumber = ticketNumber;
//	}
//
//	public Date getPlannedTravelDate() {
//		return plannedTravelDate;
//	}
//
//	public void setPlannedTravelDate(Date plannedTravelDate) {
//		this.plannedTravelDate = plannedTravelDate;
//	}
}
