package collector;

import java.util.List;
import java.util.Scanner;

import collector.entidades.Consulta;
import collector.entidades.Resultado;
import collector.rede.TipoRede;

public class Main {
	public static void main(String[] args) throws Exception {
		System.out.println("entre com o termo ou expressao a ser buscada");
		Scanner sc = new Scanner(System.in);
		String termo = sc.nextLine();
		sc.close();
		
		Consulta consulta = new Consulta(termo);
		
		List<Resultado> resultados = TipoRede.implementacao("Twitter").busca(consulta);
		
		resultados.forEach(System.out::println);
		
	}
}