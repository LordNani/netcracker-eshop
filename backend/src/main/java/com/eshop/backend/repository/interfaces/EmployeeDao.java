package com.eshop.backend.repository.interfaces;

import com.eshop.backend.model.EmployeeModel;
import com.eshop.backend.repository.generic.CrudDao;

public interface EmployeeDao extends CrudDao<EmployeeModel> {
    EmployeeModel getByRole (String status);
    EmployeeModel getByStatus (String status);
    EmployeeModel getById (Long id);
}
