package Project.edu;
import java.util.Scanner;

public class Bank {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankingSystem bankingSystem = new BankingSystem(10);
        OTPService otpService = new OTPService();

        while (true) {
            System.out.println("\n--- Banking System Menu ---");
            System.out.println("1. Create Account");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter user ID: ");
                    String userId = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();
                    System.out.print("Enter mobile number: ");
                    String mobileNumber = scanner.nextLine();

                    System.out.print("Enter account number: ");
                    String accountNumber = scanner.nextLine();
                    System.out.print("Enter account holder name: ");
                    String accountHolderName = scanner.nextLine();
                    System.out.print("Enter interest rate (for Savings Account, enter 0 for Checking): ");
                    double interestRate = scanner.nextDouble();

                    // Generate and validate OTP
                    int otp = otpService.generateOTP();
                    System.out.println("Your OTP is: " + otp);
                    System.out.print("Enter the OTP to confirm account creation: ");
                    int inputOTP = scanner.nextInt();

                    if (otpService.validateOTP(inputOTP)) {
                        Account savingsAccount = new SavingsAccount(accountNumber, accountHolderName, interestRate);
                        User newUser = new User(userId, password, mobileNumber);
                        bankingSystem.addAccount(savingsAccount, newUser);
                    } else {
                        System.out.println("Invalid OTP. Account creation failed.");
                    }
                    break;

                case 2:
                    System.out.print("Enter user ID: ");
                    String loginUserId = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String loginPassword = scanner.nextLine();

                    User loggedInUser = bankingSystem.authenticate(loginUserId, loginPassword);
                    if (loggedInUser != null) {
                        System.out.println("Login successful!");

                        while (true) {
                            System.out.println("\n--- User Menu ---");
                            System.out.println("1. Deposit");
                            System.out.println("2. Withdraw");
                            System.out.println("3. Apply Interest");
                            System.out.println("4. Display Accounts");
                            System.out.println("5. View Transaction History");
                            System.out.println("6. Check Balance");
                            System.out.println("7. Logout");
                            System.out.print("Choose an option: ");

                            int userChoice = scanner.nextInt();
                            scanner.nextLine();

                            switch (userChoice) {
                                case 1:
                                    System.out.print("Enter account number: ");
                                    String depositAccountNumber = scanner.nextLine();
                                    Account depositAccount = bankingSystem.findAccount(depositAccountNumber);
                                    if (depositAccount != null) {
                                        System.out.print("Enter amount to deposit: ");
                                        double depositAmount = scanner.nextDouble();

                                        // Generate and validate OTP
                                        otp = otpService.generateOTP();
                                        System.out.println("Your OTP is: " + otp);
                                        System.out.print("Enter the OTP to confirm deposit: ");
                                        inputOTP = scanner.nextInt();

                                        if (otpService.validateOTP(inputOTP)) {
                                            depositAccount.deposit(depositAmount);
                                            System.out.println("A message has been sent to " + loggedInUser.getMobileNumber() + " for deposit confirmation.");
                                        } else {
                                            System.out.println("Invalid OTP. Deposit failed.");
                                        }
                                    } else {
                                        System.out.println("Account not found.");
                                    }
                                    break;

                                case 2:
                                    System.out.print("Enter account number: ");
                                    String withdrawAccountNumber = scanner.nextLine();
                                    Account withdrawAccount = bankingSystem.findAccount(withdrawAccountNumber);
                                    if (withdrawAccount != null) {
                                        System.out.print("Enter amount to withdraw: ");
                                        double withdrawAmount = scanner.nextDouble();

                                        // Generate and validate OTP
                                        otp = otpService.generateOTP();
                                        System.out.println("Your OTP is: " + otp);
                                        System.out.print("Enter the OTP to confirm withdrawal: ");
                                        inputOTP = scanner.nextInt();

                                        if (otpService.validateOTP(inputOTP)) {
                                            withdrawAccount.withdraw(withdrawAmount);
                                            System.out.println("A message has been sent to " + loggedInUser.getMobileNumber() + " for withdrawal confirmation.");
                                        } else {
                                            System.out.println("Invalid OTP. Withdrawal failed.");
                                        }
                                    } else {
                                        System.out.println("Account not found.");
                                    }
                                    break;

                                case 3:
                                    System.out.print("Enter account number: ");
                                    String interestAccountNumber = scanner.nextLine();
                                    Account interestAccount = bankingSystem.findAccount(interestAccountNumber);
                                    if (interestAccount instanceof SavingsAccount) {
                                        // Generate and validate OTP
                                        otp = otpService.generateOTP();
                                        System.out.println("Your OTP is: " + otp);
                                        System.out.print("Enter the OTP to apply interest: ");
                                        inputOTP = scanner.nextInt();

                                        if (otpService.validateOTP(inputOTP)) {
                                            ((SavingsAccount) interestAccount).applyInterest();
                                            System.out.println("Interest applied.");
                                        } else {
                                            System.out.println("Invalid OTP. Interest application failed.");
                                        }
                                    } else {
                                        System.out.println("Interest can only be applied to savings accounts.");
                                    }
                                    break;

                                case 4:
                                    bankingSystem.displayAccounts();
                                    break;

                                case 5:
                                    System.out.print("Enter account number: ");
                                    String historyAccountNumber = scanner.nextLine();
                                    Account historyAccount = bankingSystem.findAccount(historyAccountNumber);
                                    if (historyAccount != null) {
                                        System.out.println("Transaction History for " + historyAccount.getAccountHolderName() + ":");
                                        for (String transaction : historyAccount.getTransactionHistory()) {
                                            if (transaction != null) {
                                                System.out.println(transaction);
                                            }
                                        }
                                    } else {
                                        System.out.println("Account not found.");
                                    }
                                    break;

                                case 6: // Check Balance
                                    System.out.print("Enter account number: ");
                                    String balanceAccountNumber = scanner.nextLine();
                                    Account balanceAccount = bankingSystem.findAccount(balanceAccountNumber);
                                    if (balanceAccount != null) {
                                        System.out.println("Current Balance for " + balanceAccount.getAccountHolderName() + ": " + balanceAccount.getBalance());
                                    } else {
                                        System.out.println("Account not found.");
                                    }
                                    break;

                                case 7:
                                    System.out.println("Logging out...");
                                    break;

                                default:
                                    System.out.println("Invalid choice. Please try again.");
                            }

                            if (userChoice == 7) {
                                break; // Exit the user menu
                            }
                        }
                    } else {
                        System.out.println("Invalid user ID or password.");
                    }
                    break;

                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
