package models;

import io.polarisdev.domain.common.enums.ProductGender;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Embeddable
@Getter
@Setter
public class Product implements Serializable {

  @Embedded
  private ProductIdentifier productIdentifier;
  private String sku;
  private String masterSku;
  private String description;
  private Hyperlink url;
  private String name;
  private Hyperlink imageUrl;
  @Transient
  private List<String> brands = new ArrayList<String>();
  private String brandsJson;
  @Transient
  private List<String> categories = new ArrayList<String>();
  private String categoriesJson;
  private Hyperlink logoUrl;
  private String manufacturer;
  private String model;
  @Embedded
  @Column(name = "product_attributes")
  private ProductAttributes productAttributes;
  private String size;
  private String color;
  private ProductGender gender;
  @Embedded
  private Quantity weight;
  private String harmonizedSystemCode;
  private String manufacturerPartNumber;
}
