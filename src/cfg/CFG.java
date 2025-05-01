package cfg;

import ast.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class CFG {
	
	private Node entry;
	private Node exit;
	
	public CFG() {
		entry = new Node("Entry");
		exit = new Node("Exit");
	}
	
	
	/**
	 * Links existing nodes and
	 * connects with entry and 
	 * exit nodes
	 * @param ast
	 */
	public void fromAST(AST ast) {
		entry = ast.getRoot();
		List<Node> stmts = ((MethodNode) entry).getStmt();
		if (!stmts.isEmpty()) {
			entry.setSucc(stmts.get(0));
		}
		linkAST(stmts, exit);

	}

	private void linkAST(List<Node> stmts, Node next) {
		for (int i = 0; i < stmts.size(); i++) {
			Node curr = stmts.get(i);
			Node after;

			if (i + 1 < stmts.size()) {
				after = stmts.get(i + 1);
			} else {
				after = next;
			}

			if (curr instanceof IfNode) {
				IfNode ifNode = (IfNode) curr;
				List<Node> trueBlock = ifNode.getTrueBlock();
				if (!trueBlock.isEmpty()) {
					ifNode.setSucc(trueBlock.get(0));
					linkAST(trueBlock, after);
				} else {
					ifNode.setSucc(after);
				}
				List<Node> falseBlock = ifNode.getFalseBlock();
				if (!falseBlock.isEmpty()) {
					ifNode.setSuccFalse(falseBlock.get(0));
					linkAST(falseBlock, after);
				} else {
					ifNode.setSuccFalse(after);
				}
			}

			else if (curr instanceof WhileNode) {
				WhileNode whileNode = (WhileNode) curr;
				List<Node> block = whileNode.getStmt();
				if (!block.isEmpty()) {
					whileNode.setSucc(block.get(0));
					linkAST(block, curr);
				} else {
					whileNode.setSucc(curr);
				}
				whileNode.setSuccFalse(after);
			}

			else if (curr instanceof ReturnNode) {
				curr.setSucc(exit);
			}

			else {
				curr.setSucc(after);
			}
		}
	}

	public void toDot() {
		String graphName = entry.getName() + "_CFG";
		StringBuilder sb = new StringBuilder("digraph " + graphName + " {\n");

		Set<Node> visited = new HashSet<>();
		List<Node> queue = new ArrayList<>();
		queue.add(entry);
		visited.add(entry);

		int i = 0;
		while (i < queue.size()) {
			Node n = queue.get(i++);
			Node succ = n.getSucc();

			if (succ != null && succ != Node.NONE) {
				sb.append("\t\"").append(n.getName()).append("\" -> \"").append(succ.getName()).append("\";\n");
				if (visited.add(succ)) {
					queue.add(succ);
				}
			}

			if (n instanceof IfNode) {
				Node f = ((IfNode) n).getSuccFalse();
				if (f != null && f != Node.NONE) {
					sb.append("\t\"").append(n.getName()).append("\" -> \"").append(f.getName()).append("\" [style=dashed];\n");
					if (visited.add(f)) {
						queue.add(f);
					}
				}
			}

			else if (n instanceof WhileNode) {
				Node f = ((WhileNode) n).getSuccFalse();
				if (f != null && f != Node.NONE) {
					sb.append("\t\"").append(n.getName()).append("\" -> \"").append(f.getName()).append("\" [style=dashed];\n");
					if (visited.add(f)) {
						queue.add(f);
					}
				}
			}
		}

		sb.append("}\n");

		try (FileWriter fw = new FileWriter(entry.getName() + "_CFG.dot")) {
			fw.write(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
