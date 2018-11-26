package models;

import io.polarisdev.domain.common.enums.Gender;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class Customer {

  private String title;

  private String email;

  private String firstName;

  private String middleName;

  private String lastName;

  private String taxId;

  private Address address;

  private List<String> phoneNumbers = new ArrayList<String>();

  private Gender gender;

}
