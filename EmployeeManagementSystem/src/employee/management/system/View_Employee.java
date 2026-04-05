package employee.management.system;

import java.awt.Choice;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import net.proteanit.sql.DbUtils;

public class View_Employee extends JFrame implements ActionListener{

	Choice choiceEmp;
	JButton Search,update,back,print;
	
	JTable table;
	 View_Employee()  {
		// TODO Auto-generated constructor stub
		
		getContentPane().setBackground(Color.orange);
		JLabel search =new JLabel("Search By Employee Id");
		search.setBounds(20,20,150,20);
		add(search);
		
		choiceEmp=new Choice();
		choiceEmp.setBounds(180, 20, 150, 20);
		add(choiceEmp);
		try {
			
			conn c=new conn();
			ResultSet resultSet=c.statement.executeQuery("select * from employee");
			while(resultSet.next())
			{
				choiceEmp.add(resultSet.getString("empid"));
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		table=new JTable();
		
		try {
			conn c=new conn();
			ResultSet resultSet=c.statement.executeQuery("select * from employee");
			table.setModel(DbUtils.resultSetToTableModel(resultSet));
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		JScrollPane jp=new JScrollPane(table);
		jp.setBounds(0, 100, 900, 600);
		add(jp);
		
		Search=new JButton("search");
		Search.setBounds(20, 70, 80, 20);
		Search.addActionListener(this);
		add(Search);
		
		print=new JButton("print");
		print.setBounds(120, 70, 80, 20);
		print.addActionListener(this);
		add(print);
		
		update=new JButton("Update");
		update.setBounds(220, 70, 80, 20);
		update.addActionListener(this);
		add(update);
		
		back=new JButton("Back");
		back.setBounds(320, 70, 80, 20);
		back.addActionListener(this);
		add(back);
		
		setSize(900, 700);
		setLayout(null);
		setLocation(300, 100);
		setVisible(true);
		
	}
	public static void main(String[] args) {
		new View_Employee();
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==Search)
		{
			String query="select * from employee where empid= '"+choiceEmp.getSelectedItem()+"'";
			
			
		try {
			
			conn c=new conn();
			ResultSet resultset=c.statement.executeQuery(query);
			table.setModel(DbUtils.resultSetToTableModel(resultset));
		} catch (Exception e2) {
			e2.printStackTrace();
			// TODO: handle exception
		}
		}
		
		else if(e.getSource()==print)
		{
			try {
				table.print();
				
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		
		else if(e.getSource()==update)
		{
			setVisible(false);
			
		}
		
		else
		{
			setVisible(false);
			new Main_class();
		}
		
	}
}
