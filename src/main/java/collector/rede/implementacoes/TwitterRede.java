package collector.rede.implementacoes;

import java.util.List;
import java.util.stream.Collectors;

import collector.entidades.Consulta;
import collector.entidades.Resultado;
import collector.rede.Rede;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterRede implements Rede {

	public List<Resultado> busca(Consulta consulta) throws Exception {
		Twitter twitter = getClient();
		Query query = new Query(consulta.getTermo());
		QueryResult result = twitter.search(query);

		List<Resultado> resultados = result.getTweets().stream().map(s -> montarResultado(s))
				.collect(Collectors.toList());

		return resultados;
	}

	private Resultado montarResultado(Status s) {
		return new Resultado(String.valueOf(s.getId()), s.getUser().getName(), s.getText(), s.getCreatedAt());
	}

	private Twitter getClient() {
		ConfigurationBuilder cb = new ConfigurationBuilder();

		cb.setDebugEnabled(false).setTweetModeExtended(true).setHttpRetryCount(5).setHttpRetryIntervalSeconds(5)
				.setHttpConnectionTimeout(18000).setHttpReadTimeout(100000)
				.setOAuthAccessToken(System.getProperty("twitter_accesstoken"))
				.setOAuthAccessTokenSecret(System.getProperty("twitter_accesstoken_secret"))
				.setOAuthConsumerKey(System.getProperty("twitter_consumerkey"))
				.setOAuthConsumerSecret(System.getProperty("twitter_consumerkey_secret"));

		return new TwitterFactory(cb.build()).getInstance();
	}
}
