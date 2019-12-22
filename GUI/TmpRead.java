import java.io.*;
import java.util.*;
import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*;
import javax.imageio.ImageIO;
class Images //implements Comparable<Images>
{
   //static String link="/home/Vrushabh/opencv-face-recognition/person";
   private String name;
   private double probs;
   private String image;
   Images(String name,double probs)
   {
      this.name=name;
      this.probs=probs;
      this.image="/home/Vrushabh/opencv-face-recognition/person/"+name.toLowerCase()+".jpg";
   }
   public double getProb()
   {
      return this.probs;
   }
   public String getName()
   {
      return this.name;
   }
   public String getImage()
   {
   		return this.image;
   }
   /*public Double compareTo(Images a)
   {
      return (double)this.probs-a.probs;
   }*/
}
public class TmpRead extends JFrame
{ 
   JLabel[] jl;
   //JButton[] jb;
   JPanel[] jp;
   TmpRead() throws Exception
   {
      super("Percentage of all criminal above 50");
    	int count=0;
      	
        	Scanner scanner = new Scanner(new File("/home/Vrushabh/opencv-face-recognition/GUI/prob.txt"));
         	ArrayList<Images> arr=new ArrayList<Images>();
         	while(scanner.hasNextLine())
         	{
            	String tmp1=scanner.nextLine();
            	String tmp2=scanner.nextLine();
              String result = String.format("%.2f", Double.parseDouble(tmp2));
            	System.out.println(tmp1+" "+tmp2);
              if((Double.parseDouble(tmp2)*100)>50.0)
            	 arr.add(new Images(tmp1,Double.parseDouble(result)*100));
         	}
         	System.out.println("//////////////////////");
         	Comparator c = Collections.reverseOrder(new SortByName()); 
         	Collections.sort(arr, c); 
         	Collections.sort(arr, Comparator.comparingDouble(Images::getProb).reversed());
         	for(Images tmp:arr)
         	{
            	count++;
            	System.out.println(tmp.getName()+" "+tmp.getProb());
         	}
      	
      	jl=new JLabel[count];
     	//jb=new JButton[count];
     	jp=new JPanel[count];

      	setLayout(new GridLayout(count,1));
      	int i=0;
      	for(Images tmp:arr)
      	{
      		jl[i]=new JLabel(tmp.getName()+" "+tmp.getProb()+"%",SwingConstants.CENTER);
      		//jb[i]=new JButton(""+tmp.getName());
      		//Image img1 = ImageIO.read(getClass().getResource(""+tmp.getImage()));
      		//jb[i].setIcon(new ImageIcon(img1));

      		jp[i]=new JPanel();
      		jp[i].setLayout(new GridLayout(1,2,10,10));
      		jp[i].add(jl[i]);
      		//jp[i].add(jb[i]);

      		add(jp[i]);
      	}

      	setSize(500,500);
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
   	}
   /*public static void main(String[] args) throws Exception
   {
      
      TmpRead rdn=new TmpRead();
   }*/
}
class SortByName implements Comparator<Images> 
{  
    public int compare(Images a, Images b) 
    { 
        return a.getName().compareTo(b.getName()); 
    } 
}