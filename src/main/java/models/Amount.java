package models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

@Embeddable
@Getter
@Setter
@ToString
public class Amount implements Serializable {

  private Currency currency;
  private BigDecimal quantity;

  public static Amount getDefaultInstance() {
    Amount amount = new Amount();
    amount.setCurrency(Currency.getInstance(Locale.US));
    amount.setQuantity(BigDecimal.ZERO);
    return amount;

  }
}
