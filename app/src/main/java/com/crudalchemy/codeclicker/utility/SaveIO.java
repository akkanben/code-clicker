package com.crudalchemy.codeclicker.utility;


import android.content.Context;
import android.util.Log;

import com.crudalchemy.codeclicker.activity.Game;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

public class SaveIO {

    public static void writeToFile(Game game, Context context) {
        Gson gson = new Gson();
        String json = gson.toJson(game);

        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("save.json", Context.MODE_PRIVATE));
            outputStreamWriter.write(json);
            outputStreamWriter.close();
            Log.i("Success", "Wrote to json File ");
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    public static  Game readFromFile(Context context) throws FileNotFoundException {
        String contents = "";
        FileInputStream fis = context.openFileInput("save.json");
        InputStreamReader inputStreamReader =
                new InputStreamReader(fis, StandardCharsets.UTF_8);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(inputStreamReader)) {
            String line = reader.readLine();
            while (line != null) {
                stringBuilder.append(line).append('\n');
                line = reader.readLine();
            }
        } catch (IOException e) {
            // Error occurred when opening raw file for reading.
        } finally {
             contents = stringBuilder.toString();
        }

        Gson gson = new Gson();

        return gson.fromJson(contents, Game.class);
    }
}
