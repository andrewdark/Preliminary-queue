package ua.pp.darknsoft.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.pp.darknsoft.models.AppUser;
import ua.pp.darknsoft.models.Client;
import ua.pp.darknsoft.repositories.ClientRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    AppUserService appUserService;
    @Value("${app.queue.time-interval}")
    public Long timeInterval;
    @Value("${app.queue.time-start}")
    private String timeStart;
    @Value("${app.queue.time-stop}")
    private String timeStop;

    @Transactional
    @Override
    public Client createClient(Client client) {
        client.setUserId(getUserId());
        return clientRepository.saveAndFlush(client);
    }

    @Override
    public Set<Client> findAll() {
        Set<Client> tmpSet = new HashSet<>();
        for (Client cli : clientRepository.findAll()) {
            tmpSet.add(cli);
        }
        return tmpSet;
    }

    @Override
    public Set<Client> findByLastName(String lastName) {

        Set<Client> tmpSet = new HashSet<>();
        if (lastName.length() > 1) {
            for (Client cli : clientRepository.findByLastNameStartingWith(lastName)) {
                tmpSet.add(cli);
            }
        }

        return tmpSet;
    }

    @Override
    public Set<Client> findByFutureAndLastName(String lastName) {

        Set<Client> tmpSet = new HashSet<>();
        if (lastName.length() > 1) {
            for (Client cli : clientRepository.findByMeetingAfterAndLastNameStartingWith(new Date(), lastName)) {
                tmpSet.add(cli);
            }
        }

        return tmpSet;
    }

    @Override
    public Client getClientById(Long id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        return optionalClient.get();
    }

    @Override
    public boolean isClientExist(Client client) {
        return clientRepository.existsClientByLocationAndMeeting(client.getLocation(), client.getMeeting());
    }

    @Transactional
    @Override
    public Client updateClient(Client currentClient) {
        currentClient.setUserId(getUserId());
        return clientRepository.saveAndFlush(currentClient);
    }

    @Override
    public void deleteClientById(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public List<Client> findAllByMeetingBetweenAndLocationId(Date date1, Date date2, Long id) {
        return clientRepository.findByMeetingBetweenAndLocationIdOrderByMeeting(date1, date2, id);
    }

    @Override
    public List<Client> findAllByMeetingDateAndLocationId(String date, Long locationId) {

        SimpleDateFormat fIn = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat fOut = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        List<Client> tmp = new ArrayList<>();

        try {
            tmp = clientRepository.findByMeetingBetweenAndLocationIdOrderByMeeting(
                    fOut.parse(date + "T06:00:00.390+0300"),
                    fOut.parse(date + "T15:00:00.390+0300"),
                    locationId);

        } catch (ParseException e) {
            e.printStackTrace();
            return tmp;
        }
        return tmp;
    }

    private Long getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AppUser appUser = new AppUser();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            appUser = appUserService.getAppUserByUserName(authentication.getName());
            return appUser.getUserId();
        } else return 0L;
    }

    private Long getUserLocationId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AppUser appUser = new AppUser();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            appUser = appUserService.getAppUserByUserName(authentication.getName());
            return appUser.getLocation().getId();
        } else return 1L;
    }

    @Override
    public List<Client> fullDayQueue(String userDay, Long locationId) throws ParseException {
        List<Client> tmpList = findAllByMeetingDateAndLocationId(userDay, locationId);
        tmpList = correctQueue(tmpList, userDay);
        return tmpList;
    }

    @Override
    public List<Client> currentClients() {
        Date curdDateStart = new Date();
        Date curdDateStop = new Date();
        curdDateStart.setTime(curdDateStart.getTime() - timeInterval);
        curdDateStop.setTime(curdDateStop.getTime() + timeInterval);
        List<Client> tmpList =
                clientRepository.findByMeetingBetweenAndLocationIdOrderByMeeting(curdDateStart, curdDateStop, getUserLocationId());
        return tmpList;
    }

    private List<Client> correctQueue(List<Client> dbList, String userDay) throws ParseException {
        List<Client> fullClientList = new ArrayList<>(25);
        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(userDay + " " + timeStart);
        date.setTime(date.getTime() - timeInterval);
        Date stop = new SimpleDateFormat("HH:mm:ss").parse(timeStop);
        Date start = new SimpleDateFormat("HH:mm:ss").parse(timeStart);

        long maxNumberOfReceptions = (stop.getTime() - start.getTime()) / timeInterval;
        for (int i = 0; i <= maxNumberOfReceptions; i++) {
            Client client = new Client();
            date.setTime(date.getTime() + timeInterval);
            client.setFirstName("");
            client.setLastName("");
            client.setMiddleName("");
            client.setMeeting((Date) date.clone());
            fullClientList.add(client);
        }
        for (Client dbClient : dbList) {
            for (Client innerClient : fullClientList) {
                if (innerClient.getMeeting().compareTo(dbClient.getMeeting()) == 0) {
                    fullClientList.set(fullClientList.indexOf(innerClient), dbClient); //replace cycle foreach
                    break;
                }
            }
        }
        return fullClientList;
    }
}
