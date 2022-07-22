package co.com.sofka.fullstackgame.model.card.gateways;

import co.com.sofka.fullstackgame.model.card.Card;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CardRepository {

    // Save
    public Mono<Card> save(Card card);

    //Get all
    public Flux<Card> findAll();

    //Find by id
    public Mono<Card> findById(String id);

    //Delete by id
    public Mono<Void> deleteById(String id);

}
