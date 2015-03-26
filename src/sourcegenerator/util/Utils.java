package sourcegenerator.util;

public class Utils {

	private static String OS = System.getProperty("os.name").toLowerCase();

	public static boolean isWindows() {
		return (OS.indexOf("win") >= 0);
 	}

	public static String getStartName(String packageName){
		String startName = "com.";
		if(packageName.startsWith("br.com.")){
			startName = "br.com.";
		}
		return startName;
	}

	public static String getCompanyName(String packageName){
		String startName = getStartName(packageName);
		String newPack = packageName.replace(startName, "");
		return newPack.split("\\.")[0];
	}

	public static String getProjectName(String packageName){
		String company= getCompanyName(packageName);
		String startName = getStartName(packageName)+company+".";
		String newPack = packageName.replace(startName, "");
		return newPack.split("\\.")[0];
	}
}
