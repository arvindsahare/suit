package models;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

@Data
@Embeddable
public class Facility {

  private String id;
  @Transient
  private String name;
}
