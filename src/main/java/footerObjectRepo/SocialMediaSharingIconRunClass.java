package footerObjectRepo;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;

public class SocialMediaSharingIconRunClass {
	@Factory(dataProvider="websitename")
	public Object[] factory(String website)
	
	{
		Object obj[] = new Object[1];
				
			//obj[0]=  new SocialMediaSharingIconCodeClass(website);
		
		
		return obj;
		
	}
	
	@DataProvider
	public String[] websitename()
	{
		String name[] = new String[3];
		name[0]="jared";
		name[1]="kay";
		name[2]="kayoutlet";
		return name;
}

}
