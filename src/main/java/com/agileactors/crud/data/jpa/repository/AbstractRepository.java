package com.agileactors.crud.data.jpa.repository;

import com.agileactors.crud.domain.AbstractPersistable;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AbstractRepository<T extends AbstractPersistable<I>, I extends Serializable>
    extends JpaRepository<T, I> {

}
