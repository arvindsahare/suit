package models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Entity
@Data
@ToString(exclude = "order")
@EqualsAndHashCode(exclude = "order")
@Table(indexes = @Index(columnList = "order_id"))
public class OrderItem extends Timestamped implements Serializable {

  @Id
  private String orderItemId;

  @NotNull
  private String itemReference;

  @Embedded
  private Product product;

  @Embedded
  private Amount listAmount;

  @Embedded
  @NotNull
  private Amount unitAmount;

  @Embedded
  private Amount costAmount;

  @Embedded
  @NotNull
  private Quantity quantity;

  @Embedded
  private Amount subTotal;

  @Embedded
  private Amount total;

  @OneToMany(mappedBy = "orderItem", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  private List<OrderItemDiscount> discounts = new ArrayList<OrderItemDiscount>();

  @OneToMany(mappedBy = "orderItem", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  private List<OrderItemTax> taxes = new ArrayList<OrderItemTax>();

  private List<KeyValue> orderItemMetaData;

  @Column(length = 4096)
  private String metaDataJson;

  @OneToMany(mappedBy = "orderItem", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  private List<ItemReturn> itemReturns = new ArrayList<ItemReturn>();

  @ManyToOne
  @JoinColumn(name = "order_id", nullable = false)
  private Order order;

  @Embedded
  private Appointment appointment;

  @Embedded
  private Exchange exchange;

  {
    orderItemId = UUID.randomUUID().toString();
  }

}
