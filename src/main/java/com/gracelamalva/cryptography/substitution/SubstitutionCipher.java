package com.gracelamalva.cryptography.substitution;

import java.util.*;
import java.io.*;

public class SubstitutionCipher {

	private Scanner kb;
	private Random random;
	private ArrayList <Character> list;
	private ArrayList <Character> shuffledList; 
	private char character;
	private char [] alphabet;
	private char [] letters;
	private float [] frequencies; 
	//private char [] cipherText;
	
	//default no arg constructor
	public SubstitutionCipher() {
		kb = new Scanner (System.in);
		random = new Random();
		list = new ArrayList();
		shuffledList = new ArrayList(); 
		character =' ';
		
		newKey();
		init();
	}
	
	//init sequence ask user
	private void init() {
		try {
			mergeFiles();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		while (true) {
			System.out.println("\nSubstitution Cipher Menu");
			System.out.println("(N)ewKey,(G)etKey,(E)ncrypt,(D)ecrypt,(Q)uit");
			char response = Character.toUpperCase(kb.nextLine().charAt(0)); //take first letter and take it to uppercase and store in response
			
			switch(response) {
			case 'N':
				newKey();
				break;
			case 'G':
				getKey();
				break;
			case 'E':
				encrypt();
				break;
			case 'D':
				decrypt();
				break;
			case 'Q':
				quit();
				break;
				
			default:
				System.out.println("Not a valid answer, try again");
			}
		}
	}
	
	//generate key
	private void newKey() {
		character = 'a';
		list.clear();
		shuffledList.clear();
		
		for (int i= 96; i<122;i++) { //ascii table full vals 32 - 127
			list.add(Character.valueOf(character));
			character++;
		}
		
		shuffledList = new ArrayList(list);
		Collections.shuffle(shuffledList);
		System.out.println("*New key has been generated*");
		
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
	
	private void encrypt() {
		System.out.println("Enter a message to encrypt: ");
		String message = kb.nextLine();
		
		
		letters = message.toCharArray();
		letterFrequency(message);
		
		
		
		for (int i =0; i< letters.length; i++) {
			for (int j =0; j< list.size(); j++) {
				if (letters[i] == list.get(j)) {
					letters[i] = shuffledList.get(j);
					break;
				}
			}
		}
		
		System.out.println("Encrypted message: ");
		for (char x: letters) {
			System.out.print(x);
			
		}
		
		
		String encrypted = String.valueOf(letters);
		letterFrequency(encrypted);
		System.out.println();
	}
	
	private void decrypt() {
		System.out.println("Enter a message to decrypt: ");
		String message = kb.nextLine();
		
		letters = message.toCharArray();
		for (int i =0; i< letters.length; i++) {
			for (int j =0; j< shuffledList.size(); j++) {
				if (letters[i] == shuffledList.get(j)) {
					letters[i] = list.get(j);
					break;
				}
			}
		}
		
		System.out.println("Decrypted message: ");
		for (char x: letters) {
			System.out.print(x);
		}
		System.out.println();
	}
	
	private void letterFrequency (String s ) { //[] float
		
		float frequency = 0;
		
		Map<Character, Integer> d = new HashMap<Character, Integer>();
        
        
        for(int i = 0; i < s.length(); i++)
        {
            if(d.containsKey(s.charAt(i)))
            {
                d.put(s.charAt(i), d.get(s.charAt(i)) + 1);
            }
            else
            {
                d.put(s.charAt(i), 1);
            }
        }
         
        // Print characters and their 
        // frequencies in same order 
        // of their appearance
        for(int i = 0; i < s.length(); i++)
        {
           
            // Print only if this 
            // character is not printed
            // before
            if(d.get(s.charAt(i)) != 0)
            {
                System.out.print(s.charAt(i));
                System.out.print(d.get(s.charAt(i)) + " ");
                d.put(s.charAt(i), 0);
            }            
        }
    
		/*
		
		for (int i = 0; i < letters.length; i++) {
			frequency = letters[i]/100;
			frequencies[i]= frequency;
			System.out.print(frequency);
		}
		return frequencies;
		
		*/
		
	}
	
	
	private void mergeFiles() throws IOException {
		/*Path mergedFile = Path.get("mergedFile");
		List<Path> filestobemerged = filesToBeMerged;
			
		try (BufferedWriter writer = Files.newBufferedWriter(mergedFile,StandardOpenOption.APPEND)){
			for (Path file: filestobemerged) {
				writer.append(Files.newBufferedReader(file));
			}
		} catch (IOException e) {
			System.err.println(e);
		}
		*/
		
		   File dir = new File("/Users/gracelamalva/Downloads/text0"); 
		   
	        // create obejct of PrintWriter for output file 
	        PrintWriter pw = new PrintWriter("/Users/gracelamalva/Downloads/output.txt"); 
	  
	        // Get list of all the files in form of String Array 
	        String[] fileNames = dir.list();
	  
	        // loop for reading the contents of all the files  
	        // in the directory GeeksForGeeks 
	        for (String fileName : fileNames) { 
	            System.out.println("Reading from " + fileName); 
	          
	            // create instance of file from Name of  
	            // the file stored in string Array 
	            File f = new File(dir, fileName); 
	            //System.out.println(f.getAbsolutePath());
	            // create object of BufferedReader 
	            BufferedReader br = new BufferedReader(new FileReader(f)); 
	            pw.println("Contents of file " + fileName); 
	  
	            // Read from current file 
	            String line = br.readLine(); 
	            while (line != null) { 
	  
	                // write to the output file 
	                pw.println(line); 
	                line = br.readLine(); 
	                
	            } 
	            br.close();
	        	pw.close();
	            pw.flush(); 
	        } 
	        System.out.println("Reading from all files" +  
	        " in directory " + dir.getName() + " Completed"); 
	    
		
		}
	
	private void quit() {
		System.out.println("You have successfully quit");
		System.exit(0);
	}
}
