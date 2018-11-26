package models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@ToString(exclude = "order")
@EqualsAndHashCode(exclude = "order")
@Table(indexes = @Index(columnList = "order_order_id"))
public class OrderUpdateReason extends Timestamped{
  @ManyToOne
  @JoinColumn(name = "order_order_id")
  private Order order;
  @Id
  private String updateReasonId;
  @Column(length = 512)
  private String reason;
  {
    updateReasonId = UUID.randomUUID().toString();
  }
}