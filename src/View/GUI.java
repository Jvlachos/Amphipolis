package View;

import Controller.Controller;
import Controller.Game_Controller;
import Controller.Save;
import Model.Board.Board;
import Model.Cards.Character;
import Model.Player.Player;
import Model.Round.Round;
import Model.Tiles.*;
import Controller.Load;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class GUI extends JFrame implements Serializable {
    private JMenuBar menu;
    private final JLayeredPane player_panel;
    private JPanel mosaic_panel;
    private JPanel entrance_panel;
    private  JPanel statue_panel;
    private JPanel amphora_panel;
    private JPanel skeleton_panel;
    protected ImageIcon image;
    private JLabel background;
    private   JLayeredPane main_Panel;
    private  JButton save;
    private  JButton load;
    private  JButton exit;
    private final JButton use_character_1;
    private final JButton use_character_2;
    private final JButton use_character_3;
    private final JButton use_character_4;
    private JButton Draw_tiles;
    private JButton End_turn;
    private JPanel player_cont;
    private CardLayout player_cont_layout;
    private ArrayList<Player>active_players=new ArrayList<>();
    private Player_Actions actions;
    public  Menu_Actions  menu_actions;
    private JButton tile_button;
    private ActionListener take;
    private Round round;
    private JLabel player_indicator;
    private ActionListener use_char;
    private int turn=0;
    public ArrayList<Tile>bag_copy;
    private Board board;
    private HashMap<String,Tile>button_mappers;
    private ArrayList<JPanel>Player_panels=new ArrayList<>();
    private Character in_use;
    private JPanel chosen_area_for_turn;
    private boolean character_mode;
    private boolean character_used_for_turn;
    private Game_Controller controller;
    private boolean singleplayer;




    public GUI(ArrayList<Player> Players, Round round, Game_Controller controller,boolean singleplayer){

         this.controller=controller;
         this.player_panel=new JLayeredPane();
         this.use_character_1 =new JButton();
         this.use_character_2 =new JButton();
         this.use_character_3 =new JButton();
         this.use_character_4 =new JButton();
         this.active_players=Players;
         board=new Board();
         this.singleplayer=singleplayer;
         button_mappers=new HashMap<>();

         /*
         Initialize action listeners
           */
         actions=new Player_Actions();
         take=new take_tiles();
         use_char=new use_character();
         menu_actions=new Menu_Actions();

         ///
         this.round=round;
         this.image=new ImageIcon(getClass().getResource("/images_2020/caryatid.png"));

         this.setIconImage(this.image.getImage());


         this.setTitle("Amphipolis");
         this.setDefaultCloseOperation(EXIT_ON_CLOSE);
         this.setPreferredSize(new Dimension(960,740));
         this.setLayout(new BorderLayout());
         Initialize_Environment();



        player_indicator=new JLabel("Player :"+round.getCurrent().getPlayer_Id()+"  "+"Round:"+this.turn);
        player_indicator.setFont(new Font("Verdana",Font.ROMAN_BASELINE,20));

        this.add(player_indicator,BorderLayout.PAGE_START);

         this.setJMenuBar(this.menu);
         this.pack();
         this.setVisible(true);
         this.setResizable(false);
         this.setLocationRelativeTo(null);

    }




    public void Initialize_Environment(){

        set_Area_Layouts();

        /*
           initialize the panel with the characters
         */
        player_panel.setPreferredSize(new Dimension(350,150));
        player_cont_layout=new CardLayout();
        player_cont=new JPanel(player_cont_layout);

        player_panel.setBackground(Color.black);
        player_panel.setOpaque(false);

        Initialize_Buttons();
        /*
           Add buttons to panel
         */

        player_panel.add(use_character_1);
        player_panel.add(use_character_2);
        player_panel.add(use_character_3);
        player_panel.add(use_character_4);

        player_panel.setLayout(new BorderLayout());
        player_cont.setPreferredSize(new Dimension(50,100));

        player_cont.setOpaque(false);
        //add player panels on card layout container
        for(int i=0; i<active_players.size(); i++) {
            Player_panels.add(new JPanel(new GridLayout(2,2)));
            player_cont.add(round.getOrder().get(i).getPlayer_Id(), Player_panels.get(i));
        }
        set_player_colors();


        ////////


        player_panel.add(player_cont,BorderLayout.PAGE_START);

        player_panel.revalidate();
        this.getContentPane().add(player_panel,BorderLayout.EAST);
        this.revalidate();




    }
    private void set_Area_Layouts(){
        background=new JLabel(new ImageIcon(getClass().getResource("/images_2020/background.png")));
        background.setBackground(Color.BLACK);

        background.setOpaque(true);
        background.setLayout(new BorderLayout());
        main_Panel=new JLayeredPane();
        main_Panel.setLayout(null);

        /**
         * Initialize mosaic area
         */

        mosaic_panel=new JPanel(new GridLayout(5,5,5,5));
        mosaic_panel.setBounds(30,45,150,150);
        main_Panel.setOpaque(false);
        mosaic_panel.setOpaque(false);
        mosaic_panel.setName("mosaic");

        /**
         * Initialize statue area
         */
        statue_panel=new JPanel(new GridLayout(5,5,5,5));
        statue_panel.setBounds(420,45,150,150);
        statue_panel.setOpaque(false);
        statue_panel.setName("statue");

        /**
         * Initialize amphora area
         */
        amphora_panel=new JPanel(new GridLayout(5,5,5,5));
        amphora_panel.setBounds(30,455,150,150);
        amphora_panel.setOpaque(false);
        amphora_panel.setName("amphora");

        /**
         * Initialize Skeleton area
         */
        skeleton_panel=new JPanel(new GridLayout(5,5,5,5));
        skeleton_panel.setBounds(420,455,150,150);
        skeleton_panel.setOpaque(false);
        skeleton_panel.setName("skeleton");
        /**
         * Initialize entrance area
         */
        entrance_panel=new JPanel(new GridLayout(4,4,5,5));

        entrance_panel.setBounds(220,300,150,150);
        entrance_panel.setOpaque(false);
        entrance_panel.setName("entrance");



        /**
         * Add panels on main panel
         */
        main_Panel.add(mosaic_panel, 0,0);
        main_Panel.add(statue_panel,0,0);
        main_Panel.add(amphora_panel,0,0);
        main_Panel.add(skeleton_panel,0,0);
        main_Panel.add(entrance_panel,0,0);
        background.add(main_Panel);


        this.add(background);

    }

   private void Initialize_Buttons(){
        //character Button 1
        ImageIcon c1=new ImageIcon(getClass().getResource("/images_2020/archaeologist.png"));

        use_character_1.setPreferredSize(new Dimension(150,244));
        use_character_1.setBounds(180,100,150,244);
        use_character_1.setIcon(c1);
        use_character_1.addActionListener(use_char);
        use_character_1.setActionCommand("archaelogist");


        //character Button 2
        ImageIcon c2=new ImageIcon(getClass().getResource("/images_2020/digger.png"));

        use_character_2.setPreferredSize(new Dimension(150,244));
        use_character_2.setBounds(180,350,150,244);
        use_character_2.setIcon(c2);
        use_character_2.addActionListener(use_char);
        use_character_2.setActionCommand("digger");

        //character Button 3
        ImageIcon c3=new ImageIcon(getClass().getResource("/images_2020/professor.png"));

        use_character_3.setPreferredSize(new Dimension(150,244));
        use_character_3.setBounds(10,100,150,244);
        use_character_3.setIcon(c3);
        use_character_3.addActionListener(use_char);
        use_character_3.setActionCommand("professor");

        //character Button 4
        ImageIcon c4=new ImageIcon(getClass().getResource("/images_2020/assistant.png"));

        use_character_4.setPreferredSize(new Dimension(150,244));
        use_character_4.setBounds(10,350,150,244);
        use_character_4.setIcon(c4);
        use_character_4.addActionListener(use_char);
        use_character_4.setActionCommand("assistant");


         End_turn=new JButton("End Turn");
         Draw_tiles=new JButton("Draw");


         End_turn.setBounds(70,600,100,50);
         Draw_tiles.setBounds(180,600,100,50);
         End_turn.addActionListener(actions);
         Draw_tiles.addActionListener(actions);

         //INITIALIZE MENU BUTTONS
        menu=new JMenuBar();
        save=new JButton("Save Game");
        load=new JButton("Load Game");
        exit=new JButton("Exit Game");
        save.addActionListener(menu_actions);
        exit.addActionListener(menu_actions);
        load.addActionListener(menu_actions);

       menu.add(this.save);
       menu.add(this.load);
       menu.add(this.exit);



         player_panel.add(End_turn);
         player_panel.add(Draw_tiles);

    }

    private JButton Create_tile_Button(String filename){
        tile_button=new JButton();
        tile_button.setPreferredSize(new Dimension(40,40));
        tile_button.setIcon(new ImageIcon(getClass().getResource("/images_2020/"+filename+".png")));
        tile_button.setBorderPainted(false);
        tile_button.setContentAreaFilled(false);
        tile_button.setOpaque(false);
        tile_button.addActionListener(take);
        tile_button.setActionCommand(filename);
        return tile_button;

    }

    public void Place_Tile(Tile tile){
         if(tile==null){
             System.out.println("Tile null\n");
             return;
         }
         if(tile instanceof LandSlideTile){
             tile_button=new JButton();
             tile_button.setPreferredSize(new Dimension(40,40));
             tile_button.setIcon(new ImageIcon(getClass().getResource("/images_2020/landslide.png")));
             tile_button.setBorderPainted(false);
             tile_button.setContentAreaFilled(false);
             tile_button.setOpaque(false);
             entrance_panel.add(tile_button);
             board.Place_Tile(tile);
             button_mappers.put("landslide",tile);
             entrance_panel.revalidate();

         }
         else if(tile instanceof SkeletonTile){
             String filename="skeleton_"+((SkeletonTile) tile).get_type()+"_"+((SkeletonTile) tile).get_part();
             skeleton_panel.add(Create_tile_Button(filename));
             board.Place_Tile(tile);
             button_mappers.put(filename,tile);
             skeleton_panel.revalidate();


         }
         else if(tile instanceof MosaicTile){
             String filename="mosaic_"+((MosaicTile) tile).get_color();
             mosaic_panel.add(Create_tile_Button(filename));
             board.Place_Tile(tile);
             button_mappers.put(filename,tile);
             mosaic_panel.revalidate();

         }
         else if(tile instanceof AmphoraTile){
             String filename="amphora_"+((AmphoraTile) tile).get_color();
             amphora_panel.add(Create_tile_Button(filename));
             board.Place_Tile(tile);
             button_mappers.put(filename,tile);
             amphora_panel.revalidate();
         }
         else if(tile instanceof StatueTile){
             String filename= ((StatueTile) tile).getType();
             statue_panel.add(Create_tile_Button(filename));
             board.Place_Tile(tile);
             button_mappers.put(filename,tile);
             statue_panel.revalidate();

         }

         }
    public static Color getColorByName(String colorName) {
        try {
            Field f = Color.class.getField(colorName.toUpperCase());
            return (Color) f.get(null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public void set_player_colors(){
             for(int i=0; i<Player_panels.size(); i++){
                 this.Player_panels.get(i).setBackground(getColorByName(active_players.get(i).get_color()));
             }
    }
    private  void save(String filename){
        new Save(this,filename+".save");
    }




    private   class Menu_Actions implements ActionListener,Serializable {

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton clicked= (JButton) e.getSource();
            Container c=clicked.getParent();
            if(e.getSource()==save){
                String filename=JOptionPane.showInputDialog(c,"Enter name for save file");
                if(filename.isBlank()){
                    JOptionPane.showMessageDialog(c,"File name cannot be blank");
                }else {
                    save(filename);
                }
            }
            if(e.getSource()==load){
                FileDialog dialog = new FileDialog((Frame) null, "Select File to Open");
                dialog.setDirectory("saves");
                dialog.setMode(FileDialog.LOAD);
                dialog.setVisible(true);
                String file = dialog.getFile();
                try {
                    new Load(file);
                    dispose();

                } catch (IOException | ClassNotFoundException ioException) {
                    ioException.printStackTrace();
                }
            }
            if(e.getSource()==exit){
                if (JOptionPane.showConfirmDialog(c,"Are you sure you want to exit Aphipolis?All unsaved process will be lost.","Amphipolis",
                        JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
                    System.exit(0);
            }

        }
    }
    public void Update_player_tiles(Tile t){
        round.getCurrent().Assign_Tiles((FindingTiles) t);
        /*for(int i=0; i<round.getCurrent().getOwned_Tiles().size(); i++){
            System.out.println(round.getCurrent().getOwned_Tiles().get(i).toString());
        }
        For loop to test functionality
         */
    }
    private void grave_rob(){
        controller.grave_robber_actions(this,board);
    }


    private void finish_game(){
        controller.CalculateScore_and_terminate(this.active_players,this);
    }

    private class Player_Actions implements ActionListener,Serializable{

        @Override
        public void actionPerformed(ActionEvent e) {

            if(e.getSource()==End_turn){
                turn++;
                round.getCurrent().setHas_drawn(false);
                round.setTurn(turn);
                chosen_area_for_turn=null;
                character_used_for_turn=false;
                if(in_use!=null){
                    in_use.setUsed(true);
                }
                character_mode=false;
                round.getCurrent().setChosen_area(null);
                for(Player i : active_players){
                    i.setTiles_taken(0);
                }
                player_cont_layout.show(player_cont,round.getCurrent().getPlayer_Id());
                player_indicator.setText("Player :"+round.getCurrent().getPlayer_Id()+"  "+"Round:"+turn);

            }else if(e.getSource()==Draw_tiles){

               if(!round.getCurrent().hasdrawn()) {
                   for (int i = 0; i < 4; i++) {
                       Place_Tile(bag_copy.get(i));

                       if(board.get_Area("entrance").size()==16){
                           finish_game();
                       }
                       if(bag_copy.get(i).toString().equals("landslide") && singleplayer){
                           grave_rob();
                       }
                       bag_copy.remove(i);
                   }

                   round.getCurrent().setHas_drawn(true);
                   Collections.shuffle(bag_copy);
               }else{
                   JOptionPane optionPane = new JOptionPane("You have already drawn tiles", JOptionPane.ERROR_MESSAGE);
                   JDialog dialog = optionPane.createDialog("Failure");
                   dialog.setAlwaysOnTop(true);
                   dialog.setVisible(true);
               }

            }
        }
    }
    private void use_skill(Character c,ActionEvent event){
        c.Character_Skill(this,event);
    }


    private class use_character implements ActionListener,Serializable {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton clicked= (JButton) e.getSource();
            Container c=clicked.getParent();
            String ac=e.getActionCommand();
            Player p=round.getCurrent();
            Character current;
            current= p.get_char_by_type(ac);

            if(current.isUsed()){
                JOptionPane optionPane = new JOptionPane("You have already used that Character", JOptionPane.ERROR_MESSAGE);
                JDialog dialog = optionPane.createDialog("Failure");
                dialog.setAlwaysOnTop(true);
                dialog.setVisible(true);
            }else if(!character_used_for_turn){
                if (JOptionPane.showConfirmDialog(c, "Character Skill:"+current.get_skill()+"\n"+"Use character?", "Amphipolis", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                    character_mode=true;
                    in_use=current;
                }
            }else{
                JOptionPane optionPane = new JOptionPane("You can use one character per turn", JOptionPane.ERROR_MESSAGE);
                JDialog dialog = optionPane.createDialog("Failure");
                dialog.setAlwaysOnTop(true);
                dialog.setVisible(true);

            }
        }
    }
    public void Update_owned_tiles_panel(JPanel card,Container parent,JButton clicked_button){
        assert card != null;
        card.add(clicked_button);
        card.revalidate();
        card.repaint();
        player_cont.revalidate();
        player_cont.repaint();
        clicked_button.removeActionListener(take);
        parent.remove(clicked_button);
        parent.revalidate();
        parent.repaint();
    }


    private class take_tiles implements ActionListener,Serializable{


        @Override
        public void actionPerformed(ActionEvent e) {
            if(!character_mode) {
                Default_Mode(e);
            }else{
                Character_Mode(e);
            }
        }

        public void Default_Mode(ActionEvent e){
            JButton clicked_button=(JButton) e.getSource();
            Player current=round.getCurrent();
            String ac_command=clicked_button.getActionCommand();
            Tile t=button_mappers.get(ac_command);

            Container parent = clicked_button.getParent();

            if(current.getChosen_area()==null){
                chosen_area_for_turn=(JPanel) parent;
                current.setChosen_area(chosen_area_for_turn);
            }
            if(current.getTiles_taken()<2 && current.getChosen_area()==parent) {
                JPanel card = null;


                board.get_Area(parent.getName()).remove(t);
                Update_player_tiles(t);
                for (Component c : player_cont.getComponents()) {
                    if (c.isVisible()) {
                        card = (JPanel) c;
                    }
                }
                current.setChosen_area((JPanel) parent);
                Update_owned_tiles_panel(card, parent, clicked_button);
                current.Increment_tiles_taken();
            }else{
                JOptionPane optionPane = new JOptionPane("You are allowed to take up to 2 tiles per turn from ONE area", JOptionPane.ERROR_MESSAGE);
                JDialog dialog = optionPane.createDialog("Failure");
                dialog.setAlwaysOnTop(true);
                dialog.setVisible(true);
            }

        }

        public void Character_Mode(ActionEvent e){

            if(round.getCurrent().getChosen_area()==null){
                JOptionPane optionPane = new JOptionPane("You haven't taken any tiles,please take tiles from any area before using the character", JOptionPane.ERROR_MESSAGE);
                JDialog dialog = optionPane.createDialog("Failure");
                dialog.setAlwaysOnTop(true);
                dialog.setVisible(true);
                character_mode=false;
                return;
            }
            use_skill(in_use,e);
            if(in_use.has_completed_skill()) {
                character_mode = false;
                character_used_for_turn=true;
                round.getCurrent().setChosen_area(null);
                in_use.setUsed(true);
            }
        }


    }

    /**
     * Some getters
     * @return board,round,player_cont,button_mappers,mosaic panel,statue panel,amphora panel,skeleton panel
     */
    public Board getBoard() {
        return board;
    }

    public Round getRound() {
        return round;
    }

    public JPanel getPlayer_cont() {
        return player_cont;
    }

    public HashMap<String, Tile> getButton_mappers() {
        return button_mappers;
    }

    public JPanel getMosaic_panel(){
        return this.mosaic_panel;

    }
    public JPanel getStatue_panell(){
        return this.statue_panel;

    }
    public JPanel getAmphora_panel(){
        return this.amphora_panel;

    }
    public JPanel getSkeleton_panel(){
        return this.skeleton_panel;

    }
}
