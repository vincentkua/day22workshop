package nus.iss.day22workshop.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import nus.iss.day22workshop.model.RSVP;
import nus.iss.day22workshop.service.RSVPService;

@RestController
@RequestMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
public class RSVPController {

    @Autowired
    RSVPService rsvpSvc;

    // Task2.1
    @GetMapping(value = "/api/rsvps")
    public ResponseEntity<String> getAllRSVP() {
        List<RSVP> rsvps = new LinkedList<>();
        rsvps = rsvpSvc.findAllRSVP();
        JsonObject payload = Json.createObjectBuilder()
                .add("rsvps", rsvps.toString())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(payload.toString());
    }

    // Task2.2
    @GetMapping(value = "/api/rsvp", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<String> getByName(@RequestParam("q") String q) {

        System.out.println(q);
        RSVP rsvp = new RSVP();
        rsvp = rsvpSvc.findByName(q);
        if (rsvp == null) {
            return ResponseEntity.status(HttpStatus.valueOf(404))
                    .body("Error 404 : user not found or more than 1 data found !!! ");
        } else {
            JsonObject payload = Json.createObjectBuilder()
                    .add("rsvp", rsvp.toString())
                    .build();
            return ResponseEntity.status(HttpStatus.OK).body(payload.toString());
        }
    }

    // Task2.3
    @PostMapping(value = "/api/rsvp", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<String> insertRSVP(@RequestBody MultiValueMap<String, String> form) throws ParseException {
        String fullname = form.getFirst("fullname");
        String email = form.getFirst("email");
        String phone = form.getFirst("phone");
        String confirmationDate = form.getFirst("confirmationdate");
        String comments = form.getFirst("comments");

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        RSVP rsvp = new RSVP();
        rsvp.setFullName(fullname);
        rsvp.setEmail(email);
        rsvp.setPhone(phone);
        rsvp.setConfirmationDate(dateFormat.parse(confirmationDate));
        rsvp.setComments(comments);

        Integer rowsupdated = rsvpSvc.insertRSVP(rsvp);

        if (rowsupdated == 1) {
            return ResponseEntity.status(201).body("Created, Data insert Successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Unable to Insert the data");
        }

    }

    // Task2.4
    @PostMapping(value = "/api/rsvp/{email}")
    public ResponseEntity<String> updateRSVP(@PathVariable("email") String email,
            @RequestBody MultiValueMap<String, String> form) throws ParseException {
        String fullname = form.getFirst("fullname");
        String newemail = form.getFirst("email");
        String phone = form.getFirst("phone");
        String confirmationDate = form.getFirst("confirmationdate");
        String comments = form.getFirst("comments");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        RSVP rsvp = new RSVP();
        rsvp.setFullName(fullname);
        rsvp.setEmail(newemail);
        rsvp.setPhone(phone);
        rsvp.setConfirmationDate(dateFormat.parse(confirmationDate));
        rsvp.setComments(comments);
        Integer rowsupdated = rsvpSvc.updateRSVP(email, rsvp);
        if (rowsupdated == 1) {
            return ResponseEntity.status(201).body("Code 201 : Update Operation Successful");
        } else {
            return ResponseEntity.status(404).body("Error 404 ; Email Not found");
        }
    }

    // Task2.5
    @GetMapping("/api/rsvps/count")
    public ResponseEntity<String> countRSVP() {
        Integer totalfound = rsvpSvc.countRSVP();
        if (totalfound > 0) {
            return ResponseEntity.status(201).body("Total RSVP = " + totalfound);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No RSVP Found");
        }
    }

}
