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

public class Admin extends Employee1 implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String admin_project_key;
	public linked_list<Admin> adm_list=new linked_list<>();
	//parametrised constructor for assigning various attributes 
	public Admin(String employee_id, String employee_name, String employee_address, String employee_designation,
			double salary, String contact, boolean status_vacancy, String password,String key)
	{
		super(employee_id,employee_name,employee_address,employee_designation,salary,contact,status_vacancy,password);
		this.admin_project_key=key;
	}
    //Non parametrised constructor is used to read previous data of file and inserting into linked list (use of object output stream so as to store full object in the file)

        public Admin()
        {
            File f1 = new File("adm1.txt");//checking whether file exists or not
		if(f1.exists())
		{
                    try{
                    	add_admin("1", "1", "1", "CO1", 123, "878451",true,"1", "1");
                      	FileInputStream file = new FileInputStream(f1);
                        BufferedInputStream b=new BufferedInputStream(file);
                        b.mark(0);
                        b.reset();
                        ObjectInputStream obj = new ObjectInputStream(b);
                       
                            Admin e=null;
                            e=(Admin) obj.readObject();//reading object from the file 
                                adm_list.insert(e);//inserting in the current linked list and appending the data in the file
                          
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
        public String getAdmin_project_key() {
        	return admin_project_key;
        }

		public void setAdmin_project_key(String admin_project_key) {
			this.admin_project_key = admin_project_key;
		}
	
        @Override
        public String toString()
        {
        	return super.toString()+"\nAdmin Project Key: "+admin_project_key;
        }
      //adding employee in the linked list and then writing into the file    
        public void add_admin(String admin_id, String admin_name, String admin_address, String admin_designation,
			 double salary, String contact, boolean status_vacancy, String password,String admin_project_key)
        {
		
        	adm_list.insert(new Admin(admin_id,admin_name,admin_address,admin_designation, salary,contact,status_vacancy,password,admin_project_key));
        	write_adm_file();
        }
        @Override
      //display details of file
        public void display()
        {
        	System.out.println(adm_list.toString());
        }
    	//remove employee from the linked list at a particular position and then updating the file
        public void remove_admin(String id)
        {
        	Node<Admin> pos=adm_list.getFirst();
        	while(pos!=null)
        	{
        		if(id.equals(pos.getData().employee_id))
        		{
        			adm_list.remove(pos);
        		}
        		pos=pos.getNext();
        	}
        write_adm_file();
        }
        //function for writing into file
    	public void write_adm_file()
    	{
    		try 
    		{
    			File f1 = new File("adm1.txt");
    			FileOutputStream fileOut = new FileOutputStream(f1); 
    			AppendingObjectOutputStream objectOut = new AppendingObjectOutputStream(new ObjectOutputStream(fileOut));//used to append in the output stream
                Node<Admin> pos=adm_list.getFirst();
                while(pos!=null)
                {
                	objectOut.writeObject(pos.getData());//traversing the whole linked list data and writing into the stream
                	pos=pos.getNext();
                }
                objectOut.flush();//flushing the stream
                objectOut.close();//closing streams
                fileOut.close();
    		} 
    		catch (IOException ex) 
    		{
               
    		}
        }
    	  //methods to change/update various details of employee 
        @Override
        public void changeContact(String id,String pass,String newno) throws IOException
        {
        	Node<Admin> pos = adm_list.getFirst();
        	while(pos!=null)
        	{
        		if(pos.getData().employee_id.equals(id) && pos.getData().password.equals(pass))
        		{
        			pos.getData().contact = newno;
        		}
        		pos = pos.getNext();
        	}
        	write_adm_file();
        }
        @Override
        public void changePassword(String id,String pass,String newpass) throws IOException
        {
        	Node<Admin> pos = adm_list.getFirst();
			while(pos!=null)
			{
				if(pos.getData().employee_id.equals(id) && pos.getData().password.equals(pass))
				{
					pos.getData().password = newpass;
				}
				
				pos = pos.getNext();
			}
			
			write_adm_file();
        }
        @Override
        public void changeAddress(String id,String pass,String newadd) throws IOException
        {
        	Node<Admin> pos = adm_list.getFirst();
			while(pos!=null)
			{
				if(pos.getData().employee_id.equals(id) && pos.getData().password.equals(pass))
				{
					pos.getData().employee_address = newadd;
				}
				
				pos = pos.getNext();
			}
			write_adm_file();
        }
        //assigning activity to group of employees by taking into consideration their id and password 
        public void assign(String id,String pass,int num,String act_id,linked_list<Project> pro_list,linked_list<Activity> act_list,linked_list<Employee1> elist) throws IOException
        {
        	Node<Admin> pos = adm_list.getFirst();
			while(pos!=null)
			{
				if(pos.getData().employee_id.equals(id) && pos.getData().password.equals(pass))
				{
					Node<Project> pro=pro_list.getFirst();
					while(pro!=null)
					{
						if(pos.getData().admin_project_key.equals(pro.getData().project_id))//checking whether the project key and id provided by admin are same or not
						{
							Node<Activity> a=act_list.getFirst();
							while(a!=null)
							{
								if(act_id.equals(a.getData().activity_id))
								{
									a.getData().assign_group(elist);
									System.out.println("Group of employees:");
									System.out.println(a.getData().toString());
								}
								a=a.getNext();
							}
						}
						pro=pro.getNext();
					}
					
				}
				
				pos = pos.getNext();
			}
			
			write_adm_file();
	   }
	
	
}
