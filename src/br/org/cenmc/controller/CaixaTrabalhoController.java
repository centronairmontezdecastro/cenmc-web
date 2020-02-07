package br.org.cenmc.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

@Named
@ConversationScoped
public class CaixaTrabalhoController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 private TreeNode root;
     
	    @PostConstruct
	    public void init() {
	        root = new DefaultTreeNode("Root", null);
	        TreeNode node1 = new DefaultTreeNode("Dados Pessoais", root);
	         
	        TreeNode node10 = new DefaultTreeNode("Node 1.0", node1);
	         
	        node1.getChildren().add(new DefaultTreeNode("Node 1.1"));
	        node10.getChildren().add(new DefaultTreeNode("Node 1.0.0"));
	        root.getChildren().add(new DefaultTreeNode("Alterar Senha"));
	        root.getChildren().add(new DefaultTreeNode("Chat"));
	    }
	 
	    public TreeNode getRoot() {
	        return root;
	    }
}
