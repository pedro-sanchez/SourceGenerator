package sourcegenerator.generation;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IField;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;

import sourcegenerator.util.Utils;

public class BuildEntity {

	public static List<Field> getFields(ICompilationUnit firstElement){
		List<Field> fields = new ArrayList<>();
		try {
			for (IType type : firstElement.getTypes()) {
			    for (IField iField : type.getFields()) {
			    	fields.add(new Field(iField));
			    }
			}
		} catch (JavaModelException e) {
			e.printStackTrace();
		}

		return fields;
	}


	public static Entity build(ICompilationUnit firstElement){
		List<Field> fields = getFields(firstElement);

		String packageName = firstElement.getParent().getElementName();

		Entity entity = new Entity();
		entity.setFields(fields);
		entity.setCompany(Utils.getCompanyName(packageName));
		entity.setSystem(Utils.getProjectName(packageName));
		entity.setClassName(firstElement.getElementName().replace(".java", ""));

		entity.setBasePackage(Utils.getStartName(packageName)+entity.getCompany()+"."+entity.getSystem());

		if (Utils.isWindows()) {
			entity.setSeparator("\\");
		}
		IResource resource = firstElement.getResource();

		String absoluteSystemPath = resource.getLocationURI().getPath();

		String splitPoint = entity.getSeparator() +"src"+ entity.getSeparator()+Utils.getStartName(packageName).replace(".", entity.getSeparator());

		String projectPath = absoluteSystemPath.split(splitPoint)[0];

		String[] split = projectPath.split(entity.getSeparator());

		String subProjectName = split[(split.length-1)];

		String projectName = split[(split.length-2)];

		if(subProjectName.startsWith(projectName)){
			String splitPointProject = entity.getSeparator() + projectName + entity.getSeparator() + subProjectName;
			projectPath = projectPath.split(splitPointProject)[0];
		}
		else{
			String splitPointProject = entity.getSeparator() + subProjectName;
			projectPath = projectPath.split(splitPointProject)[0];
			projectName = subProjectName;
		}

		entity.setBasePath(projectPath);
		entity.setProjectName(projectName);

		return entity;
	}


}
