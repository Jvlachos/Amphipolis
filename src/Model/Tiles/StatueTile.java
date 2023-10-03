package Model.Tiles;

import Model.Player.Player;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * class StatueTile
 */
public class StatueTile extends FindingTiles implements Serializable {
    protected ArrayList<SphinxTile> Sphinxes;
    protected ArrayList<CaryatidTile>Caryatids;
    /**
     * <b>Constructor</b> Creates a new statue tile
     */
    public StatueTile(String type) {
        super(type);
    }
    /**
     * <b>Constructor</b> Creates a new statue tile with an owner of type player
     */
    public StatueTile(String type, Player p){
        super(type,p);
    }

    /**
     * <b>Transformer</b>Adds a sphynx tile to the arraylist containing sphynxes
     * @param t the tile to be added
     */
    public void Set_Sphynx(SphinxTile t){
        this.Sphinxes.add(t);
    }
    /**
     * <b>Transformer</b>Adds a caryatid tile to the arraylist containing caryatids
     * @param t the tile to be added
     */
    public void Set_Caryatid(CaryatidTile t){
        this.Caryatids.add(t);
    }

    /**
     * <b>Accessor</b>Returns the arraylist containing sphnyx tiles
     * @return the array containing sphynxes
     */
    public ArrayList<SphinxTile> get_Sphynx(){
        return this.Sphinxes;
    }
    /**
     * <b>Accessor</b>Returns the arraylist containing caryatid tiles
     * @return the array containing caryatids
     */
    public ArrayList<CaryatidTile> get_Caryatids(){
        return this.Caryatids;
    }



}
