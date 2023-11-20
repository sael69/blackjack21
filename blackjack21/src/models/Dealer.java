package models;

public class Dealer extends Player {
    public Dealer(String name) {
        super(name);
    }

    public boolean shouldHit() {
        // Lógica do dealer para determinar se deve "hit" (pedir mais cartas) ou "stand" (parar).
        int dealerScore = getScore();
        
        // Exemplo simples: o dealer sempre pede mais uma carta até alcançar uma pontuação de pelo menos 17.
        return dealerScore < 17;
    }

    public Card getVisibleCard() {
        if (!getHand().isEmpty()) {
            return getHand().get(0);
        } else {
            return null;
        }
    }
}
