package com.oa.service.impl;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oa.dao.ApplicationTemplateDao;
import com.oa.domain.ApplicationTemplate;
import com.oa.service.ApplicationTemplateService;

@Service
@Transactional
public class ApplicationTemplateServiceImpl implements ApplicationTemplateService{
	
	@Autowired
	private ApplicationTemplateDao applicationTemplateDao;

	@Override
	public List<ApplicationTemplate> findAll() {
		return applicationTemplateDao.findAll();
	}

	@Override
	public void deleteById(Long id) {
		//删除数据库记录
		ApplicationTemplate applicationTemplate = applicationTemplateDao.getById(id);
		applicationTemplateDao.delete(id);
		//删除文件
		File file = new File(applicationTemplate.getPath());
		if(file.exists()){
			file.delete();
		}
	
	}

	@Override
	public void save(ApplicationTemplate model) {
		applicationTemplateDao.save(model);
	}

	@Override
	public ApplicationTemplate getById(Long id) {
		return applicationTemplateDao.getById(id);
	}

	@Override
	public void update(ApplicationTemplate model) {
		applicationTemplateDao.update(model);
	}
}
