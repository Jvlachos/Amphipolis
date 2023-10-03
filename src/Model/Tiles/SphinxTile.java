package Model.Tiles;

import Model.Player.Player;

import java.io.Serializable;

/**
 * class SphynxTile
 */
public class SphinxTile extends StatueTile implements Serializable {
    /**
     * <b>Constructor</b> Creates a new sphynx tile using parent class constructor
     */
    public SphinxTile() {
        super("sphinx");
    }
    /**
     * <b>Constructor</b> Creates a new sphynx tile using parent class constructor
     * @param p  the player to be the owner of the tile
     */
    public SphinxTile(Player p){
        super("sphinx",p);
    }
}
