package TwitterSearch.DataAccess;

import twitter4j.Status;
import twitter4j.TwitterException;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public interface TwitterProxy {
    List<Status> query(String queryText) throws TwitterException;
    URI getTweetURI(String userScreenName, long statusId) throws URISyntaxException;
}
