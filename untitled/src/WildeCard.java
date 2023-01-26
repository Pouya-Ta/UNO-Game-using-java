/**
 * WildCard is subclass of Card.
 */
public class WildeCard extends Card {


    /**
     * Create a create new wild card and set ots field.
     * @param name name of card.
     */
    public WildeCard(String name){
        super(50, 'w', name);
        int penalty;
        if(name.equals("wildColor"))
            penalty = 0;
        else
            penalty = 4;
        setPenalty(penalty);
        setChangeTurn(1);
    }

}
