package Model.Board;
import View.GUI;
import Model.Tiles.*;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class Board:This class is responsible for initializing the game board and it's components
 * The board has 5 areas,each area holds tiles of a certain type
 * The areas are represented as arraylists
 * @author Dimitris Vlachos/Csd4492
 * @version 1.0
 */
public class Board implements Serializable {
    protected   ArrayList<Tile> Mosaic_Area;
    protected  ArrayList<Tile> Amphora_Area;
    protected   ArrayList<Tile> Skeleton_Area;
    protected   ArrayList<Tile> Statue_Area;
    protected   ArrayList<Tile>Entrance_Area;

    /**
     * <b>Constructor</b>Initializes all ArrayLists to null
     */
    public Board(){
        Mosaic_Area=new ArrayList<>();
        Amphora_Area=new ArrayList<>();
        Skeleton_Area=new ArrayList<>();
        Statue_Area=new ArrayList<>();
        Entrance_Area=new ArrayList<>();
    }

    /**
     * <b>Transformer</b> Places a tile at a specific area
     * @param tile:the tile to be placed
     *  <b>Pre_condition</b>The tile type must correspond to the area that it is to be placed
     *  <b>Post_condition</b>The tile is place to the area it is supposed to according to it's type
     */
    public void Place_Tile(Tile tile){
        if(tile instanceof LandSlideTile){
            Entrance_Area.add(tile);
        }
        else if(tile instanceof SkeletonTile){
            Skeleton_Area.add(tile);
        }
        else if(tile instanceof MosaicTile){
             Mosaic_Area.add(tile);
        }
        else if(tile instanceof AmphoraTile){
            Amphora_Area.add(tile);
        }
        else if(tile instanceof StatueTile){
             Statue_Area.add(tile);
        }
    }


    /**
     * <b>Accessor</b>Returns the requested arraylist
     * @return:The arrayList refering to the requested area
     * <b>Pre_conditions</b> Choice must be either Mosaic,Amphora,Statue,Skeleton,Entrance
     * <b>Post_condition</b>The @param:choice determines which ArrayList is to be returned,if empty null is returned
     */
    public ArrayList<Tile> get_Area(String choice){
        return switch (choice) {
            case "entrance" -> this.Entrance_Area;
            case "skeleton" -> this.Skeleton_Area;
            case "mosaic" -> this.Mosaic_Area;
            case "amphora" -> this.Amphora_Area;
            case "statue" -> this.Statue_Area;
            default -> null;
        };
    }



}
