import java.util.Objects;

public class Card {
    //Score of card
    private int score;
    //Color of card.
    private char color;
    //Name of card.
    private String name;
    //Amount of penalty of card.
    private int penalty;
    //Amount of turn increment of card.
    private int changeTurn;


    /**
     * Create a card.
     * @param score score of card.
     * @param color color of card.
     * @param name name of card.
     */
    public Card(int score, char color, String name){
        this.score = score;
        this.color = color;
        this.name = name;
    }


    /**
     * @param penalty penalty.
     */
    public void setPenalty(int penalty){
        this.penalty = penalty;
    }

    /**
     * @return changeTurn.
     */
    public int getChangeTurn(){
        return changeTurn;
    }

    /**
     * @return score.
     */
    public int getScore(){
        return score;
    }

    /**
     * @return penalty.
     */
    public int getPenalty(){
        return penalty;
    }

    /**
     * @param changeTurn changeTurn.
     */
    public void setChangeTurn(int changeTurn){
        this.changeTurn = changeTurn;
    }

    /**
     * @return color.
     */
    public char getColor(){
        return color;
    }

    /**
     * @return name.
     */
    public String getName(){
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;
        Card card = (Card) o;
        return getColor() == card.getColor() &&
                getName().equals(card.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getColor(), getName());
    }
}

