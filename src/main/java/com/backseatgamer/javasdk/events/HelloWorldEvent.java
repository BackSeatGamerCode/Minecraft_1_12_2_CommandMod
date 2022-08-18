package com.backseatgamer.javasdk.events;

public class HelloWorldEvent extends BaseEvent{
    @Override
    public void execute(Object... args) {
        System.out.println("Hello, World!");
    }
}
