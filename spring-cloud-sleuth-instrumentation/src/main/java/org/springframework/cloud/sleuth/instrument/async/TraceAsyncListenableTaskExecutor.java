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

package org.springframework.cloud.sleuth.instrument.async;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import org.springframework.cloud.sleuth.SpanNamer;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.core.task.AsyncListenableTaskExecutor;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * AsyncListenableTaskExecutor that wraps all Runnable / Callable tasks into their trace
 * related representation.
 *
 * @author Marcin Grzejszczak
 * @since 1.0.0
 */
// public as most types in this package were documented for use
public class TraceAsyncListenableTaskExecutor implements AsyncListenableTaskExecutor {

	private final AsyncListenableTaskExecutor delegate;

	private final Tracer tracer;

	private final SpanNamer spanNamer;

	TraceAsyncListenableTaskExecutor(AsyncListenableTaskExecutor delegate, Tracer tracer, SpanNamer spanNamer) {
		this.delegate = delegate;
		this.tracer = tracer;
		this.spanNamer = spanNamer;
	}

	@Override
	public ListenableFuture<?> submitListenable(Runnable task) {
		return this.delegate.submitListenable(new TraceRunnable(this.tracer, this.spanNamer, task));
	}

	@Override
	public <T> ListenableFuture<T> submitListenable(Callable<T> task) {
		return this.delegate.submitListenable(new TraceCallable<>(this.tracer, this.spanNamer, task));
	}

	@Override
	public void execute(Runnable task, long startTimeout) {
		this.delegate.execute(new TraceRunnable(this.tracer, this.spanNamer, task), startTimeout);
	}

	@Override
	public Future<?> submit(Runnable task) {
		return this.delegate.submit(new TraceRunnable(this.tracer, this.spanNamer, task));
	}

	@Override
	public <T> Future<T> submit(Callable<T> task) {
		return this.delegate.submit(new TraceCallable<>(this.tracer, this.spanNamer, task));
	}

	@Override
	public void execute(Runnable task) {
		this.delegate.execute(new TraceRunnable(this.tracer, this.spanNamer, task));
	}

}
