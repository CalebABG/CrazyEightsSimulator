package CardHelpers;

import java.util.ArrayList;
import java.util.List;

public class Player {
    public List<Card> hand;
    private int cards_from_stock;
    private int cards_played;
    private String name;

    public Player(String name) {
        hand = new ArrayList<>();
        cards_from_stock = 0;
        cards_played = 0;
        this.name = name;
    }

    public void add(Card c) {
        hand.add(c);
    }

    public void playedCard() {
        cards_played++;
    }

    public void playedCard(Card c) {
        hand.remove(c);
        cards_played++;
    }

    public void pickedFromStock() {
        cards_from_stock++;
    }

    public int getCardsPickedFromStock() {
        return cards_from_stock;
    }

    public int getCardsPlayed() {
        return cards_played;
    }

    public int getRemainingCardsInHand() {
        return hand.size();
    }

    public boolean hasNoMoreCards() {
        return hand.size() == 0;
    }

    public boolean hasCrazyEights() {
        // Get any EIGHT cards in the hand, if there are two EIGHT cards, then you had crazy eights
        long count = hand.stream().filter(card -> card.getFace().getName().equals(Face.EIGHT.getName())).count();
        return count == 2;
    }

    public String getName() {
        return name;
    }

    public void showHand() {
        for (int i = 0; i < hand.size(); i++) {
            System.out.println(hand.get(i));
        }
    }
}
