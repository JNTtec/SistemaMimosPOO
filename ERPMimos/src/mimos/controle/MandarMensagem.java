package mimos.controle;
import javax.swing.*;

public class MandarMensagem {

	
	public void enviarMensagem(String mensagem){
		
		JOptionPane.showMessageDialog(null, mensagem);
		
	}
	
	
public void enviarMensagem(String mensagem , String Titulo, int tipo ){
		
		JOptionPane.showMessageDialog(null, mensagem, Titulo, tipo);
		
	}
	
}
