import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * This class represents the letters entered by the user
 * @author Terri Burns
 *
 */
public class LetterSet {
	//letters
	private ArrayList<Character> lettersInArray;
	//dictionary reference, avoids passing between recursive calls.
	private Dictionary dict; 
	
	/**
	 * 
	 * @param stringinput
	 */
	public LetterSet(String stringinput){
		checkValidity(stringinput);
		
		lettersInArray = new ArrayList<Character> ();
		for (int i = 0; i < stringinput.length(); i++)
			lettersInArray.add( stringinput.charAt(i) );
	}
	/**
	 * This method checks whether or not the input from the user meets the size and char requirements
	 * @param stringinput
	 * @return userinput - the input from the user if it meets the requirements
	 */
	public static String checkValidity(String stringinput) {		
		// if the input is the incorrect length
		if (stringinput.length() < 2 || stringinput.length() > 10) {
			System.out
					.println("ERROR: Input should be between two and ten characters.");
			System.exit(0); 
		}

		// to determine if the input is  exclusively letters,
		//im converting from string-->char-->int
		for (int i = 0; i < stringinput.length(); i++) {
			char charinput = stringinput.charAt(i);
			int asciiinput = (int)(charinput);
			if (asciiinput < 65 || (asciiinput > 90 && asciiinput < 97) || asciiinput > 122) {
				System.out
				.println("ERROR: Please make sure you enter only letters.");
				System.exit(0); 
				break;
			}
			else {
				continue;
			}			
		}

		String userInput = stringinput.toLowerCase();		
		//add string to ArrayList<String>
		ArrayList<Character> remainingLetters = new ArrayList<Character>();
		for (char c : userInput.toCharArray()) {
			  remainingLetters.add(c);
			}
		
		
		return userInput;	
	}
	
	
	/**
	 * Constructs all the possible words from randomLetters (the user input) that can
	 * be made from the given dictionary
	 * @param dict  the dictionary (TREE) being used in search
	 * @return  ArrayList object containing all possible words (null if no possible words
	 * or if dictionary is empty)
	 */
	public ArrayList<String> getAllWords (Dictionary dict) {
		if ( null == dict ) return null;	
		this.dict = dict;
		ArrayList<String> words = new ArrayList<String> ();
		StringBuffer prefix = new StringBuffer ();
		getAllWordsRecursive ( lettersInArray, prefix, words );
		cleanUpResults(words);
		
		
		return words;
	}
	
	/**
	 * recursive method (using backtracking) that constructs the words. 
	 * @param possibleLetters  remaining letters that can be added
	 * @param prefix  prefix constructed so far
	 * @param words   collection of completed words
	 */
	private void getAllWordsRecursive ( 
			ArrayList<Character> possibleLetters, 
			StringBuffer prefix,
			ArrayList<String> words
			) 
	{
		//System.out.println(prefix);
		
		if (possibleLetters.size() == 0) {
			words.add(prefix.toString());
			//prefix.deleteCharAt(prefix.length()-1);
		}
		else
			for (int i = 0; i < possibleLetters.size(); i++ ) {
				
				prefix.append(possibleLetters.get(i));
				
				//if the prefix is in the dictionary, then add the prefix to the final array
				if (dict.isWordInDictionary(prefix.toString())) {
					words.add(prefix.toString());
				}
				
				//System.out.println(prefix.toString());
				if (dict.isAPrefix(prefix.toString())) {
					ArrayList <Character> remainingLetters = new ArrayList<Character> (possibleLetters);
					remainingLetters.remove(i);
					getAllWordsRecursive(remainingLetters, prefix, words);
					prefix.deleteCharAt(prefix.length()-1);
					
				}
				else {
					prefix.deleteCharAt(prefix.length()-1);
				}
			}
	}
	
	/**
	 * This removes all repeated words
	 * @param words from getAllWordsRecursive
	 */
	private void cleanUpResults(ArrayList<String> words) {
		// sort the results 
		Collections.sort(words);
		//remove duplicates
		int a = 1;
		while (a < words.size()) {
			if (words.get(a).equals(words.get(a - 1)))
				words.remove(a);
			else
				a++;
		}

	}
	
}
