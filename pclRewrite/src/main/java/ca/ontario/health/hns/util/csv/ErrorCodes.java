/**
 *
 */
package ca.ontario.health.hns.util.csv;


public abstract class ErrorCodes {
	private ErrorCodes() {
	}

	/**
	 * APPLICATION_NUMBER_IS_NULL.
	 */
	public static final String APPLICATION_NUMBER_IS_NULL = "APPLICATION_NUMBER_IS_NULL";

	/**
	 * ODB_ELIG_NO_IS_NULL.
	 */
	public static final String ODB_ELIG_NO_IS_NULL = "ODB_ELIG_NO_IS_NULL";

	/**
	 * NO_LAST_UPDATE_ID_PROVIDED.
	 */
	public static final String NO_LAST_UPDATE_ID_PROVIDED = "NO_LAST_UPDATE_ID_PROVIDED";

	/**
	 * REC_CREATE_USERID_IS_NULL.
	 */
	public static final String REC_CREATE_USERID_IS_NULL = "REC_CREATE_USERID_IS_NULL";

	/**
	 * RECINACTIVE_ATTR_SET_FOR_SAVING.
	 */
	public static final String RECINACTIVE_ATTR_SET_FOR_SAVING = "RECINACTIVE_ATTR_SET_FOR_SAVING";

	/**
	 * NO_RECINACTIVE_ID_PROVIDED_FOR_DEL.
	 */
	public static final String NO_RECINACTIVE_ID_PROVIDED_FOR_DEL = "NO_RECINACTIVE_ID_PROVIDED_FOR_DEL";

	/**
	 * DATABASE_ERROR.
	 */
	public static final String DATABASE_ERROR = "DATABASE_ERROR";

	/**
	 * REQUIRED_PARAMETER_IS_NULL.
	 */
	public static final String REQUIRED_PARAMETER_IS_NULL = "REQUIRED_PARAMETER_IS_NULL";

	/**
	 * INVALID_ODB_ELIG_NO.
	 */
	public static final String INVALID_ODB_ELIG_NO = "INVALID_ODB_ELIG_NO";

	/**
	 * WRONG_NOTE_TYPE.
	 */
	public static final String WRONG_NOTES_TYPE = "WRONG_NOTE_TYPE";

	/**
	 * NEW_OR_UPDATE_NOTE_CONTAINS_REC_INACTIVE_ATTRIBUTES.
	 */
	public static final String NEW_OR_UPDATE_NOTE_CONTAINS_REC_INACTIVE_ATTRIBUTES = "NEW_OR_UPDATE_NOTE_CONTAINS_REC_INACTIVE_ATTRIBUTES";

	/**
	 * INVALID_ARGUMENT
	 */
	public static final String INVALID_ARGUMENT = "INVALID_ARGUMENT";

	/**
	 * APP_NUMBER_NOT_FOUND
	 */

	public static final String APP_NUMBER_NOT_FOUND = "APP_NUMBER_NOT_FOUND";

	/**
	 * LAST_USED_APP_NUMBER_NOT_FOUND
	 */

	public static final String LAST_USED_APP_NUMBER_NOT_FOUND = "LAST_USED_APP_NUMBER_NOT_FOUND";

	/**
	 * LAST_USED_APP_NUMBER_NOT_FOUND
	 */

	public static final String BATCH_OF_LABELS_NOT_FOUND = "BATCH_OF_LABELS_NOT_FOUND";

	/**
	 * No {0} were found.~~Information~~OK~~1~~Produce Application Labels
	 */
	public static final String PL03 = "PL03";

	/**
	 * LAST_USED_APP_NUMBER_NOT_FOUND
	 */

	public static final String NOT_SERVICE_AVAILABLE = "NOT_SERVICE_AVAILABLE";

	/**
	 * GENERIC_EXCEPTION
	 */

	public static final String GENERIC_EXCEPTION = "GENERIC_EXCEPTION";

	/**
	 * INVALID_STATUS
	 */

	public static final String INVALID_STATUS = "INVALID_STATUS";

	/**
	 * MISSING_DOCUMENT_PARAMETERS
	 */

	public static final String MISSING_DOCUMENT_PARAMETERS = "MISSING_DOCUMENT_PARAMETERS";

	/**
	 * UNKNOWN_DOCUMENT
	 */
	public static final String UNKNOWN_DOCUMENT = "UNKNOWN_DOCUMENT";

	/**
	 * UNSUPPORTED_DOCUMENT_FORMAT
	 */
	public static final String UNSUPPORTED_DOCUMENT_FORMAT = "UNSUPPORTED_DOCUMENT_FORMAT";

	/**
	 * INVALID_BENEFIT_YEAR
	 */
	public static final String INVALID_BENEFIT_YEAR = "INVALID_BENEFIT_YEAR";

	/**
	 * CLAIM_NOT_FOUND
	 */
	public static final String CLAIM_NOT_FOUND = "CLAIM_NOT_FOUND";

	/**
	 * RECEIPT_NOT_FOUND
	 */
	public static final String RECEIPT_NOT_FOUND = "RECEIPT_NOT_FOUND";

	/**
	 * RECIPIENT_NOT_FOUND
	 */
	public static final String RECIPIENT_NOT_FOUND = "RECIPIENT_NOT_FOUND";

	/**
	 * ODB_NUMBER_NOT_FOUND
	 */
	public static final String ODB_NUMBER_NOT_FOUND = "ODB_NUMBER_NOT_FOUND";

	/**
	 * OVERPAID_NOT_FOUND
	 */
	public static final String OVERPAID_NOT_FOUND = "OVERPAID_NOT_FOUND";

	/**
	 * CONTACT_NOT_FOUND
	 */
	public static final String CONTACT_NOT_FOUND = "CONTACT_NOT_FOUND";

	/**
	 * INVALID_STATUS_CHANGE
	 */
	public static final String INVALID_STATUS_CHANGE = "INVALID_STATUS_CHANGE";

	/**
	 * NULL_PROGRAM_GROUP
	 */
	public static final String NULL_PROGRAM_GROUP = "NULL_PROGRAM_GROUP";

	/**
	 * STALE_OBJECT_VERSION
	 */
	public static final String STALE_OBJECT_VERSION = "STALE_OBJECT_VERSION";

	/**
	 * GN04=As this is the first run of New Application Numbers to be generated for the {0} benefit year, 
	 * please enter the first Application Number to be generated.~~Information~~OK~~1~~First Run for Specified Benefit Year
	 */
	public static final String GN04 = "GN04";

	/**
	 * the requested DML operation resulted in a violation of a defined
	 * integrity constraint.
	 */
	public static final String E0001 = "E0001";
	
	/**
	 * MESSAGE_CODETABLE_NOT_FOUND
	 */
	public static final String MESSAGE_CODETABLE_NOT_FOUND = "MESSAGE_CODETABLE_NOT_FOUND";
	
	/**
	 * NOTETEXT_CODETABLE_NOT_FOUND
	 */
	public static final String NOTETEXT_CODETABLE_NOT_FOUND = "NOTETEXT_CODETABLE_NOT_FOUND";
	
	/**
	 * MN06 - Recipient not found.~~Information~~OK~~1~~Recipient Search
	 */
	public static final String MN06 = "MN06";
}
