import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;


public class Twitter2 {
	
	private static SimpleDateFormat sdf;
	private static Date date;
	private static String bdd;
	private static String user;
	private static String pass;
	private static final String TWITTER_CONSUMER_KEY = "KDJJd6V8mJdO1y48RRpA";
	private static final String TWITTER_SECRET_KEY = "MOnyneRlFrL96X5DsDAigaR8c76YGA3obcPjMwO0";
	private static final String TWITTER_ACCESS_TOKEN = "1093205246-RNmJpbAE273s1x1prqEeeTWN5juhO9D1mNRXdFB";
	private static final String TWITTER_ACCESS_TOKEN_SECRET = "tHj1MYyUJli9Qba0gHvKGI9cpAcLS8SNIKYh2FCRD0";

	
	public Twitter2(String nom_bdd, String user_bdd, String pass_bdd){
		this.date = new Date(System.currentTimeMillis());
		this.sdf = new SimpleDateFormat( "dd/MM/yy H:mm:ss" );
		this.bdd = nom_bdd;
		this.user = user_bdd;
		this.pass = pass_bdd;
	}
	
	public void getInfo(String twit_acc) throws IOException {
		
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = bdd;
		String userName = user; 
		String password = pass;
		Connection conn = null;
		Statement statement = null;
		
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		    .setOAuthConsumerKey(TWITTER_CONSUMER_KEY)
		    .setOAuthConsumerSecret(TWITTER_SECRET_KEY)
		    .setOAuthAccessToken(TWITTER_ACCESS_TOKEN)
		    .setOAuthAccessTokenSecret(TWITTER_ACCESS_TOKEN_SECRET);
		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter = (Twitter) tf.getInstance();
		try {
		    Query query = new Query(twit_acc);
		    QueryResult result;
	        result = twitter.search(query);
	        List<Status> tweets = twitter.getUserTimeline(twit_acc);
	        for (Status tweet : tweets) {
				try {
					Class.forName("com.mysql.jdbc.Driver").newInstance();
					conn = DriverManager.getConnection(url+dbName,userName,password);
					System.out.println("Connected to the database for TW");
					statement = conn.createStatement();
					statement.executeUpdate("INSERT INTO GeekHub_twitter (titre,origine,date) VALUES (\""+tweet.getText()+"\",\""+tweet.getUser().getScreenName()+"\", \""+sdf.format(date)+"\");");
				} catch (Exception e) {
					e.printStackTrace();
					break;
				}
	        }
		} catch (TwitterException te) {
		    te.printStackTrace();
		    System.out.println("Failed to search tweets: " + te.getMessage());
		}
	}
}