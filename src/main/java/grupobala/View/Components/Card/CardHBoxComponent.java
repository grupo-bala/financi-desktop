package grupobala.View.Components.Card;

import grupobala.View.Components.Component.Component;
import javafx.scene.layout.HBox;

public class CardHBoxComponent implements Component {

    private HBox HBox = new HBox();

    public CardHBoxComponent() {
        HBox.getStyleClass().add("financi-card");
        HBox
            .getStylesheets()
            .add(
                "file:src/main/resources/grupobala/css/Components/Card/Card.css"
            );
    }

    public HBox getComponent() {
        return HBox;
    }
}
