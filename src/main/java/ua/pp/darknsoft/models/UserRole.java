package ua.pp.darknsoft.models;

import javax.persistence.*;

@Entity
@Table(name = "user_role",
        uniqueConstraints = {@UniqueConstraint(name = "USER_ROLE_UC", columnNames = {"user_id", "role_id"})})
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser appUser;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    private AppRole appRole;

}
