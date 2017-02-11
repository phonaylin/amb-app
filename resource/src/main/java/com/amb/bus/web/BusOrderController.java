package com.amb.bus.web;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.amb.bus.BusOrder;
import com.amb.bus.BusOrderDto;
import com.amb.bus.BusRoute;
import com.amb.bus.service.*;
import com.amb.core.POI;
import com.amb.core.service.POIRepository;
import com.amb.core.service.RouteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/bus/api")
public class BusOrderController {

//	@Autowired
//    BusOrderService orderService;
//	
	@Autowired
    BusService busService;
	
	@Autowired
    POIRepository poiRepository;

	@Autowired
    RouteRepository routeRepository;

	
//	@RequestMapping("/api/findByRoute")
//	@Transactional(readOnly = true)
//	public List<BusSchedule> listBusSchedulesByRoute(@RequestParam("from") Long fromId,
//			@RequestParam("to") Long toId, @RequestParam("date") @DateTimeFormat(pattern="yyyy-MM-dd") Date departureDate) {
//
//		POI originPOI = poiRepository.findOne(fromId);
//		Assert.notNull(originPOI, "From must not be null");
//
//		POI destPOI = poiRepository.findOne(toId);
//		Assert.notNull(destPOI, "To must not be null");
//
//		Assert.notNull(departureDate, "Departure date must not be null");
//
//		Route route = routeRepository.findByOriginPOIAndDestPOI(originPOI, destPOI);
//		Assert.notNull(route, "Cannot find the route requested");
//
//		BusSearchCriteria criteria = new BusSearchCriteria(route, departureDate);
//
//		List<BusSchedule> result = busService.findBusSchdules(criteria);
//		return result;
//	}
	

	@RequestMapping("routes")
	@Transactional(readOnly = true)
	public List<BusRoute> listBusOffersByRoute(@RequestParam("from") Long fromId, 
			@RequestParam("to") Long toId) {
		
		POI originPOI = poiRepository.findOne(fromId);
		Assert.notNull(originPOI, "From must not be null");
		
		POI destPOI = poiRepository.findOne(toId);
		Assert.notNull(destPOI, "To must not be null");
		
		List<BusRoute> result = busService.findBusRoutes(originPOI, destPOI);
		return result;
	}
	
//	@RequestMapping(value="/orders", method=RequestMethod.GET)
//	@Transactional(readOnly = true)
//	public List<BusOrderDto> getBusOrder(@RequestParam Long customerId) {
//		List<BusOrderDto> orders = orderService.findOrdersByCustomer(customerId);
//		return orders;
//	}
//	
	@RequestMapping(value="order", method=RequestMethod.POST)
	@Transactional
	public BusOrder createBusOrder(@RequestBody BusOrder order) {
		//Assert.notNull(order..getCustomer(), "Customer must not be null");
		
		return busService.order(order);
	}

}
