package co.com.sofka.fullstackgame.usecase.deletecard;

import co.com.sofka.fullstackgame.model.card.gateways.CardRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class DeleteCardUseCase {

    private final CardRepository repository;

    public Mono<Void> deleteCardById(String id){
        return repository.deleteById(id);
    }
}
