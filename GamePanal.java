

package snakegame;



import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

 // By Muhammad Daniyal 

public class GamePanal extends JPanel implements ActionListener ,KeyListener  //for making component
{
    private int enemyX;
    private int enemyY;
    private int[] snakexlength=new int[750];
    private int[] snakeylength=new int[750];
    private int lengthofsnake=3;
    private boolean left=false;
    private boolean right=true;
    private boolean up=false;
    private boolean down=false;
    private int moves=0;
    private int score=0;
    private boolean gameover=false;
    private ImageIcon snaketitle=new ImageIcon(getClass().getResource("snaketitle.jpg"));
    private ImageIcon leftmouth=new ImageIcon(getClass().getResource("leftmouth.png"));
    private ImageIcon rightmouth=new ImageIcon(getClass().getResource("rightmouth.png"));
    private ImageIcon upmouth=new ImageIcon(getClass().getResource("upmouth.png"));
    private ImageIcon downmouth=new ImageIcon(getClass().getResource("downmouth.png"));
    private ImageIcon snakeimage=new ImageIcon(getClass().getResource("snakeimage.png"));
    private ImageIcon enemyimage = new ImageIcon(getClass().getResource("enemy.png"));

        // for enemy position
     private int[] enemyxpos= {25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,
			600,625,650,675,700,725,750,775,800,825,850};
	
	private int[] enemyypos = {83,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,
			600,634};
        // Random class object
        private Random random=new Random();
      private int xpos = random.nextInt(34);
	private int ypos = random.nextInt(23);
        private Timer t1;  //object of timer class
        private int delay=100;
        
    GamePanal()
    {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);
        
        t1=new Timer(delay,this);
        t1.start();
          enemyX = enemyxpos[xpos];
          enemyY = enemyypos[ypos];
  newEnemy();
    }

    @Override
    public void paint(Graphics grp) {
        super.paint(grp); //To change body of generated methods, choose Tools | Templates.
        grp.setColor(Color.WHITE);
        grp.drawRect(23,10,855,55);
        grp.drawRect(24, 85, 851, 577);
        
        snaketitle.paintIcon(this, grp,25,11);
         grp.setColor(Color.BLACK);
        grp.fillRect(25,87,850,575);
       if(moves==0)
       {
           snakexlength[0]=100;
           snakexlength[1]=75;
           snakexlength[2]=50;
           
            snakeylength[0]=100;
           snakeylength[1]=100;
           snakeylength[2]=100;
           //moves++;
       }
       if(left)
       {
         leftmouth.paintIcon(this, grp, snakexlength[0], snakeylength[0]);
       }
         if(right)
       {
         rightmouth.paintIcon(this, grp, snakexlength[0], snakeylength[0]);
         
       }
           if(up)
       {
         upmouth.paintIcon(this, grp, snakexlength[0], snakeylength[0]);
       }
             if(down)
       {
         downmouth.paintIcon(this, grp, snakexlength[0], snakeylength[0]);
       }
  
             for(int i=1;i<lengthofsnake;i++)
             {
                 snakeimage.paintIcon(this, grp, snakexlength[i],snakeylength[i] );
                  
             }
        enemyimage.paintIcon(this, grp, enemyX, enemyY);

          if(gameover)
          {
             
           InputStream in;
    try
    {
        in =new FileInputStream(new File("src\\wav\\fail.wav"));
        AudioStream ad=new AudioStream(in);
        AudioPlayer.player.start(ad);
    }
    catch(IOException e)
    {
        
    }
          grp.setColor(Color.WHITE);
				grp.setFont(new Font("arial",Font.BOLD,50));
				grp.drawString("Game Over!", 300, 300);
				
				grp.setFont(new Font("arial",Font.BOLD,20));
				grp.drawString("Press Space To Restart", 350, 340);
          }
          grp.setColor(Color.WHITE);
          grp.setFont(new Font("Miltin",Font.PLAIN,14));
          grp.drawString("Score : "+score,750,30);    //x-axis , y-axis
          grp.drawString("Length : "+lengthofsnake,750,50);    //x-axis , y-axis
          grp.dispose();
    }
                   
  
    @Override
    public void actionPerformed(ActionEvent ae) 
     
    {
        for(int i=lengthofsnake-1;i>0;i--)
        {
            snakexlength[i]=snakexlength[i-1];
            snakeylength[i]=snakeylength[i-1];
        }
  
        if (left)
      {
          snakexlength[0]=snakexlength[0]-25;//  25 pixels left
      }
      if(right)
      {
          snakexlength[0]=snakexlength[0]+25;//  25 pixels left
      }
      if(up)
      {
          snakeylength[0]=snakeylength[0]-25;//  25 pixels left
      }
      if(down)
      {
          snakeylength[0]=snakeylength[0]+25;//  25 pixels left
      }   // for showing enemy
            
      // for x-axis
      if(snakexlength[0]>851)
      {
          snakexlength[0]=24;
      }
        if(snakexlength[0]<24)
      {
          snakexlength[0]=851;
      }
            // for y-axis
         if(snakeylength[0]>634)
      {
          snakeylength[0]=83;
      }
        if(snakeylength[0]<83)
      {
          snakeylength[0]=634;
      }
       collidesWithEnemy();
       collidesWithBody();
    repaint();
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if(ke.getKeyCode()==KeyEvent.VK_SPACE)
        {
              InputStream in;
    try
    {
        in =new FileInputStream(new File("src\\wav\\started.wav"));
        AudioStream ad=new AudioStream(in);
        AudioPlayer.player.start(ad);
    }
    catch(IOException e)
    {
        
    }
            restart();
        }
       if(ke.getKeyCode()==KeyEvent.VK_LEFT && (!right))
       {
           left=true;
           right=false;
           up=false;
           down=false;
           moves++;
       }
       if(ke.getKeyCode()==KeyEvent.VK_RIGHT && (!left) )
       {
           left=false;
           right=true;
           up=false;
           down=false;
           moves++;
       }
       if(ke.getKeyCode()==KeyEvent.VK_UP && (!down))
       {
           left=false;
           right=false;
           up=true;
           down=false;
           moves++;
       }
       if(ke.getKeyCode()==KeyEvent.VK_DOWN && (!up))
       {
           left=false;
           right=false;
           up=false;
           down=true;
           moves++;
       }  
              
    }

    @Override
    public void keyReleased(KeyEvent ke) {
       
    }
     @Override
    public void keyTyped(KeyEvent ke) {
        
    }
  private void newEnemy()
    {
      
         enemyX = enemyxpos[random.nextInt(enemyxpos.length)];
    enemyY = enemyypos[random.nextInt(enemyypos.length)];
    } 
    private void collidesWithEnemy() {
   if(Math.abs(enemyX - snakexlength[0]) < 25 && Math.abs(enemyY - snakeylength[0]) < 25) {
        lengthofsnake++;
        score++;
        newEnemy();
        //new enemy sound
         InputStream in;
    try
    {
        in =new FileInputStream(new File("src\\wav\\newenemy.wav"));
        AudioStream ad=new AudioStream(in);
        AudioPlayer.player.start(ad);
    }
    catch(IOException e)
    {
        
    }
 }
  
    }

    private void collidesWithBody() {
       for(int i = lengthofsnake - 1; i > 3; i--) {
    if(snakexlength[i] == snakexlength[0] && snakeylength[i] == snakeylength[0]) {
        t1.stop();
        gameover = true;

       }
    }
    }

    private void restart() {
           gameover=false;
           moves=0;
           score=0;
           lengthofsnake=3;
           left=false;
           right=true;
           up=false;
           down=false;
           t1.start();
           repaint();
    }
}
