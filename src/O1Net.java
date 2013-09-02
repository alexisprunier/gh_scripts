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

public class O1Net {

		private String lien;
		private Date date;
		private String origine;
		private String titre;
		private String image;
		private SimpleDateFormat sdf;
				
		
		//Variable Jsoup
		static private Document page=null;
		
		
		public O1Net() 
		{
			this.lien = null;
			this.date = new Date(System.currentTimeMillis());
			this.sdf = new SimpleDateFormat( "dd/MM/yy H:mm:ss" );
			this.origine = "01net";
			this.titre = null;
		}
		
		public void getInfo(String nom_bdd, String user_bdd, String pass_bdd) throws IOException
		{
					
			int curseur_info =0;
			int nb_clientVu=0;
								
			page = Jsoup.connect("http://www.01net.com/rub/actualites/10001/").get();
			
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
			
//*****************R�cuperation des informations de base****************************
								
			
			for(int i=0;i<Main.nbInfos; i++)
			{
				//r�cup�ration du lien de la news complete
				this.lien = page.select(".Actu_Titre").set(i, null).attr("abs:href");
					
				//R�cuperation du titre
				this.titre = page.select(".Actu_Titre").set(i, null).text();
									
				//R�cup�ration de l'image 
				this.image = page.select(".lazy").set(i,null).attr("abs:data-original");
				
				try {
					statement.executeUpdate("INSERT INTO GeekHub_article VALUES (NULL,\""+titre+"\",\""+origine+"\", \""+lien+"\", \""+sdf.format(date)+"\", \""+image+"\", 0, 0);");
				} catch (SQLException e) {
					e.printStackTrace();
				}
								
			}						
		}

}		






