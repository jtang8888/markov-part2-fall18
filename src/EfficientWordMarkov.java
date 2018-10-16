import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class EfficientWordMarkov extends BaseWordMarkov{
	Map myMap;
	/**
	 * Construct a EfficientWordMarkov object with
	 * the specified order
	 * @param order size of this markov generator
	 */
	EfficientWordMarkov(int order)
	{
		super(order);
		myMap = new HashMap<WordGram , ArrayList<String>>();
	}
	/**
	 * Default constructor has order 3
	 */
	EfficientWordMarkov()
	{
		super();
		myMap = new HashMap<WordGram , ArrayList<String>>();
	}
	
	@Override
	public void setTraining(String text) {
		myWords = text.split("\\s+");
		myMap = new HashMap<String , ArrayList<String>>();
		
		for (int i = 0; i < myWords.length-myOrder; i++)
		{
			WordGram key = new WordGram(myWords, i, myOrder);
			if (!myMap.containsKey(key))
			{
				ArrayList<String> list = new ArrayList<String>();
				if (i+myOrder+1 < myWords.length-1)
				{
					list.add(myWords[i+myOrder+1]);
					myMap.put(key, list);
				} else {
					list.add(PSEUDO_EOS);
					myMap.put(key, list);
				}
			} else {
				if (i+myOrder+1 < myWords.length-1)
				{
					((ArrayList<String>) myMap.get(key)).add(myWords[i+myOrder+1]);
				} else {
					((ArrayList<String>) myMap.get(key)).add(PSEUDO_EOS);
				}
			}
		}
	}
	
	@Override
	public ArrayList<String> getFollows(WordGram key){
		if (!myMap.containsKey(key))
		{
			throw new NoSuchElementException(key+" not in map");
		}
		return (ArrayList<String>) myMap.get(key);
	}
}
