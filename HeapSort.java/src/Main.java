import java.text.DateFormat;

public class Main{
     static String[] heap;
     static int size;
     static void swap(int i,int j){
          String temp = heap[i];
          heap[i] = heap[j];
          heap[j] = temp;
     }
     static void heapifyDown(int i){
          int left = 2*i +1;
          int right = 2*i +2;
          int largest = i;
          if(left < size && heap[left].compareTo(heap[largest]) > 0){
               largest = left;
          }
     }
}