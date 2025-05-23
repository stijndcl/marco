# marco

![Build](https://github.com/stijndcl/marco/workflows/Build/badge.svg)
[![Version](https://img.shields.io/jetbrains/plugin/v/27378-marco.svg)](https://plugins.jetbrains.com/plugin/27378-marco)
[![Downloads](https://img.shields.io/jetbrains/plugin/d/27378-marco.svg)](https://plugins.jetbrains.com/plugin/27378-marco)

<!-- Plugin description -->
Supercharge your run configurations with powerful new IDE macros.

This plugin provides support for various utility macros such as

- Remembering user input values for subsequent runs, persisting across IDE restarts and run configurations
- Prompting enums in a choice dialog
- Common string manipulations (slicing, replacing, altering case, etc.)
- Familiar function-call-like syntax
- Support for multiple arguments

<!-- Plugin description end -->

## Usage notes

IntelliJ macros with parameters only allow one parameter to be passed. To get around this, this plugin
attempts to parse your arguments as you would pass them to a normal function in any programming language: separated by
commas.

As every macro argument is implicitly a string, quotes are _not required_. However, they are _allowed_ in case you want
to use a comma inside an argument.

```bash
$Replace(inputString, input, output)$
# Produces: outputString

$Replace("a sentence, with commas", ", with commas", without commas)$
# Produces: a sentence without commas
```

> [!WARNING]
> Spaces between comma-separated arguments are allowed, but not required (and **will be stripped out by the parser**).
> However, note that IntelliJ's expanded arguments window tends to split your arguments into new lines on `", "`, so if
> you use this window often you should refrain from using spaces. Examples here will however always use spaces for
> readability's sake.

```bash
$Replace(inputString,input,output)$
# Produces: outputString

# This is also allowed
$Replace(inputString, input, output)$
# Produces: outputString
```

> [!WARNING]
> It **is** possible to use other macros as input for these arguments, but currently they may **only** be "basic" macros
> **without arguments**:

```bash
# This will work
$Replace($FileName$, .java, .kt)$
# Produces: MyClass.kt

# This will not
$Replace($Lowercase($FileName$), .java, .kt)$
# Produces: $Replace(main.kt)$
```

## Available macros

| Macro                                                           | Description                                                                                                                                                                                                                                                                                                                                      | Example                                                              |
|-----------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------|
| `AfterNth(input, n?)`                                           | Extract the part of a string after the nth occurrence a given pattern (defaults to `n = 1`).                                                                                                                                                                                                                                                     | `$AfterNth(github.com/stijndcl/jb-marco, /, 2)$`                     |
| `Choice(prompt, Option 1, Option 2, ..., Option N)`             | Prompt a choice from a list of options. Options may specify an associated value using a colon (`Name:value`).                                                                                                                                                                                                                                    | `$Choice(Weekday, Monday:0, Tuesday:1, ...)$`                        |
| `Lowercase(input)`                                              | Convert a string to lowercase.                                                                                                                                                                                                                                                                                                                   | `$Lowercase(input)$`                                                 |
| `Remember(id, prompt, default?)`                                | Prompt an input value and cache it for subsequent runs, persisted across IDE restarts. The `id` can be used to match (or prevent matching) a cached value across multiple different run configurations.                                                                                                                                          | `$Remember(date, Enter date, 01/01/1970)$`                           |
| `RememberChoice(id, prompt, Option 1, Option 2, ..., Option N)` | Prompt a choice from a list of options and cache it for subsequent runs, persisted across IDE restarts. The `id` can be used to match (or prevent matching) a cached value across multiple different run configurations. Options may specify an associated value using a colon (`key:value`). **Note that both keys and values must be unique**. | `$RememberChoice(weekday, Enter weekday, Monday:0, Tuesday:1, ...)$` | 
| `RemovePrefix(input, prefix)`                                   | Remove a prefix from a string.                                                                                                                                                                                                                                                                                                                   | `$RemovePrefix(input, in)$`                                          |
| `RemoveSuffix(input, suffix)`                                   | Remove a suffix from a string.                                                                                                                                                                                                                                                                                                                   | `$RemoveSuffix(input, put)$`                                         |
| `Replace(input, pattern, replacement)`                          | Replace part of a string with another.                                                                                                                                                                                                                                                                                                           | `$Replace(input, in, out)$`                                          |
| `Slice(input, range)`                                           | Slice a substring using Python's slicing syntax.                                                                                                                                                                                                                                                                                                 | `$Slice(input, 1:2)$`                                                |
| `Slice(input, range, separator)`                                | Split a string using a delimiter, and slice the resulting array using Python's slicing syntax.                                                                                                                                                                                                                                                   | `$Slice("a,b,c,d", :-1, ",")$`                                       |
| `UnixNow`                                                       | Insert the current unix timestamp (in seconds).                                                                                                                                                                                                                                                                                                  | `$UnixNow$`                                                          |                                                                                                                                                                                                                                                                   
| `Uppercase(input)`                                              | Convert a string to uppercase.                                                                                                                                                                                                                                                                                                                   | `$Uppercase(input)$`                                                 |

## Installation

- Using the IDE built-in plugin system:

  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>Marketplace</kbd> > <kbd>Search for "Marco"</kbd> > <kbd>
  Install</kbd>

- Using JetBrains Marketplace:

  Go to [JetBrains Marketplace](https://plugins.jetbrains.com/plugin/27378-marco) and install it by clicking
  the <kbd>Install to ...</kbd> button in case your IDE is running.

  You can also download the [latest release](https://plugins.jetbrains.com/plugin/27378-marco/versions) from
  JetBrains Marketplace and install it manually using
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>⚙️</kbd> > <kbd>Install plugin from disk...</kbd>

- Manually:

  Download the [latest release](https://github.com/stijndcl/marco/releases/latest) and install it manually
  using
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>⚙️</kbd> > <kbd>Install plugin from disk...</kbd>

---
Plugin based on the [IntelliJ Platform Plugin Template][template].

[template]: https://github.com/JetBrains/intellij-platform-plugin-template

[docs:plugin-description]: https://plugins.jetbrains.com/docs/intellij/plugin-user-experience.html#plugin-description-and-presentation
