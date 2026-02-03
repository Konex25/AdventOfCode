/*
Day 7 Part A
Author: 292680
Github: https://github.com/Konex25/AdventOfCode
Answer: 1562
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Day_7_A {
    
    // represents a tachyon beam at a specific position
    static class Beam {
        int row;
        int col;
        
        Beam(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
    
    public static void main(String[] args) {
        try {
            // read the manifold diagram
            BufferedReader reader = new BufferedReader(new FileReader("7_input.txt"));
            List<String> grid = new ArrayList<>();
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
            
            // simulate the beams - track unique splitters hit
            Queue<Beam> beams = new LinkedList<>();
            beams.add(new Beam(startRow, startCol));
            
            // track which splitters have been hit (unique positions)
            Set<String> splittersHit = new HashSet<>();
            
            // track visited beam positions to avoid infinite loops
            Set<String> visitedBeams = new HashSet<>();
            
            while (!beams.isEmpty()) {
                Beam beam = beams.poll();
                
                // check if we've already processed this beam position
                String beamPos = beam.row + "," + beam.col;
                if (visitedBeams.contains(beamPos)) {
                    continue;  // skip already processed beam
                }
                visitedBeams.add(beamPos);
                
                // move beam down one row at a time
                while (beam.row < grid.size()) {
                    char current = grid.get(beam.row).charAt(beam.col);
                    
                    // if we hit a splitter
                    if (current == '^') {
                        // record this unique splitter position
                        String pos = beam.row + "," + beam.col;
                        splittersHit.add(pos);
                        
                        // create two new beams: left and right of the splitter
                        // they start from the next row
                        if (beam.col - 1 >= 0) {
                            Beam leftBeam = new Beam(beam.row + 1, beam.col - 1);
                            String leftPos = leftBeam.row + "," + leftBeam.col;
                            if (!visitedBeams.contains(leftPos)) {
                                beams.add(leftBeam);
                            }
                        }
                        if (beam.col + 1 < grid.get(0).length()) {
                            Beam rightBeam = new Beam(beam.row + 1, beam.col + 1);
                            String rightPos = rightBeam.row + "," + rightBeam.col;
                            if (!visitedBeams.contains(rightPos)) {
                                beams.add(rightBeam);
                            }
                        }
                        
                        // stop this beam
                        break;
                    }
                    
                    // if it's empty space or 'S', continue downward
                    beam.row++;
                }
            }
            
            System.out.println("Total beam splits: " + splittersHit.size());
            
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
