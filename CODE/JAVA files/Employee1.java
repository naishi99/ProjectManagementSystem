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

import java.io.Serializable;

public class Employee1 implements Serializable
{
	
	private static final long serialVersionUID = 1L;
	String employee_id;
	String employee_name;
	String employee_address;
	String employee_designation;
	double salary;
	String contact;
	boolean status_vacancy;
	String password;
        boolean submitted;
        int atime,subtime;
        linked_list<Employee1> emp_list=new linked_list<>();;
        //Non parametrised constructor is used to read previous data of file and inserting into linked list (use of object output stream so as to store full object in the file)
        public Employee1()
		{
			
			File f1 = new File("emp.txt");
			if(f1.exists())//checking whether file exists or not
			{
	                    try{
	                    	add_employee("1", "1", "1", "CO1", 123, "878451",true,"1");
	                       FileInputStream file = new FileInputStream("emp1.txt");
	                        BufferedInputStream b=new BufferedInputStream(file);
	                        b.mark(0);
	                        b.reset();
	                        ObjectInputStream obj = new ObjectInputStream(b);
	                       
	                            Employee1 e=null;
	                            e=(Employee1) obj.readObject();//reading object from the file 
	                                emp_list.insert(e);//inserting in the current linked list and appending the data in the file
	                          
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
     //parametrised constructor for assigning various attributes  
	public Employee1(String employee_id, String employee_name, String employee_address, String employee_designation,
		 double salary, String contact, boolean status_vacancy, String password) 
	{
                
		super();
		this.employee_id = employee_id;
		this.employee_name = employee_name;
		this.employee_address = employee_address;
		this.employee_designation = employee_designation;
		this.salary = salary;
		this.contact = contact;
		this.status_vacancy = status_vacancy;
		this.password = password;
               
	}
     //getters and setters for all attributes   
	public String getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}
	public String getEmployee_name() {
		return employee_name;
	}
	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}
	public String getEmployee_address() {
		return employee_address;
	}
	public void setEmployee_address(String employee_address) {
		this.employee_address = employee_address;
	}
	public String getEmployee_designation() {
		return employee_designation;
	}
	public void setEmployee_designation(String employee_designation) {
		this.employee_designation = employee_designation;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public boolean isStatus_vacancy() {
		return status_vacancy;
	}
	public void setStatus_vacancy(boolean status_vacancy) {
		this.status_vacancy = status_vacancy;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	 public String toString()
         {
		 return "\nName:" + employee_name + "\nID:" + employee_id  + "\nAddress:" + employee_address + "\nContact Number:"+ contact + "\nDesignation:" + employee_designation +"\nSalary is:"+ salary + "\nVacancy Status:"+ status_vacancy  ;
	       
	  }
    //adding employee in the linked list and then writing into the file    
	public void add_employee(String employee_id, String employee_name, String employee_address, String employee_designation,
			 double salary, String contact, boolean status_vacancy, String password) throws IOException
	{
		
		emp_list.insert(new Employee1(employee_id,employee_name,employee_address,employee_designation, salary,contact,status_vacancy,password));
                write_emp_file();
		
	}
	//display details of file
	public void display()
	{
		System.out.println(emp_list.toString());
	}
	//remove employee from the linked list at a particular position and then updating the file
	public void remove_employee(String id) throws IOException
	{
		Node<Employee1> pos=emp_list.getFirst();
		while(pos!=null)
		{
			if(id.equals(pos.getData().employee_id))
			{
				emp_list.remove(pos);
			}
			pos=pos.getNext();
            
		}
		write_emp_file();
	}
        
        //function for writing into file
       public void write_emp_file()
        {
           try 
           {
                File f1 = new File("emp1.txt");
               FileOutputStream fileOut = new FileOutputStream(f1); 
               AppendingObjectOutputStream objectOut = new AppendingObjectOutputStream(new ObjectOutputStream(fileOut));//used to append in the output stream
                Node<Employee1> pos=emp_list.getFirst();
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
       //methods to change/update various details of employee 
       public void changeContact(String id,String pass,String newno) throws IOException
	   {
			
			Node<Employee1> pos = emp_list.getFirst();
			
			while(pos!=null)
			{
				if(pos.getData().employee_id.equals(id) && pos.getData().password.equals(pass))
				{
					pos.getData().contact = newno;
				}
				pos = pos.getNext();
			}
			
			write_emp_file();
	   }
	public void changePassword(String id,String pass,String newpass) throws IOException
	   {
		   Node<Employee1> pos = emp_list.getFirst();
			
			while(pos!=null)
			{
				if(pos.getData().employee_id.equals(id) && pos.getData().password.equals(pass))
				{
					pos.getData().password = newpass;
				}
				
				pos = pos.getNext();
			}
			
			write_emp_file();
	   }
	public void changeAddress(String id,String pass,String newadd) throws IOException
	   {
		   Node<Employee1> pos = emp_list.getFirst();
			
			while(pos!=null)
			{
				if(pos.getData().employee_id.equals(id) && pos.getData().password.equals(pass))
				{
					pos.getData().employee_address = newadd;
				}
				
				pos = pos.getNext();
			}
			
			write_emp_file();
	   }
	
	public void changeDesignation(String id,String pass,String desig) throws IOException
	   {
		   Node<Employee1> pos = emp_list.getFirst();
			
			while(pos!=null)
			{
				if(pos.getData().employee_id.equals(id) && pos.getData().password.equals(pass))
				{
					pos.getData().employee_designation = desig;
				}
				
				pos = pos.getNext();
			}
			
			write_emp_file();
	   }
	
	public void changeSalary(String id,String pass,Double salary) throws IOException
	   {
		   Node<Employee1> pos = emp_list.getFirst();
			
			while(pos!=null)
			{
				if(pos.getData().employee_id.equals(id) && pos.getData().password.equals(pass))
				{
					pos.getData().salary=salary;
				}
				
				pos = pos.getNext();
			}
			
			write_emp_file();
	   }
        
}
