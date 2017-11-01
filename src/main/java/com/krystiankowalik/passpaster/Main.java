package com.krystiankowalik.passpaster;

import com.google.common.eventbus.EventBus;
import com.krystiankowalik.passpaster.event.ApplicationStop;
import com.krystiankowalik.passpaster.model.Shortcut;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

   // private HotKeyHandler hotKeyHandler;




    @Override
    public void init() throws Exception {
        super.init();
        EventBusProvider.getInstance().register(this);
        //hotKeyHandler = new HotKeyHandler();
    }

    private void showScene(Stage primaryStage) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/view/main_pane.fxml"));
            primaryStage.setTitle("Pass paster");
            primaryStage.setScene(new Scene(root, 300, 300));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void start(Stage primaryStage) {
        showScene(primaryStage);

        //hotKeyHandler.control();

    }

    @Override
    public void stop() throws Exception {
        // TODO Auto-generated method stub
       // hotKeyHandler.stop();
        EventBusProvider.getInstance().post(new ApplicationStop());

        super.stop();
    }

}
