package com.cengage.WebAssignReleasePlanningTool.OrderOfOperations;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "repositories")
public class Repository {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	@Transient
	private List<Repository> dependents;
	
	public Repository() {
		dependents = new ArrayList<Repository>();
	}
	
	public Repository(String _name)
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
