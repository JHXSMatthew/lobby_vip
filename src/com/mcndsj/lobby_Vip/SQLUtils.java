package com.mcndsj.lobby_Vip;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mcndsj.lobby_Vip.Database.DatabaseManager;

public class SQLUtils {
	
	
	/**
	 * @param name
	 * @return <=0 if not vip
	 * 1 = vip
	 * 2 = vip+
	 * 3 = mvp
	 * 4 = mvp+
	 */
	public static int getVipLevel(String name){
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		int returnValue = -1;
		try {
			connection = DatabaseManager.getInstance().getConnection();
			if(connection == null || connection.isClosed()){
				return -1;
			}else{
				statement = connection.createStatement();
				resultSet = statement.executeQuery("SELECT level FROM `vipStats` Where `name`='"+name+"';");
				if(resultSet.next()){
					returnValue = resultSet.getInt("level");
				}
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
	       if (resultSet != null) try { resultSet.close(); } catch (SQLException e) {e.printStackTrace();}
           if (statement != null) try { statement.close(); } catch (SQLException e) {e.printStackTrace();}
           if (connection != null) try { connection.close(); } catch (SQLException e) {e.printStackTrace();}
		}
		
		return returnValue;
	}
	
	
	/**
	 * @param name
	 * @param level
	 * @param isNew
	 * @throws Exception
	 */
	public static void setVipLevel(String name, int level, boolean isNew) throws Exception{
		Connection connection = null;
		Statement statement = null;
		try{
			connection = DatabaseManager.getInstance().getConnection();
			if(connection == null || connection.isClosed()){
				throw new Exception("Error when writting to VIPDB, no connection!");
			}
			statement = connection.createStatement();
			if(isNew){
				statement.executeUpdate("INSERT INTO `vipStats` (`name`,`level`) VALUES ('"+name+"','"+ level + "');");
			}else{
				statement.executeUpdate("UPDATE `vipStats` SET `level`='"+ level +"' Where `name`='"+name+"';");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
	           if (statement != null) try { statement.close(); } catch (SQLException e) {e.printStackTrace();}
	           if (connection != null) try { connection.close(); } catch (SQLException e) {e.printStackTrace();}
		}
			
	}
	

}
