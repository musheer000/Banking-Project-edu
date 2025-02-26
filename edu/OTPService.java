package Project.edu;
import java.util.Random;
public class OTPService
{
    private static final int OTP_LENGTH = 6;
    private int generatedOTP;

    public int generateOTP() {
        Random random = new Random();
        generatedOTP = random.nextInt((int) Math.pow(10, OTP_LENGTH));
        return generatedOTP;
    }

    public boolean validateOTP(int inputOTP) {
        return inputOTP == generatedOTP;
    }
}
