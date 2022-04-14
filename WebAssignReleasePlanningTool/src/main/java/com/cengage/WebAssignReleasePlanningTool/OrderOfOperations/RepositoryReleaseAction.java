package com.cengage.WebAssignReleasePlanningTool.OrderOfOperations;

import java.sql.Time;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
public class RepositoryReleaseAction extends ReleaseAction {

	@Transient
	Repository repository;
	@Transient
	RepositoryAction action;
	
	public RepositoryReleaseAction(final String _name, final Time _duration, final Repository _repository, final RepositoryAction _action)
	{
		super(_name, _duration);
		setRepository(_repository);
		setAction(_action);
	}
	
	public Repository getRepository()
	{
		return repository;
	}
	
	public void setRepository(final Repository _repository)
	{
		repository = _repository;
	}
	
	public RepositoryAction getAction()
	{
		return action;
	}
	
	public void setAction(final RepositoryAction _action)
	{
		action = _action;
	}
}
