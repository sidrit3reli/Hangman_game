import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class JSecretPhrase2 extends JPanel implements ActionListener
{
    final int NUM = 26;
    JLabel greeting = new JLabel("Secret Phrase Game");
    JLabel promptLabel = new JLabel("Choose one button");
    Font headlineFont = new Font("Arial", Font.BOLD, 30);
    Font mediumFont = new Font("Arial", Font.BOLD, 18);
    JButton[] button = new JButton[NUM];
    String[] letter = {"A", "B", "C", "D", "E", "F", "G", "H", "I",
       "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
       "V", "W", "X", "Y", "Z"};
    JLabel resultsLabel = new JLabel("");
    JLabel result1 = new JLabel();
    JLabel result2 = new JLabel();
    JLabel result3 = new JLabel();
    JLabel result4 = new JLabel();
    int playerCount = 0;
    int computerCount = 0;
    int tieCount = 0;
    String[] targetPhrases = {"The Wizard of Oz", "Gone With The Wind", "Casablanca",
       "Chicago", "Top Hat", "White Christmas", "The Sound of Music", "Guys and Dolls",
       "Its a Mad Mad Mad Mad World", "Defending Your Life"};
    String displayPhrase = "";
    String target;
    String buildDisplayPhrase;
    String prompt;
    String play = "  Play our game - guess the phrase  ";
    String enterOne = "  Enter one letter  ";
    String sorry = "  Sorry - not in the phrase: ";
    String correct = "  Correct!                   ";
    String losingMsg = "Sorry - you are hanged";
    String userResponse;
    char guess;
    char oneLetter;
    boolean found = false;
    int position;
    int oldPos = 0;
    int x;
    int random;
    int len;
    int wrongGuesses = 0;
    public JSecretPhrase2()
    {
       greeting.setFont(headlineFont);
       result1.setFont(headlineFont);
       promptLabel.setFont(mediumFont);
       add(greeting);
       add(promptLabel);
       add(resultsLabel);
       add(result1);
       for(x = 0; x < NUM; ++x)
       {
          button[x] = new JButton(letter[x]);
       }
             
       add(result2);
       add(result3);
       
       for(x = 0; x < NUM; ++x)
       {
          add(button[x]);
          button[x].addActionListener(this);
       }
       
       random = (int) (Math.random() * 100) % targetPhrases.length;
       target = targetPhrases[random];
       target = target.toUpperCase();
    
       len = target.length();
       x = 0;
       while(x < len)
       {
         if(target.charAt(x) == ' ')
             displayPhrase = displayPhrase + ' ';
         else
             displayPhrase = displayPhrase + "*";
         ++x;
      }
      result1.setText(displayPhrase);
      promptLabel.setText(play + enterOne);                                       
    }
   @Override
   public void actionPerformed(ActionEvent e)
   {
      int pos;
      JButton temp = (JButton)e.getSource();
      guess = temp.getText().charAt(0);
      for(pos = 0; pos < NUM; ++pos)
      {
          if(guess == letter[pos].charAt(0))
          {
              x = pos;
              pos = NUM;
          }
      }
      button[x].setEnabled(false);
      found = false;
      for(pos = 0; pos < len; ++pos)
      {
            oneLetter = target.charAt(pos);
            if(oneLetter == guess)
            {
               displayPhrase = displayPhrase.substring(0, pos) +
                 guess + displayPhrase.substring(pos + 1, len);
               found = true;
            }      
       }
       if(!found)
       {
         prompt = sorry + guess;
         ++wrongGuesses;
       }
       else
       {
         prompt = correct + enterOne;  
       }
       result1.setText(displayPhrase);
       result2.setText(prompt);
       
       if(displayPhrase.indexOf('*') == -1)
       {
         result2.setText("");
         result3.setText(" Congratulations! ");
         for(x = 0; x < NUM; ++x)
           button[x].setEnabled(false);
       }       
      result1.setLocation(20, 125);  
      }
   @Override
   public void paintComponent(Graphics pen)
   {
      super.paintComponent(pen);
      greeting.repaint();
      promptLabel.repaint();
      resultsLabel.repaint();
      result1.repaint();
      
      for(x = 0; x < NUM; ++x)
      {
         button[x].repaint();
      }
      pen.drawLine(20, 575, 20, 300);
      pen.drawLine(20, 300, 100, 300);
      pen.drawLine(100, 300, 100, 350);
      if(wrongGuesses >= 6)
      {
         pen.drawLine(100,500,60,575);
         pen.setFont(headlineFont);
         pen.drawString(losingMsg, 200, 400);
      }
      if(wrongGuesses >= 5)
         pen.drawLine(100, 500, 140, 575);
      if(wrongGuesses >= 4)
         pen.drawLine(100, 420, 140, 500);
      if(wrongGuesses >= 3)
         pen.drawLine(100,420, 60, 500);
      if(wrongGuesses >= 2)
         pen.drawLine(100, 500, 100, 400);
      if(wrongGuesses >= 1)
          pen.drawOval(75, 350, 50, 50);
      repaint();
   }
   public static void main(String[] args)        
   {
      JFrame frame = new JFrame();
      frame.add(new JSecretPhrase2());
      frame.setSize(600, 700);
      frame.setVisible(true);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
}