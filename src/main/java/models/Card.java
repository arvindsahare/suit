package models;

import io.polarisdev.domain.common.enums.CardType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Embeddable;

/**
 * Credit card details.
 */
@Embeddable
@Getter
@Setter
@ToString
public class Card {

  private String purchaseOrderNumber;
  private String lastFour;
  private CardType cardType;
  private String issuer;
  private String start;
  private String end;
  private String authCode;
  private String cardEntryMode;
  private String authorizationMode;
  private String cardholderVerificationMethod;
  private String applicationLabel;
  private String applicationId;
  private String terminalVerificationResults;
  private String issuerApplicationData;
  private String transactionStatusInformation;
  private String authorizationResponseCode;
  private String paymentNetworkToken;
}
