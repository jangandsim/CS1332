package LinkedList;
/**
 * CS 2110 Spring 2017 HW2
 * Part 2 - Coding with bases
 *
 * @author Junyoung Jang
 *
 * Global rules for this file:
 * - You may not use more than 2 conditionals per method. Conditionals are
 *   if-statements, if-else statements, or ternary expressions. The else block
 *   associated with an if-statement does not count toward this sum.
 * - You may not use more than 2 looping constructs per method. Looping
 *   constructs include for loops, while loops and do-while loops.
 * - You may not use nested loops.
 * - You may not declare any file-level variables.
 * - You may not declare any objects, other than String in select methods.
 * - You may not use switch statements.
 * - You may not use the unsigned right shift operator (>>>)
 * - You may not write any helper methods, or call any other method from this or
 *   another file to implement any method.
 * - The only Java API methods you are allowed to invoke are:
 *     String.length()
 *     String.charAt()
 * - You may not invoke the above methods from string literals.
 *     Example: "12345".length()
 * - When concatenating numbers with Strings, you may only do so if the number
 *   is a single digit.
 *
 * Method-specific rules for this file:
 * - You may not use multiplication, division or modulus in any method, EXCEPT
 *   decimalStringToInt.
 * - You may declare exactly one String variable each in intToBinaryString and
 *   and intToHexString.
 */
public class Bases
{
    /**
     * Convert a string containing ASCII characters (in binary) to an int.
     * You do not need to handle negative numbers. The Strings we will pass in will be
     * valid binary numbers, and able to fit in a 32-bit signed integer.
     *
     * Example: binaryStringToInt("111"); // => 7
     */
    public static int binaryStringToInt(String binary)
    {
        int n = 0;
        for(int i = 0; i < binary.length(); i++){
            n = n<<1;
            n += (binary.charAt(i)-'0');
        }
        return n;
    }

    /**
     * Convert a string containing ASCII characters (in decimal) to an int.
     * You do not need to handle negative numbers. The Strings we will pass in will be
     * valid decimal numbers, and able to fit in a 32-bit signed integer.
     *
     * Example: decimalStringToInt("123"); // => 123
     */
    public static int decimalStringToInt(String decimal)
    {
	int n = 0;
        int k = 1;
        for(int i = decimal.length()-1; i >= 0; i--){
            n += (decimal.charAt(i)-'0')*k;
            k *= 10;
        }
        return n;
    }

    /**
     * Convert a string containing ASCII characters (in hex) to an int.
     * The input string will only contain numbers and uppercase letters A-F.
     * You do not need to handle negative numbers. The Strings we will pass in will be
     * valid hexadecimal numbers, and able to fit in a 32-bit signed integer.
     *
     * Example: hexStringToInt("A6"); // => 166
     */
    public static int hexStringToInt(String hex)
    {
        int n = 0;
        for(int i = 0; i < hex.length(); i++){
            n = n<<4;
            char temp = hex.charAt(i);
            if(temp >= 'A' && temp <= 'F'){
                temp -= 'A';
		temp += 10;
            } else {
                temp -= '0';
            }
            n += temp;
        }
        return n;
    }

    /**
     * Convert a int into a String containing ASCII characters (in binary).
     * You do not need to handle negative numbers.
     * The String returned should contain the minimum number of characters necessary to
     * represent the number that was passed in.
     *
     * Example: intToBinaryString(7); // => "111"
     */
    public static String intToBinaryString(int binary)
    {
    	String out = "";
    	if(binary == 0){
    	    return "0";
    	} else {
    	    while(binary > 0){
    		int temp = (binary>>1)<<1;
    		if(temp == binary){
    		    out = "0" + out;
    		} else {
    		    out = "1" + out;
    		}
    		binary = binary >> 1;
    	    }
    	}
        return out;
    }

    /**
     * Convert a int into a String containing ASCII characters (in hexadecimal).
     * The output string should only contain numbers and uppercase letters A-F.
     * You do not need to handle negative numbers.
     * The String returned should contain the minimum number of characters necessary to
     * represent the number that was passed in.
     *
     * Example: intToHexString(166); // => "A6"
     */
    public static String intToHexString(int hex)
    {
    	String out = "";
    	if(hex == 0){
    	    return "0";
    	} else {
    	    while(hex> 0){
        		int temp = hex - ((hex>>4)<<4);
        		char adch;
        		if(temp > 9){
        		    adch = (char) (temp + 'A' - 10);
        		} else {
        		    adch = (char) ('0' + temp);
        		}
        		out = adch + out;
        		hex = hex >> 4;
    	    }
    	}
        return out;
    }
}
