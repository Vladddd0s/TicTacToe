package tictactoe;



public class Game {
    String winner="N";
    char[][] field ;
    void Win(char player){
        if (player == 'X')
        {
            winner="X";
        }
        if (player == 'O')
        {
            winner="O";
        }
    }
     Game(){
        field=new  char[3][3];

     }
     void fill(){
         for(int i=0;i<3;i++){
             for(int j=0;j<3;j++){
                 field[i][j]=' ';
             }
         }
     }
    public boolean Winner(char player)  {
        boolean win;
        for(int i=0;i<3;i++) {
           win=true;
           for (int j = 0; j < 3; j++) {
               if (field[i][j] != player) {
                   win=false;
                   break;
               }

           }
           if(win) {
               Win(player);
               return true;
           }

       }

       for(int j=0;j<3;j++) {
           win = true;
           for (int i = 0; i < 3; i++) {
               if (field[i][j] != player) {
                   win = false;
                   break;
               }
           }
           if(win) {
               Win(player);
               return true;
           }
       }
        win=true;
        for(int i=0; i<3; i++){
            if(field[i][i]!=player){
                win=false;
                break;
            }
        }
        if(win) {
            Win(player);
            return true;
        }
        win=true;
        for(int i=0; i<3; i++){
            if(field[i][3-i-1]!=player){
                win=false;
                break;
            }
        }
        if(win) {
            Win(player);
            return true;
        }
        win=true;
        for (int i = 0; i < 3; i++) {
            for (int j= 0; j < 3; j++) {
                if (field[i][j] != ' ') {
                    win = false;
                    break;
                }
            }
            if(!win){
                break;
            }
        }
        if(win){
            winner="Draw";
            return true;
        }


       return false;
    }
}
