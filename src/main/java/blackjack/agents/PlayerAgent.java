package blackjack.agents;

import java.util.Scanner;

public class PlayerAgent extends Agent {
  public boolean getNextCard(final Scanner scan) {
    return getIsPlaying() && scan.nextInt() == 1;
  }
}
