/*
 * Copyright (c) 2011-2013 Julien Nicoulaud <julien.nicoulaud@gmail.com>
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package net.nicoulaj.idea.markdown.highlighter;

import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.assertSame;

/**
 * MarkdownHighlighterColorsTest.
 *
 * @author <a href="mailto:lwr@coremail.cn">William Leung</a>
 */
public class MarkdownHighlighterColorsTest {

    @Test
    public void testInitialization() throws Exception {

        for (Field f : MarkdownHighlighterColors.class.getFields()) {
            TextAttributesKey value = (TextAttributesKey) f.get(null);
            System.out.println(value.getExternalName() + " -> " + value.getFallbackAttributeKey());
        }

        assertSame(DefaultLanguageHighlighterColors.STRING, MarkdownHighlighterColors.SPECIAL_TEXT_ATTR_KEY.getFallbackAttributeKey());
    }
}
