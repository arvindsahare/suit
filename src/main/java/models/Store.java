package models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
public class Store {

  private String storeRef;

  private String retailerId;

  private String name;

  @Embedded
  private Address address;

  private String phone;

  @Data
  public static class StoreId implements Serializable {

    private String storeRef;

    private String retailerId;
  }
}
