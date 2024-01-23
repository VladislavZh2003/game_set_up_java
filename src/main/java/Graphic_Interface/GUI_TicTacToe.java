package Graphic_Interface;

import com.company.SQL_Connection;
import Game_Physics.Tic_tac_toe;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI_TicTacToe {
    public static int players;//for method GUI_TicTacToe()

    public static void PlayMusic(){
        try{
            FileInputStream fis = new FileInputStream("TicTacToe materials\\ES_Pencil Write 6 - SFX Producer.mp3");
            Player player = new Player(fis);
            player.play();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (JavaLayerException e){
            e.printStackTrace();
        }
    }
    public static void TicTacToe_Button_Physics(JLabel xo, int column, int row, JTextField _win, int X, int Y, int widht, int height ){
        Tic_tac_toe.addSymbol(column,row,players);
        Tic_tac_toe.First();
        if(players == 1){
            xo.setIcon(new ImageIcon("TicTacToe materials\\x sea chess.png"));
            _win.setText(GUI_SetUp.player02+" turn:");
            players = 2;
        }else{
            xo.setIcon(new ImageIcon("TicTacToe materials\\o sea chess.png"));
            _win.setText(GUI_SetUp.player01+" turn:");
            players = 1;
        }
        xo.setBounds(X,Y,widht,height);

    }
    public static void TicTacToe_Button_Physics_VS_Comp(JLabel xo,JTextField _win, int X, int Y, int widht, int height){
        xo.setIcon(new ImageIcon("TicTacToe materials\\o sea chess.png"));
        _win.setText(GUI_SetUp.player01+" turn:");
        players = 1;
        xo.setBounds(X,Y,widht,height);
    }
    public static void TicTacToe_WinChecker(JLabel _verTape,JLabel _horTape,JLabel _diagonalTape,JLabel _revDiagonalTape,JTextField _win){
        if(Tic_tac_toe.CheckWin()  != 0) {
            switch (Tic_tac_toe.CheckWin()) {
                case 1:
                case 5:
                    _verTape.setBounds(73, 65, 10, 330);
                    break;
                case 2:
                case 6:
                    _verTape.setBounds(190,65,10,330);
                    break;
                case 3:
                case 7:
                    _verTape.setBounds(303,65,10,330);
                    break;
                case -1:
                case -5:
                    _horTape.setBounds(28, 114, 330, 10);
                    break;
                case -2:
                case -6:
                    _horTape.setBounds(28,218,330,10);
                    break;
                case -3:
                case -7:
                    _horTape.setBounds(28,330,330,10);
                    break;
                case 4:
                case 9:
                    _diagonalTape.setBounds(24, 60, 330, 330);
                    break;
                case -4:
                case -9:
                    _revDiagonalTape.setBounds(26,65,330,330);
                    break;
            }
            if (Tic_tac_toe.CheckWin() >= 5 || Tic_tac_toe.CheckWin() <= -5) {
                _win.setText(GUI_SetUp.player02 + " win!");
                SQL_Connection.AddPoints(GUI_SetUp.player02);
            } else {
                _win.setText(GUI_SetUp.player01 + " win!");
                SQL_Connection.AddPoints(GUI_SetUp.player01);
            }
        }
    }
    public static void TurnOffButtons(JButton pl1,JButton pl2,JButton pl3,JButton pl4,JButton pl5,
                                      JButton pl6, JButton pl7,JButton pl8,JButton pl9){
        pl1.setVisible(false);
        pl2.setVisible(false);
        pl3.setVisible(false);
        pl4.setVisible(false);
        pl5.setVisible(false);
        pl6.setVisible(false);
        pl7.setVisible(false);
        pl8.setVisible(false);
        pl9.setVisible(false);
        PlayMusic();
    }
    public static void TurnOnButtons(JButton pl1,JButton pl2,JButton pl3,JButton pl4,JButton pl5,
                                     JButton pl6, JButton pl7,JButton pl8,JButton pl9){
        pl1.setVisible(true);
        pl2.setVisible(true);
        pl3.setVisible(true);
        pl4.setVisible(true);
        pl5.setVisible(true);
        pl6.setVisible(true);
        pl7.setVisible(true);
        pl8.setVisible(true);
        pl9.setVisible(true);
    }
    public static void ActionListener(final JButton[] pl,final int whoBl, final JLabel[] xo, final JTextField win, final JLabel verTape,
                                      final JLabel horTape, final JLabel diagonalTape, final JLabel revDiagonalTape, final int x, final int y,
                                      final int column, final int row){
        pl[whoBl].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TicTacToe_Button_Physics(xo[whoBl],row, column, win, x, y, 100, 90);
                pl[whoBl].setVisible(false);
                if (Tic_tac_toe.CheckWin() != 0) {
                    TicTacToe_WinChecker(verTape, horTape, diagonalTape, revDiagonalTape, win);
                    TurnOffButtons(pl[0], pl[1], pl[2], pl[3], pl[4], pl[5], pl[6], pl[7], pl[8]);
                }else{
                    if (GUI_SetUp.howPlayers == 2 && players == 2) {
                        switch (Tic_tac_toe.TwoPlayers(players)) {
                            case 1:
                                TicTacToe_Button_Physics_VS_Comp(xo[0],win,30,74,100,90);
                                pl[0].setVisible(false);
                                if (Tic_tac_toe.CheckWin() != 0) {
                                    TicTacToe_WinChecker(verTape, horTape, diagonalTape, revDiagonalTape, win);
                                    TurnOffButtons(pl[0], pl[1], pl[2], pl[3], pl[4], pl[5], pl[6], pl[7], pl[8]);
                                }
                                break;
                            case 2:
                                TicTacToe_Button_Physics_VS_Comp(xo[1],win,148, 74, 100, 90);
                                pl[1].setVisible(false);
                                if (Tic_tac_toe.CheckWin() != 0) {
                                    TicTacToe_WinChecker(verTape, horTape, diagonalTape, revDiagonalTape, win);
                                    TurnOffButtons(pl[0], pl[1], pl[2], pl[3], pl[4], pl[5], pl[6], pl[7], pl[8]);
                                }
                                break;
                            case 3:
                                TicTacToe_Button_Physics_VS_Comp(xo[2],win,260, 74, 100,90);
                                pl[2].setVisible(false);
                                if (Tic_tac_toe.CheckWin() != 0) {
                                    TicTacToe_WinChecker(verTape, horTape, diagonalTape, revDiagonalTape, win);
                                    TurnOffButtons(pl[0], pl[1], pl[2], pl[3], pl[4], pl[5], pl[6], pl[7], pl[8]);
                                }
                                break;
                            case 4:
                                TicTacToe_Button_Physics_VS_Comp(xo[3],win,30, 179, 100, 90);
                                pl[3].setVisible(false);
                                if (Tic_tac_toe.CheckWin() != 0) {
                                    TicTacToe_WinChecker(verTape, horTape, diagonalTape, revDiagonalTape, win);
                                    TurnOffButtons(pl[0], pl[1], pl[2], pl[3], pl[4], pl[5], pl[6], pl[7], pl[8]);
                                }
                                break;
                            case 5:
                                TicTacToe_Button_Physics_VS_Comp(xo[4],win,148, 179, 100, 90);
                                pl[4].setVisible(false);
                                if (Tic_tac_toe.CheckWin() != 0) {
                                    TicTacToe_WinChecker(verTape, horTape, diagonalTape, revDiagonalTape, win);
                                    TurnOffButtons(pl[0], pl[1], pl[2], pl[3], pl[4], pl[5], pl[6], pl[7], pl[8]);
                                }
                                break;
                            case 6:
                                TicTacToe_Button_Physics_VS_Comp(xo[5],win,260, 179, 100, 90);
                                pl[5].setVisible(false);
                                if (Tic_tac_toe.CheckWin() != 0) {
                                    TicTacToe_WinChecker(verTape, horTape, diagonalTape, revDiagonalTape, win);
                                    TurnOffButtons(pl[0], pl[1], pl[2], pl[3], pl[4], pl[5], pl[6], pl[7], pl[8]);
                                }
                                break;
                            case 7:
                                TicTacToe_Button_Physics_VS_Comp(xo[6],win,30, 290, 100, 90);
                                pl[6].setVisible(false);
                                if (Tic_tac_toe.CheckWin() != 0) {
                                    TicTacToe_WinChecker(verTape, horTape, diagonalTape, revDiagonalTape, win);
                                    TurnOffButtons(pl[0], pl[1], pl[2], pl[3], pl[4], pl[5], pl[6], pl[7], pl[8]);
                                }
                                break;
                            case 8:
                                TicTacToe_Button_Physics_VS_Comp(xo[7],win,148, 290, 100, 90);
                                pl[7].setVisible(false);
                                if (Tic_tac_toe.CheckWin() != 0) {
                                    TicTacToe_WinChecker(verTape, horTape, diagonalTape, revDiagonalTape, win);
                                    TurnOffButtons(pl[0], pl[1], pl[2], pl[3], pl[4], pl[5], pl[6], pl[7], pl[8]);
                                }
                                break;
                            case 9:
                                TicTacToe_Button_Physics_VS_Comp(xo[8],win,260, 290, 100, 90);
                                pl[8].setVisible(false);
                                if (Tic_tac_toe.CheckWin() != 0) {
                                    TicTacToe_WinChecker(verTape, horTape, diagonalTape, revDiagonalTape, win);
                                    TurnOffButtons(pl[0], pl[1], pl[2], pl[3], pl[4], pl[5], pl[6], pl[7], pl[8]);
                                }
                                break;
                            default:
                                break;
                        }
                    }
                }
            }
        });
    }
    public static void GUI_TicTactoe(){
        final JFrame frame = new JFrame();
        JPanel panel  = new JPanel();

        JLabel background;
        final JButton pl[] = new JButton[9];
        final JLabel xo[] = new JLabel[9];
        final JLabel verTape;
        final JLabel horTape;
        final JLabel diagonalTape;
        final JLabel revDiagonalTape;
        final JTextField win;
        JButton newGame;
        JButton seeResult;
        JButton back;

        background = new JLabel("",new ImageIcon("TicTacToe materials\\for sea chess.png"),JLabel.CENTER);


        for (int i = 0; i < 9; i++) {
            pl[i] = new JButton("",new ImageIcon("TicTacToe materials\\sqere sea chess.png"));
            xo[i] = new JLabel();
        }
        pl[0].setBounds(21,74,116,94);
        pl[1].setBounds(141,74,108,94);
        pl[2].setBounds(253,74,110,94);
        pl[3].setBounds(21,173,116,107);
        pl[4].setBounds(141,173,108,107);
        pl[5].setBounds(253,173,110,107);
        pl[6].setBounds(21,285,116,102);
        pl[7].setBounds(141,285,108,102);
        pl[8].setBounds(253,285,110,102);

        verTape = new JLabel(new ImageIcon("TicTacToe materials\\vertical l.png"));
        horTape = new JLabel(new ImageIcon("TicTacToe materials\\horizont.png"));
        diagonalTape = new JLabel(new ImageIcon("TicTacToe materials\\diagonale trans.png"));
        revDiagonalTape = new JLabel(new ImageIcon("TicTacToe materials\\rev diagonale trans.png"));

        win = new JTextField();
        win.setText(GUI_SetUp.player01+" turn:");
        win.setBounds(20,400,120,21);
        newGame = new JButton("New game!");
        newGame.setBounds(260,30,100,21);
        seeResult = new JButton("See result!");
        seeResult.setBounds(23,30,100,21);
        players = 1;

        back = new JButton("<");
        back.setHorizontalAlignment(SwingConstants.LEFT);
        back.setBounds(0,440,50,15);

        ActionListener(pl,0,xo,win,verTape,horTape,diagonalTape,revDiagonalTape,30,74,0,0);
        ActionListener(pl,1,xo,win,verTape,horTape,diagonalTape,revDiagonalTape,148, 74,1,0);
        ActionListener(pl,2,xo,win,verTape,horTape,diagonalTape,revDiagonalTape,260,74,2,0);
        ActionListener(pl,3,xo,win,verTape,horTape,diagonalTape,revDiagonalTape,30,179,0,1);
        ActionListener(pl,4,xo,win,verTape,horTape,diagonalTape,revDiagonalTape,148, 179,1,1);
        ActionListener(pl,5,xo,win,verTape,horTape,diagonalTape,revDiagonalTape,260,179,2,1);
        ActionListener(pl,6,xo,win,verTape,horTape,diagonalTape,revDiagonalTape,30,290,0,2);
        ActionListener(pl,7,xo,win,verTape,horTape,diagonalTape,revDiagonalTape,148, 290,1,2);
        ActionListener(pl,8,xo,win,verTape,horTape,diagonalTape,revDiagonalTape,260,290,2,2);

        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                xo[0].setBounds(1000,1000,1,1);
                xo[1].setBounds(1000,1000,1,1);
                xo[2].setBounds(1000,1000,1,1);
                xo[3].setBounds(1000,1000,1,1);
                xo[4].setBounds(1000,1000,1,1);
                xo[5].setBounds(1000,1000,1,1);
                xo[6].setBounds(1000,1000,1,1);
                xo[7].setBounds(1000,1000,1,1);
                xo[8].setBounds(1000,1000,1,1);
                horTape.setBounds(1000,1000,1,1);
                verTape.setBounds(1000,1000,1,1);
                diagonalTape.setBounds(1000,1000,1,1);
                revDiagonalTape.setBounds(1000,1000,1,1);

                TurnOnButtons(pl[0],pl[1],pl[2],pl[3],pl[4],pl[5],pl[6],pl[7],pl[8]);
                win.setText("");

                if(Tic_tac_toe.CheckWin() >= 5 || Tic_tac_toe.CheckWin() <= -5) {
                    Tic_tac_toe.ClearArr();
                    Tic_tac_toe.firstChar = 0;
                    Tic_tac_toe.thisBe = ' ';
                    win.setText(GUI_SetUp.player02+" turn:");
                    players = 2;
                    if(GUI_SetUp.howPlayers == 2) {
                        switch (Tic_tac_toe.TwoPlayers(players)) {
                            case 1:
                                TicTacToe_Button_Physics_VS_Comp(xo[0],win,30,74,100,90);
                                pl[0].setVisible(false);
                                break;
                            case 2:
                                TicTacToe_Button_Physics_VS_Comp(xo[1],win,148, 74, 100, 90);
                                pl[1].setVisible(false);
                                break;
                            case 3:
                                TicTacToe_Button_Physics_VS_Comp(xo[2],win,260, 74, 100,90);
                                pl[2].setVisible(false);
                                break;
                            case 4:
                                TicTacToe_Button_Physics_VS_Comp(xo[3],win,30, 179, 100, 90);
                                pl[3].setVisible(false);
                                break;
                            case 5:
                                TicTacToe_Button_Physics_VS_Comp(xo[4],win,148, 179, 100, 90);
                                pl[4].setVisible(false);
                                break;
                            case 6:
                                TicTacToe_Button_Physics_VS_Comp(xo[5],win,260, 179, 100, 90);
                                pl[5].setVisible(false);
                                break;
                            case 7:
                                TicTacToe_Button_Physics_VS_Comp(xo[6],win,30, 290, 100, 90);
                                pl[6].setVisible(false);
                                break;
                            case 8:
                                TicTacToe_Button_Physics_VS_Comp(xo[7],win,148, 290, 100, 90);
                                pl[7].setVisible(false);
                                break;
                            case 9:
                                TicTacToe_Button_Physics_VS_Comp(xo[8],win,260, 290, 100, 90);
                                pl[8].setVisible(false);
                                break;
                            default:
                                break;
                        }
                    }
                }
                else if((Tic_tac_toe.CheckWin() < 5 || Tic_tac_toe.CheckWin() > -5) && Tic_tac_toe.CheckWin() != 0){
                    Tic_tac_toe.ClearArr();
                    Tic_tac_toe.firstChar = 0;
                    Tic_tac_toe.thisBe = ' ';
                    win.setText(GUI_SetUp.player01+" turn:");
                    players = 1;
                }
                else{
                    if(Tic_tac_toe.First() == 'x'){
                        players = 1;
                        Tic_tac_toe.ClearArr();
                        Tic_tac_toe.firstChar = 0;
                        Tic_tac_toe.thisBe = ' ';
                        win.setText(GUI_SetUp.player01+" turn:");
                    }
                    else if(Tic_tac_toe.First() == 'o'){
                        players = 2;
                        Tic_tac_toe.ClearArr();
                        Tic_tac_toe.firstChar = 0;
                        Tic_tac_toe.thisBe = ' ';
                        win.setText(GUI_SetUp.player02+" turn:");
                        if(GUI_SetUp.howPlayers == 2) {
                            switch (Tic_tac_toe.TwoPlayers(players)) {
                                case 1:
                                    TicTacToe_Button_Physics_VS_Comp(xo[0],win,30,74,100,90);
                                    pl[0].setVisible(false);
                                    break;
                                case 2:
                                    TicTacToe_Button_Physics_VS_Comp(xo[1],win,148, 74, 100, 90);
                                    pl[1].setVisible(false);
                                    break;
                                case 3:
                                    TicTacToe_Button_Physics_VS_Comp(xo[2],win,260, 74, 100,90);
                                    pl[2].setVisible(false);
                                    break;
                                case 4:
                                    TicTacToe_Button_Physics_VS_Comp(xo[3],win,30, 179, 100, 90);
                                    pl[3].setVisible(false);
                                    break;
                                case 5:
                                    TicTacToe_Button_Physics_VS_Comp(xo[4],win,148, 179, 100, 90);
                                    pl[4].setVisible(false);
                                    break;
                                case 6:
                                    TicTacToe_Button_Physics_VS_Comp(xo[5],win,260, 179, 100, 90);
                                    pl[5].setVisible(false);
                                    break;
                                case 7:
                                    TicTacToe_Button_Physics_VS_Comp(xo[6],win,30, 290, 100, 90);
                                    pl[6].setVisible(false);
                                    break;
                                case 8:
                                    TicTacToe_Button_Physics_VS_Comp(xo[7],win,148, 290, 100, 90);
                                    pl[7].setVisible(false);
                                    break;
                                case 9:
                                    TicTacToe_Button_Physics_VS_Comp(xo[8],win,260, 290, 100, 90);
                                    pl[8].setVisible(false);
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                }
            }
        });

        seeResult.addActionListener(new ActionListener() {
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

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Tic_tac_toe.ClearArr();
                frame.dispose();
                Graphic_Interface.GUI_SetUp.GUI_Setup();
                SQL_Connection.CloseConnection();
            }
        });


        for (int i = 0; i < 9; i++) {
            background.add(pl[i]);
        }

        background.add(verTape);
        background.add(horTape);
        background.add(diagonalTape);
        background.add(revDiagonalTape);

        for (int i = 0; i < 9; i++) {
            background.add(xo[i]);
        }

        background.add(win);
        background.add(newGame);
        background.add(seeResult);
        background.add(back);

        panel.setLayout(null);

        frame.add(panel);
        frame.add(background);
        frame.setSize(400, 500);
        frame.setTitle("Player data!");
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}
