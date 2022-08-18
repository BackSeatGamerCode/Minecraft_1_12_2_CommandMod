package com.backseatgamer.javasdk;

import com.backseatgamer.javasdk.models.Redemption;

import java.util.ArrayList;

public class RedemptionQueue {
    private final ArrayList<Redemption> queue = new ArrayList<>();

    public void enqueue(Redemption redemption){
        queue.add(redemption);
    }

    public Redemption dequeue(){
        return queue.remove(0);
    }

    public boolean isEmpty(){
        return queue.isEmpty();
    }
}
