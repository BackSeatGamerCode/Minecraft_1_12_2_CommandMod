package com.backseatgamer.melchandcpmisery;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class ReloadCommand extends CommandBase {
    @Override
    public String getName() {
        return "reload";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "command.reload.usage";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (CommandManager.reloadFile()) {
            for (EntityPlayer player : server.getPlayerList().getPlayers()){
                player.sendMessage(new TextComponentString(
                        "Successfully reloaded command file"
                ).setStyle(new Style().setColor(TextFormatting.DARK_GREEN)));
            }
        } else {
            for (EntityPlayer player : server.getPlayerList().getPlayers()){
                player.sendMessage(new TextComponentString(
                        "The message.json file (" + CommandManager.getAbsolutePath() + ") was either missing or in an invalid format. " +
                        "If it was missing, then it has been created"
                ).setStyle(new Style().setColor(TextFormatting.DARK_RED).setBold(true)));
            }
        }
    }
}
