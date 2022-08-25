package com.agileactors.crud.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class AbstractPersistable<I extends Serializable> implements Serializable {

  @Column(name = "created_at", updatable = false, nullable = false)
  protected LocalDateTime createdAt;

  @Transient
  private I id;

  @PrePersist
  public void onPrePersist() {
    //TODO: Inject clock instance

    if (createdAt == null) {
      createdAt = LocalDateTime.now();
    }
  }

}