import java.io.IOException;


public class Main {
	

	final static int nbInfos = 2;
	final static String nom_bdd = "gh";
	final static String user_bdd = "root";
	final static String pass_bdd = "anthony";

	public static void main(String[] args) throws IOException {
		
		//////// ARTICLES

		LesNumerique lesNums = new LesNumerique();
		lesNums.getInfo(nom_bdd,user_bdd,pass_bdd);
		
		O1Net net = new O1Net();
		net.getInfo(nom_bdd,user_bdd,pass_bdd);
		
		TomsGuide tom = new TomsGuide();
		tom.getInfo(nom_bdd,user_bdd,pass_bdd);
		
		LeComptoirDuHardware compt = new LeComptoirDuHardware();
		compt.getInfo(nom_bdd,user_bdd,pass_bdd);
		
		Hardware hardware = new Hardware();
		hardware.getInfo(nom_bdd,user_bdd,pass_bdd);
		
		//////// FACEBOOK
		
		//Facebook f1 = new Facebook( "http://www.facebook.com/feeds/page.php?format=rss20&id=320776853364", "Materiel.net");
		//f1.getInfo();
	}
}
