package mimos.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Funcionario {

	private JFrame frmCadastrarFuncionrio;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Funcionario window = new Funcionario();
					window.frmCadastrarFuncionrio.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Funcionario() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCadastrarFuncionrio = new JFrame();
		frmCadastrarFuncionrio.setTitle("Cadastrar Funcionário");
		frmCadastrarFuncionrio.setBounds(100, 100, 450, 300);
		frmCadastrarFuncionrio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCadastrarFuncionrio.getContentPane().setLayout(new GridLayout(2, 1, 3, 0));
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		frmCadastrarFuncionrio.getContentPane().add(btnNewButton);
	}

}
