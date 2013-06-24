package mimos.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import mimos.modelo.Artesao;
import mimos.modelo.Cliente;
import mimos.modelo.Fornecedor;
import mimos.persistencia.ArtesaoDAO;
import mimos.persistencia.ClienteDAO;
import mimos.persistencia.FornecedorDAO;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Window.Type;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

public class FornecedorView extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final JButton btnInserir = new JButton("Inserir");
	private final JButton btnAtualizar = new JButton("Atualizar");
	private final JButton btnRemover = new JButton("Remover");
	private final JButton btnPesquisar = new JButton("Pesquisar");
	private final JButton btnSair = new JButton("Sair");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FornecedorView frame = new FornecedorView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FornecedorView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(350, 100, 540, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel pnCampos = new JPanel();
		contentPane.add(pnCampos, BorderLayout.NORTH);
		pnCampos.setLayout(new GridLayout(8, 2, 0, 0));
		
		JPanel pnBotoes = new JPanel();
		contentPane.add(pnBotoes, BorderLayout.SOUTH);
		pnBotoes.setLayout(new GridLayout(1, 5, 0, 0));
		
		// Acao para os Botoes
		btnInserir.addActionListener(this);
		btnAtualizar.addActionListener(this);
		btnRemover.addActionListener(this);
		btnSair.addActionListener(this);
		btnPesquisar.addActionListener(this);
	
		pnBotoes.add(btnInserir);		
		pnBotoes.add(btnAtualizar);
		pnBotoes.add(btnRemover);
		pnBotoes.add(btnPesquisar);
		pnBotoes.add(btnSair);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if ("INSERIR".equalsIgnoreCase(cmd))
		{
			
			
		}
		
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
	
	
	

 	