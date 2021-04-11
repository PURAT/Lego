package com.example;

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
