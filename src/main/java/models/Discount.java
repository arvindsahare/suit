package models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Embedded;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
@ToString
public class Discount {

  @Id
  private String discountId;

  @Embedded
  @NotNull
  private Amount amount;

  private String code;

  {
    discountId = UUID.randomUUID().toString();
  }
}
