package models;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class ProductIdentifier implements Serializable {

  private String gtin8;
  private String gtin12;
  private String gtin13;
  private String gtin14;
}
