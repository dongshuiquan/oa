package com.oa.view.action;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.oa.domain.ApplicationTemplate;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class ApplicationTemplateAction extends ModelDrivenBaseAction<ApplicationTemplate>{

	private static final long serialVersionUID = 1L;
	
	private InputStream inputStream; //下载用
	
	private File upload; //上传文件
	
	/**列表*/
	public String list() throws Exception{
		List<ApplicationTemplate> applicationTemplateList = applicationTemplateService.findAll();
		ActionContext.getContext().put("applicationTemplateList",applicationTemplateList);
		return "list";
	}
	/**删除, 应也删除文件*/
	public String delete() throws Exception{
		applicationTemplateService.deleteById(model.getId());
		return "toList";
	}
	/**添加页面*/
	public String addUI() throws Exception{
		//准备数据
		List<ProcessDefinition> processDefinitionList = processDefinitionService.findAllLastestVersions();
		ActionContext.getContext().put("processDefinitionList", processDefinitionList);
		return "saveUI";
	}
	/**添加*/
	public String add() throws Exception{
		//封装
		String path = saveUploadFile(upload);
		model.setPath(path);
		//保存
		applicationTemplateService.save(model);
		return "toList";
	}
	
	/**修改页面*/
	public String editUI() throws Exception{
		//准备数据
		List<ProcessDefinition> processDefinitionList = processDefinitionService.findAllLastestVersions();
		ActionContext.getContext().put("processDefinitionList", processDefinitionList);
		//准备回显数据
		ApplicationTemplate applicationTemplate  = applicationTemplateService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(applicationTemplate);
		return "saveUI";
	}
	/**修改*/
	public String edit() throws Exception{
		//1、 从数据库中取出原对象
		ApplicationTemplate applicationTemplate  = applicationTemplateService.getById(model.getId());
		
		//2、 设置要修改的属性
		applicationTemplate.setName(model.getName());
		applicationTemplate.setProcessDefinitonKey(model.getProcessDefinitionKey());
		if(upload != null){//如果上传了新文件
			//删除文件
			File file = new File(applicationTemplate.getPath());
			if(file.exists()){
				file.delete();
			}
			String path = saveUploadFile(upload);
			applicationTemplate.setPath(path);
		}
		//3、 更新到数据库
		applicationTemplateService.update(applicationTemplate);
		return "toList";
	}
	/**下载*/
	public String download() throws Exception{
		//准备下载的资源
		ApplicationTemplate applicationTemplate = applicationTemplateService.getById(model.getId());
		inputStream = new FileInputStream(applicationTemplate.getPath());
		//准备文件名(解决乱码问题)
		String fileName = applicationTemplate.getName();
		fileName = URLEncoder.encode(fileName, "utf-8");
		ActionContext.getContext().put("fileName", fileName);
		return "download";
	}
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	public File getUpload() {
		return upload;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	
}
