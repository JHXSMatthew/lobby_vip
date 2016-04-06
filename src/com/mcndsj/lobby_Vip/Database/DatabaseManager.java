package com.mcndsj.lobby_Vip.Database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;
import com.mcndsj.lobby_Vip.Config;

public class DatabaseManager {

	
	public static DatabaseManager databaseManager;
	
	private BoneCP connectionPool;
	  
	public DatabaseManager(){
		try {
			// load the database driver (make sure this is in your classpath!)
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
	   try {
            // setup the connection pool using BoneCP Configuration
            BoneCPConfig config = new BoneCPConfig();
            // jdbc url specific to your database, eg jdbc:mysql://127.0.0.1/yourdb
            config.setJdbcUrl("jdbc:mysql://"+Config.host+ "/"+Config.database);
            config.setUsername(Config.user);
            config.setPassword(Config.password);
            config.setMinConnectionsPerPartition(5);
            config.setMaxConnectionsPerPartition(10);
            config.setPartitionCount(1);
            config.setMaxConnectionAge(3600, TimeUnit.SECONDS);
            // setup the connection pool
            connectionPool = new BoneCP(config);
            
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
	}
	

    public static DatabaseManager getInstance() {
    	if(databaseManager == null)
    		databaseManager = new DatabaseManager();
    	return databaseManager;
    }

    public Connection getConnection() throws SQLException {
        return this.connectionPool.getConnection();
    }
	
}
