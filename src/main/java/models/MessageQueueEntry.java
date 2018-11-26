package models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Data
@Table(name = "message_queue")
public class MessageQueueEntry {

  /**
   * The message queue id.
   */
  @Id
  private String id;

  /**
   * The date-time when this row was created.
   */
  @Column(name = "created_at")
  private Date createdAt;

  /**
   * The OrderEventRequest bytes.
   */
  @Column(name = "ORDER_BYTES")
  private byte[] orderEventRequest;
}
