package com.young.animal.dao;

import java.util.HashMap;
import java.util.List;

import com.young.animal.vo.Account;
import com.young.animal.vo.RoleAccount;

public interface AccountDAO {
	public int selectMemberCount(HashMap<String, String> map);
	public List<RoleAccount> selectMemberList();
	public int account(Account ac);
	public int roleaccount(RoleAccount ra);
	public void roleupdate(RoleAccount ra);
}
