/**
 * NumericCard Class is subclass of Card.
 */
public class NumericalCard extends Card {


    /**
     * Create a new Numeric card and set it's fields.
     * @param score score.
     * @param color color.
     */
    public NumericalCard(int score, char color){
        super(score, color, "" + score);
        setChangeTurn(1);
        setPenalty(0);
    }

}
