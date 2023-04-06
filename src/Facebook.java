import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

public class Facebook {
	
	private static String titre;
	private static String description;
	private static Date date;
	private static SimpleDateFormat sdf;
	private static String image;
	private static String origine;
	private static String lien;
	private static String page;
	private static String bdd;
	private static String user;
	private static String pass;
	
	//Variable Jsoup
	static private String xml = null;
	static private Document xmlDoc = null;

	public Facebook(String fb_page, String origine, String nom_bdd, String user_bdd, String pass_bdd){
		this.titre = null;
		this.description = null;
		this.date = new Date(System.currentTimeMillis());
		this.sdf = new SimpleDateFormat( "dd/MM/yy H:mm:ss" );
		this.image = null;
		this.origine = origine;
		this.lien = null;
		this.page = fb_page;
		this.bdd = nom_bdd;
		this.user = user_bdd;
		this.pass = pass_bdd;
	}
	
	public void getInfo() throws IOException {

		xml = Jsoup.connect(page).ignoreContentType(true).get().toString();
		xmlDoc = Jsoup.parse(xml, "", Parser.xmlParser());
		
		// Connection BDD
		
		String url = "jdbc:mysql://localhost:3306/";
		Connection conn = null;
		Statement statement = null;
		int id = 0;
		try {
			  Class.forName("com.mysql.jdbc.Driver").newInstance();
			  conn = DriverManager.getConnection(url+bdd,user,pass);
			  System.out.println("Connected to the database for FB");
			  statement = conn.createStatement();
			  ResultSet rs = statement.executeQuery("SELECT id FROM GeekHub_facebook");
			  if(rs.last()){
				  id=rs.getInt("id")+1;
			  }
		} catch (Exception e) {
				e.printStackTrace();
		}
		
		// Parcours de tous les articles
		
		for(int i=0 ; i<Main.nbInfos ; i++){
			titre = xmlDoc.select("title").set(i+1, null).text();
			description = xmlDoc.select("description").set(i+1, null).text();
			description = description.replaceAll("\"", "'");
			lien = xmlDoc.select("link").set(i+1, null).text();

			try {
				statement.executeUpdate("INSERT INTO GeekHub_facebook VALUES (NULL,\""+titre+"\",\""+origine+"\", \""+description+"\", \""+lien+"\", \""+sdf.format(date)+"\");");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}