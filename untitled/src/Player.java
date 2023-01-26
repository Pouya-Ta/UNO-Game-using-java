import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public abstract class Player {
    //List of cards of player.
    private LinkedList<Card> cards;
    //io is for display and get information from player.
    private IO io;


    /**
     * Create Player.
     * @param cards cards of player.
     */
    public Player(LinkedList<Card> cards){
        this.cards = cards;
        io = new IO();
    }


    /**
     * @return io.
     */
    public IO getIo(){
        return io;
    }

    /**
     * Calculate score of player and return it.
     * @return score.
     */
    public int getScore(){
        int score = 0;
        for(Card card: cards)
            score += card.getScore();
        return score;
    }

    /**
     * @return number cards of player.
     */
    public int numberOfCards(){
        return cards.size();
    }

    /**
     * @return list of cards of player.
     */
    public List<Card> getCards(){
        return cards;
    }

    /**
     * Add a new card to list of cards.
     * @param newCard card for add.
     */
    public void addCard(Card newCard){
        cards.add(newCard);
    }

    /**
     * Remove a card from cards of player.
     * @param card card for remove.
     */
    public void removeCard(Card card){
        cards.remove(card);
    }

    /**
     * With this method player can select a card.
     * @param nameOfCard name of last card that played.
     * @param color color of last card that played.
     * @return selected card.
     */
    public abstract Card choice(String nameOfCard, char color) throws InterruptedException;

    /**
     * Display cards.
     */
    public abstract void displayCards();

    /**
     * Determine valid choices for player.
     * For check receive name and color of a card.
     * @param nameOfCard name for check.
     * @param color color for check.
     * @return list of legal cards.
     */
    public ArrayList<Card> validChoice(String nameOfCard, char color){
        ArrayList<Card> choices = new ArrayList<Card>();
        for(Card card: cards){
            if(card.getName().equals("wildColor"))
                choices.add(card);
            if(card.getColor() == color || card.getName().equals(nameOfCard))
                choices.add(card);
        }
        if(choices.size() == 0)
            for(Card card: cards)
                if(card instanceof WildeCard)
                    choices.add(card);
        return choices;
    }

    /**
     * This method receive name of a card and check that player
     * have or not a card with this name.
     * @param nameOfCard name of card for check.
     * @return similar card if it found.
     */
    public Card haveCard(String nameOfCard){
        for(Card card: cards)
            if(card.getName().equals(nameOfCard))
                return card;
        return null;
    }

    /**
     * Change color of game to new color.
     * @return new color.
     */
    public abstract char changeColor();

    /**
     * With this method we can see cards but name and color of cards
     * are hidden . So just can number of cards.
     */
    public void displayBackOfCards(){
        io.printCards(cards, false);
    }

    /**
     * Display cards with name and color of them.
     */
    public void displayFrontOfCards(){
        io.printCards(cards, true);
    }

}
