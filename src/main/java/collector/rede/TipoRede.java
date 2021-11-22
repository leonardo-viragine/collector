package collector.rede;

import java.util.Arrays;
import java.util.Optional;

import collector.rede.implementacoes.TwitterRede;

public enum TipoRede {

	TWITTER("Twitter") {
		@Override
		public Rede implementacao() {
			return new TwitterRede();
		}
	};

	private String descricao;

	TipoRede(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public abstract Rede implementacao();

	public static Rede implementacao(String descricao) {
		if (descricao == null) {
			return null;
		}
		Optional<TipoRede> optional = Arrays.stream(values())
				.filter(value -> (descricao.equalsIgnoreCase(value.descricao))).findFirst();
		if (!optional.isPresent()) {
			return null;
		}
		return optional.get().implementacao();
	}
}
