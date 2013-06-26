package mimos.gui;
import mimos.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import mimos.constantes.Sexo;
import mimos.controle.ClienteControle;
import mimos.controle.MandarMensagem;
import mimos.excecao.MimosException;
import mimos.modelo.Artesao;
import mimos.modelo.Cliente;
import mimos.persistencia.ClienteDAO;

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

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ListSelectionModel;



import javax.swing.SwingConstants;

public class ClienteView implements ActionListener , ListSelectionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame janela;
	private JPanel panPrincipal;
	private JPanel panSouth;
	private JPanel panDetalhes;
		
	private final JButton btnInserir = new JButton("Inserir");
	private final JButton btnAtualizar = new JButton("Atualizar");
	private final JButton btnRemover = new JButton("Remover");
	private final JButton btnPesquisar = new JButton("Pesquisar");
	private final JButton btnSair = new JButton("Sair");
	
	private JTextField txtCodigo;
	private JTextField txtNome;
	private JTextField txtTelefone;
	private JTextField txtEndereco;
	private JTextField txtCPF ;
	private JTextField txtHabilidade;
	private JTextField txtDataNascimento;
	private JTextField txtEmail;
	
	private final DefaultComboBoxModel cmbSexoModel = new DefaultComboBoxModel(Sexo.values()); 
	private JComboBox<Sexo> cmbSexo;
	private JComboBox combo;
	
	
	private JTable tabela = new JTable();
	public MandarMensagem m = new MandarMensagem();
	private ClienteTableModel ClienteModelo;
	private List<Cliente> cliente;
	private List<Cliente> listaCombo;
	String nomeCliente;
	public ClienteView() {
		
		String nomeCliente;
		janela=new JFrame ("Cadastro de Clientes");
		cliente = new ArrayList<Cliente>();
		panDetalhes = new JPanel();
		panPrincipal = new JPanel();
		panSouth = new JPanel();
		
		panSouth.add(btnInserir);
		panSouth.add(btnAtualizar);
		panSouth.add(btnRemover);
		panSouth.add(btnPesquisar);
		panSouth.add(btnSair);
		
		txtCodigo = new JTextField(2);
		txtNome = new JTextField(100);
		txtTelefone = new JTextField(20);
		txtEndereco = new JTextField(10);
		txtCPF = new JTextField(100);
		txtHabilidade = new JTextField(50);
		txtDataNascimento = new JTextField(12);
		txtCodigo.setEnabled(false);
		combo = new JComboBox();
		ClienteControle ct = new ClienteControle();
		tabela.getSelectionModel().addListSelectionListener( this );
		
		cmbSexo = new JComboBox<Sexo>() ;
		cmbSexo.setModel(cmbSexoModel);
		
		
		panDetalhes.setLayout(new GridLayout(8, 2) );
		panDetalhes.add(new JLabel("Código: "));
		panDetalhes.add(txtCodigo);
		panDetalhes.add(new JLabel("Nome: "));
		panDetalhes.add(txtNome);
		panDetalhes.add(new JLabel("Telefone: "));
		panDetalhes.add(txtTelefone);
		panDetalhes.add(new JLabel("Endereço: "));
		panDetalhes.add(txtEndereco);
		panDetalhes.add(new JLabel("Empresa: "));
		panDetalhes.add(combo);
		panDetalhes.add(new JLabel("Sexo: "));
		panDetalhes.add(cmbSexo);
		panDetalhes.add(new JLabel("CPF: "));
		panDetalhes.add(txtCPF);
		panDetalhes.add(new JLabel("Data de Nascimento: "));
		panDetalhes.add(txtDataNascimento);
		panDetalhes.add(new JLabel("Email: "));
		panDetalhes.add(txtEmail);
		
		ClienteModelo = new ClienteTableModel( cliente );
		tabela = new JTable(ClienteModelo);
		tabela.getSelectionModel().addListSelectionListener( this );

		panPrincipal.setLayout( new BorderLayout() );
		panPrincipal.add(panSouth, BorderLayout.SOUTH);
		panPrincipal.add(new JScrollPane(tabela), BorderLayout.CENTER);
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
	
		public Cliente telaToCliente2() {
			Cliente cliente = new Cliente();		
			cliente.setCod_cliente(Long.parseLong(txtCodigo.getText()));			
			cliente.setNome( txtNome.getText() );
			cliente.setTelefone(txtTelefone.getText());
			cliente.setEndereco(txtEndereco.getText());
			nomeCliente = combo.getSelectedItem().toString();
			cliente.setSexo(cmbSexo.getSelectedItem().toString());			
			cliente.setCpf(txtCPF.getText());
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			try
			{
				cliente.setDataNascimento( sdf.parse(txtDataNascimento.getText()));
	
			}catch (ParseException ex){
				
				ex.getMessage();
			}
			cliente.setEmail(txtEmail.getText());
	
		return cliente;
	}		public Cliente telaToCliente() {
		Cliente cliente = new Cliente();
		//c.setCod_cliente(Long.parseLong(txtCodCliente.getText()));
		cliente.setNome( txtNome.getText() );
		cliente.setTelefone(txtTelefone.getText());
		cliente.setEndereco(txtEndereco.getText());
		nomeCliente = combo.getSelectedItem().toString();
		cliente.setSexo(cmbSexo.getSelectedItem().toString());			
		cliente.setCpf(txtCPF.getText());
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try
		{
			cliente.setDataNascimento( sdf.parse(txtDataNascimento.getText()));

		}catch (ParseException ex){
			
			ex.getMessage();
		}
		cliente.setEmail(txtEmail.getText());

	return cliente;
}@Override
	
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		ClienteControle ct = new ClienteControle();	
		
		if ("INSERIR".equalsIgnoreCase(cmd))
		{
		try {
					ct.adiciona(telaToCliente(),nomeCliente);
			} catch (MimosException ex) {
				System.out.println(ex.getMessage());
			}
		}else if ("ATUALIZAR".equalsIgnoreCase( cmd )) {
			try{
			ct.alterarCliente(telaToCliente2(), nomeCliente);
			nomeCliente = combo.getSelectedItem().toString();
			}
			catch(MimosException ex){
				System.out.println(ex.getMessage());
			}
		}else if ("PESQUISAR".equalsIgnoreCase(cmd))
		{
			List<Cliente> clientes;
			try {
				if (txtNome.getText() != ""){
					clientes = ct.realizaPesquisa(txtNome.getText());
					tabela.setModel(new ClienteTableModel( clientes ) );
					tabela.repaint();
					ClienteModelo.setCliente(clientes);
					
			}else
					m.enviarMensagem("O campo Nome não pode estar vazio", "Erro Pesquisa" , 1);
				
			} catch (MimosException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		}	
		else if ("REMOVER".equalsIgnoreCase(cmd))
		{
			try {
				
				ct.excluirCliente(telaToCliente2());
			} catch (MimosException ex) {
				// TODO Auto-generated catch block
				System.out.println(ex.getMessage());
			}
			
		}else if ("SAIR".equalsIgnoreCase(cmd))
			janela.dispose();
			Principal p = new Principal();
			
	}


	public void publishAlunoToView(Cliente c) { 
		txtCodigo.setText( String.valueOf( c.getCod_cliente() ) );
		txtNome.setText( c.getNome() );
		txtTelefone.setText( String.valueOf(c.getTelefone()));
		txtEndereco.setText( String.valueOf( c.getEndereco() ) );
		cmbSexo.setSelectedItem(String.valueOf(c.getSexo())); 
		txtCPF.setText( String.valueOf(c.getCpf()));
		
		 //ver se tah funcionando o combobox
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		txtDataNascimento.setText( sdf.format( c.getDataNascimento() ) );
		txtEmail.setText(String.valueOf(c.getEmail()));
	}

	
		@Override
	public void valueChanged(ListSelectionEvent e) {
		ListSelectionModel lsm = (ListSelectionModel)e.getSource();
//		System.out.println( "Foi clicado na Tabela na posicao " + lsm.getAnchorSelectionIndex());
		
		ClienteTableModel atm = (ClienteTableModel)tabela.getModel();
		Cliente a = atm.get( lsm.getAnchorSelectionIndex() );
		publishAlunoToView(a);
		
	}
		
}

	
	

 	