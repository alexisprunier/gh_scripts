import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Map;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;

public class Facebook {
	
	private static String titre;
	private static Date date;
	private static SimpleDateFormat sdf;
	private static String image;
	private static String logo;
	private static String origine;
	private static String lien;
	private static String page;
	
	//Variable Jsoup
	static private Document xml = null;
	static private Document xmlDoc = null;

	public Facebook(String fb_page, String origine){
		this.titre = null;
		this.date = new Date(System.currentTimeMillis());
		this.sdf = new SimpleDateFormat( "dd/MM/yy H:mm:ss" );
		this.image = null;
		this.logo = null ;
		this.origine = origine;
		this.lien = null;
		this.page = fb_page;
	}
	

	public void getInfo() throws IOException {
		
		xml = Jsoup.connect(page).get();
		//xmlDoc = Jsoup.parse(xml, "", Parser.xmlParser());
		
		// Connection BDD
		
		/*String url = "jdbc:mysql://localhost:3306/";
		String dbName = "GeekHubDB";
		String userName = "root"; 
		String password = "ghct2lb";
		Connection conn = null;
		Statement statement = null;
		int id = 0;
		try {
			  Class.forName("com.mysql.jdbc.Driver").newInstance();
			  conn = DriverManager.getConnection(url+dbName,userName,password);
			  System.out.println("Connected to the database");
			  statement = conn.createStatement();
			  ResultSet rs = statement.executeQuery("SELECT id FROM GeekHubWeb_facebook");
			  if(rs.last()){
				  id=rs.getInt("id")+1;
			  }
		} catch (Exception e) {
				e.printStackTrace();
		}
		
		// Parcours de tous les articles
		*/
		for(int i=0 ; i<Main.nbInfos ; i++){
			
			logo = xmlDoc.select("logo").set(i, null).text();
			System.out.println(logo);
			
		
			//Ajout dans la base de données
			/*
			try {
				statement.executeUpdate("INSERT INTO GeekHubWeb_facebook VALUES (\"\",\""+titre+"\",\""+origine+"\", \""+lien+"\", \""+sdf.format(date)+"\", \""+image+"\");");
			} catch (SQLException e) {
				e.printStackTrace();
			}*/
		}
		

	}

}
