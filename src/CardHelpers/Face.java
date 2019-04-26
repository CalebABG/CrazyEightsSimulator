package CardHelpers;

public enum Face {
    ACE(0, "ACE"),          // 0
    TWO(2, "TWO"),          // 1
    THREE(3, "THREE"),      // 2
    FOUR(4, "FOUR"),        // 3
    FIVE(5, "FIVE"),        // 4
    SIX(6, "SIX"),          // 5
    SEVEN(7, "SEVEN"),      // 6
    EIGHT(8, "EIGHT"),      // 7
    NINE(9, "NINE"),        // 8
    TEN(10, "TEN"),         // 9
    JACK(11, "JACK"),       // 10
    QUEEN(12, "QUEEN"),     // 11
    KING(13, "KING");       // 12

    private int value;
    private String name;
    Face(final int v, final String n){value = v; name = n;}
    public int getValue() {return value;}
    public String getName() {return name;}
}
