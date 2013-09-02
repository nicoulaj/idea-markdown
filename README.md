idea-markdown [![Build Status](https://buildhive.cloudbees.com/job/nicoulaj/job/idea-markdown/badge/icon)](https://buildhive.cloudbees.com/job/nicoulaj/job/idea-markdown/)
=============

**[Markdown](http://daringfireball.net/projects/markdown) language support for [IntelliJ IDEA](http://www.jetbrains.com/idea), [RubyMine](http://www.jetbrains.com/ruby), [PhpStorm](http://www.jetbrains.com/phpstorm), [WebStorm](http://www.jetbrains.com/webstorm), [PyCharm](http://www.jetbrains.com/pycharm), [AppCode](http://www.jetbrains.com/objc) and [Android Studio](http://developer.android.com/sdk/installing/studio.html).**

[Installation Guide](#installation-guide)

See the [official plugin page](http://plugins.intellij.net/plugin?id=5970).

[![idea-markdown](https://github.com/nicoulaj/idea-markdown/raw/assets/screenshots/preview.png)](http://plugins.intellij.net/plugin?id=5970)

---

### <a id="installation-guide"></a>Installation Guide

1. In your IntelliJ IDE go to Settings (Cmd-, on a mac)
2. In the left side setting navigation find "Plugins" and click on it
3. Click on "Browse repositories"
4. In the search box in the top right of the dialog, search for "Markdown"
5. Find this plugin and double click on it. (As of Sept 2013 this plugin is the only result)
6. Restart your IDE
7. After restarting, confirm successful installation by searching for "Markdown" within settings, within the "File Types" section.
8. Note: You may see the new "Markdown" section of the settings, but if you don't see it in the "File Types" then it did not install correctly.  If
this happens, go back to the Plugins section, delete the plugin, restart your IDE and start over
9. Once the installation succeeds, if you open any file with an .md or .markdown extension you will see the "Text" and "Preview" tabs at the bottom of code editor as seen in the above screenshot. They do what you expect them to do!

### Additional notes:

* [This page](https://github.com/MovingBlocks/Terasology/wiki/Markdown-and-Wiki) has some good tips on working with Github's markdown Including the fact that you need to enable "hard wraps" to use the right line break format for github. (To do this: under settings, go to the "Markdown" and under enable "hard wraps".)
* If the plugin still doesn't kick in - in some rare cases its possible that you mistakenly associated a SPECIFIC file in your project with
the wrong file type when you created it. For example, if you mistakenly associated README.md with the CSS file type. To fix this, go into your settings --> File Types, find the wrong file type (in this case, CSS) - you will see your file name listed among the list of filetype wildcard patterns, sitting there, mocking you. Remove that entry and re-open the file - all will be fine.

*This plugin is based on the excellent [PegDown](http://pegdown.org) library by [sirthias](https://github.com/sirthias). Greetings also to [JetBrains](http://www.jetbrains.com/) for giving away free licenses for IntelliJ IDEA for this project contributors.*
