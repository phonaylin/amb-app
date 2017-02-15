<html>
  <body>
    <script type="application/ld+json">
	{
	  "@context": "http://schema.org",
	  "@type": "BusReservation",
	  "reservationNumber": "${order.bookingRefId}",
	  "underName": {
	    "@type": "Person",
	    "name": "${order.customer.firstName}"
	  },
	  "reservationStatus": "http://schema.org/ReservationConfirmed",
	  "reservationFor": {
	    "@type": "BusTrip",
	    "busCompany": {
	      "@type": "Organization",
	      "name": "AMB Myanmar Travels"
	    },
	    "departureBusStop": {
	      "@type": "BusStop",
	      "name": "Port Authority, NYC"
	    },
	    "departureTime": "2017-02-28T12:30:00-05:00",
	    "arrivalBusStop": {
	      "@type": "BusStop",
	      "name": "Boston South Station"
	    },
	    "arrivalTime": "2017-03-04T17:10:00-05:00"
	  },
		"modifiedTime": "2017-02-28T12:30:00-05:00",
		"modifyReservationUrl": "https://abc.com/abc"
	}
	</script>
    <p>
      Dear ${order.customer.firstName}, thanks for booking your bus ticket with us.
    </p>
    <p>
      BOOKING DETAILS<br/>
      Reservation number: ${order.bookingRefId}<br/>
      Order for: ${order.customer.firstName}<br/>
      Reservation: ${order.busRoute.originPOI.title}-${order.busRoute.destPOI.title}<br/>
      Start time: May 15th 2013 8:00am PST<br/>
      Venue: Moscone Center, 800 Howard St., San Francisco, CA 94103<br/>
    </p>
  </body>
</html>