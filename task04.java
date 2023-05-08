/*
 * Написать метод, который переведёт данное целое число в римский формат.
 * 2023 => MMXXIII
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class task04 {
    public static void main(String[] args) {
        List<Integer> testData = new ArrayList<>(Arrays.asList(2024));
        for (Integer integer : testData) {
            System.out.println(integer + ": " + arabicToRoman(integer) );            
        }
    }

    public static String arabicToRoman(int arabicNumber) {        
        String result = "";
        if (arabicNumber < 4000){
            Map<Integer,String> mapArabicToRoman = new HashMap<>();
            mapArabicToRoman.put(0,    "");
            mapArabicToRoman.put(1,    "I");
            mapArabicToRoman.put(2,    "II");
            mapArabicToRoman.put(3,    "III");
            mapArabicToRoman.put(4,    "IV");
            mapArabicToRoman.put(5,    "V");
            mapArabicToRoman.put(6,    "VI");
            mapArabicToRoman.put(7,    "VII");
            mapArabicToRoman.put(8,    "VIII");
            mapArabicToRoman.put(9,    "IX");
            mapArabicToRoman.put(10,   "X");
            mapArabicToRoman.put(20,   "XX");
            mapArabicToRoman.put(30,   "XXX");
            mapArabicToRoman.put(40,   "XL");
            mapArabicToRoman.put(50,   "L");
            mapArabicToRoman.put(60,   "LX");
            mapArabicToRoman.put(70,   "LXX");
            mapArabicToRoman.put(80,   "LXXX");
            mapArabicToRoman.put(90,   "XC");
            mapArabicToRoman.put(100,  "C");
            mapArabicToRoman.put(200,  "CC");
            mapArabicToRoman.put(300,  "CCC");
            mapArabicToRoman.put(400,  "CD");
            mapArabicToRoman.put(500,  "D");
            mapArabicToRoman.put(600,  "DC");
            mapArabicToRoman.put(700,  "DCC");
            mapArabicToRoman.put(800,  "DCCC");
            mapArabicToRoman.put(900,  "CM");
            mapArabicToRoman.put(1000, "M");
            mapArabicToRoman.put(2000, "MM");
            mapArabicToRoman.put(3000, "MMM");

            int multiplier = 1000;
            while (multiplier > 0){
                System.out.println(arabicNumber / multiplier);
                result += mapArabicToRoman.get(arabicNumber / multiplier * multiplier);
                arabicNumber %= multiplier;
                multiplier /= 10;
            }
        } else {
            result = "error, value should be 0 - 3999";
        }
        return result;
    }
}
