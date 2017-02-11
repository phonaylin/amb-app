package com.amb.bus;

import lombok.Getter;

@Getter
public enum BusAmenity {
    AIRCON("AC"), MOVIE("MV"), TOWEL("TW");
    
    public String amenity;
	
    BusAmenity(String amenity) {
    	this.amenity = amenity;
    }
}


	
