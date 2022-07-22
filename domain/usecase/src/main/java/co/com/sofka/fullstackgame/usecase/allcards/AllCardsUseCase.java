package co.com.sofka.fullstackgame.usecase.allcards;

import co.com.sofka.fullstackgame.model.card.Card;
import co.com.sofka.fullstackgame.model.card.gateways.CardRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class AllCardsUseCase {
    private final CardRepository repository;

    public Flux<Card> getAllCards() {
        return repository.findAll();
    }
}
