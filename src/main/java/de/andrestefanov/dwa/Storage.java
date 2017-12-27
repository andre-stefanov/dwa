package de.andrestefanov.dwa;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class Storage {

    private File file = new File("/opt/dwa/value.txt");
    private Gson gson = new Gson();

    public Storage() {
        try {
            if (!file.exists() && file.createNewFile()) {
                resetTimestamp();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public long getTimestamp() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String sVal = reader.readLine();
            reader.close();
            return Long.valueOf(sVal);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            JsonReader reader = new JsonReader(new FileReader(file));
            return gson.fromJson(reader, Long.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return 0L;
    }

    public void resetTimestamp() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(file));
            writer.write(String.valueOf(System.currentTimeMillis()));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setTimestamp(long timestamp) {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(file));
            writer.write(String.valueOf(timestamp));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
