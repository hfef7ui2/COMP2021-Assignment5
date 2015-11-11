package simpledatabase;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Table extends Operator{
	private BufferedReader br = null;
	private boolean getAttribute=false;
	private Tuple tuple;

	
	public Table(String from){
		this.from = from;
		
		//Create buffer reader
		try{
			br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/datafile/"+from+".csv")));
		}
		catch (Exception e) {
			e.printStackTrace();
		} 
		
	}

	
	/**
     * Create a new tuple and return the tuple to its parent.
     * Set the attribute list if you have not prepare the attribute list
     * @return the tuple
     */
	@Override
	public Tuple next(){
		//Delete the lines below and add your code here
		try {
			String temp;
			ArrayList<Attribute> arrayList=new ArrayList<Attribute>();
			BufferedReader br2=new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/datafile/"+from+".csv")));
			String[] string1=br2.readLine().split(",");
			String[] string2=br2.readLine().split(",");
			String[] strings=null;
			if(!getAttribute){
			string1=br.readLine().split(",");
			string2=br.readLine().split(",");
			getAttribute=true;
			}
			if((temp=br.readLine())!=null){
			strings=temp.split(",");
			int j=0;
			while(j<strings.length){
				Attribute attribute=new Attribute();
				attribute.setAttributeName(string1[j]);
				attribute.setAttributeType(string2[j]);
				attribute.setAttributeValue(attribute.getAttributeType(), strings[j]);
				arrayList.add(attribute);
				j++;
			}
			}
			else return null;
			if(!arrayList.isEmpty())
			tuple=new Tuple(arrayList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tuple;
	}
	

	/**
     * The function is used to get the attribute list of the tuple
     * @return the attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		return tuple.getAttributeList();
	}
}