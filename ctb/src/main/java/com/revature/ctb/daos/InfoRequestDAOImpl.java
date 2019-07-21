package com.revature.ctb.daos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.revature.ctb.domains.Employee;
import com.revature.ctb.domains.InfoReq;

@Repository
public class InfoRequestDAOImpl implements InfoRequestDAO {

	private SessionFactory sessionFactory;

	@Override
	public boolean addRequest(InfoReq infoReq) {
		Session session = sessionFactory.getCurrentSession();

		session.save(infoReq);

		return infoReq.getInforeqId() > 0;
	}

	@Override
	public boolean updateRequest(InfoReq infoReq) {
		Session session = sessionFactory.getCurrentSession();

		session.update(infoReq);

		return true;
	}

	@Override
	public List<InfoReq> getReqByEmpId(Integer employeeId) {
		Session session = sessionFactory.getCurrentSession();

		Employee employee = session.get(Employee.class, employeeId);

		return employee.getInfoRequests();
	}

	@Override
	public List<InfoReq> getInfoRequests() {
		Session session = sessionFactory.getCurrentSession();

		Query<InfoReq> query = session.createQuery("from InfoReq where provided = false", InfoReq.class);

		return query.getResultList();
	}

	@Override
	public InfoReq getInfoReqById(Integer infoReqId) {
		Session session = sessionFactory.getCurrentSession();

		return session.get(InfoReq.class, infoReqId);
	}

}
