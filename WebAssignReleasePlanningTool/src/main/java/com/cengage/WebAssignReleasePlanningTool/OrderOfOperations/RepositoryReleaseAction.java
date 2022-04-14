package com.cengage.WebAssignReleasePlanningTool.OrderOfOperations;

import java.sql.Time;
import java.util.List;

public class RepositoryReleaseAction extends ReleaseAction {

	Repository repository;
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
