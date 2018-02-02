import java.io.*;
import java.util.*;
public class DataFileReader {
	public ArrayList<Media> openFile(FileReader file) throws FileNotFoundException {
		ArrayList<Media> masterList = new ArrayList<Media>();
		
		Scanner input = new Scanner(file);
		int size = 0;
		String[] list = new String[100000];
		while(input.hasNext()) {
			
			list[size] = input.nextLine();
			size++;
		}
		input.close();
		masterList=new ArrayList<Media>(size);
		for(int i = 0; i<size; i++) {
			if(list[i].lastIndexOf(',')==(list[i].length()-1))
				list[i]+=" ";
			if(list[i].indexOf('(')!=(list[i].lastIndexOf('('))) {
				list[i] = list[i].replaceAll("\\(","\\)");
				list[i] = list[i].replaceFirst("\\)","\\(");
			}
					
			list[i] = list[i].replaceAll("\\)",""); 
			
			String[] split = list[i].split("\\||\\(");//Split original string
			String[] split2 = split[2].split(",");
		
			//assign info to a string array - work
			String[] info = new String[4];
			info[0]= split[0].trim();
			info[1] = split[1].replaceAll("\\s+","");
			info[2]= split2[0].trim();
			info[3]= split2[1].trim();
			
			if(info[2].equals(""))
				info[2]+= " ";
			else
				info[2]=info[2].substring(0,info[2].indexOf("star"));
			
			//Using array info to create object
			if(info[3].contains("Serie")||info[3].contains("Episode")||info[3].contains("Season")||info[3].contains("Volume")) {
				masterList.add(new Series(info[0],info[1],info[2],info[3]));
			}
			else {
				masterList.add(new Movie(info[0],info[1],info[2],info[3]));
				
			}
			//System.out.println(info[0] + "\n" + info[1] + "\n" + info[2]+"\n"+info[3] + "\n-----------");		

		}
		return masterList;	
	}
}