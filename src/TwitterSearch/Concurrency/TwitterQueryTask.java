package TwitterSearch.Concurrency;

import TwitterSearch.DataAccess.TwitterProxy;
import TwitterSearch.Models.TweetModel;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import twitter4j.Status;

import java.util.List;

public class TwitterQueryTask extends Task<TweetModel[]> {

    private final TwitterProxy twitterProxy;
    private final String queryText;

    public TwitterQueryTask(
            final TwitterProxy twitterProxy,
            final String queryText,
            final EventHandler<TwitterQuerySucceedEvent> onSucceeded) {

        this.twitterProxy = twitterProxy;
        this.queryText = queryText;

        setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent workerStateEvent) {
                try {
                    TweetModel[] tweets = (TweetModel[]) ((Task) workerStateEvent.getSource()).get();
                    onSucceeded.handle(new TwitterQuerySucceedEvent(queryText, tweets));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected TweetModel[] call() throws Exception {
        List<Status> tweets = twitterProxy.query(queryText);
        TweetModel[] models = new TweetModel[tweets.size()];
        for (int i = 0; i < models.length; i++) {
            models[i] = new TweetModel(tweets.get(i));
        }
        return models;
    }
}