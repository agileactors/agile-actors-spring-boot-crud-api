package com.agileactors.crud.service;

import com.agileactors.crud.domain.AbstractPersistable;
import com.agileactors.crud.dto.AbstractCreateResourceRequestDto;
import com.agileactors.crud.dto.AbstractUpdateRequestResourceDto;
import com.agileactors.crud.exception.DomainResourceNotFoundException;
import com.agileactors.crud.exception.MappingNotFoundException;
import java.io.Serializable;
import java.util.List;

public interface AbstractCrudService<T extends AbstractPersistable<I>, I extends Serializable> {
  List<T> findAll();

  T getById(I id) throws DomainResourceNotFoundException;

  void deleteById(I id);

  <C extends AbstractCreateResourceRequestDto> T create(C createDto)
      throws MappingNotFoundException;

  <U extends AbstractUpdateRequestResourceDto<I>> T update(U updateDto)
      throws MappingNotFoundException, DomainResourceNotFoundException;
}
