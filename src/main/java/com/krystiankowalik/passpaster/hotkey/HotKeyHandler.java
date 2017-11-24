package com.krystiankowalik.passpaster.hotkey;

import javax.swing.KeyStroke;

import com.krystiankowalik.passpaster.util.ClipboardHelper;
import com.krystiankowalik.passpaster.model.Shortcut;
import com.tulskiy.keymaster.common.HotKey;
import com.tulskiy.keymaster.common.HotKeyListener;
import com.tulskiy.keymaster.common.Provider;
import org.apache.log4j.Logger;

import java.util.List;

public class HotKeyHandler {

    private final String MY_CLIPBOARD = "DUPA";

    private final Logger logger = Logger.getLogger(this.getClass());

    private ClipboardHelper clipboardHelper;
    private Provider provider;

    public HotKeyHandler() {
        super();
        this.clipboardHelper = new ClipboardHelper();
        this.provider = Provider.getCurrentProvider(false);
    }

    public void control(List<Shortcut> shortcuts) {

        shortcuts.forEach(shortcut -> {
            this.provider.register(KeyStroke.getKeyStroke(shortcut.getKeyCombination()), new HotKeyListener() {
                @Override
                public void onHotKey(HotKey hotKey) {
                    onHotKeyClipboardAction(shortcut.getCustomText());
                }
            });
        });

        /*this.provider.register(KeyStroke.getKeyStroke("control shift alt G"), new HotKeyListener() {
            @Override
            public void onHotKey(HotKey hotKey) {
                onHotKeyClipboardAction(MY_CLIPBOARD);
            }
        });*/

        this.provider.register(KeyStroke.getKeyStroke("control shift alt Q"), new HotKeyListener() {
            @Override
            public void onHotKey(HotKey hotKey) {
                logger.info("Exiting");
                stop();
                System.exit(0);
            }
        });
    }

    private void onHotKeyClipboardAction(String stringToPaste) {

        String originalClipboardContents = clipboardHelper.getClipboardContents();

        setClipboardContents(stringToPaste);

        delay(100);
        while (!clipboardHelper.getClipboardContents().equals(stringToPaste)) {
            setClipboardContents(stringToPaste);
            delay(100);
        }

        clipboardHelper.paste();

        delay(100);
        setClipboardContents(originalClipboardContents);

    }

    private void delay(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void stop() {
        this.provider.reset();
        this.provider.stop();
        //System.exit(0);

    }

    public void reset() {
        this.provider.reset();
    }

    @Override
    protected void finalize() throws Throwable {
        // TODO Auto-generated method stub
        this.provider.reset();
        this.provider.stop();
        System.exit(0);
        super.finalize();

    }

    private void setClipboardContents(String string) {
        clipboardHelper.setClipboardContents(string);

    }

}
