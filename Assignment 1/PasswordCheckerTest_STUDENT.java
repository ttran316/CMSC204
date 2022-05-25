
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * STUDENT tests for the methods of PasswordChecker
 * @author 
 *
 */
public class PasswordCheckerTest_STUDENT {
	ArrayList<String> passwords, passwords2;
	
	@Before
	public void setUp() throws Exception {
		String[] p = {"jG;Y@]b7N.","*1C@ih(AD}R",";Yw+/TW5D6","y@7N]A!zU3","}Gxd3n!R6g","1AP{ZK-Gg#J","LV6;y(.g8T","GT+2Xi8,cR","=7=(hXJYTF","ZR+u+5fH]X"};
		String[] p2 = {"lhmksda#1","buDTPqU%p", "pKr1!", "KUHS!1"};
		passwords = new ArrayList<String>(Arrays.asList(p));
		passwords2 = new ArrayList<String>(Arrays.asList(p2));
	}

	@After
	public void tearDown() throws Exception {
	
	}

	/**
	 * Test if the password is less than 8 characters long.
	 * This test should throw a LengthException for second case.
	 */
	@Test
	public void testIsValidPasswordTooShort()
	{
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword("1aB")); 
			PasswordCheckerUtility.isValidPassword("1aB!s3f4");
		}catch(LengthException e) {
			assertTrue("Successfully threw a LengthException",true);
		}catch(Exception e) {
			assertTrue("Threw some other exception",false);
		}
	}
	
	/**
	 * Test if the password has at least one uppercase alpha character
	 * This test should throw a NoUpperAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoUpperAlpha()
	{
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword("1aBs!234")); 
			PasswordCheckerUtility.isValidPassword("1a!s3f4");
		}catch(NoUpperAlphaException e) {
			assertTrue("Successfully threw a NoUpperAlphaException",true);
		}catch(Exception e) {
			assertTrue("Threw some other exception",false);
		}
	}
	
	/**
	 * Test if the password has at least one lowercase alpha character
	 * This test should throw a NoLowerAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoLowerAlpha()
	{
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword("12#SDFASs")); 
			PasswordCheckerUtility.isValidPassword("12#SDFAS");
		}catch(NoLowerAlphaException e) {
			assertTrue("Successfully threw a NoLowerAlphaException",true);
		}catch(Exception e) {
			assertTrue("Threw some other exception",false);
		}
	}
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsWeakPassword()
	{
		try{
			assertEquals(true,PasswordCheckerUtility.isValidPassword("1234aaAA!asd")); //Added special char because wrong exception thrown when not supposed to
			boolean weakPwd = PasswordCheckerUtility.isWeakPassword("1234!aA");
			assertTrue(weakPwd);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			assertTrue("Threw some incorrect exception",false);
		}
	}
	
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsValidPasswordInvalidSequence()
	{
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword("1234Ss#45s"));
			PasswordCheckerUtility.isValidPassword("11111abB#");
		}catch(InvalidSequenceException e) {
			assertTrue("Successfully threw a InvalidSequenceException",true);
		}catch(Exception e) {
			assertTrue("Threw some other exception",false);
		}
	}
	
	/**
	 * Test if the password has at least one digit
	 * One test should throw a NoDigitException
	 */
	@Test
	public void testIsValidPasswordNoDigit()
	{
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword("asbS2@4dsdf"));
			PasswordCheckerUtility.isValidPassword("sdfSDF");
		}catch(NoDigitException e) {
			assertTrue("Successfully threw a NoDigitException",true);
		}catch(Exception e) {
			assertTrue("Threw some other exception",false);
		}
	}
	
	/**
	 * Test correct passwords
	 * This test should not throw an exception
	 * @throws NoSpecialCharacterException 
	 * @throws InvalidSequenceException 
	 * @throws NoLowerAlphaException 
	 * @throws NoUpperAlphaException 
	 * @throws NoDigitException 
	 * @throws LengthException 
	 */
	@Test
	public void testIsValidPasswordSuccessful() throws LengthException, NoDigitException, NoUpperAlphaException, NoLowerAlphaException, InvalidSequenceException, NoSpecialCharacterException
	{
		boolean test = false;
		int t = 0, f = 0;
		for(int i = 0; i < passwords.size(); i++) {
			test = PasswordCheckerUtility.isValidPassword(passwords.get(i));
			if(test == true) {
				t++;
			}else {
				f++;
			}
		}
		assertTrue(t == 10);
	}
	
	/**
	 * Test the invalidPasswords method
	 * Check the results of the ArrayList of Strings returned by the validPasswords method
	 */
	@Test
	public void testInvalidPasswords() {
		ArrayList<String> results;
		results = PasswordCheckerUtility.invalidPasswords(passwords2);
		Scanner scan = new Scanner(results.get(0)); 
		assertEquals(scan.next(), "lhmksda#1");
		String nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("uppercase"));
		
		scan = new Scanner(results.get(1)); 
		assertEquals(scan.next(), "buDTPqU%p");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("digit"));
		
		scan = new Scanner(results.get(2)); 
		assertEquals(scan.next(), "pKr1!");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("6 characters") || nextResults.contains("digit"));
		
		scan = new Scanner(results.get(3));
		assertEquals(scan.next(), "KUHS!1");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("lowercase"));
	}
	
}