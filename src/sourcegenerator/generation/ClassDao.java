package sourcegenerator.generation;

public class ClassDao {
	public String filePath(Entity entity){
		StringBuilder path = new StringBuilder();
		path.append(entity.getEJBPoject());
		path.append(entity.getSeparator());
		path.append("repository");
		path.append(entity.getSeparator());
		path.append(entity.getClassName());
		path.append("DAO.java");

		return path.toString();
	}

	public String sourceCode(Entity entity) {
		StringBuilder classBuilder = new StringBuilder();
		classBuilder.append("package ").append(entity.getBasePackage()).append(".repository;");
		classBuilder.append(System.getProperty("line.separator"));
		classBuilder.append(System.getProperty("line.separator"));
		classBuilder.append("import br.com.bs.fw.repository.impl.GenericDAO;");
		classBuilder.append(System.getProperty("line.separator"));
		classBuilder.append("import ").append(entity.getBasePackage()).append(".entity.").append(entity.getClassName()).append(";");
		classBuilder.append(System.getProperty("line.separator"));
		classBuilder.append(System.getProperty("line.separator"));
		classBuilder.append("public class ").append(entity.getClassName()).append("DAO extends GenericDAO<").append(entity.getClassName()).append("> {");
		classBuilder.append(System.getProperty("line.separator"));
		classBuilder.append(System.getProperty("line.separator"));
		classBuilder.append("}");

		return classBuilder.toString();
	}


}