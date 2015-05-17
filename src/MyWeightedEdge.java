import org.jgrapht.graph.*;


public class MyWeightedEdge extends DefaultEdge {
	public String text;
	
	public void setText(String s){
		text = s;
	}
	
	public String toString(){
		return text;
	}
}
