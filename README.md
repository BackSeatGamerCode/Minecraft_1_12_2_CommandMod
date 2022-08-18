# Minecraft 1.12.2 CommandMod
A Mod which executes the command part of a reward as an in-game command. This architecture removes the need for any Java programming, and can easily interact with other installed mods.

## Setup
Download the latest .jar file from https://github.com/BackSeatGamerCode/Minecraft_1_12_2_CommandMod/releases/latest

Next you will need the `commands.json` file. You can use the official command file builder tool at https://backseatgamercode.github.io/CommandModFileManager/ to create it.

Once the file has been created, simply drop the JSON file in the root directory of your Minecraft installation, and place the .jar file in your mods directory. 

If you are unsure of where the mod will look file, simply fire up a single player world with the mod installed. You will get the following error message when the game loads:
```
The message.json file ([absolute path to file will be here]) was either missing or in an invalid format. If it was missing, then it has been created.
```

### CurseForge
If you are using CurseForge, simply right-click the modpack you want to install the mod into, and select `Open Folder`. 
Drop the `commands.json` file in the directory which opens in your file explorer. Finally, drop the .jar file in the `mods` sub-directory.

## Usage
Simply start a session over on the BackSeatGamer website https://backseatgamer.pythonanywhere.com 
(more info can be found https://backseatgamer.pythonanywhere.com/static/pdf/ModdingSetupGuide.pdf)

Next, start the [Reverse Proxy](https://github.com/BackSeatGamerCode/ReverseProxy/releases/latest) in `TCP/IP Broadcast` mode with `JSON` format.

## Issues
If you experience any problems with the mod, please leave a bug report over on our [Issue Tracker](https://github.com/BackSeatGamerCode/CommandModFileManager/issues)
