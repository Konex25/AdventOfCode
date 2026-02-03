/*
Day 1 Part 2
Author: 292680
Github: https://github.com/Konex25/AdventOfCode
This program simulates a dial rotation but counts EVERY time the dial passes through 0
during each rotation (click by click), not just the final position.
Answer: 6789
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day_1_B {
    public static void main(String[] args) {
        
        int position = 50; // starting position of the dial
        int passwordCount = 0; // Counter for how many ROTATIONS pass through 0
        
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
                
                // count how many times we pass through 0 during this rotation
                // not just at the end, but during each click
                for (int i = 0; i < distance; i++) {
                    if (direction == 'L') {
                        position--;
                        if (position < 0) position = 99;
                    } else if (direction == 'R') {
                        position++;
                        if (position > 99) position = 0;
                    }
                    
                    // check if we landed on 0 after this rotation
                    if (position == 0) {
                        passwordCount++;
                    }
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
