package sourcegenerator.generation;

public class ClassMessage {
	public String filePath(Entity entity){
		StringBuilder path = new StringBuilder();
		path.append(entity.getWEBMessages());
		path.append(entity.getSeparator());
		path.append("messages.properties");

		return path.toString();
	}

	public String sourceCode(Entity entity) {
		StringBuilder classBuilder = new StringBuilder();
		classBuilder.append(System.getProperty("line.separator"));
		classBuilder.append(entity.getObjectName()).append(".title=").append(System.getProperty("line.separator"));

		for (Field field : entity.getFields()) {
			classBuilder.append(entity.getObjectName()).append(".list.").append(field.getName()).append("=").append(System.getProperty("line.separator"));
		}

		for (Field field : entity.getFields()) {
			classBuilder.append(entity.getObjectName()).append(".edit.").append(field.getName()).append("=").append(System.getProperty("line.separator"));
		}

		return classBuilder.toString();
	}


}