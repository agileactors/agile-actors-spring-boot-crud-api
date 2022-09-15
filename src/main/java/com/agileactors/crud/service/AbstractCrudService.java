package com.agileactors.crud.service;

import com.agileactors.crud.domain.AbstractPersistable;
import com.agileactors.crud.dto.AbstractCreateResourceRequestDto;
import com.agileactors.crud.dto.AbstractUpdateRequestResourceDto;
import com.agileactors.crud.exception.DomainResourceNotFoundException;
import com.agileactors.crud.exception.MappingNotFoundException;
import java.io.Serializable;
import java.util.List;

/**
 * Service interface for generic CRUD operations for a specific type.
 *
 * @param <I> the type of the entity type's identifier.
 * @param <T> the persisted type.
 * @author Alexis Panousis
 */
public interface AbstractCrudService<T extends AbstractPersistable<I>, I extends Serializable> {

  /**
   * Returns all instances of the type.
   *
   * @return all entities or empty list when no entity is found
   */
  List<T> findAll();


  /**
   * Retrieves an entity by its id.
   *
   * @param id must not be {@literal null}.
   * @return the entity with the given id
   * @throws DomainResourceNotFoundException if entity is not found
   */
  T getById(I id) throws DomainResourceNotFoundException;

  /**
   * Deletes the entity with the given id.
   *
   * @param id must not be {@literal null}.
   */
  void deleteById(I id);

  /**
   * Performs all necessary actions to create an entity. The input class must extend
   * {@link AbstractCreateResourceRequestDto}.
   *
   * @param <C> the D(ata)T(ransfer)O(bject) that holds all the required fields
   * @return the persisted entity
   * @throws MappingNotFoundException see {@link MappingService#convert(Object, MappingType)}
   *                                  to understand when this exception is thrown
   */
  <C extends AbstractCreateResourceRequestDto> T create(C createDto)
      throws MappingNotFoundException;

  /**
   * Performs all necessary actions to create an entity. The input class must extend
   * {@link AbstractUpdateRequestResourceDto}.
   *
   * @param <U> the D(ata)T(ransfer)O(bject) that holds all the required fields
   * @return the updated entity
   * @throws MappingNotFoundException        see {@link MappingService#convert(Object, MappingType)}
   *                                         to understand when this exception is thrown
   * @throws DomainResourceNotFoundException when the entity to update is not found
   */
  <U extends AbstractUpdateRequestResourceDto<I>> T update(U updateDto)
      throws MappingNotFoundException, DomainResourceNotFoundException;
}
