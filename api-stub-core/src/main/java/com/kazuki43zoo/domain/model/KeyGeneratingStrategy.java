/*
 *    Copyright 2016-2017 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.kazuki43zoo.domain.model;

import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public enum KeyGeneratingStrategy {

    FIRST {
        @Override
        public String generate(List<Object> keys) {
            if (keys == null || keys.isEmpty()) {
                return null;
            }
            return keys.stream().filter(Objects::nonNull).map(Objects::toString).filter(StringUtils::hasLength).findFirst().orElse(null);
        }
    },
    ALL {
        @Override
        public String generate(List<Object> keys) {
            if (keys == null || keys.isEmpty()) {
                return null;
            }
            StringJoiner stringJoiner = new StringJoiner("/");
            keys.stream().map(Objects::toString).forEach(stringJoiner::add);
            return stringJoiner.toString();
        }
    };

    public abstract String generate(List<Object> values);

}
