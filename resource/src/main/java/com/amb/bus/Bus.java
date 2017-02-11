package com.amb.bus;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OrderColumn;

import com.amb.core.AbstractEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@Entity
@NoArgsConstructor(force = true)
//@Embeddable
//@ToString(callSuper = true)
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor(suppressConstructorProperties = true)
public class Bus extends AbstractEntity{
	
	@Column(nullable = false)
	private String licenseNumber;
	
	@Column(nullable = false)
	private int capacity;
	
//	@Column(nullable = false)
//	@Enumerated(EnumType.ORDINAL)
//	private BusType busType;
	
	@Column(nullable = false)
	private String busClass;
	
	private String busClassDescription;

	@ElementCollection(targetClass=BusAmenity.class)
	@OrderColumn
	@Enumerated(EnumType.STRING)
	@CollectionTable(name="bus_amenities")
//    @Column(name="interest")
	private List<BusAmenity> busAmenities = new ArrayList<BusAmenity>();
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "operator_id")
	private BusOperator operator;
}