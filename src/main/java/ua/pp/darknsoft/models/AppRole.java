package ua.pp.darknsoft.models;

import javax.persistence.*;

@Entity
@Table(name = "app_role",
        uniqueConstraints = {@UniqueConstraint(name = "APP_ROLE_UC", columnNames = "role_name")})
public class AppRole {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id", nullable = false)
    private Long roleId;
    @Column(name = "role_name", length = 36, nullable = false)
    private String roleName;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
