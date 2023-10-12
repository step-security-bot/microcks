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
package io.github.microcks.web.dto;

/**
 * This is DTO bean for handling creation of Service for GenericResource and dynamic mocking.
 * @author laurent
 */
public class GenericResourceServiceDTO {

   private String name;
   private String version;
   private String resource;
   private String referencePayload;

   public GenericResourceServiceDTO() {
   }

   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
   }

   public String getVersion() {
      return version;
   }
   public void setVersion(String version) {
      this.version = version;
   }

   public String getResource() {
      return resource;
   }
   public void setResource(String resource) {
      this.resource = resource;
   }

   public String getReferencePayload() {
      return referencePayload;
   }

   public void setReferencePayload(String referencePayload) {
      this.referencePayload = referencePayload;
   }
}
