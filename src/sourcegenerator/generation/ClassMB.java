package sourcegenerator.generation;


public class ClassMB {
	public String filePath(Entity entity){
		StringBuilder path = new StringBuilder();
		path.append(entity.getWEBPoject());
		path.append(entity.getSeparator());
		path.append("mb");
		path.append(entity.getSeparator());
		path.append("MB");
		path.append(entity.getClassName());
		path.append(".java");

		return path.toString();
	}

	public String sourceCode(Entity entity) {
		StringBuilder classBuilder = new StringBuilder();
		classBuilder.append("package ").append(entity.getBasePackage()).append(".mb;");
		classBuilder.append(System.getProperty("line.separator"));
		classBuilder.append(System.getProperty("line.separator"));
		classBuilder.append("import java.io.Serializable;");
		classBuilder.append(System.getProperty("line.separator"));
		classBuilder.append(System.getProperty("line.separator"));
		classBuilder.append("import javax.ejb.EJB;");
		classBuilder.append(System.getProperty("line.separator"));
		classBuilder.append("import javax.faces.bean.ManagedBean;");
		classBuilder.append(System.getProperty("line.separator"));
		classBuilder.append("import javax.faces.bean.ViewScoped;");
		classBuilder.append(System.getProperty("line.separator"));
		classBuilder.append(System.getProperty("line.separator"));
		classBuilder.append("import br.com.bs.fw.mb.MBGeneric;");
		classBuilder.append(System.getProperty("line.separator"));
		classBuilder.append("import ").append(entity.getBasePackage()).append(".business.iface.I").append(entity.getClassName()).append("BO;");
		classBuilder.append(System.getProperty("line.separator"));

		for (Field field : entity.getFields()) {
			if(field.getType().equals(ElementTypeEnum.OBJECT)){
				String basePackage = field.getPackageName().split(".entity.")[0];
				classBuilder.append("import ").append(basePackage).append(".business.iface.I").append(field.getNameCamel()).append("BO;");
				classBuilder.append(System.getProperty("line.separator"));
			}
		}

		classBuilder.append("import ").append(entity.getBasePackage()).append(".entity.").append(entity.getClassName()).append(";");
		classBuilder.append(System.getProperty("line.separator"));

		for (Field field : entity.getFields()) {
			if(field.getType().equals(ElementTypeEnum.OBJECT)){
				classBuilder.append("import ").append(field.getPackageName()).append(";");
				classBuilder.append(System.getProperty("line.separator"));
			}
		}

		for (Field field : entity.getFields()) {
			if(field.getType().equals(ElementTypeEnum.ENUM)){
				classBuilder.append("import ").append(field.getPackageName()).append(";");
				classBuilder.append(System.getProperty("line.separator"));
			}
		}

		classBuilder.append("import ").append(entity.getBasePackage()).append(".search.").append(entity.getClassName()).append("Search;");
		classBuilder.append(System.getProperty("line.separator"));
		classBuilder.append(System.getProperty("line.separator"));
		classBuilder.append("@ManagedBean(name=\"mb").append(entity.getClassName()).append("\")");
		classBuilder.append(System.getProperty("line.separator"));
		classBuilder.append("@ViewScoped");
		classBuilder.append(System.getProperty("line.separator"));
		classBuilder.append("public class MB").append(entity.getClassName()).append(" extends MBGeneric<");
		classBuilder.append(entity.getClassName()).append(", I");
		classBuilder.append(entity.getClassName()).append("BO> implements Serializable {");
		classBuilder.append(System.getProperty("line.separator"));
		classBuilder.append(System.getProperty("line.separator"));
		classBuilder.append("\tprivate static final long serialVersionUID = 1L;");
		classBuilder.append(System.getProperty("line.separator"));
		classBuilder.append(System.getProperty("line.separator"));
		classBuilder.append("\t@EJB");
		classBuilder.append(System.getProperty("line.separator"));
		classBuilder.append("\tprivate I").append(entity.getClassName()).append("BO ").append(entity.getObjectName()).append("BO;");
		classBuilder.append(System.getProperty("line.separator"));

		for (Field field : entity.getFields()) {
			if(field.getType().equals(ElementTypeEnum.OBJECT)){
				classBuilder.append(System.getProperty("line.separator"));
				classBuilder.append("\t@EJB");
				classBuilder.append(System.getProperty("line.separator"));
				classBuilder.append("\tprivate I").append(field.getNameCamel()).append("BO ").append(field.getName()).append("BO;");
				classBuilder.append(System.getProperty("line.separator"));
			}
		}

		classBuilder.append(System.getProperty("line.separator"));
		classBuilder.append("\tpublic void init() {");
		classBuilder.append(System.getProperty("line.separator"));
		classBuilder.append("\t\tthis.setSearch(new ").append(entity.getClassName()).append("Search());");
		classBuilder.append(System.getProperty("line.separator"));
		classBuilder.append("\t\tsetBo(").append(entity.getObjectName()).append("BO);");
		classBuilder.append(System.getProperty("line.separator"));
		classBuilder.append("\t}");

		for (Field field : entity.getFields()) {
			if(field.getType().equals(ElementTypeEnum.ENUM)){
				classBuilder.append(System.getProperty("line.separator"));
				classBuilder.append(System.getProperty("line.separator"));

				classBuilder.append("\tpublic List<SelectItem> findAll").append(field.getNameCamel()).append("() {").append(System.getProperty("line.separator"));
				classBuilder.append("\t\treturn JSFUtils.enumSelectItens(false, ").append(field.getNameCamel()).append(".values());").append(System.getProperty("line.separator"));
				classBuilder.append("\t}");
			}

			if(field.getType().equals(ElementTypeEnum.OBJECT)){

				classBuilder.append(System.getProperty("line.separator"));
				classBuilder.append(System.getProperty("line.separator"));

				classBuilder.append("\tpublic List<SelectItem> findAll").append(field.getNameCamel()).append("() {").append(System.getProperty("line.separator"));
				classBuilder.append("\t\tList<").append(field.getNameCamel()).append("> listResult = ").append(field.getName()).append("BO.findAllReduce();").append(System.getProperty("line.separator"));
				classBuilder.append("\t\treturn JSFUtils.getSelectItens(resultList);").append(System.getProperty("line.separator"));
				classBuilder.append("\t}");
			}
		}

		classBuilder.append(System.getProperty("line.separator"));
		classBuilder.append("}");


        /*public List<SelectItem> findAllReduce(){
                List<Estado> listResult = estadoBO.findAllReduce();
                return JSFUtils.getSelectItens(listResult);
        }
*/

		return classBuilder.toString();
	}


}