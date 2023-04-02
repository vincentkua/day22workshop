package nus.iss.day22workshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nus.iss.day22workshop.model.RSVP;
import nus.iss.day22workshop.repository.RSVPRepository;

@Service
public class RSVPService {

    @Autowired
    RSVPRepository rsvpRepo;

    public List<RSVP> findAllRSVP() {
        return rsvpRepo.findAllRSVP();
    }

    public RSVP findByName(String name) {
        return rsvpRepo.findByName(name);
    }

    public Integer insertRSVP(RSVP rsvp) {
        return rsvpRepo.insertRSVP(rsvp);
    }

    public Integer updateRSVP(String email, RSVP rsvp) {
        return rsvpRepo.updateRSVP(email, rsvp);
    }

    public Integer countRSVP() {
        return rsvpRepo.countRSVP();
    }

}
