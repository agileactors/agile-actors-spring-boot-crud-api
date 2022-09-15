package com.agileactors.crud.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Placeholder class. All update DTOs will have to extend this class.
 *
 * @param <I> the entity identifier type
 * @author Alexis Panousis
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public abstract class AbstractUpdateRequestResourceDto<I extends Serializable> {
  private I id;
}
