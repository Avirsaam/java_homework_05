/*
 * На шахматной доске расставить 8 ферзей так, чтобы они не били друг друга
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class homework04 {
    private static int boardWidth;
    private static int boardHeight;
    
    private static int[][] chessBoardArray;
    
    private static final int X = 0;
    private static final int Y = 1;
    private static int numberOfQueens;

    private static Map<Integer, int[]> positionsOfPieces;
    private static List<boolean[]> results;

    public static void main(String[] args) {
        boardHeight = 8;
        boardWidth  = 8;        
        numberOfQueens = 8;
        
        chessBoardArray = new int[boardWidth][boardHeight];        
        positionsOfPieces = new HashMap<>();

        results = new LinkedList<>();
        
        ClearChessboard();
        //printBoard();
        
        //можно через рекурсию?...
        //
        int activePiece = 0;
        while (canSetPieceOnTheBoard(activePiece)){ 
            activePiece = 1;
            while (canSetPieceOnTheBoard(activePiece)){
                activePiece = 2;
                while (canSetPieceOnTheBoard(activePiece)){                                                    
                    activePiece = 3;
                    while (canSetPieceOnTheBoard(activePiece)){

                        activePiece = 4;
                        while (canSetPieceOnTheBoard(activePiece)){
                            activePiece = 5;
                            while (canSetPieceOnTheBoard(activePiece)){
                                activePiece = 6;
                                while (canSetPieceOnTheBoard(activePiece)){
                                    activePiece = 7;
                                    while (canSetPieceOnTheBoard(activePiece)){
                                        AddToResults();                                        
                                        clearOtherPieces(activePiece = 7);
                                    }    
                                    clearOtherPieces(activePiece = 6);
                                }
                                clearOtherPieces(activePiece = 5);
                            }
                            clearOtherPieces(activePiece = 4);
                        }
                        
                        clearOtherPieces(activePiece = 3);
                    }                    
                    clearOtherPieces(activePiece = 2);
                }
                printStateOfPieces();
                clearOtherPieces(activePiece = 1);
            }
            printStateOfPieces();            
            clearOtherPieces(activePiece = 0);                                    
        }
        printResults();
    }

    private static void printResults() {
        int counter = 1;
        for (boolean[] result : results) {
            StringBuilder sb = new StringBuilder();
            sb.append("Расстановка " + counter++ + "\n");

            for (int i = 0; i < boardHeight; i++) {                
                for (int j = 0; j < boardWidth; j++) {
                    if (result[i * boardHeight + j] == true) sb.append("X");
                    else sb.append("-");

                    sb.append("     ");
                }
                sb.append("\n\n");                
            }
            sb.append("\n\n");
            System.out.println(sb);            
            
        }        
    }

    private static void AddToResults(){
        boolean [] board = new boolean[boardHeight*boardWidth];
        for (int i = 0; i < boardHeight; i++) {
            for (int j = 0; j < boardWidth; j++) {
                if (chessBoardArray[j][i] == -1){
                    board[i* boardHeight + j] = false;
                } else {
                    board[i* boardHeight + j] = true;
                }                
            }            
        }

        boolean alreadyHave = false;
        for (boolean [] resultSaved : results) {
            if (Arrays.equals(resultSaved, board)){
                alreadyHave = true;
            }                       
        }

        if (alreadyHave == false){
            results.add(board);            
        }
    }

    private static void clearOtherPieces(int index){
        for (int i = index; i < numberOfQueens; i++) {
            int thisX = positionsOfPieces.get(index)[X];
            int thisY = positionsOfPieces.get(index)[Y];

            if (chessBoardArray[thisX][thisY] == index) {
                chessBoardArray[thisX][thisY] = -1;            
            }    
        }
    }

    private static boolean canSetPieceOnTheBoard(int indexOfPiece) {        
        while (pieceNotReachedTheEnd(indexOfPiece)){
            if (squareIsGood(positionsOfPieces.get(indexOfPiece)[X], positionsOfPieces.get(indexOfPiece)[Y])){
                setPieceOnTheBoard(indexOfPiece);
                return true;
            }
        }
        return false;
                
    }

    private static void setPieceOnTheBoard(int indexOfPiece){
        int thisX = positionsOfPieces.get(indexOfPiece)[X];
        int thisY = positionsOfPieces.get(indexOfPiece)[Y];
      
        chessBoardArray[thisX][thisY] = indexOfPiece;
    }

    

    private static boolean squareIsGood(int x, int y){
        for (int[] thisMove : getListOfQuieenMoves(x, y)) {
            if (chessBoardArray[thisMove[X]][thisMove[Y]] != -1){
                return false;
            }
        }        
        return true;
    }

    private static boolean pieceNotReachedTheEnd(int indexOfPiece){
        if (positionsOfPieces.containsKey(indexOfPiece)){
            if (positionsOfPieces.get(indexOfPiece)[X] + 1 < boardWidth){
                positionsOfPieces.get(indexOfPiece)[X]++;
            } else {
                if (positionsOfPieces.get(indexOfPiece)[Y] + 1 < boardHeight){
                    positionsOfPieces.get(indexOfPiece)[Y]++;
                    positionsOfPieces.get(indexOfPiece)[X] = 0;
                } else {                   
                    positionsOfPieces.get(indexOfPiece)[X] = 0;
                    positionsOfPieces.get(indexOfPiece)[Y] = 0;                                        
                    return false;
                }
            }
        } else {
            positionsOfPieces.put(indexOfPiece, new int[]{0,0});
        }
        return true;
    }   

    private static List<int[]> getListOfQuieenMoves(int currX, int currY) {
        List<int[]> result = new ArrayList<>();
                
        for (int x = 0; x < boardWidth; x++) {            
            result.add(new int[]{x, currY});
        }

        for (int y = 0; y < boardHeight; y++) {        
            result.add(new int[]{currX, y});
        }
        
        int boardLimit = (boardHeight < boardWidth) ? boardHeight : boardWidth;

        for (int i  = -boardLimit; i < boardLimit; i++) {            
            if (i + currX < boardWidth  && i + currX >= 0
            &&  i + currY < boardHeight && i + currY >= 0){                
                result.add(new int[]{i+currX, i+currY});            
            }
        } 
        
        for (int i  = -boardLimit; i < boardLimit; i++) {            
            if (i + currX < boardWidth  && i + currX >= 0
            &&  -i + currY < boardHeight && -i + currY >= 0){                
                result.add(new int[]{i+currX, -i+currY});            
            }
        } 
        return result;              
    }

    private static void printBoard() {
        for (int y = 0; y < boardHeight; y++) {
            for (int x = 0; x < boardWidth; x++) {
                if (chessBoardArray[x][y] == -1){
                    System.out.printf("%6s", "-");                
                } else {
                    System.out.printf("%6d", chessBoardArray[x][y]);               
                }
            }
            System.out.println('\n');
        }        
        System.out.println("-----------------------------------------");
    }

    private static void ClearChessboard(){
        for (int x = 0; x < boardWidth; x++) {
            for (int y = 0; y < boardHeight; y++) {
                chessBoardArray[x][y] = -1;
            }       
        }           
        
    }

    private static void printStateOfPieces(){
        for (Map.Entry<Integer, int[]> thisPiece : positionsOfPieces.entrySet()) {
            System.out.print(thisPiece.getKey() + ":" + Arrays.toString(thisPiece.getValue()) + "   ");            
        }
        System.out.println();
        
    }
}
