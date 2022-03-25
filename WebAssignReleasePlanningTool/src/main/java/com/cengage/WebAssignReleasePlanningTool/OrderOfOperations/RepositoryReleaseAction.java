package com.cengage.WebAssignReleasePlanningTool.OrderOfOperations;

//TODO: make this inherit ReleaseAction when it is added
public class RepositoryReleaseAction {

	Repository repository;
	RepositoryAction action;
	
	public RepositoryReleaseAction(Repository _repository, RepositoryAction _action)
	{
		setRepository(_repository);
		setAction(_action);
	}
	
	public Repository getRepository()
	{
		return repository;
	}
	
	public void setRepository(Repository _repository)
	{
		repository = _repository;
	}
	
	public RepositoryAction getAction()
	{
		return action;
	}
	
	public void setAction(RepositoryAction _action)
	{
		action = _action;
	}
}
