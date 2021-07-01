package com.eshop.backend.product.dao.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilterModel {
    private Long author[];
    private Long coverType[];
    private Long genre[];
    private Long language[];
    private Long publisher[];
}
