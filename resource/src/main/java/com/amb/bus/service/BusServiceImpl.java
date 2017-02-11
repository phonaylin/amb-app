package com.amb.bus.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.amb.bus.BusOrder;
import com.amb.bus.BusOrderDto;
import com.amb.bus.BusRoute;
import com.amb.core.Customer;
import com.amb.core.POI;


@Component("busService")
@Transactional
public class BusServiceImpl implements BusService{

	private final BusRouteRepository busRouteRepo;
	
	private final BusOrderRepository busOrderRepo;
	
	
	@Autowired
	public BusServiceImpl(BusRouteRepository busRouteRepo, BusOrderRepository busOrderRepo) {
		this.busRouteRepo = busRouteRepo;
		this.busOrderRepo =  busOrderRepo;
	}

	@Override
	public List<BusRoute> findBusRoutes(POI origin, POI destination) {
		Assert.notNull(origin, "origin must not be null");
		
		List<BusRoute> r = this.busRouteRepo.findByOriginPOIAndDestPOI(origin, destination);
		return r;
	}

	
	@Override
	public List<BusRoute> findBusRoutesByOrigin(POI origin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BusOrder order(BusOrder order) {
		Customer customer = new Customer(order.getCustomer().getFirstName(), order.getCustomer().getLastName());
		//BusOrder newOrder = new BusOrder(order.getBusRoute(), customer, order.getComment(), order.getBookingRefId())
		order.setCustomer(customer);
		return this.busOrderRepo.save(order);
	}

	@Override
	public BusOrder cancel(BusOrder order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BusOrder update(BusOrderDto order) {
		// TODO Auto-generated method stub
		return null;
	}

	
//	@Override
//	public List<BusSchedule> findBusSchdules(BusSearchCriteria criteria) {
//		Assert.notNull(criteria, "Criteria must not be null");
//		final Route route = criteria.getBusRoute();
//		final Date departDate = criteria.getDepartureDate();
//
//		List<BusSchedule> r = new ArrayList<BusSchedule>();
//		if (route != null && departDate != null) {
//			r = this.busScheduleRepository.findByRouteAndDayOfWeek(route, DateTimeHelper.getDayOfWeek(departDate));
//		}
//		else if (route != null) {
//			r = this.busScheduleRepository.findByRoute(route);
//		}
//
//		return r;
//	}
	
}
