package View;

import Controller.Controller;
import Controller.Game_Controller;
import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import Controller.Load;



public class Main_menu extends JFrame {
    private JPanel panel;
    private ImageIcon icon;


    public Main_menu(){
        this.setPreferredSize(new Dimension(400,400));
        this.icon=new ImageIcon(getClass().getResource("/images_2020/caryatid.png"));
        this.setIconImage(this.icon.getImage());
        this.panel=new panel();

        this.add(panel);
        this.pack();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);



    }
    private class panel extends JPanel implements ActionListener{
        private int Panel_h;
        private int Panel_w;
        private Image image;
        private Timer timer;
        private int x_vel=1;
        private int y_vel=1;
        private boolean draw=true;
        private  int x;
        private int y;
        private JButton button;
        private JPanel menu_panel;
        private JPanel card;
        private  ActionListener menu;
        private  CardLayout c_lay;
        private  Font font;

        public panel(){
            this.Panel_h=400;
            this.Panel_w=400;
            button =new JButton();
             c_lay=new CardLayout();
            this.setLayout(c_lay);
            menu=new menu_listener();
            URL font_path=getClass().getResource("/images_2020/GamegirlClassic-9MVj.ttf");
            try {
                try {
                    font  = Font.createFont(Font.TRUETYPE_FONT, new File(font_path.toURI())).deriveFont(20f);
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(font);
            } catch (IOException | FontFormatException e) {
                e.printStackTrace();
            }


            this.setPreferredSize(new Dimension(Panel_w,Panel_h));
            this.setBackground(Color.black);
            menu_panel=new JPanel();
            menu_panel.setPreferredSize(new Dimension(400,400));
            card=new start_menu();
            this.add("menu",menu_panel);
            this.add("start_game",card);

            initialize_main_buttons();
            menu_panel.setLayout(new BoxLayout(menu_panel,BoxLayout.Y_AXIS));
            menu_panel.add(Box.createRigidArea(new Dimension(100,100)));
            menu_panel.setOpaque(false);
            x=0;
            y=0;
            image=new ImageIcon(getClass().getResource("/images_2020/caryatid.png")).getImage();
            timer=new Timer(100,this);
            timer.start();

        }
        private void initialize_main_buttons(){
            /*
            Initialize buttons for main menu
             */
            button.setMaximumSize(new Dimension(160,50));
            button.setIcon(new ImageIcon(getClass().getResource("/images_2020/tile_back2.png")));
            button.addActionListener(this);
            button.setBorderPainted(false);
            button.setContentAreaFilled(false);
            button.setOpaque(false);
            menu_panel.add(button);
            menu_panel.add(Box.createRigidArea(new Dimension(160,10)));
            String [] vals={"NEW GAME","LOAD GAME","EXIT"};
            for(String i : vals){
                JButton j=new JButton(i);
                j.setFont(font);
                j.setForeground(Color.red);
                j.setBorderPainted(false);
                j.setContentAreaFilled(false);
                j.addActionListener(menu);
                j.setActionCommand(i);
                j.setBorder(null);
                j.setOpaque(false);
                j.setMaximumSize(new Dimension(180,20));
                menu_panel.add(Box.createRigidArea(new Dimension(0,20)));
                menu_panel.add(j);
            }

        }

        private class start_menu extends  JPanel implements ActionListener{

           private ArrayList<JTextField>textFields;
           private ArrayList<JComboBox> comboBoxes;
           private JButton input;
           private ArrayList<String> ids;
           private ArrayList<String> colors;
           private JButton start;


            public start_menu(){
                textFields=new ArrayList<>();
                comboBoxes=new ArrayList<>();
                ids=new ArrayList<>();
                colors=new ArrayList<>();
                initialize_components();

            }
          private  void initialize_components(){
                Font label_font=new Font("Lucida console",Font.BOLD,20);
                JLabel j=new JLabel("Input id/color");
                j.setFont(label_font);
                j.setForeground(Color.red);
                GridBagConstraints c=new GridBagConstraints();

                this.setBackground(Color.black);
                this.setLayout(new GridBagLayout());
                setMyConstraints(c,2,0,GridBagConstraints.CENTER);

                this.add(j,c);
                c.ipadx=10;
                c.ipady=10;

                String [] colors={"NONE","RED","BLUE","GREEN","YELLOW"};
                for(int i=0; i<4; i++) {
                    JTextField textField=new JTextField(10);
                    input=new JButton("Input");
                    input.addActionListener(this);
                    input.setActionCommand(String.valueOf(i));
                    setMyConstraints(c, 1,i+1, GridBagConstraints.EAST);
                    this.add(input, c);
                    setMyConstraints(c, 2, i+1, GridBagConstraints.CENTER);
                    this.add(textField, c);
                    JComboBox<String> comboBox=new JComboBox<String>(colors);
                    setMyConstraints(c,3,i+1,GridBagConstraints.WEST);
                    this.add(comboBox,c);
                    textFields.add(textField);
                    comboBoxes.add(comboBox);
                }
                start=new JButton("START");
                setMyConstraints(c,2,6,GridBagConstraints.CENTER);
                this.add(start,c);
                start.addActionListener(this);
                start.setActionCommand("start");


            }


            private void setMyConstraints(GridBagConstraints c, int gridx, int gridy, int anchor) {
                c.gridx = gridx;
                c.gridy = gridy;
                c.anchor = anchor;
            }
            private boolean validate_inputs(String to_add_id,String to_add_color,JButton clicked){
                 to_add_id=to_add_id.trim();
                 to_add_color=to_add_color.trim();
                if(ids.contains(to_add_id) || colors.contains(to_add_color)){
                    JOptionPane optionPane = new JOptionPane("Please choose different ids and colors", JOptionPane.ERROR_MESSAGE);
                    JDialog dialog = optionPane.createDialog("Failure");
                    dialog.setAlwaysOnTop(true);
                    dialog.setVisible(true);
                    clicked.setEnabled(true);
                    return false;
                }
                if (to_add_id.isBlank() || to_add_color.isBlank()) {
                    JOptionPane optionPane = new JOptionPane("Invalid input,please try again", JOptionPane.ERROR_MESSAGE);
                    JDialog dialog = optionPane.createDialog("Failure");
                    dialog.setAlwaysOnTop(true);
                    dialog.setVisible(true);
                    clicked.setEnabled(true);
                    return false;
                }
                return  true;
            }



            @Override
            public void actionPerformed(ActionEvent e) {

                String ac= e.getActionCommand();
                JButton clicked= (JButton) e.getSource();

                if(ac.equals("start")) {
                    if(ids.size()==0 || colors.size()==0){
                        JOptionPane optionPane = new JOptionPane("Please complete at least one field", JOptionPane.ERROR_MESSAGE);
                        JDialog dialog = optionPane.createDialog("Failure");
                        dialog.setAlwaysOnTop(true);
                        dialog.setVisible(true);
                    }else {
                        Controller c = new Controller(ids, colors);
                        dispose();
                    }
                }else{
                    int index = Integer.parseInt(ac);

                    String text = textFields.get(index).getText();
                    String color = (String) comboBoxes.get(index).getEditor().getItem();
                    if(color.equals("NONE")){
                        color=" ";
                    }
                    if(validate_inputs(text,color,clicked)) {
                        ids.add(text);
                        colors.add(color);
                        clicked.setEnabled(false);
                    }
                }
            }
        }

        public void paint(Graphics g){
            super.paint(g);
            Graphics2D graphics2D=(Graphics2D) g;
            if(draw) {
                graphics2D.drawImage(image, x, y, null);
            }

        }


        @Override
        public void actionPerformed(ActionEvent e) {
            if(x>=Panel_w-image.getWidth(null) || x<0){
                x_vel=x_vel*-1;
            }
            if(y>=Panel_h-image.getHeight(null)|| y<0){
                y_vel=y_vel*-1;
            }
            x=x+x_vel;
            y=y+y_vel;
            repaint();

        }


        private class menu_listener implements ActionListener {
            final JFileChooser fc=new JFileChooser();
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getActionCommand().equals("NEW GAME")){
                    c_lay.show(panel,"start_game");
                    draw=false;
                    repaint();
                }if(e.getActionCommand().equals("EXIT")){
                    if (JOptionPane.showConfirmDialog(panel,"Are you sure you want to exit Aphipolis?","Amphipolis",
                            JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
                        System.exit(0);
                }
                if(e.getActionCommand().equals("LOAD GAME")){
                    FileDialog dialog = new FileDialog((Frame)null, "Select File to Open");
                    dialog.setDirectory("saves");
                    dialog.setMode(FileDialog.LOAD);
                    dialog.setVisible(true);
                    String file = dialog.getFile();
                    try {
                        Load load=new Load(file);
                        dispose();
                    } catch (IOException | ClassNotFoundException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
        }
    }



    public static void main (String [] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Main_menu main=new Main_menu();
            }
        });

    }

}
