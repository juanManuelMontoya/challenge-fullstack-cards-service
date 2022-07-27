package co.com.sofka.fullstackgame.usecase.createcard;

import co.com.sofka.fullstackgame.exceptions.CardException;
import co.com.sofka.fullstackgame.model.card.Card;
import co.com.sofka.fullstackgame.model.card.gateways.CardRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class CreateCardUseCase {

    private final CardRepository repository;

    public Mono<Card> saveCard(Card card){
        return repository.save(card)
                .onErrorResume(error -> Mono.error(new CardException("The given card already exists")));
    }
}
