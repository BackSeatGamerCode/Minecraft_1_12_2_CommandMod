package com.backseatgamer.melchandcpmisery.events;

import com.backseatgamer.melchandcpmisery.ExecutionMode;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;

public class SendChatMessageEvent extends BukkitBaseEvent{
    protected String message;

    public SendChatMessageEvent(String message, ExecutionMode executionMode) {
        super(executionMode);
        this.message = this.message;
    }

    public SendChatMessageEvent(String command) {
        this.message = command;
    }

    @Override
    public void forEachPlayer(EntityPlayer player) {
        player.sendMessage(new TextComponentString(message));
    }
}
