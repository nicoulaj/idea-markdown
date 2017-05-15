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
package net.nicoulaj.idea.markdown.settings;

import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.Consumer;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * UI form for {@link MarkdownGlobalSettings} edition.
 *
 * @author Julien Nicoulaud <julien.nicoulaud@gmail.com>
 * @see MarkdownGlobalSettingsConfigurable
 * @see MarkdownGlobalSettings
 * @since 0.6
 */
public class MarkdownSettingsPanel {

    /** The parent panel for the form. */
    public JPanel panel;

    /** The "settings" form container. */
    public JPanel settingsPanel;

    /** Form element for {@link MarkdownGlobalSettings#parsingTimeout}. */
    public JSpinner parsingTimeoutSpinner;

    /** Description label for {@link #parsingTimeoutSpinner}. */
    private JLabel parsingTimeoutDescriptionLabel;

    /** The "extensions" form container. */
    public JPanel extensionsPanel;

    /** Form element for {@link MarkdownGlobalSettings#smarts}. */
    public JCheckBox smartsCheckBox;

    /** Form element for {@link MarkdownGlobalSettings#quotes}. */
    public JCheckBox quotesCheckBox;

    /** Form element for {@link MarkdownGlobalSettings#abbreviations}. */
    public JCheckBox abbreviationsCheckBox;

    /** Form element for {@link MarkdownGlobalSettings#hardWraps}. */
    public JCheckBox hardWrapsCheckBox;

    /** Form element for {@link MarkdownGlobalSettings#autoLinks}. */
    public JCheckBox autoLinksCheckBox;

    /** Form element for {@link MarkdownGlobalSettings#wikiLinks}. */
    public JCheckBox wikiLinksCheckBox;

    /** Form element for {@link MarkdownGlobalSettings#tables}. */
    public JCheckBox tablesCheckBox;

    /** Form element for {@link MarkdownGlobalSettings#definitions}. */
    public JCheckBox definitionsCheckBox;

    /** Form element for {@link MarkdownGlobalSettings#fencedCodeBlocks}. */
    public JCheckBox fencedCodeBlocksCheckBox;

    /** Form element for {@link MarkdownGlobalSettings#suppressHTMLBlocks}. */
    public JCheckBox suppressHTMLBlocksCheckBox;

    /** Form element for {@link MarkdownGlobalSettings#suppressInlineHTML}. */
    public JCheckBox suppressInlineHTMLCheckBox;

    /** Form element for {@link MarkdownGlobalSettings#strikethrough}. */
    public JCheckBox strikethroughCheckBox;

    private JRadioButton defaultStylesheetRadioButton;

    private JRadioButton customStylesheetRadioButton;

    private JTextField stylesheetLocationTextBox;

    private JButton stylesheetBrowseButton;

    /** Description label for {@link #suppressInlineHTMLCheckBox}. */
    private JLabel suppressInlineHTMLDescriptionLabel;

    /** Description label for {@link #suppressHTMLBlocksCheckBox}. */
    private JLabel suppressHTMLBlocksDescriptionLabel;

    /** Description label for {@link #fencedCodeBlocksCheckBox}. */
    private JLabel fencedCodeBlocksDescriptionLabel;

    /** Description label for {@link #definitionsCheckBox}. */
    private JLabel definitionsDescriptionLabel;

    /** Description label for {@link #tablesCheckBox}. */
    private JLabel tablesDescriptionLabel;

    /** Description label for {@link #autoLinksCheckBox}. */
    private JLabel autoLinksDescriptionLabel;

    /** Description label for {@link #wikiLinksCheckBox}. */
    private JLabel wikiLinksDescriptionLabel;

    /** Description label for {@link #hardWrapsCheckBox}. */
    private JLabel hardWarpsDescriptionLabel;

    /** Description label for {@link #abbreviationsCheckBox}. */
    private JLabel abbreviationsDescriptionLabel;

    /** Description label for {@link #quotesCheckBox}. */
    private JLabel quotesDescriptionLabel;

    /** Description label for {@link #smartsCheckBox}. */
    private JLabel smartsDescriptionLabel;

    /** Description label for {@link #strikethroughCheckBox}. */
    private JLabel strikethroughDescriptionLabel;

    public String getCustomStylesheetPath() {
        if (defaultStylesheetRadioButton.isSelected()) {
            return "";
        } else {
            return stylesheetLocationTextBox.getText();
        }
    }

    public void setCustomStylesheetPath(String customStylesheetPath) {
        if (customStylesheetPath == null || customStylesheetPath.trim().equals("")) {
            defaultStylesheetRadioButton.setSelected(true);
            stylesheetLocationTextBox.setEnabled(false);
            stylesheetBrowseButton.setEnabled(false);
        } else {
            stylesheetLocationTextBox.setText(customStylesheetPath);
            customStylesheetRadioButton.setSelected(true);
            stylesheetLocationTextBox.setEnabled(true);
            stylesheetBrowseButton.setEnabled(true);
        }
    }

    public void init() {
        // init the ui. we have several components that need to work together as a group: the stylesheet handlers
        defaultStylesheetRadioButton.addChangeListener(new ChangeListener() {
            @Override public void stateChanged(ChangeEvent changeEvent) {
                if (defaultStylesheetRadioButton.isSelected()) {
                    stylesheetLocationTextBox.setEnabled(false);
                    stylesheetBrowseButton.setEnabled(false);
                } else {
                    stylesheetLocationTextBox.setEnabled(true);
                    stylesheetBrowseButton.setEnabled(true);
                }
            }
        });

        stylesheetBrowseButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent actionEvent) {
                Consumer<VirtualFile> consumer = new Consumer<VirtualFile>(){
                    @Override public void consume(VirtualFile virtualFile) {
                        if (virtualFile != null && "css".equals(virtualFile.getExtension())) {
                            stylesheetLocationTextBox.setText(virtualFile.getPath());
                        }
                    }
                };
                FileChooser.chooseFile(new FileChooserDescriptor(true, false, false, false, false, false),
                                       null,
                                       stylesheetBrowseButton,
                                       null,
                                       consumer
                                       );
            }
        });
    }
}
