package ca.ontario.health.hns.common;

public interface DocumentServiceCodes {
	public static final String DOC_FORMAT_PDF = "doc.format.pdf";
	public static final String DOC_FORMAT_RTF = "doc.format.rtf";

	public static final String DOC_TYPE_APP_NUM_CONTROL_REPORT = "app.num.control.report";
	public static final String DOC_TYPE_APP_NUM_BARCODE_LABELS = "app.num.barcode.labels";
	public static final String DOC_TYPE_SCP_CONFIRMATION_LETTER = "SRCOPAY.Confirmation";
	public static final String DOC_TYPE_SCP_PRNL_LETTER = "scp.prnl.letter";
	public static final String DOC_TYPE_SCP_PROBLEM_LETTER = "SRCOPAY.Problem";
	public static final String DOC_TYPE_SCP_REJECTION_LETTER = "SRCOPAY.Reject";
	public static final String DOC_TYPE_TDP_2_COPAY_LETTER = "tdp.2.copay.letter";
	public static final String DOC_TYPE_TDP_CONFIRMATION_LETTER = "TRILLIUM.Confirmation";
	public static final String DOC_TYPE_TDP_NON_UTILIZATION_LETTER = "tdp.non.utilization.letter";
	public static final String DOC_TYPE_TDP_PRNL_LETTER = "tdp.prnl.letter";
	public static final String DOC_TYPE_TDP_PROBLEM_LETTER = "TRILLIUM.Problem";
	public static final String DOC_TYPE_TDP_REJECTION_LETTER = "TRILLIUM.Reject";

	public static final String REGISTRATION_REPORT = "registration.report";
	public static final String PRIVATE_INSURANCE_REPORT = "private.insurance.report";
	public static final String CLAIMS_SUMMARY_REPORT = "claims.summary.report";
	public static final String CLAIMS_DETAILS_REPORT = "claims.details.report";
	public static final String RECEIPTS_SUMMARY_REPORT = "receipts.summary.report";
	public static final String RECEIPTS_DETAILS_REPORT = "receipts.details.report";
	public static final String CLAIMS_RECEIPTS_SUMMARY_REPORT = "claims.receipts.summary.report";
	public static final String CLAIMS_RECEIPTS_DETAILS_REPORT = "claims.receipts.details.report";
	public static final String OVERPAID_TRANSACTIONS_REPORT = "overpaid.transactions.report";
	public static final String PAYMENTS_REPORT = "payments.report";
}
