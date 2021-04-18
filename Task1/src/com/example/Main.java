package com.example;

/* Условие задания:
    Артём слышал, что список фамилий в публикациях сортируют в лексикографическом порядке.
    Артём очень тщеславен, и пытается придумать, в каком порядке должны идти буквы в алфавите,
    чтобы его фамилия в публикации стояла первой. Помогите Артёму написать программу которая бы
    вычисляла такой алфавит, в котором заданный список фамилий был бы лексикографически отсортированным
    или определяла, что это невозможно.

    Вход:
    Подается в стандартный ввод. В первой строке записано целое число n (1 ≤ n ≤ 100), количество имен.
    В каждой из следующих n строк записано по одному слову name%i , обозначающему i-е имя.
    Каждое имя содержит только строчные буквы латинского алфавита, не более 100 символов. Все имена различны.

    Выход:
    Подается в стандартный вывод. Если существует такой порядок букв, при котором имена в данном списке следуют
    в лексикографическом порядке, выведите любой такой порядок в виде перестановки символов 'a'–'z'
    (иными словами, выведите сначала первую букву модифицированного алфавита, затем вторую, и так далее).
    В противном случае выведите единственное слово «Impossible» (без кавычек).
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static ArrayList<Character> list = new ArrayList<>();
    private static ArrayList<Character> list2 = new ArrayList<>();
    private static ArrayList<String> words = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int countWords = Integer.parseInt(reader.readLine());
        for (int i = 0; i < countWords; i++) {
            words.add(reader.readLine());
        }
        reader.close();

        for (int j = 1; j < countWords; j++) {
            String word = words.get(j - 1);
            String word2 = words.get(j);
            if (word.charAt(0) != word2.charAt(0)) {
                if (list.contains(word.charAt(0))) {
                    System.out.println("Impossible");
                    return;
                }
                list.add(word.charAt(0));
                if (j == countWords - 1) {
                    if (list.contains(word2.charAt(0))) {
                        System.out.println("Impossible");
                        return;
                    }
                    list.add(word2.charAt(0));
                }
            } else {
                int pos = getPositionDiffChars(word, word2);
                if (pos > 0) {
                    list2.add(word.charAt(pos));
                    list2.add(word2.charAt(pos));
                }
            }
        }

        for (int i = 1; i < list2.size(); i++) {
            addCharsToList(list2.get(i - 1), list2.get(i));
        }

        if (list.size() < 27) {
            addCharacters(list);
        }

        if (checkSolve(countWords))
            for (char letter : list)
                System.out.print(letter);
        else {
            System.out.println("Impossible");
        }
    }

    private static void addCharacters(ArrayList<Character> list) {
        char letter = 'a';
        while (list.size() != 26) {
            if (!list.contains(letter)) {
                list.add(letter);
            }
            letter++;
        }
    }

    private static void addCharsToList(char a, char b) {
        int indexA = list.indexOf(a);
        int indexB = list.indexOf(b);
        if (indexA != -1 && indexB != -1) {
            if (indexA > indexB) {
                list.add(indexB, a);
            }
            return;
        }
        if (indexA == indexB) {
            list.add(a);
            list.add(b);
        }
        if (indexA >= 0) {
            list.add(b);
        }
        if (indexB >= 0) {
            list.add(indexB, a);
        }
    }

    private static int getPositionDiffChars(String str1, String str2) {
        int minLength = Math.min(str1.length(), str2.length());
        int n = 0;
        while (n < minLength) {
            if (str1.charAt(n) != str2.charAt(n)) {
                return n;
            }
            n++;
        }
        return -1;
    }

    private static boolean checkSolve(int countWords) {
        for (int i = 1; i < countWords; i++) {
            int pos = getPositionDiffChars(words.get(i - 1), words.get(i));
            if (pos >= 0) {
                char a = words.get(i - 1).charAt(pos);
                char b = words.get(i).charAt(pos);
                if (list.indexOf(a) > list.indexOf(b)) {
                    return false;
                }
            } else {
                if (words.get(i - 1).length() > words.get(i).length())
                    return false;
            }
        }
        return true;
    }
}
