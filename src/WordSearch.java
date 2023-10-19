/*
Programmer: Kai Schenkel
Class CS 145
Data 10/17/2023
Assigment Word Serch
Sources: This was from the book and from Class and pass labs
WordSearch.java
*/

import java.util.*;
public class WordSearch {
    //This is the datafield for this class 
    private static int width;
    private static int length;
    private static char [][] playingFiled;
    private static int wordCount;
    private static ArrayList<String> words;
    private static int[] positions;
    private static String useInput;
    

    public static void main(String[] args) throws Exception {
        runGame();
    }
/*This methid takes in the players data, but there was a bug where when i tried adding 
a menu while loop this method would close the scanner that was cheaking for players input*/
    public static void dataTaker(){
        Scanner scan = new Scanner(System.in);
        wordCount = 0;
        words = new ArrayList<String>();
        System.out.println("Plese enter a Word of ");
        while (scan.hasNextLine()){
            useInput = scan.next();
            if(useInput.equals("end")){
                scan.close();
                break;
            }
            wordCount++;
            words.add(useInput);
        }
    }//End of Data Taker method 

//This method Starts up the game but only once beacuse of a bug 
    public static void runGame(){
        System.out.println("Welcome to the word search generator");
        System.out.println("This simple java program will create a word search based on words you choose");
        playerDecision();
    }

//This Method will add the chars to and 2D grid so that it looks like a word search
    public static void fillGird(){
        int between, stringLength;
        int x, y;
        positions = new int[wordCount];
        //This will print the Words from the players and print it in a vertical way
        for(int i = 0; i < wordCount; i++){
            stringLength = words.get(i).length();
            between = width - stringLength;
            x = randomRange(0, between);
            y = randomRange(0, length - 5);
            if(search(positions, y)){
                y++;
            }
            positions[i] = y;
            for(int ind = 0; ind < stringLength; ind++){
                playingFiled [x][y] = words.get(i).charAt(ind);
                x++;
            }
        }
        //This will put the words in the 2D grid horiztaly 
         for(int i = 0; i < wordCount; i++){
            stringLength = words.get(i).length();
            between = width - stringLength;
            x = randomRange(0, between);
            y = randomRange(0, length - 5);
            if(search(positions, x)){
                x++;
            }
            positions[i] = x;
            for(int ind = 0; ind < stringLength; ind++){
                playingFiled [x][y] = words.get(i).charAt(ind);
                y++;
            }
        }
        //fill empty
        for(int i = 0; i < length; i++){
            for(int j = 0; j < width; j++){
                if(playingFiled[j][i] == 0){
                    char t = (char) randomRange(97, 122);
                    playingFiled[j][i] = t;
                }
            }
        }
    }
/*Need to add a fill with blank '_' method that would show where the letters to give a solution*/

//This method just make random num for being able to put the char into differnt positions
    public static int randomRange(int low, int high){
        Random generator = new Random();
        return generator.nextInt(high-low+1) + low;
    }

//This method allows the last char to rember where the last char was placed 
    public static boolean search(int [ ] numbers, int key){
        for(int index = 0; index < numbers.length; index++){
            if(numbers[index] == key){
                return true;
            }
        }
        return false;
    }
//This method sets up the 2D array gird so that char vaules can be place into the grid
    public static void makeGrid(){
        System.out.println("Creating the Word Search");
        
        for(int i = 0; i < words.size(); i++){
            if(words.get(i).length() > width){
                width = words.get(i).length();
            }
        }
        width = width * 2;
        length = width + (width/3);
         playingFiled = new char [width][length];
         
    } //End of makeGird method


//This last method just prints the 2D char grid to the Termainal so that the player can see the word search 
    public static void printPlayingFiled(){
        for(int row = 0; row < width; row++){
            for(int col = 0; col < length; col++){
                System.out.print(playingFiled[row][col]+ " ");
            }
            System.out.println(" ");
        }
        System.out.println("done");
        System.out.println(length + "\t Lines Deep");
        System.out.println(width + "\t Lines wide");
        System.out.println("Find these Words");
        for(int i = 0; i < wordCount; i++){
            System.out.println(words.get(i));
        }
    }

    public static void playerDecision(){
        try (Scanner scan = new Scanner(System.in)) {
            boolean gameStop = false;
            System.out.println("Enter 'G' to Genrate the word search, Enter 'S' to see the Slosion, Enter 'N' to make new word search and Enter Q to quit ");
            String option = scan.nextLine().toUpperCase();
            while(gameStop != true){
                System.out.println("Enter 'G' to Genrate the word search, Enter 'S' to see the Slosion, Enter 'N' to make new word search and Enter Q to quit ");
                
                    switch (option) {
                        case "G":
                            printPlayingFiled();
                            //option = "Q";
                            break;
                        case "S":
                            System.out.println("Saying Hello in S");
                            break;
                        case "N":
                            System.out.println("Saying Hello in N");
                            dataTaker();
                            makeGrid();
                            fillGird();
                            //option = "G";
                            break;
                        case "Q":
                            gameStop = true;
                            break;
                        default:
                            System.out.println("This is not a vaild choise");
                }
                
            }
        }   
    }
}