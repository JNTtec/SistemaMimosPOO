package mimos.gui;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuBar;
import javax.swing.JMenu;

import mimos.controle.MandarMensagem;
import mimos.excecao.MimosException;

public class Principal extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
				
				try {
					Principal f = new Principal();
	
				} catch (Exception e) {
					e.printStackTrace();
				
			}
		
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		setTitle("Principal");
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		this.setVisible(true);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnRecursos = new JMenu("Recursos");
		menuBar.add(mnRecursos);
		
		JMenuItem mnFuncionrios = new JMenuItem("Funcion�rio");
		
//		mnFuncionrios.setActionCommand("Funcion�rio");
		mnRecursos.add(mnFuncionrios);
		
		JMenuItem mnCliente = new JMenuItem("Cliente");
		mnRecursos.add(mnCliente);
		
		JMenuItem mnArteso = new JMenuItem("Artes�o");
		mnRecursos.add(mnArteso);
		
		JMenuItem mnVendedor = new JMenuItem("Vendedor");
		mnRecursos.add(mnVendedor);
		
		JMenuItem mnFornecedor = new JMenuItem("Fornecedor");
		mnRecursos.add(mnFornecedor);
		
		
		JMenu menu = new JMenu("");
		menuBar.add(menu);
		
		JMenu mnControle = new JMenu("Controle");
		menuBar.add(mnControle);
		
		JMenuItem mnEmpresa = new JMenuItem("Empresa");
		mnControle.add(mnEmpresa);
		
		JMenu mnAtivos = new JMenu("Ativos");
		menuBar.add(mnAtivos);
		
		JMenuItem mnProduto = new JMenuItem("Produto");
		mnAtivos.add(mnProduto);
		
		JMenu mnAjuda = new JMenu("Ajuda");
		menuBar.add(mnAjuda);
		
		JMenuItem mnSair = new JMenuItem("Sair");
		JMenuItem mnSobre = new JMenuItem("Sobre");
		mnAjuda.add(mnSobre);
		mnAjuda.add(mnSair);

		mnEmpresa.addActionListener(this);
		mnArteso.addActionListener(this);
		mnCliente.addActionListener(this);
		mnFornecedor.addActionListener(this);
		mnFuncionrios.addActionListener(this);
		mnProduto.addActionListener(this);
		mnVendedor.addActionListener(this);
		mnSobre.addActionListener(this);
		mnSair.addActionListener(this);
			
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {	
		MandarMensagem m = new MandarMensagem();
		String cmd = e.getActionCommand();
		 
		if("Empresa".equalsIgnoreCase(cmd))
		{
			EmpresaView empresa = new EmpresaView();
			//this.setExtendedState(ICONIFIED);
			
		}else if ("Funcion�rio".equalsIgnoreCase(cmd))
		{
			FuncionarioView funcionario = new FuncionarioView();
			funcionario.setVisible(true);
			//this.setExtendedState(ICONIFIED);
					
			
		}else if ("Vendedor".equalsIgnoreCase(cmd))
		{
			VendedorView vendedor = new VendedorView();
			vendedor.setVisible(true);
			//this.setExtendedState(ICONIFIED);
			
		}else if ("Cliente".equalsIgnoreCase(cmd))
		{
			ClienteView cliente = new ClienteView();
			
			//this.setExtendedState(ICONIFIED);
			
			
		}else if ("Produto".equalsIgnoreCase(cmd))
		{
			
			ProdutoView produto = new ProdutoView();
			//this.setExtendedState(ICONIFIED);
			
		}else if ("Artes�o".equalsIgnoreCase(cmd))
		{
			
			ArtesaoView artesao = new ArtesaoView();
			artesao.Carregar();
			System.out.println("foi");
			
		}else if ("Fornecedor".equalsIgnoreCase(cmd))
		{
			FornecedorView fornecedor = new FornecedorView();
			fornecedor.setVisible(true);
			//this.setExtendedState(ICONIFIED);
			
		}else if("Sobre".equalsIgnoreCase(cmd)){
//		m.enviarMensagem("Oie");
			Sobre sobre = new Sobre();
//	sobre.setVisible(true);
//		
//
		}	else if("Sair".equalsIgnoreCase(cmd)){
			System.exit(0);
			
		}
	}

	
	

}
		

