package models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@ToString(exclude = "orderItem")
@EqualsAndHashCode(exclude = "orderItem")
@Table(indexes = @Index(columnList = "order_item_order_item_id"))
public class OrderItemDiscount extends Discount {

  @ManyToOne
  @JoinColumn(name = "order_item_order_item_id")
  private OrderItem orderItem;
}
