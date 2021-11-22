package collector.rede;

import java.util.List;

import collector.entidades.Consulta;
import collector.entidades.Resultado;

public interface Rede {

	public List<Resultado> busca(Consulta consulta) throws Exception;
}
