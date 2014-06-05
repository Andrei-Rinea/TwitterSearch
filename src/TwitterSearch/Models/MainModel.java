package TwitterSearch.Models;
import TwitterSearch.Concurrency.TwitterQueryTask;
import TwitterSearch.DataAccess.TwitterProxy;
import TwitterSearch.Util.ActionHandler;
import TwitterSearch.Util.Messenger;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class MainModel {

    private final TwitterProxy twitterProxy;
    private final Messenger messenger;

    private final StringProperty queryTextProperty;
    private final ObjectProperty<EventHandler<ActionEvent>> queryActionProperty;
    private final ObjectProperty<EventHandler<ActionEvent>> stopActionProperty;
    private final BooleanProperty queryActionDisabledProperty;
    private final BooleanProperty busyProperty;
    private final ListPropertyBase<TweetModel> resultsProperty;

    private Service<TweetModel[]> service;
    private ActionHandler<Object> itemActionHandler;

    public MainModel(TwitterProxy twitterProxy, Messenger messenger) {
        this.twitterProxy = twitterProxy;
        this.messenger = messenger;

        queryTextProperty = new SimpleStringProperty("");
        queryActionDisabledProperty = new SimpleBooleanProperty(true);
        busyProperty = new SimpleBooleanProperty(false);
        resultsProperty = new SimpleListProperty<>(FXCollections.<TweetModel>observableArrayList());

        queryActionProperty = new SimpleObjectProperty<>(actionEvent -> onQuery());
        stopActionProperty = new SimpleObjectProperty<>(actionEvent -> onStop());

        queryTextProperty.addListener((observableValue, s, s2) -> {
            String queryText = queryTextProperty.get();
            queryActionDisabledProperty.setValue(queryText == null || queryText.isEmpty());
        });

        itemActionHandler = argument -> onItemAction((TweetModel) argument);
    }

    private void onItemAction(TweetModel tweet) {
        try {
            URI tweetURL = twitterProxy.getTweetURI(tweet.getUserScreenName(), tweet.getId());
            Desktop desktop = Desktop.getDesktop();
            desktop.browse(tweetURL);
        } catch (URISyntaxException | IOException | UnsupportedOperationException e) {
            messenger.displayMessage("Cannot open tweet", "Error");
        }
    }

    private void onQuery() {
        busyProperty.set(true);
        service = new Service<TweetModel[]>() {
            @Override
            protected Task<TweetModel[]> createTask() {
                return new TwitterQueryTask(twitterProxy, queryTextProperty.get(), event -> {
                    resultsProperty.setAll(event.getTweets());
                    busyProperty.set(false);
                });
            }
        };
        service.start();
    }

    private void onStop() {
        service.cancel();
        busyProperty.set(false);
        resultsProperty.clear();
        queryTextProperty.set("");
    }

    public StringProperty queryTextProperty() {
        return queryTextProperty;
    }

    public ObjectProperty<EventHandler<ActionEvent>> queryActionProperty() {
        return queryActionProperty;
    }

    public ObjectProperty<EventHandler<ActionEvent>> stopActionProperty() {
        return stopActionProperty;
    }

    public BooleanProperty queryActionDisabledProperty() {
        return queryActionDisabledProperty;
    }

    public ListPropertyBase<TweetModel> resultsProperty() {
        return resultsProperty;
    }

    public BooleanProperty busyProperty() {
        return busyProperty;
    }

    public ActionHandler<Object> getItemActionHandler() {
        return itemActionHandler;
    }
}