import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import models.*;

public class CommandLineInterface {
    private final Scanner scanner = new Scanner(System.in);

    public void startGame() {
        System.out.println("Bem-vindo ao Blackjack 21!");
        List<Result> results = playMultipleRounds();
        displayResults(results);
        scanner.close();
    }

    private List<Result> playMultipleRounds() {
        List<Result> results = new ArrayList<>();
        boolean continuePlaying = true;

        while (continuePlaying) {
            results.add(playRound());
            System.out.print("Deseja continuar jogando? (S/N): ");
            String input = scanner.next();
            continuePlaying = input.equalsIgnoreCase("S");
        }

        return results;
    }

    private Result playRound() {
        // Lógica do jogo
        Deck deck = new Deck();
        deck.shuffle();
        Player player = new Player("Jogador");
        Dealer dealer = new Dealer(null);

        // Distribuir cartas iniciais
        player.addCard(deck.drawCard());
        dealer.addCard(deck.drawCard());
        player.addCard(deck.drawCard());
        dealer.addCard(deck.drawCard());

        while (true) {
            // Exibir as cartas do jogador e a carta do dealer
            System.out.println(player.getName() + " tem as seguintes cartas: " + player.showHand());
            System.out.println("Carta do dealer: " + dealer.getHand().get(0));

            // Perguntar ao jogador se deseja "hit" ou "stand"
            System.out.print("Escolha (hit/stand): ");
            String choice = scanner.next();

            if ("hit".equalsIgnoreCase(choice)) {
                // O jogador escolhe "hit", então ele recebe uma carta
                Card card = deck.drawCard();
                player.addCard(card);

                // Verificar se o jogador estourou (bust)
                if (player.isBusted()) {
                    System.out.println("Você estourou! Perdeu.");
                    return new Result(ResultType.DERROTA, "Dealer", dealer.getScore());
                }
            } else if ("stand".equalsIgnoreCase(choice)) {
                // O jogador escolhe "stand", encerra a sua vez
                break;
            }
        }

        // Implementar a lógica do dealer (se deve "hit" ou "stand")

        return determineResult(player, dealer);
    }

    private Result determineResult(Player player, Dealer dealer) {
        // Lógica para determinar o resultado da partida
        int playerScore = player.getScore();
        int dealerScore = dealer.getScore();

        if (player.isBusted() || (dealerScore <= 21 && dealerScore > playerScore)) {
            return new Result(ResultType.DERROTA, "Dealer", dealerScore);
        } else if (dealer.isBusted() || playerScore > dealerScore) {
            return new Result(ResultType.VITORIA, "Jogador", playerScore);
        } else {
            return new Result(ResultType.EMPATE, "Nenhum", playerScore);
        }
    }

    private void displayResults(List<Result> results) {
        System.out.println("\nResultados finais de todas as partidas:");
        for (Result result : results) {
            System.out.println(result);
        }
    }
}
