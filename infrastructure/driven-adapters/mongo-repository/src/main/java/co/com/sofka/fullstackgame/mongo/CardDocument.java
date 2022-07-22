package co.com.sofka.fullstackgame.mongo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class CardDocument {

    @Id
    private String id;
    private String image;
    private int power;
    private String description;

    public CardDocument() {
    }

    public CardDocument(String id, String image, int power, String description) {
        this.id = id;
        this.image = image;
        this.power = power;
        this.description = description;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public int getPower() {
        return power;
    }

    public String getDescription() {
        return description;
    }
}
