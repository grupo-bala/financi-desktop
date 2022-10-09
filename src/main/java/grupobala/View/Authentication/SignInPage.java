package grupobala.View.Authentication;

import grupobala.View.Page.Page;
import grupobala.View.Utils.Colors.ColorFactory;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class SignInPage implements Page {
    public Scene getScene() {
        HBox mainContainer = getMainContainer();
        Scene scene = new Scene(mainContainer);
        HBox itemsContainer = getItemsContainer();

        mainContainer.getChildren().addAll(
            itemsContainer
        );

        return scene;
    }

    private HBox getItemsContainer() {
        HBox itemsContainer = new HBox();
        VBox logoContainer = getLogoBox();
        HBox loginAligmentContainer = new HBox();
        VBox loginContainer = getLoginContainer();
        
        itemsContainer.setAlignment(Pos.BOTTOM_LEFT);

        loginAligmentContainer.getChildren().add(loginContainer);
        HBox.setHgrow(loginAligmentContainer, Priority.ALWAYS);
        HBox.setHgrow(itemsContainer, Priority.ALWAYS);
        loginAligmentContainer.setAlignment(Pos.BOTTOM_RIGHT);

        itemsContainer.getChildren().addAll(
            logoContainer,
            loginAligmentContainer
        );

        return itemsContainer;
    }

    private VBox getLoginContainer() {
        VBox loginContainer = new VBox();
        Text placeholder1 = new Text("Lorem");
        Text placeholder2 = new Text("Ipsum");

        loginContainer.setAlignment(Pos.CENTER);

        loginContainer.getChildren().addAll(
            placeholder1,
            placeholder2
        );

        return loginContainer;
    }

    private VBox getLogoBox() {
        Image logo = new Image("file:src/main/resources/grupobala/images/financi-logo.png");
        ImageView logoView = new ImageView(logo);
        Text logoText = new Text("Financi");
        VBox logoContainer = new VBox();

        VBox.setMargin(logoText, new Insets(10.0));
        logoContainer.setAlignment(Pos.CENTER);
        logoText.setFill(ColorFactory.fromRGBA(255, 255, 255, 255));
        logoText.setFont(new Font("Montserrat Bold", 64));

        logoContainer.getChildren().addAll(
            logoView,
            logoText
        );

        return logoContainer;
    }

    private HBox getMainContainer() {
        HBox mainContainer = new HBox();

        mainContainer.setBackground(
            new Background(new BackgroundFill(
                ColorFactory.fromRGBA(34, 38, 34, 255),
                new CornerRadii(0),
                new Insets(0)
            ))
        );

        mainContainer.setPadding(new Insets(0.0, 200.0, 0.0, 200.0));
        return mainContainer;
    }
}
