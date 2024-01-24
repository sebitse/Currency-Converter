package currencyConverter;


public class Transaction {
	private final double amount;
	private final String sourceCurrency;
	private final String targetCurrency;
	private final double exchangeRate;
	private final double transactionFee;
	private final boolean cashWithdrawn;
	private final boolean studentStatus;

	// Integrating Builder Design Pattern using a single Constructor
	Transaction(TransactionBuilder builder) {
		this.amount = builder.getAmount();
		this.sourceCurrency = builder.getSourceCurrency();
		this.targetCurrency = builder.getTargetCurrency();
		this.exchangeRate = builder.getExchangeRate();
		this.transactionFee = builder.getTransactionFee();
		this.cashWithdrawn = builder.getCahsWithdrawn();
		this.studentStatus = builder.getStudentStatus();
	}

	// Getters Zone
	public double getAmount() {
		return amount;
	}
	public String getSourceCurrency() {
		return sourceCurrency;
	}
	public String getTargetCurrency() {
		return targetCurrency;
	}
	public double getExchangeRate() {
		return exchangeRate;
	}
	public double getTransactionFee() {
		return transactionFee;
	}
	public boolean getCahsWithdrawn() {
		return cashWithdrawn;
	}
	public boolean getStudentStatus() {
		return studentStatus;
	}

}
