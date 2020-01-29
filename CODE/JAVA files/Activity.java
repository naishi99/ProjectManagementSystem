/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import java.util.Random;
import java.util.Scanner;

public class Activity
{
	String activity_id;
	String activity_name;
	String parent_proj_id;
	int num_emp;
	String[] emp_id_list;
	boolean finished;
	int size;
	linked_list<Activity> act_list=new linked_list<>();
	//parametrised constructor for assigning various attributes 
	public Activity(String activity_id, String activity_name, String parent_proj_id,int num) 
	{
		
		this.activity_id = activity_id;
		this.activity_name = activity_name;
		this.parent_proj_id = parent_proj_id;
		num_emp=num;
		finished=false;
	}
	
	 //getters and setters for all attributes 
	public boolean isFinished() {
		return finished;
	}


	public void setFinished(boolean finished) {
		this.finished = finished;
	}


	public int getNum_emp() {
		return num_emp;
	}

	public String getActivity_id() {
		return activity_id;
	}

	
	public String getActivity_name() {
		return activity_name;
	}

	public void setActivity_name(String activity_name) {
		this.activity_name = activity_name;
	}

	public String getParent_proj_id() {
		return parent_proj_id;
	}
	
	public String[] getEmp_id_list() {
		return emp_id_list;
	}

	public void setEmp_id_list(String[] emp_id_list) {
		this.emp_id_list = emp_id_list;
	}
	//assigning atleast 3 employees a group to work on an activity in a project 
	public void assign_group(linked_list<Employee1> elist) throws IOException	
	{	
                Random r=new Random(); 
		String[] emp_list_temp=new String[num_emp];
		int p=0;
		Node<Employee1> e1=elist.getFirst();
		while(e1!=null)
		{
			//checking if the particular employee is free to work on that activity of a project
			if(e1.getData().status_vacancy && e1.getData().getEmployee_designation().contains(activity_name))
			{
				emp_list_temp[p]=e1.getData().getEmployee_id();
				e1.getData().setStatus_vacancy(false);//after assigning the work, vacancy status is assigned as false 
                                e1.getData().atime=r.nextInt(31);
                        }
			e1=e1.getNext();
			p++;
		}
		setEmp_id_list(emp_list_temp);
        write_act_file();
	}

        @Override
	public String toString()
	{
            return "\nActivity ID: "+activity_id+"\nName: "+activity_name+"\nParent Project ID:"+parent_proj_id+
				"\nNumber of employees: "+num_emp+"\nStatus: "+(finished?"Finished":"Pending");
	}
      //adding employee in the linked list and then writing into the file    
	public void add_activity(String activity_id, String activity_name, String parent_proj_id,int num) throws IOException
	{
		
		act_list.insert(new Activity(activity_id,activity_name, parent_proj_id,num));
                write_act_file();
		
	}
	//display details of file
	public void display()
	{
		System.out.println(act_list.toString());
	}
	//remove employee from the linked list at a particular position and then updating the file
	public void remove_activity() throws IOException
	{
		Node<Activity> pos=act_list.getFirst();
		act_list.remove(pos);
                write_act_file();
		
	}
    //Non parametrised constructor is used to read previous data of file and inserting into linked list (use of object output stream so as to store full object in the file)

	public Activity() throws Exception
	{
		File f1 = new File("act.txt");//checking whether file exists or not
		if(f1.exists())
		{
                    try{
                       FileInputStream file = new FileInputStream("act1.txt");
                        BufferedInputStream b=new BufferedInputStream(file);
                        b.mark(0);
                        b.reset();
                        ObjectInputStream obj = new ObjectInputStream(b);
                      
                            Activity e=null;
                            e=(Activity) obj.readObject();//reading object from the file 
                                act_list.insert(e);//inserting in the current linked list and appending the data in the file
                          
                            if(file.markSupported())//marking the pointer in the file to start the traversal
                                file.reset();
                       obj.close();//closing the streams
                       file.close(); 
                    }
                    catch(Exception e)//catching exceptions
                    {
                      
                    }
                        
		}    
           
	}
	 //function for writing into file
	public void write_act_file() throws IOException
	{
		 try 
               {
                    File f1 = new File("act1.txt");
                   FileOutputStream fileOut = new FileOutputStream(f1); 
                   AppendingObjectOutputStream objectOut = new AppendingObjectOutputStream(new ObjectOutputStream(fileOut));//used to append in the output stream
                    Node<Activity> pos=act_list.getFirst();
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
        public void submit(String emp_id,linked_list<Employee1> elist)
        {
            Scanner input=new Scanner(System.in);
            Node<Employee1> e=elist.getFirst();
            while(e!=null)
            {
                if(e.getData().employee_id.equals(emp_id))
                {
                    e.getData().submitted=true;
                    System.out.println("Enter the days after starting the activity:");
                    int time=input.nextInt(); 
                    e.getData().subtime=time;
                }
                e=e.getNext();
            }
        }
	
}
