package com.training.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.training.model.Employee;

public class EmpDao {
	
	JdbcTemplate template;    
	
    
	
	public void setTemplate(JdbcTemplate template) {    
		
	    this.template = template;    
		
	}    
		
	public int save(Employee p){    
		
	    String sql="insert into Employee(id,name,salary,designation) values(" + p.getId() + ", '"+p.getName()+"',"+p.getSalary()+",'"+p.getDesignation()+"')";    
		
	    return template.update(sql);    
		
	}    
		
	public int update(Employee p){    
		
	    String sql="update Employee set name='"+p.getName()+"', salary="+p.getSalary()+",designation='"+p.getDesignation()+"' where id="+p.getId()+"";    
		System.out.println("Inside DAO Update");
	    return template.update(sql);    
		
	}    
		
	public int delete(int id){    
		
	    String sql="delete from Employee where id="+id+"";    
		
	    return template.update(sql);    
		
	}    
		
	public Employee getEmpById(int id){    
		
	    String sql="select * from Employee where id=?";    
		System.out.println("Inside DAO class");
	    return template.queryForObject(sql, new Object[]{id},new BeanPropertyRowMapper<Employee>(Employee.class));    
		
	}    
		
	public List<Employee> getEmployees(){    
		
	    return template.query("select * from Employee",new RowMapper<Employee>(){    
		
	        public Employee mapRow(ResultSet rs, int row) throws SQLException {    
		
	            Employee e=new Employee();    
		
	            e.setId(rs.getInt(1));    
		
	            e.setName(rs.getString(2));    
		
	            e.setSalary(rs.getInt(3));    
		
	            e.setDesignation(rs.getString(4));    
		
	            return e;    
		
	        }    
		
	    });    
		
	}    
}
