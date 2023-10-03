package Model.Cards;

import Model.Board.Board;
import Model.Player.Player;
import Model.Round.Round;
import Model.Tiles.Tile;
import View.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.HashMap;

public class Archaelogist extends Character implements Serializable {
    private final String skill_desc="Take up to 2 tiles from any sorting area other than the one you chose earlier in your turn";
    private JPanel previous_choice;
    private int tiles_taken;
    private boolean skill_complete;
    public Archaelogist(String type,Player owner) {
        super(type,owner);
        this.previous_choice=null;
        this.tiles_taken=0;
        this.skill_complete=false;
    }



    @Override
    public void Character_Skill(GUI gui, ActionEvent event) {
        if(this.tiles_taken==2){
            skill_complete=true;
        }

        var round=gui.getRound();
        this.previous_choice=round.getCurrent().getChosen_area();

        //////
        var board= gui.getBoard();
        var player_cont= gui.getPlayer_cont();
        var button_mappers=gui.getButton_mappers();
        var clicked_button=(JButton) event.getSource();
        var ac_command=clicked_button.getActionCommand();
        var t=button_mappers.get(ac_command);
        Container parent = clicked_button.getParent();
        if(tiles_taken<2 && previous_choice!=parent &&!skill_complete) {
            JPanel card = null;
            board.get_Area(parent.getName()).remove(t);
            gui.Update_player_tiles(t);
            for (Component c : player_cont.getComponents()) {
                if (c.isVisible()) {
                    card = (JPanel) c;
                }
            }
            this.tiles_taken++;
            gui.Update_owned_tiles_panel(card, parent, clicked_button);
        }else{
            JOptionPane optionPane = new JOptionPane("You are allowed to take 2 tiles from any sorting area other than the one you chose earlier", JOptionPane.ERROR_MESSAGE);
            JDialog dialog = optionPane.createDialog("Failure");
            dialog.setAlwaysOnTop(true);
            dialog.setVisible(true);
        }

    }

    @Override
    public Player Get_Owner() {
        return null;
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public boolean isUsed() {
        return this.Used;
    }

    @Override
    public void setUsed(boolean value) {
        this.Used=value;
    }

    @Override
    public String get_skill() {
        return this.skill_desc;
    }

    @Override
    public boolean has_completed_skill() {
        return skill_complete;
    }


}
