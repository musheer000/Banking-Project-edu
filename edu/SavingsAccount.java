package Project.edu;
public class SavingsAccount extends Account
{
    private double interestRate;

    public SavingsAccount(String accountNumber, String accountHolderName, double interestRate)
    {
        super(accountNumber, accountHolderName);
        this.interestRate = interestRate;
    }

    @Override
    public void applyInterest()
    {
        double interest = getBalance() * interestRate / 100;
        deposit(interest);
        addTransaction("Interest Applied: " + interest);
    }

    @Override
    public String toString()
    {
        return super.toString() + ", Account Type: Savings, Interest Rate: " + interestRate + "%";
    }
}
