package com.clone.demo.shyp;

import java.util.HashMap;
import java.util.Map;

public class Ipsum {

	static Map<String, String> serverUrls = new HashMap<String,String>();

    //final static String BASE_URL = "http://192.168.1.14/shyp_android/api.php?action=";
    final static String BASE_URL = "http://192.168.1.14/shyp_android/test.php?action=";
    final static String LOGIN = "http://192.168.1.14/ShypService/api/v1/apiLogin";

	public static Map<String, String> getServerUrls() {

		serverUrls.put("login",BASE_URL+"login");
		serverUrls.put("fetch_recipient",BASE_URL+"fetch_recipient");

		serverUrls.put("pst_local","http://10.0.3.2/~admin/Test/test.php");

		return serverUrls;

	}// end method getServerUrls

    final static String shipment_array = "[{\"address\":\"Magdalen \\n\\nHouse 134 Tooley \\nStreet London \\nSE1 2TU\",\"more_address\":\"Economy, 3-4 Days\"},{\"address\":\"Magdalen \\n\\nHouse 126 Tooley \\nStreet London \\nSE1 2TU\",\"more_address\":\"Economy, 3-5 Days\"},{\"address\":\"Magdalen \\n\\nHouse 130 Tooley \\nStreet London \\nSE1 2TU\",\"more_address\":\"Economy, 2-4 Days\"},{\"address\":\"Magdalen \\n\\nHouse 96 Tooley \\nStreet London \\nSE1 2TU\",\"more_address\":\"Economy, 1-4 Days\"},{\"address\":\"Magdalen \\n\\nHouse 07 Tooley \\nStreet London \\nSE1 2TU\",\"more_address\":\"Economy, 1-6 Days\"}]";


}// end Ipsum
