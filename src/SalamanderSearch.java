import java.util.ArrayList;
import java.util.List;

public class SalamanderSearch {
    public static void main(String[] args) {
        char[][] enclosure1 = {
            {'.','.','.','.','.','.'},
            {'W','.','W','W','.','.'},
            {'.','.','W','.','.','W'},
            {'f','W','.','.','W','.'},
            {'W','.','W','s','.','.'},
        };

        char[][] enclosure2 = {
            {'.','.','.','.','.','.'},
            {'W','W','W','W','s','.'},
            {'.','.','W','.','.','W'},
            {'f','W','.','.','W','.'},
            {'W','.','W','.','.','.'},
        };
    }

    /**
     * Returns whether a salamander can reach the food in an enclosure.
     * 
     * The enclosure is represented by a rectangular char[][] that contains
     * ONLY the following characters:
     * 
     * 's': represents the starting location of the salamander
     * 'f': represents the location of the food
     * 'W': represents a wall
     * '.': represents an empty space the salamander can walk through
     * 
     * The salamander can move one square at a time: up, down, left, or right.
     * It CANNOT move diagonally.
     * It CANNOT move off the edge of the enclosure.
     * It CANNOT move onto a wall.
     * 
     * This method should return true if there is any sequence of steps that
     * the salamander could take to reach food.
     * 
     * @param enclosure
     * @return whether the salamander can reach the food
     * @throws IllegalArgumentException if the enclosure does not contain a salamander
     */
    public static boolean canReach(char[][] enclosure) {
        int[] startLocation = salamanderLocation(enclosure);
        boolean[][] visited = new boolean[enclosure.length][enclosure[0].length];
        return canReach(startLocation, enclosure, visited);
    }

    private static boolean canReach(int[] currentLocation, char[][] enclosure, boolean[][] visited) {
        int currentR = currentLocation[0];
        int currentC = currentLocation[1];
        if(enclosure[currentR][currentC] == 'f') return true;
        if(visited[currentR][currentC]) return false;

        visited[currentR][currentC] = true;

        //boolean reachable = false;
        for(int[] move : possibleMoves(enclosure, currentLocation)) {
            if(canReach(move, enclosure, visited)) return true;
            //reachable |= canReach(move, enclosure, visited);
        }

        return false;
        //return reachable;
    }

    public static List<int[]> possibleMoves(char[][] enclosure, int[] location) {
        int currentR = location[0];
        int currentC = location[1];
        List<int[]> validLocations = new ArrayList<>();

        //UP
        int newR = currentR - 1;
        int newC = currentC;
        if(newR >= 0 && enclosure[newR][newC] != 'W') { validLocations.add(new int[]{newR, newC}); }
        //DOWN
        newR = currentR + 1;
        newC = currentC;
        if(newR < enclosure.length && enclosure[newR][newC] != 'W') { validLocations.add(new int[]{newR, newC}); }
        //LEFT
        newR = currentR;
        newC = currentC - 1;
        if(newC >= 0 && enclosure[newR][newC] != 'W') { validLocations.add(new int[]{newR, newC}); }
        //RIGHT
        newR = currentR;
        newC = currentC + 1;
        if(newC < enclosure[0].length && enclosure[newR][newC] != 'W') { validLocations.add(new int[]{newR, newC}); }

        return validLocations;
    }

    // return the [row, col] location of the salamander
    // throw an IllegalArgumentException if there is no salamander
    public static int[] salamanderLocation(char[][] enclosure) {
        for(int row = 0; row < enclosure.length; row++) {
            for(int col = 0; col < enclosure[row].length; col++) {
                if(enclosure[row][col] == 's') { 
                    return new int[]{row, col};
                }
            }
        }
        throw new IllegalArgumentException("Salamander not found in enclosure");
    } 
}
