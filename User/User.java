package User;

public abstract class User
{
	protected String username;
	protected String password;
	
	// Setter
	public void setUsername(String username)
	{
		this.username = username;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	// Getter
	public String getUsername()
	{
		return username;
	}
	public String getPassword()
	{
		return password;
	}
	
    public boolean login(String username, String password)
    {
        return this.username.equals(username) && this.password.equals(password);
    }

    public abstract void tampilkanMenu();
}



