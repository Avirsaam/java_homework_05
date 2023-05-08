/*
* Шахматную доску размером NxN обойти конём так, чтобы фигура в каждой клетке была строго один раз.
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

public class task06 {
    private static int X = 0;
    private static int Y = 1;    

    static int currentX = 0;
    static int currentY = 0;
    
    private static List<int[]> ALL_KNIGHT_MOVES = new ArrayList<>(
        Arrays.asList(
            new int[]{ 2, 1},
            new int[]{ 2,-1},
            new int[]{-2, 1},
            new int[]{-2,-1},
            new int[]{ 1, 2},
            new int[]{-1, 2},
            new int[]{ 1,-2},
            new int[]{-1,-2}
        )
    );   

    private static int boardWidth;
    private static int boardHeight;
    private static int moveCounter = 1;
    private static int [][] chessBoardArray;

    public static void main(String[] args) {    
        boardWidth = 8;
        boardHeight = 8;
        
        currentX = 0;
        currentY = 0;
        
        chessBoardArray = new int[boardWidth][boardHeight];

        for (int x = 0; x < boardWidth; x++) {
            for (int y = 0; y < boardHeight; y++) {
                chessBoardArray[x][y] = 0;            
            }       
        }
        //printBoard();        
        markSqureVisited(currentX, currentY);
                
        boolean hasMoves = true;
        
        while (hasMoves){        
            /*
            для максимального обхода доски выбор следующего движения коня должен быть таким, чтобы
            конь ходил на ту клетку с которой возможно пойти на минимальное число еще не пройденных полей
            каждый раз в цикле просматриваем все возможные ходы коня и если клетка валидна (в пределах доски и 
            еще не посещена), то вычислаем количество ходов, которые мы можем сделать с этой клетки, после
            этого помещаем результат в TreeMap (количество возможных ходов -> индекс движения в массиве всех
            возможных движений коня). TreeMap автоматически отсортирован по ключу, так что выбирая первое значение
            ключа мы выбираем движение на клетку с минимальными возможными движениями.
            */
        
            TreeMap<Integer, Integer> mapOnwardMovesByMoveIndex = new TreeMap<>();            
            
            for (int i = 0; i < ALL_KNIGHT_MOVES.size(); i++) {
                int[] thisPossibleMove = ALL_KNIGHT_MOVES.get(i); 
                int nextX = ALL_KNIGHT_MOVES.get(i)[X] + currentX;
                int nextY = ALL_KNIGHT_MOVES.get(i)[Y] + currentY;
                
                if (squareIsOnTheBoard(nextX, nextY) && squareNotVisited(nextX, nextY)){                    
                    int numberOfMovesAfter = numberOfMovesFromSquare(thisPossibleMove[X]+currentX, thisPossibleMove[Y]+currentY);
                    mapOnwardMovesByMoveIndex.put(numberOfMovesAfter, i);
                }
            }          

            if (mapOnwardMovesByMoveIndex.isEmpty()){
                hasMoves = false;
            } else {
                int nextMoveIndex = mapOnwardMovesByMoveIndex.get(mapOnwardMovesByMoveIndex.firstKey());                
                makeMove(nextMoveIndex);
            }
        }
        printBoard();                

    }

    private static void makeMove(int knightMoveIndex){
        
        int [] nextMove = ALL_KNIGHT_MOVES.get(knightMoveIndex);        
        currentX += nextMove[X];
        currentY += nextMove[Y];        
        markSqureVisited(currentX, currentY);
    }

    

    private static int numberOfMovesFromSquare(int x, int y){
        int result = 0;
        chessBoardArray[x][y] = -1; //assumed that move has been effected
        for (int i = 0; i < ALL_KNIGHT_MOVES.size(); i++) {
            int nextX = x + ALL_KNIGHT_MOVES.get(i)[X];
            int nextY = y + ALL_KNIGHT_MOVES.get(i)[Y];
            if (squareIsOnTheBoard(nextX, nextY) && chessBoardArray[nextX][nextY] == 0) {
               result++;
            }             
        }       

        chessBoardArray[x][y] = 0; //restore the state of the board to actual condition
        return result;
    }

    private static void markSqureVisited(int x, int y){
        chessBoardArray[x][y] = moveCounter++;
    }

    private static boolean squareNotVisited(int x, int y) {
        if (chessBoardArray[x][y] == 0) return true;
        else return false;        
    }

    private static boolean squareIsOnTheBoard (int x, int y){        
        if (x > boardWidth  - 1  ||  x < 0  
        ||  y > boardHeight - 1  ||  y < 0){
            return false;
        }
        return true;
    }

    private static void printBoard() {
        for (int y = 0; y < boardHeight; y++) {
            for (int x = 0; x < boardWidth; x++) {
               System.out.printf("%6d", chessBoardArray[x][y]);            
            }
            System.out.println('\n');
       }        
    }

}
