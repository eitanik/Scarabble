package test;
import java.util.*;

public class Board {
    private static Tile boardArr[][] = new Tile[15][15];
    private Word wordsArr[] = new Word[15];
    private int wordsCount = 0;
    private static Board gameBoard = null;
    private String board[][] = new String[15][15];
    private static int countForScore =0;

    private static int countForBoard=0;

    private Board(){
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                boardArr[i][j] = null;
            }
        }
        for (int i = 0; i < 15; i++) {
            if (i == 7) {

                //1- red, 2-Tchelet,3-yellow,4-Blue,*-Star

                this.board[i][0] = "3";
                this.board[i][3] = "2";
                this.board[i][7] = "*";
                this.board[i][11] = "2";
                this.board[i][14] = "3";
            }
            if (i == 0 || i == 14) {
                this.board[i][0] = "1";
                this.board[i][7] = "1";
                this.board[i][14] = "1";
                this.board[i][3] = "2";
                this.board[i][11] = "2";
            }
            if (i == 1 || i == 13) {
                this.board[i][1] = "3";
                this.board[i][13] = "3";
                this.board[i][5] = "4";
                this.board[i][9] = "4";
            }
            if (i == 2 || i == 12) {
                this.board[i][2] = "3";
                this.board[i][12] = "3";
                this.board[i][6] = "2";
                this.board[i][8] = "2";
            }
            if (i == 3 || i == 11) {
                this.board[i][0] = "2";
                this.board[i][7] = "2";
                this.board[i][14] = "2";
                this.board[i][3] = "3";
                this.board[i][11] = "3";
            }
            if (i == 4 || i == 10) {
                this.board[i][4] = "3";
                this.board[i][10] = "3";
            }
            if (i == 5 || i == 9) {
                this.board[i][1] = "4";
                this.board[i][5] = "4";
                this.board[i][9] = "4";
                this.board[i][13] = "4";
            }
            if (i == 6 || i == 8) {
                this.board[i][2] = "2";
                this.board[i][6] = "2";
                this.board[i][8] = "2";
                this.board[i][12] = "2";


            }
        }
    }

    public static Board getBoard(){
        if(gameBoard == null){
            gameBoard = new Board();
        }
        return gameBoard;
    }


    public Tile[][] getTiles()
    {
        Tile [][] copy=new Tile[15][15];
        for(int i=0;i<15;i++) {
            System.arraycopy(boardArr[i], 0, copy[i], 0, 15);
        }

        return copy;
    }
    public boolean boardLegal(Word w) {
        boolean x;
        int r = w.row;
        int c = w.col;
        if (w.row < 0 || w.col < 0) {
            return false;
        }


        if (countForBoard == 0) {
            countForBoard++;
            for (int i = 0; i < w.length; i++) {
                if (r == 7 && c == 7) {
                    return true;
                }
                if (w.vertical) {
                    r++;
                } else {
                    c++;
                }
            }
            return false;
        } else {


            if (w.vertical) {
                if (r + w.length <= 14 && c <= 14) {
                    return true;

                }
                return false;

            } else {
                if (r <= 14 && c + w.length <= 14) {
                    return true;
                }
            }
            return false;

        }
    }


    public boolean dictionaryLegal(Word w){
        return true;
    }

    public ArrayList<Word> getWords(Word w)/////////////////////////////////////////
    {
        int col = w.col;
        int row = w.row;
        int start;
        int finish;
        boolean vertical = w.vertical;
        ArrayList<Tile> temp = new ArrayList<Tile>();
        ArrayList<Word> words = new ArrayList<Word>();
        words.add(w);

        for(int i = 0; i < w.tiles.length; i++){
            if(vertical){
                start = col;
                finish = col;
                while(start-1>-1 && boardArr[row+i][start-1]!=null){
                    start--;
                }
                while(finish+1<15 && boardArr[row+i][finish+1]!=null){
                    finish++;
                }
                if(start != col|| finish != col){
                    for(int j = start; j <= finish; j++){
                        temp.add(boardArr[row+i][j]);
                    }
                    Word word = new Word(temp.toArray(new Tile[temp.size()]), row + i, start, false);
                    for (int j = 0; j < wordsCount; j++) {
                        if (wordsArr[j].equals(word)) {
                            word = null;
                            break;
                        }
                    }
                    if (word != null) {
                        words.add(word);
                    }
                }

            }
            else//not vertical
            {
                start = row;
                finish = row;
                while(start-1>-1 && boardArr[start-1][col+i]!=null){
                    start--;
                }
                while(finish+1<15 && boardArr[finish+1][col+i]!=null){
                    finish++;
                }
                if(start != row|| finish != row){
                    temp.clear();
                    for(int j = start; j <= finish; j++){
                        temp.add(boardArr[j][col+i]);
                    }
                    if(temp.size()>0){
                        Word word = new Word(temp.toArray(new Tile[temp.size()]), start, col+i, true);
                        for(int j = 0; j < wordsCount; j++){
                            if(wordsArr[j].equals(word)){
                                word = null;
                                break;
                            }
                        }
                        if(word!=null){
                            words.add(word);
                        }
                    }
                }

            }
        }
        return words;
    }

    public int getScore(Word w){

        int score = 0;
        int x = w.getRow();
        int y = w.getCol();
        boolean vertical = w.vertical;
        int k1=0;
        int sum=0;
        int scoreWord=0;


        for (int i = 0; i < w.tiles.length; i++)
        {
            scoreWord = scoreWord + w.tiles[i].score;
            if (w.vertical) {
                x++;
                } else {
                    y++;
                }

            }


        x = w.row;
        y = w.col;



        for (int i = 0; i < w.tiles.length; i++) {

            if (board[x][y] == "1")
            {

                k1=1;
            }


            if (board[x][y] == "2") {
                sum = sum + (w.tiles[i].score);

            }

            if (board[x][y] == "3") {
                /*
                scoreWord = scoreWord * 2;
                */
                k1=3;
            }


            if (board[x][y] == "4") {
                sum = sum + (w.tiles[i].score * 2);
            }

            if (board[x][y] == "*"&& countForScore ==0) {
                scoreWord = scoreWord * 2;
            }


            if (w.vertical) {
                x++;
            } else {
                y++;
            }
        }

        sum += scoreWord;
        if(k1==1)
        {
            sum = sum * 3;
        }
        if(k1==3)
        {
            sum=sum*2;
        }
        countForScore++;
        return sum;

    }

    public int tryPlaceWord(Word w){
        int score = 0;
        int col = w.col;
        int row = w.row;
        boolean vertical = w.vertical;


        for(int i = 0 ; i<w.tiles.length; i++){
            if(vertical)
            {
                if(w.tiles[i]==null){
                    w.tiles[i] = boardArr[row+i][col];
                }
            }
            else//not vertical
            {
                if(w.tiles[i]==null)
                {
                    w.tiles[i] = boardArr[row][col+i];
                }
            }
        }

        if(boardLegal(w)){
            if(dictionaryLegal(w)) {
                placeWord(w);
            }

            ArrayList<Word> words = getWords(w);
        for(Word x:words)
        {
            if(dictionaryLegal(x)){
                score += getScore(x);
            }
            }
            for(Word x:words){
                wordsArr[wordsCount] = x;
                wordsCount++;
                placeWord(x);
            }
            return score;
        }

            return 0;



    }
    public void placeWord(Word w){

        int x = w.row;
        int y = w.col;
        boolean vertical = w.vertical;
        Tile[] tiles = w.tiles;

        for(int i = 0; i < tiles.length; i++){
            if(vertical){
                boardArr[x+i][y] = tiles[i];
            }
            else{
                boardArr[x][y+i] = tiles[i];
            }
        }
    }

}
