import java.io.IOException;
import java.util.logging.*;

public class Main {
	
	final static int nbInfos = 2;
	final static String nom_bdd = "GeekHubDatabase";
	final static String user_bdd = "alexis";
	final static String pass_bdd = "ghct2lb";

	public static void main(String[] args) throws IOException {
		
		Logger logger = Logger.getLogger("logger");
		FileHandler fh=new FileHandler();
		logger.addHandler(fh);
		
		//////// ARTICLES
		
		try{
			LesNumerique lesNums = new LesNumerique();
			lesNums.getInfo(nom_bdd,user_bdd,pass_bdd);
		}catch(Exception e){
			logger.log(Level.INFO, "ARTICLE LES NUMERIQUES -- "+e.getMessage());
		}
		
		try{
			O1Net net = new O1Net();
			net.getInfo(nom_bdd,user_bdd,pass_bdd);
		}catch(Exception e){
			logger.log(Level.INFO, "ARTICLE 01NET -- "+e.getMessage());
		}
		
		try{
			TomsGuide tom = new TomsGuide();
			tom.getInfo(nom_bdd,user_bdd,pass_bdd);
		}catch(Exception e){
			logger.log(Level.INFO, "ARTICLE TOMS GUIDE -- "+e.getMessage());
		}
		
		try{
			LeComptoirDuHardware compt = new LeComptoirDuHardware();
			compt.getInfo(nom_bdd,user_bdd,pass_bdd);
		}catch(Exception e){
			logger.log(Level.INFO, "ARTICLE COMPTOIR DU HARDWARE -- "+e.getMessage());
		}
		
		try{
			Hardware hardware = new Hardware();
			hardware.getInfo(nom_bdd,user_bdd,pass_bdd);
		}catch(Exception e){
			logger.log(Level.INFO, "ARTICLE HARDWARE -- "+e.getMessage());
		}
		
		
		//////// FACEBOOK
		try{
			Facebook f1 = new Facebook("http://www.facebook.com/feeds/page.php?format=rss20&id=320776853364", "Materiel.net", nom_bdd, user_bdd, pass_bdd);
			f1.getInfo();
		}catch(Exception e){
			logger.log(Level.INFO, "FACEBOOK MATERIEL -- "+e.getMessage());
		}
		
		try{
			Facebook f2 = new Facebook("http://www.facebook.com/feeds/page.php?format=rss20&id=121548574526465", "LDLC", nom_bdd, user_bdd, pass_bdd);
			f2.getInfo();
		}catch(Exception e){
			logger.log(Level.INFO, "FACEBOOK LDLC -- "+e.getMessage());		
		}
		
		try{
			Facebook f3 = new Facebook("http://www.facebook.com/feeds/page.php?format=rss20&id=119794289202", "Rue du Commerce", nom_bdd, user_bdd, pass_bdd);
			f3.getInfo();
		}catch(Exception e){
			logger.log(Level.INFO, "FACEBOOK RUE DU COMMERCE -- "+e.getMessage());		
		}
		
		try{
			Facebook f4 = new Facebook("http://www.facebook.com/feeds/page.php?format=rss20&id=123427650425", "Top Achat", nom_bdd, user_bdd, pass_bdd);
			f4.getInfo();
		}catch(Exception e){
			logger.log(Level.INFO, "FACEBOOK TOPACHAT -- "+e.getMessage());		
		}
		try{
			Facebook f4 = new Facebook("http://www.facebook.com/feeds/page.php?format=rss20&id=312422015254", "GrosBill", nom_bdd, user_bdd, pass_bdd);
			f4.getInfo();
		}catch(Exception e){
			logger.log(Level.INFO, "FACEBOOK GROSBILL -- "+e.getMessage());		
		}
		
		
		//////////// TWITTER

		Twitter2 t1 = new Twitter2(nom_bdd, user_bdd, pass_bdd);
		try{t1.getInfo("pressecitron");}catch(Exception e){logger.log(Level.INFO, "TWITTER -- "+e.getMessage());}
		try{t1.getInfo("gchampeau");}catch(Exception e){logger.log(Level.INFO, "TWITTER -- "+e.getMessage());}
		try{t1.getInfo("Ulrichfr");}catch(Exception e){logger.log(Level.INFO, "TWITTER -- "+e.getMessage());}
		try{t1.getInfo("JeromeColombain");}catch(Exception e){logger.log(Level.INFO, "TWITTER -- "+e.getMessage());}
		try{t1.getInfo("fleurpellerin");}catch(Exception e){logger.log(Level.INFO, "TWITTER -- "+e.getMessage());}
		try{t1.getInfo("altolabs");}catch(Exception e){logger.log(Level.INFO, "TWITTER -- "+e.getMessage());}
		try{t1.getInfo("Locita");}catch(Exception e){logger.log(Level.INFO, "TWITTER -- "+e.getMessage());}
		try{t1.getInfo("isabellemathieu");}catch(Exception e){logger.log(Level.INFO, "TWITTER -- "+e.getMessage());}
	}
}
