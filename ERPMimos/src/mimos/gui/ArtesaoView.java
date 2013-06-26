package mimos.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import mimos.modelo.Artesao;
import mimos.modelo.Empresa;

import mimos.persistencia.ArtesaoDAO;

import mimos.controle.ArtesaoControle;
import mimos.controle.ArtesaoControle;
import mimos.controle.EmpresaControle;
import mimos.excecao.MimosException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
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

public class ArtesaoView  implements ActionListener, ListSelectionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame janela;
	private JPanel panPrincipal;
	private JPanel panSouth;
	private JPanel panDetalhes;
	private JTable tblDados;	

	private final JButton btnInserir = new JButton("Inserir");
	private final JButton btnAtualizar = new JButton("Atualizar");
	private final JButton btnRemover = new JButton("Remover");
	private final JButton btnPesquisar = new JButton("Pesquisar");
	private final JButton btnSair = new JButton("Sair");
	private JTextField txtCodigo;
	private JTextField txtSenha;
	private JTextField txtNome;
	private JTextField txtUsuario;
	private JTextField txtSalario;
	private JTextField txtHabilidade;
	private JTextField txtIdEmpresa;
	private JComboBox combo;
	private ArtesaoModel modeloArtesao;
	private List<Artesao> artesao;
	private List<Artesao> listaCombo;
	private String nomeEmpresa;
	
	public ArtesaoView() {
		janela=new JFrame ("Cadastro de Artesao");
		artesao = new ArrayList<Artesao>();
		panDetalhes = new JPanel();
		panPrincipal = new JPanel();
		panSouth = new JPanel();
		

		
		
		panSouth.add(btnInserir);
		panSouth.add(btnAtualizar);
		panSouth.add(btnRemover);
		panSouth.add(btnPesquisar);
		panSouth.add(btnSair);
		
		txtCodigo = new JTextField(2);
		txtSenha = new JTextField(10);
		txtNome = new JTextField(100);
		txtUsuario = new JTextField(20);
		txtSalario = new JTextField(10);
		txtHabilidade = new JTextField(100);
		txtIdEmpresa = new JTextField(50);
		txtCodigo.setEnabled(false);
		combo = new JComboBox();
		
		panDetalhes.setLayout(new GridLayout(8, 2) );
		panDetalhes.add(new JLabel("Código: "));
		panDetalhes.add(txtCodigo);
		panDetalhes.add(new JLabel("Senha: "));
		panDetalhes.add(txtSenha);
		panDetalhes.add(new JLabel("Nome: "));
		panDetalhes.add(txtNome);
		panDetalhes.add(new JLabel("Usuario: "));
		panDetalhes.add(txtUsuario);
		panDetalhes.add(new JLabel("Salário: "));
		panDetalhes.add(txtSalario);
		panDetalhes.add(new JLabel("Habilidade: "));
		panDetalhes.add(txtHabilidade);
		panDetalhes.add(new JLabel("ID Empresa: "));
		panDetalhes.add(combo);
		
		modeloArtesao = new ArtesaoModel( artesao );
		tblDados = new JTable(modeloArtesao);
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
	
	public Artesao telaToArtesao() {
		Artesao artesao = new Artesao();
	
		artesao.setSenha( txtSenha.getText() );
		artesao.setNome( txtNome.getText() );
		artesao.setUsuario( txtUsuario.getText() );
		artesao.setSalario(Double.parseDouble( txtSalario.getText()) );
		artesao.setHabilidade( txtHabilidade.getText() );
		nomeEmpresa = combo.getSelectedItem().toString();  
		return artesao;
	}
	public void Carregar(){
		
		try {
			ArtesaoControle art = new ArtesaoControle();
			listaCombo = art.buscaTodos();
		} catch (MimosException ex) {
			// TODO Auto-generated catch block
			System.out.println("nao foi");
			
		} //'produtoDAO' é meu objeto que retorna os produtos do banco.  
		Iterator i = listaCombo.iterator();  
		  
		while(i.hasNext()) {  
		  combo.addItem(String.valueOf(i.next()));  
		} 
		combo.updateUI();
		
		
	}
	public Artesao telaToArtesao2() {
		Artesao artesao = new Artesao();
		
		artesao.setcodArtesao(Long.parseLong(txtCodigo.getText()));
		artesao.setSenha( txtSenha.getText() );
		artesao.setNome( txtNome.getText() );
		artesao.setUsuario( txtUsuario.getText() );
		artesao.setSalario(Double.parseDouble( txtSalario.getText()) );
		artesao.setHabilidade( txtHabilidade.getText() );
		nomeEmpresa = combo.getSelectedItem().toString();  
		return artesao;
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		ArtesaoControle art = new ArtesaoControle();
		if ("INSERIR".equalsIgnoreCase(cmd))
		{
			try {
				art.adiciona(telaToArtesao(), nomeEmpresa);
				nomeEmpresa = combo.getSelectedItem().toString();  
				}
				catch(MimosException ex){
					System.out.println(ex.getMessage());
					
				}
		} else if ("ATUALIZAR".equalsIgnoreCase( cmd )) {
			try{
			art.alterarArtesao(telaToArtesao(), nomeEmpresa);
			nomeEmpresa = combo.getSelectedItem().toString();
			}
			catch(MimosException ex){
				System.out.println(ex.getMessage());
			}
		} else if ("REMOVER".equalsIgnoreCase( cmd )) {
			try{
			art.excluirArtesao(telaToArtesao2());
			}
			catch(MimosException ex){
				System.out.println(ex.getMessage());
			}
		}else if ("PESQUISAR".equalsIgnoreCase( cmd )) {
				try{
				artesao= art.realizaPesquisa( txtNome.getText() );
				
				tblDados.setModel(new ArtesaoModel (artesao));
				tblDados.repaint();
				modeloArtesao.setArtesao(artesao);
				 
				}
				catch(MimosException ex){
					System.out.println(ex.getMessage());
				}
			}
			
			
		else if ("SAIR".equalsIgnoreCase(cmd)){
			
			 
		}
		
		
	}


	public void publishArtesaoToView(Artesao a) { 
		txtCodigo.setText(String.valueOf(a.getcodArtesao()));
		txtSenha.setText( String.valueOf(a.getSenha()));
		txtNome.setText( a.getNome());
		txtUsuario.setText( a.getUsuario() );
		txtSalario.setText( String.valueOf(a.getSalario()));
		txtHabilidade.setText( a.getHabilidade());
		
		
	}
	@Override
	public void valueChanged(ListSelectionEvent a) {
		ListSelectionModel lsm = (ListSelectionModel)a.getSource();
		ArtesaoModel atm = (ArtesaoModel)tblDados.getModel();
		Artesao e = atm.get( lsm.getAnchorSelectionIndex() );
		System.out.println( "Foi clicado na Tabela na posicao " + lsm.getAnchorSelectionIndex());


		publishArtesaoToView(e);
	}
	public static void main(String args[]){
		ArtesaoView e = new ArtesaoView();
	}

	

	}
	
	
	

 	