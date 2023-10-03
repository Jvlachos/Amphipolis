package Model.Tiles;


import Model.Player.Player;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Locale;

public class MosaicTile extends FindingTiles implements Serializable {
     protected Colors color;
     protected ArrayList<MosaicTile>Mosaics;


     public MosaicTile(Colors color){
         super("Mosaic");
         this.color=color;
     }
    public MosaicTile(Colors color, Player p){
        super("Mosaic,p");
        this.color=color;
    }
    public void setMosaics(MosaicTile t){
         this.Mosaics.add(t);
    }
    public ArrayList<MosaicTile> getMosaics(){
         return this.Mosaics;
    }
    public String get_color(){
         return this.color.toString().toLowerCase(Locale.ROOT);
    }
}
