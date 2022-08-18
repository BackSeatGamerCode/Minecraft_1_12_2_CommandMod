package com.backseatgamer.melchandcpmisery;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CommandManager {
    public static Map<String, ArrayList<String>> commands = new HashMap<>();
    public static final String FILENAME = "commands.json";

    private static final Gson gson = new Gson();

    public static String getAbsolutePath(){
        return new File(FILENAME).getAbsolutePath();
    }

    public static void initializeFile(){
        try {
            FileWriter myWriter = new FileWriter(FILENAME);
            myWriter.write("{}");
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean reloadFile(){
        try {
            File file = new File(FILENAME);
            Scanner scanner = new Scanner(file);
            StringBuilder data = new StringBuilder();
            while (scanner.hasNextLine()) {
                data.append(scanner.nextLine());
            }
            scanner.close();

            Map<String, Object> commandData = gson.fromJson(data.toString(), Map.class);
            commands = (Map<String, ArrayList<String>>) commandData.get("commands");
            Map<String, Object> variables = (Map<String, Object>) commandData.get("variables");

            for (Map.Entry<String, ArrayList<String>> entry : commands.entrySet()) {
                ArrayList<String> commandList = entry.getValue();

                for (int i = 0; i < commandList.size(); i++) {
                    for (Map.Entry<String, Object> variable : variables.entrySet()) {
                        commandList.set(i, commandList.get(i).replace(
                                "{{" + variable.getKey() + "}}",
                                variable.getValue().toString()
                        ));
                    }
                }
            }

        } catch (FileNotFoundException e) {
            initializeFile();
            return false;

        } catch (JsonSyntaxException e){
            return false;
        }

        return true;
    }
}
