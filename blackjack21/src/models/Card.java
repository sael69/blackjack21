package models;

public class Card {
    private String suit;
    private String rank;
    private int value;

    public Card(String suit, String rank) {
        this.suit = suit;
        this.rank = rank;

        // Atribui valor à carta (considerando Ás como 11).
        if (rank.equals("Ás")) {
            value = 11;
        } else if (rank.equals("Valete") || rank.equals("Dama") || rank.equals("Rei")) {
            value = 10;
        } else {
            value = Integer.parseInt(rank);
        }
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return rank + " de " + suit;
    }
}
