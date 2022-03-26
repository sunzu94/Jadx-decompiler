package jadx.gui.ui.codearea;

import java.awt.event.KeyEvent;

import org.jetbrains.annotations.Nullable;

import jadx.api.CodePosition;
import jadx.gui.treemodel.JNode;
import jadx.gui.utils.JumpPosition;
import jadx.gui.utils.NLS;

import static javax.swing.KeyStroke.getKeyStroke;

public final class GoToDeclarationAction extends JNodeAction {
	private static final long serialVersionUID = -1186470538894941301L;

	private transient @Nullable JumpPosition declPos;

	public GoToDeclarationAction(CodeArea codeArea) {
		super(NLS.str("popup.go_to_declaration") + " (d)", codeArea);
		addKeyBinding(getKeyStroke(KeyEvent.VK_D, 0), "trigger goto decl");
	}

	@Override
	public boolean isActionEnabled(JNode node) {
		declPos = null;
		if (node == null) {
			return false;
		}
		CodePosition defPos = getCodeArea().getDecompiler().getDefinitionPosition(node.getJavaNode());
		if (defPos == null) {
			return false;
		}
		declPos = new JumpPosition(node.getRootClass(), defPos);
		return true;
	}

	@Override
	public void runAction(JNode node) {
		if (declPos != null) {
			getCodeArea().getContentPanel().getTabbedPane().codeJump(declPos);
		}
	}
}
