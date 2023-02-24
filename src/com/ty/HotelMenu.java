package com.ty;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;



import com.mysql.cj.protocol.Resultset;

public class HotelMenu {

	public static void main(String[] args) {
		Scanner in=new Scanner(System.in);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection=DriverManager.getConnection("jdbc:mysql://Localhost:3306/hotel","root","root");
			boolean repeat=true;
			do {
				System.out.println("---------welcome to hotel Taj---------------------");
				System.out.println("1.Add menu\n2.Delete Menu\n3.updateMenu\n4.getMenu\n5.getMenu based on the item");
				System.out.println("------------------------------------------------------------------------");
				System.out.println("Enter choice 1 to 6 "
						+ "	");
				int ch=in.nextInt();
				switch (ch) {
				case 1: {
					System.out.println("Enter the menu id");
					int id=in.nextInt();
					System.out.println("Enter the name ");
					String name=in.next();
					System.out.println("Enter the quantity");
					int quantity=in.nextInt();
					System.out.println("----------------\nEnter the cost\n----------------------------");
					int cost=in.nextInt();
					PreparedStatement preparedStatement=connection.prepareStatement("insert into hotel values(?,?,?,?)");
				preparedStatement.setInt(1,id);
				preparedStatement.setString(2,name);
				preparedStatement.setInt(3,quantity);
				preparedStatement.setInt(4,cost);
				preparedStatement.execute();
				System.out.println("menu saved");
				}break;
				case 2:{
					System.out.println("Enter the Id You want to remove");
					int id=in.nextInt();
					PreparedStatement preparedStatement=connection.prepareStatement("delete from hotel where id=?");
					preparedStatement.setInt(1, id);
					preparedStatement.execute();
					System.out.println("item deleted");
					
				}break;
				case 3:{
					System.out.println("enter the Id you want to update");
					int id=in.nextInt();
					System.out.println("enter the name u want to update");
					String name=in.next();
					System.out.println("Enter the quantity to update");
					int quantity=in.nextInt();
					System.out.println("Enter the cost to update");
					int cost=in.nextInt();
					PreparedStatement preparedStatement=connection.prepareStatement("update hotel set name=?,quantity=?,cost=? where id=? ");
					preparedStatement.setString(1,name);
					preparedStatement.setInt(2,quantity);
					preparedStatement.setInt(3,cost);
					preparedStatement.setInt(4,id);
					preparedStatement.execute();
					System.out.println("item updated");
					
				}break;
				case 4:{
					System.out.println("your Menu is");
					PreparedStatement preparedStatement=connection.prepareStatement("Select * from hotel");
					ResultSet resultset=preparedStatement.executeQuery();
					while(resultset.next()) {
						System.out.print(" id -> "+resultset.getInt(1)+",");
						System.out.print(" name->"+resultset.getString(2)+",");
						System.out.print(" quantity->"+resultset.getInt(3)+",");
						System.out.print(" cost->"+resultset.getInt(4)+";");
						System.out.println();
					}
				}		
				break;
				case 5:{
					System.out.println("Enter the name retrive data ");
					String name=in.next();
					PreparedStatement preparedStatement=connection.prepareStatement("select * from hotel where name=?");
					preparedStatement.setString(1, name);
					ResultSet resultset=preparedStatement.executeQuery();
					while(resultset.next()) {
						System.out.println("id --> "+resultset.getInt(1));
						System.out.println("name-->"+resultset.getString(2));
						System.out.println("quantity-->"+resultset.getInt(3));
						System.out.println("cost-->"+resultset.getInt(4)+";\n");
					}
				}break;
				case 6:{
					repeat=false;
					System.out.println("thank you");
				}
				default:System.out.println("Invalid choice");
				break;
				}
			}while (repeat) ;
				connection.close();	
			}catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	
}

}
