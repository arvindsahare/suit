package models;

import io.polarisdev.domain.partner.enums.ReturnsVendor;
import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.Transient;
import java.io.Serializable;

@Embeddable
@Data
public class Returns implements Serializable {

  @Transient
  ReturnsVendor returnsVendor;
}