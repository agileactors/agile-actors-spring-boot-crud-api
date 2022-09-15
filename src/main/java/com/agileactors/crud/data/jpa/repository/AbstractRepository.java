package com.agileactors.crud.data.jpa.repository;

import com.agileactors.crud.domain.AbstractPersistable;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Abstract repository that extends {@link JpaRepository}. Any entity extending {@link AbstractPersistable}
 * has to extend this repository to enable the usage of the framework from repo to api.
 *
 * @author Alexis Panousis
 * @param <I> the type of the auditing type's identifier.
 * @param <T> the persisted type.
 */
@NoRepositoryBean
public interface AbstractRepository<T extends AbstractPersistable<I>, I extends Serializable>
    extends JpaRepository<T, I> {

}
