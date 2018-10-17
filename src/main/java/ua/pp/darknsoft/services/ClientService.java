package ua.pp.darknsoft.services;

import ua.pp.darknsoft.models.Client;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Set;

public interface ClientService {

    Client createClient(Client client);

    Set<Client> findAll();

    Client getClientById(Long id);

    //Client getAppClientByClientName(String name);

    boolean isClientExist(Client client);

    Client updateClient(Client currentClient);

    void deleteClientById(Long id);

    List<Client> findAllByMeetingBetweenAndLocationId(Date date1, Date date2, Long id);

    List<Client> findAllByMeetingDateAndLocationId(String date,Long locationId);

    List<Client> fullDayQueue(String userDay, Long locationId) throws ParseException;
}
