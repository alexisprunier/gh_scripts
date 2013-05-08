import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.sql.*;
import java.text.SimpleDateFormat;
	
public class Hardware {
	
	private String titre;
	private Date date;
	private String image;
	private String origine;
	private String lien;
	private SimpleDateFormat sdf;
	
	//Variable Jsoup
	static private Document page_web = null;
	static private Element news = null;
	
	public Hardware(){
		this.titre = null;
		this.date = new Date(System.currentTimeMillis());
		this.sdf = new SimpleDateFormat( "dd/MM/yy H:mm:ss" );
		this.origine = "Hardware";
		this.lien = null;
		this.news = null;
	}
	
	public void getInfo(String nom_bdd, String user_bdd, String pass_bdd) throws IOException{
						
		page_web = Jsoup.connect("http://www.hardware.fr/html/news/").get();
		
		// Connection BDD
		
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = nom_bdd;
		String userName = user_bdd; 
		String password = pass_bdd;
		Connection conn = null;
		Statement statement = null;
		int id = 0;
		try {
			  Class.forName("com.mysql.jdbc.Driver").newInstance();
			  conn = DriverManager.getConnection(url+dbName,userName,password);
			  System.out.println("Connected to the database");
			  statement = conn.createStatement();
			  ResultSet rs = statement.executeQuery("SELECT id FROM GeekHub_article");
			  if(rs.last()){
				  id=rs.getInt("id")+1;
			  }
		} catch (Exception e) {
				e.printStackTrace();
		}
		
		// Parcours de tous les articles
			
		for(int i=0 ; i<Main.nbInfos ; i++){
			news = page_web.select(".content_actualite.clearfix").set(i,null);					
			//R�cup�ration du titre de la news
			this.titre = page_web.select("h2.title_index").set(i,null).text();	
			//R�cup�ration du lien de la news
			this.lien = page_web.select("h2.title_index > a").set(i,null).attr("abs:href");
			//R�cup�ration de la premiere image de la news
			this.image = news.select("img").first().attr("abs:src");
			
		
			//Ajout dans la base de données
			
			try {
				statement.executeUpdate("INSERT INTO GeekHub_article VALUES (NULL,\""+titre+"\",\""+origine+"\", \""+lien+"\", \""+sdf.format(date)+"\", \""+image+"\");");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
}		



