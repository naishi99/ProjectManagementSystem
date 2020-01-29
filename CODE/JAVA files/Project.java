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


public class Project 
{
	String project_name;
	String project_id;
	String project_type;
	String admin_id;
	boolean finished;
	public linked_list<Project> pro_list=new linked_list<>();
	//parametrised constructor for assigning various attributes 
	public Project(String project_name, String project_id, String project_type, String admin_id) 
	{
		super();
		this.project_name = project_name;
		this.project_id = project_id;
		this.project_type = project_type;
		this.admin_id = admin_id;
		finished=false;
	}
	 //getters and setters for all attributes 
	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public String getProject_name() {
		return project_name;
	}

	public String getProject_id() {
		return project_id;
	}

	public String getProject_type() {
		return project_type;
	}

	public void setProject_type(String project_type) {
		this.project_type = project_type;
	}

	public String getAdmin_id() {
		return admin_id;
	}

	public String toString()
	{
		String str="";
		str="\nProject Name: "+project_name+"\nProject ID: "+project_id+"\nProject type: "+project_type+
				"\nAdmin ID: "+admin_id+"\nStatus: "+(finished?"Finished":"Pending");
		return str;
	}
	//adding employee in the linked list and then writing into the file    
	public void add_project(String project_name, String project_id, String project_type, String admin_id)
	{
		
		pro_list.insert(new Project(project_name, project_id, project_type, admin_id));
                write_pro_file();
		
	}
	//display details of file
	public void display()
	{
		System.out.println(pro_list.toString());
	}
	//remove employee from the linked list at a particular position and then updating the file
	public void remove_promotion()
	{
		Node<Project> pos=pro_list.getFirst();
		pro_list.remove(pos);
                write_pro_file();
		
	}
	 //function for writing into file
        public void write_pro_file()
        {
           try 
           {
                File f1 = new File("pro1.txt");
               FileOutputStream fileOut = new FileOutputStream(f1); 
               AppendingObjectOutputStream objectOut = new AppendingObjectOutputStream(new ObjectOutputStream(fileOut));//used to append in the output stream
                Node<Project> pos=pro_list.getFirst();
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

        public Project()
	{
	
		File f1 = new File("pro.txt");//checking whether file exists or not
		if(f1.exists())
		{
                    try{
                       FileInputStream file = new FileInputStream("pro1.txt");
                        BufferedInputStream b=new BufferedInputStream(file);
                        b.mark(0);
                        b.reset();
                        ObjectInputStream obj = new ObjectInputStream(b);
                            Project e=null;
                            e=(Project) obj.readObject();//reading object from the file 
                                pro_list.insert(e);//inserting in the current linked list and appending the data in the file
                          
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
