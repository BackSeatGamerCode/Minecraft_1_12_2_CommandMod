package com.backseatgamer.melchandcpmisery;

import com.backseatgamer.javasdk.BSGJavaSDK;
import com.backseatgamer.javasdk.events.BaseEvent;
import com.backseatgamer.javasdk.models.Redemption;
import com.backseatgamer.melchandcpmisery.events.RunCommandsEvent;
import com.backseatgamer.melchandcpmisery.events.SendErrorEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BSGInterface extends BSGJavaSDK {
    @Override
    protected void onRedemptionReceived(Redemption redemption, Object... args) {
        MelchAndCPMisery.announceReward(redemption.toMessage());
    }

    @Override
    protected BaseEvent getEvent(Redemption redemption) {
        Map<String, String> variables = new HashMap<String, String>() {{
            put("guest", redemption.getGuest());
            put("name", redemption.getName());
            put("command", redemption.getCommand());
        }};

        if (CommandManager.commands.containsKey(redemption.getCommand())){
            ArrayList<String> rawCommands = CommandManager.commands.get(redemption.getCommand());
            String[] commands = rawCommands.toArray(new String[rawCommands.size()]);

            for (int i = 0; i < commands.length; i++) {
                for (Map.Entry<String, String> entry : variables.entrySet()) {
                    commands[i] = commands[i].replace(
                            "{{" + entry.getKey() + "}}",
                            entry.getValue()
                    );
                }
            }

            return new RunCommandsEvent(commands);

        } else {
            return new SendErrorEvent("Requested Command '" + redemption.getCommand() + "' Is Not Registered");
        }
    }
}
