import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileFilter;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.border.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.beans.*;
import java.awt.image.*;
import javax.imageio.*;
import java.util.concurrent.*;


class GuiFDR extends JFrame implements ActionListener
{
	Process p;
	JLabel l1,l2;
	JPanel p1,p2;
	JButton b1,b2,b3,b4,b5;
	BufferedReader reader;
	JLabel background;
	static String filename,filepath;

	GuiFDR(String s)
	{
		super(s);
		background=new JLabel(new ImageIcon("/home/Vrushabh/opencv-face-recognition/GUI/white.jpg"),JLabel.CENTER);

		JPanel p1=new JPanel();
		JPanel p2=new JPanel();
		//p1.setBackground(Color.gray);  
		l1=new JLabel();
		l2=new JLabel();
		l1.setIcon(new ImageIcon("/home/Vrushabh/opencv-face-recognition/GUI/face.jpeg"));
		l2.setIcon(new ImageIcon("/home/Vrushabh/opencv-face-recognition/GUI/hello.jpeg"));
		p1.setBounds(70,0,460,170);
		p2.setBounds(600,0,526,807);
		p2.add(l2);
		p1.add(l1);

		b1=new JButton("Train");
		b2=new JButton("Search Image");
		b3=new JButton("Video Streaming");
		b4=new JButton("Add New Person");
		b5=new JButton("Search Info");

		b1.setBorder(new RoundedBorder(10));
		b2.setBorder(new RoundedBorder(10));
		b3.setBorder(new RoundedBorder(10));
		b4.setBorder(new RoundedBorder(10));
		b5.setBorder(new RoundedBorder(10));
		b5.setFont(new Font("Lato", Font.PLAIN, 24));
		b5.setBackground(Color.WHITE);
		b3.setFont(new Font("Lato", Font.PLAIN, 24));
		b3.setBackground(Color.WHITE);
		b4.setFont(new Font("Lato", Font.PLAIN, 24));
		b4.setBackground(Color.WHITE);
		b2.setFont(new Font("Lato", Font.PLAIN, 24));
		b2.setBackground(Color.WHITE);
		b1.setFont(new Font("Lato", Font.PLAIN, 24));
		b1.setBackground(Color.WHITE);

		b1.setBounds(70,450,435,70);
		b2.setBounds(70,330,435,70);
		b3.setBounds(70,570,435,70);
		b4.setBounds(70,210,435,70);
		b5.setBounds(70,690,435,70);

		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		
		setLayout(null);
		background.setLayout(null);
		background.setBounds(0,0,1200,800);
		background.add(b1);
		background.add(b2);
		background.add(b3);
		background.add(b4);
		background.add(b5);
		background.add(p1);
		background.add(p2);
		add(background);
		setSize(1200,800);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
	}
	public void actionPerformed(ActionEvent e)
	{
		JButton b=(JButton)e.getSource();
		if(b==b5)
		{
			String name=JOptionPane.showInputDialog(this,"Enter Name:");
			filepath="/home/Vrushabh/opencv-face-recognition/information/"+name+".dat";//"+s+".txt";
			Person obj;
			try 
			{
 				FileInputStream fileIn = new FileInputStream(filepath);
    	    	ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            	obj = (Person)objectIn.readObject();
         		System.out.println(obj.id+"\n"+obj.name+"\n"+obj.dateofbirth+"\n"+obj.gender+"\n"+"Criminal is Present");
           		System.out.println("The Object has been read from the file");
           		objectIn.close();
           		name=obj.id+"\n"+obj.name+"\n"+obj.dateofbirth+"\n"+obj.gender+"\n"+obj.address+"\n"+obj.city+"\n"+obj.state+"\n"+"Criminal is Present";
       		} 
       		catch (Exception ex) 
       		{
           		ex.printStackTrace();
           		//return null;
       		} 	
       		JOptionPane.showMessageDialog(this,name);
		}
		if(b==b1)
		{
			System.out.println("Start train");
			try 
			{
				String[] cmd = { "sh", "/home/Vrushabh/opencv-face-recognition/GUI/script.sh"};
   				p = Runtime.getRuntime().exec(cmd); 
   				p.waitFor(); 
   				reader=new BufferedReader(new InputStreamReader(p.getInputStream())); 
   				String line; 
   				while((line = reader.readLine()) != null) 
   				{ 
    				System.out.println(line);
   				} 
  			} catch (IOException exp) 
  			{
   				// TODO Auto-generated catch block
   				exp.printStackTrace();
  			}	 
  			catch (InterruptedException exp) 
  			{
   				// TODO Auto-generated catch block
   				exp.printStackTrace();
  			}
			System.out.println("End of training");
		}
		if(b==b2)
		{
			JFileChooser fileChooser = new JFileChooser();
			FileFilter imageFilter = new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes());
			fileChooser.setFileFilter(imageFilter);
			fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
			//*******Change for Preview Pane*******19-5-19
			// Create label
	        JLabel img=new JLabel();
	        
	        // Let label come fatty!!
	        img.setPreferredSize(new Dimension(175,175));
	        fileChooser.setAccessory(img);
			fileChooser.addPropertyChangeListener(new PropertyChangeListener(){
        
            // When any JFileChooser property changes, this handler
            // is executed
            public void propertyChange(final PropertyChangeEvent pe)
            {
                // Create SwingWorker for smooth experience
                SwingWorker<Image,Void> worker=new SwingWorker<Image,Void>(){
                
                    // The image processing method
                    protected Image doInBackground()
                    {
                        // If selected file changes..
                        if(pe.getPropertyName().equals(JFileChooser.SELECTED_FILE_CHANGED_PROPERTY))
                        {
                        // Get selected file
                        File f=fileChooser.getSelectedFile();
                        
                            try
                            {
                            // Create FileInputStream for file
                            FileInputStream fin=new FileInputStream(f);
                            
                            // Read image from fin
                            BufferedImage bim=ImageIO.read(fin);
                            
                            // Return the scaled version of image
                            return bim.getScaledInstance(178,170,BufferedImage.SCALE_FAST);
                            
                            }catch(Exception e){
                                // If there is a problem reading image,
                                // it might not be a valid image or unable
                                // to read
                                img.setText(" Not valid image/Unable to read");
                            }
                        }
                    
                    return null;
                    }
                    
                    protected void done()
                    {
                        try
                        {
                        // Get the image
                        Image i=get(1L,TimeUnit.NANOSECONDS);
                        
                        // If i is null, go back!
                        if(i==null) return;
                        
                        // Set icon otherwise
                        img.setIcon(new ImageIcon(i));
                        }catch(Exception e){
                            // Print error occured
                            img.setText(" Error occured.");
                        }
                    }
                };
                
                // Start worker thread
                worker.execute();
            }
        });
        
			//*************************************
			int result = fileChooser.showOpenDialog(this);
			if (result == JFileChooser.APPROVE_OPTION) 
			{
    			File selectedFile = fileChooser.getSelectedFile();
    			System.out.println("Selected file: " + selectedFile.getAbsolutePath());
    			System.out.println("Start to Search Image");
				try 
				{
					String[] cmd = { "sh", "/home/Vrushabh/opencv-face-recognition/GUI/search.sh",selectedFile.getAbsolutePath()};
					//p= new ProcessBuilder("sh /home/Vrushabh/opencv-face-recognition/GUI/tmp.sh", selectedFile.getAbsolutePath()).start();
   					p = Runtime.getRuntime().exec(cmd); 
   					p.waitFor(); 
   					reader=new BufferedReader(new InputStreamReader(p.getInputStream())); 
   					String line; 
   					while((line = reader.readLine()) != null) 
   					{ 
    					System.out.println(line);
   					} 
  				} 
  				catch (IOException exp) 
  				{
   					// TODO Auto-generated catch block
   					exp.printStackTrace();
  				}	 
  				catch (InterruptedException exp) 
  				{
   					// TODO Auto-generated catch block
   					exp.printStackTrace();
  				}
				System.out.println("End of Searching");
				try{

				TmpRead rdn=new TmpRead();
			}catch(Exception rtp){
				//find();
			}
			}
		}
		if(b==b3)
		{
			System.out.println("Start Of Camera");
			try 
			{
				String[] cmd = { "sh", "/home/Vrushabh/opencv-face-recognition/GUI/script2.sh"};
   				p = Runtime.getRuntime().exec(cmd); 
   				p.waitFor(); 
   				reader=new BufferedReader(new InputStreamReader(p.getInputStream())); 
   				String line; 
   				while((line = reader.readLine()) != null) 
   				{ 
    				System.out.println(line);
   				} 
  			} catch (IOException exp) 
  			{
   				// TODO Auto-generated catch block
   				exp.printStackTrace();
  			}	 
  			catch (InterruptedException exp) 
  			{
   				// TODO Auto-generated catch block
   				exp.printStackTrace();
  			}
			System.out.println("End of Camera");
			//find();
		}
		if(b==b4)
		{
			String name=JOptionPane.showInputDialog(this,"Enter Name of Person:");
			File theDir = new File("/home/Vrushabh/opencv-face-recognition/dataset/"+name);

			// if the directory does not exist, create it
			if (!theDir.exists()) 
			{
    			System.out.println("creating directory: " + theDir.getName());
    			boolean result = false;
    			try
    			{
        			theDir.mkdir();
       				result = true;
    			} 
    			catch(SecurityException se)
    			{}        
    			if(result) 
    			{    
        			System.out.println("DIR created");  
    			}
				////////////////////BASH SCRIPT//////////////////////////////////////
				JOptionPane.showMessageDialog(this, "Enter SPACE for capturing images");

				System.out.println("Start Of Input Image");
				try 
				{
					String[] cmd = { "sh", "/home/Vrushabh/opencv-face-recognition/GUI/tmp.sh","/home/Vrushabh/opencv-face-recognition/dataset/"+name};
   					p = Runtime.getRuntime().exec(cmd); 
   					p.waitFor(); 
   					reader=new BufferedReader(new InputStreamReader(p.getInputStream())); 
   					String line; 
   					while((line = reader.readLine()) != null) 
   					{ 
    					System.out.println(line);
   					} 
  				} 	catch (IOException exp) 
  				{
   					// TODO Auto-generated catch block
   					exp.printStackTrace();
  				}	 
  				catch (InterruptedException exp) 
  				{
   					// TODO Auto-generated catch block
   					exp.printStackTrace();
  				}
				System.out.println("End of Camera");
				Criminal ob=new Criminal(name);
			}
		}
	}
	public static void main(String[] args)
	{
		GuiFDR frame1=new GuiFDR("Facial Recognition System");
	}
}
class RoundedBorder implements Border {

    private int radius;


    RoundedBorder(int radius) {
        this.radius = radius;
    }


    public Insets getBorderInsets(Component c) {
        return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
    }


    public boolean isBorderOpaque() {
        return true;
    }


    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.drawRoundRect(x, y, width-1, height-1, radius, radius);
    }
}
