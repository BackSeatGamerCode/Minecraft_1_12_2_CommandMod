package com.backseatgamer.melchandcpmisery.events;

import com.backseatgamer.javasdk.events.BaseEvent;
import com.backseatgamer.melchandcpmisery.ExecutionMode;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.Random;

public abstract class BukkitBaseEvent extends BaseEvent {
    protected ExecutionMode executionMode;
    protected Random rand = new Random();

    public BukkitBaseEvent(ExecutionMode executionMode) {
        this.executionMode = executionMode;
    }

    public BukkitBaseEvent() {
        this(ExecutionMode.SINGLE_PLAYER);
    }

    public abstract void forEachPlayer(EntityPlayer player);

    public void sendKeepUpFailureMessage(EntityPlayer player){
        player.sendMessage(
                new TextComponentString(
                        "Failed to execute message. Could not keep up!"
                ).setStyle(
                        new Style()
                                .setColor(TextFormatting.DARK_RED)
                                .setBold(true)
                )
        );
    }

    @Override
    public void execute(Object... args) {
        World world = (World) args[0];
        switch (executionMode){
            case ALL_PLAYERS:
                for(EntityPlayer player : world.playerEntities){
                    forEachPlayer(player);
                }
                break;

            case RANDOM_PLAYER:
                forEachPlayer(
                        world.playerEntities.get(rand.nextInt(world.playerEntities.size()))
                );
                break;

            case SINGLE_PLAYER:
                forEachPlayer(world.playerEntities.get(0));
                break;
        }
    }
}
