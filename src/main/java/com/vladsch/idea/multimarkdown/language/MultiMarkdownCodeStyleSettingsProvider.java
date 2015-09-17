package com.vladsch.idea.multimarkdown.language;

import com.intellij.application.options.CodeStyleAbstractConfigurable;
import com.intellij.application.options.CodeStyleAbstractPanel;
import com.intellij.application.options.TabbedLanguageCodeStylePanel;
import com.intellij.openapi.options.Configurable;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.CodeStyleSettingsProvider;
import com.intellij.psi.codeStyle.CustomCodeStyleSettings;
import com.intellij.psi.codeStyle.LanguageCodeStyleSettingsProvider;
import com.vladsch.idea.multimarkdown.MultiMarkdownLanguage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MultiMarkdownCodeStyleSettingsProvider extends CodeStyleSettingsProvider {
    @Override
    public CustomCodeStyleSettings createCustomSettings(CodeStyleSettings settings) {
        return new MultiMarkdownCodeStyleSettings(settings);
    }

    @Nullable
    @Override
    public String getConfigurableDisplayName() {
        return MultiMarkdownLanguage.NAME;
    }

    @NotNull
    @Override
    public Configurable createSettingsPage(CodeStyleSettings settings, CodeStyleSettings originalSettings) {
        return new CodeStyleAbstractConfigurable(settings, originalSettings, MultiMarkdownLanguage.NAME) {
            @Override
            protected CodeStyleAbstractPanel createPanel(CodeStyleSettings settings) {
                return new CodeStyleMainPanel(getCurrentSettings(), settings);
            }

            @Nullable
            @Override
            public String getHelpTopic() {
                return null;
            }
        };
    }

    private static class CodeStyleMainPanel extends TabbedLanguageCodeStylePanel {
        public CodeStyleMainPanel(CodeStyleSettings currentSettings, CodeStyleSettings settings) {
            super(MultiMarkdownLanguage.INSTANCE, currentSettings, settings);
        }
        /**
         * Initializes all standard tabs: "Tabs and Indents", "Spaces", "Blank Lines" and "Wrapping and Braces" if relevant.
         * For "Tabs and Indents" LanguageCodeStyleSettingsProvider must instantiate its own indent options, for other standard tabs it
         * must return false in usesSharedPreview() method. You can override this method to add your own tabs by calling super.initTabs() and
         * then addTab() methods or selectively add needed tabs with your own implementation.
         * @param settings  Code style settings to be used with initialized panels.
         * @see LanguageCodeStyleSettingsProvider
         * @see #addIndentOptionsTab(com.intellij.psi.codeStyle.CodeStyleSettings)
         * @see #addSpacesTab(com.intellij.psi.codeStyle.CodeStyleSettings)
         * @see #addBlankLinesTab(com.intellij.psi.codeStyle.CodeStyleSettings)
         * @see #addWrappingAndBracesTab(com.intellij.psi.codeStyle.CodeStyleSettings)
         */
        protected void initTabs(CodeStyleSettings settings) {
            LanguageCodeStyleSettingsProvider provider = LanguageCodeStyleSettingsProvider.forLanguage(getDefaultLanguage());
            addIndentOptionsTab(settings);
/*
            if (provider != null) {
                addSpacesTab(settings);
                addWrappingAndBracesTab(settings);
                addBlankLinesTab(settings);
            }
*/
        }

    }
}
