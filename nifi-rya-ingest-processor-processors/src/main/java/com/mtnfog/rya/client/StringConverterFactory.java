/*
 * (C) Copyright 2017 Mountain Fog, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mtnfog.rya.client;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import com.mtnfog.rya.RyaIngestProcessor;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Converter.Factory;
import retrofit2.Retrofit;

/**
 * Converter for Retrofit to process strings.
 *
 */
public class StringConverterFactory extends Factory {	

	public static StringConverterFactory FACTORY;
	
	/**
	 * Gets an instance of this class.
	 * @return An instance of {@link StringConverterFactory}.
	 */
	public static StringConverterFactory getInstance() {
		
		if(FACTORY == null) {			
			FACTORY = new StringConverterFactory();			
		}
		
		return FACTORY;
		
	}
	
	@Override
	public Converter<ResponseBody, ?> responseBodyConverter(Type type,
			Annotation[] annotations, Retrofit retrofit) {
		
		if (String.class.equals(type)) {
			
			return new Converter<ResponseBody, String>() {
				
				@Override
				public String convert(ResponseBody value) throws IOException {
					return value.string();
				}
				
			};
			
		}
		
		return null;
		
	}

	@Override
	public Converter<?, RequestBody> requestBodyConverter(Type type,
			Annotation[] parameterAnnotations, Annotation[] methodAnnotations,
			Retrofit retrofit) {

		if (String.class.equals(type)) {
			
			return new Converter<String, RequestBody>() {
				
				@Override
				public RequestBody convert(String value) throws IOException {
					return RequestBody.create(RyaIngestProcessor.MEDIA_TYPE_TEXT_PLAIN, value);
				}
				
			};
			
		}
		
		return null;
		
	}

}