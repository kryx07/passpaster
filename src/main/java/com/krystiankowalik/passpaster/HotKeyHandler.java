package com.krystiankowalik.passpaster;

import javax.swing.KeyStroke;

import com.tulskiy.keymaster.common.HotKey;
import com.tulskiy.keymaster.common.HotKeyListener;
import com.tulskiy.keymaster.common.Provider;

public class HotKeyHandler {

	private final String MY_CLIPBOARD = "DUPA";

	private ClipboardHelper clipboardHelper;
	private Provider provider;

	public HotKeyHandler() {
		super();
		this.clipboardHelper = new ClipboardHelper();
		this.provider = Provider.getCurrentProvider(false);
	}

	public void control() {

		this.provider.register(KeyStroke.getKeyStroke("control shift alt G"), new HotKeyListener() {
			@Override
			public void onHotKey(HotKey hotKey) {
				onHotKeyClipboardAction(MY_CLIPBOARD);
			}
		});

		this.provider.register(KeyStroke.getKeyStroke("control shift alt Q"), new HotKeyListener() {
			@Override
			public void onHotKey(HotKey hotKey) {
				System.out.println("Exiting...");
				stop();
			}
		});
	}

	private void onHotKeyClipboardAction(String stringToPaste) {

		String currentClipboardContents = clipboardHelper.getClipboardContents();

		clipboardHelper.setClipboardContents(stringToPaste);

		delay(100);

		clipboardHelper.paste();

		delay(100);

		clipboardHelper.setClipboardContents(currentClipboardContents);
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
		System.exit(0);

	}

	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		this.provider.reset();
		this.provider.stop();
		System.exit(0);
		super.finalize();

	}

}
