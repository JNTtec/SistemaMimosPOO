package mimos.gui;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import mimos.controle.ProdutoControle;
import mimos.persistencia.ProdutoDAO;


public class Produto implements  ActionListener {
	private JFrame janela;
	private JPanel panPrincipal;
	private JPanel panSouth;
	private JPanel panDetalhes;
	private JTable tblDados;
	
	private JButton btnInserir;
	private JButton btnAtualizar;
	private JButton btnRemover;
	private JButton btnPesquisar;
	
	private JTextField txtDescricao;
	private JTextField txtPreco;
	private JTextField txtMargemLucro;
	private JTextField txtPrecoVenda;
	private JTextField txtQuantidade;
	
	private ProdutoModel modeloProduto;
	private List<Produto> produto;
	
	private ProdutoControle produtocont;
	public Produto(){
		janela=new JFrame("Cadastro de Produtos");
		produto = new ArrayList<Produto>();
		
		
	}
	private ProdutoDAO produtoDAO;
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
