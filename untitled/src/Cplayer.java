import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * CPlayer is subclass of Player that is for computer players.
 */
public class Cplayer extends Player {


    /**
     * Create CPlayer.
     * @param cards cards of player.
     */
    public Cplayer(LinkedList<Card> cards){
        super(cards);
    }


    /**
     * This method determine legal cards and select a card randomly.
     * for determine legal cards receive name and color of last card.
     * @param nameOfCard name of cards for check.
     * @param color color of card for check.
     * @return selected card.
     */
    @Override
    public Card choice(String nameOfCard, char color) throws InterruptedException {
        ArrayList<Card> choices = validChoice(nameOfCard, color);
        if(choices.size() == 0) {

            getIo().haveNotChoice();
            return null;
        }
        TimeUnit.MILLISECONDS.sleep(4000);
        Card card = choices.get(new Random().nextInt(choices.size()));
        removeCard(card);
        return card;
    }

    /**
     * Display cards.
     */
    @Override
    public void displayCards() {
        getIo().printCards(getCards(), true);
    }

    /**
     * Change color.
     * @return new color
     */
    @Override
    public char changeColor() {
        return getIo().setNewColor(new Random().nextInt(4));
    }

}
