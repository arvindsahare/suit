package models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@ToString(exclude = "order")
@EqualsAndHashCode(exclude = "order")
@Table(indexes = @Index(columnList = "order_order_id"))
public class OrderDiscount extends Discount {

  @ManyToOne
  @JoinColumn(name = "order_order_id")
  private Order order;
}
