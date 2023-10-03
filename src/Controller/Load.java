package Controller;

import View.GUI;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Load {
    private FileInputStream in;
    ObjectInputStream ois;

    public Load(String filename) throws ClassNotFoundException, IOException {
        try {
            in=new FileInputStream("saves/"+filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            ois=new ObjectInputStream(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        GUI c=(GUI) ois.readObject();

        c.setVisible(true);

    }
}
