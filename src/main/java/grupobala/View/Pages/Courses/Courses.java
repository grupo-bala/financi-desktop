package grupobala.View.Pages.Courses;

import java.util.ArrayList;

import grupobala.Controller.Lesson.LessonController;
import grupobala.Controller.Lesson.ILessonController.ILessonController;
import grupobala.View.Components.Card.CardVBoxComponent;
import grupobala.View.Components.NavigationBar.NavigationBar;
import grupobala.View.Components.Popup.PopupComponent;
import grupobala.View.Pages.Page.Page;
import grupobala.Entities.Course.ICourse.ICourse;
import grupobala.Entities.Lesson.ILesson.ILesson;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Courses implements Page {

    private LessonController lessonController= new LessonController();
    private StackPane mainPane = new StackPane();
    private NavigationBar navigationBar = new NavigationBar();
    private PopupComponent coursePopup = new PopupComponent();
    private ICourse currentCourse;

    @Override
    public Pane getMainPane() {
        VBox mainContainer = new VBox();
        VBox pageContent = new VBox();
        ScrollPane clipContainer = new ScrollPane();

        mainPane
            .getChildren()
            .addAll(mainContainer, coursePopup.getComponent());
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
        ILessonController lessonController = new LessonController();
        VBox card = new CardVBoxComponent().getComponent();
        Text title = new Text("Aprendizado");

        ArrayList<ICourse> courses = new ArrayList<>();

        try {
            courses = lessonController.getCourses();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        card.getChildren().add(title);

        for (ICourse course : courses) {
            card.getChildren().add(getCourseCard(course));
        }

        card.getStyleClass().add("courses-card-content");
        title.getStyleClass().add("courses-title");

        return card;
    }

    private VBox getCourseCard(ICourse course) {
        VBox card = new CardVBoxComponent().getComponent();
        Text title = new Text(course.getName());
        Text description = new Text(course.getDescription());
        HBox info = getCardInfo(5, 10, 30);
        Button showCourse = new Button("Ver curso");
        //getCoursePopup();
        showCourse.setOnMouseClicked(e -> {
            this.currentCourse = course;
            coursePopup.showPopup();
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

    private void getCoursePopup() {
        Text popupTitle = new Text(this.currentCourse.getName());
        VBox card = new CardVBoxComponent().getComponent();
    
        VBox lessonsVbox = new VBox();
        ArrayList<ILesson> lessons = new ArrayList<>();

        try {
            lessons = lessonController.getLessons(this.currentCourse.getId());
        } catch(Exception error){
            //error popup
        }
        
        for(ILesson lesson : lessons){
            HBox lessonHBox = new HBox();
            try{
                lessonHBox = createLesson(lessonController.getLesson(this.currentCourse.getId(), lesson.getId()));
            }
            catch(Exception error){
                //erro popup
            }

            lessonsVbox
                .getChildren()
                .addAll(lessonHBox, createEmptyVbox());
        }

        card.getChildren().addAll(popupTitle, lessonsVbox);

        card.getStyleClass().add("card");
        popupTitle.getStyleClass().add("popup-title");
        lessonsVbox.getStyleClass().add("lessons-vbox");

        VBox.setVgrow(popupTitle, Priority.ALWAYS);
        coursePopup.getComponent().getChildren().addAll(card);
    }

    HBox createLesson(ILesson lesson) {
        Button watchLesson = new Button("ASSISTIR AULA");
        Text lessonTitle = new Text(lesson.getName());
        HBox info = new HBox();
        VBox checkBoxVbox = new VBox();
        Image checkBox = new Image(
            "file:src/main/resources/grupobala/images/Checkbox.png"
        );
        ImageView checkBoxView = new ImageView(checkBox);

        info
            .getChildren()
            .addAll(
                getInfoItem(
                    "file:src/main/resources/grupobala/images/Future.png",
                    String.format("%d segundos", lesson.getDurationInSeconds()),
                    ""
                )
            );

        checkBoxView.setFitHeight(40);
        checkBoxView.setFitWidth(40);
        checkBoxView.setPreserveRatio(true);

        checkBoxVbox.getChildren().add(checkBoxView);

        checkBoxVbox.getStyleClass().add("checked-check-box");
        watchLesson.getStyleClass().add("watch-lesson");
        info.getStyleClass().add("info");
        lessonTitle.getStyleClass().add("lesson-title");

        HBox lessonExample = new HBox();
        lessonExample
            .getChildren()
            .addAll(lessonTitle, info, watchLesson, checkBoxVbox);
        lessonExample.getStyleClass().add("lesson-example");

        return lessonExample;
    }

    VBox createEmptyVbox() {
        VBox vbox = new VBox();
        vbox.getStyleClass().add("padding-vbox");
        return vbox;
    }
}
