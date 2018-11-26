package models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@ToString(exclude = "paymentMethod")
@EqualsAndHashCode(exclude = "paymentMethod")
public class PaymentMethodMetaData extends Timestamped {

  @Id
  private String paymentMethodMetaDataId;
  private String key;
  @Column(length = 4096)
  private String value;
  @ManyToOne
  @JoinColumn(name = "payment_method_id", nullable = false)
  private PaymentMethod paymentMethod;

  {
    paymentMethodMetaDataId = UUID.randomUUID().toString();
  }
}
