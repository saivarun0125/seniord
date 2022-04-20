package com.cengage.WebAssignReleasePlanningTool.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.data.repository.query.Param;

import com.cengage.WebAssignReleasePlanningTool.OrderOfOperations.Release;
import com.cengage.WebAssignReleasePlanningTool.OrderOfOperations.ReleaseAction;
import com.cengage.WebAssignReleasePlanningTool.OrderOfOperations.Repository;
import com.cengage.WebAssignReleasePlanningTool.OrderOfOperations.RepositoryReleaseAction;

public class ReleaseRepositoryImpl implements ReleaseRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;
    
    @Override
    public List<Release> findAll ()
    {
    	CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Release> query = cb.createQuery(Release.class);
		Root<Release> release = query.from(Release.class);
		
		query.select(release);		
		
		List<Release> rels =  entityManager.createQuery(query).getResultList();
		
		for(Release r : rels)
		{
			//get release actions
			String qString = "select 0 as dtype, name, id, releaseId, rollback, isSelected, notes, duration from releaseaction where releaseId = " 
								+ r.getId() + " and rollback = 0";
			Query q = entityManager.createNativeQuery(qString, ReleaseAction.class);
			
			List<ReleaseAction> res = q.getResultList();
			
			for(ReleaseAction ra : res)
			{
				r.getReleaseActions().add(ra);
			}
			
			//repeat for rollback
			qString = "select 0 as dtype, name, id, releaseId, rollback, isSelected, notes, duration from releaseaction where releaseId = " 
						+ r.getId() + " and rollback = 1";
			q = entityManager.createNativeQuery(qString, ReleaseAction.class);
			
			res = q.getResultList();
			
			for(ReleaseAction ra : res)
			{
				r.getRollbackActions().add(ra);
			}
		}
		
		return rels;
    }
    
    @Override
	public List<Release> findById(int id)
	{  	
    	CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    	String qString = "select id, name from releases where id = " + id;
    	Query query = entityManager.createNativeQuery(qString, Release.class);
		List<Release> rels = query.getResultList();
		
		for(Release r : rels)
		{
			//get release actions
			qString = "select 0 as dtype, name, id, releaseId, rollback, isSelected, notes, duration from releaseaction where releaseId = " 
								+ r.getId() + " and rollback = 0";
			Query q = entityManager.createNativeQuery(qString, ReleaseAction.class);
			
			List<ReleaseAction> res = q.getResultList();
			
			for(ReleaseAction ra : res)
			{
				r.getReleaseActions().add(ra);
			}
			
			//repeat for rollback
			qString = "select 0 as dtype, name, id, releaseId, rollback, isSelected, notes, duration from releaseaction where releaseId = " 
						+ r.getId() + " and rollback = 1";
			q = entityManager.createNativeQuery(qString, ReleaseAction.class);
			
			res = q.getResultList();
			
			for(ReleaseAction ra : res)
			{
				r.getRollbackActions().add(ra);
			}
		}
		
		return rels;
	}
}
