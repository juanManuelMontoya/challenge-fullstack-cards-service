package co.com.sofka.fullstackgame.model.card;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Card {

    private String id;
    private String image;
    private int power;
    private String description;
}
