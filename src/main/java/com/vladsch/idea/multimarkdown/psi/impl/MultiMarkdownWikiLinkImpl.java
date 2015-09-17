/*
 * Copyright (c) 2015-2015 Vladimir Schneider <vladimir.schneider@gmail.com>
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
package com.vladsch.idea.multimarkdown.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.sun.istack.internal.Nullable;
import com.vladsch.idea.multimarkdown.psi.MultiMarkdownFile;
import com.vladsch.idea.multimarkdown.psi.MultiMarkdownVisitor;
import com.vladsch.idea.multimarkdown.psi.MultiMarkdownWikiLink;
import org.jetbrains.annotations.NotNull;

public class MultiMarkdownWikiLinkImpl extends ASTWrapperPsiElement implements MultiMarkdownWikiLink {
    public static String getElementText(@NotNull String name, @Nullable String title) {
        String displayName = MultiMarkdownFile.makeLinkRef(name);
        return "[[" + displayName + (title != null && title.length() > 0 && !displayName.equals(title) ? "|" + title : "") + "]]";
    }

    public MultiMarkdownWikiLinkImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof MultiMarkdownVisitor) ((MultiMarkdownVisitor) visitor).visitElement(this);
        else super.accept(visitor);
    }

    @Override
    public String getPageTitle() {
        return MultiMarkdownPsiImplUtil.getPageTitle(this);
    }

    @Override
    public String getFileName() {
        return MultiMarkdownFile.makeFileName(getName(), true);
    }
}
