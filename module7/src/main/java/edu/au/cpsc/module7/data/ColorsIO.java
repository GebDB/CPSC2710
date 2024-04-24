package edu.au.cpsc.module7.data;

import javafx.scene.paint.Color;

import java.io.*;

public class ColorsIO {
    public static final File DEFAULT_FILE = new File("colors.dat");

    /**
     * Saves an array of Color objects to a file.
     */
    public static void save(Color[] colors) {
        try (OutputStream os = new FileOutputStream(DEFAULT_FILE);
             ObjectOutputStream oos = new ObjectOutputStream(os)) {

            // Convert Color objects to hexadecimal format
            String[] hexValues = new String[colors.length];
            for (int i = 0; i < colors.length; i++) {
                hexValues[i] = toHexString(colors[i]);
            }

            oos.writeObject(hexValues);
        } catch (IOException ex) {
            System.err.println("Error saving colors: " + DEFAULT_FILE);
            ex.printStackTrace();
            System.exit(-1);
        }
    }

    /**
     * Loads an array of Color objects from a file.
     */
    public static Color[] load() throws ClassNotFoundException {
        try (InputStream is = new FileInputStream(DEFAULT_FILE);
             ObjectInputStream ois = new ObjectInputStream(is)) {

            String[] hexValues = (String[]) ois.readObject();
            Color[] colors = new Color[hexValues.length];

            for (int i = 0; i < hexValues.length; i++) {
                colors[i] = Color.web(hexValues[i]);
            }

            return colors;

        } catch (IOException ex) {
            return null;
        }
    }

    /**
     * Converts a Color object to a hexadecimal string.
     */
    public static String toHexString(Color color) {
        int r = (int) (color.getRed() * 255);
        int g = (int) (color.getGreen() * 255);
        int b = (int) (color.getBlue() * 255);

        return String.format("#%02X%02X%02X", r, g, b);
    }
}