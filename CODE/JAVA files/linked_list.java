/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsa;
import java.io.Serializable;

class Node<T> implements Serializable
{
	
	private static final long serialVersionUID = -5453036576296198636L;
	private T data;
	private Node<T> next;
	static int cnt_emp=0,cnt_ad=0,cnt_ac=0,cnt_proj=0,cnt_prom=0;
	
	public Node(T x)
	{
		data = x;
		next = null;
		//count used to get size of various linked lists used in system
		if(x.getClass().getName().equals("Employee1"))
		{
			cnt_emp++;
		}
		if(x.getClass().getName().equals("Admin"))
		{
			cnt_ad++;
		}
		if(x.getClass().getName().equals("Activity"))
		{
			cnt_ac++;
		}
		if(x.getClass().getName().equals("Project"))
		{
			cnt_proj++;
		}
		if(x.getClass().getName().equals("Promotion"))
		{
			cnt_prom++;
		}
	
	}

	public T getData()
	{
		return data;
	}

	public void setData(T data) 
	{
		this.data = data;
	}

	public Node<T> getNext() 
	{
		return next;
	}

	public void setNext(Node<T> next) 
	{
		this.next = next;
	}
	
}

public class linked_list<T> implements Serializable
{
	
	private static final long serialVersionUID = -2022072349099728481L;
	private Node<T> first;
	
	//constructor initialising linked list's first element as null
	public linked_list()
	{
		first = null;	
	}
	//returns first element of linked list
	public Node<T> getFirst()
	{
		return first;
	}
	//tells whether linked list is empty or not
	public boolean isEmpty()
	{
		return first==null;
	}
	//used to display the details of linked list in an organised manner and is overridden by various classes 
	public String toString()
	{
		String str="";
		Node<T> pos = first;
		
		while(pos!=null)
		{
			str = str + pos.getData();
			if(pos.getNext()!=null)
				str = str + " ";
			pos = pos.getNext();
		}
		
		return str;
	}
	//used to insert data in the linked list
	public void insert(T x)
	{
		Node<T> q = new Node<T>(x);
		
		if(first == null)
			first = q;
		else
		{
			Node<T> pos = first;
			while(pos.getNext()!=null)//traversing the linked list till end
			{
				pos = pos.getNext();
			}
			
			pos.setNext(q);//setting value after traversal at the end
		}
	}
	
	public void remove(Node<T> pos)//used to remove the end element in the linked list
	{
		if(first == pos)
		{
			first = pos.getNext();
		}
		else
		{
			Node<T> prev = first;
			while(prev.getNext()!=pos)
			{
				prev = prev.getNext();
			}
			prev.setNext(pos.getNext());
		}
	}
	

}
