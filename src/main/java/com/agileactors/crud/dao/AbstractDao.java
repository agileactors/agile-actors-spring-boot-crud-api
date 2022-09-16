package com.agileactors.crud.dao;

import com.agileactors.crud.domain.AbstractPersistable;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * Abstract dao that exposes the repository methods to the service layer. The existence of this
 * layer will enable the de-coupling of the service layer with the repository technology.
 *
 * @param <I> the type of the entity type's identifier.
 * @param <T> the persisted type.
 * @author Alexis Panousis
 */
public interface AbstractDao<T extends AbstractPersistable<I>, I extends Serializable> {

  /**
   * Retrieves an entity by its id.
   *
   * @param id must not be {@literal null}.
   * @return the entity with the given id or {@literal Optional#empty()} if none found.
   * @throws IllegalArgumentException if {@literal id} is {@literal null}.
   */
  Optional<T> findById(I id);

  /**
   * Returns all instances of the type.
   *
   * @return all entities
   */
  List<T> findAll();

  /**
   * Returns all instances of the type {@code T} with the given IDs.
   *
   * <p>If some or all ids are not found, no entities are returned for these IDs.
   *
   * <p>Note that the order of elements in the result is not guaranteed.
   *
   * @param ids must not be {@literal null} nor contain any {@literal null} values.
   * @return guaranteed to be not {@literal null}. The size can be equal or less than the number of
   *     {@literal ids}.
   * @throws IllegalArgumentException in case the given {@link Iterable ids} or one of its items is
   *                                  {@literal null}.
   */
  List<T> findAllById(Iterable<I> ids);

  /**
   * Returns a reference to the entity with the given identifier. Depending on how the JPA
   * persistence provider is implemented this is very likely to always return an instance and throw
   * a {@link javax.persistence.EntityNotFoundException} on first access. Some of them will reject
   * invalid identifiers immediately.
   *
   * @param id must not be {@literal null}.
   * @return a reference to the entity with the given identifier.
   */
  T getById(I id);

  /**
   * Saves a given entity. Use the returned instance for further operations as the save operation
   * might have changed the entity instance completely.
   *
   * @param entity must not be {@literal null}.
   * @return the saved entity; will never be {@literal null}.
   * @throws IllegalArgumentException in case the given {@literal entity} is {@literal null}.
   */
  T save(T entity);

  /**
   * Saves an entity and flushes changes instantly.
   *
   * @param entity entity to be saved. Must not be {@literal null}.
   * @return the saved entity
   */
  T saveAndFlush(T entity);

  /**
   * Saves all given entities.
   *
   * @param entities must not be {@literal null} nor must it contain {@literal null}.
   * @return the saved entities; will never be {@literal null}. The returned {@literal Iterable}
   *     will have the same size as the {@literal Iterable} passed as an argument.
   * @throws IllegalArgumentException in case the given {@link Iterable entities} or one of its
   *                                  entities is {@literal null}.
   */
  List<T> saveAll(List<T> entities);

  /**
   * Deletes the entity with the given id.
   *
   * @param id must not be {@literal null}.
   * @throws IllegalArgumentException in case the given {@literal id} is {@literal null}
   */
  void deleteById(I id);

  /**
   * Deletes a given entity.
   *
   * @param entity must not be {@literal null}.
   * @throws IllegalArgumentException in case the given entity is {@literal null}.
   */
  void delete(T entity);
}
