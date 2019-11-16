import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main_shell.MainShell;

import java.io.IOException;

public class MainStage extends Application
{
    public void CreateShell(String[] args){
        launch(args);
    }
    private double xOffset = 0.0;
    private double yOffset = 0.0;

    @Override
    public void start(Stage primaryStage)
    {
        primaryStage.setTitle("BloodMist Launcher");
        primaryStage.initStyle(StageStyle.UNDECORATED);

        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("main_shell/main-shell.fxml"));
            var quickPlayScene = new MainShell(root, 800, 800);

            quickPlayScene.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    xOffset = primaryStage.getX() - event.getScreenX();
                    yOffset = primaryStage.getY() - event.getScreenY();
                }
            });

            quickPlayScene.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    primaryStage.setX(event.getScreenX() + xOffset);
                    primaryStage.setY(event.getScreenY() + yOffset);
                }
            });

            primaryStage.setScene(quickPlayScene);
            primaryStage.show();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
