package models;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * A locale code. ISO-639-1-language-code and ISO-3166-alpha-2-country-code.
 */
@Embeddable
@Data
public class Localization implements Serializable {

  private String locale;
  private String timezone;
}
