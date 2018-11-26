package models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
public abstract class Timestamped {

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "created_at", nullable = false)
  private Date created;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "updated_at", nullable = false)
  private Date updated;


  protected void onCreate() {
    this.created = new Date();
    this.updated = this.created;
  }


  protected void onUpdate() {
    this.updated = new Date();
  }
}
