package com.gracelamalva.cryptography.vigenere;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class VigenereCipher {

	private Scanner kb;
	private Random random;
	private ArrayList <Character> list;
	private ArrayList <Character> shuffledList; 
	private char character;
	private char [] alphabet;
	private char [] letters;
	private float [] frequencies; 
	private String plaintext;
	private String ciphertext;
	
	
	//default no arg constructor
	public VigenereCipher()  {
		kb = new Scanner (System.in);
		random = new Random();
		list = new ArrayList();
		shuffledList = new ArrayList(); 
		character =' ';
		
		// newKey();
		
/*		try {
			init();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
	}
	
	//init sequence ask user
	private void init() throws IOException {
		
		while (true) {
			
			System.out.println("Vigenere Cipher Menu");
			System.out.print("(N)ewKey,(G)etKey,(E)ncrypt,(D)ecrypt,(M)ergeFiles,(O)penFile,(Q)uit");
			if(kb.hasNextLine()) {
			String ans = kb.nextLine();
			char response = Character.toUpperCase(ans.charAt(0)); //take first letter and take it to uppercase and store in response
			
			
			//try {
			switch(response) {
			case 'N':
				// newKey();
				break;
			case 'G':
				getKey();
				break;
			case 'E':
				// encrypt();
				break;
			case 'D':
				// decrypt();
				break;
			case 'M':
				mergeFiles();
				break;
			case 'O':
				openFile();
				break;
				
			case 'Q':
				quit();
				break;
				
			default:
				System.out.println("Not a valid answer, try again");
				break;
			}}
		/*}catch (IOException e) {
				e.printStackTrace();
			}
		}*/
		}
	}
	
	//generate key
	private String newKey(String str, String key) { 
	    int x = str.length(); 
	  
	    for (int i = 0; ; i++) { 
	        if (x == i) 
	            i = 0; 
	        if (key.length() == str.length()) 
	            break; 
	        key += (key.charAt(i));
	    } 
	    return key; 
	} 
	  
	
	private void getKey() {
		System.out.println("Key: " );
		
		for(Character x :list ) {
			System.out.print(x);
		}
		System.out.println();
		
		for(Character x : shuffledList ) {
			System.out.print(x);
		}
		System.out.println();		
		
	}
	
	private static String encrypt(String str, String key){ 
	    String cipher_text=""; 
	  
	    for (int i = 0; i < str.length(); i++) 
	    { 
	        // converting in range 0-25 
	        int x = (str.charAt(i) + key.charAt(i)) %26; 
	  
	        // convert into alphabets(ASCII) 
	        x += 'A'; 
	  
	        cipher_text+=(char)(x); 
	    } 
	    return cipher_text; 
	} 
	
	private static String decrypt(String cipher_text, String key) 
	{ 
	    String orig_text=""; 
	  
	    for (int i = 0 ; i < cipher_text.length() &&  
	                            i < key.length(); i++) 
	    { 
	        // converting in range 0-25 
	        int x = (cipher_text.charAt(i) -  
	                    key.charAt(i) + 26) %26; 
	  
	        // convert into alphabets(ASCII) 
	        x += 'A'; 
	        orig_text+=(char)(x); 
	    } 
	    return orig_text; 
	}

	public void letterFrequency(String w) {
		String s = w.replaceAll("(\\P{L}+)", "").toLowerCase();
		Map<Character, Integer> frequency = new HashMap<>();
		System.out.println("Letter Frequency method");
		System.out.println("Old: " + w);
		System.out.println("New: " + s);

        for(int i = 0; i < s.length(); i++) {
            if(frequency.containsKey(s.charAt(i)))  {
				frequency.put(s.charAt(i), frequency.get(s.charAt(i)) + 1);
            }

            else{
				frequency.put(s.charAt(i), 1);
            }    
        }

        double total = 0;
        for (Map.Entry<Character, Integer> entry : frequency.entrySet()) {
			double percentage = (entry.getValue() * 100) / (double) s.length();

			System.out.printf("Char: %s | Count: %d | Percentage: %.8f%% %n", entry.getKey(), entry.getValue(), percentage);
			total += percentage;
        }

        System.out.println(total);
    
	}
	
	
	private void mergeFiles() throws IOException {
	
		   String path = "/Users/gracelamalva/Downloads/text0";
		   File dir = new File(path); 
		   
	        // create obejct of PrintWriter for output file 
	        PrintWriter pw = new PrintWriter("output.txt"); 
	  
	        // Get list of all the files in form of String Array 
	        String[] fileNames = dir.list(); 
	  
	        // loop for reading the contents of all the files  

	        for (String fileName : fileNames) { 
	            System.out.println("Reading from " + fileName); 
	          
	            File f = new File(dir, fileName); 
	          
	            BufferedReader br = new BufferedReader(new FileReader(f)); 
	            pw.println("Contents of file " + fileName); 
	  
	            // Read from current file 
	            
	            String line = br.readLine(); 
	            line = line.replaceAll("[^\\p{Alpha}]", " ");
	            while (line != null) { 
	  
	                // write to the output file 
	                pw.println(line); 
	                line = br.readLine();
	                
	                //System.out.println(line);
	                
	            } 
	            br.close();
	        	
	            pw.flush(); 
	        } 
	        System.out.println("Reading from all files" +  
	        " in directory " + dir.getName() + " Completed"); 
	        
	        pw.close();
		}
	
	private String openFile() throws IOException {
		String word = " ";
		String path = "/Users/gracelamalva/Downloads/output0.txt";
		Reader reader;
		try {
			reader = new BufferedReader(new FileReader(path));
			int theCharNum = reader.read();
			System.out.println("Please wait while we open the large file");
			while(theCharNum != -1) {
			    char theChar = (char) theCharNum;
			    word+=theChar;
			    //System.out.print(theChar);

			    theCharNum = reader.read();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("We have turned the chars to a string");
		//plaintext = word;
		letterFrequency(word);
		
		return word;
			
	}
	
	private void quit() {
		System.out.println("You have successfully quit");
		System.exit(0);
	}

}

