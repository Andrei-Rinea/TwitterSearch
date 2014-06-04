package TwitterSearch.Models;

import javafx.beans.property.LongProperty;
import javafx.beans.property.ReadOnlyLongWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.StringProperty;
import twitter4j.Status;

public class TweetModel {
    private StringProperty usernameProperty;
    private StringProperty textProperty;
    private StringProperty userScreenNameProperty;
    private LongProperty idProperty;


    public TweetModel(Status tweet) {
        usernameProperty = new ReadOnlyStringWrapper(tweet.getUser().getName());
        textProperty = new ReadOnlyStringWrapper(tweet.getText());
        userScreenNameProperty = new ReadOnlyStringWrapper(tweet.getUser().getScreenName());
        idProperty = new ReadOnlyLongWrapper(tweet.getId());
    }

    public String getUsername() {
        return this.usernameProperty.get();
    }

    public StringProperty usernameProperty() {
        return usernameProperty;
    }

    public String getText() {
        return textProperty.get();
    }

    public StringProperty textProperty() {
        return textProperty;
    }

    public String getUserScreenName() {
        return userScreenNameProperty.get();
    }

    public StringProperty userScreenNameProperty() {
        return userScreenNameProperty;
    }

    public long getId() {
        return idProperty.get();
    }
}