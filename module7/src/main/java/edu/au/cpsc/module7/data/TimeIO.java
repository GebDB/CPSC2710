package edu.au.cpsc.module7.data;

import java.io.*;

public class TimeIO {
    public static final File DEFAULT_FILE = new File("time.dat");
    public static void save(String timeString) {
        try (OutputStream os = new FileOutputStream(DEFAULT_FILE)) {
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(timeString);
        } catch (IOException ex) {
            System.err.println("Error saving time: "+DEFAULT_FILE);
            ex.printStackTrace();
            System.exit(-1);
        }
    }
    public static String load() throws ClassNotFoundException {
        try (InputStream is = new FileInputStream(DEFAULT_FILE)) {
            ObjectInputStream ois = new ObjectInputStream(is);
            return (String) ois.readObject();
        } catch (IOException ex) {
            System.err.println("Error loading time: "+DEFAULT_FILE);
            ex.printStackTrace();
            System.exit(-1);
        }
        return "00:00:00";
    }
}
