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
package net.nicoulaj.idea.markdown.lang.parser;

import com.intellij.lang.ASTNode;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiParser;
import com.intellij.psi.tree.IElementType;
import net.nicoulaj.idea.markdown.lang.MarkdownElementType;
import org.intellij.markdown.MarkdownElementTypes;
import org.intellij.markdown.ast.LeafASTNode;
import org.intellij.markdown.ast.visitors.RecursiveVisitor;
import org.intellij.markdown.parser.MarkdownParser;
import org.intellij.markdown.parser.dialects.commonmark.CommonMarkMarkerProcessor;
import org.jetbrains.annotations.NotNull;

/**
 * Parser implementation for Markdown.
 *
 * @author Julien Nicoulaud <julien.nicoulaud@gmail.com>
 * @since 0.1
 */
public class MarkdownParserAdapter implements PsiParser {

    /**
     * Parse the contents of the specified PSI builder and returns an AST tree with the
     * specified type of root element.
     *
     * @param root    the type of the root element in the AST tree.
     * @param builder the builder which is used to retrieve the original file tokens and build the AST tree.
     * @return the root of the resulting AST tree.
     */
    @NotNull
    public ASTNode parse(IElementType root, PsiBuilder builder) {

        PsiBuilder.Marker rootMarker = builder.mark();

        final PsiBuilderTokensCache tokensCache = new PsiBuilderTokensCache(builder);
        final org.intellij.markdown.ast.ASTNode parsedTree = new MarkdownParser(new CommonMarkMarkerProcessor.object.Factory())
                .parse(MarkdownElementTypes.MARKDOWN_FILE, tokensCache);

        assert builder.getCurrentOffset() == 0;
        new PsiBuilderFillingVisitor(builder).visitNode(parsedTree);

        rootMarker.done(root);

        return builder.getTreeBuilt();
    }

    private static class PsiBuilderFillingVisitor extends RecursiveVisitor {
        @NotNull private final PsiBuilder builder;

        public PsiBuilderFillingVisitor(@NotNull PsiBuilder builder) {
            this.builder = builder;
        }

        @Override public void visitNode(@NotNull org.intellij.markdown.ast.ASTNode node) {
            if (node instanceof LeafASTNode) {
                return;
            }

            ensureBuilderInPosition(node.getStartOffset());
            final PsiBuilder.Marker marker = builder.mark();

            super.visitNode(node);

            ensureBuilderInPosition(node.getEndOffset());
            marker.done(MarkdownElementType.platformType(node.getType()));
        }

        private void ensureBuilderInPosition(int position) {
            while (builder.getCurrentOffset() < position) {
                builder.advanceLexer();
            }

            if (builder.getCurrentOffset() != position) {
                throw new AssertionError("parsed tree and lexer are unsynchronized");
            }
        }
    }
}
