package com.krystiankowalik.passpaster.parser;

import com.krystiankowalik.passpaster.Main;
import com.krystiankowalik.passpaster.io.StreamFileHelper;
import com.krystiankowalik.passpaster.model.Shortcut;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ShortcutParser {

    public List<Shortcut> parse(List<String> lines) {

        List<Shortcut> shortcutList = new ArrayList<>();

        lines.forEach(line ->
                shortcutList.add(parseLine(line)));

        return shortcutList;

    }

    private Shortcut parseLine(String line) {
        Shortcut shortcut = new Shortcut();
        String[] tokens = line.split("\\|");
        shortcut.setKeyCombination(tokens[0]);
        shortcut.setCustomText(tokens[1]);
        return shortcut;
    }
    }
