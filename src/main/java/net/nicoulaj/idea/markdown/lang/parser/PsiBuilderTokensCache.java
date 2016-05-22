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
package net.nicoulaj.idea.markdown.lang.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.util.containers.ContainerUtil;
import net.nicoulaj.idea.markdown.lang.MarkdownElementType;
import org.intellij.markdown.parser.TokensCache;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PsiBuilderTokensCache extends TokensCache {
    @NotNull
    private final List<TokenInfo> cachedTokens;
    @NotNull
    private final List<TokenInfo> filteredTokens;
    @NotNull
    private final PsiBuilder builder;

    public PsiBuilderTokensCache(@NotNull PsiBuilder builder) {
        this.builder = builder;
        cachedTokens = ContainerUtil.newArrayList();
        filteredTokens = ContainerUtil.newArrayList();

        cacheTokens();
        verify();
    }

    private void cacheTokens() {
        PsiBuilder.Marker startMarker = builder.mark();

        for (int i = 0; builder.rawLookup(i) != null; ++i) {
            cachedTokens.add(new TokenInfo(MarkdownElementType.markdownType(builder.rawLookup(i)),
                                           builder.rawTokenTypeStart(i),
                                           builder.rawTokenTypeStart(i + 1),
                                           i,
                                           -1));
        }

        int listIndex = 0;
        int builderIndex = 0;
        while (builder.getTokenType() != null) {
            while (builder.getCurrentOffset() > cachedTokens.get(listIndex).getTokenStart()) {
                listIndex++;
            }
            assert  builder.getCurrentOffset() == cachedTokens.get(listIndex).getTokenStart();
            cachedTokens.get(listIndex).setNormIndex(builderIndex);
            filteredTokens.add(cachedTokens.get(listIndex));

            builder.advanceLexer();
            builderIndex++;
        }

        startMarker.rollbackTo();
    }

    @NotNull @Override public List<TokenInfo> getCachedTokens() {
        return cachedTokens;
    }

    @NotNull @Override public List<TokenInfo> getFilteredTokens() {
        return filteredTokens;
    }

    @NotNull @Override public CharSequence getOriginalText() {
        return builder.getOriginalText();
    }
}
