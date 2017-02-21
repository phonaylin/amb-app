/*
 * Copyright 2012-2013 the original author or authors.
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.event.AbstractRepositoryEventListener;
import org.springframework.stereotype.Component;

import com.amb.Utility;
import com.amb.bus.BusOrder;
import com.amb.bus.service.MailService;
import com.amb.core.Customer;

/**
 * Event listener to reject {@code DELETE} requests to Spring Data REST.
 * 
 * @author Oliver Gierke
 */
@Component
class BusOrderControllerEventListener extends AbstractRepositoryEventListener<BusOrder> {

	@Autowired
	MailService mailService;
	
	/* 
	 * (non-Javadoc)
	 * @see org.springframework.data.rest.repository.context.AbstractRepositoryEventListener#onBeforeDelete(java.lang.Object)
	 */
	@Override
	protected void onBeforeCreate(BusOrder order) {
		order.setBookingRefId(Utility.generateBookingRefNumber(6).toString());
		order.setTotalPrice(order.getBusRoute().getFare().multiply(order.getOrderItems().size()));
		
		// create customer
		String firstName = order.getOrderItems().get(0).getPassengerFirstName();
		String lastName = order.getOrderItems().get(0).getPassengerLastName();
		String email = order.getBuyerEmailAddress();
		String contact = order.getBuyerMobileNumber();
		
		Customer customer = new Customer(firstName, lastName, email, contact);
		order.setCustomer(customer);
	}
	
	@Override
	protected void onAfterCreate(BusOrder order) {
		mailService.sendEmail(order);
	}
}
