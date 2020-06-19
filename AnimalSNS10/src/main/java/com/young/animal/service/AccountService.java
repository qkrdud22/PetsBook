package com.young.animal.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.young.animal.dao.AccountDAO;
import com.young.animal.vo.Account;
import com.young.animal.vo.RoleAccount;

@Service
public class AccountService {
	@Autowired
	private AccountDAO accDAO;
	static Logger logger = LoggerFactory.getLogger(AccountService.class);
	
	public List<RoleAccount> selectMemberList(){
		logger.info("AccountService.selectMemberList 인수값 : " );
		List<RoleAccount> list = null;
		try {
			list = accDAO.selectMemberList();
		} catch(Exception e) {
			e.printStackTrace();
		}
		logger.info("AccountService.selectMemberList 리턴값 : " + list);
		return list;
	}
	
	public void roleupdate(RoleAccount ra) {
		logger.info("AccountService.roleupdate 인수 : " + ra);
		try {
			if(ra != null) {
				if(ra.getUsername() != null && ra.getUsername().trim().length() > 0) {
					if(ra.getRole() != null && ra.getRole().trim().length() > 0) {
						ra.setUsername(ra.getUsername().trim());
						ra.setRole(ra.getRole().trim());
						accDAO.roleupdate(ra);
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		logger.info("AccountService.roleupdate 리턴값 : ");
	}
	
	public void insert(Account ac, RoleAccount ra) {
		logger.info("AccountService.insert 인수값 :" + ac + "insert" + ra);
		try {
			if(ac != null) {
				accDAO.account(ac);
				ra.setUsername(ac.getUserid());
				ra.setRole("ROLE_USER");
				accDAO.roleaccount(ra);
			}			
		} catch(Exception e) {
			e.printStackTrace();
		}
		logger.info("AccountService.insert 리턴값: ");
	}
}
