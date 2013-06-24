package mimos.gui;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import mimos.excecao.MimosException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


import mimos.modelo.Produto;
import mimos.controle.ProdutoControle;
import mimos.persistencia.ProdutoDAO;


public class ProdutoView implements    ActionListener, ListSelectionListener {
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
	public ProdutoView(){
		janela=new JFrame("Cadastro de Produtos");
		produto = new ArrayList<Produto>();
		panDetalhes = new JPanel();
		panPrincipal = new JPanel();
		panSouth = new JPanel();
		
		btnInserir = new JButton("Inserir");
		btnInserir.addActionListener( this );
		btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener( this );
		btnRemover = new JButton("Remover");
		btnRemover.addActionListener( this );
		btnPesquisar = new JButton("Pesquisar Titulo");
		btnPesquisar.addActionListener( this );
		
		panSouth.add(btnInserir);
		panSouth.add(btnAtualizar);
		panSouth.add(btnRemover);
		panSouth.add(btnPesquisar);
		
		txtDescricao = new JTextField(10);
		txtPreco = new JTextField(100);
		txtMargemLucro = new JTextField(30);
		txtPrecoVenda = new JTextField(100);
		txtQuantidade = new JTextField(50);
	
		panDetalhes.setLayout(new GridLayout(6, 2) );
		panDetalhes.add(new JLabel("Descrição: "));
		panDetalhes.add(txtDescricao);
		panDetalhes.add(new JLabel("Preço: "));
		panDetalhes.add(txtPreco);
		panDetalhes.add(new JLabel("Margem Lucro: "));
		panDetalhes.add(txtMargemLucro);
		panDetalhes.add(new JLabel("Preço Venda: "));
		panDetalhes.add(txtPrecoVenda);
		panDetalhes.add(new JLabel("Quantidade: "));
		panDetalhes.add(txtQuantidade);

		modeloProduto = new ProdutoModel( produto );
		tblDados = new JTable(modeloProduto);
		
		panPrincipal.setLayout( new BorderLayout() );
		panPrincipal.add(panSouth, BorderLayout.SOUTH);
		panPrincipal.add(new JScrollPane(tblDados), BorderLayout.CENTER);
		panPrincipal.add(panDetalhes, BorderLayout.NORTH);
		
		janela.setContentPane(panPrincipal);
		janela.setSize(600, 600);
		janela.setVisible(true);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public Produto telaToProduto() {
		Produto produto = new Produto();
		produto.setDescricao( txtDescricao.getText() );
		produto.setPreco( Double.parseDouble(txtPreco.getText()) );
		produto.setMargemLucro( Double.parseDouble(txtMargemLucro.getText()) );
		produto.setPrecoVenda(Double.parseDouble(txtPrecoVenda.getText()));
		produto.setQuantidade( Double.parseDouble(txtQuantidade.getText()) );
		return produto;
	}

	
	private ProdutoDAO produtoDAO;
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		ProdutoControle prod = new ProdutoControle();
		if ("Inserir".equalsIgnoreCase( cmd )) { 
			try {
			prod.adiciona(telaToProduto());
			
			}
			catch(MimosException ex){
				System.out.println(ex.getMessage());
				
			}
		} else if ("ATUALIZAR".equalsIgnoreCase( cmd )) {
			try{
			prod.alterarProduto(telaToProduto());
			}
			catch(MimosException ex){
				System.out.println(ex.getMessage());
			}
		} else if ("REMOVER".equalsIgnoreCase( cmd )) {
			try{
			prod.excluirProduto(telaToProduto());
			}
			catch(MimosException ex){
				System.out.println(ex.getMessage());
			}
		} else if ("PESQUISAR TITULO".equalsIgnoreCase( cmd )) {
			try{
			produto = prod.realizaPesquisa( txtDescricao.getText() );
			tblDados.setModel(new ProdutoModel (produto));
			tblDados.repaint();
			modeloProduto.setProduto(produto);
			 
			}
			catch(MimosException ex){
				System.out.println(ex.getMessage());
			}
		}
		
	}
	public void publishAlunoToView(Produto a) { 
		txtDescricao.setText( a.getDescricao() );
		txtPreco.setText( String.valueOf(a.getPreco() ));
		txtMargemLucro.setText( String.valueOf( a.getMargemLucro() ) );
		txtPrecoVenda.setText( String.valueOf( a.getPrecoVenda()  ) );
		txtPrecoVenda.setText( String.valueOf( a.getPrecoVenda()  ) );
		txtQuantidade.setText( String.valueOf( a.getQuantidade()  ) );
	}
	public static void main(String args[]){
		ProdutoView p = new ProdutoView();
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		ListSelectionModel lsm = (ListSelectionModel)e.getSource();
		ProdutoModel atm = (ProdutoModel)tblDados.getModel();
		Produto a = atm.get( lsm.getAnchorSelectionIndex() );
		publishAlunoToView(a);
	}

}
