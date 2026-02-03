/*
Day 7 Part B
Author: 292680
Github: https://github.com/Konex25/AdventOfCode
This program counts quantum timelines (different paths) through the manifold.
Uses recursion with memoization to handle the exponential number of timelines efficiently.
Answer: 24292631346665
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day_7_B {
    
    static List<String> grid;
    static Map<String, Long> memo = new HashMap<>();
    
    // recursively count timelines from position (row, col)
    static long countTimelines(int row, int col) {
        // if out of bounds horizontally, no timeline
        if (col < 0 || col >= grid.get(0).length()) {
            return 0;
        }
        
        // if exited bottom, that's 1 timeline
        if (row >= grid.size()) {
            return 1;
        }
        
        // check memoization
        String key = row + "," + col;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        
        // move down until we hit a splitter or exit
        int currentRow = row;
        while (currentRow < grid.size()) {
            char c = grid.get(currentRow).charAt(col);
            
            if (c == '^') {
                // hit a splitter - split into left and right
                long leftTimelines = countTimelines(currentRow + 1, col - 1);
                long rightTimelines = countTimelines(currentRow + 1, col + 1);
                long total = leftTimelines + rightTimelines;
                memo.put(key, total);
                return total;
            }
            
            currentRow++;
        }
        
        // exited the manifold - 1 timeline
        memo.put(key, 1L);
        return 1;
    }
    
    public static void main(String[] args) {
        try {
            // read the manifold diagram
            BufferedReader reader = new BufferedReader(new FileReader("7_input.txt"));
            grid = new ArrayList<>();
            String line;
            
            while ((line = reader.readLine()) != null) {
                grid.add(line);
            }
            reader.close();
            
            // find starting position 'S'
            int startRow = -1;
            int startCol = -1;
            
            for (int r = 0; r < grid.size(); r++) {
                int col = grid.get(r).indexOf('S');
                if (col != -1) {
                    startRow = r;
                    startCol = col;
                    break;
                }
            }
            
            // count timelines starting from S
            long timelineCount = countTimelines(startRow, startCol);
            
            System.out.println("Total timelines: " + timelineCount);
            
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
