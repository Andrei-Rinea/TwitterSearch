package TwitterSearch.DataAccess;

import twitter4j.*;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public class TwitterProxyImpl implements TwitterProxy {
    private Twitter twitter;
    /* username : T4J_1
     * password : SomePassword
    **/

    public TwitterProxyImpl(String consumerKey, String consumerSecret, String accessToken, String accessTokenSecret) {

        buildTwitterInstance(consumerKey, consumerSecret, accessToken, accessTokenSecret);
    }

    @Override
    public List<Status> query(String queryText) throws TwitterException {
        return twitter.search(new Query(queryText)).getTweets();
    }

    @Override
    public URI getTweetURI(String userScreenName, long statusId) throws URISyntaxException {
        return new URI("https://twitter.com/" + userScreenName + "/status/" + statusId);
    }

    private void buildTwitterInstance(String consumerKey, String consumerSecret, String accessToken, String accessTokenSecret) {
        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
        configurationBuilder.setOAuthConsumerKey(consumerKey);
        configurationBuilder.setOAuthConsumerSecret(consumerSecret);
        configurationBuilder.setOAuthAccessToken(accessToken);
        configurationBuilder.setOAuthAccessTokenSecret(accessTokenSecret);
        Configuration cfg = configurationBuilder.build();
        TwitterFactory factory = new TwitterFactory(cfg);
        twitter = factory.getInstance();
    }
}