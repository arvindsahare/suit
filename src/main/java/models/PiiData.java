package models;

import lombok.Data;

import java.util.UUID;

/**
 * Bean to facilitate storing and retrieving protobuf messages containing PII data in Databunker.
 */
@Data
public class PiiData {

  /**
   * The Customer associated with an order.
   */
  private Customer customer;

  /**
   * The Databunker 'id' for the Customer.
   */
  private UUID customerId;

  /**
   * The Databunker 'hash' for the Customer.
   */
  private String customerHash;

  /**
   * The billingAddress associated with an order.
   */
  private Address billingAddress;

  /**
   * The Databunker 'id' for the billingAddress.
   */
  private UUID billingAddressId;

  /**
   * The Databunker 'hash' for the billingAddress.
   */
  private String billingAddressHash;

  /**
   * The shippingAddress associated with an order.
   */
  private Address shippingAddress;

  /**
   * The Databunker 'id' for the shippingAddress.
   */
  private UUID shippingAddressId;

  /**
   * The Databunker 'hash' for the shippigAddress.
   */
  private String shippingAddressHash;

  public PiiData(Customer customer, Address billingAddress, Address shippingAddress) {
    this.customer = customer;
    this.billingAddress = billingAddress;
    this.shippingAddress = shippingAddress;
  }

  public PiiData(UUID customerId, UUID billingAddressId, UUID shippingAddressId) {
    this.customerId = customerId;
    this.billingAddressId = billingAddressId;
    this.shippingAddressId = shippingAddressId;
  }

}

