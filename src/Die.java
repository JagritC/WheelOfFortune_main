/**
 *
 * @author 
 * @version 1.00 2014/10/14
 */

public class Die {
        
    /**
     * Creates a new instance of <code>Die</code>.
     */
     
    private int faces;  // attributes of a die
    private int value;
    
    public Die() {
    	this.faces = 6;
    	rollDie(); // creating a Die also rolls it
    }
    
    public Die (int faces){
    	this.faces = faces;
    	rollDie();
    }
    
    //method to roll my die
    public void rollDie(){
    	this.value = (int)(Math.random()*this.faces + 1);
    }
    
    // method to read my Die value
    public int getValue(){
    	return this.value;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        // TODO code application logic here
        Die d;
        d = new Die();
        
        System.out.println(d.getValue());
        
        Die d1  = new Die(12);
        System.out.println(d1.getValue());
    }
}
