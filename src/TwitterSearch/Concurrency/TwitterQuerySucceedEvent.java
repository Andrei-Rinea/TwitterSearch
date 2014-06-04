package TwitterSearch.Concurrency;

import javafx.event.Event;
import javafx.event.EventType;
import TwitterSearch.Models.TweetModel;

public class TwitterQuerySucceedEvent extends Event {
    private final String queryText;
    private final TweetModel[] tweets;

    public TwitterQuerySucceedEvent(String queryText, TweetModel[] tweets) {
        super(new EventType<TwitterQuerySucceedEvent>());
        this.queryText = queryText;
        this.tweets = tweets;
    }

    public TweetModel[] getTweets() {
        return tweets;
    }

    public String getQueryText() {
        return queryText;
    }
}