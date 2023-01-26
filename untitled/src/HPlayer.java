import java.util.ArrayList;
import java.util.LinkedList;

/**
 * HPLayer is sub Class of Player that is for human player.
 */
public class HPlayer extends Player {


    /**
     * Create a HPlayer.
     * @param cards cards of player
     */
    public HPlayer(LinkedList<Card> cards){
        super(cards);
    }

    /**
     * In this method player select a card from cards.
     * For check selected card check it with name and color that receive them.
     * If card is valid card return it.
     * @param nameOfCard name of card for check.
     * @param color color of card for check.
     * @return card as choice.
     */
    @Override
    public Card choice(String nameOfCard, char color) throws InterruptedException {
        ArrayList<Card> choices = validChoice(nameOfCard, color);
        getIo().printlnString(" number of valid choices : " + choices.size());
        if(choices.size() == 0){
            getIo().haveNotChoice();
            return null;
        }
        int choice;
        boolean flag = false;
        do{
            if(flag)
                getIo().printlnString("Number of Card not supported. Select another card");
            flag = true;
            choice = getIo().enterChoice();
        }while(choice < 1 || choice > numberOfCards() || !choices.contains(getCards().get(choice - 1)));
        Card card = getCards().get(choice - 1);
        removeCard(card);
        return card;
    }

    /**
     * Display cards of player.
     */
    @Override
    public void displayCards() {
        getIo().printCards(getCards(), true);
    }

    /**
     * Change color of game.
     * @return new color.
     */
    @Override
    public char changeColor() {
        return getIo().newColor();
    }


}
