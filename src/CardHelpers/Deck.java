package CardHelpers;

import java.util.*;

import static CardHelpers.Face.*;
import static CardHelpers.Suit.*;

public class Deck {
    private static HashMap<Integer, Suit> SUITS = new HashMap<>();
    private static HashMap<Integer, Face> FACES = new HashMap<>();
    private static final int NUM_SUITS          = 4;
    private static final int CARDS_PER_SUIT     = 13;
    static {
        SUITS.put(0, CLUBS);
        SUITS.put(1, SPADES);
        SUITS.put(2, HEARTS);
        SUITS.put(3, DIAMONDS);

        FACES.put(0, ACE);
        FACES.put(1, TWO);
        FACES.put(2, THREE);
        FACES.put(3, FOUR);
        FACES.put(4, FIVE);
        FACES.put(5, SIX);
        FACES.put(6, SEVEN);
        FACES.put(7, EIGHT);
        FACES.put(8, NINE);
        FACES.put(9, TEN);
        FACES.put(10, JACK);
        FACES.put(11, QUEEN);
        FACES.put(12, KING);
    }

    //Standard deck = 52 cards = 13 (ace - king) * 4 (each type of suit)
    public Stack<Card> cards;

    public Deck() {
        cards = new Stack<>();
        for (int i = 0; i < NUM_SUITS; i++) {
            Suit current_suit = SUITS.get(i);
            for (int j = 0; j < CARDS_PER_SUIT; j++) {
                Face face = FACES.get(j);
                Card card = new Card(current_suit, face, null);
                cards.push(card);
            }
        }
    }

    public Stack<Card> getCards() {return cards;}
    public void shuffle() {Collections.shuffle(cards);}
    public void showDeck() {cards.forEach(System.out::println);}
}
