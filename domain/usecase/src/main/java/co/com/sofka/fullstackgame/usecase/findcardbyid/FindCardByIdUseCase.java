package co.com.sofka.fullstackgame.usecase.findcardbyid;

import co.com.sofka.fullstackgame.exceptions.CardException;
import co.com.sofka.fullstackgame.model.card.Card;
import co.com.sofka.fullstackgame.model.card.gateways.CardRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class FindCardByIdUseCase {

    private final CardRepository repository;

    public Mono<Card> findCardById(String id){
        return repository.findById(id)
                .onErrorResume(error -> Mono.error(new CardException("The given card id doesn't exists")));
    }
}
