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

package com.amb.bus.service;

import com.amb.bus.BusRoute;
import com.amb.bus.web.BusRouteProjection;
import com.amb.core.POI;
import com.amb.core.Route;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "busroutes", path = "busroutes", excerptProjection = BusRouteProjection.class)
//@RepositoryRestResource(excerptProjection = BusRouteProjection.class)
interface BusRouteRepository extends CrudRepository<BusRoute, Long> {

	<Optional>List<BusRoute> findByOriginPOIAndDestPOI(@Param("origin") POI originPOI, @Param("dest") POI destPOI);
	
	<Optional>List<BusRoute> findByOriginPOI(@Param("origin") POI originPOI);
}
