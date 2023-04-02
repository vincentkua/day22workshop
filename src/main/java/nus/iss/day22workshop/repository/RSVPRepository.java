package nus.iss.day22workshop.repository;

import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import nus.iss.day22workshop.model.RSVP;

@Repository
public class RSVPRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String FINDALLSQL = "select * from rsvp";
    private static final String FINDBYNAME = "select * from rsvp where full_name like ? ";
    private static final String INSERTRSVP = "insert into rsvp (full_name,email,phone,confirmation_date,comments) values (? , ? , ? , ? , ?)";
    private static final String UPDATESQL = "update rsvp set email = ? , phone = ? , confirmation_date = ? , comments = ? where email = ? ";
    private static final String COUNTSQL = "select count(*)  from rsvp";

    public List<RSVP> findAllRSVP() {
        List<RSVP> rsvps = new LinkedList<>();
        rsvps = jdbcTemplate.query(FINDALLSQL, BeanPropertyRowMapper.newInstance(RSVP.class));
        return rsvps;
    }

    public RSVP findByName(String name) {
        RSVP rsvp = new RSVP();
        try {
            rsvp = jdbcTemplate.queryForObject(FINDBYNAME, BeanPropertyRowMapper.newInstance(RSVP.class),
                    "%" + name + "%");
        } catch (Exception e) {
            rsvp = null;
        }
        return rsvp;
    }

    public Integer insertRSVP(RSVP rsvp) {
        Integer rowsupdated = jdbcTemplate.update(INSERTRSVP, rsvp.getFullName(), rsvp.getEmail(), rsvp.getPhone(),
                rsvp.getConfirmationDate(), rsvp.getComments());
        return rowsupdated;
    }

    public Integer updateRSVP(String email, RSVP rsvp) {
        Integer rowsupdated = jdbcTemplate.update(UPDATESQL, rsvp.getEmail(), rsvp.getPhone(),
                rsvp.getConfirmationDate(), rsvp.getComments(), email);
        return rowsupdated;
    }

    public Integer countRSVP(){
        Integer totalfound = jdbcTemplate.queryForObject(COUNTSQL, Integer.class);
        return totalfound;
    }

}
