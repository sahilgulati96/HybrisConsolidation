package testData;

public class KayOutletTestData implements TestData{
	
	public String[] logincredentials() {
		String lc[] = new String[2];
		lc[0]="sahiltest3@gmail.com";
		lc[1]="Password@123";
		return lc;
		
	}

	public String[] orderstatusdata() {
		String osd[] = new String[2];
		osd[0]="sahiltest3@gmail.com";
		osd[1] = "80000000035084";
		return osd;
	}
	
	public String[] Wishlistlogincredentials() {
		String lc[] = new String[2];
		lc[0]="sahiltest2@gmail.com";
		lc[1]="Password@12345";
		return lc;
		
	}

	public String ESPProduct() {
		// TODO Auto-generated method stub
		return "999111354003";
	}

	public String JRPProduct() {
		// TODO Auto-generated method stub
		return "999111412805";
	}

	public String nonESPAndJRPProduct() {
		// TODO Auto-generated method stub
		return "999132982404";
	}

	public String searchterm() {
		// TODO Auto-generated method stub
		return "bracelet";
	}

	public String invalidPromocode() {
		// TODO Auto-generated method stub
		return "invalidpromocode";
	}

}
