import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Game Class simulate UNO game.
 * for play must call play() method.
 * Other methods are private.
 */
public class Game {
    //List of players
    private ArrayList<Player> players;
    //Repository for stare cards.
    private Repository repository;
    //io for Display and get information from players.
    private IO io;
    //Last card that played.
    private Card lastCard;
    //Color of game.
    private char colorOfGame;
    //Turn.
    private int turn;
    //Direction of game.
    private int turnDirection;
    //Number of human players
    private int numberOfHuman;


    /**
     * Create a UNO game and initialized it to first state.
     */
    public Game(){
        repository = new Repository();
        lastCard = repository.randomCard();
        colorOfGame = lastCard.getColor();
        io = new IO();
        players = new ArrayList<Player>();
        turnDirection = 1;
    }


    /**
     * Set Players and initialized cards of them.
     * @param start start[0] is for number of players
     *              start[1] is for number of human players.
     */
    private void setPlayers(int[] start){
        int numberOfPlayers = start[0];
        numberOfHuman = start[1];
        for(int i = 0; i < numberOfPlayers; i++){
            LinkedList<Card> cards = new LinkedList<Card>();
            for(int j = 0; j < 7; j++)
                cards.add(repository.randomCard());
            if(i < numberOfHuman)
                players.add(new HPlayer(cards));
            else
                players.add(new Cplayer(cards));
        }
    }

    /**
     * Play game.
     */
    public void play() throws InterruptedException {
        setPlayers(io.start());
        turn = new Random().nextInt(players.size());
        cardFunction(lastCard);
        while(!isEnd()){
            //new Scanner(System.in).next();
            display();
            Card choice = players.get(turn).choice(lastCard.getName(), colorOfGame);
            if(choice == null){
                players.get(turn).addCard(repository.randomCard());
                display();
                io.printlnString("A card added to cards of player" + (turn + 1) + " from repository.");

                choice = players.get(turn).choice(lastCard.getName(), colorOfGame);
                if(choice == null){
                    io.printlnString("player losses the turn ...");
                    TimeUnit.MILLISECONDS.sleep(500);
                    incrementTurn();
                    continue;
                }
            }
            updateLastCard(choice);
            cardFunction(lastCard);
        }
        finish();
    }

    /**
     * This method determine scores of players.
     * Also specific winner.
     */
    private void finish(){
        ArrayList<Integer> scores = new ArrayList<Integer>();
        ArrayList<String>  stringOfPlayers = new ArrayList<String>();
        for(int i = 0; i < players.size(); i++){
            scores.add(players.get(i).getScore());
            stringOfPlayers.add("Player" + (i + 1));
        }
        for(int i = 0; i < scores.size() - 1; i++)
            for(int j = i; j < scores.size(); j++)
                if(scores.get(i) > scores.get(j)){
                    int temp = scores.get(i);
                    scores.set(i, scores.get(j));
                    scores.set(j, temp);
                    String string = stringOfPlayers.get(i);
                    stringOfPlayers.set(i, stringOfPlayers.get(j));
                    stringOfPlayers.set(j, string);
                }
        display();
        for(int i = 0; i < scores.size(); i++)
            System.out.println(i + 1 + "- " + stringOfPlayers.get(i) + "  Score: " + scores.get(i));
        new Scanner(System.in).next();
    }

    /**
     * Increment value related to direction of game.
     * if value reached to limit set value = 0 .
     * @param value value.
     * @param limit limit.
     * @return updated value.
     */
    private int increment(int value, int limit){
        int change;
        if(turnDirection == 1)
            change = 1;
        else
            change = -1;
        value += change;
        if(value >= limit)
            value = 0;
        if(value < 0)
            value = limit - 1;
        return value;
    }

    /**
     * Increment turn.
     */
    private void incrementTurn(){
        turn = increment(turn, players.size());
    }

    /**
     * Change direction of game.
     */
    private void changeDirection(){
        turnDirection *= -1;
    }

    /**
     * Check that game ended or not.
     * @return true if it ended and else return false.
     */
    private boolean isEnd(){
        for(Player player: players)
            if(player.numberOfCards() == 0)
                return true;
        return false;
    }

    /**
     * Receive a card and update last card of game.
     * @param newCard new card
     */
    private void updateLastCard(Card newCard){
        repository.addCard(lastCard);
        lastCard = newCard;
    }

    /**
     * When draw2+ or wildDraw card played this method call
     * and determine that penalty is for which player
     * aslo determine amount of penalty.
     * @param penaltyCard penalty card
     */
    private void penaltyCard(Card penaltyCard) throws InterruptedException {
        int penalty = 0, temp = penaltyCard.getPenalty();
        boolean condition = true;
        boolean flag = false;
        while(condition){
            if(flag){
                display();
                TimeUnit.MILLISECONDS.sleep(4500);
            }
            flag = true;
            penalty += temp;
            Card card = players.get(turn).haveCard(penaltyCard.getName());
            if(card != null){
                players.get(turn).removeCard(card);
                updateLastCard(card);
                if(penaltyCard instanceof WildeCard)
                    setColorOfGame(players.get(turn).changeColor());
                else

                    setColorOfGame(lastCard.getColor());
                incrementTurn();
                //display();
                continue;
            }
            condition = false;
        }
        for(int i = 0; i < penalty; i++)
            players.get(turn).addCard(repository.randomCard());
        incrementTurn();
    }

    /**
     * When a card played this method calls and do its functionality.
     * @param card card.
     */
    private void cardFunction(Card card) throws InterruptedException {
        if (card.getColor() == 'w') {
            setColorOfGame(players.get(turn).changeColor());
            incrementTurn();
            if (card.getName().contains("+"))
                penaltyCard(card);
            return;
        }
        if (card.getName().contains("+")){
            incrementTurn();
            setColorOfGame(card.getColor());
            penaltyCard(card);
            return;
        }
        if(card.getChangeTurn() < 0){
            changeDirection();
            incrementTurn();
            setColorOfGame(card.getColor());
            return;
        }
        for(int i = 0; i < card.getChangeTurn(); i++)
            incrementTurn();
        setColorOfGame(card.getColor());
    }

    /**
     * Display current state of game.
     */
    private void display(){
        try {
            int processBuilder = new ProcessBuilder("cmd", "/c", "clear").inheritIO().start().waitFor();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
        io.printlnString("\n\n\n\n");
        io.printCard(lastCard);
        io.printString("Color : ");
        io.printColor(colorOfGame);
        if(turnDirection == 1)
            io.printlnString("Direction : clockwise  (left to right --> )");
        else
            io.printlnString("Direction : anticlockwise  (right to left <-- )");
        io.printString("Players :     ");
        for(int i = 0 ; i < players.size(); i++){
            if(i == turn)
                io.printString("*turn* ");
            io.printString("Player" + (i + 1) + "     ");
        }
        io.printlnString("\n");

        int i = turn;
        do {
            String type;
            if(players.get(i) instanceof HPlayer)
                type = "  (Human)";
            else
                type = "  (Computer)";
            io.printlnString("Cards Of Player" + (i + 1) + type);
            if(numberOfHuman == 1)
                players.get(i).displayCards();
            else
                displayCards(i);
            i = increment(i, players.size());
        }while (i != turn);

    }

    /**
     * Change color of game.
     * @param color new color.
     */
    private void setColorOfGame(char color){
        colorOfGame = color;
    }

    /**
     * Displays cards of a player.
     * @param indexOfPlayer index of player.
     */
    private void displayCards(int indexOfPlayer){
        if(players.get(indexOfPlayer) instanceof HPlayer && turn == indexOfPlayer)
            players.get(indexOfPlayer).displayFrontOfCards();
        else
            players.get(indexOfPlayer).displayBackOfCards();
    }

}
