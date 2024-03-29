package com.codurance.StringCalculator;

import java.util.ArrayList;

/**
 * A utility class for performing operations on strings representing numbers.
 */
public class StringCalculator {

    /**
     * Adds the numbers provided in a string format.
     *
     * @param numbers a string containing numbers separated by commas
     * @return the sum of the numbers
     * @throws IllegalArgumentException if any of the numbers are negative
     */
    public int Add(String numbers) {
        int result = -1;
        if (numbers.equals("")) {
            result = 0;
        } else if (numbers.length() == 1) {
            result = Integer.parseInt(numbers);
        } else if (numbers.length() > 1) {
            ArrayList<Integer> arrNum = new ArrayList<Integer>();
//            for (char c: numbers.toCharArray()) {
//                if (Character.isDigit(c)) {
//                    arrNum.add(Character.getNumericValue(c));
//                }
//            }
            if (numbers.contains("-")) {
                String[] nums = numbers.split(",");
                String negativeNums = "";
                for (String s: nums) {
                    if (Integer.parseInt(s) < 0) {
                        negativeNums += s;
                    }
                }
                throw new IllegalArgumentException("negatives not allowed: " + negativeNums);
            }
            String tempStr1 = "";
            for (int i = 0; i < numbers.length(); i++) {
                if (Character.isDigit(numbers.charAt(i))) {
                    String tempStr2 = String.valueOf(numbers.charAt(i));
                    for (int j = i + 1; j < numbers.length(); j++) {
                        if (Character.isDigit(numbers.charAt(j))) {
                            tempStr2 += String.valueOf(numbers.charAt(j));
                        } else {
                            break;
                        }
                    }
                    if (!tempStr1.contains(tempStr2)) {
                        tempStr1 += tempStr2 + ",";
                    }
                }
            }
            String[] arrStr = tempStr1.split(",");
            for (String s: arrStr) {
                int num = Integer.parseInt(s);
                if (num <= 1000) { // Ignore the numbers greater than 1000.
                    arrNum.add(num);
                }
            }

            int total = 0;
            for (int i : arrNum) {
                total += i;
            }
            result = total;
        }
//        } else if (numbers.equals("4")) {
//            result = 4;
//        } else if (someString.equals("1,2")) {
//            return 3;
//        }
        return result;
    }
}
