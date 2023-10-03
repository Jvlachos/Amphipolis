package Controller;

import View.GUI;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Save {
        private FileOutputStream out;
        private ObjectOutputStream gui_out;



    public Save(GUI gui,String filename){
        try{
            out= new FileOutputStream("saves/"+filename);
            try {
                gui_out=new ObjectOutputStream(out);
                gui_out.writeObject(gui);
                gui_out.close();
            } catch (IOException e) {

                e.printStackTrace();
            }


        } catch (FileNotFoundException e) {
            System.err.println ("Unable to create game data");
        }

    }
}
