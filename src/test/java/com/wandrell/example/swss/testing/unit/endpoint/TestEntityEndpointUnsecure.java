/**
 * The MIT License (MIT)
 * <p>
 * Copyright (c) 2015 the original author or authors.
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.wandrell.example.swss.testing.unit.endpoint;

import static org.springframework.ws.test.server.RequestCreators.withPayload;
import static org.springframework.ws.test.server.ResponseMatchers.payload;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.ws.test.server.MockWebServiceClient;
import org.testng.annotations.Test;

@ContextConfiguration(
        locations = { "classpath:context/ws/test-unsecure-ws.xml" })
public final class TestEntityEndpointUnsecure extends
        AbstractTestNGSpringContextTests {

    @Autowired
    private ApplicationContext applicationContext;

    /**
     * Constructs a {@code TestEntityEndpointUnsecure}.
     */
    public TestEntityEndpointUnsecure() {
        super();
        // TODO: Get this to work
    }

    @Test
    public void testEndpoint() throws Exception {
        final MockWebServiceClient mockClient; // Mocked client
        final Source requestPayload;  // SOAP payload for the request
        final Source responsePayload; // SOAP payload for the response

        mockClient = MockWebServiceClient.createClient(applicationContext);

        requestPayload = new StreamSource(
                ClassLoader.class
                        .getResourceAsStream("/soap/entity-request-not-secured.xml"));
        responsePayload = new StreamSource(
                ClassLoader.class
                        .getResourceAsStream("/soap/entity-response-not-secured.xml"));

        mockClient.sendRequest(withPayload(requestPayload)).andExpect(
                payload(responsePayload));
    }

}
