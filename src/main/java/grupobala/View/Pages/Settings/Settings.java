package grupobala.View.Pages.Settings;

import grupobala.Entities.User.IUser.IUser;
import grupobala.Entities.User.User;
import grupobala.View.PageManager;
import grupobala.View.Components.NavigationBar.NavigationBar;
import grupobala.View.Components.Popups.EditPasswordPopup;
import grupobala.View.Components.Popups.EditUserInfoPopup;
import grupobala.View.Pages.Authentication.SignIn.SignInPage;
import grupobala.View.Pages.Page.Page;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class Settings implements Page {

    private StackPane mainPane = new StackPane();
    private NavigationBar navigationBar = new NavigationBar();
    private VBox container = new VBox();
    private Button edit = new Button("Editar dados");
    private Button logout = new Button("Fazer logout");
    private EditUserInfoPopup editPopup = new EditUserInfoPopup();
    private EditPasswordPopup editPasswordPopup = new EditPasswordPopup();

    public Settings() {
        this.logout.setOnAction(e -> {
            new User().close();
            new PageManager().setCurrentPage(new SignInPage());
        });
    }

    @Override
    public Pane getMainPane() {
        VBox mainContainer = new VBox();

        mainPane
            .getStylesheets()
            .add(
                "file:src/main/resources/grupobala/css/Pages/Settings/Settings.css"
            );
        mainPane.getStyleClass().add("mainPane");
        mainContainer.getStyleClass().add("mainContainer");

        setContainer();

        mainContainer
            .getChildren()
            .addAll(navigationBar.getComponent(), this.container);

        mainPane
            .getChildren()
            .addAll(
                mainContainer,
                editPopup.getComponent(),
                editPasswordPopup.getComponent()
            );

        editPopup.setOnConfirm(() -> {
            reloadInfo();
        });

        editPopup.setOnChangePassword(() -> {
            this.editPasswordPopup.getPopup().showPopup();
        });

        return mainPane;
    }

    private void setContainer() {
        Image avatarImg = new Image(
            "file:src/main/resources/grupobala/images/avatar.png"
        );
        ImagePattern avaPattern = new ImagePattern(avatarImg);
        Circle icon = new Circle(80);
        icon.setFill(avaPattern);
        VBox userInfo = new VBox();

        this.container.getStyleClass().add("container");
        userInfo.getStyleClass().add("user-info");
        icon.getStyleClass().add("avatar-icon");

        userInfo.getChildren().addAll(icon, getNameUserName());

        this.container.getChildren().addAll(userInfo, getButtons());
    }

    private VBox getButtons() {
        VBox vbox = new VBox();

        vbox.getStyleClass().add("buttons");
        edit.getStyleClass().add("edit-button");
        logout.getStyleClass().add("logout-button");

        vbox.getChildren().addAll(edit, logout);

        edit.setOnMouseClicked(e -> {
            this.editPopup.getPopup().showPopup();
        });

        return vbox;
    }

    private VBox getNameUserName() {
        VBox vbox = new VBox();
        Text username = new Text();
        Text name = new Text();
        IUser user = new User();

        username.setText("@" + user.getUsername());
        name.setText(user.getName());

        vbox.getStyleClass().add("infoName");
        username.getStyleClass().add("username");
        name.getStyleClass().add("name");

        vbox.getChildren().addAll(username, name);

        return vbox;
    }

    private void reloadInfo() {
        this.container.getChildren().clear();
        this.setContainer();
    }
}
