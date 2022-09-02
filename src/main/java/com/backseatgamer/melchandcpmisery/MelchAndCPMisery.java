package com.backseatgamer.melchandcpmisery;

import com.backseatgamer.melchandcpmisery.events.SendChatMessageEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandHandler;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.apache.logging.log4j.Logger;

import java.util.Collection;
import java.util.PriorityQueue;
import java.util.Queue;

@Mod(modid = MelchAndCPMisery.MODID, name = MelchAndCPMisery.NAME, version = MelchAndCPMisery.VERSION)
public class MelchAndCPMisery
{
    public static final String MODID = "melchandcpmisery";
    public static final String NAME = "Melch And CP Misery";
    public static final String VERSION = "1.0";

    private static Queue<String> messageQueue = new PriorityQueue<>();
    public static Queue<String> commandQueue = new PriorityQueue<>();

    private static BSGInterface bsgInterface;

    private static Logger logger;

    public static void announceReward(String message){
        commandQueue.add(
                "/tellraw @a {\"text\":\"" + message.replace("\"", "\\\"") + "\",\"color\":\"dark_green\"}"
        );
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        bsgInterface = new BSGInterface();

        MinecraftForge.EVENT_BUS.register(MelchAndCPMisery.class);

        if (!CommandManager.reloadFile()){
            messageQueue.add(
                    "The message.json file (" + CommandManager.getAbsolutePath() +") was either missing or in an invalid format. " +
                    "If it was missing, then it has been created"
            );
        }
    }

    @EventHandler
    public void serverInit(FMLServerStartingEvent event)
    {
        logger.info("init FMLServerStartingEvent :" + NAME);
        event.registerServerCommand(new ReloadCommand());
    }

    @SubscribeEvent
    public static void gameTick(TickEvent.PlayerTickEvent event){
        bsgInterface.poll(event.player.getEntityWorld());
        if(!messageQueue.isEmpty()){
            new SendChatMessageEvent(messageQueue.poll()).execute(event.player.getEntityWorld());
        }
//        MinecraftServer server = event.player.getServer();

        if (!commandQueue.isEmpty()){
            Minecraft.getMinecraft().player.sendChatMessage(commandQueue.poll());
        }

//        event.player.sendMessage(new TextComponentString("/tp @p ~ ~5 ~"));

//        Minecraft.getMinecraft().world.playerEntities.get(0).send

//        Minecraft.getMinecraft().player.sendChatMessage("/tp @p ~ ~ ~");
//        event.player.
//        if(server != null){
//            if (!commandQueue.isEmpty()){
//                server.commandManager.executeCommand(event.player, commandQueue.poll());
//
//            }
//        }
    }
}
