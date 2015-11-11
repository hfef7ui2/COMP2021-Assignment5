package simpledatabase;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Sort extends Operator{
	
	private ArrayList<Attribute> newAttributeList;
	private String orderPredicate;
	ArrayList<Tuple> tuplesResult;

	
	public Sort(Operator child, String orderPredicate){
		this.child = child;
		this.orderPredicate = orderPredicate;
		newAttributeList = new ArrayList<Attribute>();
		tuplesResult = new ArrayList<Tuple>();
		
	}
	
	
	/**
     * The function is used to return the sorted tuple
     * @return tuple
     */
	@Override
	public Tuple next(){
		//Delete the lines below and add your code here
		Tuple tuple;
		ArrayList<Tuple> tempArrayList=new ArrayList<Tuple>();
		while((tuple=child.next())!=null){
			tempArrayList.add(tuple);
		}
		String[] strings=new String[tempArrayList.size()];
		for(int i=0;i<tempArrayList.size();i++){
			for(int j=0;j<tempArrayList.get(i).getAttributeList().size();j++){
				if(tempArrayList.get(i).getAttributeName(j).equals(orderPredicate)){
					strings[i]=tempArrayList.get(i).getAttributeValue(j).toString();
				}
			}
		}
		if(tuplesResult.size()==0){
		for(int j=0;j<tempArrayList.size();j++){
			String min=null;
			for(int p=0;p<tempArrayList.size();p++){
				if(strings[p]!="|"){
					min=strings[p];
					break;
				}
			}
		int a=-1;
		for(int i=0;i<tempArrayList.size();i++){
			if(strings[i].compareTo(min)<0&&strings[i]!="|"){
				min=strings[i];
				a=i;
			}
		}
		if(a==-1){
			for(int p=0;p<tempArrayList.size();p++){
				if(strings[p]!="|"){
					a=p;
					break;
				}
			}
		}
		tuplesResult.add(tempArrayList.get(a));
		strings[a]="|";
		}
		}
		if(tuplesResult.size()==0)
			return null;
		Tuple tuple2=tuplesResult.remove(0);
		return tuple2;
	}
	
	/**
     * The function is used to get the attribute list of the tuple
     * @return attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		return child.getAttributeList();
	}

}