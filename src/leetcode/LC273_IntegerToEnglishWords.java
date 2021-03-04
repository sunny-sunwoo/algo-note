package leetcode;

import java.util.List;

/**
 * LC 273. Integer to English Words
 * 
 * [Approach] 3 lists of names pre-defined
 * 
	1) GROUP
	
	1,234,567,891
	 ^   ^   ^ 
	         thousand
	    million
	 billion   
	 
	2) TO19: "", "One", ..., "Nineteen"
	3) TO99: "", "Ten", ..., "Ninety"
	 
	 [public level] proceed by 3 numbers
	 res = convert(i..j)  + grp name  + res
	  => EDGE CASE: when converted str is empty? // skip e.g. 1,000,000
	 
	 
	 [private level] convert
	 1) val <= 19 ? find from TO19
	 
	 2) val <= 99?
	    TO99.get(val/10) + " " + TO19.get(val%10)
	 
	 3) 
	 TO19.get(val/100) + " Hundred " + convert(val%100) // RECURSIVE
 
 [Note] 
 - 40 = Forty
 - edge case: 1,000,000 (One Million), 0(Zero)
 - consider range: 0 <= num <= 2^31 - 1
   (about 2.1 billion / integer positive number range)
 
 * @author sunnypark
 *
 */
public class LC273_IntegerToEnglishWords {
    private static final List<String> GROUP = List.of("", "Thousand", "Million", "Billion");
    private static final List<String> TO19 = List.of("", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen","Sixteen", "Seventeen", "Eighteen", "Nineteen");
    private static final List<String> TO99 = List.of("", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety");
    
    public static String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }

        String res = "";
        int groupNum = 0;
        while (num > 0) {
            int currNum = num % 1000;
            
            // build res
            String currName = convert(currNum).trim();
            if (!currName.isEmpty()) {
                res = currName + " " + GROUP.get(groupNum) + " " + res;
            }

            num = num/1000;
            groupNum++;
        }
        return res.trim();
    }
    
    private static String convert(int num) {
        if (num <= 19) {
            return TO19.get(num);
        }
        
        if (num <= 99) {
            return TO99.get(num/10) + " " + TO19.get(num%10);   
        }
        
        return TO19.get(num/100) + " Hundred " + convert(num%100);
    }
    
    public static void main(String[] args) {
    	System.out.println(numberToWords(1234567891)); 
    	// output: "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"
    	
    	System.out.println(numberToWords(1000000)); // output: One Million
    	
    	System.out.println(numberToWords(0)); // output: Zero
    }
}
