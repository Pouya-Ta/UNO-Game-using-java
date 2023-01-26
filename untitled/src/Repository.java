import java.util.LinkedList;
import java.util.Random;

public class Repository {
    //Cards of repository.
    private LinkedList<Card> cards;


    /**
     * Create repository and initialized it.
     */
    public Repository(){
        cards = new LinkedList<Card>();
        first();
    }


    /**
     * Initialized repository.
     */
    private void first(){
        for(int i = 0; i < 10; i++){
            int counter;
            if(i == 0)
                counter = 1;
            else
                counter = 2;
            for(int j = 0; j < counter; j++){
                cards.add(new NumericalCard(i, 'r'));
                cards.add(new NumericalCard(i, 'g'));
                cards.add(new NumericalCard(i, 'y'));
                cards.add(new NumericalCard(i, 'b'));
            }
        }
        for(int i = 0; i < 4; i++){
            char color;
            switch(i){
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
            for(int j = 0; j < 2; j++){
                cards.add(new MotionCard("+2", color));
                cards.add(new MotionCard("reverse", color));
                cards.add(new MotionCard("skip", color));
            }
        }
        for(int i = 0; i < 4; i++){
            cards.add(new WildeCard("+4"));
            cards.add(new WildeCard("wildColor"));
        }
    }

    /**
     * Add new card to repository.
     * @param newCard card for add.
     */
    public void addCard(Card newCard){
        cards.add(newCard);
    }

    /**
     * Remove a card from repository.
     * @param index index of card to remove.
     */
    private void removeCard(int index){
        cards.remove(index);
    }

    /**
     * This method return a card from repository.
     * Remove that card from list of cards.
     * @return card.
     */
    public Card randomCard(){
        Random random = new Random();
        int index = random.nextInt(cards.size());
        Card card = cards.get(index);
        removeCard(index);
        return card;
    }
}
