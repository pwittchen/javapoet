/*
 * Copyright (C) 2014 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.squareup.javawriter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static com.google.common.truth.Truth.assertThat;

@RunWith(JUnit4.class)
public final class WildcardNameTest {
  @Test public void simple() {
    WildcardName wild = WildcardName.create();
    assertThat(Writables.writeToString(wild)).isEqualTo("?");
  }

  @Test public void lowerBound() {
    ClassName string = ClassName.fromClass(String.class);
    WildcardName wild = WildcardName.createWithLowerBound(string);
    assertThat(Writables.writeToString(wild)).isEqualTo("? super java.lang.String");
  }

  @Test public void upperBound() {
    ClassName string = ClassName.fromClass(String.class);
    WildcardName wild = WildcardName.createWithUpperBound(string);
    assertThat(Writables.writeToString(wild)).isEqualTo("? extends java.lang.String");
  }

  @Test public void annotated() {
    WildcardName wild = WildcardName.create();
    wild.annotate(ClassName.create("test", "NonNull"));
    assertThat(Writables.writeToString(wild)).isEqualTo("@test.NonNull ?");
  }
}
