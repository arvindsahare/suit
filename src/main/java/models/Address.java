package models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Embeddable;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Embeddable
@ToString
public class Address implements Serializable {

  @Transient
  private List<String> streetLines = new ArrayList<String>();

  private String addressLine1;

  private String addressLine2;

  private String addressLine3;

  private String addressLine4;

  private String locality;

  private String region;

  private String postalCode;

  private String country;
}


