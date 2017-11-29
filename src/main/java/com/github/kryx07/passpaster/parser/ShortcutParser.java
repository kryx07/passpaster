package com.github.kryx07.passpaster.parser;

import com.github.kryx07.passpaster.model.Shortcut;

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

    public List<String> unParse(List<Shortcut> shortcuts) {
        List<String> lines = new ArrayList<>();

        shortcuts
                .forEach(s -> lines.add(s.getKeyCombination() + "|" + s.getCustomText()));
        return lines;
    }
}
