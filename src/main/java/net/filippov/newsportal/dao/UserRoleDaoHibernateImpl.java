package net.filippov.newsportal.dao;

import java.util.List;

import net.filippov.newsportal.domain.UserRole;
import net.filippov.newsportal.exception.PersistentException;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository("UserRoleDao")
public class UserRoleDaoHibernateImpl extends GenericDaoHibernateImpl<UserRole, Long>
		implements UserRoleDao {

	public UserRoleDaoHibernateImpl() {}

	@Override
	public UserRole getByAuthority(String authority) {
		try {
    		Query query = getCurrentSession().createQuery(
    			"from UserRole where authority = :authority")
    			.setParameter("authority", authority);
    		
    		if (query.list().size() > 0) {
    			return (UserRole) query.list().get(0);
    		} else {
    			return null;
    		}
		} catch (HibernateException e) {
			throw new PersistentException("Error getting role with authority=" + authority, e);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<UserRole> getAll() {
		try {
    		return getCurrentSession().createQuery("from User").list();
		} catch (HibernateException e) {
			throw new PersistentException("Error getting list of users", e);
		}
	}
}
