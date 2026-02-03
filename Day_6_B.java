/*
Day 6 Part B
Author: 292680
Github: https://github.com/Konex25/AdventOfCode
This program solves cephalopod math (right-to-left reading).
Each column is a separate number read from top to bottom.
Answer: 9348430857627
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day_6_B {
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
    
    // solves a single problem (group of columns) - CEPHALOPOD MATH
    // Read each column right-to-left as a separate number
    // Within each column, read digits top-to-bottom (most to least significant)
    private static long solveProblem(List<String> problemCols, List<String> lines) {
        List<Long> numbers = new ArrayList<>();
        char operation = ' ';
        
        // read columns from RIGHT to LEFT
        for (int colIdx = problemCols.size() - 1; colIdx >= 0; colIdx--) {
            String col = problemCols.get(colIdx);
            
            // extract digits from this column (top to bottom, excluding last row)
            StringBuilder numStr = new StringBuilder();
            
            for (int row = 0; row < lines.size() - 1; row++) {
                char c = col.charAt(row);
                if (c != ' ') {
                    numStr.append(c);
                }
            }
            
            // if we found digits, parse as a number
            if (numStr.length() > 0) {
                numbers.add(Long.parseLong(numStr.toString()));
            }
            
            // check for operator in last row
            char lastRowChar = col.charAt(lines.size() - 1);
            if (lastRowChar == '*' || lastRowChar == '+') {
                operation = lastRowChar;
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
