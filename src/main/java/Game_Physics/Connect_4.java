package Game_Physics;

public class Connect_4 {
    public static char[][] connect4 = new char[6][7];
    public static int firstChar = 0;//for method First()
    public static char thisBe = ' ';//for method First()

    public static void PrintArr(){
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(connect4[i][j]);
            }
            System.out.println();
        }
        System.out.println("----------------");
    }//help function
    public static void AddSymbol(int row, int column, int player){
        if(player == 1)
            connect4[row][column] = 'x';
        else
            connect4[row][column] = 'o';
    }
    public static int[] WinChecker(char [][] field){
        int[] returnArray = new int[5];
        //Horizontal
        for(int i = 0; i<6; i++){
            for(int j = 0; j<7-3; j++){
                if(field[i][j] == 'x' && field[i][j+1] == 'x' && field[i][j+2] == 'x' && field[i][j+3] == 'x'){
                    returnArray[0] = 1;
                    returnArray[1] = i+1;
                    returnArray[2] = j+1;
                    returnArray[3] = i+1;
                    returnArray[4] = j+3+1;
                    return returnArray;
                }else if(field[i][j] == 'o' && field[i][j+1] == 'o' && field[i][j+2] == 'o' && field[i][j+3] == 'o'){
                    returnArray[0] = 2;
                    returnArray[1] = i+1;
                    returnArray[2] = j+1;
                    returnArray[3] = i+1;
                    returnArray[4] = j+3+1;
                    return returnArray;
                }
            }
        }
        //Vertical
        for(int i = 0; i<7; i++) {
            for (int j = 0; j < 6 - 3; j++) {
                if (field[j][i] == 'x' && field[j + 1][i] == 'x' && field[j + 2][i] == 'x' && field[j + 3][i] == 'x') {
                    returnArray[0] = 1;
                    returnArray[1] = j+1;
                    returnArray[2] = i+1;
                    returnArray[3] = j+3+1;
                    returnArray[4] = i+1;
                    return returnArray;
                } else if (field[j][i] == 'o' && field[j + 1][i] == 'o' && field[j + 2][i] == 'o' && field[j + 3][i] == 'o') {
                    returnArray[0] = 2;
                    returnArray[1] = j+1;
                    returnArray[2] = i+1;
                    returnArray[3] = j+3+1;
                    returnArray[4] = i+1;
                    return returnArray;
                }
            }
        }
        //RisingDiagonale
        for(int i = 3; i<6; i++){
            for(int j = 0; j<7-3; j++){
                if(field[i][j] == 'x' && field[i-1][j+1] == 'x' && field[i-2][j+2] == 'x' && field[i-3][j+3] == 'x') {
                    returnArray[0] = 1;
                    returnArray[1] = i+1;
                    returnArray[2] = j+1;
                    returnArray[3] = i-3+1;
                    returnArray[4] = j+3+1;
                    return returnArray;
                }else if(field[i][j] == 'o' && field[i-1][j+1] == 'o' && field[i-2][j+2] == 'o' && field[i-3][j+3] == 'o') {
                    returnArray[0] = 2;
                    returnArray[1] = i+1;
                    returnArray[2] = j+1;
                    returnArray[3] = i-3+1;
                    returnArray[4] = j+3+1;
                }
            }

        }
        //DecreasindDiagonale
        for(int i = 0; i<6-3; i++){
            for(int j = 0; j<7-3; j++){
                if(field[i][j] == 'x' && field[i+1][j+1] == 'x' && field[i+2][j+2] == 'x' && field[i+3][j+3] == 'x'){
                    returnArray[0] = 1;
                    returnArray[1] = i+1;
                    returnArray[2] = j+1;
                    returnArray[3] = i+3+1;
                    returnArray[4] = j+3+1;
                    return returnArray;
                }else if(field[i][j] == 'o' && field[i+1][j+1] == 'o' && field[i+2][j+2] == 'o' && field[i+3][j+3] == 'o'){
                    returnArray[0] = 2;
                    returnArray[1] = i+1;
                    returnArray[2] = j+1;
                    returnArray[3] = i+3+1;
                    returnArray[4] = j+3+1;
                    return returnArray;
                }
            }
        }
        for (int i = 0; i < 5; i++) {
            returnArray[i] = 0;
        }
        return returnArray;
    }
    public static void ClearArr(){
        for(int i = 0; i<6; i++){
            for(int j = 0; j<7; j++){
                connect4[i][j] = ' ';
            }
        }
    }
    public static char First(){

        if(firstChar == 0){
            for(int i = 0; i<6; i++){
                for(int j = 0; j<7; j++){
                    if(connect4[i][j] == 'x' || connect4[i][j] == 'o'){
                        thisBe = connect4[i][j];
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
    public static void clearFirst(){

        firstChar = 0;
        thisBe = ' ';
    }

}
