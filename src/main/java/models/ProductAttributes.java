package models;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class ProductAttributes implements Serializable {

  private String color;
  private Double depth;
  private Double weight;
  private Quantity width;
  private Quantity height;
  private Quantity length;
}
