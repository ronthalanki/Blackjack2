package blackjack.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class Card {
  @Getter private final Suit suit;
  @Getter private final Rank rank;
}
