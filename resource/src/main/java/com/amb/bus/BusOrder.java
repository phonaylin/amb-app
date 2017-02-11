package com.amb.bus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.money.MonetaryAmount;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;

import org.javamoney.moneta.Money;

import com.amb.core.Customer;
import com.amb.core.AbstractEntity;
import com.amb.core.OrderStatusType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AccessLevel;

@Data
@Entity
@NoArgsConstructor(force = true)
//@Embeddable
//@ToString(callSuper = true)
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor(suppressConstructorProperties = true)
public class BusOrder extends AbstractEntity{
	
	@ManyToOne(optional = false)
	private BusRoute busRoute;
	
	private String buyerEmailAddress;
	
	private String buyerMobileNumber;
	
	private LocalDate travelDate;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Customer customer;
	
	private String comment;
	
	@Column(nullable = false)
	private String bookingRefId;
	
	@Column(nullable = false)
	private MonetaryAmount totalPrice;
	
	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private OrderStatusType orderStatus = OrderStatusType.OPENED;
	
//	@OneToMany(mappedBy = "busOrder", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@OrderColumn //
	@Column(unique = true) //
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true) //
//	@CollectionTable(name="bus_order_item")
	private List<BusOrderItem> orderItems = new ArrayList<BusOrderItem>();

	
//	public BusOrder() {
//		super();
//	}
	
//	
//	public BusOrder(BusRoute busOffer, Customer customer, String comment) {
//		this.busRoute = busOffer;
//		this.customer = customer;
//		this.comment = comment;
//	}
//
//	public BusOffer getBusOffer() {
//		return busRoute;
//	}

//	public Customer getCustomer() {
//		return customer;
//	}
//
//	public String getComment() {
//		return comment;
//	}
//
//	public void setComment(String comment) {
//		this.comment = comment;
//	}
//	
//	public List<Customer> getPassengers() {
//		List<Customer> passengers = new ArrayList<Customer>();
//		for(BusOrderItem item : this.orderItems) {
//			passengers.add(item.getPassenger());
//		}
//		
//		return passengers;
//	}
	
	// TODO: to think more further. how about concurrency issue?
//	public boolean addOrderItem(BusOrderItem item) {
//		item.setBusOrder(this);
//		if (this.orderItems.add(item)) {
//			this.quantity = this.quantity + 1;
//			this.totalPrice = calcTotalPrice(this.quantity, this.unitPrice);
//			return true;
//		}
//		return false;
//	}
//	
//	public boolean removeOrderItem(BusOrderItem item) {
//		if (this.orderItems.add(item)) {
//			this.quantity = this.quantity + 1;
//			this.totalPrice = calcTotalPrice(this.quantity, this.unitPrice);
//			return true;
//		}
//		return false;
//	}
	
//	public List<BusOrderItem> getOrderItems() {
//		return orderItems; // TODO: give the clone items
//	}
//	
//	private static final BigDecimal calcTotalPrice(final int quantity, final BigDecimal unitPrice) {
//		if(quantity <= 0) 
//			return new BigDecimal(0);
//		
//		return unitPrice.multiply(new BigDecimal(quantity));
//	}
//
//	public String getBookingRefId() {
//		return bookingRefId;
//	}
//
//	public void setBookingRefId(String bookingRefId) {
//		this.bookingRefId = bookingRefId;
//	}
}
