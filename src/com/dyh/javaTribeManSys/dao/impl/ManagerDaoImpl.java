package com.dyh.javaTribeManSys.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dyh.javaTribeManSys.dao.IManagerDao;
import com.dyh.javaTribeManSys.pojo.User;

/**
 * 管理员关于数据库操作实现类，实现IManagerDao接口:
 * 1、完成管理员对数据库的具体操作
 * @author ding
 *
 */
public class  ManagerDaoImpl implements IManagerDao{
	
	private Connection conn = null ;
	private PreparedStatement pstmt = null ;
	private ResultSet rs = null ;
	
	
	/**
	 * 连接数据库
	 * @return
	 */
	public Connection getConnection() {	
		conn = DatabaseConnectionFactory.newInstance().
				createDao(DBConstants.DBTYPE_MYSQL).
				getConnection();	
		return conn;	
	}

	/**
	 * 按学号进行模糊查询
	 * @param id
	 * @return
	 */
	public ResultSet selectLikeId(String id) {
		
		conn = getConnection();
				
		try {
			//1、Sql语句
			String selectSql = "SELECT * FROM tb_user WHERE id LIKE ?";	
			//2、创建PrepareStatement对象
			pstmt = conn.prepareStatement(selectSql);	
			//3、设置占位符参数
			pstmt.setString(1, "%"+id+"%");					
			//4、执行查询
			rs = pstmt.executeQuery();			
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		return rs;
	}
	
	/**
	 * 按姓名进行模糊查询
	 * @param name
	 * @return
	 */
	public ResultSet selectLikeName(String name) {
    	
		conn =  getConnection();
		
		try {
			//1、Sql语句
			String selectSql = "SELECT * FROM tb_user WHERE name LIKE ?";
			//2、创建PrepareStatement对象
		    pstmt = conn.prepareStatement(selectSql);	
			//3、设置占位符参数
			pstmt.setString(1, "%"+name+"%");					
			//4、执行查询
			rs = pstmt.executeQuery();		
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	/**
	 * 按学号进行精确查询
	 */
	public ResultSet selectById(String id) {
		conn = getConnection();
		
		try {
			//1、Sql语句
			String selectSql = "SELECT * FROM tb_user WHERE id = ?";	
			//2、创建PrepareStatement对象
			pstmt = conn.prepareStatement(selectSql);	
			//3、设置占位符参数
			pstmt.setString(1, id);					
			//4、执行查询
			rs = pstmt.executeQuery();			
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		return rs;
	}
	
	/**
	 * 按管理员权限进行精确查询
	 * @param isManager
	 * @return
	 */
    public ResultSet selectByIsManager(String isManager) {
    	
		conn =  getConnection();
		
		try {
			//1、Sql语句
			String selectSql = "select * from tb_user where isManager = ?";	
			//2、创建PrepareStatement对象
		    pstmt = conn.prepareStatement(selectSql);	
			//3、设置占位符参数
			pstmt.setString(1, isManager);					
			//4、执行查询
			rs = pstmt.executeQuery();		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
    /**
     * 按年级、系别、性别进行条件查询
     * @param grade
     * @param department
     * @param sex
     * @return
     */
	public ResultSet selectByGrade_Department_Sex(String grade,String department,String sex){
		
		conn = getConnection();
		
		String sql = "select * from tb_user where grade =? and department=? and sex=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, grade);
			pstmt.setString(2, department);
			pstmt.setString(3, sex);
			rs = pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
		
	/**
	 * 插入用户
	 * @param id
	 * @param name
	 * @param headImage
	 * @param sex
	 * @param grade
	 * @param department
	 * @param phone
	 * @param qq
	 * @param sign
	 * @param birthday
	 * @param address
	 * @param isManager
	 * @return
	 */
	public boolean insertUser(User user) {          
		conn =  getConnection();
		int i = 0 ;
		
		try {
			//SQL语句
			String insertSql = "insert into tb_user(id,password,name,headImage,sex,grade,department,phone,qq,sign,birthday,address,isManager) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
			//创建PrepareStatement对象
			pstmt = conn.prepareStatement(insertSql);	
			//设置参数
			pstmt.setString(1, user.getId());	
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getName());	
			pstmt.setString(4, user.getHeadImage());	
			pstmt.setString(5, user.getSex());	
			pstmt.setString(6, user.getGrade());	
			pstmt.setString(7, user.getDepartment());	
			pstmt.setString(8, user.getPhone());	
			pstmt.setString(9, user.getQQ());	
			pstmt.setString(10, user.getSign());	
			pstmt.setString(11, user.getBirthday());	
			pstmt.setString(12, user.getBirthday());	
			pstmt.setString(13, user.getIsManager());		
			//执行添加			
			i = pstmt.executeUpdate();				
		} catch (SQLException e) {
			System.out.println("插入数据库时出错：");
			e.printStackTrace();		
		} catch (Exception e) {
			System.out.println("插入时出错：");
			e.printStackTrace();		
		}
		
		if(i>0){
			return true ;
		}else{
			return false ;
		}

	}
	
	/**
	 * 按学号删除
	 * @param id
	 * @return
	 */
	public boolean deleteUser(String id) {
		
		conn =  getConnection();
        int i = 0;
		
		try {
			//SQL语句
			String deleteSql = "delete from tb_user where id=?";
			//创建PreparedStatement语句
			pstmt  = conn.prepareStatement(deleteSql);
			//为占位符赋值
			pstmt.setString(1, id);	
			
			//执行SQL语句
			i = pstmt.executeUpdate();			
		} catch (SQLException e) {
			System.out.println("插入数据库时出错：");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("插入时出错：");
			e.printStackTrace();
		}	
		
		if(i>0){
			return true ;
		}else{
			return false;
		}
		
	}
	
	/**
	 * 修改个人信息
	 * @param id
	 * @param name
	 * @param headImage
	 * @param sex
	 * @param grade
	 * @param department
	 * @param phone
	 * @param qq
	 * @param sign
	 * @param birthday
	 * @param address
	 * @return
	 */
	public boolean updateUser(User user) {
		conn =  getConnection();
		int i = 0;
		
		try {					
			String updateSql = "update tb_user set name=?,"
					+ "headImage=?,sex=?,grade=?,department=?,phone=?,"
					+ "qq=?,sign=?,birthday=?,address=? where id=?";
			PreparedStatement pstmt = conn.prepareStatement(updateSql);
			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getHeadImage());
			pstmt.setString(3, user.getSex());
			pstmt.setString(4, user.getGrade());
			pstmt.setString(5, user.getDepartment());
			pstmt.setString(6, user.getPhone());
			pstmt.setString(7, user.getQQ());
			pstmt.setString(8, user.getSign());
			pstmt.setString(9, user.getBirthday());
			pstmt.setString(10, user.getAdress());
			pstmt.setString(11, user.getId());		
			
			i = pstmt.executeUpdate() ;			
		} catch (SQLException e) {
			System.out.println("插入数据库时出错：");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("插入时出错：");
			e.printStackTrace();
		}
		
		if(i>0){
			return true;
		}else{
			return false ;
		}
		
	}

	/**
	 * 修改管理员权限
	 * @param id
	 * @param isManager
	 * @return
	 */
    public boolean updateIsManager(String id,String isManager) {   	  
  	    conn =  getConnection();
  	    int i = 0 ;
  	    
  		try {					
  			String updateSql = "update tb_user set isManager=? where id=?";
  			pstmt = conn.prepareStatement(updateSql);
  			pstmt.setString(1, isManager);
  			pstmt.setString(2, id);		
  			
  			i = pstmt.executeUpdate() ;
  		} catch (SQLException e) {
  			System.out.println("插入数据库时出错：");
  			e.printStackTrace();
  		} catch (Exception e) {
  			System.out.println("插入时出错：");
  			e.printStackTrace();
  		}
  		
  		if(i>0){
			return true ;
		}else{
			return false ;
		}
  		
  	}
	
	/**
	 * 修改密码
	 * @param id
	 * @param password
	 * @return
	 */
    public boolean updatePassword(String id,String password) {
    	conn =  getConnection();   	
    	int i = 0 ;
  	    
  		try {					
  			String updateSql = "update tb_user set password=? where id=?";
  			pstmt = conn.prepareStatement(updateSql);
  			pstmt.setString(1, password);
  			pstmt.setString(2, id);	
  			i = pstmt.executeUpdate() ;
  			

  		} catch (SQLException e) {
  			System.out.println("插入数据库时出错：");
  			e.printStackTrace();
  		} catch (Exception e) {
  			System.out.println("插入时出错：");
  			e.printStackTrace();
  		}
  		if(i>0){
			return true ;
		}else{
			return false ;
		}
  	}
	
    /**
     * 登陆---检查用户名是否与密码相符
     * @param id
     * @param password
     * @return
     */
    public boolean checkUser(String id,String password){
		
		boolean yn = false;
		
		try {
			//1-2得到数据库连接对象
			conn = getConnection();
			//3、SQL语句
			String checkSql = "select * from tb_user where id=? and password=?";			
			//4、创建PrepareStatement对象
			pstmt = conn.prepareStatement(checkSql);	
			//5、占位符设置参数
			pstmt.setString(1, id);
			pstmt.setString(2, password);		
			//6、执行查询
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()){
				yn = true;
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return yn;		
    }
		
    /**
     * 检查是否为管理员
     * @param id
     * @return
     */
	public boolean isManager(String id){
  		
  		ResultSet rs = selectById(id) ;
  		User user = getUser(rs);
  		closeAll();
  		
  		if(user != null){
  			String isManager = user.getIsManager();	
  	  		if(isManager!=null){
  	  			if(isManager.trim().equals("是"))
  	  	  			return true;
  	  	  		else
  	  	  			return false;
  	  		}else{
  	  			return false;
  	  		}
  		}
  		else{
  			return false;
  		}
  		
  		
  	}
  	
    /**
     * 得到一个指定用户  
     * @param rs
     * @return
     */
    public User getUser(ResultSet rs) {
      	
      	User  user  =  null;
      	
      	try {
      		
			while (rs.next()) {    	
				user  =  new User();
				
				user.setId(rs.getString("id"));
				user.setPassword(rs.getString("password"));
				user.setName(rs.getString("name"));
				user.setHeadImage(rs.getString("headImage"));
				user.setSex(rs.getString("sex"));
				user.setGrade(rs.getString("grade"));
				user.setDepartment(rs.getString("department"));
				user.setPhone(rs.getString("phone"));
				user.setQQ(rs.getString("qq"));
				user.setSign(rs.getString("sign"));
				user.setBirthday(rs.getString("birthday"));
				user.setAdress(rs.getString("address"));   		
				user.setIsManager(rs.getString("isManager"));
			}
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
      	
  		return user;		     
     }
    
  	/**
  	 * 得到所有用户
  	 * @param rs
  	 * @return
  	 */
  	public List<User> getAllUsers(ResultSet rs) {
  		      
  		List<User>  users = new ArrayList<User>();
     	
      	try {
      			    		
      		while ( rs.next() ) {			
				User  user  =  new User();
					
				user.setId(rs.getString("id"));
				user.setPassword(rs.getString("password"));
				user.setName(rs.getString("name"));
				user.setHeadImage(rs.getString("headImage"));
				user.setSex(rs.getString("sex"));
				user.setGrade(rs.getString("grade"));
				user.setDepartment(rs.getString("department"));
				user.setPhone(rs.getString("phone"));
				user.setQQ(rs.getString("qq"));
				user.setSign(rs.getString("sign"));
				user.setBirthday(rs.getString("birthday"));
				user.setAdress(rs.getString("address"));   		
				user.setIsManager(rs.getString("isManager"));
				
				users.add(user);
			}
      		
		} catch (SQLException e) {
	
			e.printStackTrace();
		}
     	    
  	 return users;   	
     }
  	
	/**
	 * 关闭数据库，释放资源
	 */
	public void closeAll(){
		//关闭结果集
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		//关闭statement
		if(pstmt!=null){
			try {
				pstmt.close();
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
		}
		//关闭 数据库连接
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
		
				e.printStackTrace();
			}
		}
		
	}

	
	 
}
