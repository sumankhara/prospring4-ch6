package com.apress.prospring4.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.apress.prospring4.model.Contact;
import com.mysql.jdbc.Statement;

public class ContactDaoJDBCImpl implements ContactDao {

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}
	
	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://localhost:6000/prospring4", "root", "su1028kh");
	}
	
	private void closeConnection(Connection connection) {
		if(connection == null) {
			return;
		}
		
		try {
			connection.close();
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	@Override
	public List<Contact> findByFirstName(String firstName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Contact> findAll() {
		List<Contact> result = new ArrayList<>();
		Connection connection = null;
		
		try {
			connection = getConnection();
			
			PreparedStatement statement = connection.prepareStatement("select * from contact");
			
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				Contact contact = new Contact();
				contact.setId(resultSet.getLong("id"));
				contact.setFirstName(resultSet.getString("FIRST_NAME"));
				contact.setLastName(resultSet.getString("LAST_NAME"));
				contact.setBirthDate(resultSet.getDate("BIRTH_DATE"));
				
				result.add(contact);
			}
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		finally {
			closeConnection(connection);
		}
		
		return result;
	}

	@Override
	public String findLastNameById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String findFirstNameById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Contact contact) {
		Connection connection = null;
		
		try {
			connection = getConnection();
			
			PreparedStatement statement = connection.prepareStatement("insert into contact (first_name, last_name, birth_date) values (?,?,?)", 
					Statement.RETURN_GENERATED_KEYS);
			
			statement.setString(1, contact.getFirstName());
			statement.setString(2, contact.getLastName());
			statement.setDate(3, contact.getBirthDate());
			
			statement.execute();
			
			ResultSet generatedKeys = statement.getGeneratedKeys();
			
			if(generatedKeys.next()) {
				contact.setId(generatedKeys.getLong(1));
			}
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		finally {
			closeConnection(connection);
		}
	}

	@Override
	public void update(Contact contact) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Long contactId) {
		Connection connection = null;
		
		try {
			connection = getConnection();
			
			PreparedStatement statement = connection.prepareStatement("delete from contact where id = ?");
			statement.setLong(1, contactId);
			statement.execute();
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		finally {
			closeConnection(connection);
		}
	}

}
