package com.findproject.dao;

import java.sql.Connection;
import java.sql.ResultSet;

import com.findproject.DB.DB;
import com.findproject.domain.PersonBean;

public class PersonDao {
	//String person_id, int contact_gets, int total_gets,
	//String person_name, String project_resource, String e_mail
	 // person_name,   project_resource,   e_mail
	Connection conn;
	static DB db=new DB();
	
	public String updateImg(String email, String picPath){
		String flag="";
		String sql="update person set person_img='"+picPath+"' where e_mail='"+email+"'";
		try{
			flag=db.execUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
			flag="error1";
		}
		return flag;
	}
	
	//ע���˻�
//	public String addperson(String e_mail){
//		String rt="";
//		String flag=existPerson(e_mail);
//		if(flag.equals("nonexist")){
//			String sql="insert into	person(person_id,person_name,project_resource,e_mail)" +
//					"values(?,?,?,?)";
//			try{
//				rt=db.execUpdate(sql);  //�ɹ�success��ʧ��error
//			}catch(Exception e){
//				e.printStackTrace();
//				rt="error1";
//			}
//		}else
//			rt=flag;
//		return rt;//�ɹ�success��ʧ��error,����exist
//	}
	
	//�жϸ����Լ�ע����Ϣ�Ƿ����
	public String existPerson(String e_mail) {
		String flag="nonexist";
		ResultSet rs=null;
		String sql="select * from person where e_mail='"+e_mail+"'";
		try{
			rs=db.execSelect(sql);
			if(rs.next()){
				flag="exist";
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			flag="exist";
		}
		return flag;
	}
	//��ø�����Ϣ
	public PersonBean getPerson(String e_mail){
		ResultSet rs=null;
		PersonBean person=null;
		String sql="select * from person where e_mail='"+e_mail+"'";
		try{
			rs=db.execSelect(sql);
			while(rs.next())
			{
				person=new PersonBean(rs.getString("e_mail"),rs.getString("project_resource"),rs.getString("person_name"));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
//		finally{
//			db.freeCon();
//		}
		return person;
	}
	//ע�������Ϣ�����������м�¼��
	public String deletePerson(String email){
		String flag=""; 
		String sql="delete person where e_mail='"+email+"'";
		try{
			flag=db.execUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
			flag="error1";
		}
		return flag;
	}
	//��Ӹ�����Ϣ
	public String addper(PersonBean addper){
		String rt="";
		//String flag=existPerson(addper.getE_mail());
		//if(flag.equals("nonexist")){
			String sql="insert into	person(e_mail,project_resource,person_name,person_img)values('"+addper.getE_mail()+"','"+addper.getProject_resource()+"','"+addper.getPerson_name()+"','"+""+"')";
				try{
					rt=db.execUpdate(sql);  //�ɹ�success��ʧ��error
				}catch(Exception e){
					e.printStackTrace();
					rt="error1";
				}
//		}else
//			rt=flag;
		return rt;//�ɹ�success��ʧ��error,����exist
	}
	//String person_id, int contact_gets, int total_gets,
		//String person_name, String project_resource, String e_mail
	//���¸���ʵ����Ϣ
	public String updateperson(PersonBean updper){
		String flag="";
		String sql="update person set person_name='"+updper.getPerson_name()+"',project_resource='"+updper.getProject_resource()+"',where email='"+updper.getE_mail()+"'";
		try{
			flag=db.execUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
			flag="error1";
		}
		return flag;
	}
//	//���¸�����Ϣ
//	public String updateper(String person_id,String person_name,String project_resource,String e_mail){
//		String flag="";
//		String sql="update person set person_name='"+person_name+"',project_resource='"+project_resource+"'," +
//						"email='"+e_mail+"',where person_id='"+person_id+"'";
//		try{
//			flag=db.execUpdate(sql);
//		}catch(Exception e){
//			e.printStackTrace();
//			flag="error1";
//		}
//		return flag;
//	}	
	
	public PersonDao() {
		super();
		db.getConn();
	}
	public void setConnClose(){
		if(conn!=null){
			try{conn.close();}catch(Exception ex){}
		}
	}
}
