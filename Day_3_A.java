/*
Day 3 Part A
Author: 292680
Github: https://github.com/Konex25/AdventOfCode
Answer: 17383
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day_3_A {
    public static void main(String[] args) {
        int totalJoltage = 0;
        
        try {
            BufferedReader reader = new BufferedReader(new FileReader("3_input.txt"));
            String line;
            
            // read each bank of batteries
            while ((line = reader.readLine()) != null) {
                // find max joltage for this bank
                int maxJoltage = findMaxJoltage(line);
                totalJoltage += maxJoltage;
            }
            
            reader.close();
            
            System.out.println("Total output joltage: " + totalJoltage);
            
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
    
    // finds the maximum two digit number we can make by picking two batteries
    private static int findMaxJoltage(String bank) {
        int maxJoltage = 0;
        
        // try all pairs of positions
        for (int i = 0; i < bank.length(); i++) {
            for (int j = i + 1; j < bank.length(); j++) {
                // get the two digits
                int firstDigit = bank.charAt(i) - '0';
                int secondDigit = bank.charAt(j) - '0';
                
                // form the two digit number
                int joltage = firstDigit * 10 + secondDigit;
                
                // update max if this is bigger
                if (joltage > maxJoltage) {
                    maxJoltage = joltage;
                }
            }
        }
        
        return maxJoltage;
    }
}
