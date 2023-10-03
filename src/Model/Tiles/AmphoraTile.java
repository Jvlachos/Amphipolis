package Model.Tiles;

import Model.Player.Player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Class AmphoraTile:This class is responsible for managing amphora tiles and grouping them together
 */
public class AmphoraTile extends FindingTiles implements Serializable {
    protected Colors color;
    /**
     * The purpose of the arraylist is to group amphoras together
     */
    protected ArrayList<AmphoraTile>Amphoras;

    /**
     * <b>Constructor</b>Creates a new tile of type amphora with a given color
     * @param color : The color of the amphora
     * <b>Post_condition</b>A new amphora tile is created and a color is assigned to it
     */
    public AmphoraTile(Colors color){
        super("Amphora");
        this.color=color;
    }
    /**
     * <b>Constructor</b>Creates a new tile of type amphora with a given color and an owner(player)
     * @param color : The color of the amphora
     * @param p :The owner of the amphora tile(player)
     * <b>Pre_condition</b>Player must not be null
     * <b>Post_condition</b>A new amphora tile is created and a color is assigned to it,also an owner is assigned to it
     */
    public AmphoraTile(Colors color, Player p){
        super("Amphora",p);
        this.color=color;
    }

    /**
     * <b>Accessor</b>Returns the arraylist of amphoras
     * @return: The arraylist containing tiles of type amphora
     * <b>Post_condition</b>The arraylist is returned
     */

    public ArrayList<AmphoraTile> getAmphoras(){
        return this.Amphoras;
    }

    /**
     * <b>Transformer</b>Adds a new amphora tile to the arraylist
     * @param t:The tile to be added
     * <b>Pre_condition</b>The tile must not be null
     * <b>Post_condition</b>The tile is added to the arraylist
     */
    public void setAmphoras(AmphoraTile t){
        Amphoras.add(t);
    }

    public String get_color(){
        return this.color.toString().toLowerCase(Locale.ROOT);
    }

    public Colors get_color_value(){
        return  this.color;
    }
}
