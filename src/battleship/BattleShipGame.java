 /*
Alex Stevens
3/14/2017
MidTerm
Csis 123B-3183
0495503
 */
package battleship;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.image.*;
import java.awt.Color;
import java.awt.Desktop.Action;
import javafx.geometry.Insets;
import java.util.Random;
import javafx.event.Event;
import javafx.scene.input.MouseEvent;
import static javafx.scene.paint.Color.*;

/**
 *
 * @author gstev
 */
public class BattleShipGame extends Application  {
    
    private static final int MAXSHIPS = 14;
    private static final int GRIDSIZE  = 16;
    private GridPane pnlPlayer = new GridPane();
    private Label[][] lblPlayer = new Label[GRIDSIZE][GRIDSIZE];
    private Ship[] shipInfo = new Ship[8];
    private char[][] ocean = new char[16][16];    
    private GridPane controlPane = new GridPane();
    private Button reset = new Button("Reset");
    private Button showShips = new Button("Show Ships");
    private int missCount = 0;
    private Label infoLabel = new Label();
    private String miss = "Missed: ";
    
    private Label[][] shipBoard = new Label[GRIDSIZE][GRIDSIZE];
    private Label[][] hitShips = new Label[GRIDSIZE][GRIDSIZE];
    
    private Ship[][] Command = new Ship[GRIDSIZE][GRIDSIZE];
    
   private int numberShips=14;
    
  
    
    @Override
    public void start(Stage primaryStage) {
                
        BorderPane root = new BorderPane();
                
        Scene scene = new Scene(root, 290,390);
        
        primaryStage.setTitle("Battleship");
        primaryStage.setScene(scene);
        primaryStage.show();
        this.newGame();   // new game method handles the new game procedures
        //this.initOcean();
        //this.createPlayerPanel();
        this.createOptionPane();
        root.setCenter(pnlPlayer);
        root.setTop(controlPane);
        //createShips();
     
        //placeShips();
      
        
        this.reset.setOnAction(new EventHandler<ActionEvent>() {   // button event for reset button that will start a new game.
            @Override
            public void handle(ActionEvent event) {
               revealShips();
               newGame();
            }
        }
        
        );
        
        
        this.showShips.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            
              revealShips();
            }
            
        });
        
        
        
    }
    
    private void newGame(){ 
        // call this to reset everything and start a new game or at beginning of game launch.
        this.createPlayerPanel();
        
        this.initOcean();
        this.createShipInfo();
        this.placeShips();
        this.missCount=0;
        infoLabel.setText(miss+""+missCount);
        this.reset.setText("Reset");
           this.showShips.setText("Show Ships");
           this.numberShips=14;
        
    }
      
    private void createOptionPane()     // creates the panel to house reset,show boats, and # missed shots 
    {
      controlPane.setStyle("-fx-background-color:BLACK;");
       
       controlPane.setHgap(10);
       
       infoLabel.setText(miss+""+missCount);
       controlPane.add(infoLabel,0,0);
        infoLabel.setTextFill(LIGHTGREEN);
        
      
       controlPane.add(reset,1,0);
   
            reset.setTextFill(YELLOW);
            
            reset.setStyle("-fx-border-width:1;-fx-border-color:YELLOW;-fx-background-color:BLACK;");
            
       controlPane.add(showShips,2,0);
           showShips.setTextFill(YELLOW);
          
           showShips.setStyle("-fx-border-width:1;-fx-border-color:YELLOW;-fx-background-color:BLACK;");
       
      
        
    }
    private void createPlayerPanel()
    {
       pnlPlayer.setStyle("-fx-background-color:#0000FF;");
       for(int row = 0; row < GRIDSIZE; row++)
       {
           for(int col = 0; col < GRIDSIZE; col++)
           {
               lblPlayer[row][col] = new Label();
               
               Image imgShip = new Image("file:Images\\batt100.gif");
               lblPlayer[row][col].setGraphic(new ImageView(imgShip));
               lblPlayer[row][col].setMaxSize(16.0, 16.0);
               lblPlayer[row][col].setStyle("-fx-border-width:1;-fx-border-color:black;");
               
               
               hitShips[row][col] = new Label();
               shipBoard[row][col] = new Label();
               
                pnlPlayer.add(lblPlayer[row][col], col, row);      

               
               lblPlayer[row][col].setOnMouseClicked(new EventHandler<MouseEvent>()
               {
                   @Override
                   public void handle(MouseEvent t) {
                       
                       for(int row=0;row<GRIDSIZE;row++){
                           for(int col=0;col<GRIDSIZE;col++){
                               
                               Label clicked = (Label) t.getSource();
                               
                               if(clicked == lblPlayer[row][col]){
                                   
                                   char Seeker = Character.toUpperCase(ocean[row][col]);
                                   
                                   
                                   switch(Seeker){
                                       
                                       case 'O':
                                           lblPlayer[row][col].setGraphic(new ImageView("file:Images\\batt102.gif"));
                                           missCount++;
                                           infoLabel.setText(miss+""+missCount);
                                         break;
                                         
                                       case 'B': 
                                           
                                           checkHit(row,col);
                                           break;
                                       case 'F':
                                           checkHit(row,col);
                                           break;
                                       case 'M':
                                           checkHit(row,col);
                                           break;
                                       case 'C':
                                           checkHit(row,col);
                                           break;
                                           
                                        
                                           
                                       
                                   }
                                   
                               }
                               
                           }
                           
                           
                       }
                   
                       
                   }
                   
               });
               
               

           }
       }
    }
    
    private void checkHit(int row,int col){
        
        ocean[row][col]='H';
        lblPlayer[row][col].setGraphic(new ImageView("file:Images\\batt103.gif"));
        
       Boolean Check =Command[row][col].hitShip();
       
       if(Check == true){
           
           numberShips--;
           checkEndGame();
           for(int r=0;r<GRIDSIZE;r++){
         for(int c=0;c<GRIDSIZE;c++){
             
          
           if(ocean[r][c]=='H')      
           lblPlayer[r][c].setGraphic(hitShips[r][c]);
           
      
            
         }
        }
          
       }
    }
    

    
 private void checkEndGame(){
     if(numberShips==0){
         
         this.reset.setText("New Game");
           this.showShips.setText("You Win");
         
     }
     
     
 }
          
    private void revealShips(){
        
        for(int row=0;row<GRIDSIZE;row++){
         for(int col=0;col<GRIDSIZE;col++){
             
             char Seeker = Character.toUpperCase(ocean[row][col]);
             
             switch(Seeker){
             
                 case 'B':
                 case 'F':
                 case 'M':
                 case 'C':
                     lblPlayer[row][col].setGraphic(shipBoard[row][col]);
                     break;
                 case 'H':
                     lblPlayer[row][col].setGraphic(hitShips[row][col]);
                     break;
                    
             
         }
        }
        }
    }
  
             

   
  
   
    private void createShipInfo()
    {
        //Start with the frigate, we create 2 of them here but will place 3 total randomly it as two images
		shipInfo[0] = new Frigate('H');
		shipInfo[1] = new Frigate('V');
		
        // Create the mine Sweep it has 3 pieces
		shipInfo[2] = new MineSweep('H');
		shipInfo[3] = new MineSweep('V');
		
		shipInfo[4] = new Cruiser('H');
		shipInfo[5] = new Cruiser('V');
		
		shipInfo[6] = new BattleShip('H');
		shipInfo[7] = new BattleShip('V'); 
                
               
    }
    private void initOcean()
    {
        for(int row = 0; row < 16; row++)
        {
            for(int col = 0; col < 16; col++)
            {
                    ocean[row][col] = 'O';
                    Command[row][col]=null;
            }
        }
    }
    private void placeShips()
    {
       // Create a Random object to select ships
        Random r = new Random();

        // Create random objects to place the ship at a row and a column
        Random randCol = new Random();
        Random randRow = new Random();

        //Place the ships, typically there are 14
        for(int ships = 0; ships < MAXSHIPS; ships++)
        {
                //Get a random ship
                Ship si = shipInfo[r.nextInt(8)];

                int row = randRow.nextInt(16);
                int col = randCol.nextInt(16);
                int direction = checkDirection(si, row, col);
                while(direction == 0) // 0 direction says that we can not place the ship
                {
                        row = randRow.nextInt(16);
                        col = randCol.nextInt(16);
                        direction = checkDirection(si, row, col);
                }
                // got a clear path, let put the ship on the ocean
               Label[] hBoat = si.retShip();   //houses the boat piece image labels
               
               Label[] hDBoat = si.retDest();  // houses the hit ships image labels
                if(si.Direction == 'H')  // place horizontal
                {
                        if(direction == 1)
                        {
                            for(int i = col, j = 0; i < col + si.length(); i++, j++)
                            {    
                                
                                Command[row][i]=si;
                                shipBoard[row][i].setGraphic(hBoat[j]);
                                hitShips[row][i].setGraphic(hDBoat[j]);
                                String name = si.getName();
                                ocean[row][i] = name.charAt(0);
                            }
                        }
                        else
                        {
                            for(int i = col + si.length(), j = 0 ; i > col; i--, j++)
                            {
                            
                               Command[row][i]=si;
                            shipBoard[row][i].setGraphic(hBoat[j]);
                                hitShips[row][i].setGraphic(hDBoat[j]);    
                            
                                String name = si.getName();
                                ocean[row][i] = name.charAt(0);
                            }
                        }
                }
                else // Must be vertical direction
                {
                        if(direction == 1) // place pieces in positive direction
                        {
                            for(int i = row, j = 0; i < row + si.length(); i++, j++)
                            {
                                Command[i][col]=si;
                                shipBoard[i][col].setGraphic(hBoat[j]);
                                hitShips[i][col].setGraphic(hDBoat[j]);
                                String name = si.getName();
                                ocean[i][col] = name.charAt(0);
                            }
                        }
                        else
                        {
                                for(int i = row + si.length(), j = 0; i > row; i--, j++)
                                {
                                 Command[i][col]=si;   
                                 shipBoard[i][col].setGraphic(hBoat[j]);
                                hitShips[i][col].setGraphic(hDBoat[j]);
                                    String name = si.getName();
                                    ocean[i][col] = name.charAt(0);
                                }
                        }
                }			
        } 
    }
    private int checkDirection(Ship si, int row, int col)
    {
        if(si.Direction == 'H')
            return checkHorizontal(si, row, col);
        else
            return checkVertical(si, row, col);
    }
    int checkHorizontal(Ship si,int row, int col)
    {
            boolean clearPath = true;

            int len = si.length();
            System.out.println(len);
            for(int i = col; i < (col + si.length()); i++)
            {
                    if(i >= GRIDSIZE) //This would put us outside the ocean
                    {
                            clearPath = false;
                            break;
                    }
                    if(ocean[row][i] != 'O') // Ship already exists in this spot
                    {
                            clearPath = false;
                            break;
                    }
            }
            if(clearPath == true) // ok to move in the positive direction
                    return 1; 

            //Next Chec the negative direction
            for(int i = col; i > (col - si.length()); i--)
            {
                    if(i < 0) //This would put us outside the ocean
                    {
                            clearPath = false;
                            break;
                    }
                    if(ocean[row][i] != 'O') // Ship already exists in this spot
                    {
                            clearPath = false;
                            break;
                    }			

            }
            if(clearPath == true) //Ok to move in negative direction
                    return -1;
            else
                    return 0;   // No place to move			

    }
	
    int checkVertical(Ship si,int row, int col)
    {
            boolean clearPath = true;
            int len = si.length();
            System.out.println(len);
            

            for(int i = row; i < (row + si.length()); i++)
            {
                if(i >= GRIDSIZE) //This would put us outside the ocean
                {
                        clearPath = false;
                        break;
                }
                if(ocean[i][col] != 'O') // Ship already exists in this spot
                {
                        clearPath = false;
                        break;
                }
            }
            if(clearPath == true) // ok to move in the positive direction
                    return 1; 

            //Next Check the negative direction
            for(int i = row; i > (row - si.length() ); i--)
            {
                if(i < 0) //This would put us outside the ocean
                {
                        clearPath = false;
                        break;
                }
                if(ocean[i][col] != 'O') // Ship already exists in this spot
                {
                        clearPath = false;
                        break;
                }			

            }
            if(clearPath == true) //Ok to move in negative direction
                return -1;
            else
                return 0;   // No place to move			

    }
 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
