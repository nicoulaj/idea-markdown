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
package net.nicoulaj.idea.markdown.braces;

import com.intellij.codeInsight.editorActions.QuoteHandler;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.highlighter.HighlighterIterator;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.tree.TokenSet;
import net.nicoulaj.idea.markdown.lang.MarkdownTokenTypes;
import org.jetbrains.annotations.NotNull;

public class MarkdownQuoteHandler implements QuoteHandler {
    private final static TokenSet QUOTE_TYPES = TokenSet.create(MarkdownTokenTypes.EMPH,
                                                                MarkdownTokenTypes.BACKTICK,
                                                                MarkdownTokenTypes.SINGLE_QUOTE,
                                                                MarkdownTokenTypes.DOUBLE_QUOTE,
                                                                MarkdownTokenTypes.CODE_FENCE_START);

    @Override public boolean isClosingQuote(HighlighterIterator iterator, int offset) {
        final CharSequence charsSequence = iterator.getDocument().getCharsSequence();
        final TextRange current = getRangeOfThisType(charsSequence, offset);

        final int prev = locateNextPosition(charsSequence, charsSequence.charAt(offset), current.getStartOffset() - 1, -1);
        return prev != -1 && getRangeOfThisType(charsSequence, prev).getLength() <= current.getLength();
    }

    @Override public boolean isOpeningQuote(HighlighterIterator iterator, int offset) {
        return QUOTE_TYPES.contains(iterator.getTokenType());
    }

    @Override public boolean hasNonClosedLiteral(Editor editor, HighlighterIterator iterator, int offset) {
        final CharSequence charsSequence = editor.getDocument().getCharsSequence();
        final TextRange current = getRangeOfThisType(charsSequence, offset);

        final int next = locateNextPosition(charsSequence, charsSequence.charAt(offset), current.getEndOffset(), +1);
        return next == -1 || getRangeOfThisType(charsSequence, next).getLength() < current.getLength();
    }

    @Override public boolean isInsideLiteral(HighlighterIterator iterator) {
        return false;
    }

    private TextRange getRangeOfThisType(@NotNull CharSequence charSequence, int offset) {
        final int length = charSequence.length();
        final char c = charSequence.charAt(offset);

        int l = offset, r = offset;
        while (l - 1 >= 0 && charSequence.charAt(l - 1) == c) {
            l--;
        }
        while (r + 1 < length && charSequence.charAt(r + 1) == c) {
            r++;
        }
        return TextRange.create(l, r + 1);
    }

    private int locateNextPosition(@NotNull CharSequence haystack, char needle, int from, int dx) {
        while (from >= 0 && from < haystack.length()) {
            final char currentChar = haystack.charAt(from);
            if (currentChar == needle) {
                return from;
            }
            else if (currentChar == '\n') {
                return -1;
            }

            from += dx;
        }
        return -1;
    }
}
