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
		
	  
	  performDecrypt(results,dictionary);
		  
	
	  
	}

	private static void performDecrypt(ArrayList<String> results, ArrayList<String> dictionary) {
		
	
		
		boolean shiftWorks = true;
		int shift = 0;
		for(int i = 0; i < results.size();i++){
			if(results.get(i).length() < 4 && results.get(i).length() > 0){
				String testShift = shiftWord(shift,results.get(i));
				System.out.println(results.get(i));
				//Compares shifted word to see if shift int makes sense
				shiftWorks = matchesDictionary(testShift,dictionary);
			}
		}
		if(shiftWorks){
			//shift int makes sense
		}
		else{
			//Try new shift
		}
		
	}

	private static boolean matchesDictionary(String testShift, ArrayList<String> dictionary) {
		for(int i = 0; i < dictionary.size();i++){
			
		}
		return false;
	}

	private static String shiftWord(int shift, String string) {
		// TODO Auto-generated method stub
		return null;
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


