package gpa.common;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;


public class DbUtil {
	

	private ResultSet rs = null;
//	public static final String url = "jdbc:mysql://192.168.1.122/gpa";  
	public static  String url = "jdbc:mysql://172.16.3.245/gpa";  
    public static  String name = "com.mysql.jdbc.Driver";  
    public static  String user = "me";  
    public static  String password = "me";  
    public static  String path = "C:"; 
    
    public DbUtil(){
    	 InputStream in;
		try {
			String path = System.getProperty("user.dir");
			
			path=path.substring(0, path.length()-3)+"webapps/gpa/config.properties";
			in = new BufferedInputStream(new FileInputStream(path));
			Properties p = new Properties();
	    	p.load(in);
	    	name = p.getProperty("name");
	    	url = p.getProperty("url");
	    	user = p.getProperty("user");
	    	password = p.getProperty("password");
	    	path = p.getProperty("path");
	    	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    
	/**
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	public boolean execute(String sql) throws SQLException {
		boolean vBOOL = false;
		System.out.println("数据库操作|更新SQL:"+sql);
		Statement stat = null;
		Connection conn =null;
		try {
			conn = this.getConn();
			stat = conn.createStatement();
			vBOOL=stat.execute(sql);

		} catch (SQLException e) {
		
			throw e;
		}finally{
			if(stat!=null){
				stat.close();
			}
			if(conn!=null){
				conn.close();
			}
			
		}
		return vBOOL;
	}
	
	public ArrayList select(String sql) throws SQLException {
		System.out.println("数据库操作|更新SQL:"+sql);
		ArrayList<HashMap> list = new ArrayList();
		Statement stat = null;
		Connection conn =null;
		try {
			conn = this.getConn();
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while(rs.next()){
				HashMap map = new HashMap();
				createRSMap(map);
				System.out.println("记录："+map.toString());
				list.add(map);
			}
		} catch (SQLException e) {
		
			throw e;
		}finally{

			if(rs!=null){
				rs.close();
				rs=null;
			}
			
			if(stat!=null){
				stat.close();
				stat=null;
			}
			if(conn!=null){
				conn.close();
				conn=null;
			}
		}
		return list;
	}
	
	private void createRSMap(HashMap map)
	throws SQLException {
		ResultSetMetaData md = rs.getMetaData();
		for(int i=1; i<=md.getColumnCount(); i++){
			int cType = md.getColumnType(i);
			Object  colvalue = null;
			switch(cType){
			case Types.INTEGER: colvalue=rs.getInt(i);break;
			case Types.FLOAT : colvalue =rs.getFloat(i);break;
			case Types.DOUBLE :colvalue = rs.getDouble(i);break;
			case Types.NUMERIC:colvalue = rs.getLong(i);break;
			case Types.CLOB:colvalue = rs.getClob(i);break;
			default: colvalue = rs.getObject(i);
			}
			if(colvalue == null)
				colvalue ="";
			String colname = md.getColumnName(i);
			map.put(colname, colvalue);
}
}
	
	
	public Connection getConn(){
		Connection conn =null;
		try {
			Class.forName(name);
			conn = DriverManager.getConnection(url, user, password);//获取连接 
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
		
	}

   

}
