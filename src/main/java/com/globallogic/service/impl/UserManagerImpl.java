package com.globallogic.service.impl;

import com.globallogic.dao.UserDao;
import com.globallogic.model.User;
import com.globallogic.service.UserExistsException;
import com.globallogic.service.UserManager;
import com.globallogic.service.UserService;
import java.util.List;
import javax.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Implementation of UserManager interface.
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
@Service("userManager")
@WebService(serviceName = "UserService", endpointInterface = "com.globallogic.service.UserService")
public class UserManagerImpl extends GenericManagerImpl<User, Long> implements UserManager, UserService {

    private PasswordEncoder passwordEncoder;
    private UserDao userDao;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.dao = userDao;
        this.userDao = userDao;
    }

    /**
     * {@inheritDoc}
     */
    public User getUser(String userId) {
        return userDao.get(new Long(userId));
    }

    /**
     * {@inheritDoc}
     */
    public List<User> getUsers() {
        return userDao.getAllDistinct();
    }

    /**
     * {@inheritDoc}
     */
    public User saveUser(User user) throws UserExistsException {

        if (user.getVersion() == null) {
            // if new user, lowercase userId
            
        }

        // Get and prepare password management-related artifacts
        boolean passwordChanged = false;
        if (passwordEncoder != null) {
            // Check whether we have to encrypt (or re-encrypt) the password
            if (user.getVersion() == null) {
                // New user, always encrypt
                passwordChanged = true;
            } else {
                // Existing user, check password in DB
                String currentPassword = userDao.getUserPassword(user.getUsername());
                if (currentPassword == null) {
                    passwordChanged = true;
                } else {
                    if (!currentPassword.equals(user.getPassword())) {
                        passwordChanged = true;
                    }
                }
            }

            // If password was changed (or new user), encrypt it
            if (passwordChanged) {
                user.setPassword(passwordEncoder.encodePassword(user.getPassword(), null));
            }
        } else {
            log.warn("PasswordEncoder not set, skipping password encryption...");
        }

        try {
            return userDao.saveUser(user);
        } catch (DataIntegrityViolationException e) {
            //e.printStackTrace();
            log.warn(e.getMessage());
            throw new UserExistsException("User '" + user.getUsername() + "' already exists!");
        } catch (JpaSystemException e) { // needed for JPA
            //e.printStackTrace();
            log.warn(e.getMessage());
            throw new UserExistsException("User '" + user.getUsername() + "' already exists!");
        }
    }

    /**
     * {@inheritDoc}
     */
    public void removeUser(String userId) {
        log.debug("removing user: " + userId);
        userDao.remove(new Long(userId));
    }

    /**
     * {@inheritDoc}
     *
     * @param empId the login empId of the human
     * @return User the populated user object
     * @throws UsernameNotFoundException thrown when empId not found
     */
   
    /**
     * {@inheritDoc}
     */
    public List<User> search(String searchTerm) throws UsernameNotFoundException {
        return userDao.searchUserByEmpId(Long.parseLong(searchTerm));
    }

    /**
     * {@inheritDoc}
     */
    public User savePassword(User user) {
        return userDao.saveUser(user);
    }

    public List<User> getUsersByEnabled() {
        return userDao.getUsersByEnabled();
    }


    public User getUserByUserName(String userName) {
        return userDao.getUserByUserName(userName);
    }
}
