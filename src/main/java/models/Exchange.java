package models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
@ToString
public class Exchange implements Serializable {

  /**
   * Default serial ID
   */
  private static final long serialVersionUID = 1L;

  // Order reference of original order for which exchange is initiated.
  private String orderRef;

  // Order Item reference of original order item for which exchange is initiated.
  // Optional
  private String orderItemRef;
}
