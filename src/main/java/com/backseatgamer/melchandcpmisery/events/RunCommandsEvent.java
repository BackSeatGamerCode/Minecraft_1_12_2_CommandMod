package com.backseatgamer.melchandcpmisery.events;

import com.backseatgamer.melchandcpmisery.ExecutionMode;
import com.backseatgamer.melchandcpmisery.MelchAndCPMisery;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

import java.util.Arrays;

public class RunCommandsEvent extends BukkitBaseEvent{
    protected String[] commands;

    public RunCommandsEvent(String[] commands, ExecutionMode executionMode) {
        super(executionMode);
        this.commands = commands;
    }

    public RunCommandsEvent(String[] commands) {
        this.commands = commands;
    }

    @Override
    public void forEachPlayer(EntityPlayer player) {
        MelchAndCPMisery.commandQueue.addAll(Arrays.asList(commands));
    }
}
