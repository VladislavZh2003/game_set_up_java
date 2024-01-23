package Game_Physics;

import java.util.Random;

public class Tic_tac_toe {

    public static char[][] gameSet = new char[3][3];
    public static int firstChar = 0;//for method First()
    public static char thisBe = ' ';//for method First()

    public static void addSymbol(int column,int row,int symbol){
        if(symbol == 1)
            gameSet[column][row] = 'x';
        else
            gameSet[column][row] = 'o';
    }

    public static int CheckWin(){

        for(int i = 0; i<3; i++) {
            for(int j = 0; j<3; j++){
                if(i == 0){
                    if(gameSet[i][j] == 'x' && gameSet[i+1][j] == 'x' && gameSet[i+2][j] == 'x')
                        return j+1;
                    if(gameSet[i][j] == 'o' && gameSet[i+1][j] == 'o' && gameSet[i+2][j] == 'o')
                        return j+5;
                }
                if(j == 0){
                    if (gameSet[i][j] == 'x' && gameSet[i][j + 1] == 'x' && gameSet[i][j + 2] == 'x')
                        return (i+1)*(-1);
                    if (gameSet[i][j] == 'o' && gameSet[i][j + 1] == 'o' && gameSet[i][j + 2] == 'o')
                        return (i+5)*(-1);
                }

            }

        }
        if(gameSet[0][0] == 'x' && gameSet[1][1] == 'x' && gameSet[2][2] == 'x')
            return 4;
        if(gameSet[0][0] == 'o' && gameSet[1][1] == 'o' && gameSet[2][2] == 'o')
            return 9;
        if(gameSet[0][2] == 'x' && gameSet[1][1] == 'x' && gameSet[2][0] == 'x')
            return -4;
        if(gameSet[0][2] == 'o' && gameSet[1][1] == 'o' && gameSet[2][0] == 'o')
            return -9;

        return 0;
    }

    public static boolean CheckForNotFull(){
        for(int i = 0; i<3; i++){
            for(int j = 0; j<3; j++){
                if(gameSet[i][j] != 'x' && gameSet[i][j] != 'o')
                    return true;
            }
        }
        return false;
    }

    public static int Convert(int row, int column){
        switch (row){
            case 0:
                switch (column){
                    case 0:
                        return 1;
                    case 1:
                        return 2;
                    case 2:
                        return 3;
                }
            case 1:
                switch (column){
                    case 0:
                        return 4;
                    case 1:
                        return 5;
                    case 2:
                        return 6;
                }
            case 2:
                switch (column){
                    case 0:
                        return 7;
                    case 1:
                        return 8;
                    case 2:
                        return 9;
                }
        }
        return 0;
    }

    public static char First(){

        if(firstChar == 0){
            for(int i = 0; i<3; i++){
                for(int j = 0; j<3; j++){
                    if(gameSet[i][j] == 'x' || gameSet[i][j] == 'o'){
                        thisBe = gameSet[i][j];
                        firstChar = 1;
                        return thisBe;
                    }
                }
            }
        }
        if(thisBe == ' ')
            return 0;
        else
            return thisBe;


    }

    public static void ClearArr(){
        for(int i = 0; i<3; i++){
            for(int j = 0; j<3; j++){
                gameSet[i][j] = ' ';
            }
        }
    }

    public static int TwoPlayers(int player){
        Random rand = new Random();

        int rand_row;
        int rand_column;

        if(CheckForNotFull()){
            for(;;){
                rand_row = rand.nextInt(3);
                rand_column = rand.nextInt(3);
                if(gameSet[rand_row][rand_column] != 'x' && gameSet[rand_row][rand_column] != 'o'){
                    if(player == 1){
                        gameSet[rand_row][rand_column] = 'x';
                        return Convert(rand_row,rand_column);
                    }
                    else{
                        gameSet[rand_row][rand_column] = 'o';
                        return Convert(rand_row,rand_column);
                    }
                }
            }
        }
        else{
            return 0;
        }

    }

}

