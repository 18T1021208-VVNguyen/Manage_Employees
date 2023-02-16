package repository.Irepository;

import java.util.List;

import model.BaseModel;

public interface  IBaseRepository <ID , T extends BaseModel>{
		
		public List<T> findAll(String className) throws Exception;
		
		public T create(T model) throws Exception;
		
		public boolean delete(ID id ,String className) throws Exception;
		
		public boolean update(T model) throws Exception;
		
		public T findByID(ID id , String className) throws Exception;
		
		public List<T> findByNameOrId(String name , String className) throws Exception;
		
		
}	
