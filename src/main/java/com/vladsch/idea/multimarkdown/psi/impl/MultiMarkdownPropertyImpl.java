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
 *
 * This file is based on the IntelliJ SimplePlugin tutorial
 *
 */
package com.vladsch.idea.multimarkdown.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.vladsch.idea.multimarkdown.psi.MultiMarkdownProperty;
import com.vladsch.idea.multimarkdown.psi.MultiMarkdownVisitor;
import org.jetbrains.annotations.NotNull;

public class MultiMarkdownPropertyImpl extends MultiMarkdownNamedElementImpl implements MultiMarkdownProperty {
    public MultiMarkdownPropertyImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof MultiMarkdownVisitor) ((MultiMarkdownVisitor) visitor).visitProperty(this);
        else super.accept(visitor);
    }

    public String getKey() {
        return MultiMarkdownPsiImplUtil.getKey(this);
    }

    public String getDisplayName() {
        return getKey();
    }

    public String getValue() {
        return MultiMarkdownPsiImplUtil.getValue(this);
    }

    public String getName() {
        return MultiMarkdownPsiImplUtil.getName(this);
    }

    public PsiElement setName(String newName) {
        return MultiMarkdownPsiImplUtil.setName(this, newName);
    }

    public PsiElement getNameIdentifier() {
        return MultiMarkdownPsiImplUtil.getNameIdentifier(this);
    }

    public ItemPresentation getPresentation() {
        return MultiMarkdownPsiImplUtil.getPresentation(this);
    }
}
