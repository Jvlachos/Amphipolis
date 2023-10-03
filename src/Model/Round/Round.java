package Model.Round;

import Model.Player.Player;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class Round:This class is corresponds to the games rounds which is defined by the player who's turn is to play
 * and the turn's index
 * Moreover it implements the operations of checking if the player is ready to play or has finished his round
 * @author Dimitris Vlachos/Csd4492
 * @version 1.0
 */
public class Round implements Serializable {
    private Player current;
    private int turn;
    private ArrayList<Player>order;
    private boolean has_finished;
    private boolean is_ready;

    /**
     * <b>Constructor</b>Default Constructor assigning turn to 0
     */
    public Round(){
        turn=0;

    }

    /**
     * <b>Constructor</b>Initializes a game round with index and a player who is/isn't ready
     * @param turn:The current turn's index
     *
     * @param ready:Dictates whether player is ready to play
     * <b>Pre_conditions</b>a)Turn must be turn>0 and new.turn==old.turn+1
     *  b)player must not be null
     *  <b>Post_condition</b>The player who's turn is to play is assigned to the p field ,the turn is set to a new turn
     *   and also it is determined if the player is ready by assigning ready to is_ready field
     */
  public Round(int turn,boolean ready){
        this.turn=turn;
        this.is_ready=ready;
        this.order=new ArrayList<>();
  }
    /**
     * <b>Transformer</b>Sets the turns index
     * @param turn:the index to be assigned to the turn
     * <b>Pre_conditions</b>turn must be a positive integer,and turn must be ==old.turn+1
     *  <b>Post_conditions</b>Player's id is set or modified
     */
    public void setTurn(int turn){
        assert(turn>=0);
        this.turn=turn;
        if(turn% order.size()!=0) {
            current = order.get((turn % order.size()));
        }else{
            current = order.get(0);
        }
    }

    public void set_order(ArrayList<Player>order){
        this.order=order;
        this.current=order.get(0);
    }

    public ArrayList<Player>getOrder(){
        return this.order;
    }

    public Player getCurrent(){
        return this.current;
    }
}
