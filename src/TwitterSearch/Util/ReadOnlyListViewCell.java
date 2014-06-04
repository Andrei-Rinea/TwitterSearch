package TwitterSearch.Util;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;

public class ReadOnlyListViewCell extends ListCell {

    private final URL fxmlURL;

    public ReadOnlyListViewCell(URL fxmlURL) {

        this.fxmlURL = fxmlURL;
    }

    @Override
    protected void updateItem(final Object item, boolean empty) {
        super.updateItem(item, empty);
        if (item == null || empty) return;

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.getNamespace().put("model", item);
            loader.setLocation(fxmlURL);
            GridPane gp = (GridPane) loader.load();
            setGraphic(gp);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
