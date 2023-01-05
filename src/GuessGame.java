// import statements  
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
    
JFrame frameObj;
static JButton [] buttonArray = new JButton[9];
static ArrayList returnedList;
static ImageIcon guessedImage;
static ImageIcon placeholder = new ImageIcon("question.png");
static ArrayList<Object> guessArray = new ArrayList<>();
static ArrayList<Integer> guessArrayIndex = new ArrayList<>();
static HashMap<Integer, Integer> scores = new HashMap<>();
static int triesLeft = 5;

// constructor  
public GuessGame()  
{
//Create a new Frame    
frameObj = new JFrame();

//Random Image
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

//Initialize a new arraylist for the game
ArrayList<ImageIcon> gamePicList = new ArrayList<>();
//Pass the previous list items into the new list
for (ImageIcon icon: pictures) {
     gamePicList.add(icon);
   }
//Add the copies of the same list
gamePicList.add(image2);
gamePicList.add(image3);




//Shuffle List
returnedList = shuffleList(gamePicList);


for(int i = 0; i < 9; i++){
    buttonArray[i] = new JButton();
//    buttonArray[i].setIcon((Icon) returnedList.get(i));
buttonArray[i].setIcon(placeholder);
    buttonArray[i].addActionListener(this);
    frameObj.add(buttonArray[i]);
}



//new java.util.Timer().schedule( 
//        new java.util.TimerTask() {
//            @Override
//            public void run() {
//                // your code here
//                for(int i = 0; i < 9; i++){
//                buttonArray[i].setIcon(placeholder);
//}
//            }
//        }, 
//        2000 
//);

  
frameObj.setLayout(new GridLayout(3,3,0,0));     
frameObj.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
frameObj.setSize(800, 800);    
frameObj.setVisible(true);    
}  


private ArrayList shuffleList(ArrayList list) {
    Collections.shuffle(list);
    return list;
}



private void guessImage(int index) {
    
            if(guessArray.isEmpty()){
                guessArray.add(0, returnedList.get(index));
//                Flip                 
                buttonArray[index].setIcon((Icon) returnedList.get(index));
                guessArrayIndex.add(0,index);
                
//                System.out.println("first if");
            } else if(guessArray.size() == 1){
//                Flip 
                guessArrayIndex.add(1,index);
                buttonArray[index].setIcon((Icon) returnedList.get(index));
                guessArray.add(1, returnedList.get(index));
                String guessItem1 = guessArray.get(0).toString();
                String[] guessItem1Letters = guessItem1.split("[.]");
                String guessItem1LettersArray[] = Arrays.copyOf(guessItem1Letters, guessItem1Letters.length - 1);
                String guess1 = guessItem1LettersArray[0];
                String guessItem2 = guessArray.get(1).toString();
                String[] guessItem2Letters = guessItem2.split("[.]");
                String guessItem2LettersArray[] = Arrays.copyOf(guessItem2Letters, guessItem2Letters.length - 1);
                String guess2 = guessItem2LettersArray[0];

                if(guess1.equals(guess2)){
                    
//DO NOTHING
                } else{
//                    Decrement tries left
                    triesLeft--;
                    if(triesLeft==0){
                        JOptionPane.showMessageDialog(frameObj,"You Lost The Game!","Alert",JOptionPane.WARNING_MESSAGE); 
                        frameObj.setVisible(false);
                        triesLeft = 5;
                        new GuessGame();
                        
                    }
                    new java.util.Timer().schedule( 
                    new java.util.TimerTask() {
                        @Override
                            public void run() {
                        // your code here
                        buttonArray[guessArrayIndex.get(0)].setIcon(placeholder);
                        buttonArray[guessArrayIndex.get(1)].setIcon(placeholder);
                        guessArrayIndex.removeAll(guessArrayIndex);
                        guessArray.removeAll(guessArray);
                    }
                }, 
        500 
        );

                    
                }
//                System.out.println("second if");
            } else if(guessArray.size()== 2){
                guessArrayIndex.add(2,index);
//                Flip 
                buttonArray[index].setIcon((Icon) returnedList.get(index));
                guessArray.add(2, returnedList.get(index));
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
                if(guess1.equals(guess2)&& guess1.equals(guess3)){
                    guessArrayIndex.removeAll(guessArrayIndex);
                    guessArray.removeAll(guessArray);
                    int score = scores.get(triesLeft);    
                    String displayScore = "You won the game with score "+score;
                    JOptionPane.showMessageDialog(frameObj,displayScore,"Alert",JOptionPane.WARNING_MESSAGE); 
                    frameObj.setVisible(false);
                    triesLeft = 5;
                    new GuessGame();

                }else{
                    triesLeft--;
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
                        // your code here
                        buttonArray[guessArrayIndex.get(0)].setIcon(placeholder);
                        buttonArray[guessArrayIndex.get(1)].setIcon(placeholder);
                        buttonArray[guessArrayIndex.get(2)].setIcon(placeholder);
                        guessArrayIndex.removeAll(guessArrayIndex);
                        guessArray.removeAll(guessArray);
                    }
                }, 
        500 
        ); 
                }
                
            }

}



  
// main method  
public static void main(String argvs[])   
{    
     new GuessGame(); 
}    

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