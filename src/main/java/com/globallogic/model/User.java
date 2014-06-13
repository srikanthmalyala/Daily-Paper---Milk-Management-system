package com.globallogic.model;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.compass.annotations.Searchable;
import org.compass.annotations.SearchableId;
import org.compass.annotations.SearchableProperty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * This class represents the basic "user" object in AppFuse that allows for
 * authentication and user management. It implements Acegi Security's
 * UserDetails interface.
 *
 */
@Entity
@Table(name = "app_user")
@Searchable
@XmlRootElement
public class User extends BaseObject implements Serializable, UserDetails {

    private static final long serialVersionUID = 3832626162173359411L;
    private Long id;
 
    private String userName;                    // required
    private String password;                    // required
    private String confirmPassword;
    private String oldPassword;
    private String passwordHint;
    private String firstName;                   // required
    private String lastName;                    // required
    private String email;                      // required; unique
    private Integer version;
  
    private Set<Role> roles = new HashSet<Role>();
    private boolean enabled;
    private boolean accountExpired;
    private boolean accountLocked;
    private boolean credentialsExpired;  
    private String disableUser;
    private String editUser;
    private String deleteUser;
    private String backUser;
    private Date disableDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "disable_date")
    public Date getDisableDate() {
        return disableDate;
    }

    public void setDisableDate(Date disableUserDate) {
        this.disableDate = disableUserDate;
    }

    @Transient
    public String getDisableUser() {
        disableUser = "<a href=/disableUser?id=" + id + " onClick='return confirmDelete()' title='Delete User'><img src='/images/delete.jpg' width='18px' height='18px'/></a><a href=/admin/userForm?id=" + id + "&admin=true&edit=true title='User Edit'><img src='/images/edit.jpg' width='18px' height='18px'/></a>";
        return disableUser;
    }

    public void setDisableUser(String disableUser) {
        this.disableUser = disableUser;
    }

    @Transient
    public String getEditUser() {
        editUser = "/admin/userForm?id=" + id + "&admin=true&edit=true";
        return editUser;
    }

     @Transient
    public String getDeleteUser() {
        deleteUser = "/disableUser?id=" + id;
        return deleteUser;
    }

    @Transient
    public String getBackUser() {
        backUser = "/admin/users";
        return backUser;
    }

    /**
     * Default constructor - creates a new instance with no values set.
     */
    public User() {
    }

    /**
     * Create a new instance and set the userName.
     *
     * @param userName login userName for user.
     */
     

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SearchableId
    public Long getId() {
        return id;
    }

   
    public String getUsername() {
        return userName;
    }

    
    @Column(nullable = false)
    @XmlTransient
    public String getPassword() {
        return password;
    }

    @Transient
    @XmlTransient
    public String getConfirmPassword() {
        return confirmPassword;
    }

    

    @Transient
    @XmlTransient
    public String getOldPassword() {
        return oldPassword;
    }

    @Column(name = "password_hint")
    @XmlTransient
    public String getPasswordHint() {
        return passwordHint;
    }
    
  
    @Column(name = "first_name", nullable = false, length = 50)
    @SearchableProperty
    public String getFirstName() {
        return firstName;
    }

    @Column(name = "last_name", nullable = false, length = 50)
    @SearchableProperty
    public String getLastName() {
        return lastName;
    }

    @Column(nullable = false, unique = true)
    @SearchableProperty
    public String getEmail() {
        return email;
    }

   
   
    

    /**
     * Returns the full name.
     *
     * @return firstName + ' ' + lastName
     */
    @Transient
    public String getFullName() {
        return firstName + ' ' + lastName;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
    joinColumns = {
        @JoinColumn(name = "user_id")},
    inverseJoinColumns =
    @JoinColumn(name = "role_id"))
    public Set<Role> getRoles() {
        return roles;
    }

    /**
     * Convert user roles to LabelValue objects for convenience.
     *
     * @return a list of LabelValue objects with role information
     */
    @Transient
    public List<LabelValue> getRoleList() {
        List<LabelValue> userRoles = new ArrayList<LabelValue>();

        if (this.roles != null) {
            for (Role role : roles) {
                // convert the user's roles to LabelValue Objects
                userRoles.add(new LabelValue(role.getName(), role.getName()));
            }
        }

        return userRoles;
    }

    /**
     * Adds a role for the user
     *
     * @param role the fully instantiated role
     */
    public void addRole(Role role) {
        getRoles().add(role);
    }

    /**
     * @return GrantedAuthority[] an array of roles.
     * @see
     * org.springframework.security.core.userdetails.UserDetails#getAuthorities()
     */
    @Transient
    public Set<GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new LinkedHashSet<GrantedAuthority>();
        authorities.addAll(roles);
        return authorities;
    }

    @Version
    public Integer getVersion() {
        return version;
    }

    @Column(name = "account_enabled")
    public boolean isEnabled() {
        return enabled;
    }

     @Transient
    @Column(name = "account_expired", nullable = false)
    public boolean isAccountExpired() {
        return accountExpired;
    }

    /**
     * @see
     * org.springframework.security.core.userdetails.UserDetails#isAccountNonExpired()
     * @return true if account is still active
     */
    @Transient
    public boolean isAccountNonExpired() {
        return !isAccountExpired();
    }
    
     @Transient
    @Column(name = "account_locked", nullable = false)
    public boolean isAccountLocked() {
         return accountLocked;
    }

    /**
     * @see
     * org.springframework.security.core.userdetails.UserDetails#isAccountNonLocked()
     * @return false if account is locked
     */
    @Transient
    public boolean isAccountNonLocked() {
        return !isAccountLocked();
    }

     @Transient
    @Column(name = "credentials_expired", nullable = false)
    public boolean isCredentialsExpired() {
        return credentialsExpired;
    }

    /**
     * @see
     * org.springframework.security.core.userdetails.UserDetails#isCredentialsNonExpired()
     * @return true if credentials haven't expired
     */
    @Transient
    public boolean isCredentialsNonExpired() {
        return !credentialsExpired;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String userName) {
        this.userName = userName;
    }

    

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public void setPasswordHint(String passwordHint) {
        this.passwordHint = passwordHint;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

   

    

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setAccountExpired(boolean accountExpired) {
        this.accountExpired = accountExpired;
    }

    public void setAccountLocked(boolean accountLocked) {
        this.accountLocked = accountLocked;
    }

    public void setCredentialsExpired(boolean credentialsExpired) {
        this.credentialsExpired = credentialsExpired;
    }

    /**
     * {@inheritDoc}
     */
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }

        final User user = (User) o;

        return !(user!= null ? !userName.equals(user.getUsername()) : user.getUsername() != null);

    }

    /**
     * {@inheritDoc}
     */
    public int hashCode() {
        return (userName != null ? userName.hashCode() : 0);
    }

    /**
     * {@inheritDoc}
     */
    public String toString() {
        return "";
    }
}
