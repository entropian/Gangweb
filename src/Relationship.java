
public class Relationship {
	public Integer id1 = null;
	public Integer id2 = null;
	public String status;
	
	public Relationship(){
	
	}
	
	public Relationship(Integer id1, Integer id2, String status){
		this.id1 = id1;	
		this.id2 = id2;
		this.status = status;
	}
}
