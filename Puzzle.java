package Puzzle;

import BasicIO.*;

import java.util.Arrays;

/* shawki sha'aban student ID:7356108
Cosc 1P03 - Assignment 1-word search Puzzle
2022/1/28
 */

public class Puzzle {
    private ASCIIDataFile File; //data file input
    private ASCIIDisplayer display; //displayer
    String[] wordListS = new String[21];
    String[] puzzleS = new String[25];
    private char[][] puzzle = new char[puzzleS.length][];//char array for the word puzzle
    private char[][] wordList = new char[wordListS.length][];//char array for the words to look for
    private char[][] output = new char[25][25];// char array to print solutions
    private int wordsFound = 0;//checks how many words have been found
    private int chk = 0;//checks if a word has been found



 // constructor for the word search puzzle that will read in the words and puzzle
    // then fill the output with empty spaces and the comparing the word list with the puzzle
    // prints the number of words and solution puzzle in gridOutput*/

    public Puzzle() {


        File = new ASCIIDataFile();
        display = new ASCIIDisplayer();

        loadWords();
        loadPuzzle();
        addEmptySpace();
        compareLetters();
        gridOutput();

        System.out.println(wordsFound);


        display.close();
        File.close();
    }

    /*this method will read in the 21 words in the word list */

    private void loadWords(){
        for(int i=0;i<21;i++){
            wordListS[i] = File.readString();
        }
        for(int i = 0; i<wordListS.length;i++){
            wordList[i] = wordListS[i].toCharArray();
        }
    }

   /*this method will read the characters from word puzzle in a char array*/

    private void loadPuzzle(){
        for(int i = 0; i<25;i++){
            puzzleS[i] = File.readString();
        }
        for(int i = 0; i<puzzleS.length; i++){
            System.out.println(puzzle.length);
            puzzle[i] = puzzleS[i].toCharArray();

        }
    }

    /* This method will fill the output array with blank spaces*/

    private void addEmptySpace(){
        for(int i = 0; i< output.length; i++){
            Arrays.fill(output[i],' ');
        }
    }

    /*This method compares the first letter of every word in the list to the puzzle character
    then it will move on to the next character in the puzzle
     */
    private void compareLetters(){
        for(int i = 0; i< puzzle.length;i++){
            for(int j = 0; j< puzzle[i].length;j++){
                for(int k = 0; k<wordList.length;k++){
                    if(puzzle[i][j]==Character.toUpperCase(wordList[k][0])){
                        searchPuzzleDir(i,j,k);
                    }
                }
            }
        }
    }
/*
@param i is used to find the row the word puzzle is in
@param j is used to find the column that the word puzzle in
@param k is used to determine what word in the word list it is referencing
this method is meant to check in all 8 directions to match the characters
 */
    private void searchPuzzleDir(int i, int j, int k){
       //searching from left to right horizontally
        chk = 0;

        for(int c = 0; c<wordList[k].length;c++){

            if(j+wordList[k].length<=25 && Character.toUpperCase(wordList[k][c])==puzzle[i][j+c]){
                chk++;
            }
        }
        if(chk==wordList[k].length){
            wordsFound++;
            for(int c = 0; c<wordList[k].length;c++){
                output[i][j+c]=puzzle[i][j+c];
            }
        }
        // searching from right to left horizontally
        chk = 0;
        for(int c = 0; c<wordList[k].length;c++){
            if(j-wordList[k].length>=0&&Character.toUpperCase(wordList[k][c])==puzzle[i][j-c]){
                chk++;
            }
        }
        if(chk==wordList[k].length){
            wordsFound++;
            for(int c=0; c<wordList[k].length;c++){
                output[i][j-c]=puzzle[i][j-c];
            }
        }
        // top to bottom vertically
        chk =0;
        for(int c = 0; c<wordList[k].length;c++){
            if(i+wordList[k].length<=25 && Character.toUpperCase(wordList[k][c])==puzzle[i+c][j]){
                chk++;
            }
        }

        if(chk==wordList[k].length){
            wordsFound++;
            for(int c = 0; c<wordList[k].length;c++){
                output[i+c][j]=puzzle[i+c][j];
            }
        }
        //searching bottom to top vertically
        chk = 0;
        for(int c = 0; c<wordList[k].length;c++){
            if (i - wordList[k].length>=0&&Character.toUpperCase(wordList[k][c])==puzzle[i-c][j]){
                chk++;
            }
        }
        if(chk==wordList[k].length){
            wordsFound++;
            for(int c = 0; c<wordList[k].length;c++){
                output[i-c][j]=puzzle[i-c][j];
            }
        }

        //searching top left to bottom right diagonally
        chk = 0;
        for(int c = 0; c<wordList[k].length;c++){
            if(j+wordList[k].length<=25 && i +wordList[k].length<=25 && Character.toUpperCase(wordList[k][c])==puzzle[i+c][j+c]){
                chk++;
            }
        }
        if(chk==wordList[k].length){
            wordsFound++;
            for(int c = 0; c<wordList[k].length;c++){
                output[i+c][j+c]=puzzle[i+c][j+c];
            }
        }

        //searching bottom right to top left (reverse)
        chk= 0;
        for(int c = 0;c<wordList[k].length;c++ ){
            if(j-wordList[k].length>=0 && i - wordList[k].length>=0 && Character.toUpperCase(wordList[k][c])==puzzle[i-c][j-c]){
                chk++;
            }
        }
        if(chk==wordList[k].length){
            wordsFound++;
            for(int c = 0; c<wordList[k].length;c++){
                output[i-c][j-c]=puzzle[i-c][j-c];
            }
        }

        //searching bottom left to top right
        chk = 0;
        for(int c = 0; c<wordList[k].length;c++){
            if(j+wordList[k].length<=25&&i-wordList[k].length>=0&&Character.toUpperCase(wordList[k][c])==puzzle[i-c][j+c]){
                chk++;
            }
        }
        if(chk==wordList[k].length){
            wordsFound++;
            for(int c = 0;c<wordList[k].length;c++){
                output[i-c][j+c]=puzzle[i-c][j+c];
            }
        }

        //search top right to bottom left
        chk=0;
        for(int c=0; c<wordList[k].length;c++){
            if(j-wordList[k].length>=0 && i+wordList[k].length<=25 && Character.toUpperCase(wordList[k][c])==puzzle[i+c][j-c]){
                chk++;
            }
        }
        if(chk==wordList[k].length){
            wordsFound++;
            for(int c = 0; c<wordList[k].length;c++){
                output[i+c][j-c]=puzzle[i+c][j-c];
            }
        }
    }
/*
This method is used to print the how many words were found,
aswell as the 2D array for the word puzzle solution
 */
    private void gridOutput(){
        System.out.println("number of words found:" + wordsFound);
        display.writeString(   "number of words found:");
        display.writeInt(wordsFound);

        System.out.println("\n");



        for(int x = 0; x<output.length;x++){
            for(int y = 0; y<output[x].length;y++){
                System.out.print(" "+ output[x][y]);

                display.writeC(output[x][y]);

            }

            System.out.println();
            display.newLine();

        }
    }

    public static void main(String[] args) {
        Puzzle P = new Puzzle();
    }
}




