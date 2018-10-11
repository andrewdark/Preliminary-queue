package ua.pp.darknsoft.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "location", uniqueConstraints = {@UniqueConstraint(name="TSC_NUMBER_UQ",columnNames = "tsc_number")})
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    Long id;

    @Column(name = "tsc_number", nullable = false)
    Integer tscNumber;

    @Column(name = "address")
    String address;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "location")
    @JsonManagedReference
    private Set<AppUser> appUsers;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "location")
    @JsonManagedReference
    //@JsonIgnore
    private Set<Client> clients;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTscNumber() {
        return tscNumber;
    }

    public void setTscNumber(Integer tscNumber) {
        this.tscNumber = tscNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<AppUser> getAppUsers() {
        return appUsers;
    }

    public void setAppUsers(Set<AppUser> appUsers) {
        this.appUsers = appUsers;
    }

    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location)) return false;
        Location location = (Location) o;
        return Objects.equals(tscNumber, location.tscNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tscNumber);
    }
}
