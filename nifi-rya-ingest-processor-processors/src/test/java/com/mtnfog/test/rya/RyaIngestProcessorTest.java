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
package com.mtnfog.test.rya;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.nifi.util.MockFlowFile;
import org.apache.nifi.util.TestRunner;
import org.apache.nifi.util.TestRunners;
import org.junit.Before;
import org.junit.Test;

import com.mtnfog.rya.RyaIngestProcessor;

public class RyaIngestProcessorTest {

	private static final String RYA_ENDPOINT = "http://localhost:8080/web.rya/";
	
    private TestRunner runner;

    @Before
    public void init() {
    	runner = TestRunners.newTestRunner(RyaIngestProcessor.class);
    }

    /**
     * Note that this test requires Rya to be listening at the defined endpoint.
     */
    @Test
    public void testOnTrigger() throws IOException {
    	
    	final String triple = "<http://mynamespace/ProductType3> <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://mynamespace/ProductType> .";

        InputStream content = new ByteArrayInputStream(triple.getBytes(StandardCharsets.UTF_8));
        
        runner.setProperty(RyaIngestProcessor.RYA_API_ENDPOINT, RYA_ENDPOINT);
        runner.setProperty(RyaIngestProcessor.RYA_TRIPLES_FORMAT, "N-Triples");
        
        runner.enqueue(content);
        runner.run(1);
        runner.assertQueueEmpty();
        
        List<MockFlowFile> results = runner.getFlowFilesForRelationship(RyaIngestProcessor.REL_SUCCESS);
        assertTrue("1 ingested", results.size() == 1);
        
    }
        
}