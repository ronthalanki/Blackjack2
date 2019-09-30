package blackjack.agents;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BotAgent extends Agent {
  private static final List<Integer> stopScores = Arrays.asList(16, 17, 18, 19, 20, 21);

  public boolean getNextCard() {
    return isPlaying() && Collections.disjoint(stopScores, getScores());
  }
}
