package ua.pp.darknsoft.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "app_user",
        uniqueConstraints = {@UniqueConstraint(name = "APP_USER_UC", columnNames = "user_name")})
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "user_name", length = 36, nullable = false)
    @Size(min = 2)
    private String userName;

    @Column(name = "encrypted_password", length = 128, nullable = false)
    @Size(min = 6)
    private String encryptedPassword;

    @Column(name = "enabled", length = 1, nullable = false)
    private Boolean enabled;

    @ManyToOne
    @JsonBackReference
    private Location location;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private UserDetails userDetails;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AppUser)) return false;
        AppUser appUser = (AppUser) o;
        return Objects.equals(userName, appUser.userName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userName);
    }
}
