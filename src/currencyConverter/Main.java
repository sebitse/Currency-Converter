package currencyConverter;

import javax.swing.*;

import org.json.JSONException;
import org.json.JSONObject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
	
	// API attribute zone
	private static final String API_KEY = "PROIECT_PIP";
	
	
	// GUI attribute zone
	private static final long serialVersionUID = 1L;
	private static final String[] currencies = {"AUD", "AZN", "BRL", "BWP", "CHF", "EGP", "EUR", "GBP", "ISK", "JPY", "LKR",
			"LTL", "MXN", "NOK", "PLN", "RON", "RUB", "SEK", "TND", "USD", "VED", "XAF", "ZWD"};
	private final JComboBox<String> baseCurrencyComboBox;
    private final JComboBox<String> targetCurrencyComboBox;
    private final JTextField amountTextField;
    private final JTextField transactionFeeTextField;
    private final JCheckBox cashWithdrawnCheckBox;
    private final JCheckBox studentStatusCheckBox;
    private final JButton convertButton;
    private final JLabel selectedCurrencyLabel;
    private final JLabel selectedAmountLabel;
    private final JLabel taxesLabel;

    public Main() {
        // Setup frame
        setTitle("Currency Converter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GroupLayout(getContentPane()));

        // Create GUI component
        baseCurrencyComboBox = new JComboBox<>(currencies); 
        targetCurrencyComboBox = new JComboBox<>(currencies); 
        amountTextField = new JTextField();
        transactionFeeTextField = new JTextField();
        cashWithdrawnCheckBox = new JCheckBox("Cash Withdrawn");
        studentStatusCheckBox = new JCheckBox("Student Status");
        convertButton = new JButton("Convert");
        selectedCurrencyLabel = new JLabel("Selected Currency: ");
        selectedAmountLabel = new JLabel("Selected Amount: ");
        taxesLabel = new JLabel("Taxes:");

        // Create Layout
        GroupLayout layout = (GroupLayout)getContentPane().getLayout();

        layout.setAutoCreateGaps(true); // automatic spacing between components
        layout.setAutoCreateContainerGaps(true);

        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(baseCurrencyComboBox)
                        .addComponent(targetCurrencyComboBox)
                        .addComponent(amountTextField)
                        .addComponent(selectedCurrencyLabel)
                        .addComponent(selectedAmountLabel)
                        .addComponent(taxesLabel)
                        .addComponent(transactionFeeTextField)
                        .addComponent(cashWithdrawnCheckBox)
                        .addComponent(studentStatusCheckBox)
                        .addComponent(convertButton));

        layout.setHorizontalGroup(hGroup);

        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup()
        		.addComponent(selectedCurrencyLabel)
                .addComponent(baseCurrencyComboBox)
                .addComponent(targetCurrencyComboBox)
                .addComponent(selectedAmountLabel)
                .addComponent(amountTextField)
                .addComponent(taxesLabel)
                .addComponent(transactionFeeTextField)
                .addComponent(cashWithdrawnCheckBox)
                .addComponent(studentStatusCheckBox)
                .addComponent(convertButton);

        layout.setVerticalGroup(vGroup);

        // Add ActionListener for Convert button
        convertButton.addActionListener(new ActionListener() {
        	
        	// Logical implementation to be executed after pressing the button
            @Override
            public void actionPerformed(ActionEvent e) {
                
            	// Data acquisition area for the transaction process
                String baseCurrency = (String)baseCurrencyComboBox.getSelectedItem();
                String targetCurrency = (String)targetCurrencyComboBox.getSelectedItem();
                double amount = parseAmount(); 
                double exchangeRate = getExchangeRateFromAPI(baseCurrency, targetCurrency);
                double transactionFee = parseTransactionFee();
                boolean cashWithdrawn = cashWithdrawnCheckBox.isSelected();
                boolean studentStatus = studentStatusCheckBox.isSelected();

                // Create instance of Transaction object and model it using Builder
                Transaction transaction = null;
				try {
					transaction = new TransactionBuilder(baseCurrency)
					        .setAmount(amount)
					        .setTargetCurrency(targetCurrency)
					        .setExchangeRate(exchangeRate)
					        .setTransactionFee(transactionFee)
					        .setCashWithdrawn(cashWithdrawn)
					        .setStudentStatus(studentStatus)
					        .build();
				} catch (Exception e1) {
					System.out.println(e1);
				}

                // Display transaction details
                if (transaction != null) {
                    Raport raport = new Raport(transaction);
                    raport.print();
                } else {
                    System.out.println("Transaction failed.");
                }
            }
        });

        setSize(300, 340);
    }

    /**
     * Get Exchange Rate from an API call.
     * 
     * @param baseCurrency is the source currency from which the transaction starts
     * @param targetCurrency is the currency in which we want to get the amount
     * @return The value of the exchange rate directly from API
     */
    private double getExchangeRateFromAPI(String baseCurrency, String targetCurrency) {
        
    	JSONObject exchangeRates = CurrencyConverterAPI.fetchExchangeRates(API_KEY, baseCurrency);
    	
    	try {
			return exchangeRates.getDouble(targetCurrency);
		} catch (JSONException e) {
			System.out.println("API Error.");
		}
        return 0;
    }

    /**
     * Retrieve information entered in GUI(TextField)
     * 
     * @return The value of the amount that was set in the JTextField
     */
    private double parseAmount() {
        try {
            return Double.parseDouble(amountTextField.getText());
        } catch (NumberFormatException e) {
            return 1;
        }
    }
    
    /**
     * Parse the transaction fee from the text field, return 0 if parsing fails
     * 
     * @return 0 if the text is corrupted, transaction fee if all good.
     */
    private double parseTransactionFee() {
        try {
            return Double.parseDouble(transactionFeeTextField.getText());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static void main(String[] args) {
    	
        SwingUtilities.invokeLater(() -> {
            new Main().setVisible(true);
        });
        
    }
}




