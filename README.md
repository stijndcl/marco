# jb-enhanced-macros

![Build](https://github.com/stijndcl/jb-enhanced-macros/workflows/Build/badge.svg)
[![Version](https://img.shields.io/jetbrains/plugin/v/MARKETPLACE_ID.svg)](https://plugins.jetbrains.com/plugin/MARKETPLACE_ID)
[![Downloads](https://img.shields.io/jetbrains/plugin/d/MARKETPLACE_ID.svg)](https://plugins.jetbrains.com/plugin/MARKETPLACE_ID)

## Template ToDo list

- [x] Create a new [IntelliJ Platform Plugin Template][template] project.
- [ ] Get familiar with the [template documentation][template].
- [ ] Adjust the [pluginGroup](./gradle.properties) and [pluginName](./gradle.properties), as well as
  the [id](./src/main/resources/META-INF/plugin.xml) and [sources package](./src/main/kotlin).
- [ ] Adjust the plugin description in `README` (see [Tips][docs:plugin-description])
- [ ] Review
  the [Legal Agreements](https://plugins.jetbrains.com/docs/marketplace/legal-agreements.html?from=IJPluginTemplate).
- [ ] [Publish a plugin manually](https://plugins.jetbrains.com/docs/intellij/publishing-plugin.html?from=IJPluginTemplate)
  for the first time.
- [ ] Set the `MARKETPLACE_ID` in the above README badges. You can obtain it once the plugin is published to JetBrains
  Marketplace.
- [ ] Set the [Plugin Signing](https://plugins.jetbrains.com/docs/intellij/plugin-signing.html?from=IJPluginTemplate)
  related [secrets](https://github.com/JetBrains/intellij-platform-plugin-template#environment-variables).
- [ ] Set
  the [Deployment Token](https://plugins.jetbrains.com/docs/marketplace/plugin-upload.html?from=IJPluginTemplate).
- [ ] Click the <kbd>Watch</kbd> button on the top of the [IntelliJ Platform Plugin Template][template] to be notified
  about releases containing new features and fixes.

<!-- Plugin description -->
Supercharge your run configurations with additional macros.
<!-- Plugin description end -->

## Available macros & usage

IntelliJ macros with parameters generally only allow one parameter to be passed. To get around this, this plugin
attempts to parse your arguments as you would pass them to a normal function in any programming language: separated by
commas.

Spaces between arguments are allowed, but not required (and **will be stripped out**). However, do note that
IntelliJ's program arguments preview window tends to split your arguments into new lines on `", "`, so if you use this
window you should refrain from using spaces.

As every argument is implicitly a string, quotes are _not required_. However, they are allowed in case you want to use a
comma inside an argument.

```bash
$Replace(inputString, input, output)$
# Produces: outputString

$Replace("a sentence, with commas", ", with commas", without commas)$
# Produces: a sentence without commas
```

Note that it **is** possible to use other macros as input for these arguments:

```bash
$Replace($FileName$, .java, .kt)$
$Replace($RemoveSuffix($FileName$, .example), .java, .kt$)$
```

| Macro                                  | Description                            | Example                      |
|----------------------------------------|----------------------------------------|------------------------------|
| `RemovePrefix(input, prefix)`          | Remove a prefix from a string.         | `$RemovePrefix(input, in)$`  |
| `RemoveSuffix(input, suffix)`          | Remove a suffix from a string.         | `$RemoveSuffix(input, put)$` |
| `Replace(input, pattern, replacement)` | Replace part of a string with another. | `$Replace(input, in, out)$`  |

## Installation

- Using the IDE built-in plugin system:

  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>Marketplace</kbd> > <kbd>Search for "Enhanced
  Macros"</kbd> >
  <kbd>Install</kbd>

- Using JetBrains Marketplace:

  Go to [JetBrains Marketplace](https://plugins.jetbrains.com/plugin/MARKETPLACE_ID) and install it by clicking
  the <kbd>Install to ...</kbd> button in case your IDE is running.

  You can also download the [latest release](https://plugins.jetbrains.com/plugin/MARKETPLACE_ID/versions) from
  JetBrains Marketplace and install it manually using
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>⚙️</kbd> > <kbd>Install plugin from disk...</kbd>

- Manually:

  Download the [latest release](https://github.com/stijndcl/jb-enhanced-macros/releases/latest) and install it manually
  using
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>⚙️</kbd> > <kbd>Install plugin from disk...</kbd>

---
Plugin based on the [IntelliJ Platform Plugin Template][template].

[template]: https://github.com/JetBrains/intellij-platform-plugin-template

[docs:plugin-description]: https://plugins.jetbrains.com/docs/intellij/plugin-user-experience.html#plugin-description-and-presentation
