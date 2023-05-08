/*
 * Пусть дан список сотрудников:
 * Написать программу, которая найдёт и выведет повторяющиеся имена с количеством повторений. 
 * Отсортировать по убыванию популярности
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class homework02 {
    public static void main(String[] args) {
        String [] allNamesArr = new String[]{
            "Иван Иванов","Светлана Петрова","Кристина Белова","Анна Мусина","Анна Крутова","Иван Юрин",
            "Петр Лыков", "Павел Чернов", "Петр Чернышов", "Мария Федорова", "Марина Светлова", "Мария Савина",
            "Мария Рыкова", "Марина Лугова", "Анна Владимирова", "Иван Мечников", "Петр Петин", "Иван Ежов"
        };

        Map <String, Integer> mapNameByRepetition = new HashMap<>();
        
        for (String thisName : allNamesArr) {
            String firstName = thisName.split(" ")[0];
            if (mapNameByRepetition.containsKey(firstName)){
                mapNameByRepetition.put(firstName, mapNameByRepetition.get(firstName) + 1);
            } else {
                mapNameByRepetition.put(firstName, 1);
            }
        }

        Map <Integer, ArrayList<String>> mapSorted = new TreeMap<>(Comparator.reverseOrder());

        for (Map.Entry<String, Integer> thisEntry : mapNameByRepetition.entrySet()) {
            if (mapSorted.containsKey(thisEntry.getValue())){
                mapSorted.get(thisEntry.getValue()).add(thisEntry.getKey());
            } else {
                mapSorted.put(thisEntry.getValue(), new ArrayList<>(Arrays.asList(thisEntry.getKey())));
            }            
        }

        for (Map.Entry<Integer, ArrayList<String>> thisEntry : mapSorted.entrySet()) {
            for (String thisName : thisEntry.getValue()) {
                System.out.printf("%s - %d\n", thisName, thisEntry.getKey() );                
            }            
        }
    }
}
