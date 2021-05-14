/*
 * Copyright 2013-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.cloud.sleuth.instrument.circuitbreaker;

import java.util.function.Function;
import java.util.function.Supplier;

import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.sleuth.docs.DocumentedSpan;

enum SleuthCircuitBreakerSpan implements DocumentedSpan {

	/**
	 * Span created when we wrap a {@link Supplier} passed to the {@link CircuitBreaker}.
	 */
	SUPPLIER_SPAN {
		@Override
		public String getName() {
			return "%s";
		}
	},

	/**
	 * Span created when we wrap a {@link Function} passed to the {@link CircuitBreaker}
	 * as fallback.
	 */
	FUNCTION_SPAN {
		@Override
		public String getName() {
			return "%s";
		}
	}

}
