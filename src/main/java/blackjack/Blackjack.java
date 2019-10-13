package blackjack;

import blackjack.agents.Agent;
import blackjack.agents.BotAgent;
import blackjack.agents.PlayerAgent;
import blackjack.models.Deck;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Builder
public class Blackjack {
  @Getter @NonNull @Builder.Default private final Deck deck = new Deck();
  @Getter @NonNull @Builder.Default private final BotAgent dealer = new BotAgent();
  @Getter @NonNull @Builder.Default private final PlayerAgent player = new PlayerAgent();
  private static final String template = "%s State: %s, Scores: %s\n";

  @NonNull @Builder.Default
  private final List<BotAgent> bots = Arrays.asList(new BotAgent(), new BotAgent());

  public List<Agent> getAllAgents() {
    final ArrayList<Agent> agents = new ArrayList<Agent>();
    agents.add(player);
    agents.addAll(bots);
    agents.add(dealer);

    return agents;
  }

  public List<BotAgent> getAllBots() {
    final ArrayList<BotAgent> allBots = new ArrayList<BotAgent>();
    allBots.addAll(bots);
    allBots.add(dealer);

    return allBots;
  }

  public String getState() {
    String gameState = "";

    gameState += String.format(template, "Dealer", dealer.getHand(), dealer.getScores());
    gameState += String.format(template, "Player", player.getHand(), player.getScores());

    for (final BotAgent botAgent : bots) {
      gameState += String.format(template, "Bot", botAgent.getHand(), botAgent.getScores());
    }

    return gameState;
  }

  public static String getAgentState(final Agent agent) {
    return String.format(template, agent, agent.getHand(), agent.getScores());
  }

  public static void clearScreen(final Scanner scan) {
    System.out.println("To continue, press any letter");
    scan.next();
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  public static void main(String[] args) {
    final Blackjack game = Blackjack.builder().deck().build();
    final Scanner scan = new Scanner(System.in);

    System.out.println("Welcome to Blackjack!");
    clearScreen(scan);

    for (final Agent agent : game.getAllAgents()) {
      for (int i = 0; i < 2; i++) {
        agent.addCard(game.getDeck().drawCard());
      }
    }

    System.out.println("Here are everyone's cards");
    System.out.println(game.getState());
    clearScreen(scan);

    System.out.println(game.getAgentState(game.getPlayer()));
    System.out.println("If you would like another card, press 1. Otherwise, press 0");
    while (game.getPlayer().getNextCard(scan)) {
      game.getPlayer().addCard(game.getDeck().drawCard());
      System.out.println(game.getAgentState(game.getPlayer()));
    }
    clearScreen(scan);

    for (final BotAgent bot : game.getAllBots()) {
      while (bot.getNextCard()) {
        bot.addCard(game.getDeck().drawCard());
      }
    }

    System.out.println(game.getState());

    if (game.getDealer().compareTo(game.getPlayer()) == 1) {
      System.out.println("You lost.");
    } else {
      System.out.println("You Win!");
    }
  }
}
