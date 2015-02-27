/*
 * Licensed to Laurent Broudoux (the "Author") under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. Author licenses this
 * file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.github.lbroudoux.microcks.domain;

/**
 * TestStepResult is an entity embedded within TestCaseResult. They are created
 * for each request associated with an operation / action of a microservice.
 * @author laurent
 */
public class TestStepResult {

   private boolean success = false;
   private long elapsedTime;
   private String requestName;
   private String message;

   public boolean isSuccess() {
      return success;
   }

   public void setSuccess(boolean success) {
      this.success = success;
   }

   public long getElapsedTime() {
      return elapsedTime;
   }

   public void setElapsedTime(long elapsedTime) {
      this.elapsedTime = elapsedTime;
   }

   public String getRequestName() {
      return requestName;
   }

   public void setRequestName(String requestName) {
      this.requestName = requestName;
   }

   public String getMessage() {
      return message;
   }

   public void setMessage(String message) {
      this.message = message;
   }
}
