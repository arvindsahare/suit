package models;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Data
@ToString(exclude = "orderItem")
public class ItemReturn implements Serializable {

  @Id
  private String itemReturnId;

  private OrderItem orderItem;

  private String reason;

  private Date timestamp = new Date();

  private Store returnStore;

  private Quantity quantity;

  {
    itemReturnId = UUID.randomUUID().toString();
  }
}
