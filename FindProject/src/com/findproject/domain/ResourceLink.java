package com.findproject.domain;

public class ResourceLink {
	private String _id; // ��Դ����id
	private ProjectBean project; // ��Դ����������Ŀ(trouble_flag=link_flag)
	private String link_flag; // ��Դ��ţ���Ŀ�ĵڼ������ӣ�
	private String link_description; // ��Դ�����ı�
	private String resourcelink;// ��Դ����
	// private Trouble trouble; //��Դ������������

	public ResourceLink() {
		super();
		// TODO Auto-generated constructor stub
	}
	//��flag
	public ResourceLink(String _id, ProjectBean project, String link_flag,
			String link_description, String resourcelink) {
		super();
		this._id = _id;
		this.project = project;
		this.link_flag = link_flag;
		this.link_description = link_description;
		this.resourcelink = resourcelink;
	}
	//û��flag
	public ResourceLink(String _id, ProjectBean project,
			String link_description, String resourcelink) {
		super();
		this._id = _id;
		this.project = project;
		this.link_description = link_description;
		this.resourcelink = resourcelink;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public ProjectBean getProject() {
		return project;
	}

	public void setProject(ProjectBean project) {
		this.project = project;
	}

	public String getLink_flag() {
		return link_flag;
	}

	public void setLink_flag(String link_flag) {
		this.link_flag = link_flag;
	}

	public String getLink_description() {
		return link_description;
	}

	public void setLink_description(String link_description) {
		this.link_description = link_description;
	}

	public String getResourcelink() {
		return resourcelink;
	}

	public void setResourcelink(String resourcelink) {
		this.resourcelink = resourcelink;
	}

}
