package Model.Tiles;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class SkeletonTile:This class is responsible for the skeleton tiles,checking whether a tile is complete
 * managing their parts and grouping them together if they complete a skeleton family
 */
public class SkeletonTile extends FindingTiles implements Serializable {
    protected String type;
    protected  String part;
    protected boolean isComplete;
    protected ArrayList<SkeletonTile> Skeleton_Family =new ArrayList<>();
    protected ArrayList<SkeletonTile>Skeleton_Parts=new ArrayList<>();

    /**
     * <b>Constructor</b>Default constructor creating a new skeleton tile with default values
     */
   public SkeletonTile(){
        super("Skeleton");
        type="none";
        isComplete=false;
        Skeleton_Family=null;
        Skeleton_Parts=null;

    }

    /**
     * <b>Constructor</b>Creates a new skeleton tile  of given type
     * @param type :The type of the skeleton(child,adult)
     * <b>Pre_condition</b>Type must be either child or adult
     * <b>Post_condition</b>The skeleton tile is created and it's type is assigned
     */
   public SkeletonTile(String type,String part){
        super("skeleton");
        this.type=type;
        this.part=part;
    }

    /**
     * <b>Accessor</b> Returns the skeleton's type
     * @return :The skeleton's type (adult/child)
     * <b>Post_condition</b>The type of the skeleton is returned
     */
    public String get_type(){
         return this.type;
    }


    public String get_part(){
        return this.part;
    }
    /**
     * <b>Transformer</b> Assigns a type to the skeleton tile
     * @param type :The skeleton's type (adult/child)
     * <b>Pre_condition</b>Type must be either child or adult
     * <b>Post_condition</b>The type of the skeleton is assigned
     */
    public void setType(String type){
        this.type=type;
    }

    /**
     * <b>Transformer</b> Adds a tile to the skeletonfamily arraylist
     * @param skeleton :Skeleton tile
     * <b>Pre_condition</b>skeleton must not be null
     * <b>Post_condition</b>A skeleton tile is added to the array list
     */
    public void setSkeleton_Family(StatueTile skeleton){}

    /**
     * <b>Observer</b>Sees if skeleton has all his parts
     * @return: True if skeleton is complete/false if not
     */
    public boolean is_Complete(){
        return isComplete;
    }

}
