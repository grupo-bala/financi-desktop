package grupobala.View.Components.Logo;

import grupobala.View.Components.Component.Component;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class LogoComponent implements Component {

    VBox logoVBox = new VBox();
    Image logoImg = new Image(
        "file:src/main/resources/grupobala/images/financi-logo.png"
    );
    ImageView logo = new ImageView(logoImg);
    Text financiTitle = new Text("Financi");

    public LogoComponent() {
        logoVBox
            .getStylesheets()
            .add(
                "file:src/main/resources/grupobala/css/Components/Logo/Logo.css"
            );

        logoVBox.getStyleClass().add("logo-container");
        financiTitle.getStyleClass().add("title");

        logoVBox.getChildren().addAll(logo, financiTitle);
    }

    @Override
    public VBox getComponent() {
        return logoVBox;
    }
}
