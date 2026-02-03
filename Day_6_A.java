/*
Day 6: Trash Compactor - Part A
This program solves vertical math problems arranged horizontally.
Each problem is a set of numbers stacked vertically with an operation at the bottom.
Answer: 4449991244405
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day_6_A {
    public static void main(String[] args) {
        try {
            // read all lines
            BufferedReader reader = new BufferedReader(new FileReader("6_input.txt"));
            List<String> lines = new ArrayList<>();
            String line;
            
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            reader.close();
            
            // pad all lines to same length
            int maxLen = 0;
            for (String l : lines) {
                maxLen = Math.max(maxLen, l.length());
            }
            
            for (int i = 0; i < lines.size(); i++) {
                String l = lines.get(i);
                while (l.length() < maxLen) {
                    l += " ";
                }
                lines.set(i, l);
            }
            
            // parse problems column by column
            long grandTotal = 0;
            List<String> currentProblem = new ArrayList<>();
            
            for (int col = 0; col < maxLen; col++) {
                // check if this column is all spaces (separator)
                boolean isSeparator = true;
                for (String l : lines) {
                    if (col < l.length() && l.charAt(col) != ' ') {
                        isSeparator = false;
                        break;
                    }
                }
                
                if (isSeparator) {
                    // end of problem
                    if (!currentProblem.isEmpty()) {
                        long result = solveProblem(currentProblem, lines);
                        grandTotal += result;
                        currentProblem.clear();
                    }
                } else {
                    // add column to current problem
                    StringBuilder colData = new StringBuilder();
                    for (String l : lines) {
                        colData.append(l.charAt(col));
                    }
                    currentProblem.add(colData.toString());
                }
            }
            
            // don't forget last problem
            if (!currentProblem.isEmpty()) {
                long result = solveProblem(currentProblem, lines);
                grandTotal += result;
            }
            
            System.out.println("Grand total: " + grandTotal);
            
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    // solves a single problem (group of columns)
    private static long solveProblem(List<String> problemCols, List<String> lines) {
        // for each row, extract the number/operation across all columns
        List<Long> numbers = new ArrayList<>();
        char operation = ' ';
        
        for (int row = 0; row < lines.size(); row++) {
            StringBuilder rowStr = new StringBuilder();
            
            for (String col : problemCols) {
                rowStr.append(col.charAt(row));
            }
            
            String cleaned = rowStr.toString().trim();
            
            if (!cleaned.isEmpty()) {
                if (row == lines.size() - 1) {
                    // last row is the operation
                    operation = cleaned.charAt(0);
                } else {
                    // other rows are numbers
                    numbers.add(Long.parseLong(cleaned));
                }
            }
        }
        
        // calculate result
        long result = numbers.get(0);
        
        for (int i = 1; i < numbers.size(); i++) {
            if (operation == '*') {
                result *= numbers.get(i);
            } else {
                result += numbers.get(i);
            }
        }
        
        return result;
    }
}
