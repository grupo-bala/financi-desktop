package grupobala.View.Components.TextField;

import grupobala.View.Components.Component.Component;
import javafx.scene.control.TextField;

public class TextFieldComponent implements Component {

    TextField textField = new TextField();

    public TextFieldComponent() {
        textField.getStyleClass().add("financi-text-field");
        textField
            .getStylesheets()
            .add(
                "file:src/main/resources/grupobala/css/Components/TextField/TextField.css"
            );
    }

    @Override
    public TextField getComponent() {
        return textField;
    }
}
