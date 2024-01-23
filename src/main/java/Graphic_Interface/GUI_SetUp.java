package Graphic_Interface;

import com.company.SQL_Connection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI_SetUp {
    public static int gameChooser;
    public static int howPlayers;//for method GUI_PlayersData()
    public static String player01;//for method GUI_PlayersData()
    public static String player02;//for method GUI_PlayersData()

    public static void GUI_Setup(){

        final JFrame frame = new JFrame();
        JPanel panel  = new JPanel();

        JLabel background;
        JButton tic_tac_toe;
        JButton connect_4;
        JButton exit;

        background = new JLabel("",new ImageIcon("setUP materials\\best-family-board-games-1588260911.jpg"),JLabel.CENTER);
        background.setBounds(0,0,400,250);

        tic_tac_toe = new JButton();
        tic_tac_toe.setText("Tic-tac-toe");
        tic_tac_toe.setBounds(130,90,120,20);

        connect_4 = new JButton();
        connect_4.setText("Connect 4");
        connect_4.setBounds(130,120,120,20);

        exit = new JButton();
        exit.setText("exit");
        exit.setBounds(290,160,60,30);

        tic_tac_toe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameChooser = 1;
                frame.dispose();
                GUI_PlayersData();
            }
        });

        connect_4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameChooser = 2;
                frame.dispose();
                GUI_PlayersData();
            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SQL_Connection.CloseConnection();
                System.exit(0);
            }
        });

        panel.setLayout(null);

        panel.add(tic_tac_toe);
        panel.add(connect_4);
        panel.add(exit);

        frame.add(background);
        background.add(tic_tac_toe);
        background.add(connect_4);
        background.add(exit);

        frame.add(panel);
        frame.setSize(400, 250);
        frame.setTitle("Game setup!");
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void GUI_PlayersData(){
        final JFrame frame = new JFrame();
        final JPanel panel  = new JPanel();

        final JRadioButton onePlayer;
        final JRadioButton twoPlayers;
        final ButtonGroup players;
        JButton enter;// = back
        final JButton continueGame;
        final JTextField firstPlayer;
        final JTextField secondPlayer;
        final JLabel plOne;
        final JLabel plTwo;
        final JLabel background;

        onePlayer = new JRadioButton("One player");
        onePlayer.setBounds(40,10,110,15);

        twoPlayers = new JRadioButton("Two players");
        twoPlayers.setBounds(155,10,110,15);

        enter = new JButton("<");
        enter.setHorizontalAlignment(SwingConstants.LEFT);
        enter.setBounds(0,95,50,15);

        continueGame = new JButton("Continue");
        continueGame.setBounds(180,85,86,15);
        continueGame.setVisible(false);

        firstPlayer = new JTextField();
        firstPlayer.setBounds(100,45,60,16);
        firstPlayer.setVisible(false);

        secondPlayer = new JTextField();
        secondPlayer.setBounds(100,75,60,16);
        secondPlayer.setVisible(false);

        plOne = new JLabel();
        plOne.setText("player name:");
        plOne.setForeground(Color.ORANGE);
        plOne.setBounds(25,45,75,16);
        plOne.setVisible(false);

        plTwo = new JLabel();
        plTwo.setText("player name:");
        plTwo.setForeground(Color.ORANGE);
        plTwo.setBounds(25,75,75,16);
        plTwo.setVisible(false);

        background = new JLabel("",new ImageIcon("setUP materials\\player data.jpg"),JLabel.CENTER);
        background.setBounds(0,0,300,150);

        players = new ButtonGroup();

        onePlayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                plOne.setVisible(true);
                firstPlayer.setVisible(true);
                continueGame.setVisible(true);
                plTwo.setVisible(false);
                secondPlayer.setVisible(false);
                howPlayers = 2;
            }
        });

        twoPlayers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                plOne.setVisible(true);
                plTwo.setVisible(true);
                firstPlayer.setVisible(true);
                secondPlayer.setVisible(true);
                continueGame.setVisible(true);
                howPlayers = 1;
            }
        });

        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                GUI_Setup();
            }
        });

        continueGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                SQL_Connection.Connection();
                if(onePlayer.isSelected()){
                    player01 = firstPlayer.getText();
                    player02 = "Computer";
                }else{
                    player01 = firstPlayer.getText();
                    player02 = secondPlayer.getText();
                }

                if(player01.matches("^[a-zA-Z]+$") && player02.matches("^[a-zA-Z]+$")){
                    SQL_Connection.Delete();
                    SQL_Connection.Create(player01);
                    SQL_Connection.Create(player02);

                    if(gameChooser == 1)
                        GUI_TicTacToe.GUI_TicTactoe();
                    else if(gameChooser == 2)
                        GUI_Connect_4.GUI_Connect4();
                    frame.dispose();
                }else{
                    JOptionPane.showMessageDialog(null,"Add players name!");
                }
            }
        });

        panel.setLayout(null);
        panel.add(onePlayer);
        panel.add(twoPlayers);
        panel.add(enter);
        panel.add(continueGame);
        panel.add(firstPlayer);
        panel.add(secondPlayer);
        panel.add(plOne);
        panel.add(plTwo);

        players.add(onePlayer);
        players.add(twoPlayers);

        background.add(onePlayer);
        background.add(twoPlayers);
        background.add(enter);
        background.add(continueGame);
        background.add(firstPlayer);
        background.add(secondPlayer);
        background.add(plOne);
        background.add(plTwo);

        frame.add(background);
        frame.add(panel);
        frame.setSize(300, 150);
        frame.setTitle("Player data!");
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
}
