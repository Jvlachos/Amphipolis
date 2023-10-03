package Controller;
import Model.Board.Board;
import Model.Cards.*;
import Model.Cards.Character;
import Model.Player.Player;
import Model.Round.Round;
import Model.Tiles.*;
import View.GUI;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.*;

/**
 * Derived class Game_Controller
 * this class extends the main controller and is responsible for
 * controlling the game environment ,the game state and general game options(save,load,exit)
 */
public class Game_Controller extends Controller implements Serializable {
    protected GUI gui;
    protected boolean game_state;
    private Player grave_robber;
    private boolean single_player;


    /**
     * <b>Constructor</b>Default constructor that sets the game state to false(game hasn't started)
     */
    public Game_Controller(){

        game_state=false;
    }

    /**
     * <b>Transformer</b> Changes the game state
     * @param state the new game state to be assigned
     * <p><b>Post_Condition</b>The game state must be true or false(true if the game has started and false if ended)
     */
    public void setGame_state(boolean state) {this.game_state=state;}


    /**
     * <b>Accessor</b>Initializes the components of the game environment (graphics,menus)
     * <p><b>Pre_condition</b> To enter single player mode:
     * @param  mode must be true
     * @param Players :the active players
     * <p><b>Post_condition</b>The game's GUI is initialized
     */
    public void initialize_environment(ArrayList<Player> Players, Round round,boolean mode){
        ArrayList<LandSlideTile>landSlideTiles=new ArrayList<>();
        this.single_player=mode;
        this.gui=new GUI(Players,round,this,mode);
        Generate_Tiles();
        gui.Place_Tile(bag.get_Bag().get(0));
        bag.get_Bag().remove(0);
        gui.Place_Tile(bag.get_Bag().get(30));
        bag.get_Bag().remove(30);
        gui.Place_Tile(bag.get_Bag().get(90));
        bag.get_Bag().remove(90);
        gui.Place_Tile(bag.get_Bag().get(121));
        bag.get_Bag().remove(121);
        if(mode){
            int counter=0;
           for(Tile t : bag.get_Bag()){
               if(t instanceof LandSlideTile && counter<8){
                   landSlideTiles.add((LandSlideTile) t);
                   gui.Place_Tile(t);
                   counter++;
               }
           }
          bag.get_Bag().removeAll(landSlideTiles);
           grave_robber=new Player();
        }
        Collections.shuffle(this.bag.get_Bag());
        gui.bag_copy=this.bag.get_Bag();


    }

    public  void grave_robber_actions(GUI gui,Board board){
        JOptionPane.showMessageDialog(gui, "Grave_robber appears", "Warning",
                JOptionPane.WARNING_MESSAGE);

            JPanel amphora=gui.getAmphora_panel();
            JPanel mosaic=gui.getMosaic_panel();
            JPanel skeleton= gui.getSkeleton_panel();
            JPanel statue= gui.getStatue_panell();
            JButton b;
            String ac;
            for(Component c: amphora.getComponents()){
                b=(JButton) c;
                ac=b.getActionCommand();
                Tile t=  gui.getButton_mappers().get(ac);
                grave_robber.Assign_Tiles((FindingTiles) t);
                amphora.remove(b);
                amphora.repaint();
                amphora.revalidate();
                board.get_Area("amphora").remove(t);

            }
            for(Component c: mosaic.getComponents()){
            b=(JButton) c;
            ac=b.getActionCommand();
            Tile t=  gui.getButton_mappers().get(ac);
            grave_robber.Assign_Tiles((FindingTiles) t);
            mosaic.remove(b);
            mosaic.repaint();
            mosaic.revalidate();
            board.get_Area("mosaic").remove(t);
            }
            for(Component c: statue.getComponents()){
            b=(JButton) c;
            ac=b.getActionCommand();
            Tile t=  gui.getButton_mappers().get(ac);
            grave_robber.Assign_Tiles((FindingTiles) t);
            statue.remove(b);
            statue.repaint();
            statue.revalidate();
            board.get_Area("statue").remove(t);
           }
           for(Component c: skeleton.getComponents()){
            b=(JButton) c;
            ac=b.getActionCommand();
            Tile t=  gui.getButton_mappers().get(ac);
            grave_robber.Assign_Tiles((FindingTiles) t);
            skeleton.remove(b);
            skeleton.repaint();
            skeleton.revalidate();
            board.get_Area("skeleton").remove(t);
        }

    }


    public void Generate_Tiles(){
        /**
         * Generate Amphoras
         */
        int i=0;
        int k=0;
        super.bag=new TileContainer();
        while(i<5) {
            for(Colors c : Colors.values()) {
                AmphoraTile a = new AmphoraTile(c);
                bag.add_to_bag(a);
            }
            i++;
        }
        /**
         * Generate Skeletons
         */
         for(int j=0; j<30; j++){
             SkeletonTile s;
             SkeletonTile c;
             if(j%2==0){
                 s = new SkeletonTile("big", "top");
                 c = new SkeletonTile("small","top");
             }
             else{
                 s = new SkeletonTile("big", "bottom");
                 c = new SkeletonTile("small","bottom");
             }
             bag.add_to_bag(s);
             bag.add_to_bag(c);
         }
        /**
         * Generate Mosaics
         */
        MosaicTile m;
       while(k<9){
           for(Colors c: Colors.values()){
               if(c==Colors.BROWN){
                   break;
               }else{
                   m=new MosaicTile(c);
                   bag.add_to_bag(m);
               }
           }
           k++;
       }

        /**
         * Generate statues and landslides
         */
        for(int a=0; a<24; a++){
            LandSlideTile l=new LandSlideTile();
            bag.add_to_bag(l);
            if(a<18){
                CaryatidTile c=new CaryatidTile();
                bag.add_to_bag(c);
            }
            if(a<12){
                SphinxTile s=new SphinxTile();
                bag.add_to_bag(s);
            }
        }




    }
    public  void generate_and_give_characters(ArrayList<Player>Players){
        for(Player i : Players){
            Character c1=new Archaelogist("archaelogist",i);
            Character c2=new Digger("digger",i);
            Character c3=new Professor("professor",i);
            Character c4=new Assistant("assistant",i);
                i.add_characters(c1);
                i.add_characters(c2);
                i.add_characters(c3);
                i.add_characters(c4);
        }
    }



    public void CalculateScore_and_terminate(ArrayList<Player>active_players,GUI gui){
        if(single_player){
            active_players.add(grave_robber);
        }
       for(Player i : active_players){
           calc(i.getOwned_Tiles(),i);
    }
       check_statues(active_players);

      active_players=sort(active_players,"points");
      String [] score_board=new String[active_players.size()];
       for(int i=0; i<active_players.size(); i++){
           score_board[i]=("Player: "+active_players.get(i).getPlayer_Id()+","+"Score: "+active_players.get(i).getPoints());
       }


        JOptionPane.showMessageDialog(gui, new JList(score_board),"Winner is :"+active_players.get(0).getPlayer_Id(),JOptionPane.WARNING_MESSAGE);
            System.exit(0);
    }
    private void calc(ArrayList<FindingTiles>owned,Player player){
        ArrayList<String>amphoras=new ArrayList<>();
        ArrayList<String>mosaics=new ArrayList<>();
        ArrayList<String>skeletons=new ArrayList<>();
        for(FindingTiles i : owned){
            if(i instanceof MosaicTile){
                MosaicTile t=(MosaicTile) i;
                mosaics.add(i.getType()+"_"+((MosaicTile) i).get_color());
            }
            if(i instanceof AmphoraTile){
                AmphoraTile a=(AmphoraTile) i;
                amphoras.add(a.getType()+"_"+a.get_color());
            }
            if(i instanceof SkeletonTile){
                SkeletonTile s=(SkeletonTile)i;
                skeletons.add(s.getType()+"_"+s.get_type()+"_"+s.get_part());
            }else if(i instanceof CaryatidTile) {
                player.setCaryatids(+1);
            }else{
                player.setSphinxes(+1);
            }
        }
        Collections.sort(amphoras);
        Collections.sort(mosaics);
        Collections.sort(skeletons);


        check_mosaics(mosaics,player);
        check_amphoras(amphoras,player);
        check_skeletons(skeletons,player);


    }

    private ArrayList<Player> sort(ArrayList<Player>sort,String flag){
        Player temp;
        for(int i=0; i<sort.size(); i++){
            for(int j=i+1; j<sort.size(); j++){
                 if(flag.equals("sphinxes")){
                     if(sort.get(i).getSphinxes()<sort.get(j).getSphinxes()){
                         temp=sort.get(i);
                         sort.set(i,sort.get(j));
                         sort.set(j,temp);
                     }

                }else if(flag.equals("caryatids")){
                     if(sort.get(i).getCaryatids()<sort.get(j).getCaryatids()){
                         temp=sort.get(i);
                         sort.set(i,sort.get(j));
                         sort.set(j,temp);
                     }
                 }else if(flag.equals("points")){
                     if(sort.get(i).getPoints()<sort.get(j).getPoints()){
                         temp=sort.get(i);
                         sort.set(i,sort.get(j));
                         sort.set(j,temp);
                     }
                 }
            }
        }
        return sort;
    }

    private void check_statues(ArrayList<Player>players){


            ArrayList<Player>sorted_sphinxes=new ArrayList<Player>();
            ArrayList<Player>sorted_caryatids=new ArrayList<>();
            sorted_caryatids= (ArrayList<Player>) players.clone();
            sorted_sphinxes= (ArrayList<Player>) players.clone();
            sorted_caryatids=sort(sorted_caryatids,"caryatids");
            sorted_sphinxes=sort(sorted_sphinxes,"sphinxes");
            if(sorted_caryatids.get(0).getCaryatids()!=0) {
                sorted_caryatids.get(0).setPoints(6);
                sorted_caryatids.get(sorted_caryatids.size()-1).setPoints(0);
                sorted_caryatids.remove(sorted_caryatids.size()-1);
                sorted_caryatids.remove(0);

            }
            if(sorted_sphinxes.get(0).getSphinxes()!=0) {
                sorted_sphinxes.get(0).setPoints(6);
                sorted_sphinxes.get(sorted_sphinxes.size() - 1).setPoints(0);
                sorted_sphinxes.remove(sorted_sphinxes.size() - 1);
                sorted_sphinxes.remove(0);

            }
            for(Player p: sorted_caryatids){
                if(p.getCaryatids()>0){
                    p.setPoints(3);
                }
            }
            for(Player p: sorted_sphinxes){
                if(p.getSphinxes()>0){
                    p.setPoints(3);
                }
            }

        }



    private void check_skeletons(ArrayList<String>skeletons ,Player player){
             int ad_tops=0;
             int ad_bots=0;
             int ch_tops=0;
             int ch_bots=0;
             int completed_ad=0;
             int completed_ch=0;
             int familys=0;
             for(String i : skeletons){
                 switch (i){
                     case "skeleton_big_top"->ad_tops++;
                     case "skeleton_big_bottom"->ad_bots++;
                     case "skeleton_small_top"->ch_tops++;
                     case "skeleton_small_bottom"->ch_bots++;
                 }
             }
             if(ad_tops>ad_bots){
                 completed_ad=((ad_tops+ad_bots)-(ad_tops-ad_bots))/2;
             }else if(ad_bots>ad_tops){
                 completed_ad=((ad_tops+ad_bots)-(ad_bots-ad_tops))/2;
             }else{
                 completed_ad=(ad_tops+ad_bots)/2;
             }

             if(ch_tops>ch_bots){
                completed_ch=((ch_tops+ad_bots)-(ch_tops-ad_bots))/2;
             }else if(ch_bots>ch_tops){
                completed_ch=((ch_tops+ch_bots)-(ch_bots-ch_tops))/2;
             }else{
                completed_ch=(ch_tops+ch_bots)/2;
             }
             int total=completed_ad+completed_ch;
             for(int i=0; i<completed_ad/2; i++){
                 if(completed_ch!=0){
                     familys++;
                 }
                 completed_ch--;
             }
             player.setPoints(familys*6);
             int remainder=total-(3*familys);
             player.setPoints(remainder);


    }


    private void check_amphoras(ArrayList<String>amphoras,Player player){
        HashSet<String>groups=new HashSet<>();
        ArrayList<String>temp=new ArrayList<>();
       while(amphoras.size()>0) {
           for (int i = 0; i < amphoras.size(); i++) {
               if (!groups.contains(amphoras.get(i))) {
                   groups.add(amphoras.get(i));
               }else{
                   temp.add(amphoras.get(i));
               }
           }

            amphoras= (ArrayList<String>) temp.clone();
            temp.clear();

           switch (groups.size()) {
               case 6 -> player.setPoints(6);
               case 5 -> player.setPoints(4);
               case 4 -> player.setPoints(2);
               case 3 -> player.setPoints(1);
           }
          groups.clear();
       }

    }



    private void check_mosaics(ArrayList<String>mosaics,Player player){
        int total=0;
        ArrayList<String> temp=new ArrayList<>();
        for(Colors c : Colors.values()){
            int counter=0;
            for(int i=0; i<mosaics.size(); i++){
                if(mosaics.get(i).equals("Mosaic_"+c.toString().toLowerCase(Locale.ROOT))){
                    counter++;
                }
                if(counter==4){
                    total++;
                    player.setPoints(4);
                    counter=0;
                }
            }
        }
        int multiplier=(mosaics.size()-(total*4))/4;
        player.setPoints(multiplier*2);


    }



}
