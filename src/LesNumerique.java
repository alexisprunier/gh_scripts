import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.sql.*;
import java.text.SimpleDateFormat;
	
public class LesNumerique {
	
	// Variable Article
	private String titre;
	private Date date;
	private String image;
	private String origine;
	private String lien;
	private SimpleDateFormat sdf;

	// Variable Jsoup
	static private Document page_web = null;
	static private Document page_art = null;
	static private Element news = null;
	
	public LesNumerique(){
		this.titre = null;
		this.date = new java.sql.Date(System.currentTimeMillis());
		this.sdf = new SimpleDateFormat( "dd/MM/yy H:mm:ss" );
		this.origine = "LesNumeriques";
		this.lien = null;
	}
	
	public void getInfo(String nom_bdd, String user_bdd, String pass_bdd) throws IOException{
						
		page_web = Jsoup.connect("http://www.lesnumeriques.com/news/1").get();											

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
								
			//r�cup�ration du lien de la news complete
			this.lien = page_web.select("a[class*=cat_]").set(i,null).attr("abs:href");
			
			//R�cup�ration d'une news pour r�cuperer l'image de celle ci
			news = page_web.select(".wrap").set(i, null);
			image=news.select("img").first().attr("abs:src");
			
			//Connexion � la page de la news						
			page_art = Jsoup.connect(this.lien).get(); 
			
			//R�cuperation du titre
			titre = page_art.select("title").first().text();
			
			//Ajout dans la base de données
			
			try {
				statement.executeUpdate("INSERT INTO GeekHub_article VALUES (NULL,\""+titre+"\",\""+origine+"\", \""+lien+"\", \""+sdf.format(date)+"\", \""+image+"\", 0, 0);");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}		



