package sourcegenerator.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;

import sourcegenerator.generation.BuildEntity;
import sourcegenerator.generation.ClassDao;
import sourcegenerator.generation.ClassEJB;
import sourcegenerator.generation.ClassEdit;
import sourcegenerator.generation.ClassList;
import sourcegenerator.generation.ClassMB;
import sourcegenerator.generation.ClassMessage;
import sourcegenerator.generation.ClassSearch;
import sourcegenerator.generation.Entity;
import sourcegenerator.generation.InterfaceEJB;
import sourcegenerator.util.FileUtil;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class ServiceHandler extends AbstractHandler {
	/**
	 * The constructor.
	 *
  		private QualifiedName path = new QualifiedName("html", "path");
	 */
	public ServiceHandler() {
	}

	/**
	 * the command has been executed, so extract extract the needed information
	 * from the application context.
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {

		Shell shell = HandlerUtil.getActiveShell(event);
		ISelection selection = HandlerUtil.getActiveMenuSelection(event);
		IStructuredSelection structuredSelection = (IStructuredSelection) selection;

		Object firstElement = structuredSelection.getFirstElement();
		if (firstElement instanceof ICompilationUnit) {


			try {
				Entity entity = BuildEntity.build((ICompilationUnit) firstElement);
				generate(entity);
				MessageDialog.openInformation(shell, "Info", "Arquivo Gerado com Sucesso");
			} catch (Exception e) {
				e.printStackTrace();
				MessageDialog.openInformation(shell, "Error", "Falha ao gerar Serviço");
			}

		} else {
			MessageDialog.openInformation(shell, "Info", "Favor selecionar uma entidade java");
		}
		return null;
	}

	public static Boolean generate(Entity entity) {
		if(entity.getDAO()){
			ClassDao dao = new ClassDao();
			FileUtil.writeFile(dao.filePath(entity), dao.sourceCode(entity));
		}
		if(entity.getIEJB()){
			InterfaceEJB iEjb = new InterfaceEJB();
			FileUtil.writeFile(iEjb.filePath(entity), iEjb.sourceCode(entity));
		}
		if(entity.getEJB()){
			ClassEJB ejb = new ClassEJB();
			FileUtil.writeFile(ejb.filePath(entity), ejb.sourceCode(entity));
		}
		if(entity.getWRAPPER()){
			ClassSearch wrapper = new ClassSearch();
			FileUtil.writeFile(wrapper.filePath(entity), wrapper.sourceCode(entity));
		}
		if(entity.getMB()){
			ClassMB mb = new ClassMB();
			FileUtil.writeFile(mb.filePath(entity), mb.sourceCode(entity));
		}
		if(entity.getMESSAGE()){
			ClassMessage msg = new ClassMessage();
			FileUtil.writeFile(msg.filePath(entity), msg.sourceCode(entity));
		}
		if(entity.getLIST_XHTML()){
			ClassList list = new ClassList();
			FileUtil.writeFile(list.filePath(entity), list.sourceCode(entity));
		}
		if(entity.getEDIT_XHTML()){
			ClassEdit edit = new ClassEdit();
			FileUtil.writeFile(edit.filePath(entity), edit.sourceCode(entity));
		}

		return Boolean.TRUE;
	}

}
