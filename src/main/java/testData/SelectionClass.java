package testData;

public class SelectionClass {
	
	public TestData testdata;
	
	public Object select(String website) {
		
		if(website.equalsIgnoreCase("kay")) {
			testdata = new KayTestData();
			return testdata;
		}
		else if(website.equalsIgnoreCase("kayoutlet")) {
			testdata = new KayOutletTestData();
			return testdata;
		}
		else if(website.equalsIgnoreCase("jared")) {
			testdata = new JaredTestData();
			return testdata;
		}
		else {
			System.out.println("Wrong Website Selection");
			return null;
		}
		
	}

}
