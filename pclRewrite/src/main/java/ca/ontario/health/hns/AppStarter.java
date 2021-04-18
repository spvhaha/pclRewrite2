/**
 * 
 */
package ca.ontario.health.hns;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ca.ontario.health.hns.domain.SCPRejection;
import ca.ontario.health.hns.util.csv.CustomCsvProcessor;
import ca.ontario.health.hns.util.csv.CustomCsvReader;


@Component
public class AppStarter implements CommandLineRunner {

	@Override
	public void run(String... arg0) throws Exception {
		
		/*String file = "src/main/resources/sample-data.csv";
		List<String> ord = new ArrayList<String>();
		ord.add("id");
		ord.add("name");
		ord.add("series");*/

		// String file = "src/main/resources/Scprejl2009140150.txt";  // with char 159
		 String file = "src/main/resources/Scprejl2009110213.txt";  // with comma


		/*"rec_Type='" + rec_Type + '\'' +
				", appNum='" + appNum + '\'' +
				", contactName='" + contactName + '\'' +
				", lang='" + lang + '\'' +
				", add1='" + add1 + '\'' +
				", add2='" + add2 + '\'' +
				", city='" + city + '\'' +
				", prov='" + prov + '\'' +
				", postCode='" + postCode + '\'' +
				", country='" + country + '\'' +
				", benefitYear='" + benefitYear + '\'' +
				", memCount='" + memCount + '\'' +
				", covEndDate='" + covEndDate + '\'' +
				", thersholdAmt='" + thersholdAmt + '\'' +
				", deductAmt='" + deductAmt + '\'' +
				", probCodes='" + probCodes + '\'' +
				'}';*/


	}

}
