/*
 * Copyright (c) 2011-2015 Julien Nicoulaud <julien.nicoulaud@gmail.com>
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
package net.nicoulaj.idea.markdown.lang;

import com.intellij.psi.tree.IElementType;

import static net.nicoulaj.idea.markdown.lang.MarkdownElementType.platformType;

public interface MarkdownElementTypes {
    IElementType MARKDOWN_FILE = platformType(org.intellij.markdown.MarkdownElementTypes.MARKDOWN_FILE);

    IElementType UNORDERED_LIST = platformType(org.intellij.markdown.MarkdownElementTypes.UNORDERED_LIST);

    IElementType ORDERED_LIST = platformType(org.intellij.markdown.MarkdownElementTypes.ORDERED_LIST);

    IElementType LIST_ITEM = platformType(org.intellij.markdown.MarkdownElementTypes.LIST_ITEM);

    IElementType BLOCK_QUOTE = platformType(org.intellij.markdown.MarkdownElementTypes.BLOCK_QUOTE);

    IElementType CODE_FENCE = platformType(org.intellij.markdown.MarkdownElementTypes.CODE_FENCE);

    IElementType CODE_BLOCK = platformType(org.intellij.markdown.MarkdownElementTypes.CODE_BLOCK);

    IElementType CODE_SPAN = platformType(org.intellij.markdown.MarkdownElementTypes.CODE_SPAN);

    IElementType PARAGRAPH = platformType(org.intellij.markdown.MarkdownElementTypes.PARAGRAPH);

    IElementType EMPH = platformType(org.intellij.markdown.MarkdownElementTypes.EMPH);

    IElementType STRONG = platformType(org.intellij.markdown.MarkdownElementTypes.STRONG);

    IElementType LINK_DEFINITION = platformType(org.intellij.markdown.MarkdownElementTypes.LINK_DEFINITION);
    IElementType LINK_LABEL = platformType(org.intellij.markdown.MarkdownElementTypes.LINK_LABEL);
    IElementType LINK_DESTINATION = platformType(org.intellij.markdown.MarkdownElementTypes.LINK_DESTINATION);
    IElementType LINK_TITLE = platformType(org.intellij.markdown.MarkdownElementTypes.LINK_TITLE);
    IElementType LINK_TEXT = platformType(org.intellij.markdown.MarkdownElementTypes.LINK_TEXT);
    IElementType INLINE_LINK = platformType(org.intellij.markdown.MarkdownElementTypes.INLINE_LINK);
    IElementType FULL_REFERENCE_LINK = platformType(org.intellij.markdown.MarkdownElementTypes.FULL_REFERENCE_LINK);
    IElementType SHORT_REFERENCE_LINK = platformType(org.intellij.markdown.MarkdownElementTypes.SHORT_REFERENCE_LINK);

    IElementType AUTOLINK = platformType(org.intellij.markdown.MarkdownElementTypes.AUTOLINK);

    IElementType SETEXT_1 = platformType(org.intellij.markdown.MarkdownElementTypes.SETEXT_1);
    IElementType SETEXT_2 = platformType(org.intellij.markdown.MarkdownElementTypes.SETEXT_2);

    IElementType ATX_1 = platformType(org.intellij.markdown.MarkdownElementTypes.ATX_1);
    IElementType ATX_2 = platformType(org.intellij.markdown.MarkdownElementTypes.ATX_2);
    IElementType ATX_3 = platformType(org.intellij.markdown.MarkdownElementTypes.ATX_3);
    IElementType ATX_4 = platformType(org.intellij.markdown.MarkdownElementTypes.ATX_4);
    IElementType ATX_5 = platformType(org.intellij.markdown.MarkdownElementTypes.ATX_5);
    IElementType ATX_6 = platformType(org.intellij.markdown.MarkdownElementTypes.ATX_6);
}
