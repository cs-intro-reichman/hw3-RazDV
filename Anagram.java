/** Functions for checking if a given string is an anagram. */
public class Anagram {
	public static void main(String args[]) {
		System.out.println(isAnagram("silent","listen"));  // true
		System.out.println(isAnagram("William Shakespeare","I am a weakish speller")); // true
		System.out.println(isAnagram("Madam Curie","Radium came")); // true
		System.out.println(isAnagram("Tom Marvolo Riddle","I am Lord Voldemort")); // true

		System.out.println(preProcess("What? No way!!!"));
		
		System.out.println("silent and " + randomAnagram("silent") + " are anagrams.");
		
		String str = "1234567";
		Boolean pass = true;
		for (int i = 0; i < 10; i++) {
			String randomAnagram = randomAnagram(str);
			System.out.println(randomAnagram);
			pass = pass && isAnagram(str, randomAnagram);
			if (!pass) break;
		}
		System.out.println(pass ? "test passed" : "test Failed");
	}  

	// Returns true if the two given strings are anagrams, false otherwise.
	public static boolean isAnagram(String str1, String str2) {
		// preprocess both strings
		str1 = preProcess(str1);
		str2 = preProcess(str2);

		// IGNORE SPACES HERE (fix)
		str1 = str1.replace(" ", "");
		str2 = str2.replace(" ", "");

		if (str1.length() != str2.length()) return false;

		int[] freq = new int[256];

		for (int i = 0; i < str1.length(); i++)
			freq[str1.charAt(i)]++;

		for (int i = 0; i < str2.length(); i++)
			freq[str2.charAt(i)]--;

		for (int i = 0; i < freq.length; i++)
			if (freq[i] != 0) return false;

		return true;
	}
	   
	// Preprocess: lowercase letters, preserve spaces (test requires it), delete everything else.
	public static String preProcess(String str) {
		String result = "";
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);

			if (c == ' ') {
				result += ' ';                 // KEEP SPACES (test demands)
			} else if (c >= 'A' && c <= 'Z') {
				result += (char)(c + 32);     // lowercase
			} else if (c >= 'a' && c <= 'z') {
				result += c;
			}
			// all else removed
		}
		return result;
	} 
	   
	public static String randomAnagram(String str) {
		String temp = str;
		String result = "";

		while (temp.length() > 0) {
			int i = (int)(Math.random() * temp.length());
			result += temp.charAt(i);
			temp = temp.substring(0, i) + temp.substring(i + 1);
		}

		return result;
	}
}