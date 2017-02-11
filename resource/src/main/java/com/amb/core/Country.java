package com.amb.core;

import javax.persistence.Entity;

@Entity
public class Country extends AbstractEntity{

	private String name;

	private String shortDescription;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
}
