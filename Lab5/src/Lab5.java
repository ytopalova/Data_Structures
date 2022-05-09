import java.util.*;

public class Lab5 {

   private static Scanner scan;
   /**
    * BinarySearch method
    * @param list_item
    * @param num
    * @return -1 if element not found
    */
   public int BinarySearch(ArrayList<Integer> list_item, int num) { //num is the number to be searched
   
       int low = 0; 
       int mid;                             //low is the starting index =0
       int high = list_item.size()-1;       //high is the index of last element in list
                                           
       while(low <= high){
           mid = low + (high - low)/2;      //mid is the index of middle element
           if(list_item.get(mid) == num)    //checking if num is present at mid .
               return mid;
           if(list_item.get(mid) < num)     //ignore left half, if num > mid
               low = mid + 1;
           else 							//ignore right half, if num < mid
               high = mid - 1;      
       }
       return -1;                           //if element not found return -1  
   }
   
   /**
	 * Main method of the class outputs menu
	 * reads input from Scanner
	 * and implements our methods.
	 * @param args String
	 */
   public static void main(String[] args){
	   
       int choice;
       int index;
       scan = new Scanner(System.in);
       ArrayList<Integer> list_item = new ArrayList<Integer>();   
       while(true)
       {
           System.out.println("Please Enter:");
           System.out.println("1. Add Item");                       
           System.out.println("2. Search for an item");
           System.out.println("3. Display List");
           System.out.println("4. Exit");
          
           choice=scan.nextInt();                                   

           switch(choice)
           {
               case 1: System.out.println("Please specify an integer");
                       list_item.add(scan.nextInt());                   
                       break;

               case 2: System.out.println("Please specify an integer");
                       Lab5 SearchBinary = new Lab5();               
                       index=SearchBinary.BinarySearch(list_item,scan.nextInt());  
                                                                       
                       if(index==-1)                                
                           System.out.println("Index of integer is: Not found");  
                       else
                           System.out.println("Index of integer is: "+index);
                       break;

               case 3: System.out.println(list_item);
                       break;                                   

               case 4: System.out.println("Exiting...");       
                       return ;

               default :System.out.println("Enter a valid integer");  
           }
           Collections.sort(list_item);                       
           System.out.println();
       }
   }
}