/*
 * Copyright (c) 2011-2013 Julien Nicoulaud <julien.nicoulaud@gmail.com>
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"), you may not use this file except in compliance
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

package net.nicoulaj.idea.markdown;


import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.psi.PsiFile;
import net.nicoulaj.idea.markdown.highlighter.MarkdownHighlighterColorsForIDEA11;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Compatibility for IDEA 11.
 *
 * @author <a href="mailto:lwr@coremail.cn">William Leung</a>
 */
@SuppressWarnings("UnusedDeclaration")
public class Compatibility {

    public static abstract class ExternalAnnotatorHack<InitialInfoType, AnnotationResultType>
            extends com.intellij.lang.annotation.ExternalAnnotator<InitialInfoType, AnnotationResultType> {

        @Nullable
        @SuppressWarnings("UnusedDeclaration")
        // @Override for IDEA11 / IDEA12 compatibility
        public final InitialInfoType collectionInformation(@NotNull PsiFile file) {
            return collectInformation(file);
        }


        @Nullable
        @SuppressWarnings("UnusedDeclaration")
        // @Override for IDEA13 compatibility
        public final InitialInfoType collectInformation(@NotNull PsiFile file) {
            return hackCollectInformation(file);
        }


        @Nullable // the hacking implementation should defined in subclass
        public abstract InitialInfoType hackCollectInformation(@NotNull PsiFile file);
    }


    public static TextAttributesKey createTextAttributesKey(String externalName,
                                                            DefaultLanguageHighlighterColorsEnum fallbackAttributeKeyEnum) {
        try {
            @SuppressWarnings("SpellCheckingInspection")
            Class c = Class.forName("com.intellij.openapi.editor.DefaultLanguageHighlighterColors");
            TextAttributesKey fallbackAttributeKey = (TextAttributesKey) c.getField(fallbackAttributeKeyEnum.name()).get(null);

            java.lang.reflect.Method m = TextAttributesKey.class.getMethod("createTextAttributesKey", String.class, TextAttributesKey.class);
            return (TextAttributesKey) m.invoke(null, externalName, fallbackAttributeKey);
        } catch (Exception e) {
            for (java.lang.reflect.Field f : MarkdownHighlighterColorsForIDEA11.class.getFields()) {
                try {
                    TextAttributesKey value = (TextAttributesKey) f.get(null);
                    if (externalName.equals(value.getExternalName())) {
                        return value;
                    }
                } catch (Exception e1) {
                    // ignore
                }
            }

            // There is one missing entry in old version: 'MARKDOWN.TABLE_CAPTION'
            // just ignore this type of issue with default markup attributes
            //      throw new Error("Can not initiate TextAttributesKey for: " + externalName);
            @SuppressWarnings({"UnnecessaryLocalVariable", "deprecation"}) TextAttributesKey result = TextAttributesKey.createTextAttributesKey(
                    externalName, new com.intellij.openapi.editor.markup.TextAttributes(null, null, null, null, java.awt.Font.PLAIN)
            );
            return result;
        }
    }


    public enum DefaultLanguageHighlighterColorsEnum {
        TEMPLATE_LANGUAGE_COLOR,
        IDENTIFIER,
        NUMBER,
        KEYWORD,
        STRING,
        BLOCK_COMMENT,
        LINE_COMMENT,
        DOC_COMMENT,
        OPERATION_SIGN,
        BRACES,
        DOT,
        SEMICOLON,
        COMMA,
        PARENTHESES,
        BRACKETS,
        LABEL,
        CONSTANT,
        LOCAL_VARIABLE,
        GLOBAL_VARIABLE,
        FUNCTION_DECLARATION,
        FUNCTION_CALL,
        PARAMETER,
        CLASS_NAME,
        INTERFACE_NAME,
        CLASS_REFERENCE,
        INSTANCE_METHOD,
        INSTANCE_FIELD,
        STATIC_METHOD,
        STATIC_FIELD,
        DOC_COMMENT_MARKUP,
        DOC_COMMENT_TAG,
        DOC_COMMENT_TAG_VALUE,
        VALID_STRING_ESCAPE,
        INVALID_STRING_ESCAPE,
        PREDEFINED_SYMBOL,
        METADATA,
        MARKUP_TAG,
        MARKUP_ATTRIBUTE,
        MARKUP_ENTITY,
    }
}
