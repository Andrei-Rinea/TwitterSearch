package TwitterSearch.Util;

import javafx.event.EventHandler;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.InflaterOutputStream;

public class FXUtils {
    private FXUtils() {
        throw new UnsupportedOperationException();
    }

    public static void setListViewCellFactory(ListView lstItems, final URL url, final ActionHandler<Object> onDoubleClickHandler) {
        lstItems.setCellFactory(new Callback<ListView, ListCell>() {
            @Override
            public ListCell call(ListView listView) {
                final ReadOnlyListViewCell cell = new ReadOnlyListViewCell(url);
                if (onDoubleClickHandler != null) {
                    EventHandler<? super MouseEvent> mouseClickEventHandler = new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            if (mouseEvent.getClickCount() == 2) {
                                onDoubleClickHandler.handle(cell.getItem());
                            }
                        }
                    };
                    cell.setOnMouseClicked(mouseClickEventHandler);
                }
                return cell;
            }
        });
    }
}