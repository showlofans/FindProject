package com.stufinish.findproject.service;

public interface UserService {
	public void userLogin(String loginName,String loginPassword) throws Exception;
	public void userRegister(String loginName,String loginPassword,String resource) throws Exception;
}
