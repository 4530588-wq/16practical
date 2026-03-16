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
          if(right < size && heap[right].compareTo(heap[largest]) > 0){
               largest = right;
          }
          if(largest != i){
               swap(i,largest);
               heapifyDown(largest);
          }
     }
     static void buildBottomUp(){
          for(int i=size/2 -1 ;i>0 ;i--){
               heapifyDown(i);
          }
     }
     static void insert(String word,int index){
          heap[index] = word;

          int i = index;
          while(i>0){
               int parent = (i -1)/2;
               if(heap[i].compareTo(heap[parent]) >0){
                    swap(i,parent);
                    i = parent;
               }
               else{
                    break;
               }
          }
     }
}