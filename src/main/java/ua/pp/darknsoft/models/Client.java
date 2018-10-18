package ua.pp.darknsoft.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "client",
        uniqueConstraints = {@UniqueConstraint(name = "CLIENT_UC", columnNames = {"meeting_time", "location_fk"})})
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 48)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 48)
    private String lastName;

    @Column(name = "middle_name", nullable = true, length = 48)
    private String middleName;

    @Enumerated(value = EnumType.STRING)
    private Action action;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "location_fk")
    @JsonBackReference
    private Location location;

    @Column(name = "meeting_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date meeting;

    @Column(name = "user_id")
    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Date getMeeting() {
        return meeting;
    }

    public void setMeeting(Date meeting) {
        this.meeting = meeting;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;
        Client client = (Client) o;
        return Objects.equals(firstName, client.firstName) &&
                Objects.equals(lastName, client.lastName) &&
                Objects.equals(middleName, client.middleName) &&
                Objects.equals(action, client.action) &&
                Objects.equals(location, client.location) &&
                Objects.equals(meeting, client.meeting);
    }

    @Override
    public int hashCode() {

        return Objects.hash(firstName, lastName, middleName, action, location, meeting);
    }
}
