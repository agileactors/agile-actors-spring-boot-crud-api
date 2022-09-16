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
import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
  private ConversionService conversionService;

  @Autowired
  private MappingService mappingService;

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
