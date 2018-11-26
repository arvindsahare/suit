package models;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Date;

@Data
@Embeddable
@ToString
public class Appointment implements Serializable {
  private Date appointmentFrom;

  private Date appointmentTo;

  private String locationName;

  private Address appointmentLocation;

  private String appointmentName;

  private String description;

  private String qrcodeData;
}
