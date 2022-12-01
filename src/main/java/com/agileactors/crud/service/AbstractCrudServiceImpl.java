package com.agileactors.crud.service;

import com.agileactors.crud.dao.AbstractDao;
import com.agileactors.crud.domain.AbstractPersistable;
import com.agileactors.crud.dto.AbstractCreateResourceRequestDto;
import com.agileactors.crud.dto.AbstractUpdateRequestResourceDto;
import com.agileactors.crud.exception.DomainResourceNotFoundException;
import com.agileactors.crud.exception.MappingNotFoundException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.MappedSuperclass;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Default implementation of the {@link AbstractCrudService} interface.
 *
 * @param <I> the type of the entity type's identifier.
 * @param <T> the persisted type.
 * @param <D> the dao used for any database operation
 * @author Alexis Panousis
 */
@MappedSuperclass
public abstract class AbstractCrudServiceImpl<
    T extends AbstractPersistable<I>,
    D extends AbstractDao<T, I>,
    I extends Serializable> implements AbstractCrudService<T, I> {

  protected final D dao;

  @Autowired
  private MappingService mappingService;

  public AbstractCrudServiceImpl(D dao) {
    this.dao = dao;
  }

  /*
   * (non-Javadoc)
   * @see AbstractCrudService#findAll()
   */
  @Override
  public List<T> findAll() {
    return dao.findAll();
  }

  /*
   * (non-Javadoc)
   * @see AbstractCrudService#getById(java.io.Serializable)
   */
  @Override
  public T getById(I id) throws DomainResourceNotFoundException {
    return dao.findById(id)
        .orElseThrow(() -> new DomainResourceNotFoundException("Resource not found", id));
  }

  /*
   * (non-Javadoc)
   * @see AbstractCrudService#deleteById(java.io.Serializable)
   */
  @Override
  public void deleteById(I id) {
    dao.deleteById(id);
  }

  /*
   * (non-Javadoc)
   * @see AbstractCrudService#create(java.lang.Object)
   */
  @Override
  public <C extends AbstractCreateResourceRequestDto> T create(C createDto)
      throws MappingNotFoundException {
    T newEntity = (T) mappingService.convert(createDto, MappingType.DOMAIN);
    return dao.save(newEntity);
  }

  /*
   * (non-Javadoc)
   * @see AbstractCrudService#update(java.lang.Object)
   */
  @Override
  public <U extends AbstractUpdateRequestResourceDto<I>> T update(U updateDto)
      throws MappingNotFoundException, DomainResourceNotFoundException {
    T existingEntity = getById(updateDto.getId());

    T newEntity = (T) mappingService.convert(updateDto, MappingType.DOMAIN);

    newEntity.setCreatedAt(existingEntity.getCreatedAt());

    return dao.save(newEntity);
  }
}
