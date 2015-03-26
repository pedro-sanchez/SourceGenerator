package sourcegenerator.generation;

import java.util.List;

public class Entity {

	private String separator = "/";

	private String projectName;

	private String company;

	private String system;

	private String basePath;

	private String basePackage;

	private String className;

	private String objectName;

	private List<Field> fields;

	private Boolean DAO = Boolean.TRUE;

	private Boolean IEJB = Boolean.TRUE;

	private Boolean EJB = Boolean.TRUE;

	private Boolean WRAPPER = Boolean.TRUE;

	private Boolean MB = Boolean.TRUE;

	private Boolean MESSAGE = Boolean.TRUE;

	private Boolean LIST_XHTML = Boolean.TRUE;

	private Boolean EDIT_XHTML = Boolean.TRUE;

	public String getSeparator() {
		return separator;
	}

	public void setSeparator(String separator) {
		this.separator = separator;
	}

	public void setSO(String so){
		if(so.equals("windows")){
			setSeparator("\\");
		}
		else{
			setSeparator("/");
		}
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

	public String getBasePath() {
		return basePath;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	public String getBasePackage() {
		return basePackage;
	}

	public void setBasePackage(String basePackage) {
		this.basePackage = basePackage;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;

		if(this.className != null){
			setObjectName(this.className.substring(0, 1).toLowerCase()+ this.className.substring(1));
		}
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public List<Field> getFields() {
		return fields;
	}

	public void setFields(List<Field> fields) {
		this.fields = fields;
	}

	public Boolean getDAO() {
		return DAO;
	}

	public void setDAO(Boolean dAO) {
		DAO = dAO;
	}

	public Boolean getIEJB() {
		return IEJB;
	}

	public void setIEJB(Boolean iEJB) {
		IEJB = iEJB;
	}

	public Boolean getEJB() {
		return EJB;
	}

	public void setEJB(Boolean eJB) {
		EJB = eJB;
	}

	public Boolean getWRAPPER() {
		return WRAPPER;
	}

	public void setWRAPPER(Boolean wRAPPER) {
		WRAPPER = wRAPPER;
	}

	public Boolean getMB() {
		return MB;
	}

	public void setMB(Boolean mB) {
		MB = mB;
	}

	public Boolean getMESSAGE() {
		return MESSAGE;
	}

	public void setMESSAGE(Boolean mESSAGE) {
		MESSAGE = mESSAGE;
	}

	public Boolean getLIST_XHTML() {
		return LIST_XHTML;
	}

	public void setLIST_XHTML(Boolean lIST_XHTML) {
		LIST_XHTML = lIST_XHTML;
	}

	public Boolean getEDIT_XHTML() {
		return EDIT_XHTML;
	}

	public void setEDIT_XHTML(Boolean eDIT_XHTML) {
		EDIT_XHTML = eDIT_XHTML;
	}

	public String getEJBPoject(){
		StringBuilder path = new StringBuilder();
		path.append(this.getBasePath());
		path.append(this.getSeparator());
		path.append(this.getProjectName());
		path.append(this.getSeparator());

		path.append(this.getProjectName()).append("-ejb");
		path.append(this.getSeparator());

		path.append("src");
		path.append(this.getSeparator());
		path.append("main");
		path.append(this.getSeparator());
		path.append("java");
		path.append(this.getSeparator());
		path.append("br");
		path.append(this.getSeparator());
		path.append("com");
		path.append(this.getSeparator());
		path.append(this.company);
		path.append(this.getSeparator());
		path.append(this.system);

		return path.toString();
	}

	public String getWEBPoject(){
		StringBuilder path = new StringBuilder();
		path.append(this.getBasePath());
		path.append(this.getSeparator());
		path.append(this.getProjectName());
		path.append(this.getSeparator());

		path.append(this.getProjectName()).append("-web");
		path.append(this.getSeparator());

		path.append("src");
		path.append(this.getSeparator());
		path.append("main");
		path.append(this.getSeparator());
		path.append("java");
		path.append(this.getSeparator());
		path.append("br");
		path.append(this.getSeparator());
		path.append("com");
		path.append(this.getSeparator());
		path.append(this.company);
		path.append(this.getSeparator());
		path.append(this.system);

		return path.toString();
	}


	public String getWEBMessages(){

		StringBuilder path = new StringBuilder();
		path.append(this.getBasePath());
		path.append(this.getSeparator());
		path.append(this.getProjectName());
		path.append(this.getSeparator());

		path.append(this.getProjectName()).append("-web");
		path.append(this.getSeparator());

		path.append("src");
		path.append(this.getSeparator());
		path.append("main");
		path.append(this.getSeparator());
		path.append("resources");
		path.append(this.getSeparator());
		path.append("i18n");

		return path.toString();
	}


	public String getWEBPages(){

		StringBuilder path = new StringBuilder();
		path.append(this.getBasePath());
		path.append(this.getSeparator());
		path.append(this.getProjectName());
		path.append(this.getSeparator());

		path.append(this.getProjectName()).append("-web");
		path.append(this.getSeparator());

		path.append("src");
		path.append(this.getSeparator());
		path.append("main");
		path.append(this.getSeparator());
		path.append("webapp");
		path.append(this.getSeparator());
		path.append("paginas");

		return path.toString();
	}


}
