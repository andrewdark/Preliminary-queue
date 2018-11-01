package ua.pp.darknsoft.commands;

import ua.pp.darknsoft.models.AppRole;
import ua.pp.darknsoft.models.Location;
import ua.pp.darknsoft.models.UserDetails;

import javax.persistence.Transient;
import java.util.Set;

public class UserCommand {
    private Long userId;
    private String userName;
    @Transient
    private String encryptedPassword;
    private Boolean enabled;
    private Location location;
    private UserDetails userDetails;
    private Set<AppRole> roles;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public Set<AppRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<AppRole> roles) {
        this.roles = roles;
    }
}
