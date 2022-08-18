package com.backseatgamer.javasdk.events;

import com.backseatgamer.javasdk.models.Redemption;

public abstract class BaseEvent {
    public abstract void execute(Object... args);
}
