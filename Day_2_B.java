/*
Day 2 Part B
Author: 292680
Github: https://github.com/Konex25/AdventOfCode
This program finds invalid product IDs with a looser rule.
An ID is invalid if it contains ANY repeating pattern (like 123123 or 77777).
It sums up all these invalid IDs.
Answer: 20942028255
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day_2_B {
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
    
    // checks if ID is invalid (pattern repeated at least twice)
    private static boolean isInvalidID(long id) {
        String idStr = String.valueOf(id);
        int len = idStr.length();
        
        // try all possible pattern lengths (from 1 to len/2)
        for (int patternLen = 1; patternLen <= len / 2; patternLen++) {
            // total length must be divisible by pattern length
            if (len % patternLen != 0) {
                continue; // this pattern doesn't fit
            }
            
            // extract pattern (first patternLen characters)
            String pattern = idStr.substring(0, patternLen);
            
            // check if whole string consists of repetitions of this pattern
            boolean isRepeating = true;
            for (int i = 0; i < len; i += patternLen) {
                String chunk = idStr.substring(i, i + patternLen);
                if (!chunk.equals(pattern)) {
                    isRepeating = false;
                    break;
                }
            }
            
            // if we found a repeating pattern, the ID is invalid
            if (isRepeating) {
                return true;
            }
        }
        
        return false; // no repeating pattern found
    }
}
