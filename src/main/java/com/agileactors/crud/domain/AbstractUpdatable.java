package com.agileactors.crud.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;

/**
 * Abstract base class for updatable entities. Introduces an updatedAt which is always updated upon
 * enity storage
 *
 * @author Alexis Panousis
 * @param <I> the type of the auditing type's identifier.
 */
@MappedSuperclass
@Getter
@Setter
public abstract class AbstractUpdatable<I extends Serializable> extends AbstractPersistable<I> {

  @Column(name = "updated_at")
  protected LocalDateTime updatedAt;

  public void onPrePersist() {
    super.onPrePersist();
    updatedAt = createdAt;
  }

  @PreUpdate
  public void onPreUpdate() {
    //TODO: Inject clock instance
    updatedAt = LocalDateTime.now();
  }
}
