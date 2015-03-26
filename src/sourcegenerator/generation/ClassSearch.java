package sourcegenerator.generation;

public class ClassSearch {
	public String filePath(Entity entity){
		StringBuilder path = new StringBuilder();
		path.append(entity.getWEBPoject());
		path.append(entity.getSeparator());
		path.append("search");
		path.append(entity.getSeparator());
		path.append(entity.getClassName());
		path.append("Search.java");

		return path.toString();
	}

	public String sourceCode(Entity entity) {
		StringBuilder classBuilder = new StringBuilder();
		classBuilder.append("package ").append(entity.getBasePackage()).append(".search;");
		classBuilder.append(System.getProperty("line.separator"));
		classBuilder.append(System.getProperty("line.separator"));
		classBuilder.append("import br.com.bs.fw.enumeration.FilterType;");
		classBuilder.append(System.getProperty("line.separator"));
		classBuilder.append("import br.com.bs.fw.util.GenericSearch;");
		classBuilder.append(System.getProperty("line.separator"));
		classBuilder.append("import br.com.bs.fw.util.IGenericSearch;");
		classBuilder.append(System.getProperty("line.separator"));
		classBuilder.append("import ").append(entity.getBasePackage()).append(".entity.").append(entity.getClassName()).append(";");
		classBuilder.append(System.getProperty("line.separator"));
		classBuilder.append(System.getProperty("line.separator"));
		classBuilder.append("public class ").append(entity.getClassName()).append("Search extends GenericSearch<");
		classBuilder.append(entity.getClassName()).append("> IGenericSearch<");
		classBuilder.append(entity.getClassName()).append("> {");
		classBuilder.append(System.getProperty("line.separator"));
		classBuilder.append(System.getProperty("line.separator"));
		classBuilder.append("\tpublic ").append(entity.getClassName()).append("Search () {");
		classBuilder.append(System.getProperty("line.separator"));
		classBuilder.append("\t\taddSelectFilter(\"id\", \"Código\", FilterType.Long);");
		classBuilder.append(System.getProperty("line.separator"));
		classBuilder.append("\t}");
		classBuilder.append(System.getProperty("line.separator"));
		classBuilder.append("}");

		return classBuilder.toString();
	}


}