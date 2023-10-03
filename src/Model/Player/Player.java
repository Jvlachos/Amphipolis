package Model.Player;

import Model.Cards.Character;
import Model.Tiles.*;

import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;

/**
 * Class Player:This class is responsible for initializing a player,setting his id,color ,his owned cards and score
 * @author Dimitris Vlachos/Csd4492
 * @version 1.0
 */
public class Player implements Serializable {
    private String Player_Id;
    private ArrayList<FindingTiles> Owned_Tiles;
    private ArrayList<Character>CharacterList;
    private int tiles_taken;
    private boolean has_drawn;
    private JPanel chosen_area;
    private Colors Color;
    private int Points;
    private int sphinxes;
    private int caryatids;





    /**
     * <b>Constructor</b>for grave_robber
     */
   public Player() {
        Player_Id="grave_robber";
       this.sphinxes=0;
       this.sphinxes=0;
       this.Points=0;
       this.Color=Colors.BROWN;
       this.tiles_taken=0;
       this.Owned_Tiles=new ArrayList<>();

    }

    /**
     * <b>Constructor</b>A new player is initialized
     * @param id:The player's id
     */
    public Player(String id,Colors color){
        this.Player_Id=id;
        this.Color=color;
        this.sphinxes=0;
        this.sphinxes=0;
        this.Points=0;
        this.tiles_taken=0;
        this.chosen_area=null;
        this.Owned_Tiles=new ArrayList<>();
        this.CharacterList=new ArrayList<>();
        this.has_drawn=false;

    }
    public JPanel getChosen_area() {
         return this.chosen_area;
    }

    public void setChosen_area(JPanel chosen_area) {
        this.chosen_area = chosen_area;
    }

    public void setTiles_taken(int num){
        this.tiles_taken=num;
    }


    public void Increment_tiles_taken(){
        this.tiles_taken++;
    }

    public int getTiles_taken(){
        return  this.tiles_taken;
    }

    public boolean hasdrawn(){
        return this.has_drawn;
    }

    public void setHas_drawn(boolean value){
        this.has_drawn=value;
    }



    /**
     * <b>Transformer</b>Sets the player's id
     * @param id:the id to be assigned to the player
     * <b>Pre_conditions</b>id must not contain symbols
     *  <b>Post_conditions</b>Player's id is set or modified
     */
    public void setPlayer_Id(String id){ }

    /**
     * <b>Accessor</b>Returns the player's id
     * @return:The player's id
     * <b>Pre_condition</b>Player must exist and therefore must not be null
     */
    public String getPlayer_Id(){
        return this.Player_Id;
    }

    /**
     * <b>Accessor</b>Returns the player's score
     * @return:The player's score(points)
     * <b>Pre_condition</b>Player must not be null
     */
    public int getPoints(){
        return this.Points;
    }

    /**
     * <b>Transformer</b>Assigns the player's score
     * @param p :the new player's score(points)
     * <b>Pre_condition</b>Player must not be null and score must be a positive integer
     */
    public void setPoints(int p){
        this.Points+=p;
    }

    /**
     * <b>Transformer</b>Assign tiles to the player
     * @param tile:A tile of type FindingTiles to be assigned to the player
     *  <b>Pre_conditions</b>The player must not be null,the tile must not be null
     *  The tile must be instance of FindingTiles
     *  <b>Post_condition</b>The player field Owned tiles is an array list of array lists containing instances of FindingTile class
     *   Because of that,tiles can be organized in groups according to their type
     *   for example in index Arraylist[1] there can all the amphora tiles the player owns,on index[2] all the statues etc.
     */
    public void Assign_Tiles(FindingTiles tile){
        this.Owned_Tiles.add(tile);
    }



    /**
     * <b>Accessor</b>Returns the owned tiles
     * @return:The array list containing the player's tiles
     */
    public ArrayList<FindingTiles> getOwned_Tiles(){return this.Owned_Tiles;}


    public String get_color(){
        return this.Color.toString();
    }


    public ArrayList<Character> get_characters(){
        return this.CharacterList;
    }

    public Character get_char_by_type(String type){
        Character return_val = null;
        for(Character c : this.CharacterList){
            if(c.getType().equals(type)){
                return_val=c;
                break;
            }
        }
        return return_val;
    }

    public void add_characters(Character c){
        this.CharacterList.add(c);
    }


    public int getSphinxes(){return this.sphinxes;}

    public int getCaryatids(){return this.caryatids;}

    public void setSphinxes(int val){
        sphinxes+=val;
    }
    public void setCaryatids(int val){
        caryatids+=val;
    }
}