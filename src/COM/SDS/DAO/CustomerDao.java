package COM.SDS.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import COM.SDS.FRAME.Dao;
import COM.SDS.FRAME.SQL;
import COM.SDS.VO.CustomerVO;
import COM.SDS.VO.ItemVO;

public class CustomerDao extends Dao<String, CustomerVO> {

	@Override
	public void insert(CustomerVO v, Connection con) throws Exception {
		// Connection을 통해 PreparedStatement 생성
		// SQL 작성하여 DB전송
		// Resource Close
		// SQL for DB. NOT Coding
		PreparedStatement pstmt = con.prepareStatement(SQL.insertCustomer);
		try {
			pstmt.setString(1, v.getId());
			pstmt.setString(2, v.getPwd());
			pstmt.setString(3, v.getName());
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			close(pstmt);
		}
		
		
	}

	@Override
	public void delete(String t, Connection con) throws Exception {
		// TODO Auto-generated method stub
		
		PreparedStatement pstmt = con.prepareStatement(SQL.deleteCustomer);
		try {
			pstmt.setString(1, t);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			close(pstmt);
		}
		
	}

	@Override
	public void update(CustomerVO v, Connection con) throws Exception {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = con.prepareStatement(SQL.updateCustomer);
		try {
			pstmt.setString(1, v.getPwd());
			pstmt.setString(2, v.getName());
			pstmt.setString(3, v.getId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			close(pstmt);
		}
	}

	@Override
	public CustomerVO select(String t, Connection con) throws Exception {
		CustomerVO customer=null;
		PreparedStatement pstmt = null;
		ResultSet rset=null;
		try {
			pstmt=con.prepareStatement(SQL.getCustomer);
			pstmt.setString(1, t);
			rset=pstmt.executeQuery();
			rset.next();
			customer=new CustomerVO(rset.getString("ID"),rset.getString("PWD"),rset.getString("NAME"));
			customer.setItem(new ItemVO(rset.getString("ID"),rset.getString("ITEM"),rset.getDouble("PRICE")));
			

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			close(pstmt);
			close(rset);
		}
		
		
		
		return customer;
	}

	@Override
	public ArrayList<CustomerVO> select(Connection con) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<CustomerVO> customers=new ArrayList<>();
		CustomerVO customer=null;
		PreparedStatement pstmt = con.prepareStatement(SQL.selectAll);
		ResultSet rset=null;
		try {
			rset=pstmt.executeQuery();
			while(rset.next())
			{
				customer=new CustomerVO(rset.getString("ID"),rset.getString("PWD"),rset.getString("NAME"));
				customer.setItem(new ItemVO(rset.getString("ID"),rset.getString("ITEM"),rset.getDouble("PRICE")));
				customers.add(customer);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;       
		}finally {
			close(pstmt);
			close(rset);
		}
		
		
		
		return customers;
	}

}




