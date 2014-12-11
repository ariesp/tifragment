package ch.genidea.geniweb.base.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import ch.genidea.geniweb.base.domain.Role;

@Repository
public class RoleRepositoryImpl implements RoleRepository {

    @PersistenceContext
    EntityManager entityManager;

	@Override
	public void save(Role role) {
		entityManager.persist(role);	
	}
}
