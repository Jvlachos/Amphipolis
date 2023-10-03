package Controller;

import Model.Board.Board;
import Model.Player.Player;
import Model.Tiles.Colors;
import Model.Tiles.FindingTiles;
import Model.Tiles.Tile;
import Model.Round.Round;
import Model.Tiles.TileContainer;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *This is the main Controller class
 * The Controller is the control center of the game that controls all of it's operations
 * @version 1.0
 * @author Dimitris Vlachos/csd4492
 */
public class Controller implements Serializable {
     protected Round round;
     protected Player who_plays;
     protected TileContainer bag;
     protected Board board;
     protected ArrayList<Player>Players=new ArrayList<>();
     protected int num_of_Players;
     public Game_Controller game;
     private boolean single_player;

    /**
     *<b>Constructor</b>:Creates a new controller and prepares the game to start
     * <b>post_condition</b>:Initializes four(4) players with id's from 1 to 4
     * initializes the bag that contains the tiles ,initializes the first round and creates the board
     */

    public Controller(ArrayList<String>ids,ArrayList<String>colors){

         num_of_Players=ids.size();
        for(int i=0; i<num_of_Players; i++){
            String c=colors.get(i);
            for(Colors col : Colors.values()){
                if(c.equals(String.valueOf(col))){
                    Player player=new Player(ids.get(i),col);
                    Players.add(player);
                }
            }
        }
        if(num_of_Players==1){
            single_player=true;
        }

         this.round=new Round(1,true);

         this.board=new Board();
         this.game =new Game_Controller();

        game_start(game);
    }

    public Controller() {
    }

    public static void main(String[] args){
        ArrayList<String>ids=new ArrayList<>();
        ArrayList<String >colors=new ArrayList<>();
        ids.add("p1");
        ids.add("p2");
        colors.add("RED");
        colors.add("BLUE");
        Controller c=new Controller(ids,colors);
    }


    /**
     * <b>Transformer</b>Sets the game state to true thus starting the game
     * @param game : the current game controller instance
     * <b>post_conditions</b> The enviroment is initialized and the character cards are given to the players
     */

    public void game_start(Game_Controller game){
        game.setGame_state(true);
        round.set_order(Players);
        game.generate_and_give_characters(this.Players);
        game.initialize_environment(this.Players,round,this.single_player);

    }



     public ArrayList<Player> getPlayers(){
        return this.Players;
     }
}
