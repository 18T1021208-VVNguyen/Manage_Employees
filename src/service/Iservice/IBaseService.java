package service.Iservice;

import java.util.List;

import model.BaseModel;

public interface IBaseService <ID , T extends BaseModel> {
	
	public List<T> findAll() throws Exception;
	
	public T create(T model) throws Exception;
	
	public boolean delete(T model ) throws Exception;
	
	public boolean update(T model) throws Exception;
	
	public T findByID(ID id ) throws Exception;
	
	public List<T> findByNameOrId(String text ) throws Exception;
	
}
