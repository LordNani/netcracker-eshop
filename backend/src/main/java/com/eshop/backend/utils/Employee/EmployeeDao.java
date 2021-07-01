package com.eshop.backend.utils.Employee;

import com.eshop.backend.DAO.Models.Employee;
import com.eshop.backend.utils.CrudDao;

public interface EmployeeDao extends CrudDao<Employee> {
    Employee getByRole (String status);
    Employee getByStatus (String status);
    Employee getById (Long id);
}
