package models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@ToString
public class OrderProperties implements Serializable {

  private String salesPerson;
  
  private String salesPersonRef;

  private String operator;
  
  private String salesPersonDisplayName;
}
