import java.util.ArrayList;

public class PasswordCheckerUtility {
	public static boolean isValidPassword(String passwordString) throws  LengthException, NoDigitException, NoUpperAlphaException, NoLowerAlphaException, InvalidSequenceException, NoSpecialCharacterException {
		//LengthException
		if(passwordString.length() < 6) {
			throw new LengthException("The password must be at least 6 characters long");
		}
		//UpperException and LowerException
		int upper = 0, lower = 0;
		for(int i = 0; i < passwordString.length(); i++) {
			if(Character.isUpperCase(passwordString.charAt(i))) {
				upper++;
			}else if(Character.isLowerCase(passwordString.charAt(i))) {
				lower++;
			}
		}
		if(upper == 0) {
			throw new NoUpperAlphaException("The password must contain at least one uppercase alphabetic character");
		}else if(lower == 0) {
			throw new NoLowerAlphaException("The password must contain at least one lowercase alphabetic character");
		}
		//DigitException
		int digit = 0;
		for(int i = 0; i < passwordString.length(); i++) {
			if(Character.isDigit(passwordString.charAt(i))) {
				digit++;
			}
		}
		if(digit == 0) {
			throw new NoDigitException("The password must contain at least one digit");
		}
		//SpecialCharacter
		int special = 0;
		for(int i = 0; i < passwordString.length(); i++) {
			if(String.valueOf(passwordString.charAt(i)).matches("[^a-zA-Z0-9\\\\s]")) {
				special++;
			}
		}
		if(special == 0) {
			throw new NoSpecialCharacterException("The password must contain at least one special character");
		}
		//InvalidSequence
		for(int i = 0; i < passwordString.length(); i++) {
			if(i > passwordString.length() - 3) {
				break;
			}else if(passwordString.charAt(i) == passwordString.charAt(i+1) && passwordString.charAt(i) == passwordString.charAt(i+2)) {
				throw new InvalidSequenceException("The password cannot contain more than two of the same character in sequence.");
			}
		}
		//If pass all, valid password
		return true;
	}
	
	public static boolean isWeakPassword(String passwordString) throws WeakPasswordException {
		if(passwordString.length() >= 6 && passwordString.length() <= 9) {
			return true;
			//throw new WeakPasswordException("The password is OK but weak - it contains fewer than 10 characters.");
		}else {
			return false;
		}
	}
	
	public static ArrayList<String> invalidPasswords(ArrayList<String> passwords){
		ArrayList<String> statusPass = new ArrayList<>();
		for(int i = 0; i < passwords.size(); i++) {
			String pass = passwords.get(i);
			int upper = 0, lower = 0, digit = 0, special = 0;
			for(int x = 0; x < pass.length(); x++) {
				if(Character.isUpperCase(pass.charAt(x))) {
					upper++;
				}else if(Character.isLowerCase(pass.charAt(x))) {
					lower++;
				}else if(Character.isDigit(pass.charAt(x))) {
					digit++;
				}else if(String.valueOf(pass.charAt(x)).matches("[^a-zA-Z0-9\\\\s]")) {
					special++;
				}
	
			}
			//InvalidSequence
			for(int y = 0; y <pass.length();y++) {
				if(y > (pass.length() - 3)) {
					break;
				}else if (pass.charAt(y) == pass.charAt(y+1) && pass.charAt(y) == pass.charAt(y+2)) {
					statusPass.add(pass + " The password cannot contain more than two of the same character in sequence.");
				}
			}
			//Add to arraylist for invalid passwords
			if(pass.length() < 6) {
				statusPass.add(pass + " The password must be at least 6 characters long");
			}else if(upper == 0) {
				statusPass.add(pass + " The password must contain at least one uppercase alphabetic character");
			}else if(lower == 0) {
				statusPass.add(pass + " The password must contain at least one lowercase alphabetic character");
			}else if(digit == 0) {
				statusPass.add(pass + " The password must contain at least one digit");
			}else if(special == 0) {
				statusPass.add(pass + " The password must contain at least one special character");
			}
		}
		//System.out.println(statusPass);
		return statusPass;
	}
}
