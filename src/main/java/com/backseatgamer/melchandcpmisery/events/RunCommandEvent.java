package com.backseatgamer.melchandcpmisery.events;

import com.backseatgamer.melchandcpmisery.ExecutionMode;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

public class RunCommandEvent extends BukkitBaseEvent{
    protected String command;

    public RunCommandEvent(String command, ExecutionMode executionMode) {
        super(executionMode);
        this.command = command;
    }

    public RunCommandEvent(String command) {
        this.command = command;
    }

    @Override
    public void forEachPlayer(EntityPlayer player) {
        MinecraftServer server = player.getServer();
        if (server == null) {
            sendKeepUpFailureMessage(player);
        } else {
            server.commandManager.executeCommand(player, command);
        }
    }
}
