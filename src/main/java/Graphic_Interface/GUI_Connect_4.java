package Graphic_Interface;

import com.company.SQL_Connection;
import Game_Physics.Connect_4;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.swing.*;

public class GUI_Connect_4 {
    public static int player = 1;
    public static int[] counters = {5,5,5,5,5,5,5};
    public static JLabel[][] Ggame = new JLabel[7][6];

    public static void InicaliziseGgame(){
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                Ggame[i][j] = new JLabel();
                Ggame[i][j].setSize(60,60);
            }
        }
    }
    public static int ConvertX(int counter){
        int x = 56;
        for (int i = 0; i < 7; i++,x+=73) {
            if(counter == i)
                return x;
        }
        return 0;
    }
    public static int ConvertY(int counter){
        int y = 378;
        for (int i = 5; i >= 0; i--,y-=63) {
            if(counter == i)
                return y;
        }
        return 0;
    }
    public static int SetLabel(int x, JLabel[] gameB, int column, int count, int _y, JTextArea textArea){
        int fin = Connect_4.WinChecker(Connect_4.connect4)[0];
        if(fin == 0) {
            for (int i = 5; i >= 0; i--) {
                if (count == i) {
                    Connect_4.AddSymbol(i, column, player);
                    Connect_4.First();
                    if (player == 1) {
                        gameB[i].setIcon(new ImageIcon("connect4 materials\\trans red ball.png"));
                        player = 2;
                        textArea.setText(GUI_SetUp.player02 + " turn:");
                    } else {
                        gameB[i].setIcon(new ImageIcon("connect4 materials\\trans yellow ball.png"));
                        player = 1;
                        textArea.setText(GUI_SetUp.player01 + " turn:");
                    }
                    gameB[i].setBounds(x,_y,60,60);
                    int[] winCh = Connect_4.WinChecker(Connect_4.connect4);
                    if (winCh[0] != 0) {
                        if (player == 1) {
                            textArea.setText(GUI_SetUp.player02 + " win!"
                                    + "\n" + "from row " + winCh[1] + ", column " + winCh[2]
                                    + "\n" + "to row " + winCh[3] + ", column " + winCh[4]);
                            player = 2;
                            SQL_Connection.AddPoints(GUI_SetUp.player02);
                        } else {
                            textArea.setText(GUI_SetUp.player02 + " win!"
                                    + "\n" + "from row " + winCh[1] + ", column " + winCh[2]
                                    + "\n" + "to row " + winCh[3] + ", column " + winCh[4]);
                            player = 1;
                            SQL_Connection.AddPoints(GUI_SetUp.player01);
                        }

                    }
                    return count-=1;
                }
            }
        }
        return -1;
    }
    public static void MouseListener(final JLabel gBack, final int x, final JLabel[] gameB, final JLabel Arrow, final int column, final int xForAr, final int chooser, final JTextArea textArea){
        gBack.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {

                SetLabel(x,gameB,column,counters[chooser],ConvertY(counters[chooser]),textArea);
                counters[chooser]--;
                if(GUI_SetUp.howPlayers == 2 && Connect_4.WinChecker(Connect_4.connect4)[0] == 0 && player == 2){
                    Random rand = new Random();
                    int randNum = rand.nextInt(7);
                    SetLabel(ConvertX(randNum),Ggame[randNum],randNum,counters[randNum],ConvertY(counters[randNum]),textArea);
                    counters[randNum]--;
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if(Connect_4.WinChecker(Connect_4.connect4)[0] == 0)
                Arrow.setBounds(xForAr,4,60,60);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Arrow.setBounds(1000,4,60,60);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
        });
    }
    public static void GUI_Connect4(){
        final JFrame frame = new JFrame();
        JPanel panel = new JPanel();

        final JLabel background;
        final JLabel[] gameBackground = new JLabel[7];
        final JLabel downArrow;
        JButton newGame;
        JButton results;
        JButton back;
        final JTextArea turnArea;

        background = new JLabel();
        background.setIcon(new ImageIcon("connect4 materials\\planks-dark-old-wood-texture-background-170515350.jpg"));
        background.setBounds(0,0,800,533);

        int x = 50;
        for (int i = 0; i < 7; i++,x+=73) {
            gameBackground[i] = new JLabel("",new ImageIcon("connect4 materials\\work.png"),JLabel.CENTER);
            gameBackground[i].setBounds(x,-20,73,540);
        }

        newGame = new JButton("",new ImageIcon("connect4 materials\\new game.jpg"));
        newGame.setBounds(575,80,188,55);

        results = new JButton("",new ImageIcon("connect4 materials\\results.png"));
        results.setBounds(601,200,140,80);

        back = new JButton("<");
        back.setHorizontalAlignment(SwingConstants.LEFT);
        back.setBounds(0,460,50,15);

        InicaliziseGgame();

        downArrow = new JLabel("",new ImageIcon("connect4 materials\\down arrow.gif"),JLabel.CENTER);

        turnArea = new JTextArea();
        turnArea.setBounds(585,360,170,100);
        turnArea.setFont(new Font("CG OMEGA", Font.BOLD ,16));
        turnArea.setText(GUI_SetUp.player01 + " turn:");


        MouseListener(gameBackground[0],56,Ggame[0],downArrow,0,57,0,turnArea);
        MouseListener(gameBackground[1],130,Ggame[1],downArrow,1,130,1,turnArea);
        MouseListener(gameBackground[2],204,Ggame[2],downArrow,2,203,2,turnArea);
        MouseListener(gameBackground[3],277,Ggame[3],downArrow,3,276,3,turnArea);
        MouseListener(gameBackground[4],350,Ggame[4],downArrow,4,349,4,turnArea);
        MouseListener(gameBackground[5],423,Ggame[5],downArrow,5,422,5,turnArea);
        MouseListener(gameBackground[6],496,Ggame[6],downArrow,6,495,6,turnArea);

        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < 7; i++) {
                    counters[i] = 5;
                }
                if(Connect_4.First() == 'o' && Connect_4.WinChecker(Connect_4.connect4)[0] == 0)
                    player = 2;
                else if(Connect_4.First() == 'x' && Connect_4.WinChecker(Connect_4.connect4)[0] == 0)
                    player = 1;
                Connect_4.ClearArr();
                Connect_4.clearFirst();
                frame.dispose();
                GUI_Connect_4.GUI_Connect4();

                if(GUI_SetUp.howPlayers == 2 && player == 2){
                    Random rand = new Random();
                    int randNum = rand.nextInt(7);
                    SetLabel(ConvertX(randNum),Ggame[randNum],randNum,counters[randNum],ConvertY(counters[randNum]),turnArea);
                    counters[randNum]--;
                    Connect_4.First();
                }

            }
        });

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < 7; i++) {
                    counters[i] = 5;
                }
                Connect_4.ClearArr();
                Connect_4.clearFirst();
                frame.dispose();
                GUI_SetUp.GUI_Setup();
                player = 1;
                SQL_Connection.CloseConnection();
            }
        });

        results.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(GUI_SetUp.howPlayers == 1){
                    JOptionPane.showMessageDialog(null,GUI_SetUp.player01+" - "+SQL_Connection.SeeResult(GUI_SetUp.player01)
                            +"\n"+GUI_SetUp.player02+" - "+SQL_Connection.SeeResult(GUI_SetUp.player02));
                }else{
                    JOptionPane.showMessageDialog(null,"wins - "+SQL_Connection.SeeResult(GUI_SetUp.player01)
                            +"\n"+"loses - "+SQL_Connection.SeeResult(GUI_SetUp.player02));
                }
            }
        });


        panel.setLayout(null);

        frame.add(background);

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                background.add(Ggame[i][j]);
            }
        }

        for (int i = 0; i < 7; i++) {
            background.add(gameBackground[i]);
        }


        background.add(downArrow);
        background.add(newGame);
        background.add(results);
        background.add(back);
        background.add(turnArea);


        frame.add(panel);
        frame.setSize(800, 533);
        frame.setTitle("Connect 4");
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}