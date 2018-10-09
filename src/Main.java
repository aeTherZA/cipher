import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
	
	
	public static void main(String[] args) throws IOException{
	
	ArrayList<String> results = new ArrayList<String>();
	ArrayList<String> dictionary = new ArrayList<String>();
	ArrayList<String> punctuation = new ArrayList<String>();
	
	File sL = new File("cypherDictionary.txt");
	BufferedReader br = new BufferedReader(new FileReader(sL));
	String st;
	  while ((st = br.readLine()) != null){ 
	    dictionary.add(st); 
	  }
	  
	   sL = new File("punctuation.txt");
	   br = new BufferedReader(new FileReader(sL));
	   st = "";
		  while ((st = br.readLine()) != null){ 
		    punctuation.add(st); 
		  }
	  
	  //Deal with CaeserText
	  File file = new File("CeasarText.txt");
	  
	  BufferedReader buffer = new BufferedReader(new FileReader(file));
	  String newLine = System.getProperty("line.seperator");
	  String out;
	  boolean newL = true;
	  String word = "";
	  while((out = buffer.readLine()) != null){
		  if(out.length() > 0){
			  for(int i = 0; i < out.length();i++){
				  
					  if(out.charAt(i) == ' ')
					  {
						  results.add(word);
						  word = "";
					  }
					  else{
						  word += out.charAt(i);
					  }
				  }
				  results.add(word);
				  word = "";
				  newL = false;
			  
			  }
		  
	  }
	  
	  //Remove punctuations
	  removePunctuations(results,punctuation);
	  
	  //
	  //TODO Check duplicates first
	  ArrayList<String> dups = new ArrayList<String>();
	  dups = populateDups(results);
	  for(String i : dups){
		  //System.out.println(i);
	  }
	  int shift = performDecrypt(dups,dictionary);
	  if(shift == -1){
		shift = performDecrypt(results,dictionary);
	  }
	  
	  
	  if(shift == -1){
		  System.out.println("Unable to decode Cipher :(");
		  return;
	  }
	  
	  
	  
		  
	
	  
	}

	private static ArrayList<String> populateDups(ArrayList<String> results) {
		ArrayList<String> returnArr = new ArrayList<String>();
		for(String t : results){
			if((results.indexOf(t) != results.lastIndexOf(t)) && (t.length() < 4 && t.length() > 0))
				
		        if(!returnArr.contains(t))
		          returnArr.add(t);
		}
		return returnArr;
	}
	
	

	private static int performDecrypt(ArrayList<String> results, ArrayList<String> dictionary) {
		
	
		
		boolean shiftWorks = true;
		int shift = 0;
		while(shift < 62){
		for(int i = 0; i < results.size();i++){
			if(results.get(i).length() < 4 && results.get(i).length() > 0){
				String testShift = shiftWord(shift,results.get(i));
				//Compares shifted word to see if shift int makes sense
				shiftWorks = matchesDictionary(testShift,dictionary);
			}
		}
		if(shiftWorks){
			//shift int makes sense
			break;
		}
		else{
			//Try new shift
			shift++;
		}
		}
		if(shift == 62){
			return -1;
		}
		else{
		return shift;
		}
		
	}

	private static boolean matchesDictionary(String testShift, ArrayList<String> dictionary) {

		return dictionary.contains(testShift);
	}

	private static String shiftWord(int shift, String string) {
		String newS = "";
		try {
			for (int x = 0; x < string.length(); ++x) {
				newS += CypherChar.shiftChar(string.charAt(x), shift);
			}
		}
		catch(NotACypherChar ncc)
		{
			System.out.println("The character \"" + ncc.getInvalidChar() + "\" is not part of the alphabet");
			ncc.printStackTrace();
		}
		return newS;
	}

	private static void removePunctuations(ArrayList<String> results,ArrayList<String> punctuation) {
		for(int i = 0; i < results.size();i++){
			for(int j = 0; j < punctuation.size();j++){
				if(results.get(i).contains(punctuation.get(j))){
					results.set(i, results.get(i).replace(punctuation.get(j),""));
				}
			}
		}
		
		
		
	}
}


