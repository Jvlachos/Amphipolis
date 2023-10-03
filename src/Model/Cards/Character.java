package Model.Cards;

import Model.Player.Player;
import Model.Round.Round;
import View.GUI;

import java.awt.event.ActionEvent;
import java.io.Serializable;

/**
 * Class Character:This class is responsible for implementing the character cards skills and attributes
 * @author Dimitris Vlachos/Csd4492
 * @version 1.0
 */
public abstract class Character implements Serializable {
    protected boolean Used;
    protected Player Owner;
    protected String type;
    protected String skill_desc;

    /**
     * <b>Constructor</b>Initializes a new character card and assigning it to a player
     * :Player object ,the owner of the card
     */
    public Character(String type,Player owner){

        this.Used=false;
        this.type=type;
        this.Owner=owner;

    }

    /**
     * Abstract methods implemented by the derived classes
     */

    /**
     * <b>Accessor</b> Uses the character skill
     */
    public abstract void Character_Skill(GUI gui, ActionEvent event);

    /**
     * <b>Accessor</b>Returns the owner of the card
     * @return: Player instance,the owner of the card
     */
    public abstract Player Get_Owner();

   public abstract String getType();

   public abstract boolean isUsed();

   public  abstract void setUsed(boolean value);

   public  abstract String get_skill();

   public abstract boolean has_completed_skill();


}
