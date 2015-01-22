/*
 * Copyright (c) 2011-2014 Julien Nicoulaud <julien.nicoulaud@gmail.com>
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

import com.intellij.psi.tree.TokenSet;

/**
 * Token type sets for the Markdown language.
 *
 * @author Julien Nicoulaud <julien.nicoulaud@gmail.com>
 * @since 0.1
 */
public interface MarkdownTokenTypeSets extends MarkdownElementTypes {

    /** Plain text token type set. */
    TokenSet TEXT_SET = TokenSet.create(MarkdownTokenTypes.TEXT);

    /** Bold text token type set. */
    TokenSet BOLD_SET = TokenSet.create(STRONG);

    /** Italic token type set. */
    TokenSet ITALIC_SET = TokenSet.create(EMPH);

    /** Header of level 1 token type set. */
    TokenSet HEADER_LEVEL_1_SET = TokenSet.create(ATX_1, SETEXT_1, MarkdownTokenTypes.SETEXT_1);

    /** Header of level 2 token type set. */
    TokenSet HEADER_LEVEL_2_SET = TokenSet.create(ATX_2, SETEXT_2, MarkdownTokenTypes.SETEXT_2);

    /** Header of level 3 token type set. */
    TokenSet HEADER_LEVEL_3_SET = TokenSet.create(ATX_3);

    /** Header of level 4 token type set. */
    TokenSet HEADER_LEVEL_4_SET = TokenSet.create(ATX_4);

    /** Header of level 5 token type set. */
    TokenSet HEADER_LEVEL_5_SET = TokenSet.create(ATX_5);

    /** Header of level 6 token type set. */
    TokenSet HEADER_LEVEL_6_SET = TokenSet.create(ATX_6);

    /** Code token type set. */
    TokenSet CODE_SET = TokenSet.create(CODE_SPAN, CODE_FENCE, MarkdownTokenTypes.CODE);

    /** HRule token type set. */
    TokenSet HRULE_SET = TokenSet.create(MarkdownTokenTypes.HORIZONTAL_RULE);

    /** Link token type set. */
    TokenSet EXPLICIT_LINK_SET = TokenSet.create(INLINE_LINK);

    /** Reference link token type set. */
    TokenSet REFERENCE_LINK_SET = TokenSet.create(FULL_REFERENCE_LINK, SHORT_REFERENCE_LINK);

    /** Auto link token type set. */
    TokenSet AUTO_LINK_SET = TokenSet.create(AUTOLINK);

    /** Verbatim token type set. */
    TokenSet VERBATIM_SET = TokenSet.create(CODE_BLOCK);

    /** Block quote token type set. */
    TokenSet BLOCK_QUOTE_SET = TokenSet.create(BLOCK_QUOTE, MarkdownTokenTypes.BLOCK_QUOTE);

    /** Bullet list token type set. */
    TokenSet BULLET_LIST_SET = TokenSet.create(UNORDERED_LIST, MarkdownTokenTypes.LIST_BULLET);

    /** Ordered list token type set. */
    TokenSet ORDERED_LIST_SET = TokenSet.create(ORDERED_LIST, MarkdownTokenTypes.LIST_NUMBER);

    /** List item token type set. */
    TokenSet LIST_ITEM_SET = TokenSet.create(LIST_ITEM);

    /** Definition token type set. */
    TokenSet DEFINITION_SET = TokenSet.create(LINK_DEFINITION);

    /** HTML block token type set. */
    TokenSet HTML_BLOCK_SET = TokenSet.create(MarkdownTokenTypes.HTML_BLOCK);

    /** Inline HTML token type set. */
    TokenSet INLINE_HTML_SET = TokenSet.create(MarkdownTokenTypes.HTML_TAG);

}
