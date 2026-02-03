/*
Day 1 Part A
Author: 292680
Github: https://github.com/Konex25/AdventOfCode
This program simulates a dial (0-99) that rotates left or right.
It counts how many times the dial lands on position 0 after each rotation.
Answer: 1147 
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day_1_A {
    public static void main(String[] args) {
        
        int position = 50; // starting position of the dial
        int passwordCount = 0; // Counter for how many times we land on 0
        
        try {
            // read the input file
            BufferedReader reader = new BufferedReader(new FileReader("1_input.txt"));
            String line;
            
            // process each rotation instruction
            while ((line = reader.readLine()) != null) {
                // parse first char (left or right)
                char direction = line.charAt(0);
                // rest is the distance number
                int distance = Integer.parseInt(line.substring(1));
                
                // apply the rotation
                if (direction == 'L') {
                    // left == subtraction
                    position = position - distance;
                    // handle wrapping around the dial
                    while (position < 0) {
                        position += 100; // since dial goes 0-99
                    }
                } else if (direction == 'R') {
                    // right == addition
                    position = position + distance;
                    // handle wrapping when we go above 99
                    position = position % 100;
                }
                
                // check if we landed on 0 after this rotation
                if (position == 0) {
                    passwordCount++;
                }
            }
            
            reader.close();
            
            // answer
            System.out.println("The password is: " + passwordCount);

        // error handling
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
