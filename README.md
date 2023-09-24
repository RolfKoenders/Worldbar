# Worldbar
A quickly thrown together plugin that can show a bossBar with the name of the world the player is in.

## Motivation
The motivation for this plugin was that we have multiple worlds on our server (SMP) and some of those worlds reset daily. Some players arent always aware in which world they are, hence the creation of this plugin.

## Configurations
```yaml
# The prefix for the bar, in this example the bar for world "world_name" would say: "You are in: My world"
titlePrefix: "You are in:"
worlds:
  # "world_name" is the name of the world you want to configure
  world_name:
    # You can override the title for a specific world
    title: "My world"
    # The default bar color is PINK, but you can configure it per world.
    color: "PINK"
```

## Commands
| Command                           | Description                                                                                      | Permission         |
|-----------------------------------|--------------------------------------------------------------------------------------------------|--------------------|
| `/worldbar` or `/worldbar toggle` | Show or hide the worldbar                                                                          | `worldbar.toggle` |
| `/worldbar reload`                | Allow to reload the plugin config with `/worldbar reload` (Preferably only give this to staff/admin | `worldbar.reload`  |

## Preview
![Preview of the worldbar plugin](https://i.imgur.com/iyv3k2i.gif)
_in case gif doesn't load: https://i.imgur.com/iyv3k2i.gif_
