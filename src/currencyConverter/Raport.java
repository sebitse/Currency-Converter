package currencyConverter;

public class Raport {
	private static Transaction t;
	private static double convertedAmount;
	
	public Raport(Transaction transaction) {
		t = transaction;
		convertedAmount = (t.getAmount() * t.getExchangeRate()) - t.getTransactionFee();
	}
	
	// Drawing up a report with all the data about the transaction
	public void print() {
		System.out.println("Transaction Details:");
        System.out.println("Amount: " + t.getAmount() + " " + t.getSourceCurrency());
        System.out.println("Target Currency: " + t.getTargetCurrency());
        System.out.println("Exchange Rate: " + t.getExchangeRate());
        System.out.println("Taxes: " + t.getTransactionFee() + " " + t.getTargetCurrency());
        
        System.out.println("Converted Amount: " + convertedAmount + " " + t.getTargetCurrency());
	}
}
