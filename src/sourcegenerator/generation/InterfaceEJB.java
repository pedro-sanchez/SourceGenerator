package sourcegenerator.generation;

public class InterfaceEJB {
	public String filePath(Entity entity){
		StringBuilder path = new StringBuilder();
		path.append(entity.getEJBPoject());
		path.append(entity.getSeparator());
		path.append("business");
		path.append(entity.getSeparator());
		path.append("iface");
		path.append(entity.getSeparator());
		path.append("I");
		path.append(entity.getClassName());
		path.append("BO.java");

		return path.toString();
	}

	public String sourceCode(Entity entity) {
		StringBuilder classBuilder = new StringBuilder();
		classBuilder.append("package ").append(entity.getBasePackage()).append(".business.iface;");
		classBuilder.append(System.getProperty("line.separator"));
		classBuilder.append(System.getProperty("line.separator"));
		classBuilder.append("import br.com.bs.fw.business.iface.IGenericBO;");
		classBuilder.append(System.getProperty("line.separator"));
		classBuilder.append("import ").append(entity.getBasePackage()).append(".entity.").append(entity.getClassName()).append(";");
		classBuilder.append(System.getProperty("line.separator"));
		classBuilder.append(System.getProperty("line.separator"));
		classBuilder.append("public interface I").append(entity.getClassName()).append("BO extends IGenericBO<").append(entity.getClassName()).append("> {");
		classBuilder.append(System.getProperty("line.separator"));
		classBuilder.append(System.getProperty("line.separator"));
		classBuilder.append("}");

		return classBuilder.toString();
	}


}