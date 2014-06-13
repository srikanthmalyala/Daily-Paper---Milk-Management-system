package com.globallogic.dao.hibernate;

import com.globallogic.dao.UserDao;
import com.globallogic.model.User;
import java.util.List;
import javax.persistence.Table;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

/**
 * This class interacts with Spring's HibernateTemplate to save/delete and
 * retrieve User objects.
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a> Modified by
 * <a href="mailto:dan@getrolling.com">Dan Kibler</a> Extended to implement
 * Acegi UserDetailsService interface by David Carter david@carter.net Modified
 * by <a href="mailto:bwnoll@gmail.com">Bryan Noll</a> to work with the new
 * BaseDaoHibernate implementation that uses generics.
 */
@Repository("userDao")
public class UserDaoHibernate extends GenericDaoHibernate<User, Long> implements UserDao, UserDetailsService {

    /**
     * Constructor that sets the entity to User.class.
     */
    public UserDaoHibernate() {
        super(User.class);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<User> getUsers() {
        return getHibernateTemplate().find("from User");
    }

    /**
     * {@inheritDoc}
     */
    public User saveUser(User user) {
        if (log.isDebugEnabled()) {
            log.debug("user's id: " + user.getId());
        }
        getHibernateTemplate().saveOrUpdate(user);
        // necessary to throw a DataIntegrityViolation and catch it in UserManager
        getHibernateTemplate().flush();
        return user;
    }

    /**
     * Overridden simply to call the saveUser method. This is happenening
     * because saveUser flushes the session and saveObject of BaseDaoHibernate
     * does not.
     *
     * @param user the user to save
     * @return the modified user (with a primary key set if they're new)
     */
    @Override
    public User save(User user) {
        return this.saveUser(user);
    }

    /**
     * {@inheritDoc}
     */
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return getUserByUserName(userName);
    }

    /**
     * {@inheritDoc}
     */
    public User getUserByUserName(String userName) throws UsernameNotFoundException {
        List users = getHibernateTemplate().find("from User where userName=?", userName);
        if (users == null || users.isEmpty()) {
            throw new UsernameNotFoundException("user '" + userName + "' not found...");
        } else {
            return (User) users.get(0);
        }
    }

    /**
     * {@inheritDoc}
     */
    public String getUserPassword(String userName) {
        SimpleJdbcTemplate jdbcTemplate =
                new SimpleJdbcTemplate(SessionFactoryUtils.getDataSource(getSessionFactory()));
        Table table = AnnotationUtils.findAnnotation(User.class, Table.class);
        return jdbcTemplate.queryForObject(
                "select password from " + table.name() + " where userName=?", String.class, userName);

    }

    /**
     * {@inheritDoc}
     */
    public List<User> searchUserByEmpId(Long empId) {
        List user = getHibernateTemplate().find("from User where empId=?", empId);
        if (user == null || user.isEmpty()) {
            throw new UsernameNotFoundException("EmpId not found in database");
        } else {
            return user;
        }
    }

    public List<User> getUsersByEnabled()  {
        return getHibernateTemplate().find("from User where account_enabled=1");
    }
}
