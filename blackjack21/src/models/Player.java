package models;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private List<Card> hand;

    public Player(String name) {
        this.name = name;
        hand = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public List<Card> getHand() {
        return hand;
    }

    public String showHand() {
        StringBuilder handString = new StringBuilder();
        for (Card card : hand) {
            handString.append(card.toString()).append(" "); // Supondo que a classe Card tenha um método toString() definido
        }
        return handString.toString().trim();
    }

    public int getScore() {
        int score = 0;
        int aces = 0;

        for (Card card : hand) {
            score += card.getValue();
            if (card.getValue() == 11) {
                aces++;
            }
        }

        while (score > 21 && aces > 0) {
            score -= 10;  // Tratar o Ás como 1 ao invés de 11.
            aces--;
        }

        return score;
    }

    public boolean isBusted() {
        return getScore() > 21;
    }
}
