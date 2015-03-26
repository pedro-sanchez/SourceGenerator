package sourcegenerator.generation;

public class ClassEdit {
	public String filePath(Entity entity){
		StringBuilder path = new StringBuilder();
		path.append(entity.getWEBPages());
		path.append(entity.getSeparator());
		path.append(entity.getObjectName());
		path.append(entity.getSeparator());
		path.append(entity.getObjectName());
		path.append("Edit.xhtml");

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

		classBuilder.append("	<tags:editWindow title=\"").append(entity.getObjectName()).append(".title\" mb=\"#{mb").append(entity.getClassName()).append("}\">").append(System.getProperty("line.separator"));
		classBuilder.append("		<ui:define name=\"content\">").append(System.getProperty("line.separator"));

		classBuilder.append(buildField(entity));

        classBuilder.append("		</ui:define>").append(System.getProperty("line.separator"));
		classBuilder.append("	</tags:editWindow>").append(System.getProperty("line.separator"));

		classBuilder.append("</ui:composition>");

		return classBuilder.toString();
	}


	private StringBuilder buildField(Entity entity){
		StringBuilder fieldBuilder = new StringBuilder();

		for (Field field : entity.getFields()) {

			fieldBuilder.append(System.getProperty("line.separator"));

			if(field.getType().equals(ElementTypeEnum.NUMBER)){
				fieldBuilder.append("			<tags:inputText id=\"txt").append(field.getNameCamel()).append("\"").append(System.getProperty("line.separator"));
				fieldBuilder.append("					label=\"").append(entity.getObjectName()).append(".edit.").append(field.getName()).append("\"").append(System.getProperty("line.separator"));
				fieldBuilder.append("        			value=\"#{mb").append(entity.getClassName()).append(".entity.").append(field.getName()).append("}\"").append(System.getProperty("line.separator"));
				if(field.getAnnotationId()){
					fieldBuilder.append("			        disabled=\"true\"").append(System.getProperty("line.separator"));
					fieldBuilder.append("			        autofocus=\"false\"").append(System.getProperty("line.separator"));
					fieldBuilder.append("					rendered=\"#{mb").append(entity.getClassName()).append(".mode eq 'EDIT'}\"").append(System.getProperty("line.separator"));
				}

				if(field.getRequired()){
					fieldBuilder.append("			        required=\"true\"").append(System.getProperty("line.separator"));
				}
				fieldBuilder.append("			/>").append(System.getProperty("line.separator"));
			}
			else if(field.getType().equals(ElementTypeEnum.STRING)){
				fieldBuilder.append("			<tags:inputText id=\"txt").append(field.getNameCamel()).append("\"").append(System.getProperty("line.separator"));
				fieldBuilder.append("					label=\"").append(entity.getObjectName()).append(".edit.").append(field.getName()).append("\"").append(System.getProperty("line.separator"));
				fieldBuilder.append("			        value=\"#{mb").append(entity.getClassName()).append(".entity.").append(field.getName()).append("}\"").append(System.getProperty("line.separator"));

				if(field.getRequired()){
					fieldBuilder.append("			        required=\"true\"").append(System.getProperty("line.separator"));
				}
				fieldBuilder.append("			/>").append(System.getProperty("line.separator"));
			}
			else if(field.getType().equals(ElementTypeEnum.DATE)){
				fieldBuilder.append("			<tags:inputDate id=\"dt").append(field.getNameCamel()).append("\"").append(System.getProperty("line.separator"));
				fieldBuilder.append("					label=\"").append(entity.getObjectName()).append(".edit.").append(field.getName()).append("\"").append(System.getProperty("line.separator"));
				fieldBuilder.append("			        value=\"#{mb").append(entity.getClassName()).append(".entity.").append(field.getName()).append("}\"").append(System.getProperty("line.separator"));

				if(field.getRequired()){
					fieldBuilder.append("			        required=\"true\"").append(System.getProperty("line.separator"));
				}
				fieldBuilder.append("			/>").append(System.getProperty("line.separator"));
			}
			else if(field.getType().equals(ElementTypeEnum.OBJECT) || field.getType().equals(ElementTypeEnum.ENUM)){
				fieldBuilder.append("			<tags:selectOneMenu id=\"cmb").append(field.getNameCamel()).append("\"").append(System.getProperty("line.separator"));
				fieldBuilder.append("					label=\"").append(entity.getObjectName()).append(".edit.").append(field.getName()).append("\"").append(System.getProperty("line.separator"));
				fieldBuilder.append("			        value=\"#{mb").append(entity.getClassName()).append(".entity.").append(field.getName()).append("}\"").append(System.getProperty("line.separator"));
				fieldBuilder.append("			        itens=\"#{mb").append(entity.getClassName()).append(".findAll").append(field.getNameCamel()).append("()}\"").append(System.getProperty("line.separator"));

				if(field.getRequired()){
					fieldBuilder.append("			        required=\"true\"").append(System.getProperty("line.separator"));
				}
				fieldBuilder.append("			/>").append(System.getProperty("line.separator"));

			}

		}

		return fieldBuilder;
	}





}