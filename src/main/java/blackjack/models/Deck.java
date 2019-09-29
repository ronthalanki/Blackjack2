package blackjack.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Deck {
  private final List<Card> deck;
  private final Random random;

  public Deck() {
    this(new Random());
  }

  public Deck(final Random random) {
    this.deck = new ArrayList<>();
    this.random = random;
    resetDeck(random);
  }

  public Card drawCard() {
    return deck.remove(deck.size() - 1);
  }

  private void resetDeck(final Random random) {
    deck.clear();

    for (final Suit s : Suit.values()) {
      for (final Rank r : Rank.values()) {
        deck.add(new Card(s, r));
      }
    }

    Collections.shuffle(deck, random);
  }
}
