/*
Day 5 Part B
Author: 292680
Github: https://github.com/Konex25/AdventOfCode
This program counts ALL ingredient IDs covered by fresh ranges.
It merges overlapping ranges and counts total unique IDs in the merged ranges.
Answer: 359913027576322
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Day_5_B {
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
            
            reader.close();
            
            // merge overlapping ranges
            List<long[]> mergedRanges = mergeRanges(freshRanges);
            
            // count total IDs in merged ranges
            long totalFresh = 0;
            for (long[] range : mergedRanges) {
                long count = range[1] - range[0] + 1; // inclusive count
                totalFresh += count;
            }
            
            System.out.println("Total fresh ingredient IDs: " + totalFresh);
            
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
    
    // merges overlapping ranges
    private static List<long[]> mergeRanges(List<long[]> ranges) {
        if (ranges.isEmpty()) {
            return ranges;
        }
        
        // sort ranges by start position
        Collections.sort(ranges, Comparator.comparingLong(a -> a[0]));
        
        List<long[]> merged = new ArrayList<>();
        long[] current = ranges.get(0);
        
        for (int i = 1; i < ranges.size(); i++) {
            long[] next = ranges.get(i);
            
            // check if ranges overlap or are adjacent
            if (next[0] <= current[1] + 1) {
                // merge: extend current range
                current[1] = Math.max(current[1], next[1]);
            } else {
                // no overlap: save current and start new range
                merged.add(current);
                current = next;
            }
        }
        
        // don't forget the last range
        merged.add(current);
        
        return merged;
    }
}
