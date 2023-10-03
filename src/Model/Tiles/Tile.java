package Model.Tiles;


import java.io.Serializable;

/**Interface Tile:This interface implements the game's tiles and their operations
 * @author : Dimitris Vlachos/Csd4492
 * @version 1.0
 */

public interface Tile extends Serializable {
    /**
     *<b>Transformer</b> Place a tile at the corresponding area on the board
     * @param t :The tile to be placed
     * <b>Post_condition</b>The tile is place to the area matching it's type
     */
    void place_Tile(Tile t);

    /**
     * <b>Observer</b> Returns the type of the tile at string format
     * @return the type of the tile
     * <b>Post_condition</b>The type of the tile is returne at string format
     */
    @Override
   public String toString();

}
