package blackjack;

import blackjack.models.Card;
import blackjack.models.Rank;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Util {
  public static List<Integer> getScore(final List<Card> cards) {
    final int partialSum =
        cards.stream().mapToInt(i -> i.getRank().value).filter(i -> i != 1).sum();
    final int countAces =
        Math.toIntExact(cards.stream().filter(c -> c.getRank() == Rank.ACE).count());

    List<Integer> scores = Arrays.asList(partialSum);
    for (int i = 0; i < countAces; i++) {
      final List<Integer> scoreCopy = new ArrayList<Integer>(scores);
      scores = new ArrayList<Integer>();

      for (final Integer score : scoreCopy) {
        scores.add(score + 1);
        scores.add(score + 11);
      }
    }

    return scores.stream()
        .mapToInt(i -> i)
        .filter(i -> i <= 21)
        .boxed()
        .collect(Collectors.toList());
  }
}
