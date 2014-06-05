package TwitterSearch;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Callback;

import TwitterSearch.Controllers.MainController;
import TwitterSearch.DataAccess.TwitterProxyImpl;
import TwitterSearch.Models.MainModel;
import TwitterSearch.Util.MessengerImpl;

import java.net.URL;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        final URL url = getClass().getResource("Views/main.fxml");
        final MainModel mainModel = new MainModel(
                new TwitterProxyImpl(
                        "DefftQ1LQbipBw1jVGSnw",
                        "BwjWBRsCZWgyjb6rzmxKRqZVlMJEupGQ5pE18MIAik",
                        "2196674683-XaIz4T5n1ZqsUe4PU8ffAEUktAWEpOsSlHrIgUW",
                        "s8MIjtr2apScTb02FtTIuqTon3tymZgl7pRBVh0VAaWei"),
                new MessengerImpl(primaryStage));

        FXMLLoader loader = new FXMLLoader();
        loader.setControllerFactory(new Callback<Class<?>, Object>() {
            @Override
            public Object call(Class<?> aClass) {
                return new MainController(mainModel);
            }
        });
        loader.setLocation(url);
        Parent root = (Parent) loader.load();

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 640, 400));
        primaryStage.show();
    }
}