package models;

import io.polarisdev.domain.common.enums.PaymentMethodType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.UUID;

@Data
@ToString(exclude = "order")
@EqualsAndHashCode(exclude = "order")
public class PaymentMethod extends Timestamped {

  @Id
  private String paymentMethodId;
  private String method;
  private PaymentMethodType type;
  @Embedded
  private Amount amount;
  @Embedded
  private Card card;

  private Order order;

  {
    paymentMethodId = UUID.randomUUID().toString();
  }
}
