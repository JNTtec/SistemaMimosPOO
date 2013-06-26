package mimos.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class VendaView extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel principal = new JPanel();
	private JButton btnFinalizar = new JButton("Finalizar Venda");
	private JButton btnCancelar = new JButton("Cancelar Venda");
	private JButton btnAddProduto = new JButton("Adicionar Produto");
	private JButton btnRemoverProduto = new JButton("Remover Produto");
	private JLabel lblValorTotal = new JLabel("Valor Total");
	private JLabel lblValorTroco = new JLabel("Valor Troco");
	private JTextField txtValorTotal = new JTextField();
	private JTextField txtValorTroco = new JTextField();
	private JTable produtos = new JTable();
	
	
	public VendaView() {
	
	principal.setLayout(null);
	this.setContentPane(principal);
	lblValorTroco.setBounds(520, 600, 100, 30);
	principal.add(lblValorTroco);
	txtValorTroco.setBounds(500, 630, 100, 30);
	principal.add(txtValorTroco);
	
	lblValorTotal.setBounds(520, 540, 100, 30);
	principal.add(lblValorTotal);
	txtValorTotal.setBounds(500, 570, 100, 30);
	principal.add(txtValorTotal);
	
	btnFinalizar.setBounds(670, 500, 150, 30);
	principal.add(btnFinalizar);
	btnCancelar.setBounds(670, 538, 150, 30);
	principal.add(btnCancelar);
	
	btnAddProduto.setBounds(50, 60, 150, 30);
	btnAddProduto.addActionListener(this);
	principal.add(btnAddProduto);
	btnRemoverProduto.setBounds(230, 60, 150, 30);
	principal.add(btnRemoverProduto);
	
	produtos.setBounds(20, 80, 500, 400);
	principal.add(new JScrollPane(produtos));
	
	
	this.setBounds(200,20,850,750);
	this.setVisible(true);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	
	
		
		
			}
	/**
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * @param args
	 * 
	 * 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new VendaView();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnAddProduto){
			ProdutoView p = new ProdutoView();
			System.out.println("Teste");
		}
		
	}

}
