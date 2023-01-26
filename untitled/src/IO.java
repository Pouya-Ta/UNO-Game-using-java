import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * IO Class is for print and scan information.
 */
public class IO {
    //scn for scan.
    private Scanner scn;

    /**
     * New IO.
     */
    public IO(){
        scn = new Scanner(System.in);
    }


    /**
     * Print first information and also get number of players.
     * start[0] is for number of players.
     * start[1] is for number of human players.
     * @return start.
     */
    public int[] start(){
        printlnString(" << Welcome To UNO >>");
        printString(" Please enter number of players :");
        int[] start = new int[2];
        int number;
        boolean flag = false;
        do{
            if(flag)
                System.out.println("Number of players not supported");
            flag = true;
            number = scn.nextInt();
        }while(number < 0);
        start[0] = number;
        printString(" Please enter number of human players :");
        flag = false;
        do{
            if(flag)
                System.out.println("Number of players not supported");
            flag = true;
            number = scn.nextInt();
        }while(number < 0 || number > start[0]);
        start[1] = number;
        return start;
    }

    /**
     * Print string using println.
     * @param string string for print.
     */
    public void printlnString(String string){
        System.out.println(string);
    }

    /**
     * Print string using print.
     * @param string string for print.
     */
    public void printString(String string){
        System.out.print(string);
    }

    /**
     * This method receive a card and display it.
     * @param card card to display.
     */
    public void printCard(Card card){
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(card);
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        numbers.add(1);
        displayCards(cards, numbers, true);
        System.out.println();
    }

    /**
     * This method receive list of cards and divide it to
     * list with length = 12 then called displayCards() for
     * every slice of list.
     * if showString is true display cards normally and
     * if showString is false display back of cards.
     * @param cards cards to display.
     * @param showString control value.
     */
    public void printCards(List<Card> cards, boolean showString){
        if(cards.size() <= 12){
            ArrayList<Integer> numbers = new ArrayList<Integer>();
            for(int i = 0; i < cards.size(); i++)
                numbers.add(i + 1);
            displayCards(cards, numbers, showString);
        }
        else {
            int i;
            for(i = 0; (i + 12) < cards.size(); i += 12){
                ArrayList<Card> temp = new ArrayList<Card>();
                ArrayList<Integer> numbers = new ArrayList<Integer>();
                for(int j = 0; j < 12; j++) {
                    numbers.add(j + i + 1);
                    temp.add(cards.get(j + i));
                }
                displayCards(temp, numbers, showString);
                System.out.println("");
            }
            ArrayList<Card> temp = new ArrayList<Card>();
            ArrayList<Integer> numbers = new ArrayList<Integer>();
            for( ; i < cards.size(); i++){
                numbers.add(i + 1);
                temp.add(cards.get(i));
            }
            displayCards(temp, numbers, showString);
        }
        printWithColor('w', "************************************************");
        printWithColor('w', "************************************************");
        printWithColor('w', "************************************************");
        printWithColor('w', "************************************************");
        System.out.println();
    }

    /**
     * Display cards with numbers.
     * if showString is true display cards normally and
     * if showString is false display back of cards.
     * @param cards cards to display.
     * @param numbers numbers to show with cards.
     * @param showString control value.
     */
    private void displayCards(List<Card> cards, ArrayList<Integer> numbers, boolean showString){
        String str0 = "|$$$$$$$$$$$|";
        String str1 = "|           |";
        for(int i = 0; i < 5; i++){
            /*if(i == 3)
                continue;*/
            for (int j = 0; j < cards.size(); j++) {
                char color;
                if(showString)
                    color = cards.get(j).getColor();
                else
                    color = ' ';
                if (i % 4 == 0) {
                    printWithColor(color, str0);
                    System.out.print("   ");
                    continue;
                }
                if (i % 2 == 1){
                    if(i == 1){
                        StringBuilder number = new StringBuilder("| number");
                        number.append(numbers.get(j));
                        number.append(":");
                        number.append(" ".repeat(Math.max(0, 11 - number.length())));
                        number.append(" |");
                        printWithColor(color, number.toString());
                    }
                    else
                        printWithColor(color, str1);
                }
                else {
                    String string;
                    if(showString)
                        string = cards.get(j).getName();
                    else
                        string = "?????";
                    StringBuilder before = new StringBuilder("|");
                    StringBuilder after = new StringBuilder("");
                    int lengthOfBefore = (11 - string.length()) / 2;
                    int lengthOfAfter = 11 - string.length() - lengthOfBefore;
                    before.append(" ".repeat(Math.max(0, lengthOfBefore)));
                    after.append(" ".repeat(Math.max(0, lengthOfAfter)));
                    after.append("|");
                    printWithColor(color, before.toString());
                    printWithColor(color, string);
                    printWithColor(color, after.toString());
                }
                System.out.print("   ");
            }
            System.out.println();
        }

    }

    /**
     * Set color of background.
     * @param color color for set.
     * @param string String for print
     */
    private void printWithColor(char color, String string){
        ArrayList<String> colors = new ArrayList<String>();
        colors.add("\033[1;91m");   // RED
        colors.add("\033[1;92m"); // GREEN
        colors.add("\033[1;93m");// YELLOW
        colors.add("\033[1;94m");  // BLUE
        colors.add("\033[1;95m");// PURPLE
        colors.add("\033[1;96m");  // CYAN
        /////////
        String BLACK_BOLD = "\033[90m";
        //////////
        //String BLACK_BOLD = "\033[1;97m";
        String ANSI_RESET = "\u001B[0m";
        String ANSI_BLACK_BACKGROUND = "\033[1;30m" ;//"\u001B[47m";
        String ANSI_RED_BACKGROUND = "\u001B[41m" + BLACK_BOLD;
        String ANSI_GREEN_BACKGROUND = "\033[0;102m" + BLACK_BOLD;
        String ANSI_YELLOW_BACKGROUND = "\033[0;103m" + BLACK_BOLD;
        String ANSI_BLUE_BACKGROUND = "\u001B[46m" + BLACK_BOLD;
        String ANSI_WHITE_BACKGROUND = "\u001B[40m" + BLACK_BOLD;

        switch (color){
            case 'r':
                printString(ANSI_RED_BACKGROUND + string + ANSI_RESET);
                break;
            case 'b':
                printString(ANSI_BLUE_BACKGROUND + string + ANSI_RESET);
                break;
            case 'g':
                printString(ANSI_GREEN_BACKGROUND + string + ANSI_RESET);
                break;
            case 'y':
                printString(ANSI_YELLOW_BACKGROUND + string + ANSI_RESET);
                break;
            case 'w':
                Random random = new Random();
                for(char ch: string.toCharArray())
                    printString(ANSI_BLACK_BACKGROUND + colors.get(random.nextInt(6)) + ch + ANSI_RESET);
                break;
            default:
                printString(/*ANSI_WHITE_BACKGROUND + */string + ANSI_RESET);
                break;
        }
    }

    /**
     * Receive a choice from.
     * @return choice.
     */
    public int enterChoice(){
        printString("Please enter number of card that you want to choice : ");
        return scn.nextInt();
    }

    /**
     * When a player haven"t any choice this method called
     * and print a massage.
     */
    public void haveNotChoice() throws InterruptedException {
        printlnString("This player haven't any choice");
        TimeUnit.MILLISECONDS.sleep(3000);
    }

    /**
     * When game needed that player select a new color
     * this method called and receive new color and return it.
     * @return new color
     */
    public char newColor(){
        printlnString("Please enter number of new color :");
        printWithColor('r', "1- Red   ");
        printlnString("");
        printWithColor('b', "2- Blue  ");
        printlnString("");
        printWithColor('g', "3- Green ");
        printlnString("");
        printWithColor('y', "4- Yellow");
        printlnString("");
        int number;
        boolean flag = false;
        do{
            if(flag)
                printlnString("Color not supported. Enter another color");
            flag = true;
            number = scn.nextInt();
        }while (number < 1 || number > 4);
        return setNewColor(number - 1);
    }

    /**
     * Receive a integer number and set return color related to it.
     * @param number number.
     * @return color.
     */
    public char setNewColor(int number){
        char color;
        switch (number){
            case 0:
                color = 'r';
                break;
            case 1:
                color = 'b';
                break;
            case 2:
                color = 'g';
                break;
            default:
                color = 'y';
                break;
        }
        return color;
    }

    /**
     * Print just a color.
     * @param color color.
     */
    public void printColor(char color){
        printWithColor(color, "   ");
        printlnString("\n");
    }

}
