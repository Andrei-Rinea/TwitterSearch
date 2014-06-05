package TwitterSearch.Util;

import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.net.URL;

public class FXUtils {
    private FXUtils() {
        throw new UnsupportedOperationException();
    }

    public static void setListViewCellFactory(ListView lstItems, final URL url, final ActionHandler<Object> onDoubleClickHandler) {
        lstItems.setCellFactory(listView -> {
            final ReadOnlyListViewCell cell = new ReadOnlyListViewCell(url);
            if (onDoubleClickHandler != null) {
                EventHandler<? super MouseEvent> mouseClickEventHandler = mouseEvent -> {
                    if (mouseEvent.getClickCount() == 2) {
                        onDoubleClickHandler.handle(cell.getItem());
                    }
                };
                cell.setOnMouseClicked(mouseClickEventHandler);
            }
            return cell;
        });
    }
}