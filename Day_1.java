import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day_1 {
    public static void main(String[] args) {
        // starting position of the dial
        int position = 50;
        int passwordCount = 0; // counts how many times we land on 0
        
        try {
            // read the input file
            BufferedReader reader = new BufferedReader(new FileReader("1_input.txt"));
            String line;
            
            // process each rotation instruction
            while ((line = reader.readLine()) != null) {
                // parse the instruction - first char is L or R
                char direction = line.charAt(0);
                // rest is the distance number
                int distance = Integer.parseInt(line.substring(1));
                
                // apply the rotation
                if (direction == 'L') {
                    // left means subtract (go to lower numbers)
                    position = position - distance;
                    // handle wrapping around the dial
                    while (position < 0) {
                        position += 100; // since dial goes 0-99
                    }
                } else if (direction == 'R') {
                    // right means add (go to higher numbers)
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
            
            // print the answer
            System.out.println("The password is: " + passwordCount);
            
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
