/*
Day 5 Part A
Author: 292680
Github: https://github.com/Konex25/AdventOfCode
Answer: 840
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day_5_A {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("5_input.txt"));
            String line;
            
            // read fresh ingredient ranges
            List<long[]> freshRanges = new ArrayList<>();
            
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    break; // blank line separates ranges from IDs
                }
                
                // parse range: start/end
                String[] parts = line.split("-");
                long start = Long.parseLong(parts[0]);
                long end = Long.parseLong(parts[1]);
                freshRanges.add(new long[]{start, end});
            }
            
            // read available ingredient IDs and check if fresh
            int freshCount = 0;
            
            while ((line = reader.readLine()) != null) {
                long id = Long.parseLong(line.trim());
                
                // check if this ID falls into any fresh range
                if (isFresh(id, freshRanges)) {
                    freshCount++;
                }
            }
            
            reader.close();
            
            System.out.println("Fresh ingredient count: " + freshCount);
            
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
    
    // checks if an ingredient ID is fresh (falls into any range)
    private static boolean isFresh(long id, List<long[]> ranges) {
        for (long[] range : ranges) {
            long start = range[0];
            long end = range[1];
            
            // check if id is in this range (inclusive)
            if (id >= start && id <= end) {
                return true;
            }
        }
        
        return false; // not in any range
    }
}
