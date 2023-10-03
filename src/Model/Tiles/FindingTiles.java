package Model.Tiles;

import Model.Player.Player;

import java.io.Serializable;

/**
 * Class FindingTiles:This class is responsible for handling Finding type of tiles,assign them to players and assign their type
 * @author Dimitris Vlachos/Csd4492
 * @version 1.0
 */
public  class FindingTiles implements Tile, Serializable {
    Player owner;
    String type;

    /**
     * <b>Constructor</b>Assigns a type to the finding tile
     * @param type:The type of the tile
     * <b>Pre_condition</b>Type must be Skeleton,Sphynx,Caryatid,Mosaic or Amphora
     * <b>Post_condition</b>The type is assigned to the type field
     */
    public FindingTiles(String type){
          this.type=type;
    }
    /**
     * <b>Constructor</b>Assigns a type to the finding tile and it's owner
     * @param type:The type of the tile
     * @param p:The owner(player object)
     * <b>Pre_conditions</b>Type must be Skeleton,Sphynx,Caryatid,Mosaic or Amphora and p must not be null
     * <b>Post_condition</b>The type is assigned to the type field and the owner of the tile is assigned
     */

    public FindingTiles(String type,Player p){
        this.type=type;
        this.owner=p;

    }

    /**
     * <b>Accessor</b>Returns the type of the tile(string format)
     * @return :The type of the tile
     * <b>Post_condition</b> The type of tile is returned
     */
    public String getType(){
        return this.type;
    }

    /**
     * <b>Accessor</b>Returns the owner of the tile(player object)
     * @return :The owner of the tile
     * <b>Pre_condition</b> owner must not be null
     * <b>Post_condition</b> The owner of tile is returned
     */
    public Player getOwner(){return this.owner;}

    /**
     * <b>Transformer</b>Assigns an owner to the tile
     * @param player :The owner of the tile
     * <b>Pre_condition</b> owner must not be null
     * <b>Post_condition</b> The owner of tile is assigned
     */
    public void setOwner(Player player){
        this.owner=player;
    }


    @Override
    public void place_Tile(Tile t) {

    }

    @Override
    public String toString(){
        return this.type;
    }
}
