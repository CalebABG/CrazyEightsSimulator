package CardHelpers;

public enum Suit {
    CLUBS("CLUBS"),         // 0
    SPADES("SPADES"),       // 1
    HEARTS("HEARTS"),       // 2
    DIAMONDS("DIAMONDS");   // 3

    private String name;
    Suit(final String n){name = n;}
    public String getName() {return name;}
}
