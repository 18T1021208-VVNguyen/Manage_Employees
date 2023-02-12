package repository;

import java.util.List;

public interface  IBaseRepository <ID , T>{
		public List<T> findAll();
		
		public T create(T model);
		
		public boolean delete(ID id);
		
		public boolean update(T model);
		
		public T findByID(ID id);
		
		
}	
