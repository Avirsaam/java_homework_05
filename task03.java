import java.util.HashMap;
import java.util.Map;

/*
 *  Написать метод, который переводит число из римского формата записи в арабский. 
 *   Например, MMXXII = 2022
 */


public class task03 {
    public static void main(String[] args) {
        System.out.println("MMXXII = " + romanToArabic("MMXXII"));
        System.out.println("XXL = " + romanToArabic("XXL"));
    }

    public static Integer romanToArabic (String romanNumber){
        int result = 0;
        Map<Character, Integer> hmRomanDigits = new HashMap<>();
        hmRomanDigits.put('I', 1);
        hmRomanDigits.put('V', 5);
        hmRomanDigits.put('X', 10);
        hmRomanDigits.put('L', 50);
        hmRomanDigits.put('C', 100);
        hmRomanDigits.put('D', 500);
        hmRomanDigits.put('M', 1000);
        
        int prevDigit = 0;
        for (int i = romanNumber.toCharArray().length - 1; i > -1; i--) {            
            int thisDigit = hmRomanDigits.get(romanNumber.toCharArray()[i]);
            
            if (thisDigit >= prevDigit){
                result += thisDigit;
                prevDigit = thisDigit;
            } else {
                result -= thisDigit;
            }
        }

        return result;
    }
    
}
