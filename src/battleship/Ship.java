 /*
Alex Stevens
3/14/2017
MidTerm
Csis 123B-3183
0495503
 */
package battleship;


import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author gstev
 */
public class Ship
{
	private String shipName;
	char Direction;
	
        protected Image[] shipImageV;
        protected Image[] shipImageH;
        
        
       protected Image[] hitShipH;
       protected Image[] hitShipV;
       
       protected int[] DestroyV;
       protected int[] DestroyH;
        
      
       protected Label[] Destroyed;
       protected Label[] Built;
       
      protected ImageView[] n;
      protected ImageView[] d;
      protected int Health;
      
      protected int maxHealth;
       
      
      protected boolean isDestroy=false;
      
	Ship(String name,int[]V,int[]H,int[]DV,int[]DH,char Dir)
	{
            this.Direction = Dir;
            this.shipName = name;
            
            
            this.shipImageV = new Image[V.length];
            this.shipImageH = new Image[H.length];
            
             this.DestroyH = new int[DH.length];
             this.DestroyV = new int[DV.length];
             
             this.Assembler(DV,DH);
          this.hitShipH = new Image[H.length];
          this.hitShipV = new Image[V.length];
          
          this.Destroyed= new Label[H.length];
          this.Built = new Label[H.length];  
          
           Built = new Label[V.length];
           Destroyed = new Label[V.length];
          
            this.ImageLoader(V,H);
           // this.shipBuilder(V,H);
            this.DestroBuild(DestroyV, DestroyH);
           
           
                
            }
        
        public boolean hitShip(){
            
            this.Health--;
            
            if(Health==0){
               
                this.Health = this.maxHealth;
                this.isDestroy=true;
                return isDestroy;
            }
           
            this.isDestroy =false;
            return this.isDestroy;
              
        }
            
       private void ImageLoader(int[] V,int[] H){
                   for(int i=0;i<V.length;i++){
            
            shipImageV[i] =new Image("file:Images\\batt" + (V[i]+1) + ".gif");
          
            shipImageH[i] = new Image("file:Images\\batt" + (H[i]+1) + ".gif");
            

            
        }
               
           }
        

    
    private void DestroBuild(int[] V,int[] H){
        
        for(int i=0;i<V.length;i++){
            
            hitShipV[i] = new Image("file:Images\\batt" + V[i]+ ".gif");
            hitShipH[i] = new Image("file:Images\\batt" + H[i] + ".gif");
            
            
        }

    }
    public void Assembler(int[] V,int[]H){
        
       
        
        for(int i=0;i<V.length;i++){
       DestroyV[i]= V[i];
       DestroyH[i]=H[i];
            
            
        }
    }

	
	public String getName()
	{
		return this.shipName;
	}

	public int getDirection()
	{
		return this.Direction;
	}
	public int length()
	{
		return shipImageV.length;
	}

      public Label[] retDest(){
            
            if(Direction=='H'){
            d = new ImageView[hitShipH.length];
           
             for(int j=0;j<hitShipH.length;j++){
                
                d[j]= new ImageView(hitShipH[j]);
                Destroyed[j]= new Label();
                Destroyed[j].setGraphic(d[j]);
            }
            }
            else{
                 d = new ImageView[hitShipV.length];
           
             for(int j=0;j<shipImageH.length;j++){
                
                d[j]= new ImageView(hitShipV[j]);
                Destroyed[j]= new Label();
                Destroyed[j].setGraphic(d[j]);
            }
                
            }
             return this.Destroyed;

        }
        public Label[] retShip(){
            
            if(Direction=='H'){
           // return this.shipImageH;
           
           n = new ImageView[shipImageH.length];
           
             for(int j=0;j<shipImageH.length;j++){
                
                n[j]= new ImageView(shipImageH[j]);
                Built[j]= new Label();
                Built[j].setGraphic(n[j]);
            }

           return this.Built;
 
            }
            else{
              //  return this.shipImageV;
              n = new ImageView[shipImageV.length];
           
             for(int j=0;j<shipImageV.length;j++){
                
                n[j]= new ImageView(shipImageV[j]);
                Built[j]= new Label();
                Built[j].setGraphic(n[j]);
            }
                           return this.Built;

            }
            
        }

}




class Frigate extends Ship{
   
    static int[] frigateH = {0,1,4};
   static int[] frigateV = {5,6,9};
   
   static int[] frigHitH = {201,202,203};
   static int[] frigHitV ={204,205,206};
   
    public Frigate(char Direction) {
        super("Frigate",frigateV,frigateH,frigHitV,frigHitH,Direction);
       // super.Direction=Direction;
       super.Health=3;
       super.maxHealth=3;
        
    }
   
    
    
}
class MineSweep extends Ship{
    
   static int[] mineSweepH = {0,4};
   static int[] mineSweepV = {5,9};
   
   static int[] mSweepHitH = {201,203};
   static int[] mSweepHitV = {204,206};
    
    public MineSweep(char Direction) {
        super("MineSweep",mineSweepV,mineSweepH,mSweepHitV,mSweepHitH,Direction);
        super.Health =2;
        super.maxHealth=2;
      //  super.Direction=Direction;
        
    }
    
    
    
}
class Cruiser extends Ship{
    
   static int[] cruiserH = {0,1,2,4};
   static int[] cruiserV = {5,6,7,9};
    
   static int[] cruiseHitH ={201,202,202,203};
   static int[] cruiseHitV ={204,205,205,206};
    
    public Cruiser(char Direction) {
        super("Cruiser",cruiserV,cruiserH,cruiseHitV,cruiseHitH,Direction);
        super.Health = 4;
        super.maxHealth=4;
       // super.Direction = Direction;
      
    }
    
    
    
}

class BattleShip extends Ship{
   static int[] battShipV ={5,6,7,8,9};
  static int[] battShipH ={0,1,2,3,4};
  
  static int[] bShipHitH ={201,202,202,202,203};
  static int[] bShipHitV = {204,205,205,205,206};
    
    public BattleShip(char Direction) {
       super("BattleShip",battShipV,battShipH,bShipHitV,bShipHitH,Direction);
       super.Health =5;
       super.maxHealth=5;
      // super.Direction = Direction; 
      
    }
    
    
    
}