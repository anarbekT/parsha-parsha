package web.app.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import web.app.entities.QueryWord;

@Service
@Transactional
public class StemmerImpl implements Stemmer {

	@Override
	public String removeStopWords(String text) {
		WordHandler stopWords = new WordHandler();

		String withOutStopWords = stopWords.removeStopWords(text);
		return withOutStopWords;
	}

	@SuppressWarnings("resource")
	@Override
	public List<QueryWord> getWords(String text) {
		List<QueryWord> words = new ArrayList<>();
		Scanner scan = new Scanner(text);
		while (scan.hasNext()) {
			String word = scan.next();
			// word = word.replaceAll("[^\\p{L}\\p{Nd}\\s]", "");
			QueryWord query = new QueryWord();
			query.setInitialWord(word);
			words.add(query);
		}
		return words;
	}

	public List<QueryWord> removeSuffixes1(List<QueryWord> words) {
		List<QueryWord> withoutSuffixesWords = new ArrayList<>();
		WordHandler wordHandler = new WordHandler();

		for (QueryWord query : words) {
			String word = query.getInitialWord();
			// String withoutSuffixes = wordHandler.removeZhiktikZhalgau(word);
			// withoutSuffixes =
			// wordHandler.removeSeptikZhalgau(withoutSuffixes);
			// // тауелдікті алып тастағаннан кейін сөз 3 тен аз болса онда
			// // кайтадан орнына келтіреміз
			// withoutSuffixes =
			// wordHandler.removeTaueldikZhalgau(withoutSuffixes);
			// withoutSuffixes =
			// wordHandler.removeKoptikZhalgau(withoutSuffixes);
			String withoutSuffixes = wordHandler.removeSeptikZhalgau(word);
			withoutSuffixes = wordHandler.removeTaueldikZhalgau(withoutSuffixes);
			withoutSuffixes = wordHandler.removeZhiktikZhalgau(withoutSuffixes);
			withoutSuffixes = wordHandler.removeKoptikZhalgau(withoutSuffixes);

			query.getStemOfWord().add(withoutSuffixes);

			withoutSuffixesWords.add(query);
		}
		return withoutSuffixesWords;
	}

	@Override
	public List<QueryWord> removeSuffixes(List<QueryWord> words) {
		List<QueryWord> withoutSuffixesWords = new ArrayList<>();
		WordHandler wordHandler = new WordHandler();

		for (QueryWord query : words) {
			String word = query.getInitialWord();
			//////// SEPTIK -> TAUELDIK -> KOPTIK////////
			String withoutSuffixes = wordHandler.removeSeptikZhalgau(word);
			if (withoutSuffixes != null) {
				word = withoutSuffixes;
				withoutSuffixes = wordHandler.removeTaueldikZhalgau(word);
				if (withoutSuffixes != null) {
					word = withoutSuffixes;
					withoutSuffixes = wordHandler.removeKoptikZhalgau(word);
				}
				query.getStemOfWord().add(word);
				withoutSuffixesWords.add(query);
				continue;
			}
			//////// ZHIKTIK -> TAUELDIK -> KOPTIK////////
			withoutSuffixes = wordHandler.removeZhiktikZhalgau(word);
			if (withoutSuffixes != null) {
				word = withoutSuffixes;
				withoutSuffixes = wordHandler.removeTaueldikZhalgau(word);
				if (withoutSuffixes != null) {
					word = withoutSuffixes;
					withoutSuffixes = wordHandler.removeKoptikZhalgau(word);
				}
				query.getStemOfWord().add(word);
				withoutSuffixesWords.add(query);
				continue;
			}
			//////// KOPTIK -> ZHIKTIK -> TAUELDIK -> KOPTIK////////
			withoutSuffixes = wordHandler.removeKoptikZhalgau(word);
			if (withoutSuffixes != null) {
				word = withoutSuffixes;
				withoutSuffixes = wordHandler.removeZhiktikZhalgau(word);
				if (withoutSuffixes != null) {
					word = withoutSuffixes;
					withoutSuffixes = wordHandler.removeTaueldikZhalgau(word);
					if (withoutSuffixes != null) {
						word = withoutSuffixes;
						withoutSuffixes = wordHandler.removeKoptikZhalgau(word);
					} else {
						word = withoutSuffixes;
						withoutSuffixes = wordHandler.removeKoptikZhalgau(word);
					}
				}
				query.getStemOfWord().add(word);
				withoutSuffixesWords.add(query);
				continue;
			}
			//////// TAUELDIK -> KOPTIK////////
			withoutSuffixes = wordHandler.removeTaueldikZhalgau(word);
			if (withoutSuffixes != null) {
				word = withoutSuffixes;
				withoutSuffixes = wordHandler.removeKoptikZhalgau(withoutSuffixes);
				if (withoutSuffixes != null) {
					word = withoutSuffixes;
				}
				query.getStemOfWord().add(word);
				withoutSuffixesWords.add(query);
				continue;
			}
			
			//////////WITHOUT AFFIX////////////
			query.getStemOfWord().add(word);
			withoutSuffixesWords.add(query);
			continue;
		}
		return withoutSuffixesWords;
	}

}
