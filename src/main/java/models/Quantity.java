package models;

import io.polarisdev.common.proto.QuantityProto;
import io.polarisdev.domain.common.converter.UnitsTypeConverter;
import io.polarisdev.domain.common.enums.UnitsType;
import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class Quantity implements Serializable {


  private Float quantity;

  private UnitsType units;
}
