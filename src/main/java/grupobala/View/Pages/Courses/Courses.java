package grupobala.View.Pages.Courses;

import grupobala.View.Components.Card.CardVBoxComponent;
import grupobala.View.Components.NavigationBar.NavigationBar;
import grupobala.View.Components.Popup.PopupComponent;
import grupobala.View.Pages.Page.Page;

import javafx.scene.layout.Priority;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Courses implements Page {

    private StackPane mainPane = new StackPane();
    private NavigationBar navigationBar = new NavigationBar();
    private PopupComponent coursePopup = new PopupComponent();
    @Override
    public Pane getMainPane() {
        VBox mainContainer = new VBox();
        VBox pageContent = new VBox();
        ScrollPane clipContainer = new ScrollPane();

        mainPane.getChildren().addAll(mainContainer, coursePopup.getComponent());
        mainContainer
            .getChildren()
            .addAll(navigationBar.getComponent(), clipContainer);
        clipContainer.setContent(pageContent);
        pageContent.getChildren().add(getMainContent());

        mainPane.getStyleClass().add("courses");
        mainContainer.getStyleClass().add("courses-main-container");
        pageContent.getStyleClass().add("courses-page-content");

        mainPane
            .getStylesheets()
            .add(
                "file:src/main/resources/grupobala/css/Pages/Courses/Courses.css"
            );

        clipContainer.setStyle("-fx-background-color: transparent;");
        clipContainer.setFitToHeight(true);
        clipContainer.setFitToWidth(true);

        return this.mainPane;
    }

    private VBox getMainContent() {
        VBox card = new CardVBoxComponent().getComponent();
        Text title = new Text("Aprendizado");

        card
            .getChildren()
            .addAll(
                title,
                getCourseCard(),
                getCourseCard(),
                getCourseCard(),
                getCourseCard()
            );

        card.getStyleClass().add("courses-card-content");
        title.getStyleClass().add("courses-title");

        return card;
    }

    private VBox getCourseCard() {
        VBox card = new CardVBoxComponent().getComponent();
        Text title = new Text("Fundamentos de finanças");
        Text description = new Text(
            "O curso Fundamentos de Finanças proporciona  a você uma visão básica de finanças, abordando  os objetivos e a estruturação da administração financeira bem como são tomadas as decisões financeiras ótimas."
        );
        HBox info = getCardInfo(5, 10, 30);
        Button showCourse = new Button("Ver curso");
        showCourse.setOnMouseClicked(course -> {
            coursePopup.showPopup();;
        });
        description.setWrappingWidth(400);

        card.getChildren().addAll(title, description, info, showCourse);

        card.getStyleClass().add("courses-course-card");
        title.getStyleClass().add("courses-card-title");
        description.getStyleClass().add("courses-card-description");
        showCourse.getStyleClass().add("courses-show-course");

        return card;
    }

    private HBox getCardInfo(
        int workload,
        int classesCount,
        int minutesPerClass
    ) {
        HBox info = new HBox();

        info
            .getChildren()
            .addAll(
                getInfoItem(
                    "file:src/main/resources/grupobala/images/Future.png",
                    "Carga horária",
                    String.format("%d horas", workload)
                ),
                getInfoItem(
                    "file:src/main/resources/grupobala/images/VideoPlaylist.png",
                    String.format("%d aulas", classesCount),
                    String.format("%d minutos", minutesPerClass)
                )
            );

        info.setStyle("-fx-alignment: center; -fx-spacing: 30px;");

        return info;
    }

    private VBox getInfoItem(String img, String upper, String bottom) {
        VBox infoItem = new VBox();
        HBox upperBox = new HBox();
        ImageView icon = new ImageView(img);
        Text title = new Text(upper);
        Text description = new Text(bottom);

        upperBox.getChildren().addAll(icon, title);
        infoItem.getChildren().addAll(upperBox, description);

        infoItem.setStyle(
            "-fx-alignment: center; -fx-spacing: 5px; -fx-fill: white;"
        );
        upperBox.setStyle("-fx-alignment: center; -fx-spacing: 2px;");
        title.setStyle("-fx-font-weight: bold; -fx-fill: white;");
        description.setStyle("-fx-fill: white;");

        return infoItem;
    }
    
    public void coursePopup(){
        VBox card = new CardVBoxComponent().getComponent();
        Text popupTitle = new Text("Produtividade e gestão de custos");
        VBox lessonsVbox = new VBox();
        HBox lessonExample = new HBox();
        Button watchLesson = new Button("ASSISTIR AULA");
        Text lessonTitle = new Text("Aula 01");
        HBox info = new HBox();

        info
            .getChildren()
            .addAll(
                getInfoItem(
                    "file:src/main/resources/grupobala/images/Future.png","30 minutos",""));


        lessonExample.getChildren().addAll(lessonTitle, info, watchLesson);
        lessonsVbox.getChildren().addAll(lessonExample, lessonExample, lessonExample, lessonExample
        , lessonExample);
        card.getChildren().addAll(popupTitle, lessonsVbox);

        card.getStyleClass().add("card");
        popupTitle.getStyleClass().add("popup-title");
        lessonsVbox.getStyleClass().add("lessons-vbox");

        VBox.setVgrow(popupTitle, Priority.ALWAYS);
        coursePopup.getComponent().getChildren().addAll(card);
        coursePopup.showPopup();
    }
    
}
