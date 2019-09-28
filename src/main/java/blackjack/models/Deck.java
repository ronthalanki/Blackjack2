package blackjack.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
  private final List<Card> deck;

  public Deck() {
    deck = new ArrayList<>();
    resetDeck();
  }

  public Card drawCard() {
    return deck.remove(deck.lastIndexOf(deck));
  }

  private void resetDeck() {
    deck.clear();

    for (final Suit s : Suit.values()) {
      for (final Rank r : Rank.values()) {
        deck.add(new Card(s, r));
      }
    }

    Collections.shuffle(deck);
  }
}
