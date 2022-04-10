package com.cengage.WebAssignReleasePlanningTool.repositories;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;

import com.cengage.WebAssignReleasePlanningTool.OrderOfOperations.Repository;

public class RepositoryRepositoryImpl  implements RepositoryRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;
    
	@Override
	public List<Repository> findAll() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Repository> query = cb.createQuery(Repository.class);
		Root<Repository> repository = query.from(Repository.class);
		
		query.select(repository);
		
		List<Tuple> relations = new ArrayList<Tuple>();
		
		
		List<Repository> reps =  entityManager.createQuery(query).getResultList();
		
		for(Repository r : reps)
		{
			String qString = "SELECT childId FROM repositorydependencies where parentId =" + r.getId();
			javax.persistence.Query q = entityManager.createNativeQuery(qString);
			
			List<Object> res = q.getResultList();
			
			for(Object o : res)
			{
				r.addDependent(reps.get((int) o)); //assumes index = id
			}
		}
		return reps;
	}
}
