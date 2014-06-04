package TwitterSearch.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import TwitterSearch.Main;
import TwitterSearch.Models.MainModel;
import TwitterSearch.Util.FXUtils;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private final MainModel mainModel;

    @FXML
    private Button btnStop;
    @FXML
    private Button btnQuery;
    @FXML
    private ListView lstItems;
    @FXML
    private TextField txtText;

    public MainController(MainModel model) {
        this.mainModel = model;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtText.textProperty().bindBidirectional(mainModel.queryTextProperty());
        txtText.disableProperty().bind(mainModel.busyProperty());
        btnQuery.onActionProperty().bind(mainModel.queryActionProperty());
        btnQuery.disableProperty().bind(mainModel.queryActionDisabledProperty().or(mainModel.busyProperty()));
        btnStop.disableProperty().bind(mainModel.busyProperty().not());
        btnStop.onActionProperty().bind(mainModel.stopActionProperty());
        lstItems.itemsProperty().bind(mainModel.resultsProperty());
        lstItems.visibleProperty().bind(mainModel.resultsProperty().emptyProperty().not());

        FXUtils.setListViewCellFactory(
                lstItems,
                Main.class.getResource("Views/tweetItem.fxml"),
                mainModel.getItemActionHandler());
    }
}