/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jclouds.openstack.ceilometer.v2.binders;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jclouds.http.HttpRequest;
import org.jclouds.openstack.ceilometer.v2.options.ComplexQueryOptions;
import org.jclouds.rest.MapBinder;

import javax.inject.Inject;
import java.util.Map;

public class BindQueryRequestPayload implements MapBinder {

   protected Gson gson;

   @Inject
   public BindQueryRequestPayload() {
      gson = new GsonBuilder().disableHtmlEscaping().create();
   }

   @Override
   public <R extends HttpRequest> R bindToRequest(R request, Map<String, Object> postParams) {
      Map<String, Object> map = Maps.newHashMap();

      ComplexQueryOptions options = (ComplexQueryOptions)postParams.get("options");

      if (options != null) {

         if (!isNullOrEmptyString(options.getFilter())) {
            map.put("filter", options.getFilter());
         }

         if (options.getLimit() != null) {
            map.put("limit", options.getLimit());
         }

         if (!isNullOrEmptyString(options.getOrderBy())) {
            map.put("orderby", options.getOrderBy());
         }
      }

      request.setPayload(gson.toJson(map));
      request.getPayload().getContentMetadata().setContentType("application/json");
      return request;
   }

   private boolean isNullOrEmptyString(Object value) {
      return (value == null) || ((String)value).isEmpty();
   }

   @Override
   public <R extends HttpRequest> R bindToRequest(R request, Object toBind) {
      throw new IllegalStateException("Illegal unwrap operation");
   }
}
