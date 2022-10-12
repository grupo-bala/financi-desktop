package grupobala.View.Components.TextField;

import grupobala.View.Components.Component.Component;
import javafx.scene.control.PasswordField;

public class PasswordFieldComponent implements Component {

    @Override
    public PasswordField getComponent() {
        PasswordField passwordField = new PasswordField();
        passwordField.getStyleClass().add("financi-text-field");
        passwordField
            .getStylesheets()
            .add(
                "file:src/main/java/grupobala/View/Components/TextField/TextField.css"
            );

        return passwordField;
    }
}
