package com.agileactors.crud.dao;

import com.agileactors.crud.data.jpa.repository.AbstractRepository;
import com.agileactors.crud.domain.AbstractPersistable;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.NoRepositoryBean;


/**
 * Default implementation of the {@link AbstractDao} interface.
 *
 * @author Alexis Panousis
 */
@NoRepositoryBean
@AllArgsConstructor
public abstract class AbstractDaoImpl<
    T extends AbstractPersistable<I>,
    I extends Serializable,
    R extends AbstractRepository<T, I>> implements AbstractDao<T, I> {

  protected R jpaRepository;

  /*
   * (non-Javadoc)
   * @see AbstractDao#findAll()
   */
  @Override
  public List<T> findAll() {
    return jpaRepository.findAll();
  }

  /*
   * (non-Javadoc)
   * @see AbstractDao#findAllById(java.lang.Iterable)
   */
  @Override
  public List<T> findAllById(Iterable<I> ids) {
    return jpaRepository.findAllById(ids);
  }

  /*
   * (non-Javadoc)
   * @see AbstractDao#findById(java.io.Serializable)
   */
  @Override
  public Optional<T> findById(I id) {
    return jpaRepository.findById(id);
  }

  /*
   * (non-Javadoc)
   * @see AbstractDao#getById(java.io.Serializable)
   */
  @Override
  public T getById(I id) {
    return jpaRepository.getReferenceById(id);
  }

  /*
   * (non-Javadoc)
   * @see AbstractDao#save(java.lang.Object)
   */
  @Override
  public T save(T entity) {
    return jpaRepository.save(entity);
  }

  /*
   * (non-Javadoc)
   * @see AbstractDao#saveAndFlush(java.lang.Object)
   */
  @Override
  public T saveAndFlush(T entity) {
    return jpaRepository.saveAndFlush(entity);
  }

  /*
   * (non-Javadoc)
   * @see AbstractDao#saveAll(java.lang.Iterable)
   */
  @Override
  public List<T> saveAll(List<T> entities) {
    return jpaRepository.saveAll(entities);
  }

  /*
   * (non-Javadoc)
   * @see AbstractDao#deleteById(java.io.Serializable)
   */
  @Override
  public void deleteById(I id) {
    jpaRepository.deleteById(id);
  }

  /*
   * (non-Javadoc)
   * @see AbstractDao#delete(java.lang.Object)
   */
  @Override
  public void delete(T entity) {
    jpaRepository.delete(entity);
  }

}
