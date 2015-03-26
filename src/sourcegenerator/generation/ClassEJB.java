package sourcegenerator.generation;

public class ClassEJB {
	public String filePath(Entity entity){
		StringBuilder path = new StringBuilder();
		path.append(entity.getEJBPoject());
		path.append(entity.getSeparator());
		path.append("business");
		path.append(entity.getSeparator());
		path.append("impl");
		path.append(entity.getSeparator());
		path.append(entity.getClassName());
		path.append("BO.java");

		return path.toString();
	}

	public String sourceCode(Entity entity) {
		StringBuilder classBuilder = new StringBuilder();
		classBuilder.append("package ").append(entity.getBasePackage()).append(".business.impl;");
		classBuilder.append(System.getProperty("line.separator"));
		classBuilder.append(System.getProperty("line.separator"));
		classBuilder.append("import javax.ejb.Local;");
		classBuilder.append(System.getProperty("line.separator"));
		classBuilder.append("import javax.ejb.Stateless;");
		classBuilder.append(System.getProperty("line.separator"));
		classBuilder.append(System.getProperty("line.separator"));
		classBuilder.append("import br.com.bs.fw.business.impl.GenericBO;");
		classBuilder.append(System.getProperty("line.separator"));
		classBuilder.append("import ").append(entity.getBasePackage()).append(".business.iface.I").append(entity.getClassName()).append("BO;");
		classBuilder.append(System.getProperty("line.separator"));
		classBuilder.append("import ").append(entity.getBasePackage()).append(".entity.").append(entity.getClassName()).append(";");
		classBuilder.append(System.getProperty("line.separator"));
		classBuilder.append("import ").append(entity.getBasePackage()).append(".repository.").append(entity.getClassName()).append("DAO;");
		classBuilder.append(System.getProperty("line.separator"));
		classBuilder.append(System.getProperty("line.separator"));
		classBuilder.append("@Local");
		classBuilder.append(System.getProperty("line.separator"));
		classBuilder.append("@Stateless");
		classBuilder.append(System.getProperty("line.separator"));

		classBuilder.append("public class ").append(entity.getClassName()).append("BO extends GenericBO<");
		classBuilder.append(entity.getClassName()).append(", ");
		classBuilder.append(entity.getClassName()).append("DAO> implements I");
		classBuilder.append(entity.getClassName()).append("BO {");
		classBuilder.append(System.getProperty("line.separator"));
		classBuilder.append(System.getProperty("line.separator"));
		classBuilder.append("}");

		return classBuilder.toString();
	}


}