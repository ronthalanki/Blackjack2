package blackjack.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Card {
  @Getter private final Suit suit;
  @Getter private final Rank rank;
}
