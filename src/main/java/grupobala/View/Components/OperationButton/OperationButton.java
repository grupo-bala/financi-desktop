package grupobala.View.Components.OperationButton;

import grupobala.View.Components.Component.Component;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class OperationButton implements Component {

    VBox vBox = new VBox();

    @Override
    public VBox getComponent() {
        return this.vBox;
    }

    public enum IconEnum {
        ENTRADA("file:src/main/resources/grupobala/images/incoming-icon.png"),
        SAIDA("file:src/main/resources/grupobala/images/output-icon.png"),
        META("file:src/main/resources/grupobala/images/goal-icon.png");

        public final String pathIcon;

        private IconEnum(String pathIcon) {
            this.pathIcon = pathIcon;
        }
    }

    public VBox getComponent(String buttonText, IconEnum iconEnum) {
        Image operation = new Image(iconEnum.pathIcon);
        ImageView operationIcon = new ImageView(operation);
        Text buttonLabel = new Text(buttonText);

        vBox.getStyleClass().add("container");
        buttonLabel.getStyleClass().add("button-label");
        vBox
            .getStylesheets()
            .add(
                "file:src/main/resources/grupobala/css/Components/OperationButton/OperationButton.css"
            );

        vBox.getChildren().addAll(operationIcon, buttonLabel);

        return this.vBox;
    }
}
