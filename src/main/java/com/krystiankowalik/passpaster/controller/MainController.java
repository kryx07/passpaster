package com.krystiankowalik.passpaster.controller;

import com.google.common.eventbus.Subscribe;
import com.krystiankowalik.passpaster.util.EventBusProvider;
import com.krystiankowalik.passpaster.hotkey.HotKeyHandler;
import com.krystiankowalik.passpaster.event.ApplicationStop;
import com.krystiankowalik.passpaster.io.StreamFileHelper;
import com.krystiankowalik.passpaster.parser.ShortcutParser;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private TextArea text;
    private StreamFileHelper streamFileHelper;
    private HotKeyHandler hotKeyHandler;
    private ShortcutParser shortcutParser;


    private void init() {
        streamFileHelper = new StreamFileHelper();
        hotKeyHandler = new HotKeyHandler();
        shortcutParser = new ShortcutParser();
        text.setWrapText(true);
        EventBusProvider.getInstance().register(this);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        init();

        text.setText(getFormattedFileContents());

        hotKeyHandler.control(shortcutParser.parse(getConfigFileContents()));
    }

    private List<String> getConfigFileContents() {
        System.out.println();
        String folderPath = null;
        try {
            folderPath = new File(getClass().getProtectionDomain().getCodeSource().getLocation().toURI()).getParent();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return streamFileHelper.readAllLines(Paths.get(folderPath + File.separator + "shortcuts.cfg"));

    }

    private String getFormattedFileContents() {
        List<String> lines = getConfigFileContents();
        StringBuilder stringBuilder = new StringBuilder();
        lines.forEach(line -> {
            stringBuilder.append(line);
            stringBuilder.append(System.lineSeparator());
        });

        return stringBuilder.toString();
    }

    @Subscribe
    public void handleApplicationStop(ApplicationStop applicationStop) {
        hotKeyHandler.stop();

    }
}
