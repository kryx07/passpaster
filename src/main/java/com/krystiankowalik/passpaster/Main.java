package com.krystiankowalik.passpaster;

import com.krystiankowalik.passpaster.event.ApplicationStop;
import com.krystiankowalik.passpaster.util.EventBusProvider;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {


    @Override
    public void init() throws Exception {
        super.init();
        EventBusProvider.getInstance().register(this);
    }

    private void showScene(Stage primaryStage) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/view/main_pane.fxml"));
            primaryStage.setTitle("Pass paster");
            primaryStage.setScene(new Scene(root, 600, 600));
            primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("/images/icon.png")));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void start(Stage primaryStage) {
        showScene(primaryStage);
    }

    @Override
    public void stop() throws Exception {
        EventBusProvider.getInstance().post(new ApplicationStop());

        super.stop();
    }

}
