/*
Day 4 Part B
Author: 292680
Github: https://github.com/Konex25/AdventOfCode
Answer: 8899
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day_4_B {
    public static void main(String[] args) {
        try {
            // read the grid into a mutable structure
            BufferedReader reader = new BufferedReader(new FileReader("4_input.txt"));
            String line;
            List<String> gridLines = new ArrayList<>();
            
            while ((line = reader.readLine()) != null) {
                gridLines.add(line);
            }
            reader.close();
            
            // convert to char array for easy modification
            int rows = gridLines.size();
            int cols = gridLines.get(0).length();
            char[][] grid = new char[rows][cols];
            
            for (int r = 0; r < rows; r++) {
                grid[r] = gridLines.get(r).toCharArray();
            }
            
            // simulate removal process
            int totalRemoved = 0;
            
            while (true) {
                // find all accessible rolls in current state
                List<int[]> accessible = findAccessibleRolls(grid);
                
                // if no more accessible rolls, we're done
                if (accessible.isEmpty()) {
                    break;
                }
                
                // remove all accessible rolls
                for (int[] pos : accessible) {
                    grid[pos[0]][pos[1]] = '.';
                }
                
                totalRemoved += accessible.size();
            }
            
            System.out.println("Total removed rolls: " + totalRemoved);
            
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
    
    // finds all currently accessible rolls (< 4 adjacent rolls)
    private static List<int[]> findAccessibleRolls(char[][] grid) {
        List<int[]> accessible = new ArrayList<>();
        int rows = grid.length;
        int cols = grid[0].length;
        
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == '@') {
                    int adjacentRolls = countAdjacentRolls(grid, r, c);
                    if (adjacentRolls < 4) {
                        accessible.add(new int[]{r, c});
                    }
                }
            }
        }
        
        return accessible;
    }
    
    // counts how many of the 8 adjacent positions contain a roll (@)
    private static int countAdjacentRolls(char[][] grid, int row, int col) {
        int count = 0;
        int rows = grid.length;
        int cols = grid[0].length;
        
        // check all 8 directions
        int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};
        
        for (int i = 0; i < 8; i++) {
            int newRow = row + dr[i];
            int newCol = col + dc[i];
            
            if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols) {
                if (grid[newRow][newCol] == '@') {
                    count++;
                }
            }
        }
        
        return count;
    }
}
