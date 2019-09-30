package blackjack.agents;

import java.util.Scanner;

public class PlayerAgent extends Agent {
  public boolean getNextCard(final Scanner scan) {
    return isPlaying() && scan.nextInt() == 1;
  }
}
