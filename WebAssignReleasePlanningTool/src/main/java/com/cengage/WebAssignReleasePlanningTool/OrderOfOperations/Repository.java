package com.cengage.WebAssignReleasePlanningTool.OrderOfOperations;

import java.util.ArrayList;
import java.util.List;

public class Repository {

	private String name;
	private List<Repository> dependents;
	
	Repository(String _name)
	{
		setName(_name);
		dependents = new ArrayList<Repository>();
	}
	
	public void setName(String _name)
	{
		name = _name;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void addDependent(Repository repository)
	{
		dependents.add(repository);
	}
	
	public List<Repository> getDependents()
	{
		return dependents;
	}
	
	public void removeDependent(Repository repository)
	{
		dependents.remove(repository);
	}
}
