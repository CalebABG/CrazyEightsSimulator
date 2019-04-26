package CardHelpers;

import java.util.Stack;

public class CrazyEights {

    public static void play(Deck deck) {
        //Number of cards dealt to each player
        final int DEALT_CARDS = 8;

        //Keep track of players turns and if the game is still ongoing
        boolean isPlayer1Turn = false;
        boolean isPlaying = true;

        //Keep track of the cards which have been put down
        Stack<Card> cards_in_play = new Stack<>();

        //simulated two players
        Player player1 = new Player("Bob"), player2 = new Player("Mark");

        //Shuffle the cards to randomize suits and faces
        System.out.println("Shuffling the deck!!");
        deck.shuffle();

        //Show the deck
        System.out.println("The deck of cards");
        deck.showDeck();

        System.out.println();

        //Give 8 cards to player 1
        for (int i = 0; i < DEALT_CARDS; i++)
            player1.add(deck.cards.pop());

        //Show player 1's cards
        System.out.println(player1.getName() + "'s cards");
        player1.showHand();

        System.out.println();

        //Give 8 cards to player 2
        for (int i = 0; i < DEALT_CARDS; i++)
            player2.add(deck.cards.pop());

        //Show player 2's cards
        System.out.println(player2.getName() + "'s cards");
        player2.showHand();

        System.out.println();

        //Add starting card from deck to cards which are in play
        cards_in_play.push(deck.cards.pop());

        //Show the starting card
        System.out.println("The starting card is: " + cards_in_play.peek());
        System.out.println();

        //Show how many cards are left in the deck
        System.out.println("Number of cards left in the deck: " + deck.cards.size());
        System.out.println();

        //Determine who plays first
        if (Math.random() > .5f) {
            isPlayer1Turn = true;
            System.out.println(player1.getName() + " plays first");
        }
        else {
            System.out.println(player2.getName() + " plays first");
        }

        System.out.println();

        //Measure game time :D
        long startTime = System.nanoTime();

        //While either players hand is not empty
        while (isPlaying) {
            if (player1.hasNoMoreCards()) {
                isPlaying = false;
                winRecap(player1, player2, deck, cards_in_play, startTime);
            }
            else if (player2.hasNoMoreCards()) {
                isPlaying = false;
                winRecap(player2, player1, deck, cards_in_play, startTime);
            }
            else {
                if (isPlayer1Turn) {
                    takeTurn(player1, cards_in_play, deck);
                    isPlayer1Turn = false;
                }
                else {
                    takeTurn(player2, cards_in_play, deck);
                    isPlayer1Turn = true;
                }
            }
        }
    }

    private static void takeTurn(Player player, Stack<Card> cards_inplay, Deck deck) {
        boolean foundPlayableCard = false;
        for (int i = 0; i < player.hand.size(); i++) {
            Card c = player.hand.get(i);
            if (c.getFace().getName().equals(cards_inplay.peek().getFace().getName()) || c.getSuit().getName().equals(cards_inplay.peek().getSuit().getName())) {
                foundPlayableCard = true;
                cards_inplay.push(c);
                player.playedCard(c);
                System.out.println(player.getName() + " played " + c + " - " + player.getName() + "'s cards left: " + player.getRemainingCardsInHand());
                break;
            }
        }
        if (!foundPlayableCard) {
            if (deck.cards.size() < 1) maintainStockPile(cards_inplay, deck);
            Card stockpile_card = deck.cards.pop();
            System.out.println();
            System.out.println("Stockpile cards left: " + deck.cards.size() + " - Cards in-play pile: " + cards_inplay.size());
            if (stockpile_card.getFace().getName().equals(cards_inplay.peek().getFace().getName()) ||
                    stockpile_card.getSuit().getName().equals(cards_inplay.peek().getSuit().getName())) {
                cards_inplay.push(stockpile_card);
                System.out.println(player.getName() + " got lucky from the stockpile and played " + stockpile_card);
                player.playedCard();
            }
            else {
                System.out.println(player.getName() + " could not play, had to keep stockpile card");
                player.add(stockpile_card);
                player.pickedFromStock();
            }
        }
    }

    private static void maintainStockPile(Stack<Card> cards_inplay, Deck deck) {
        System.out.println("\n" + "needed to re-stock card stockpile".toUpperCase());
        Card lastInPlayCard = cards_inplay.pop();
        deck.cards.addAll(cards_inplay);
        deck.shuffle();
        cards_inplay.clear();
        cards_inplay.push(lastInPlayCard);
    }

    private static void winRecap(Player p1, Player p2, Deck deck, Stack<Card> cards_inplay, long startTime) {
        long stopTime = System.nanoTime();
        System.out.println();
        System.out.println("-------------------------------GAME RECAP--------------------------------");
        System.out.println(p1.getName() + " wins :D - Won in " + ((float) (stopTime - startTime) / 1.0E9D) + " seconds.");
        System.out.println();
        System.out.println("PLAYER STATS");
        System.out.println(p1.getName() + " played " + p1.getCardsPlayed() + " card(s). Played " + p1.getCardsPickedFromStock() + " card(s) picked from the stockpile");
        System.out.println(p1.getName() + " has " + p1.getRemainingCardsInHand() + " card(s) left in their hand");
        System.out.println();
        System.out.println(p2.getName() + " played " + p2.getCardsPlayed() + " card(s). Played " + p2.getCardsPickedFromStock() + " card(s) picked from the stockpile");
        System.out.println(p2.getName() + " has " + p2.getRemainingCardsInHand() + " card(s) left in their hand");
        System.out.println();
        System.out.println("There are " + deck.cards.size() + " card(s) left in the stockpile");
        System.out.println("There are " + cards_inplay.size() + " card(s) in the cards in-play pile");
        System.out.println();
        System.out.println("Check Deck Maintains Capacity");
        System.out.println("(cards in-play + stockpile + (" + p1.getName() + "'s cards + " + p2.getName() + "'s cards) should = 52 cards total");
        System.out.println("Check Outcome: " + ((cards_inplay.size() + deck.cards.size() + p1.getRemainingCardsInHand() + p2.getRemainingCardsInHand()) == 52));
        System.out.println("-------------------------------------------------------------------------");
    }
}