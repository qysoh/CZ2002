package assignment;

public class Generate {
	private double hashval=0.0;
	/**
	 * Generates a username for user which includes part of his email id, with all letters in capital and contains numbers.
	 * @param email Email ID will be used in generation process
	 * @return Username of user and hash value of each username
	 */
	public String generateUsername(String email)
	{
		
		String username="";
		String newEmail="";
		int k=0;
		while(k<email.length() && email.charAt(k)!='@')   // if the user enters email id as xxxxxx@gmail.com , the email is generated excluding the @gmail.com
		{
			newEmail+=email.charAt(k);
			k+=1;
		}
		int length=newEmail.length();
		int max=length;
		int min=length/2;
		
		// Generate random number between length/2 to length
		int num=(int)(Math.random()*(max-min+1)+min);
		
		for(int i=0;i<num;i++)
		{
			username+=email.charAt(i); //adding "num" number of characters from the email id to the generated username
		}
		for(int j=0;j<3;j++)                      //this loop will run thrice so three numbers will be added to the username
		{
			int n=(int)(Math.random()*(3-1+1)+1); // creating a random number between 1 and 3 and adding to the username
			username+=n;                         
		}
		username=username.toUpperCase();
		System.out.println("Username:"+username);
		return username;
	}
	

	
	/**
	 * Generates unique hash value for each password
	 * @param pwd
	 * @return Unique hash value
	 */
	public double hashing(String pwd) 
	{   
		int length=pwd.length();  
        for(int i=0;i<length;i++)
        {
		 char var=pwd.charAt(i);   // choose each character of the password
		 hashval+=(int)var*(Math.pow(2, i)); // hashing each character by multiplying its ascii value with 2 raised to 'i' where 'i' would depend on the character's position
	}                                        // then adding all the character hash values to generate a total hash value for the password
        return hashval; 
	}
	
	/**
	 * Generates a random password for each user, of 8-12 characters with acsii value between 32 and 122
	 * @return Password of user, along with hash value
	 */
	public double CreatePass()
	{
		String pass="";
		//To generate random values within a range use the formula Math.random()*(max-min+1)+min => values with the min inclusive and max exclusive
		// we want to generate passwords within the range of 8 and 10 characters
		// Since max is not inclusive in the range, we have used 11 instead of 10 so we can have the range between 8 and 10
		
		int length=(int)(Math.random()*(11-8+1)+8);
		System.out.println("Length="+length);
		for(int i=0;i<length;i++)
		{
			int cnum=(int)(Math.random()*(123-48+1)+48);// ive limited the characters to the characters with the ascii value between 48 and 122
			char ch=(char)cnum;// we are generating each character now by converting the ascii value to the corresponding character by using explicit type casting
			pass+=ch;
		}
		System.out.println("Password:"+pass);
		hashval=hashing(pass); // the password generated is passed to the hashing function to generate a hash value
		return hashval;
	}
}