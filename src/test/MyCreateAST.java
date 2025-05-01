package test;

import java.util.ArrayList;
import java.util.List;

import cfg.CFG;
import org.junit.jupiter.api.Test;

import ast.*;

public class MyCreateAST {

    @Test
    void testCustom1() {
        MethodNode m = new MethodNode("custom_m1");
        AST ast = new AST(m);
        CFG cfg = new CFG();
        WhileNode n = new WhileNode("while_1");
        List<Node> whileB = new ArrayList<>();
        Node wB = new Node("wn_1");
        whileB.add(wB);
        n.addBlock(whileB);
        m.addNode(n);

        ast.toDot();
        cfg.fromAST(ast);
        cfg.toDot();
    }

    @Test
    void testCustom2() {
        MethodNode m = new MethodNode("custom_m2");
        AST ast = new AST(m);
        CFG cfg = new CFG();
        WhileNode n = new WhileNode("while_1");
        List<Node> whileB = new ArrayList<>();
        Node wB = new Node("wn_1");
        whileB.add(wB);
        IfNode ifN = new IfNode("if_1");
        Node n_1 = new Node("n_1");
        Node n_2 = new Node("n_2");
        List<Node> ifTrueBlock = new ArrayList<>();
        ifTrueBlock.add(n_1);
        List<Node> ifFalseBlock = new ArrayList<>();
        ifFalseBlock.add(n_2);
        ifN.setTrueBlock(ifTrueBlock);
        ifN.setFalseBlock(ifFalseBlock);
        whileB.add(ifN);

        n.addBlock(whileB);
        m.addNode(n);

        ast.toDot();
        cfg.fromAST(ast);
        cfg.toDot();
    }

    @Test
    void testCustom3() {
        MethodNode m = new MethodNode("custom_m3");
        AST ast = new AST(m);
        CFG cfg = new CFG();
        WhileNode n = new WhileNode("while_1");
        List<Node> whileB = new ArrayList<>();
        Node wB_1 = new Node("wn_1");
        whileB.add(wB_1);
        IfNode ifN = new IfNode("if_1");
        Node n_1 = new Node("n_1");
        ReturnNode n_2 = new ReturnNode("r_1");
        List<Node> ifTrueBlock = new ArrayList<>();
        ifTrueBlock.add(n_1);
        List<Node> ifFalseBlock = new ArrayList<>();
        ifFalseBlock.add(n_2);
        ifN.setTrueBlock(ifTrueBlock);
        ifN.setFalseBlock(ifFalseBlock);
        whileB.add(ifN);
        Node wB_2 = new Node("wn_2");
        whileB.add(wB_2);

        n.addBlock(whileB);
        m.addNode(n);

        ast.toDot();
        cfg.fromAST(ast);
        cfg.toDot();
    }

    @Test
    void testCustom4() {
        MethodNode m = new MethodNode("custom_m4");
        AST ast = new AST(m);
        CFG cfg = new CFG();
        WhileNode n = new WhileNode("while_1");
        List<Node> whileB = new ArrayList<>();
        Node wB_1 = new Node("wn_1");
        whileB.add(wB_1);
        IfNode ifN = new IfNode("if_1");
        Node n_1 = new Node("n_1");
        ReturnNode n_2 = new ReturnNode("r_1");
        List<Node> ifTrueBlock = new ArrayList<>();
        ifTrueBlock.add(n_1);
        List<Node> ifFalseBlock = new ArrayList<>();
        ifFalseBlock.add(n_2);
        ifN.setTrueBlock(ifFalseBlock);
        ifN.setFalseBlock(ifTrueBlock);
        whileB.add(ifN);

        Node wB_2 = new Node("wn_2");
        whileB.add(wB_2);

        n.addBlock(whileB);
        m.addNode(n);

        ast.toDot();
        cfg.fromAST(ast);
        cfg.toDot();
    }

    @Test
    void testCustom5() {
        MethodNode m = new MethodNode("custom_m5");
        AST ast = new AST(m);
        CFG cfg = new CFG();

        IfNode ifN = new IfNode("if_1");
        Node n_1 = new Node("n_1");
        IfNode ifN_2 = new IfNode("if_2");
        ReturnNode r_1 = new ReturnNode("r_1");
        List<Node> if1TrueBlock = new ArrayList<>();
        List<Node> if2TrueBlock = new ArrayList<>();
        if2TrueBlock.add(r_1);
        if1TrueBlock.add(n_1);
        if1TrueBlock.add(ifN_2);
        ifN.setTrueBlock(if1TrueBlock);
        ifN_2.setTrueBlock(if2TrueBlock);

        IfNode ifN_3 = new IfNode("if_3");
        Node n_3 = new Node("n_3");
        List<Node> if3TrueBlock = new ArrayList<>();
        if3TrueBlock.add(n_3);
        ifN_3.setTrueBlock(if3TrueBlock);
        List<Node> if1FalseBlock = new ArrayList<>();
        if1FalseBlock.add(ifN_3);
        ifN.setFalseBlock(if1FalseBlock);

        m.addNode(ifN);

        ast.toDot();
        cfg.fromAST(ast);
        cfg.toDot();
    }


    @Test
    void testCustom6() {
        MethodNode m = new MethodNode("custom_m6");
        AST ast = new AST(m);
        CFG cfg = new CFG();

        Node n_1 = new Node("n_1");
        ReturnNode r_1 = new ReturnNode("r_1");
        Node n_2 = new Node("n_2");
        m.addNode(n_1);
        m.addNode(r_1);
        m.addNode(n_2);

        ast.toDot();
        cfg.fromAST(ast);
        cfg.toDot();
    }

    @Test
    void testCustom7() {
        MethodNode m = new MethodNode("custom_m7");
        AST ast = new AST(m);
        CFG cfg = new CFG();

        WhileNode w = new WhileNode("while_1");
        List<Node> whileB = new ArrayList<>();
        IfNode if_1 = new IfNode("if_1");
        Node n_1 = new Node("n_1");
        if_1.setTrueBlock(List.of(n_1));
        whileB.add(if_1);
        w.addBlock(whileB);
        m.addNode(w);

        ast.toDot();
        cfg.fromAST(ast);
        cfg.toDot();
    }

    @Test
    void testCustom8() {
        MethodNode m = new MethodNode("custom_m8");
        AST ast = new AST(m);
        CFG cfg = new CFG();

        IfNode if1 = new IfNode("if_1");
        Node n_1 = new Node("n_1");
        Node n_2 = new Node("n_2");
        if1.setTrueBlock(List.of(n_1));
        if1.setFalseBlock(List.of(n_2));

        IfNode if2 = new IfNode("if_2");
        Node n_3 = new Node("n_3");
        if2.setTrueBlock(List.of(n_3));

        m.addNode(if1);
        m.addNode(if2);

        ast.toDot();
        cfg.fromAST(ast);
        cfg.toDot();
    }

    @Test
    void testCustom9() {
        MethodNode m = new MethodNode("custom_m9");
        AST ast = new AST(m);
        CFG cfg = new CFG();

        WhileNode wEmpty = new WhileNode("while_1");
        wEmpty.addBlock(new ArrayList<>());

        IfNode if_1 = new IfNode("if_1");
        Node n_1 = new Node("n_1");
        Node n_2 = new Node("n_2");
        if_1.setTrueBlock(List.of(n_1));
        if_1.setFalseBlock(List.of(n_2));

        m.addNode(wEmpty);
        m.addNode(if_1);

        ast.toDot();
        cfg.fromAST(ast);
        cfg.toDot();
    }

    @Test
    void testCustom10() {
        MethodNode m = new MethodNode("custom_m10");
        AST ast = new AST(m);
        CFG cfg = new CFG();

        IfNode if_1 = new IfNode("if_1");
        WhileNode while_1 = new WhileNode("while_1");
        IfNode if_2 = new IfNode("if_2");
        ReturnNode r_1 = new ReturnNode("r_1");

        if_2.setTrueBlock(List.of(r_1));
        while_1.addBlock(List.of(if_2));
        if_1.setTrueBlock(List.of(while_1));

        m.addNode(if_1);

        ast.toDot();
        cfg.fromAST(ast);
        cfg.toDot();
    }


}
