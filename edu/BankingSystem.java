package Project.edu;
import java.util.Scanner;

public class BankingSystem
{
    private Account[] accounts;
    private User[] users;
    private int accountCount;
    private int userCount;

    public BankingSystem(int size)
    {
        accounts = new Account[size];
        users = new User[size];
        accountCount = 0;
        userCount = 0;
    }

    public void addAccount(Account account, User user)
    {
        if (accountCount < accounts.length && userCount < users.length)
        {
            accounts[accountCount++] = account;
            users[userCount++] = user;
            System.out.println("Account created successfully.");
            System.out.println("A message has been sent to " + user.getMobileNumber() + " for account creation.");
        } else {
            System.out.println("Account limit reached.");
        }
    }

    public User authenticate(String userId, String password)
    {
        for (int i = 0; i < userCount; i++)
        {
            if (users[i].getUserId().equals(userId) && users[i].getPassword().equals(password)) {
                return users[i];
            }
        }
        return null;
    }

    public Account findAccount(String accountNumber)
    {
        for (int i = 0; i < accountCount; i++)
        {
            if (accounts[i].getAccountNumber().equals(accountNumber))
            {
                return accounts[i];
            }
        }
        return null;
    }

    public void displayAccounts()
    {
        for (int i = 0; i < accountCount; i++)
        {
            System.out.println(accounts[i]);
        }
    }
}
