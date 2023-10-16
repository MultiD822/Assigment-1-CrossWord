//Programmer: Kai Schenkel
//Class CS 145
//Data 10/9/2023
//Lab 3: Letter Inventory 
//Soures: A lot of these idea was from the class and help from the Teacher
import java.util.*;
public class App {
    //This is the datafield for this class 
    private static int width;
    private static int length;
    private static int wordCount;
    private static ArrayList<String> words;
    private static int[] positions;
    private static char [][] playingFiled;
    private static String useInput;

    public static void main(String[] args) throws Exception {
        runGame();
        printPlayingFiled();
    }

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
    }

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
        //This would idley print the words on the grid horizontal
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

    public static int randomRange(int low, int high){
        Random generator = new Random();
        return generator.nextInt(high-low+1) + low;
    }

    public static boolean search(int [ ] numbers, int key){
        for(int index = 0; index < numbers.length; index++){
            if(numbers[index] == key){
                return true;
            }
        }
        return false;
    }

    public static void computation(){
        System.out.println("Creating the Word Search");
        
        for(int i = 0; i < words.size(); i++){
            if(words.get(i).length() > width){
                width = words.get(i).length();
            }
        }
        width = width * 2;
        length = width + (width/3);
         playingFiled = new char [width][length];
         
    } 

    public static void runGame(){
        System.out.println("Welcome to the word search generator");
        System.out.println("This simple java program will create a word search based on words you choose");
        
        dataTaker();
        computation();
        fillGird();
    }
        

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
}