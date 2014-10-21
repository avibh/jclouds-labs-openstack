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
package org.jclouds.openstack.ceilometer.v2.options;

import com.google.common.base.Objects;

/**
 * A Complex Query the Ceilometer server knows about
 *
 * @see <a href= "http://developer.openstack.org/api-ref-telemetry-v2.html" />
 * @see <a href= "https://github.com/openstack/ceilometer/tree/master/" />
 */
public class ComplexQueryOptions  {

   public static Builder builder() {
      return new Builder();
   }

   public Builder toBuilder() {
      return new Builder().fromComplexQueryOptions(this);
   }

   public static class Builder {

      protected String filter;
      protected Integer limit;
      protected String orderBy;

      public Builder filter(String filter) {
         this.filter = filter;
         return this;
      }

      public Builder limit(int limit) {
         this.limit = limit;
         return this;
      }

      public Builder orderBy(String orderBy) {
         this.orderBy = orderBy;
         return this;
      }

      public ComplexQueryOptions build() {
         return new ComplexQueryOptions(filter, limit, orderBy);
      }

      public Builder fromComplexQueryOptions(ComplexQueryOptions in) {
         return this
               .filter(in.filter)
               .limit(in.limit)
               .orderBy(in.orderBy);
      }
   }

   protected String filter;
   protected Integer limit;
   protected String orderBy;

   public ComplexQueryOptions(String filter, int limit, String orderBy) {
      this.filter = filter;
      this.limit = limit;
      this.orderBy = orderBy;
   }

   public String getFilter() {
      return filter;
   }

   public Integer getLimit() {
      return limit;
   }

   public String getOrderBy() {
      return orderBy;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      ComplexQueryOptions complexQueryOptions = (ComplexQueryOptions) o;

      return Objects.equal(this.filter, complexQueryOptions.filter) &&
            Objects.equal(this.limit, complexQueryOptions.limit) &&
            Objects.equal(this.orderBy, complexQueryOptions.orderBy);
   }

   @Override
   public int hashCode() {
      return Objects.hashCode(filter, limit, orderBy);
   }

   protected Objects.ToStringHelper string() {
      return Objects.toStringHelper(this)
            .add("filter", filter).add("limit", limit).add("orderBy", orderBy);
   }

   @Override
   public String toString() {
      return string().toString();
   }
}
