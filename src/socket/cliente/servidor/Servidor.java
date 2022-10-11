package socket.cliente.servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	
	public static void main(String[] args) throws IOException {
		
		Boolean selecao = true;
		double somaNumero = 0;
		
		// Definir o serverSocket (Abrir Conexão)
		ServerSocket serverSocket = new ServerSocket(12345);
		System.out.println("A porta 12345 foi Aberta!");
		System.out.println("Servidor esperando receber mensagem de Cliente");
		
		// Aguardar solicitação de conexão de cliente
		Socket socket = serverSocket.accept();
		
		System.out.println("Cliente " + socket.getInetAddress().getHostAddress() + " conectado");
		
		// Definir stream de entrada e saida de dados do cliente.
		DataInputStream entrada = new DataInputStream(socket.getInputStream());
		DataOutputStream saida = new DataOutputStream(socket.getOutputStream());
		
		while(selecao) {				
			
			double numero = entrada.readDouble();
			if(numero != 0) {
				System.out.println("Numero digitado :" + numero);
				somaNumero += numero;
				saida.writeUTF("");
				
			}else {
				System.out.println("Fim de Processo");
				System.out.println("Somatorio :" + somaNumero);
				saida.writeUTF(String.valueOf(somaNumero));
				
				// Fechar streams de entrada e saida de dados
				saida.close();
				entrada.close();
				
				// Fechar sockets de comunicação e conexão
				socket.close();
				serverSocket.close();
				
				selecao = false;
			}			
		}
	}
}
