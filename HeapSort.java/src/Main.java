import java.text.DateFormat;
import java.text.DecimalFormat;
import java.io.*;
import java.util.*;

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
     static void buildTopDown(String[] words){
          heap = new String[words.length];
          size = 0;
          for(int i = 0;i< words.length;i++ ){
               insert(words[i],size);
               size++;
          }
     }
     static void heapSort(){
          int originalSize = size;
          for(int i = size -1;i>0;i--){
               swap(0,i);
               size--;
               heapifyDown(0);
          }
          size = originalSize;
     }
     static String[] readWordsFromFile(String filename) throws IOException {
          List<String> wordList = new ArrayList<>();
          BufferedReader br = new BufferedReader(new FileReader(filename));
          String line;

          while((line = br.readLine()) != null){
               line = line.trim();
               if(!line.isEmpty()){
                    String[] parts = line.split("\\s+");
                    for(String w : parts){
                         w = w.replaceAll("[^a-zA-Z]", "");
                         if(!w.isEmpty())
                              wordList.add(w.toLowerCase());
                    }
               }
          }
          br.close();
          return wordList.toArray(new String[0]);
     }
     public static void main(String[] args) throws IOException{
          DecimalFormat df = new DecimalFormat("0.0000");
          String filename = "C:\\Users\\Cscience1\\Documents\\15practical.zip\\16practical\\ulysses (1).numbered";
          String[] words = readWordsFromFile(filename);
          int N = words.length;
          heap = words.clone();
          size = N;
          long start1 = System.nanoTime();
          buildBottomUp();
          heapSort();
          long end1 = System.nanoTime();
          double bottomTime = (end1-start1)/1e6;
          long start2 = System.nanoTime();
          buildTopDown(words);
          heapSort();
          long end2 = System.nanoTime();
          double topTime = (end2-start2)/1e6;
          System.out.println("Number of words: " + N);
          System.out.println("Bottom-up heap: " + df.format(bottomTime) + " ms");
          System.out.println("Top-down heap:  " + df.format(topTime) + " ms");

          // Optional: first 50 sorted words
          System.out.println("\nFirst 50 sorted words:");
          for(int i = 0; i < Math.min(50, heap.length); i++){
               System.out.print(heap[i] + " ");
          }
          System.out.println();
     }
     }

