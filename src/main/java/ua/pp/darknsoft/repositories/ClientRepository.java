package ua.pp.darknsoft.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.pp.darknsoft.models.Client;

import java.util.Date;
import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findByMeetingBetweenAndLocationIdOrderByMeeting(Date date1, Date date2, Long locationId);

    List<Client> findByLastNameStartingWith(String lastName);

    List<Client> findByMeetingAfterAndLastNameStartingWith(Date date, String lastName);
}
