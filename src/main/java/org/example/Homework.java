package org.example;

import java.util.Arrays;
import java.util.List;

public class Homework {

    // Перевернуть строку и вывести на консоль
    //  String string = "I love Java";
    public static void turnString(String string)
    {
        System.out.printf(new StringBuilder(string).reverse().toString());
    }

    // int[] ints = {1, 2, 2, 3, 4, 5, 5, 6, 7, 8, 8, 9};
    // Удалить дубликаты из массива и вывести в консоль
    public static void getDistinctNumbers(int[] ints)
    {
        int[] arr = Arrays.stream(ints)
                .distinct().toArray();

        System.out.println(Arrays.toString(arr));
    }

    // Дан массив, заполненный уникальными значениями типа int.
    // int[] arr = {10, 15, 23, 11, 44, 13, 66, 1, 6, 47};
    // Необходимо найти элемент, который меньше максимума, но больше всех остальных.
    public static Integer findSecondMaxElement(int[] arr)
    {
        Arrays.sort(arr);
        int result = arr[arr.length - 2];
        //System.out.println(result);
        return result;
    }

    // Найти длину последнего слова в строке. В строке только буквы и пробелы.
    // "Hello world" - 5
    // "    fly me    to the moon    " - 4
    public static Integer lengthOfLastWord(String string)
    {
        String[] arrayString = string.split(" ");
        String lastStringToArray = arrayString[arrayString.length - 1];
        return lastStringToArray.length();
    }

    // Определить, что строка является палиндромом
    // Сложность по памяти O(1), не создавать новые String, StringBuilder
    // Примеры:
    // abc - false
    // 112233 - false
    // aba - true
    // 112211 - true
    public static boolean isPalindrome(String string)
    {
       String myString = new StringBuilder(string).reverse().toString();
       return string.equals(myString);
    }
}
