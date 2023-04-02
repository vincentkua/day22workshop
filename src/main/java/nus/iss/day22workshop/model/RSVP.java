package nus.iss.day22workshop.model;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RSVP {
    private Integer id;
    private String fullName;
    private String email;
    private String phone;
    private Date confirmationDate;
    private String comments;    
}
