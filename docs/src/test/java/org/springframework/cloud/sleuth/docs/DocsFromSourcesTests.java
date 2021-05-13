/*
 * Copyright 2013-2020 the original author or authors.
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

package org.springframework.cloud.sleuth.docs;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;

class DocsFromSourcesTests {

	@Test
	void should_build_a_table_out_of_enum_tag_key() throws IOException {
		File root = new File(".");
		File output = new File(root, "target");

		new DocsFromSources(root, ".*", output).generate();

		BDDAssertions.then(new String(Files.readAllBytes(new File(output, "_tags.adoc").toPath())))
				.contains("|bar|Some description.");
	}

}