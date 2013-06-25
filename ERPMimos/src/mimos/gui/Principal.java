package mimos.gui;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuBar;
import javax.swing.JMenu;

import mimos.controle.MandarMensagem;

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
		
		JMenu mnFuncionrios = new JMenu("Funcion�rio");
		
//		mnFuncionrios.setActionCommand("Funcion�rio");
		mnRecursos.add(mnFuncionrios);
		
		JMenu mnCliente = new JMenu("Cliente");
		mnRecursos.add(mnCliente);
		
		JMenu mnArteso = new JMenu("Artes�o");
		mnRecursos.add(mnArteso);
		
		JMenu mnVendedor = new JMenu("Vendedor");
		mnRecursos.add(mnVendedor);
		
		JMenu mnFornecedor = new JMenu("Fornecedor");
		mnRecursos.add(mnFornecedor);
		
		
		JMenu menu = new JMenu("");
		menuBar.add(menu);
		
		JMenu mnControle = new JMenu("Controle");
		menuBar.add(mnControle);
		
		JMenu mnEmpresa = new JMenu("Empresa");
		mnControle.add(mnEmpresa);
		
		JMenu mnAtivos = new JMenu("Ativos");
		menuBar.add(mnAtivos);
		
		JMenu mnProduto = new JMenu("Produto");
		mnAtivos.add(mnProduto);
		
		JMenu mnAjuda = new JMenu("Ajuda");
		menuBar.add(mnAjuda);
		
		JMenu mnSobre = new JMenu("Sobre");
		mnAjuda.add(mnSobre);
		

		mnEmpresa.addActionListener(this);
		mnArteso.addActionListener(this);
		mnCliente.addActionListener(this);
		mnFornecedor.addActionListener(this);
		mnFuncionrios.addActionListener(this);
		mnProduto.addActionListener(this);
		mnVendedor.addActionListener(this);
		mnSobre.addActionListener(this);
		
			
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);
		
	}

	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		MandarMensagem m = new MandarMensagem();
		
		String cmd = e.getActionCommand();
		if("Sobre".equalsIgnoreCase("Sobre")){
			m.enviarMensagem("Oie");
			Sobre sobre = new Sobre();
			sobre.setVisible(true);
			
		}else if("Empresa".equalsIgnoreCase(cmd))
		{
			EmpresaView empresa = new EmpresaView();
//			empresa.setVisible(true);
			this.hide();
		}else if ("Funcion�rio".equalsIgnoreCase(cmd))
		{
			FuncionarioView funcionario = new FuncionarioView();
			funcionario.setVisible(true);
			this.hide();
			
			
		}else if ("Vendedor".equalsIgnoreCase(cmd))
		{
			VendedorView vendedor = new VendedorView();
			vendedor.setVisible(true);
			this.hide();
			
		}else if ("Cliente".equalsIgnoreCase(cmd))
		{
			ClienteView cliente = new ClienteView();
			cliente.setVisible(true);
			this.hide();
			
			
		}else if ("Produto".equalsIgnoreCase(cmd))
		{
			
			ProdutoView produto = new ProdutoView();
			this.hide();
			
		}else if ("Artes�o".equalsIgnoreCase(cmd))
		{
			ArtesaoView artesao = new ArtesaoView();
			artesao.setVisible(true);
			this.hide();
			
		}else if ("Fornecedor".equalsIgnoreCase(cmd))
		{
			FornecedorView fornecedor = new FornecedorView();
			fornecedor.setVisible(true);
			this.hide();
			
		}
		
	}

}
		

