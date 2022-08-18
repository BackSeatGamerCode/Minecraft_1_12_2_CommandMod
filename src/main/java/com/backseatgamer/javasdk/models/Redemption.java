package com.backseatgamer.javasdk.models;

import java.util.Objects;

public class Redemption {
    private String command;
    private String name;
    private String guest;

    public Redemption(String command, String name, String guest) {
        this.command = command;
        this.name = name;
        this.guest = guest;
    }

    @Override
    public String toString() {
        return "Redemption{" +
                "message='" + command + '\'' +
                ", name='" + name + '\'' +
                ", guest='" + guest + '\'' +
                '}';
    }

    public String toMessage() {
        return guest + " has redeemed the reward " + name;
    }

    public String getCommand() {
        return command;
    }

    public String getName() {
        return name;
    }

    public String getGuest() {
        return guest;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        Redemption that = (Redemption) obj;
        return Objects.equals(this.command, that.command);
    }

    @Override
    public int hashCode() {
        return Objects.hash(command);
    }

}
