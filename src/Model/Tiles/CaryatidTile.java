package Model.Tiles;

import Model.Player.Player;

import java.io.Serializable;

/**
 * Class CaryatidTile
 */
public class CaryatidTile extends StatueTile implements Serializable {
    /**
     * <b>Constructor</b> Creates a new caryatid tile using parent class constructor
     */
    public CaryatidTile() {
        super("caryatid");
    }
    /**
     * <b>Constructor</b> Creates a new caryatid tile using parent class constructor
     * @param p  the player to be the owner of the tile
     */
    public CaryatidTile(Player p){
        super("caryatid",p);

    }
}
