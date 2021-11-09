package com.crowdstreet.demo.Count;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CountResource {

	public boolean insertValue(int value) throws SQLException {
		Connection co = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa",""); 
		String query = "insert into counts(value) values(" + value + ")";
		PreparedStatement st = co.prepareStatement(query);
		boolean rs = st.execute();
		
		return rs;
	}
	
	public int getCount(long id) throws SQLException {
		Connection co = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa",""); 
		String query = "select * from counts where id = "+ (int)id;
		PreparedStatement st = co.prepareStatement(query);
		ResultSet rs = st.executeQuery();
		
		int value = -1;
		while(rs.next()) {
			value = rs.getInt("value");
		}
		return value;
	}

}