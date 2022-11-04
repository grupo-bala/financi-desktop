package grupobala.View.Components.AvatarCard;

import grupobala.Entities.User.User;
import grupobala.View.Components.Component.Component;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class AvatarCardComponent implements Component {

    private HBox avatarCard = new HBox();
    private Text greetings = new Text();
    private Text userName = new Text();

    public AvatarCardComponent() {
        Image avatarImg = new Image(
            "file:src/main/resources/grupobala/images/avatar.png"
        );
        ImagePattern avaPattern = new ImagePattern(avatarImg);
        Circle icon = new Circle(30);
        VBox textContainer = new VBox();

        icon.setFill(avaPattern);

        avatarCard
            .getStylesheets()
            .add(
                "file:src/main/java/grupobala/View/Components/AvatarCard/AvatarCard.css"
            );
        avatarCard.getStyleClass().add("financi-avatar-card");
        icon.getStyleClass().add("financi-avatar-icon");
        greetings.getStyleClass().add("financi-avatar-greetings");
        userName.getStyleClass().add("financi-avatar-name");
        textContainer.getStyleClass().add("financi-avatar-texts");

        setGreetings();
        setUserName();

        textContainer.getChildren().addAll(userName, greetings);

        avatarCard.getChildren().addAll(icon, textContainer);
    }

    public HBox getComponent() {
        return avatarCard;
    }

    private void setUserName() {
        User user = new User();
        userName.setText(user.getName());
    }

    private void setGreetings() {
        Date currentDate = new Date();
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(currentDate);
        int currentHour = calendar.get(Calendar.HOUR_OF_DAY);

        if (currentHour >= 6 && currentHour <= 12) {
            greetings.setText("Bom dia!");
        } else if (currentHour > 12 && currentHour < 18) {
            greetings.setText("Boa tarde!");
        } else {
            greetings.setText("Boa noite!");
        }
    }
}
