package com.amb.bus;

import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.money.MonetaryAmount;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.amb.core.AbstractEntity;
import lombok.*;
import com.amb.core.POI;
import com.fasterxml.jackson.annotation.JsonFormat;

@Data
@Entity
//@Getter
//@Embeddable
//@ToString(callSuper = true)
@NoArgsConstructor(force = true)
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor(suppressConstructorProperties = true)
public class BusRoute extends AbstractEntity{
	
	@ManyToOne(optional=false)
	private Bus bus;

	@Column(nullable = false)
	private int dayOfWeek; // Starts from 1-Sunday to 7-Saturday

	@Column(nullable = false)
	@JsonFormat(pattern = "KK:mm a")
	private LocalTime departureTime;
	
	@ManyToOne(optional = false)
	private POI originPOI;
	
	@ManyToOne(optional = false)
	private POI destPOI;

	@Column(nullable = true)
	private MonetaryAmount fare;
	
	private LocalTime arrivalTime;
	
	private LocalDateTime activeStartDate;
	
	private LocalDateTime activeEndDate;
	
//	public BusRoute(POI originPOI, POI destPOI, Bus bus, int dayOfWeek, LocalTime departureTime, MonetaryAmount fare) {
//		super();
//		this.originPOI = originPOI;
//		this.destPOI = destPOI;
//		this.bus = bus;
//		this.dayOfWeek = dayOfWeek;
//		this.departureTime = departureTime;
//		this.fare = fare;
//	}
//	public MonetaryAmount getFare() {
//		return fare;
//	}
//	public void setFare(MonetaryAmount fare) {
//		this.fare = fare;
//	}
}
