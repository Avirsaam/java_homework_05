/*
 * Реализуйте структуру телефонной книги с помощью HashMap, учитывая, что 1 человек может иметь несколько телефонов.

 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class homework01 {

    public static void main(String[] args) {
        Map<String, ArrayList<String>> phonebook = new HashMap<>();
        
        phonebook.put("Иванов Иван", new ArrayList<>(Arrays.asList("+7-999-123-45-67")));
        phonebook.put("Петров Пётр",  new ArrayList<>(Arrays.asList("+7-999-234-56-78")));
        phonebook.put("Сидоров Сидор",  new ArrayList<>(Arrays.asList("+7-999-345-67-89")));

        String newNameToAdd = "Иванов Иван"; String newNumberToAdd = "+1-234-56-55-66";

        if (phonebook.containsKey(newNameToAdd)){
            phonebook.get(newNameToAdd).add(newNumberToAdd);
        } else {
            phonebook.put(newNameToAdd, new ArrayList<>(Arrays.asList(newNumberToAdd)));
        }

        for (Map.Entry<String, ArrayList<String>> phonebookRecord : phonebook.entrySet()) {
            System.out.println(phonebookRecord);            
        }        
    }
    
}
