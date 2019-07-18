package com.revature.ctb.daos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.revature.ctb.domains.Employee;
import com.revature.ctb.domains.InfoReq;
import com.revature.ctb.utils.LogUtil;

public class InfoRequestDAOImpl implements InfoRequestDAO {

	private SessionFactory sessionFactory;

	@Override
	public boolean addRequest(InfoReq infoReq) {
		LogUtil.trace("InfoRequestDAOImpl - addRequest");

		Session sess = sessionFactory.openSession();

		sess.save(infoReq);

		return infoReq.getInforeqId() > 0;
	}

	@Override
	public boolean updateRequest(InfoReq infoReq) {
		LogUtil.trace("InfoRequestDAOImpl - updateRequest");

		Session sess = sessionFactory.openSession();

		sess.update(infoReq);

		return true;
	}

	@Override
	public List<InfoReq> getReqByEmpId(Integer employeeId) {
		LogUtil.trace("InfoRequestDAOImpl - getReqById");

		Session sess = sessionFactory.openSession();

		Employee employee = sess.get(Employee.class, employeeId);

		return employee.getInfoRequests();
	}

	@Override
	public List<InfoReq> getInfoRequests() {
		LogUtil.trace("InfoRequestDAOImpl - getInfoRequests");

		Session sess = sessionFactory.openSession();

		Query<InfoReq> query = sess.createQuery("from InfoReq where provided = false", InfoReq.class);

		return query.getResultList();
	}

	@Override
	public InfoReq getInfoReqById(Integer infoReqId) {
		LogUtil.trace("InfoRequestDAOImpl - getInfoReqById");

		Session sess = sessionFactory.openSession();

		return sess.get(InfoReq.class, infoReqId);
	}

}
