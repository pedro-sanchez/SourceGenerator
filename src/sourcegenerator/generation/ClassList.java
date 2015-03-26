package sourcegenerator.generation;

public class ClassList {
	public String filePath(Entity entity){
		StringBuilder path = new StringBuilder();
		path.append(entity.getWEBPages());
		path.append(entity.getSeparator());
		path.append(entity.getObjectName());
		path.append(entity.getSeparator());
		path.append(entity.getObjectName());
		path.append("List.xhtml");

		return path.toString();
	}

	public String sourceCode(Entity entity) {
		StringBuilder classBuilder = new StringBuilder();

		classBuilder.append("<?xml version='1.0' encoding='UTF-8' ?>").append(System.getProperty("line.separator"));
		classBuilder.append("<ui:composition xmlns:ui=\"http://xmlns.jcp.org/jsf/facelets\"").append(System.getProperty("line.separator"));
		classBuilder.append("	xmlns:h=\"http://xmlns.jcp.org/jsf/html\"").append(System.getProperty("line.separator"));
		classBuilder.append("	xmlns:f=\"http://xmlns.jcp.org/jsf/core\"").append(System.getProperty("line.separator"));
		classBuilder.append("	xmlns:pt=\"http://xmlns.jcp.org/jsf/passthrough\"").append(System.getProperty("line.separator"));
		classBuilder.append("	xmlns:tags=\"http://tags.com/facelets\">").append(System.getProperty("line.separator"));

		classBuilder.append(System.getProperty("line.separator"));

		classBuilder.append("	<tags:listWindow title=\"").append(entity.getObjectName()).append(".title\" mb=\"#{mb").append(entity.getClassName()).append("}\" editWindow=\"paginas/").append(entity.getObjectName()).append("/").append(entity.getObjectName()).append("Edit.xhtml\">").append(System.getProperty("line.separator"));;
		classBuilder.append("		<ui:define name=\"columns\">").append(System.getProperty("line.separator"));

		classBuilder.append(buildColumn(entity));

		classBuilder.append("		</ui:define>").append(System.getProperty("line.separator"));
		classBuilder.append("	</tags:listWindow>").append(System.getProperty("line.separator"));
		classBuilder.append("</ui:composition>").append(System.getProperty("line.separator"));

//column
		/*classBuilder.append("<tags:column label=\"estado.list.id\"");
		classBuilder.append("titleHeader=\"estado.list.id.tooltip\"");
		classBuilder.append("titleRow=\"estado.list.id.tooltip\" ");
		classBuilder.append("sortField=\"nome\"");
		classBuilder.append("value=\"#{entity.id}\" />");*/


		return classBuilder.toString();
	}

	private StringBuilder buildColumn(Entity entity){
		StringBuilder columnBuilder = new StringBuilder();

		for (Field field : entity.getFields()) {

			columnBuilder.append(System.getProperty("line.separator"));
			columnBuilder.append("			<tags:column label=\"").append(entity.getObjectName()).append(".list.").append(field.getName()).append("\"").append(System.getProperty("line.separator"));
			columnBuilder.append("						 titleHeader=\"").append(entity.getObjectName()).append(".list.").append(field.getName()).append(".tooltip\"").append(System.getProperty("line.separator"));
			columnBuilder.append("						 titleRow=\"").append(entity.getObjectName()).append(".list.").append(field.getName()).append(".tooltip\" ").append(System.getProperty("line.separator"));
			//columnBuilder.append("sortField=\"nome\"");
			columnBuilder.append("						 value=\"#{entity.").append(field.getName()).append("}\" />").append(System.getProperty("line.separator"));

		}

		return columnBuilder;
	}


}