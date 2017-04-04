package com.oa.view.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.List;
import java.util.zip.ZipInputStream;

import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class ProcessDefinitionAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	
	private InputStream inputStream; //下载用
	private File upload; //上传用的文件 
	private String id; //
	private String key;

	/**列表*/
	public String list() throws Exception{
		List<ProcessDefinition> processDefinitionList = processDefinitionService.findAllLastestVersions();
		ActionContext.getContext().put("processDefinitionList", processDefinitionList);
		return "list";
	}
	/**删除*/
	public String delete() throws Exception{
		key = URLDecoder.decode(key, "utf-8");
		processDefinitionService.deleteByKey(key);
		return "toList";
	}
	/**部署页面*/
	public String addUI() throws Exception{
		return "addUI";
	}
	/**部署*/
	public String add() throws Exception{
		try(
			ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(upload));
			)
		{
		processDefinitionService.deploy(zipInputStream);
		} 
		return "toList";
	}
	/**查看流程图*/
	public String downloadProcessImage() throws Exception{
		inputStream = processDefinitionService.getResourceImageResourceAsStream(id);
		return "downloadProcessImage";
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
