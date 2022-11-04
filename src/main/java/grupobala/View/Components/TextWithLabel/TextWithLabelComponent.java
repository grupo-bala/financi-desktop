package grupobala.View.Components.TextWithLabel;

import grupobala.View.Components.Component.Component;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class TextWithLabelComponent implements Component {

    private VBox container = new VBox();
    private Text topText;
    private Text botText;

    public TextWithLabelComponent(String topText, String botText) {
        this.topText = new Text(topText);
        this.botText = new Text(botText);

        this.topText.getStyleClass().add("financi-textwlabel-top");
        this.botText.getStyleClass().add("financi-textwlabel-bot");

        container
            .getStylesheets()
            .add(
                "file:src/main/resources/grupobala/css/Components/TextWithLabel/TextWithLabel.css"
            );
        container.getChildren().addAll(this.topText, this.botText);
    }

    @Override
    public VBox getComponent() {
        return this.container;
    }
}
