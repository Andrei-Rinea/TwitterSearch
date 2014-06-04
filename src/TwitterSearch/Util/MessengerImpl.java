package TwitterSearch.Util;

import javafx.stage.Stage;
import jfx.messagebox.MessageBox;

public class MessengerImpl implements Messenger {
    private final Stage primaryStage;

    public MessengerImpl(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @Override
    public void displayMessage(String message, String title) {
        MessageBox.show(primaryStage, message, title, MessageBox.OK);
    }
}