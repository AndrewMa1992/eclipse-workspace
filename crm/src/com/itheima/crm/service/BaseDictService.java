package com.itheima.crm.service;

import java.util.List;

import com.itheima.crm.domain.BaseDict;

/**
 * 字典的业务层的接口
 * @author jt
 *
 */
public interface BaseDictService {

	List<BaseDict> findByTypeCode(String dict_type_code);

}
