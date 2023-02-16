package service;

import java.util.List;

import model.Department;
import repository.DepartmentRepository;
import service.Iservice.IDepartmentService;

public class DepartmentService implements IDepartmentService {

	public static DepartmentRepository departmentRepository;

	public static DepartmentRepository getInstanceDepartmentRepository() {
		if (DepartmentService.departmentRepository == null) {
			DepartmentService.departmentRepository = new DepartmentRepository();
		}

		return DepartmentService.departmentRepository;
	}

	public final static String CLASS_NAME = "Department";

	@Override
	public List<Department> findAll() throws Exception {
		return getInstanceDepartmentRepository().findAll(CLASS_NAME);
	}

	@Override
	public Department create(Department model) throws Exception {
		return getInstanceDepartmentRepository().create(model);
	}

	@Override
	public boolean delete(Department depart) throws Exception {
		boolean result =  getInstanceDepartmentRepository().delete(depart.getNo(), CLASS_NAME);
		TitleService.getInstanceTitleRepository().updateTitleWhenDeleDepartment(depart.getNo());
		return result;
	}

	@Override
	public boolean update(Department model) throws Exception {
		return getInstanceDepartmentRepository().update(model);
	}

	@Override
	public Department findByID(String id) throws Exception {
		return getInstanceDepartmentRepository().findByID(id, CLASS_NAME);
	}

	@Override
	public List<Department> findByNameOrId(String text) throws Exception {
		return getInstanceDepartmentRepository().findByNameOrId(text, CLASS_NAME);
	}

}
