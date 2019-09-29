package blackjack.agents;

import blackjack.Util;
import blackjack.models.Card;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

public abstract class Agent {
  @Getter private final List<Card> hand;
  private final boolean isPlaying;

  public Agent() {
    this.hand = new ArrayList<Card>();
    this.isPlaying = true;
  }

  public void addCard(final Card c) {
    hand.add(c);
  }

  public List<Integer> getScores() {
    return Util.getScore(hand);
  }

  public boolean getIsPlaying() {
    if (isPlaying && getScores().size() != 0) {
      return true;
    }
    return false;
  }
}
