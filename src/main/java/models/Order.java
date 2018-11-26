package models;

import io.polarisdev.domain.order.enums.OrderStatus;
import io.polarisdev.domain.order.enums.OrderType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name = "`order`",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"orderRef", "retailerId"})}, indexes = {
    @Index(columnList = "partyId")})
public class Order extends Timestamped implements Serializable {

  @Id
  @Setter(AccessLevel.NONE)
  private String orderId;

  private String orderUrn;

  @NotNull
  private String orderRef;

  private String orderNumber;

  @NotNull
  private String retailerId;

  @NotNull
  private String retailerConsumerId;

  private String partyId;

  private String confirmationNumber;

  @Transient
  private Address billingAddress;

  @Transient
  private Customer customer;

  @Transient
  private Address shippingAddress;

  @NotNull
  private Date orderDate;

  @NotNull
  private OrderStatus status;

  private Boolean gift;

  private Boolean receiptSent = Boolean.FALSE;

  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  private List<PaymentMethod> paymentMethods = new ArrayList<PaymentMethod>();

  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  private List<OrderDiscount> discounts = new ArrayList<OrderDiscount>();
  
  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  private List<OrderUpdateReason> updateReasons = new ArrayList<OrderUpdateReason>();

  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  private List<OrderTax> taxes = new ArrayList<OrderTax>();

  private List<OrderItem> orderItems = new ArrayList<OrderItem>();

  @Embedded
  @NotNull
  private Amount subTotal;

  @Embedded
  private Amount cashReturned;

  @Embedded
  private Amount gratuity;

  @Embedded
  @NotNull
  private Amount total;


  @Embedded
  private Amount shippingAndHandling;

  private String shippingAndHandlingService;

  private Date expectedDeliveryDateFrom;

  private Date expectedDeliveryDateTo;

  @NotNull
  private OrderType orderType;

  @Embedded
  private Retailer retailer;

  @Embedded
  private OrderProperties properties;

  @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
  @JoinColumns(value = {@JoinColumn(name = "store_store_ref", referencedColumnName = "store_ref"),
      @JoinColumn(name = "store_retailer_id", referencedColumnName = "retailer_id")})
  private Store store;

  @Embedded
  @NotNull
  private Localization localization;

  /**
   * The Databunker-assigned ID for the 'customer' message associated with the order. Not a part of
   * the protobuf class
   */
  private String customerDatabunkerId;

  /**
   * The Databunker-assigned hash-value for the 'customer' message associated with the order. Not a
   * part of the protobuf class
   */
  private String customerDatabunkerHash;

  /**
   * The Databunker-assigned ID for the 'billing_address' message associated with the order. Not a
   * part of the protobuf class
   */
  private String billingAddressDatabunkerId;

  /**
   * The Databunker-assigned hash-value for the 'billing_address' message associated with the Not a
   * part of the protobuf class order.
   */
  private String billingAddressDatabunkerHash;

  /**
   * The Databunker-assigned ID for the 'shipping_address' message associated with the order. Not a
   * part of the protobuf class
   */
  private String shippingAddressDatabunkerId;

  /**
   * The Databunker-assigned hash-value for the 'shipping_address' message associated with the Not a
   * part of the protobuf class order.
   */
  private String shippingAddressDatabunkerHash;

  @Embedded
  private Venue venue;

  @Embedded
  private Facility facility;

  {
    if (StringUtils.isEmpty(this.orderId)) {
      this.orderId = UUID.randomUUID().toString();
    }
  }

  public Order(String orderId) {
    this.orderId = orderId;
  }
}
