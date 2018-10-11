package ua.pp.darknsoft.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.pp.darknsoft.models.AppUser;
import ua.pp.darknsoft.models.Client;
import ua.pp.darknsoft.repositories.ClientRepository;

import java.util.*;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    AppUserService appUserService;

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
    public Client getClientById(Long id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        return optionalClient.get();
    }

    @Override
    public boolean isClientExist(Client client) {
        return findAll().contains(client);
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
        return clientRepository.findByMeetingBetweenAndLocationId(date1, date2, id);
    }

    private Long getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AppUser appUser = new AppUser();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            appUser = appUserService.getAppUserByUserName(authentication.getName());
            return appUser.getUserId();
        } else return 0L;
    }
}
