package dsa;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Promotion 
{
	
	Scanner input=new Scanner(System.in); 
	String promo_type;
	double salary_raise;
	String promo_date;
	String emp_id;
	linked_list<Promotion> promo_list=new linked_list<>();
	//parametrised constructor for assigning various attributes 
	public Promotion(String promo_type, double salary_raise, String promo_date,String emp_id) {
		
		this.promo_type = promo_type;
		this.salary_raise = salary_raise;
		this.promo_date = promo_date;
		this.emp_id=emp_id;
	}
	 //getters and setters for all attributes 
	public String getPromo_type() {
		return promo_type;
	}

	public void setPromo_type(String promo_type) {
		this.promo_type = promo_type;
	}

	public double getSalary_raise() {
		return salary_raise;
	}

	public void setSalary_raise(double salary_raise) {
		this.salary_raise = salary_raise;
	}

	public String getPromo_date() {
		return promo_date;
	}

	public void setPromo_date(String promo_date) {
		this.promo_date = promo_date;
	}
	
	
	public String toString()
	{
		String str="";
		str="Promotion type: "+promo_type+"\nSalary Raise: "+salary_raise+"\nPromotion Date: "+promo_date;
		return str;
	}
	//adding project in the linked list and then writing into the file    
	public void add_promotion(String promo_type, double salary_raise, String promo_date,String id)
	{
		
		promo_list.insert(new Promotion(promo_type,salary_raise,promo_date,id));
		promo_list.toString();
                write_promo_file();
		
	}
	//display details of file
	public void display()
	{
		System.out.println(promo_list.toString());
	}
	//remove project from the linked list at a particular position and then updating the file
	public void remove_promotion()
	{
		Node<Promotion> pos=promo_list.getFirst();
		promo_list.remove(pos);
                write_promo_file();
		
	}
        public String[][] feedback(String[] emp_id,linked_list<Employee1> elist)//function to give ranks 1,2,3 from the group of employees
	{
		String[][] rank=new String[emp_id.length][2];
		System.out.println("Number of employees: "+emp_id.length);
                Node<Employee1> e=Main.elist.emp_list.getFirst();
                
		System.out.println("Give ranks less than or equal to "+emp_id.length);
		for(int t=0;t<emp_id.length&&e!=null;)
		{
                    if(e.getData().employee_id.equals(emp_id[t])&&e.getData().submitted)
                    {
                        System.out.println("Submitted? "+e.getData().submitted);
			System.out.println("Days for submission: "+e.getData().subtime);
                        System.out.println(emp_id[t]+": ");
			rank[t][1]=input.next();
			rank[t][0]=emp_id[t];
                        t++;
                    }
                    e=e.getNext();
			
		}
		return rank;
	}
        
	
	public String[] analysis(String[] emp_id,linked_list<Employee1> elist)//analysing the ranks of employees and giving the 30% salary raise 
	{
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		
		String[] arr=customBubbleSort(feedback(emp_id,elist));
		int p=0;
		Node<Employee1> emp=Main.elist.emp_list.getFirst();
		while(emp!=null&&p<3)
		{
			if(emp_id[p].equals(emp.getData().employee_id))
			{
				salary_raise=emp.getData().getSalary()*0.3;
				add_promotion("Raise",emp.getData().getSalary()*0.3,dtf.format(now),emp_id[p]);
				emp.getData().setSalary(emp.getData().getSalary()+salary_raise);
				p++;
			}
			
			emp=emp.getNext();
			
		}
		return arr;
	}
	
	//sorting employees in the order of their ranks 
	public String[] customBubbleSort(String[][] arr)
	{
		String temp0,temp1;
		for(int i=0;i<arr.length-1;i++)
		{
			for(int j=i+1;j<arr.length;j++)
			{
				if(arr[i][1].compareTo(arr[j][1])>0)
				{
					temp0=arr[i][0];
					arr[i][0]=arr[j][0];
					arr[j][0]=temp0;
					
					temp1=arr[i][1];
					arr[i][1]=arr[j][1];
					arr[j][1]=temp1;
				}
			}
		}
		return arr[0];
	}
	 //function for writing into file
	public void write_promo_file()
        {
           try 
           {
                File f1 = new File("promo1.txt");
               FileOutputStream fileOut = new FileOutputStream(f1); 
               AppendingObjectOutputStream objectOut = new AppendingObjectOutputStream(new ObjectOutputStream(fileOut));//used to append in the output stream
                Node<Promotion> pos=promo_list.getFirst();
                   while(pos!=null)
                   {
                       objectOut.writeObject(pos.getData());//traversing the whole linked list data and writing into the stream
                       pos=pos.getNext();
                   }
                   objectOut.flush();//flushing the stream
                           
                   objectOut.close();//closing streams
                   fileOut.close();
                
            } catch (IOException ex) {
                }
           

        }
    //Non parametrised constructor is used to read previous data of file and inserting into linked list (use of object output stream so as to store full object in the file)

	public Promotion()
        {
        	
        	File f1 = new File("promo.txt");//checking whether file exists or not
        	if(f1.exists())
        	{
                    try{
                       FileInputStream file = new FileInputStream("promo1.txt");
                        BufferedInputStream b=new BufferedInputStream(file);
                        b.mark(0);
                        b.reset();
                        ObjectInputStream obj = new ObjectInputStream(b);
                            Promotion e=null;
                            e=(Promotion) obj.readObject();//reading object from the file 
                                promo_list.insert(e);//inserting in the current linked list and appending the data in the file
                          
                            if(file.markSupported())//marking the pointer in the file to start the traversal
                                file.reset();
                       obj.close();
                       file.close(); //closing the streams
                    }
                    catch(Exception e)//catching exceptions
                    {
                       
                    }
                        
        	}    
        }
	
}
