/*
Day 2 Part A
Author: 292680
Github: https://github.com/Konex25/AdventOfCode
This program finds invalid product IDs within given ranges.
An ID is invalid if it's a pattern repeated exactly twice (like 12341234).
It sums up all these invalid IDs.
Answer: 12599655151
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day_2_A {
    public static void main(String[] args) {
        long totalSum = 0; // sum of all invalid IDs
        
        try (BufferedReader reader = new BufferedReader(new FileReader("2_input.csv"))) {
            String line = reader.readLine();
            
            // check if file is empty
            if (line == null || line.trim().isEmpty()) {
                System.err.println("Error: file is empty");
                return;
            }
            
            // parse ranges separated by commas
            String[] ranges = line.split(",");
            
            // process each range
            for (String range : ranges) {
                String[] parts = range.split("-");
                
                // skip invalid format
                if (parts.length != 2) {
                    continue;
                }
                
                // parse start and end
                long start = Long.parseLong(parts[0].trim());
                long end = Long.parseLong(parts[1].trim());
                
                // check each ID in this range
                for (long id = start; id <= end; id++) {
                    if (isInvalidID(id)) {
                        totalSum += id;
                    }
                }
            }
            
            System.out.println("Sum of invalid IDs: " + totalSum);
            
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error parsing number: " + e.getMessage());
        }
    }
    
    // checks if ID is invalid (pattern repeated exactly twice)
    private static boolean isInvalidID(long id) {
        String idStr = String.valueOf(id);
        
        // length must be even to split in half
        if (idStr.length() % 2 != 0) {
            return false;
        }
        
        // split into two halves
        int halfLen = idStr.length() / 2;
        String firstHalf = idStr.substring(0, halfLen);
        String secondHalf = idStr.substring(halfLen);
        
        // check if both halves match
        return firstHalf.equals(secondHalf);
    }
}
