/**
 * 
 */
package ca.ontario.health.hns.util.csv;

import ca.ontario.health.hns.builder.LetterBuilder;
import ca.ontario.health.hns.common.ByteWrapper;
import ca.ontario.health.hns.domain.SCPRejection;


public class CustomCsvProcessor implements CsvProcessor<SCPRejection> {

	/*@Override
	public CsvData process(CsvData inData) {
		inData.setName(inData.getName().toUpperCase());
		return inData;
	}*/

	@Override
	public SCPRejection process(SCPRejection inData) throws Exception {
		//inData.setContactName(inData.getContactName().toUpperCase());
		LetterBuilder ltbuild= new LetterBuilder();
		ByteWrapper btw = ltbuild.generateSCPRejectionLetter(inData)   ;

		return inData;
	}


}
