package CardHelpers;

import java.awt.*;

public class Card {
    private Suit suit;
    private Face face;
    private Color suit_color;

    public Card(Suit suit, Face face, Color suit_color) {
        this.suit = suit;
        this.face = face;
        this.suit_color = suit_color;
    }

    public Suit getSuit() {return suit;}
    public Face getFace() {return face;}
    public int getCardValue() {return face.getValue();}
    public Color getSuitColor() {return suit_color;}
    public String toString() {
        return String.format("[%s of %s]", face.getName(), suit.getName());
    }
}
