package grupobala.View.Components.Card;

import grupobala.View.Components.Component.Component;
import javafx.scene.layout.VBox;

public class CardVBoxComponent implements Component {

    private VBox vBox = new VBox();

    public CardVBoxComponent() {
        vBox.getStyleClass().add("financi-card");
        vBox
            .getStylesheets()
            .add("file:src/main/java/grupobala/View/Components/Card/Card.css");
    }

    public VBox getComponent() {
        return vBox;
    }
}
