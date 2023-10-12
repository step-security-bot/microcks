/*
 * Copyright The Microcks Authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.microcks.util.soapui.assertions;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This is a test case for class SimpleNotContainsAssertion.
 * @author laurent
 */
public class SimpleNotContainsAssertionTest {

   private String validSoap = """
         <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:hel="http://www.example.com/hello">
            <soapenv:Header/>
            <soapenv:Body>
               <hel:sayHelloResponse>
                  <sayHello>Hello Andrew!</sayHello>
               </hel:sayHelloResponse>
            </soapenv:Body>
         </soapenv:Envelope>
         """;

   private String invalidSoap = """
         <soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/" xmlns:hel="http://www.example.com/hello">
            <soap:Header/>
            <soap:Body>
               <hel:sayHelloResponse>
                  <sayHello>Andrew!</sayHello>
               </hel:sayHelloResponse>
            </soap:Body>
         </soap:Envelope>
         """;

   @Test
   public void testExactMatch() {
      // Passing case.
      Map<String, String> configParams = Map.of(
            SimpleNotContainsAssertion.TOKEN_PARAM, "Hello Andrew!",
            SimpleNotContainsAssertion.IGNORE_CASE_PARAM, "false"
      );
      SoapUIAssertion assertion = AssertionFactory.intializeAssertion(AssertionFactory.SIMPLE_NOT_CONTAINS, configParams);
      AssertionStatus status = assertion.assertResponse(new RequestResponseExchange(null, null, validSoap, 100L),
            new ExchangeContext(null, null, null, null));
      assertEquals(AssertionStatus.FAILED, status);
      assertEquals(1, assertion.getErrorMessages().size());
      assertTrue(assertion.getErrorMessages().get(0).contains("Response contains"));

      // Failing case.
      status = assertion.assertResponse(new RequestResponseExchange(null, null, invalidSoap, 100L),
            new ExchangeContext(null, null, null, null));
      assertEquals(AssertionStatus.VALID, status);
   }

   @Test
   public void testCaseSensitiveness() {
      // Passing case.
      Map<String, String> configParams = Map.of(
            SimpleNotContainsAssertion.TOKEN_PARAM, "HeLlO AnDreW!",
            SimpleNotContainsAssertion.IGNORE_CASE_PARAM, "true"
      );
      SoapUIAssertion assertion = AssertionFactory.intializeAssertion(AssertionFactory.SIMPLE_NOT_CONTAINS, configParams);
      AssertionStatus status = assertion.assertResponse(new RequestResponseExchange(null, null, validSoap, 100L),
            new ExchangeContext(null, null, null, null));
      assertEquals(AssertionStatus.FAILED, status);
      assertEquals(1, assertion.getErrorMessages().size());
      assertTrue(assertion.getErrorMessages().get(0).contains("Response contains"));

      // Failing case.
      status = assertion.assertResponse(new RequestResponseExchange(null, null, invalidSoap, 100L),
            new ExchangeContext(null, null, null, null));
      assertEquals(AssertionStatus.VALID, status);
   }

   @Test
   public void testRegularExpression() {
      // Passing case.
      Map<String, String> configParams = Map.of(
            SimpleNotContainsAssertion.TOKEN_PARAM, "Hello\\s(.*)!",
            SimpleNotContainsAssertion.IGNORE_CASE_PARAM, "false",
            SimpleNotContainsAssertion.USE_REGEX_PARAM, "true"
      );
      SoapUIAssertion assertion = AssertionFactory.intializeAssertion(AssertionFactory.SIMPLE_NOT_CONTAINS, configParams);
      AssertionStatus status = assertion.assertResponse(new RequestResponseExchange(null, null, validSoap, 100L),
            new ExchangeContext(null, null, null, null));
      assertEquals(AssertionStatus.FAILED, status);
      assertEquals(1, assertion.getErrorMessages().size());
      assertTrue(assertion.getErrorMessages().get(0).contains("Response contains"));

      // Failing case.
      status = assertion.assertResponse(new RequestResponseExchange(null, null, invalidSoap, 100L),
            new ExchangeContext(null, null, null, null));
      assertEquals(AssertionStatus.VALID, status);

      // RegExp + case insensitive passing case.
      configParams = Map.of(
            SimpleNotContainsAssertion.TOKEN_PARAM, "HeLlO\\s(.*)!",
            SimpleNotContainsAssertion.IGNORE_CASE_PARAM, "true",
            SimpleNotContainsAssertion.USE_REGEX_PARAM, "true"
      );
      assertion = AssertionFactory.intializeAssertion(AssertionFactory.SIMPLE_NOT_CONTAINS, configParams);
      status = assertion.assertResponse(new RequestResponseExchange(null, null, validSoap, 100L),
            new ExchangeContext(null, null, null, null));
      assertEquals(AssertionStatus.FAILED, status);
      assertEquals(1, assertion.getErrorMessages().size());
      assertTrue(assertion.getErrorMessages().get(0).contains("Response contains"));
   }
}
