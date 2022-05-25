import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MorseCodeConverter {
	public static MorseCodeTree originalCode = new MorseCodeTree();
	
	public static String convertToEnglish(String code) {
		String[] letter, word = code.split(" / ");
		StringBuilder converted = new StringBuilder();
		
		for(String space : word) {
			letter = space.split(" ");
			for(String temp : letter) {
				converted.append(originalCode.fetch(temp));
			}
			converted.append(" ");
		}
		return converted.toString().trim();
	}
	
	public static String convertToEnglish(File codeFile) throws FileNotFoundException {
		InputStream input = new FileInputStream(codeFile);
		BufferedReader buffer = new BufferedReader(new InputStreamReader(input));
		StringBuilder result = new StringBuilder();
		
		buffer.lines().forEach(x -> result.append(convertToEnglish(x)).append("\n"));
		try {
			buffer.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
		return result.toString().trim();
	}

	public static String printTree() {
		ArrayList<String> tree = originalCode.toArrayList();
		StringBuilder result = new StringBuilder();
		for(String convert : tree) {
			result.append(convert).append(" ");
		}
		return result.toString().trim();
	}
}
