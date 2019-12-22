import java.awt.*;
// import java.awt.Toolkit;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.event.*;
import java.io.*;
import java.util.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


class Person implements Serializable
{
	int id;
	String name;
	String dateofbirth;
	String gender;
	String address;
	String city,state;
	Person(int id,String name,String dateofbirth,String gender,String address,String city,String state)
	{
		this.id=id;
		this.name=name;
		this.dateofbirth=dateofbirth;
		this.gender=gender;
		this.address=address;
		this.city=city;
		this.state=state;
	}	
}
public class Criminal extends JFrame implements ActionListener,Serializable
{
	int id;
	String name;
	String dateofbirth;
	String gender;
	String address;
	String city,state;
	JLabel li,ln,ld,lg,la,lc,ls,background;
	JRadioButton male,female;
	JTextField ti,tn,td,tg,ta,tc,ts;
	JPanel pi,pn,pd,pg,pa,pc,ps;
	String filename;
	JButton submit;//,display;
	ButtonGroup group;
	//JDateChooser chooser;
	private String filepath; //="E:\\opencv-face-recognition\\GUI\\information\\";

	Criminal(String s)
	{
		super(s);
		gender="";
		background=new JLabel(new ImageIcon("/home/Vrushabh/opencv-face-recognition/GUI/crim.jpg"),JLabel.CENTER);
		filename=s;
		filepath="/home/Vrushabh/opencv-face-recognition/information/"+s+".dat";//"+s+".txt";

		li=new JLabel("Criminal ID:", SwingConstants.CENTER);
		ln=new JLabel("Full Name:", SwingConstants.CENTER);
		ld=new JLabel("Date of Birth:", SwingConstants.CENTER);
		lg=new JLabel("Gender :", SwingConstants.CENTER);
		la=new JLabel("Address :", SwingConstants.CENTER);
		lc=new JLabel("City :", SwingConstants.CENTER);
		ls=new JLabel("State :", SwingConstants.CENTER);


		ti=new JTextField("");
		tn=new JTextField("");
		td=new JTextField("");
		//tg=new JTextField("")
		ta=new JTextField("");
		tc=new JTextField("");
		ts=new JTextField("");

		male=new JRadioButton("Male");
		male.setMnemonic(KeyEvent.VK_M);
		female=new JRadioButton("Female");
		female.setMnemonic(KeyEvent.VK_F);
		ButtonGroup group = new ButtonGroup();
        group.add(male);
        group.add(female);

        male.setBounds(300,175,75,20);
        female.setBounds(400,175,80,20);

		/*pi=new JPanel();
		pn=new JPanel();
		pd=new JPanel();
		pg=new JPanel();
		pa=new JPanel();
		//JScrollPane scroll = new JScrollPane (ta);
		//add(scroll,BorderLayout.CENTER);
		pc=new JPanel();
		ps=new JPanel();*/
		//tiPaneld250,200);


		submit=new JButton("Submit");
		//display=new JButton("Display");
		submit.addActionListener(this);
		//display.addActionListener(this);
		
		li.setBounds(50,25,100,30);//=new JLabel("Criminal ID:", SwingConstants.CENTER);
		ln.setBounds(50,75,100,30);//=new JLabel("Full Name:", SwingConstants.CENTER);
		ld.setBounds(50,125,100,30);//=new JLabel("Date of Birth:", SwingConstants.CENTER);
		lg.setBounds(50,175,100,30);//=new JLabel("Gender :", SwingConstants.CENTER);
		la.setBounds(50,225,100,30);//=new JLabel("Address :", SwingConstants.CENTER);
		lc.setBounds(50,275,100,30);//=new JLabel("City :", SwingConstants.CENTER);
		ls.setBounds(50,325,100,30);//=new JLabel("State :", SwingConstants.CENTER);

		ti.setBounds(300,25,100,30);//pi.setBounds(null);
		tn.setBounds(300,75,100,30);
		td.setBounds(300,125,100,30);
		//tg.setBounds(300,175,100,30);
		ta.setBounds(300,225,100,30);
		tc.setBounds(300,275,100,30);
		ts.setBounds(300,325,100,30);
		submit.setBounds(100,400,300,30);
		
		//chooser = new JDateChooser();
		//chooser.setLocale(Locale.US);
		//chooser.setBounds(300,125,100,30);
		//JPanel panel = new JPanel();
		//panel.add(chooser);

		/*pi.add(ti);
		pn.add(tn);
		pd.add(td);
		pg.add(tg);
		pa.add(ta);
		pc.add(tc);
		ps.add(ts);*/


		//add(display);

		setLayout(null);
		background.setLayout(null);
		background.setBounds(0,0,500,500);
		background.add(li);
		background.add(ti);
		background.add(ln);
		background.add(tn);
		background.add(ld);
		background.add(td);
		background.add(lg);
		background.add(male);
		background.add(female);
		background.add(la);
		background.add(ta);
		background.add(lc);
		background.add(tc);
		background.add(ls);
		background.add(ts);
		background.add(submit);
		add(background);

		setSize(500,500);
		//setLayout(new GridLayout(8,2,0,35));
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		setVisible(true);
	}
	public void actionPerformed(ActionEvent e)
	{
		JButton btn=(JButton)e.getSource();
		/*if(btn==display)
		{
			Criminal obj;
			try 
			{
 
        	    FileInputStream fileIn = new FileInputStream(filepath);
    	        ObjectInputStream objectIn = new ObjectInputStream(fileIn);
 
	            obj = (Criminal)objectIn.readObject();
 
        	System.out.println(obj.id+"\n"+obj.name+"\n"+obj.dateofbirth);
            	System.out.println("The Object has been read from the file");
            	objectIn.close();
            	//return obj;
 	
        	} 
        	catch (Exception ex) 
        	{
            	ex.printStackTrace();
            	//return null;
        	}
		}*/
		if(btn==submit)
		{
			System.out.println("Hello");
			try{

			this.id=Integer.parseInt(ti.getText());
			}
			catch(Exception ex)
			{
				//JOptionPane.showMessageDialog(this,"Fill It Again Due to ID");
				JOptionPane.showMessageDialog(this,"Fill It Again Due to ID","Alert",JOptionPane.WARNING_MESSAGE);
				tn.setText("");  
				td.setText("");  
				//tg.setText(""); 
				male.setSelected(false); 
				female.setSelected(false); 
				ta.setText("");  
				tc.setText("");  
				ts.setText("");  
				return;
			}
			this.name=tn.getText();
			this.dateofbirth=td.getText();
			//this.gender=tg.getText();
			if(male.isSelected())gender="Male";
			if(female.isSelected())gender="Female";
			this.address=ta.getText();
			this.city=tc.getText();
			this.state=ts.getText();
			Person p1=new Person(this.id,this.name,this.dateofbirth,gender,this.address,this.city,this.state);
			try 
			{
            	FileOutputStream fileOut = new FileOutputStream(filepath);
            	ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            	objectOut.writeObject(p1);
            	objectOut.close();
            	System.out.println("The Object  was succesfully written to a file");
 			} 
 			catch (Exception ex) 
 			{
            	ex.printStackTrace();
        	}
		}
	}
	/*public static void main(String[] args) 
	{
			Criminal a=new Criminal("tmp");
	}*/
}