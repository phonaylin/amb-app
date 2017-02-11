/*
 * Copyright 2016 the original author or authors.
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
package com.amb;

import com.amb.bus.BusAmenity;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.springframework.context.annotation.Configuration;

//@Converter(autoApply = true)
//public class BusAmenityAttributeConverter implements AttributeConverter<BusAmenity, String> {
//
//	@Override
//    public String convertToDatabaseColumn(BusAmenity attribute) {
//        switch (attribute) {
//            case AIRCON:
//                return "AC";
//            case MOVIE:
//                return "MV";
//            case TOWEL:
//                return "TW";
//            default:
//                throw new IllegalArgumentException("Unknown" + attribute);
//        }
//    }
// 
//    @Override
//    public BusAmenity convertToEntityAttribute(String dbData) {
//        switch (dbData) {
//            case "AC":
//                return BusAmenity.AIRCON;
//            case "MV":
//                return BusAmenity.MOVIE;
//            case "TW":
//                return BusAmenity.TOWEL;
//            default:
//                throw new IllegalArgumentException("Unknown" + dbData);
//        }
//    }
//}

