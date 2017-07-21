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

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Client API interface for Rya.
 *
 */
public interface RyaClientService {
	
	/**
	 * Ingests triples into Rya.
	 * @param format The format of the triples. One of: <code>RDF/XML</code>, <code>N-Triples</code>, 
	 * <code>Turtle</code>, <code>N3</code>, <code>TriX</code>, <code>TriG</code>
	 * @param triples The triples.
	 * @param contentType The content-type.
	 * @return A Retrofit {@link Call}.
	 */
	@POST("loadrdf")
	Call<ResponseBody> ingest(@Query("format") String format, @Body String triples, @Header("Content-Type") String contentType);

}