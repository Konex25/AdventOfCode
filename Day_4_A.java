/*
Day 4 Part A
Author: 292680
Github: https://github.com/Konex25/AdventOfCode
Answer: 1480
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day_4_A {
    public static void main(String[] args) {
        try {
            // read the grid
            List<String> grid = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader("4_input.txt"));
            String line;
            
            while ((line = reader.readLine()) != null) {
                grid.add(line);
            }
            reader.close();
            
            // count accessible rolls
            int accessibleCount = 0;
            int rows = grid.size();
            int cols = grid.get(0).length();
            
            // check each position
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    // only check rolls of paper (@)
                    if (grid.get(r).charAt(c) == '@') {
                        // count adjacent rolls
                        int adjacentRolls = countAdjacentRolls(grid, r, c);
                        
                        // accessible if fewer than 4 adjacent rolls
                        if (adjacentRolls < 4) {
                            accessibleCount++;
                        }
                    }
                }
            }
            
            System.out.println("Accessible rolls: " + accessibleCount);
            
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
    
    // counts how many of the 8 adjacent positions contain a roll (@)
    private static int countAdjacentRolls(List<String> grid, int row, int col) {
        int count = 0;
        int rows = grid.size();
        int cols = grid.get(0).length();
        
        // check all 8 directions
        int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};  // row offsets
        int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};  // col offsets
        
        for (int i = 0; i < 8; i++) {
            int newRow = row + dr[i];
            int newCol = col + dc[i];
            
            // check bounds
            if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols) {
                if (grid.get(newRow).charAt(newCol) == '@') {
                    count++;
                }
            }
        }
        
        return count;
    }
}
