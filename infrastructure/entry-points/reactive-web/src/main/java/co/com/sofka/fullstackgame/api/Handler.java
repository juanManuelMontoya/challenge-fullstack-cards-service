package co.com.sofka.fullstackgame.api;

import co.com.sofka.fullstackgame.model.card.Card;
import co.com.sofka.fullstackgame.usecase.allcards.AllCardsUseCase;
import co.com.sofka.fullstackgame.usecase.createcard.CreateCardUseCase;
import co.com.sofka.fullstackgame.usecase.deletecard.DeleteCardUseCase;
import co.com.sofka.fullstackgame.usecase.findcardbyid.FindCardByIdUseCase;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@CrossOrigin(origins = {"*"})
public class Handler {
     private final CreateCardUseCase createCardUseCase;
     private final FindCardByIdUseCase findCardByIdUseCase;
     private final AllCardsUseCase allCardsUseCase;
     private final DeleteCardUseCase deleteCardUseCase;

     Logger logger = LoggerFactory.getLogger(Handler.class);

     public Mono<ServerResponse> getCards(ServerRequest serverRequest){
         return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                 .body(BodyInserters.fromPublisher(allCardsUseCase.getAllCards(), Card.class))
                 .switchIfEmpty(ServerResponse.notFound().build());
     }

    public Mono<ServerResponse> createCard(ServerRequest serverRequest){

        return serverRequest.bodyToMono(Card.class)
                .flatMap(createCardUseCase::saveCard)
                .flatMap(card -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(card))
                .onErrorResume(this::onErrorResume);
    }

    public Mono<ServerResponse> getCardById(ServerRequest serverRequest){
         var id = serverRequest.pathVariable("id");

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(findCardByIdUseCase.findCardById(id), Card.class))
                .onErrorResume(this::onErrorResume);
    }
    public Mono<ServerResponse> updateCard(ServerRequest serverRequest){
        return serverRequest.bodyToMono(Card.class)
                .flatMap(createCardUseCase::saveCard)
                .flatMap(card -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(card))
                .onErrorResume(this::onErrorResume);
    }

    private Mono<ServerResponse> onErrorResume(Throwable error) {
        return ServerResponse.status(error.getMessage().contains("doesn't exists") ? 404 : 409)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(error.getMessage()));
    }

    /*public Mono<ServerResponse> deleteCardById(ServerRequest serverRequest){
        var id = serverRequest.pathVariable("id");

        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(deleteCardUseCase.deleteCardById(id), Card.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }*/

}
