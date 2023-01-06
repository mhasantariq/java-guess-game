//import necessary Libraries
import java.awt.*;    
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import javax.swing.*;    
import java.util.Random; 
import java.util.HashMap; 


    
public class GuessGame implements ActionListener
{    

//Frame    
JFrame frameObj;

//Array of 9 buttons
static JButton [] buttonArray = new JButton[9];

//The list that gets shuffled (contains the pictures arraylist as well as the 2 copies of randomly selected image)
static ArrayList shuffledList;

//Image picked randomly from the arrayList of 7 images
static ImageIcon guessedImage;

//Placeholder image
static ImageIcon placeholder = new ImageIcon("question.png");

//This stores the images you guess on each click
static ArrayList<Object> guessArray = new ArrayList<>();

//This stores the references to the index of images you click (is useful when you have to flip back the cards on wrong image selection)
static ArrayList<Integer> guessArrayIndex = new ArrayList<>();

//HashMap is for mapping tries left to the score i.e- For 100 score triesLeft should be 5 (means on first try you guessed the images)
static HashMap<Integer, Integer> scores = new HashMap<>();

//Tracks the tries
static int triesLeft = 5;

// constructor  
public GuessGame()  
{
//Create a new Frame    
frameObj = new JFrame();

//Pick a random image from an array of 7 elements
Random rand = new Random(); 
int getRandomImage = rand.nextInt(6 - 0 + 1) + 0;

//Defined the images
ImageIcon imageCat = new ImageIcon("cat.png");
ImageIcon imageLion = new ImageIcon("lion.png");
ImageIcon imageFox = new ImageIcon("fox.png");
ImageIcon imageTiger = new ImageIcon("tiger.png");
ImageIcon imageDog = new ImageIcon("dog.png");
ImageIcon imageEagle = new ImageIcon("eagle.png");
ImageIcon imageSnake = new ImageIcon("snake.png");

//Stored the images inside an arrayList
ArrayList<ImageIcon> pictures = new ArrayList<>();
pictures.add(imageCat);
pictures.add(imageLion);
pictures.add(imageFox);
pictures.add(imageTiger);
pictures.add(imageDog);
pictures.add(imageEagle);
pictures.add(imageSnake);

//Populating scores map
scores.put(5, 100);
scores.put(4, 80);
scores.put(3, 60);
scores.put(2, 40);
scores.put(1, 20);



//Get Random image from the list
 guessedImage = pictures.get(getRandomImage);

//Make its two copies
ImageIcon image2 = guessedImage;
ImageIcon image3 = guessedImage;

//Initialize a new arraylist (This arrayList maps the corresponding image to each button element)
ArrayList<ImageIcon> gamePicList = new ArrayList<>();
//Pass the previous list items into the new list
for (ImageIcon icon: pictures) {
     gamePicList.add(icon);
   }
//Add the copies of the same list
gamePicList.add(image2);
gamePicList.add(image3);




//Shuffle List
shuffledList = shuffleList(gamePicList);


for(int i = 0; i < 9; i++){
    //Added 9 buttons to the buttons array, set the icons, add click handler to each button 
    buttonArray[i] = new JButton();    
    buttonArray[i].setIcon(placeholder);
    buttonArray[i].addActionListener(this);
    frameObj.add(buttonArray[i]);
}



//  Set the Layout
frameObj.setLayout(new GridLayout(3,3,0,0));     
frameObj.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
frameObj.setSize(800, 800);    
frameObj.setVisible(true);    
}  

//Function that the shuffles the array List
private ArrayList shuffleList(ArrayList list) {
    Collections.shuffle(list);
    return list;
}


//Game Logic
private void guessImage(int index) {
            //When the first image is clicked
            if(guessArray.isEmpty()){
                
                //Add the image, with its index number to the necessary arrayLists                 
                guessArray.add(0, shuffledList.get(index));            
                guessArrayIndex.add(0,index);
                
                //Reflect the changes on the frontend                
                buttonArray[index].setIcon((Icon) shuffledList.get(index));
                
                //When the second image is clicked   
            } else if(guessArray.size() == 1){

                //Add the image, with its index number to the necessary arrayLists    
                guessArray.add(1, shuffledList.get(index));
                guessArrayIndex.add(1,index);
                
                //Reflect the changes on the frontend                
                buttonArray[index].setIcon((Icon) shuffledList.get(index));
                
                //As the images are stored as an object we convert em to string we get i.e: dog.png so we ignore the png part and compare the left part
                String guessItem1 = guessArray.get(0).toString();
                String[] guessItem1Letters = guessItem1.split("[.]");
                String guessItem1LettersArray[] = Arrays.copyOf(guessItem1Letters, guessItem1Letters.length - 1);
                String guess1 = guessItem1LettersArray[0];
                String guessItem2 = guessArray.get(1).toString();
                String[] guessItem2Letters = guessItem2.split("[.]");
                String guessItem2LettersArray[] = Arrays.copyOf(guessItem2Letters, guessItem2Letters.length - 1);
                String guess2 = guessItem2LettersArray[0];

                //Compare the images
                if(guess1.equals(guess2)){  
                //DO NOTHING
                } else{
                    //Decrement tries left
                    triesLeft--;
                    
                    //If there are no remaining tries display a message, hide the previour frame, reset triesLeft variable, start a new game
                    //Lost the game
                    if(triesLeft==0){
                        JOptionPane.showMessageDialog(frameObj,"You Lost The Game!","Alert",JOptionPane.WARNING_MESSAGE); 
                        frameObj.setVisible(false);
                        triesLeft = 5;
                        new GuessGame();
                    }
                    
                    //Flip back cards and clear the guessArray,guessArrayIndex after the 200ms delay
                    new java.util.Timer().schedule( 
                    new java.util.TimerTask() {
                        @Override
                            public void run() {
                        buttonArray[guessArrayIndex.get(0)].setIcon(placeholder);
                        buttonArray[guessArrayIndex.get(1)].setIcon(placeholder);
                        guessArrayIndex.removeAll(guessArrayIndex);
                        guessArray.removeAll(guessArray);
                    }
                }, 
        200 
        );       
                }

                //When the third image is clicked  
            } else if(guessArray.size()== 2){
                
                //Add the image, with its index number to the necessary arrayLists  
                guessArray.add(2, shuffledList.get(index));
                guessArrayIndex.add(2,index);
                
                //Reflect the changes on the frontend    
                buttonArray[index].setIcon((Icon) shuffledList.get(index));
                
                //As the images are stored as an object we convert em to string we get i.e: dog.png so we ignore the png part and compare the left part
                //Compare three images
                String guessItem1 = guessArray.get(0).toString();
                String[] guessItem1Letters = guessItem1.split("[.]");
                String guessItem1LettersArray[] = Arrays.copyOf(guessItem1Letters, guessItem1Letters.length - 1);
                String guess1 = guessItem1LettersArray[0];
                String guessItem2 = guessArray.get(1).toString();
                String[] guessItem2Letters = guessItem2.split("[.]");
                String guessItem2LettersArray[] = Arrays.copyOf(guessItem2Letters, guessItem2Letters.length - 1);
                String guess2 = guessItem2LettersArray[0];
                String guessItem3 = guessArray.get(2).toString();
                String[] guessItem3Letters = guessItem3.split("[.]");
                String guessItem3LettersArray[] = Arrays.copyOf(guessItem3Letters, guessItem3Letters.length - 1);
                String guess3 = guessItem3LettersArray[0];
                
                //Compare the images
                //Winning case
                if(guess1.equals(guess2)&& guess1.equals(guess3)){
                    
                    //Clear the guessArray and guessArrayIndex
                    guessArrayIndex.removeAll(guessArrayIndex);
                    guessArray.removeAll(guessArray);
                    
                    //Calculate the scores
                    int score = scores.get(triesLeft);    
                    
                    //Display the winning message
                    String displayScore = "You won the game with score "+score;
                    JOptionPane.showMessageDialog(frameObj,displayScore,"Alert",JOptionPane.WARNING_MESSAGE); 
                    
                    //Reset the variables and restart the game
                    frameObj.setVisible(false);
                    triesLeft = 5;
                    new GuessGame();

                }else{
                    //Decrement tries left
                    triesLeft--;
                    
                    //When there are no tries left, Losing case, restart game, reset variables
                    if(triesLeft==0){
                        JOptionPane.showMessageDialog(frameObj,"You Lost The Game!","Alert",JOptionPane.WARNING_MESSAGE);
                        frameObj.setVisible(false);
                        triesLeft=5;
                        new GuessGame();
                    }
                    
                    new java.util.Timer().schedule( 
                    new java.util.TimerTask() {
                        @Override
                            public void run() {
                        // Flip back the images and clear the guessArray and guessArrayIndex
                        buttonArray[guessArrayIndex.get(0)].setIcon(placeholder);
                        buttonArray[guessArrayIndex.get(1)].setIcon(placeholder);
                        buttonArray[guessArrayIndex.get(2)].setIcon(placeholder);
                        guessArrayIndex.removeAll(guessArrayIndex);
                        guessArray.removeAll(guessArray);
                    }
                }, 
        200 
        ); 
                }
                
            }

}



  
// main method  
public static void main(String argvs[])   
{    
     new GuessGame(); 
}    

    //Click Handling
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == buttonArray[0]){
            guessImage(0);
                

        } else if(e.getSource() == buttonArray[1]) {
            guessImage(1);
        }else if(e.getSource() == buttonArray[2]) {
            guessImage(2);

        }else if(e.getSource() == buttonArray[3]) {
                guessImage(3);

        }else if(e.getSource() == buttonArray[4]) {
           guessImage(4);

        }else if(e.getSource() == buttonArray[5]) {
            guessImage(5);
            
        }else if(e.getSource() == buttonArray[6]) {
           guessImage(6);
            
        }else if(e.getSource() == buttonArray[7]) {
            guessImage(7);
            
        }else if(e.getSource() == buttonArray[8]) {
          guessImage(8);
            
        }
        
    }

}    