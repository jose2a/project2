package com.revature.ctb.daos;

import java.util.List;

import com.revature.ctb.domains.InfoReq;

public interface InfoRequestDAO {

	public boolean addRequest(InfoReq infoReq);
	
	public boolean updateRequest(InfoReq infoReq);
	
	public List<InfoReq> getReqByEmpId(Integer employeeId);
	
	public List<InfoReq> getInfoRequests();
}
