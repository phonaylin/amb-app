/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.amb.bus.web;

import org.springframework.data.rest.core.config.Projection;

import com.amb.bus.Bus;
import com.amb.bus.BusRoute;
import com.amb.core.POI;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalTime;

import javax.money.MonetaryAmount;

/**
 * Projection interface to render {@link Order} summaries.
 * 
 * @author Oliver Gierke
 */
@Projection(name = "summary", types = BusRoute.class)
public interface BusRouteProjection {

	Bus getBus();
	
	int getDayOfWeek(); // Starts from 1-Sunday to 7-Saturday

	@JsonFormat(pattern = "KK:mm a")
	LocalTime getDepartureTime();
	
	MonetaryAmount getFare();
	
	POI getOriginPOI();

	POI getDestPOI();

}
