package Project.edu;

public class User
{
    private String userId;
    private String password;
    private String mobileNumber;

    public User(String userId, String password, String mobileNumber)
    {
        this.userId = userId;
        this.password = password;
        this.mobileNumber = mobileNumber;
    }

    public String getUserId()
    {
        return userId;
    }

    public String getPassword()
    {
        return password;
    }

    public String getMobileNumber()
    {
        return mobileNumber;
    }
}
