/**
 * MotionCard Class is subclass of Card.
 */
public class MotionCard extends Card {


    /**
     * Create a new Motion Card and set it's fields.
     * @param name name.
     * @param color color.
     */
    public MotionCard(String name, char color){
        super(20, color, name);
        int penalty = 0, changeTurn = 0;
        if(name.equals("skip"))
            changeTurn = 2;
        if(name.equals("reverse"))
            changeTurn = -1;
        if(name.equals("+2")){
            penalty = 2;
            changeTurn = 2;
        }
        setChangeTurn(changeTurn);
        setPenalty(penalty);

    }

}
