package com.globallogic.dao;

import com.globallogic.model.User;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * User Data Access Object (GenericDao) interface.
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public interface UserDao extends GenericDao<User, Long> {

    /**
     * Use to authenticate user.
     *
     * @param empId the user's empId
     * @return userDetails populated userDetails object
     * @throws
     * org.springframework.security.core.userdetails.UsernameNotFoundException
     * thrown when user not found in database
     */
    @Transactional
    UserDetails loadUserByUsername(String empId) throws UsernameNotFoundException;

    /**
     * Gets users information based on login empId.
     *
     * @param empId the user's empId
     * @return user object
     * @throws
     * org.springframework.security.core.userdetails.UsernameNotFoundException
     * thrown when user not found in database
     */
    User getUserByUserName(String  userName) ;

    /**
     * search users information based on given Employee Exists.
     *
     * @param empId the user's empId
     * @return user object
     * @throws
     * org.springframework.security.core.userdetails.UsernameNotFoundException
     * thrown when user not found in database
     */
    
    List<User> getUsersByEnabled() throws UsernameNotFoundException;
    
    /**
     * search users information based on given empId.
     *
     * @param empId the user's empId
     * @return user object
     * @throws
     * org.springframework.security.core.userdetails.UsernameNotFoundException
     * thrown when user not found in database
     */
    List<User> searchUserByEmpId(Long empId) throws UsernameNotFoundException;

    /**
     * Gets a list of users.
     *
     * @return List populated list of users
     */
    List<User> getUsers();

    /**
     * Saves a user's information.
     *
     * @param user the object to be saved
     * @return the persisted User object
     */
    User saveUser(User user);

    /**
     * Retrieves the password in DB for a user
     *
     * @param empId the user's empId
     * @return the password in DB, if the user is already persisted
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    String getUserPassword(String userName);
}
