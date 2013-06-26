package mimos.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import mimos.controle.ProdutoControle;
import mimos.excecao.MimosException;

import mimos.modelo.Artesao;
import mimos.modelo.Cliente;
import mimos.modelo.Empresa;
import mimos.modelo.Produto;
import mimos.persistencia.ArtesaoDAO;
import mimos.persistencia.ClienteDAO;
import mimos.persistencia.EmpresaDAO;
import mimos.persistencia.ProdutoDAO;
import mimos.controle.EmpresaControle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Window.Type;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

public class EmpresaView  implements ActionListener, ListSelectionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame janela;
	private JPanel contentPane;
	private JPanel panPrincipal;
	private JPanel panSouth;
	private JPanel panDetalhes;
	private JTable tblDados;
	private final JButton btnInserir;
	private final JButton btnAtualizar;
	private final JButton btnRemover;
	private final JButton btnPesquisar;
	private final JButton btnSair;
	
	private JTextField txtCodigo;
	private JTextField txtEndereco;
	private JTextField txtFilial;
	
	private EmpresaModel modeloEmpresa;
	private List<Empresa> empresa;

	public EmpresaView() {
		janela=new JFrame ("Cadastro de Empresas");
		empresa = new ArrayList<Empresa>();
		panDetalhes = new JPanel();
		panPrincipal = new JPanel();
		panSouth = new JPanel();
		
		btnInserir = new JButton("Inserir");
		btnAtualizar = new JButton("Atualizar");
		btnRemover = new JButton("Remover");
	    btnPesquisar = new JButton("Pesquisar");
		btnSair = new JButton("Sair");
		
		
		panSouth.add(btnInserir);
		panSouth.add(btnAtualizar);
		panSouth.add(btnRemover);
		panSouth.add(btnPesquisar);
		panSouth.add(btnSair);
		
		txtCodigo = new JTextField(2);
		txtEndereco = new JTextField(10);
		txtFilial = new JTextField(100);
		txtCodigo.setEnabled(false);
		
		panDetalhes.setLayout(new GridLayout(3, 2) );
		panDetalhes.add(new JLabel("Código: "));
		panDetalhes.add(txtCodigo);
		panDetalhes.add(new JLabel("Endereço: "));
		panDetalhes.add(txtEndereco);
		panDetalhes.add(new JLabel("Filial: "));
		panDetalhes.add(txtFilial);
		
		modeloEmpresa = new EmpresaModel( empresa );
		tblDados = new JTable(modeloEmpresa);
		tblDados.getSelectionModel().addListSelectionListener( this );
		
		panPrincipal.setLayout( new BorderLayout() );
		panPrincipal.add(panSouth, BorderLayout.SOUTH);
		panPrincipal.add(new JScrollPane(tblDados), BorderLayout.CENTER);
		panPrincipal.add(panDetalhes, BorderLayout.NORTH);
		
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.setBounds(500, 400, 500, 400);
		janela.setContentPane(panPrincipal); 
		janela.setVisible(true);
		janela.setLocationRelativeTo(null); 
		
		
		
		// Acao para os Botoes
		btnInserir.addActionListener(this);
		btnAtualizar.addActionListener(this);
		btnRemover.addActionListener(this);
		btnSair.addActionListener(this);
		btnPesquisar.addActionListener(this);
		
	}
	
	public Empresa telaToEmpresa() {
		Empresa empresa = new Empresa();
	
		empresa.setEndereco( txtEndereco.getText() );
		empresa.setFilial(txtFilial.getText());
		return empresa;
	}
	public Empresa telaToEmpresa2() {
		Empresa empresa = new Empresa();
		empresa.setId_empresa(Long.parseLong(txtCodigo.getText()));
		empresa.setEndereco( txtEndereco.getText() );
		empresa.setFilial(txtFilial.getText());
		return empresa;
	}
	private EmpresaDAO empresaDAO;

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		EmpresaControle emp = new EmpresaControle();
		if ("INSERIR".equalsIgnoreCase(cmd))
		{
			try {
				emp.adiciona(telaToEmpresa());
				
				}
				catch(MimosException ex){
					System.out.println(ex.getMessage());
					
				}
		} else if ("ATUALIZAR".equalsIgnoreCase( cmd )) {
			try{
			emp.alterarEmpresa(telaToEmpresa2());
			}
			catch(MimosException ex){
				System.out.println(ex.getMessage());
			}
		} else if ("REMOVER".equalsIgnoreCase( cmd )) {
			try{
			emp.excluirEmpresa(telaToEmpresa2());
			}
			catch(MimosException ex){
				System.out.println(ex.getMessage());
			}
		}else if ("PESQUISAR".equalsIgnoreCase( cmd )) {
				try{
				empresa= emp.realizaPesquisa( txtFilial.getText() );
				tblDados.setModel(new EmpresaModel (empresa));
				tblDados.repaint();
				modeloEmpresa.setEmpresa(empresa);
				 
				}
				catch(MimosException ex){
					System.out.println(ex.getMessage());
				}
			}
			
			
		else if ("SAIR".equalsIgnoreCase(cmd)){
			System.exit(0);
}	
	}
	public void publishEmpresaToView(Empresa a) { 
		txtCodigo.setText(String.valueOf(a.getId_empresa()));
		txtEndereco.setText( a.getEndereco());
		txtFilial.setText( a.getFilial() );
	}
	@Override
	public void valueChanged(ListSelectionEvent a) {
		ListSelectionModel lsm = (ListSelectionModel)a.getSource();
		EmpresaModel atm = (EmpresaModel)tblDados.getModel();
		Empresa e = atm.get( lsm.getAnchorSelectionIndex() );
		System.out.println( "Foi clicado na Tabela na posicao " + lsm.getAnchorSelectionIndex());


		publishEmpresaToView(e);
	}
	public static void main(String args[]){
		EmpresaView e = new EmpresaView();
	}
	
	//	@Override
//	public void valueChanged(ListSelectionEvent e) {
//		ListSelectionModel lsm = (ListSelectionModel)e.getSource();
////		System.out.println( "Foi clicado na Tabela na posicao " + lsm.getAnchorSelectionIndex());
//		
//		AlunoTableModel atm = (AlunoTableModel)tabela.getModel();
//		Aluno a = atm.get( lsm.getAnchorSelectionIndex() );
//		publishAlunoToView(a);
//		
	}
	
	
	

 	