/*
Day 3 Part B
Author: 292680
Github: https://github.com/Konex25/AdventOfCode
Answer: 172601598658203
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day_3_B {
    public static void main(String[] args) {
        long totalJoltage = 0;
        
        try {
            BufferedReader reader = new BufferedReader(new FileReader("3_input.txt"));
            String line;
            
            // read each bank of batteries
            while ((line = reader.readLine()) != null) {
                // find max 12 digit joltage for this bank
                long maxJoltage = findMaxJoltage12(line);
                totalJoltage += maxJoltage;
            }
            
            reader.close();
            
            System.out.println("Total output joltage: " + totalJoltage);
            
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
    
    // finds the max 12 digit number by picking 12 batteries greedily
    private static long findMaxJoltage12(String bank) {
        StringBuilder result = new StringBuilder();
        int lastPickedIndex = -1;
        
        // pick 12 digits one by one
        for (int i = 0; i < 12; i++) {
            int remainingToPick = 12 - i - 1; // how many more digits needed after this
            int searchEnd = bank.length() - remainingToPick; // last position we can pick from
            
            // find the largest digit in the searchable range
            char maxDigit = '0';
            int maxIndex = -1;
            
            for (int j = lastPickedIndex + 1; j < searchEnd; j++) {
                if (bank.charAt(j) > maxDigit) {
                    maxDigit = bank.charAt(j);
                    maxIndex = j;
                }
            }
            
            // add this digit to result and update last picked position
            result.append(maxDigit);
            lastPickedIndex = maxIndex;
        }
        
        return Long.parseLong(result.toString());
    }
}
