package com.backseatgamer.melchandcpmisery.events;

import com.backseatgamer.melchandcpmisery.ExecutionMode;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class SendErrorEvent extends BukkitBaseEvent{
    protected String errorMessage;

    public SendErrorEvent(String errorMessage, ExecutionMode executionMode) {
        super(executionMode);
        this.errorMessage = errorMessage;
    }

    public SendErrorEvent(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public void forEachPlayer(EntityPlayer player) {
        player.sendMessage(new TextComponentString(errorMessage).setStyle(new Style().setColor(TextFormatting.DARK_RED).setBold(true)));
    }
}
