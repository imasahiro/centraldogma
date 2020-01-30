/*
 * Copyright 2020 LINE Corporation
 *
 * LINE Corporation licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.linecorp.centraldogma.testing.internal;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.extension.Extension;
import org.junit.jupiter.api.extension.ExtensionContext;

import com.linecorp.centraldogma.testing.junit.AbstractAllOrEachExtension;

/**
 * A JUnit {@link Extension} that creates a {@link TemporaryFolder}.
 *
 * <pre>{@code
 * > class MyTest {
 * >     @RegisterExtension
 * >     static final TemporaryFolderExtension folder = new TemporaryFolderExtension();
 * >
 * >     @Test
 * >     void test() throws Exception {
 * >         Path file = folder.newFile();
 * >         ...
 * >     }
 * > }
 * }</pre>
 */
public class TemporaryFolderExtension extends AbstractAllOrEachExtension {

    private final TemporaryFolder delegate;

    public TemporaryFolderExtension() {
        delegate = new TemporaryFolder();
    }

    @Override
    public void before(ExtensionContext context) throws Exception {
        delegate.create();
    }

    @Override
    public void after(ExtensionContext context) throws Exception {
        delegate.delete();
    }

    public void create() throws IOException {
        delegate.create();
    }

    public boolean exists() {
        return delegate.exists();
    }

    public Path getRoot() {
        return delegate.getRoot();
    }

    public Path newFolder() throws IOException {
        return delegate.newFolder();
    }

    public Path newFile() throws IOException {
        return delegate.newFile();
    }

    public void delete() throws IOException {
        delegate.delete();
    }
}
