package com.oa.service;

import java.util.List;

import com.oa.domain.ApplicationTemplate;

public interface ApplicationTemplateService {

	List<ApplicationTemplate> findAll();

	void deleteById(Long id);

	void save(ApplicationTemplate model);

	ApplicationTemplate getById(Long id);

	void update(ApplicationTemplate model);

}
