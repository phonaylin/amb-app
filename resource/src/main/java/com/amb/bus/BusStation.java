package com.amb.bus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.amb.core.City;
import com.amb.core.AbstractEntity;

@Entity
public class BusStation extends AbstractEntity{
	
	@ManyToOne(optional=false)
	private City city;
	
	@Column(nullable = false)
	private String name;
	
	
}