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
	public List<QueryWord> getWordsArray(String text) {
		List<QueryWord> words = new ArrayList<>();
		Scanner scan = new Scanner(text);
		while (scan.hasNext()) {
			String word = scan.next();
			word = word.replaceAll("[^a-zA-Z0-9]", ""); 
			QueryWord query = new QueryWord();
			query.setWord(word);
			words.add(query);
		}
		return words;
	}

	@Override
	public List<QueryWord> generateAllPossibleVariants(List<QueryWord> words) {
		List<QueryWord> allOptions = new ArrayList<>();
		for (int i = 1; i <= words.size(); i++) {
			List<QueryWord> result = getAllLists(words, i);
			allOptions.addAll(result);
		}
		return allOptions;
	}

	private ArrayList<QueryWord> getAllLists(List<QueryWord> words, int numOfWords) {
		// initialize our returned list
		ArrayList<QueryWord> allList = new ArrayList<>();

		// lists of length 1 are just the original elements
		if (numOfWords == 1)
			return (ArrayList<QueryWord>) words;
		else {
			// the recursion--get all lists of length 3, length 2, all the way
			// up to 1
			ArrayList<QueryWord> allSublists = getAllLists(words, numOfWords - 1);

			for (int i = 0; i < words.size(); i++) {
				for (int j = 0; j < allSublists.size(); j++) {
					// add the newly appended combination to the list

					if (words.get(i).getWord().equals(allSublists.get(j).getWord())
							|| allSublists.get(j).getWord().contains(words.get(i).getWord())) {
						continue;
					}
					QueryWord query = new QueryWord();
					query.setWord(words.get(i).getWord() + " " + allSublists.get(j).getWord());
					allList.add(query);
				}
			}
			return allList;
		}
	}

	@Override
	public List<QueryWord> removeSuffixes(List<QueryWord> words) {
		List<QueryWord> withoutSuffixesWords = new ArrayList<>();
		WordHandler wordHandler = new WordHandler();

		for (QueryWord query : words) {
			QueryWord tempQuery = new QueryWord();
			String word = query.getWord();
			String withoutSuffixes = wordHandler.removeZhiktikZhalgau(word);
			withoutSuffixes = wordHandler.removeSeptikZhalgau(withoutSuffixes);
			// тауелдікті алып тастағаннан кейін сөз 3 тен аз болса онда кайтадан орнына келтіреміз
			withoutSuffixes = wordHandler.removeTaueldikZhalgau(withoutSuffixes);
			withoutSuffixes = wordHandler.removeKoptikZhalgau(withoutSuffixes);

			tempQuery.setWord(withoutSuffixes);

			withoutSuffixesWords.add(tempQuery);
		}
		return withoutSuffixesWords;
	}

}
