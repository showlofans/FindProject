//package com.findproject.service;
//
//import com.findproject.dao.PersonDao;
//import com.findproject.domain.PersonBean;
//
//public class PersonService {
//	//private String person_id;
//	String flag="";
//	private PersonBean perbean;
//	private String email,resources,username;
//	
//	private PersonDao dao=new PersonDao();
//	//��ø�����Ϣ
//	public PersonBean getpereson(String person_id){
//		perbean=dao.getPerson(person_id);
//		return perbean;
//	}
//	//ע���û��˺�
////	public String addper(String person_id){
////		
////		flag=dao.addper(person_id);//�ɹ�success��ʧ��erro��error1
////		return flag;
////	}
//	public String delper(String person_id){
//		flag=dao.deletePerson(person_id);//�ɹ�success��ʧ��erro��error1
//		return flag;
//	}
//	//���ĸ�����Ϣ(�������ݣ����ؽ��)
//	public String modify_person(PersonBean updper){
//		flag=dao.updateperson(updper);
//		return flag;
//	}
//	//���ĸ�����Ϣ(�������ݣ����ؽ��)
//		public String modify_per(String person_id,String person_name,String project_resource,String e_mail){
//			flag=dao.updateperson(person_name,project_resource,e_mail);
//			return flag;
//		}
//	public String getEmail() {
//		return email;
//	}
//	public void setEmail(String email) {
//		this.email = email;
//	}
//	public String getResources() {
//		return resources;
//	}
//	public void setResources(String resources) {
//		this.resources = resources;
//	}
//	public String getUsername() {
//		return username;
//	}
//	public void setUsername(String username) {
//		this.username = username;
//	}
//	
//}
