package models;

import io.polarisdev.domain.partner.enums.EntityStatus;
import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.Transient;
import java.io.Serializable;

@Embeddable
@Data
public class Retailer implements Serializable {

  private String retailerRef;

  private String name;

  private String phone;

  @Transient
  private String returnExchangeUrl;

  @Transient
  private String faqUrl;

  @Transient
  private String logoUrl;

  @Transient
  private String receiptHeaderLogoUrl;

  @Transient
  private String alternateLogoUrl;

  @Transient
  private String fbGenericLogoUrl;

  @Transient
  private String customerServiceName;

  @Transient
  private String customerServiceUrl;

  @Transient
  private String customerServiceEmail;

  @Transient
  private String chatUrl;

  @Transient
  private String websiteUrl;

  @Transient
  private String returnPolicyBasics;

  @Transient
  private String returnPolicyDetails;

  @Transient
  private EntityStatus status;

  @Transient
  private Returns returns;

  @Transient
  private String directionsUrl;

  @Transient
  private String mapUrl;

  @Transient
  private String directoryUrl;

  @Transient
  private String promotionsUrl;

  @Transient
  private String giftWrapUrl;

  @Transient
  private String giftWrapFeedbackUrl;

  @Transient
  private String trackDonationUrl;

  @Transient
  private String santaBookingUrl;

  @Transient
  private String santaModifyBookingUrl;

  @Transient
  private String santaFeedbackUrl;
  
  @Transient
  private String drawerBody;

  @Transient
  private String drawerButtonValue;

  @Transient
  private String widgetBody;

  @Transient
  private String widgetHeader;

  @Transient
  private String confirmationHeader;

  @Transient
  private String confirmationBody;
}
