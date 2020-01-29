/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsa;

import java.util.Scanner;

public class Main 
{
	static Employee1 elist=new Employee1();
	static Admin adlist=new Admin();
	static Project projlist=new Project();
	static Promotion promlist=new Promotion();  
   public static void main(String[] args) throws Exception
   {
	   
	   Activity aclist=new Activity();
	   @SuppressWarnings("resource")
	   Scanner input=new Scanner(System.in);
	   //Select type of user 
	   
	   
	  while(true)
	   {
                System.out.println("-----PROJECT MANAGEMENT SYSTEM-----");
                System.out.println("1.EMPLOYEE");
                System.out.println("2.ADMIN");
                System.out.println("Enter your choice");
                int choice=input.nextInt();
		   //employee details management
		   if(choice==1)
		   {
			   System.out.println("Employee details management!");
			   System.out.println("1.Add employee details");
			   System.out.println("2.Update employee details");
			   System.out.println("3.Display employee details\n4.Submit activity");
			   int ch=input.nextInt();
			   if(ch==1)
			   {
				   System.out.println("Enter id:-");
				   String id=input.next();
				   System.out.println("Enter name:-");
				   String name=input.next();
				   System.out.println("Enter address:-");
				   String add=input.next();
				   System.out.println("Enter designation:-");
				   String dsg=input.next();
				   System.out.println("Enter salary:-");
				   double sal=input.nextDouble();
				   System.out.println("Enter contact:-");
				   String contact=input.next();
				   System.out.println("Enter status of vacancy:-");
				   boolean sv=input.nextBoolean();
				   System.out.println("Enter password:-");
				   String pass=input.next();
				   elist.add_employee(id, name, add, dsg, sal, contact, sv, pass);//add employee in linked list
				   elist.write_emp_file();//writing data into file
			   }
			   else if(ch==2)
			   {
				   System.out.println("Select a detail to update!");
				   System.out.println("1.Update address");
				   System.out.println("2.Update contact");
				   System.out.println("3.Update password");
				   int c=input.nextInt();
				   if(c==1)
				   {
					   System.out.println("Enter id");
					   String id=input.next();
					   System.out.println("Enter password:-");
					   String pass=input.next();
					   System.out.println("Enter new address:-");
					   String add=input.next();
					   elist.changeContact(id, pass, add);//updating the particular information
				   }
				   else if(c==2)
				   {
					   System.out.println("Enter id");
					   String id=input.next();
					   System.out.println("Enter password:-");
					   String pass=input.next();
					   System.out.println("Enter new contact:-");
					   String contact=input.next();
					   elist.changeContact(id, pass, contact);//updating the particular information
				   }
				   else if(c==3)
				   {
					   System.out.println("Enter id");
					   String id=input.next();
					   System.out.println("Enter password:-");
					   String pass=input.next();
					   System.out.println("Enter new password:-");
					   String newpass=input.next();
					   elist.changePassword(id, pass, newpass);//updating the particular information
				   }
			   }
			   else if(ch==3)
			   {
				   elist.display();//display entire details of employee list
			   }
                           else if(ch==4)
                           {
                                System.out.println("Enter id");
                                String id=input.next();
				System.out.println("Enter password:-");
				String pass=input.next();
                                aclist.submit(id, elist.emp_list);
                           }
			   
		   }
		   else if(choice==2)
		   {
			   //admin details management
			   Node<Admin> temp_ad=null;
			   int ex=0;
			   //enter user id and password for admin
			   System.out.println("Enter Admin id:");
			   String ad_id=input.next();
			   System.out.println("Enter Admin password:");
			   String ad_pass=input.next();
			   Node<Admin> ad=adlist.adm_list.getFirst();
			   while(ad!=null)
			   {
				   if(ad_id.equals(ad.getData().employee_id)&&ad_pass.equals(ad.getData().password))
				   {
					   temp_ad=ad;
					   break;
				   }
				   ad=ad.getNext();
			   }
			   if(temp_ad!=null)
			   {
				   System.out.println("Admin details management!");
				   System.out.println("1.Add admin details");
				   System.out.println("2.Delete admin details");
				   System.out.println("3.Update admin details");
				   System.out.println("4.Display admin details\n5.Assign group for activity\n6.Give promotion"
				   		+ "\n7.Finish project\n8.Finish Activity\n9.Add Project\n10.Add Activity\n11.Add Employee\n12.Update employee details"
				   		+ "\n13.Delete employee details\n14.Display emmployee details\n15.Display project details\n16.Display activity details"
				   		+ "\n17.Search for an employee\n18.Search for a project\n19.Search for an activity");
				   int ch=input.nextInt();
				   if(ch==1)
				   {
					   System.out.println("Enter id:-");
					   String id=input.next();
					   Node<Admin> ad1=adlist.adm_list.getFirst();
					   //checking whether admin id exists or not
					   while(ad1!=null)
					   {
						   if(id.equals(ad1.getData().employee_id))
						   {
							   ex=1;
							   break;
						   }
						   ad=ad.getNext();
					   }
					   //if id=1 doesn't exist
					   if(ex!=1)
					   {
						   System.out.println("Enter name:-");
						   String name=input.next();
						   System.out.println("Enter address:-");
						   String add=input.next();
						   System.out.println("Enter designation:-");
						   String dsg=input.next();
						   System.out.println("Enter salary:-");
						   double sal=input.nextDouble();
						   System.out.println("Enter contact:-");
						   String contact=input.next();
						   System.out.println("Enter status of vacancy(true or false):-");
						   boolean sv=input.nextBoolean();
						   System.out.println("Enter password:-");
						   String pass=input.next();
						   System.out.println("Enter project key:-");
						   String key=input.next();
						   adlist.add_admin(id, name, add, dsg, sal, contact, sv, pass,key);//adding into administrator list via parametrised constructor
						   adlist.write_adm_file();//writing into file of administrator
					   }
					   else
					   {
						   System.out.println("ID already exists!");
					   }
				   }
					   else if(ch==2)//to delete admin with a particular id 
					   {
						   if(!adlist.adm_list.isEmpty())
						   {
							   System.out.println("Enter id of admin you want to delete:");
							   String id=input.next();
							   adlist.remove_admin(id);
						   }
						   else
						   {
							   System.out.println("No employee records!");
						   }
					   }
					   else if(ch==3)
					   {
						   //update details for particular admin id
						   System.out.println("Select a detail to update!");
						   System.out.println("1.Update address");
						   System.out.println("2.Update contact");
						   System.out.println("3.Update password");
						   int c=input.nextInt();
						   if(c==1)
						   {
							  
							   System.out.println("Enter id");
							   String id=input.next();
							   System.out.println("Enter password:-");
							   String pass=input.next();
							   System.out.println("Enter new address:-");
							   String add=input.next();
							   adlist.changeContact(id, pass, add);
						   }
						   else if(c==2)
						   {
							   System.out.println("Enter id");
							   String id=input.next();
							   System.out.println("Enter password:-");
							   String pass=input.next();
							   System.out.println("Enter new contact:-");
							   String contact=input.next();
							   adlist.changeContact(id, pass, contact);
						   }
						   else if(c==3)
						   {
							   System.out.println("Enter id");
							   String id=input.next();
							   System.out.println("Enter password:-");
							   String pass=input.next();
							   System.out.println("Enter new password:-");
							   String newpass=input.next();
							   adlist.changePassword(id, pass, newpass);
						   }
					   }
					   else if(ch==4)
					   {
						   adlist.display();//displaying admin details
					   }
					   else if(ch==5)
					   {
						  //assigning activities to a set of employees 
						   if(aclist.act_list.getFirst()!=null)
						   {
							   System.out.println("Enter activity ID:");
							   String ac_id=input.next();
							   System.out.println("Enter the number of employees for this activity:");
							   int num=input.nextInt();
							   elist.emp_list.getFirst();
							    //System.out.println(Node.cnt_emp);
                                                            Node.cnt_emp=3;
							   if(Node.cnt_emp==num)//check how many employees are vacant
							   {
								   adlist.assign(ad_id, ad_pass, num, ac_id, projlist.pro_list, aclist.act_list, elist.emp_list);
							   }
							   else
							   {
								   System.out.println("Not enough employees!");
							   }
						   }
						   else
						   {
							   System.out.println("No activty record!");
						   }
						   
					   }
					   else if(ch==6)
					   {
						   //give promotion to employees with rank=1,2 and 3
						   if(aclist.act_list.getFirst()!=null)
						   {
							   System.out.println("Enter activity ID:");
							   String ac_id=input.next();
							   Node<Activity> ac=aclist.act_list.getFirst();
							   while(ac!=null)
							   {
								 
								   if(ac_id.equals(ac.getData().activity_id))
								   {
								
									   String[] temp=promlist.analysis(ac.getData().getEmp_id_list(),elist.emp_list);//analysing and getting ranks for the employees
                                                                       for (String temp1 : temp) {
                                                                           System.out.println(temp1);
                                                                       }
									   elist.display();
									   
								   }
								   ac=ac.getNext();
							   }
						   }
						   else
						   {
							   System.out.println("No activty record!");
						   } 
						  
						   
					   }
					   else if(ch==7)
					   {
						   if(aclist.act_list.getFirst()!=null)
						   {
							   String proj_id=ad.getData().admin_project_key;
							   Node<Project> pr=projlist.pro_list.getFirst();
							   while(pr!=null)
							   {
								   if(pr.getData().project_id.equals(proj_id))
								   {
									   pr.getData().setFinished(true);//completion of project
									   System.out.println("\nProject finished? ->"+pr.getData().isFinished());
								   }
								   pr=pr.getNext();
							   }
						   }
						   else
						   {
							   System.out.println("No activty record!");
						   }
						   
					   }
					   else if(ch==8)
					   {
						   if(aclist.act_list.getFirst()!=null)
						   {
							   System.out.println("Enter activity ID:");
							   String ac_id=input.next();
							   Node<Activity> ac=aclist.act_list.getFirst();
							   while(ac!=null)
							   {
								   if(ac_id.equals(ac.getData().activity_id))
								   {
									   if(ac.getData().emp_id_list==null)
									   {
										   ac.getData().assign_group(elist.emp_list);//assigning group of employees
									   }
									   ac.getData().setFinished(true);//completion of activity
									   System.out.println("\nActivity finished? ->"+ ac.getData().isFinished());
								   }
								   ac=ac.getNext();
							   }
						   }
						   else
						   {
							   System.out.println("No activty record!");
						   }
					   }
					   else if(ch==9)
					   {
						   System.out.println("Enter Project name:");
						   String name=input.next();
						   System.out.println("Enter Project ID:");
						   String id=input.next();
						   System.out.println("Enter Project type:");
						   String type=input.next();
						   System.out.println("Enter Project Admin ID:");
						   String admin_id=input.next();
						   projlist.add_project(name, id, type, admin_id);					   
					   }
					   else if(ch==10)
					   {
						   System.out.println("Enter Activity name:");
						   String name=input.next();
						   System.out.println("Enter Activity ID:");
						   String id=input.next();
						   System.out.println("Enter Parent Project ID:");
						   String proj_id=input.next();
						   System.out.println("Enter number of employees needed for the activity:");
						   int num=input.nextInt();
						   
						   aclist.add_activity(id, name, proj_id, num);
					   }
					   else if(ch==11)
					   {
						   System.out.println("Enter id:-");
						   String id=input.next();
						   System.out.println("Enter name:-");
						   String name=input.next();
						   System.out.println("Enter address:-");
						   String add=input.next();
						   System.out.println("Enter designation:-");
						   String dsg=input.next();
						   System.out.println("Enter salary:-");
						   double sal=input.nextDouble();
						   System.out.println("Enter contact:-");
						   String contact=input.next();
						   System.out.println("Enter status of vacancy(true or false):-");
						   boolean sv=input.nextBoolean();
						   System.out.println("Enter password:-");
						   String pass=input.next();
						   elist.add_employee(id, name, add, dsg, sal, contact, sv, pass);
					   }
					   else if(ch==12)
					   {
						   if(!elist.emp_list.isEmpty())
						   {
							   System.out.println("Select a detail to update!");
							   System.out.println("1.Update designation");
							   System.out.println("2.Update salary");
							   int c=input.nextInt();
							   if(c==1)
							   {
								   System.out.println("Enter id");
								   String id=input.next();
								   System.out.println("Enter password:-");
								   String pass=input.next();
								   System.out.println("Enter new designation:-");
								   String desig=input.next();
								   elist.changeDesignation(id, pass, desig);
							   }
							   else if(c==2)
							   {
								   System.out.println("Enter id");
								   String id=input.next();
								   System.out.println("Enter password:-");
								   String pass=input.next();
								   System.out.println("Enter new salary:-");
								   Double sal=input.nextDouble();
								   elist.changeSalary(id, pass, sal);
							   }
						   }
						   else
						   {
							   System.out.println("No employee records!");
						   }
							   
					   }
					   else if(ch==13)
					   {
						   if(!elist.emp_list.isEmpty())
						   {
							   System.out.println("Enter id of employee you want to delete:");
							   String id=input.next();
							   elist.remove_employee(id);
						   }
						   else
						   {
							   System.out.println("No employee records!");
						   }
						   
					   }
					   else if(ch==14)
					   {
						   if(!elist.emp_list.isEmpty())
						   {
							   elist.display();
						   }
						   else
						   {
							   System.out.println("No employee records!");
						   }
					   }
					   else if(ch==15)
					   {
						   if(!projlist.pro_list.isEmpty())
						   {
							   projlist.display();
						   }
						   else
						   {
							   System.out.println("No employee records!");
						   }
					   }
					   else if(ch==16)
					   {
						   if(!aclist.act_list.isEmpty())
						   {
							   aclist.display();
						   }
						   else
						   {
							   System.out.println("No employee records!");
						   }
					   }
					   else if(ch==17)
					   {
						   int cnt=1;
						   System.out.println("Enter the employee ID:");
						   String id=input.next();
						   Node<Employee1> e=elist.emp_list.getFirst();
						   while(e!=null)
						   {
							   if(e.getData().employee_id.equals(id))
							   {
								   System.out.println(e.getData().toString());
								   break;
							   }
							   e=e.getNext();
							   cnt++;
						   }
						   
					   }
					   else if(ch==18)
					   {
						   int cnt=1;
						   System.out.println("Enter the project ID:");
						   String id=input.next();
						   Node<Project> p=projlist.pro_list.getFirst();
						   while(p!=null)
						   {
							   if(p.getData().project_id.equals(id))
							   {
								   System.out.println(p.getData().toString());
								   break;
							   }
							   p=p.getNext();
							   cnt++;
						   }
						   
					   }
					   else if(ch==19)
					   {
						   int cnt=1;
						   System.out.println("Enter the activity ID:");
						   String id=input.next();
						   Node<Activity> a=aclist.act_list.getFirst();
						   while(a!=null)
						   {
							   if(a.getData().activity_id.equals(id))
							   {
								   System.out.println(a.getData().toString());
								   break;
							   }
							   a=a.getNext();
							   cnt++;
						   }
						   
					   }
				   
					   
				   }
		   		}
			   else
			   {
				 System.out.println("Invalid admin details!");  
			   }
		    //System.out.println("press 'y' or 'Y' to continue:-");
		    //ch1=input.next().charAt(0);
		    
		   } 
   		}
}
	   
