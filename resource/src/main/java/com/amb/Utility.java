package com.amb;

import java.security.SecureRandom;
import java.util.Random;

public class Utility {
	public static Random rng = new SecureRandom();
	
	public static String generateBookingRefNumber(int length)
	{
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	    char[] text = new char[length];
	    for (int i = 0; i < length; i++)
	    {
	        text[i] = characters.charAt(rng.nextInt(characters.length()));
	    }
	    return new String(text);
	}
}
