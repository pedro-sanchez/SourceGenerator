package sourcegenerator.generation;

import org.eclipse.jdt.core.IAnnotation;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IField;
import org.eclipse.jdt.core.IImportDeclaration;
import org.eclipse.jdt.core.JavaModelException;

public class Field {

	private ElementTypeEnum type;

	private String name;

	private String packageName;

	private String nameCamel;

	private Boolean annotationId = Boolean.FALSE;

	private Boolean annotationName = Boolean.FALSE;

	private Boolean required = Boolean.FALSE;

	private Boolean hasAnnotation(IField iField, String annotationName){
		IAnnotation[] annotations;
		try {
			annotations = iField.getAnnotations();
			for (IAnnotation iAnnotation : annotations) {
				if(iAnnotation.getElementName().equals(annotationName)){
					return Boolean.TRUE;
				}
			}
		} catch (JavaModelException e) {
			e.printStackTrace();
		}
		return Boolean.FALSE;

	}

	private Boolean hasId(IField iField){
		return hasAnnotation(iField, "Id");
	}

	private Boolean hasName(IField iField){
		return hasAnnotation(iField, "Name");
	}

	private Boolean isRequired(IField iField){
		return hasAnnotation(iField, "NotNull");
	}

	public Field(IField iField) {

        try {
			String typeSignature = iField.getTypeSignature();

			if(typeSignature.contains("Long") || typeSignature.contains("Integer")){
				type= ElementTypeEnum.NUMBER;
			}
			else if(typeSignature.contains("String")){
				type= ElementTypeEnum.STRING;
			}
			else if(typeSignature.contains("Date")){
				type= ElementTypeEnum.DATE;
			}
			else{
				type= ElementTypeEnum.OBJECT;
				typeSignature = typeSignature.substring(1).replace(";", "");
				IImportDeclaration[] imports = ((ICompilationUnit)iField.getParent().getParent()).getImports();
				for (IImportDeclaration iImportDeclaration : imports) {
					if(iImportDeclaration.getElementName().endsWith( typeSignature)){
						packageName = iImportDeclaration.getElementName();
						if (iImportDeclaration.getElementName().replace(("."+typeSignature), "").endsWith("enumeration")) {
							type= ElementTypeEnum.ENUM;
						}
						break;
					}
				}
			}

			annotationId = hasId(iField);
			annotationName = hasName(iField);
			required = isRequired(iField);

			name = iField.getElementName();

			nameCamel = (this.name.substring(0, 1).toUpperCase()+ this.name.substring(1));
		} catch (JavaModelException e) {
		}
	}

	public ElementTypeEnum getType() {
		return type;
	}

	public void setType(ElementTypeEnum type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getAnnotationId() {
		return annotationId;
	}

	public void setAnnotationId(Boolean annotationId) {
		this.annotationId = annotationId;
	}

	public Boolean getAnnotationName() {
		return annotationName;
	}

	public void setAnnotationName(Boolean annotationName) {
		this.annotationName = annotationName;
	}

	public String getNameCamel() {
		return nameCamel;
	}

	public void setNameCamel(String nameCamel) {
		this.nameCamel = nameCamel;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public Boolean getRequired() {
		return required;
	}

	public void setRequired(Boolean required) {
		this.required = required;
	}

}
