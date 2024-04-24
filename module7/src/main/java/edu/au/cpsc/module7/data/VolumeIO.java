package edu.au.cpsc.module7.data;

import java.io.*;

public class VolumeIO {
    public static final File DEFAULT_FILE = new File("volume.dat");
    public static void save(double volume) {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(DEFAULT_FILE))) {
            dos.writeDouble(volume);
        } catch (IOException ex) {
            System.err.println("Error saving audio volume: " + DEFAULT_FILE);
            ex.printStackTrace();
            System.exit(-1);
        }
    }
    public static double load() {
        if (!DEFAULT_FILE.exists()) {
            return 50;
        }
        try (DataInputStream dis = new DataInputStream(new FileInputStream(DEFAULT_FILE))) {
            return dis.readDouble();
        } catch (IOException ex) {
            System.err.println("Error loading audio volume: " + DEFAULT_FILE);
            ex.printStackTrace();
            System.exit(-1);
        }
        return 50;
    }
}
