package blackjack.agents;

import blackjack.models.Card;
import blackjack.models.Rank;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;

public abstract class Agent implements Comparable<Agent> {
  @Getter private final List<Card> hand;
  @Getter private List<Integer> scores;

  public Agent() {
    this.hand = new ArrayList<Card>();
    this.scores = new ArrayList<Integer>();

    this.scores.add(0);
  }

  public void addCard(final Card c) {
    hand.add(c);

    if (c.getRank() == Rank.ACE) {
      final List<Integer> scoresCopy = new ArrayList<Integer>(scores);
      for (int i = 0; i < scoresCopy.size(); i++) {
        scores.add(scoresCopy.get(i));
      }

      for (int i = 0; i < scores.size() / 2; i++) {
        scores.set(i, scores.get(i) + 1);
      }

      for (int i = scores.size() / 2; i < scores.size(); i++) {
        scores.set(i, scores.get(i) + 11);
      }
    } else {
      for (int i = 0; i < scores.size(); i++) {
        scores.set(i, scores.get(i) + c.getRank().value);
      }
    }

    scores =
        scores.stream().mapToInt(i -> i).filter(i -> i <= 21).boxed().collect(Collectors.toList());
  }

  public boolean isPlaying() {
    return getScores().size() != 0;
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
