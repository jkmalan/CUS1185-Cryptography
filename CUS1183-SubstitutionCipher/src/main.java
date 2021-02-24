import java.util.Scanner;


//key gen, encrypt, decrypt
//alice, bob, eve
//key k shared between alice and bob symmetric


public class main {
	public static void main (String [] args) {
		sequence();
	}
	
	public static void sequence(){
		Scanner kb = new Scanner (System.in);
		System.out.println("Enter a string to encrypt or -1 to quit");
		String in = kb.nextLine();
		
		char plaintext [] = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
		char ciphertext [] = {};
		
		String key = "sdfa";
		
		
	
	
		while(in != "-1") {

			System.out.println(in);
		
		}
			
		
	}
}
