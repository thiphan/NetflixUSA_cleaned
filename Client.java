import java.io.*;
import java.util.*;
import java.util.ArrayList;

public class Client{
    public static void main(String[] args) throws FileNotFoundException{
        
        FileReader f = new FileReader("NetflixUSA_Oct15_cleaned.txt");
        DataFileReader file = new DataFileReader();
        ArrayList<Media> masterList = file.openFile(f);//initial list from the file
        
        ArrayList<Media> resultList = masterList; 
        
        ArrayList<Filter> filterList = createFilter(); 	       
        //filter master lists 
        for(Filter i: filterList) {
        	resultList.addAll(search(i, masterList));
        }
        System.out.println(resultList.size());
        System.out.println(filterList.size());
        resultList= intersection(resultList,filterList.size());
        if(resultList.size()==0)
        	System.out.println("There is no item as your looking!");
        else {
	        for(Media a: resultList) {
	        	System.out.println(a);
	        }
        }
        
        
    }
    
    /*create filter list*/
    public static ArrayList<Filter> createFilter(){
    	ArrayList<Filter> result = new ArrayList<Filter>();
    	Scanner inputInt = new Scanner(System.in);
    	Scanner inputString = new Scanner(System.in);
    	String exit="";
    	int choose = 0;
    	System.out.println("Filter list: \n"
    	        		+ "1. Genre = series \n"
    	        		+ "2. Genre = movie\n"
    	        		+ "3. Title match (title =)\n"
    	        		+ "4. Title contains\n"
    	        		+ "5. Before year\n"
    	        		+ "6. After year\n"
    	        		+ "7. Rating greater than...\n"
    	        		+ "8. Rating less than...\n"
    	        		+ "0. to finish your chosen");
		System.out.println("Please choose your filters (press ENTER after each chosen or press 0 to finish): ");
		while(!exit.equals("x")) {
    		try { 
    			choose = inputInt.nextInt();
    		}
    		catch(Exception e) {
    			System.out.println("Integer only please!");
    		}
    		 if(choose == 1) {
    			 Filter a = new Filter("genre","=","movie");
    			 result.add(a);
    		 }
    		 else if(choose ==2) {
    			 Filter b = new Filter("genre","=","series");
    			 result.add(b);
    		 }
    		 else if(choose ==3) {
    			 System.out.println("Enter the movie title: ");
    			 
    			 String t = inputString.nextLine();
    			 Filter c = new Filter("title","=",t);
    			 result.add(c);
    		 }
    		 else if(choose ==4) {
    			 System.out.print("Enter the words that title contains: ");
    			 String s = inputString.nextLine();
    			 Filter d = new Filter("title","contains",s);
    			 result.add(d);
    		 }
    		 else if(choose ==5) {
    			 System.out.println("Enter the year: ");
    			 String y1 = inputString.nextLine();
    			 Filter e = new Filter("year","<",y1);
    			 result.add(e);
    		 }
    		 else if(choose ==6) {
    			 System.out.println("Enter the year: ");
    			 String y3 = inputString.nextLine();
    			 Filter g = new Filter("title",">",y3);
    			 result.add(g);
    			 
    			 
    		 }
    		 else if(choose ==7) {
    			 System.out.println("Enter number of rating: ");
				 String r1 = inputString.next();
    			 Filter i = new Filter("rating",">",r1);
    			 result.add(i);    			 
    		 }
    		 else if(choose ==8) {
    			 System.out.println("Enter number of rating: ");
    			 String r2 = inputString.next();
    			 Filter j = new Filter("rating","<",r2);
    			 result.add(j);
    		 }
    		 else if(choose ==0) {
    			 exit="x";
    		 }
    		 else 
    			 System.out.println("We do not have that choice, please choose again!");
		}
    	System.out.println("Your filter list include: " );
    	for(Filter x: result) {
        	System.out.println(result.indexOf(x) + " - " + x+"\n");
    	}
    	
    	//create remove filter loop
    	String remove ="";
    	Scanner input = new Scanner(System.in);

    	while(!(remove.equals("N")||remove.equals("n"))) {
    		System.out.println("Do you want to remove any filter from the list? Y/N");
    		remove = inputString.next();
    		if(remove.equals("N")||remove.equals("n"))
    			break;
    		else if(remove.equals("Y")||remove.equals("y")) { 		
    			int removeChoose = 0;
	        	int validInput = 0;
	        	while(validInput==0) {
	            	System.out.println("Enter the index of filter you want to remove:");
	            	removeChoose = input.nextInt();
	    	    	if(removeChoose <0 || removeChoose > result.size()-1)
	    	    		System.out.println("You don't have that filter in your list, please choose another number between 0 -" + (result.size()-1));
	    	    	else
	    	    		validInput =1;
	        	}
	        	result.remove(choose);
	        	System.out.println("Your filter list now include: " );
	        	for(Filter x: result) {
	            	System.out.println(result.indexOf(x) + " - " + x);
	        	}
    		}
    		else
    			System.out.println("Please only press Y/N !");			
		}
    	input.close();
    	inputInt.close();
    	inputString.close();
    
    	return result;
    }
    
    
    /* search elements due to conditions*/
    public static ArrayList<Media> search(Filter f, ArrayList<Media> list) throws NumberFormatException {
    	ArrayList<Media> result = new ArrayList<Media>();
    	
    	//Field = genre
    	if(f.getField().equals("genre")) {
    		if(f.getTarget().equals("movie")) {
    			for(Media a: list) {
    				if(a instanceof Movie)
    					result.add(a);
    			}
    		}
    		else if(f.getTarget().equals("series")) {
    			for(Media a: list) {
    				if(a instanceof Series)
    					result.add(a);
    			}
    		}
    			
    	}
    	
    	//Field =  title
    	if(f.getField().equals("title")) {
    		if(f.getRelation().equals("=")) {
    			for(Media a: list) {
    				if((f.getTarget()).equals(a.getTitle()))
    					result.add(a);
    			}
    		}
    		else if(f.getRelation().equals("contains")) {
    			for(Media a: list) {
    				if(a.getTitle().contains(f.getTarget()))
    					result.add(a);
    			}
    		}
    	}
    	
    	//Field = year
    	if(f.getField().equals("year")) {
    		if(f.getRelation().equals("<")) {
    			for(Media a: list) {
    				if((a.getYear().compareTo(f.getTarget()))<0)
    					result.add(a);
    			}
    		}
    		else if(f.getRelation().equals(">")){
    			for(Media a: list) {
    				if((a.getYear().compareTo(f.getTarget()))>=0)
    					result.add(a);
    			}
    		}
    			
    	}
    	
    	//field = rating
    	if(f.getField().equals("rating")) {
    		try {
	    		if(f.getRelation().equals("<")) {
	    			for(Media a: list) {
	    				if((a.getRating().compareTo(f.getTarget()))>0)
	    					result.add(a);
	    			}
	    		}
	    		else {
	    			for(Media a: list) {
	    				if((a.getRating().compareTo(f.getTarget()))<0)
	    					result.add(a);
	    			}
	    		}
    		}
    		catch(NumberFormatException e) {
   			 System.out.println("Please enter only double type!");

    		}
    	}

    	return result;
    	
    }
    /*choose elements */
    public static ArrayList<Media> intersection(ArrayList<Media> list, int n) {
    	ArrayList<Media> result = new ArrayList<Media>();
    	int count=0;
    
    	for(Media a: list) {
    	   for(Media e:list) {
    		   if (a==e) 
    			   count++;
    	   }
    	   if(count==n)
    		   result.add(a);
    	   count=0;
	   }
    	return result;
    	
    }
  
}
