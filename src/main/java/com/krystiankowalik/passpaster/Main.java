package com.krystiankowalik.passpaster;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	private HotKeyHandler hotKeyHandler;

	@Override
	public void start(Stage primaryStage) throws Exception {

		hotKeyHandler = new HotKeyHandler();

		Parent root = FXMLLoader.load(getClass().getResource("/view/main_pane.fxml"));
		primaryStage.setTitle("Pdf NC");
		primaryStage.setScene(new Scene(root, 900, 600));
		primaryStage.show();

		hotKeyHandler.control();

	}
	
	@Override
	public void stop() throws Exception {
		// TODO Auto-generated method stub
		hotKeyHandler.stop();
		super.stop();
	}

	/*
	 * public void control() { displaySysTray(); Provider provider =
	 * Provider.getCurrentProvider(false);
	 * 
	 * provider.register(KeyStroke.getKeyStroke("control shift alt G"), new
	 * HotKeyListener() {
	 * 
	 * @Override public void onHotKey(HotKey hotKey) { onHotKeyAction(); } });
	 * provider.register(KeyStroke.getKeyStroke("control shift alt Q"), new
	 * HotKeyListener() {
	 * 
	 * @Override public void onHotKey(HotKey hotKey) {
	 * System.out.println("Exiting..."); System.exit(0); } }); }
	 * 
	 * private void onHotKeyAction() {
	 * 
	 * String currentClipboardContents = getClipboardContents();
	 * 
	 * setClipboardContents(MY_CLIPBOARD);
	 * 
	 * delay(100);
	 * 
	 * paste();
	 * 
	 * delay(100);
	 * 
	 * setClipboardContents(currentClipboardContents); }
	 * 
	 * private void paste() { Robot robot = null; try { robot = new Robot();
	 * 
	 * robot.keyRelease(KeyEvent.VK_V); robot.keyRelease(KeyEvent.VK_CONTROL);
	 * robot.keyRelease(KeyEvent.VK_SHIFT); robot.keyRelease(KeyEvent.VK_ALT);
	 * 
	 * robot.keyPress(KeyEvent.VK_CONTROL); robot.keyPress(KeyEvent.VK_V);
	 * robot.keyRelease(KeyEvent.VK_V); robot.keyRelease(KeyEvent.VK_CONTROL);
	 * 
	 * } catch (AWTException e) { e.printStackTrace(); } }
	 * 
	 * private void delay(int ms) { try { Thread.sleep(ms); } catch
	 * (InterruptedException e) { e.printStackTrace(); }
	 * 
	 * }
	 * 
	 * public void setClipboardContents(String aString) { StringSelection
	 * stringSelection = new StringSelection(aString); Clipboard clipboard =
	 * Toolkit.getDefaultToolkit().getSystemClipboard();
	 * clipboard.setContents(stringSelection, null); }
	 * 
	 *//**
		 * Get the String residing on the clipboard.
		 *
		 * @return any text found on the Clipboard; if none found, return an empty
		 *         String.
		 *//*
			 * public String getClipboardContents() { String result = ""; Clipboard
			 * clipboard = Toolkit.getDefaultToolkit().getSystemClipboard(); // odd: the
			 * Object param of getContents is not currently used Transferable contents =
			 * clipboard.getContents(null); boolean hasTransferableText = (contents != null)
			 * && contents.isDataFlavorSupported(DataFlavor.stringFlavor); if
			 * (hasTransferableText) { try { result = (String)
			 * contents.getTransferData(DataFlavor.stringFlavor); } catch
			 * (UnsupportedFlavorException ex) { System.out.println(ex);
			 * ex.printStackTrace(); } catch (IOException ex) { System.out.println(ex);
			 * ex.printStackTrace(); } } return result; }
			 * 
			 * public void displaySysTray() { // Check the SystemTray is supported if
			 * (!SystemTray.isSupported()) {
			 * System.out.println("SystemTray is not supported"); return; } final PopupMenu
			 * popup = new PopupMenu(); final TrayIcon trayIcon = new TrayIcon(new
			 * BufferedImage(1, 1, 1)); final SystemTray tray = SystemTray.getSystemTray();
			 * 
			 * // Create a pop-up menu components MenuItem aboutItem = new
			 * MenuItem("About"); CheckboxMenuItem cb1 = new
			 * CheckboxMenuItem("Set auto size"); CheckboxMenuItem cb2 = new
			 * CheckboxMenuItem("Set tooltip"); Menu displayMenu = new Menu("Display");
			 * MenuItem errorItem = new MenuItem("Error"); MenuItem warningItem = new
			 * MenuItem("Warning"); MenuItem infoItem = new MenuItem("Info"); MenuItem
			 * noneItem = new MenuItem("None"); MenuItem exitItem = new MenuItem("Exit");
			 * 
			 * // Add components to pop-up menu popup.add(aboutItem); popup.addSeparator();
			 * popup.add(cb1); popup.add(cb2); popup.addSeparator(); popup.add(displayMenu);
			 * displayMenu.add(errorItem); displayMenu.add(warningItem);
			 * displayMenu.add(infoItem); displayMenu.add(noneItem); popup.add(exitItem);
			 * 
			 * trayIcon.setPopupMenu(popup);
			 * 
			 * try { tray.add(trayIcon); } catch (AWTException e) {
			 * System.out.println("TrayIcon could not be added."); } }
			 */
}
