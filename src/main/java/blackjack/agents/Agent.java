package blackjack.agents;

import blackjack.Util;
import blackjack.models.Card;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.Getter;

public abstract class Agent implements Comparable<Agent> {
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

  @Override
  public int compareTo(final Agent other) {
    final List<Integer> thisScore = this.getScores();
    final List<Integer> thatScore = other.getScores();

    if (thisScore.size() == 0 && thatScore.size() == 0) {
      return 0;
    } else if (thisScore.size() == 0) {
      return -1;
    } else if (thatScore.size() == 0) {
      return 1;
    } else {
      return Collections.max(thisScore).compareTo(Collections.max(thatScore));
    }
  }
}
