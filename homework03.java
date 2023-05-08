/*
 * Реализовать алгоритм пирамидальной сортировки (HeapSort)
 * реализация алгоритма из статьи https://en.wikipedia.org/wiki/Heapsort
 * реализованы оба алгоритма с просеиванием сверху и снизу
 *  
 * 
 */

import java.util.Arrays;

public class homework03 {
    private static int[] myArray;
    

    public static void main(String[] args) {
        myArray = new int[]{85, 92, 94, 19, 75, 81, 35, 36, 82, 61, 24, 70, 53, 31, 49, 70, 42, 18, 97, 53};
        //myArray = new int[]{85, 92, 94, 19, 75, 81, 35} ;
                
        System.out.println(Arrays.toString(myArray));
        
        //HeapSortSiftUp();
        HeapSortSiftDown();
        System.out.println(Arrays.toString(myArray));
    }

    private static void HeapSortSiftUp() {        
        int end = myArray.length - 1;
        HeapifySiftUp(end);        
        
        while (end > 0){
            SwapElements(0, end);
            HeapifySiftUp(end);
            end--;
        }        
    }    

    private static void HeapifySiftUp(int count){                
        int end = 1;       

        while (end < count){
            SiftUp(0, end);
            end++;
        }           
    }
    
    
    private static void SiftUp(int start, int end){        
        int child = end;
        
        while (child > start){
            int parent = indexOfParent(child);
            if (myArray[parent] < myArray[child]){
                SwapElements(parent, child);
                child = parent;
            } else {
                return;
            }
        }        
    }
    private static void HeapSortSiftDown() {        
        HeapifySiftDown(myArray.length);
        int end = myArray.length - 1;

        while (end > 0){
            SwapElements(end, 0);
            end--;
            SiftDown(0, end);
        }
    }    

    private static void HeapifySiftDown(int count){                
        int start = indexOfParent(count - 1);

        while (start >= 0){
            SiftDown(start, count - 1);
            start--;
        }
    }

    private static void SiftDown(int start, int end){
        int root = start;

        while (indexOfLeftChild(root) <= end){
            int child = indexOfLeftChild(root);
            int swap = root;

            if (myArray[swap] < myArray[child]){
                swap = child;
            }
            if (child + 1 <= end && myArray[swap] < myArray[child + 1]){
                swap = child + 1;
            } 

            if (swap == root){
                return;
            } else {
                SwapElements(root, swap);
                root = swap;
            }
        }
    }

    private static int indexOfParent(int index){
        return (index - 1) / 2;
    }

    private static int indexOfLeftChild(int index){
        return 2 * index + 1;
    }

    private static int indexOfRightChild(int index){
        return 2 * index + 2;
    }

    private static void SwapElements (int first, int second){        
        int temp = myArray[first];
        myArray[first] = myArray[second];
        myArray[second] = temp;        
    }
}


