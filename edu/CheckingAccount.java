package Project.edu;
public class CheckingAccount extends Account
{
    public CheckingAccount(String accountNumber, String accountHolderName)
    {
        super(accountNumber, accountHolderName);
    }

    @Override
    public void applyInterest()
    {
        // No interest for checking accounts
    }

    @Override
    public String toString()
    {
        return super.toString() + ", Account Type: Checking";
    }
}
