package Model.Tiles;

import java.io.Serializable;
import java.util.ArrayList;

public class TileContainer implements Serializable {
    private ArrayList<Tile> Bag;

    public TileContainer(){
        this.Bag=new ArrayList<>();
    }
    public void add_to_bag(Tile tile){
        this.Bag.add(tile);
    }
    public ArrayList<Tile> get_Bag(){
        return this.Bag;
    }
}
