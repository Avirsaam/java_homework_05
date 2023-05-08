/*
 * Взять набор строк, например:
 * 
 * Мороз и солнце день чудесный
 * Еще ты дремлешь друг прелестный
 * Пора красавица проснись. 
 * 
 * Написать метод, который отсортирует эти строки по длине с помощью TreeMap. 
 * Строки с одинаковой длиной не должны “потеряться”.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class task05 {
    public static void main(String[] args) {

        Map <Integer, ArrayList<String>> map = new TreeMap<>();

        String [] inpuString  = {
        "Мороз и солнце; день чудесный!",
        "Еще ты дремлешь, друг прелестный",
        "Пора, красавица, проснись:",
        "Открой сомкнуты негой взоры",
        "Навстречу северной Авроры,",
        "Звездою севера явись!",
        "Мороз и солнце; день чудесный!",
        "Еще ты дремлешь, друг прелестный",
        "Пора, красавица, проснись:",
        "Открой сомкнуты негой взоры",
        "Навстречу северной Авроры,",
        "Звездою севера явись!"
        };
        
        
        for (String thisLine : inpuString) {
            if (map.containsKey(thisLine.length())){
                map.get(thisLine.length()).add(thisLine);
            } else {
                map.put(thisLine.length(), new ArrayList<>(Arrays.asList(thisLine)));
            }
            
        }
        for (Map.Entry<Integer, ArrayList<String>> thisMapEntry : map.entrySet()) {
            System.out.print("Длинна строки " + thisMapEntry.getKey() + ": "); 
            for (String string : thisMapEntry.getValue()) {
                System.out.print(string + " ");    
            }
            System.out.println();
        } 
    }

    
}
