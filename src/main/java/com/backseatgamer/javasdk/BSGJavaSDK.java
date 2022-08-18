package com.backseatgamer.javasdk;

import com.backseatgamer.javasdk.events.BaseEvent;
import com.backseatgamer.javasdk.models.Redemption;
import com.google.gson.Gson;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public abstract class BSGJavaSDK extends Thread {
    protected final RedemptionQueue eventQueue = new RedemptionQueue();
    private final int PORT = 29175;
    private final Gson gson = new Gson();

    private void startThread(){
        setDaemon(true);
        setName("BackSeatGamer Server Poll Thread");
        start();
    }

    public BSGJavaSDK() {
        startThread();
    }

    public void run() {
        while (true){
            try(
                    ServerSocket socketServer = new ServerSocket(PORT);
                    Socket socket = socketServer.accept()
            ) {
                while (true){
                    InputStream input = socket.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    String line = reader.readLine();

                    Redemption redemption = gson.fromJson(line, Redemption.class);

                    if(line != null){
                        eventQueue.enqueue(redemption);
                        break;
                    }

                    OutputStream output = socket.getOutputStream();
                    PrintWriter writer = new PrintWriter(output, true);
                    writer.println("done");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void poll(Object... args){
        if(!eventQueue.isEmpty()) {
            Redemption redemption = eventQueue.dequeue();
            onRedemptionReceived(redemption, args);

            BaseEvent event = getEvent(redemption);

            if (event != null)
                event.execute(args);
        }
    }

    protected abstract void onRedemptionReceived(Redemption redemption, Object... args);

    protected abstract BaseEvent getEvent(Redemption redemption);
}
