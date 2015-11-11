package simpledatabase;
import java.util.ArrayList;

public class Selection extends Operator{
	
	ArrayList<Attribute> attributeList;
	String whereTablePredicate;
	String whereAttributePredicate;
	String whereValuePredicate;

	
	public Selection(Operator child, String whereTablePredicate, String whereAttributePredicate, String whereValuePredicate) {
		this.child = child;
		this.whereTablePredicate = whereTablePredicate;
		this.whereAttributePredicate = whereAttributePredicate;
		this.whereValuePredicate = whereValuePredicate;
		attributeList = new ArrayList<Attribute>();

	}
	
	
	/**
     * Get the tuple which match to the where condition
     * @return the tuple
     */
	@Override
	public Tuple next(){
		//Delete the lines below and add your code here
		if(whereTablePredicate==""){
			return child.next();
		}
		Tuple tuple;
		if((tuple=child.next())!=null){
		attributeList=tuple.getAttributeList();
		ArrayList<Attribute> arrayList=new ArrayList<Attribute>();
		for(int i=0;i<attributeList.size();i++){
			if(child.from.equals(whereTablePredicate)){
			if(attributeList.get(i).getAttributeName().equals(whereAttributePredicate)){
				if(attributeList.get(i).getAttributeValue().equals(whereValuePredicate)){
					return tuple;
				}
				else {
					return this.next();
				}
			}
			}
			else return tuple;
		}
		}
		return null;
	}
	
	/**
     * The function is used to get the attribute list of the tuple
     * @return the attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		
		return(child.getAttributeList());
	}

	
}