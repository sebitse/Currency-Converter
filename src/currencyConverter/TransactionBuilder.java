package currencyConverter;

public class TransactionBuilder {
	private final String sourceCurrency;
	private String targetCurrency;
	private double amount = 0;
    private double exchangeRate = 0;
    private double transactionFee = 0;
    private boolean cashWithdrawn = false;
    private boolean studentStatus = false;

    // Single constructor for single mandatory field
    public TransactionBuilder(String sourceCurrency) {
        this.sourceCurrency = sourceCurrency;
    }

    // Setters Zone
    public TransactionBuilder setTargetCurrency(String targetCurrency) throws Exception {
    	
        if(targetCurrency != sourceCurrency) 
        	this.targetCurrency = targetCurrency;
        else 
        	throw new Exception("Illogical transaction");
        
        return this;
    }
    
    public TransactionBuilder setAmount(double amount) {
        this.amount = amount;
        return this;
    }

    public TransactionBuilder setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
        return this;
    }

    public TransactionBuilder setTransactionFee(double transactionFee) {
        this.transactionFee = transactionFee;
        return this;
    }
    
    public TransactionBuilder setCashWithdrawn(boolean cashWithdrawn) {
    	this.cashWithdrawn = cashWithdrawn;
    	
    	if(this.cashWithdrawn == true)
    		transactionFee++; 
    	
    	return this;
    }
    
    public TransactionBuilder setStudentStatus(boolean studentStatus) {
    	this.studentStatus = studentStatus;
    	
    	if(this.studentStatus == true)
    		transactionFee--; 
    		// hypothetical
    	
    	return this;
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


    // Method returning the final object
    public Transaction build() {
        return new Transaction(this);
    }
}

