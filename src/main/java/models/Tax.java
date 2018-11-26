package models;

import io.polarisdev.domain.common.enums.TaxType;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@MappedSuperclass
@Data
public class Tax {

  @Id
  private String taxId;
  private TaxType taxType;
  private Amount amount;
  private Float taxRate;

  {
    taxId = UUID.randomUUID().toString();
  }
}
