package TwitterSearch.Concurrency;

import javafx.event.Event;
import javafx.event.EventType;
import TwitterSearch.Models.TweetModel;

public class TwitterQuerySucceedEvent extends Event {
    private final String queryText;
    private final TweetModel[] tweets;

    private final static EventType<TwitterQuerySucceedEvent> type = new EventType<>();

    public TwitterQuerySucceedEvent(String queryText, TweetModel[] tweets) {
        super(type);
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