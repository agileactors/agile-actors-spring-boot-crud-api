package com.agileactors.crud.api;

import com.agileactors.crud.domain.AbstractPersistable;
import com.agileactors.crud.dto.AbstractCreateResourceRequestDto;
import com.agileactors.crud.dto.AbstractGetResourceResponseDto;
import com.agileactors.crud.dto.AbstractUpdateRequestResourceDto;
import com.agileactors.crud.exception.DomainResourceNotFoundException;
import com.agileactors.crud.exception.MappingNotFoundException;
import com.agileactors.crud.service.AbstractCrudService;
import com.agileactors.crud.service.MappingService;
import com.agileactors.crud.service.MappingType;
import java.io.Serializable;
import java.util.stream.Stream;
import javax.persistence.MappedSuperclass;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Default implementation of CRUD api
 *
 * @param <I> the type of the entity type's identifier.
 * @param <T> the persisted type.
 * @param <S> the type of the service that exposes the crud operations of the given <T> type
 * @param <C> the type of the DTO used while creating an entity
 * @param <U> the type of the DTO used while updating an entity
 * @param <R> the type of the DTO used while retrieving an entity
 */
@MappedSuperclass
public abstract class AbstractCrudApi<
    T extends AbstractPersistable<I>,
    I extends Serializable,
    S extends AbstractCrudService<T, I>,
    C extends AbstractCreateResourceRequestDto,
    U extends AbstractUpdateRequestResourceDto<I>,
    R extends AbstractGetResourceResponseDto<I>
    > {

  @Autowired
  private S service;

  @Autowired
  private MappingService mappingService;

  /**
   * Returns all instances of the type.
   *
   * @return all entities or empty list when no entity is found
   */
  @GetMapping
  // TODO: Paging
  public Stream<Object> findAll() {
    return service.findAll()
        .parallelStream()
        .map(it -> {
          try {
            return mappingService.convert(it.getClass(), MappingType.API_RESPONSE_GET);
          } catch (MappingNotFoundException e) {
            // TODO: fix this shit
            e.printStackTrace();
            return null;
          }
        });
  }

  /**
   * Retrieves an entity by its id.
   *
   * @param id must not be {@literal null}.
   * @return the entity with the given id
   * @throws DomainResourceNotFoundException if entity is not found
   */
  @GetMapping("/{id}")
  public R getById(@PathVariable I id)
      throws DomainResourceNotFoundException, MappingNotFoundException {
    T entity = service.getById(id);
    return (R) mappingService.convert(entity, MappingType.API_RESPONSE_GET);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable I id) {
    service.deleteById(id);
    return ResponseEntity.noContent().build();
  }

  @PostMapping
  public R create(@Valid @RequestBody C createDto) throws MappingNotFoundException {
    T newEntity = service.create(createDto);
    return (R) mappingService.convert(newEntity, MappingType.API_RESPONSE_CREATE);
  }

  @PutMapping("/{id}")
  public R update(@PathVariable I id, @Valid @RequestBody U updateDto)
      throws MappingNotFoundException, DomainResourceNotFoundException {

    updateDto.setId(id);

    T newEntity = service.update(updateDto);

    return (R) mappingService.convert(newEntity, MappingType.API_RESPONSE_UPDATE);
  }

}
