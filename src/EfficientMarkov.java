import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import java.util.*;

public class EfficientMarkov extends BaseMarkov{
	private Map myMap;
	/**
	 * Construct a EfficientMarkov object with
	 * the specified order
	 * @param order size of this markov generator
	 */
	public EfficientMarkov(int order)
	{
		super(order);
		myMap = new HashMap<String , ArrayList<String>>();
	}
	/**
	 * Default constructor has order 3
	 */
	public EfficientMarkov()
	{
		super();
		myMap = new HashMap<String , ArrayList<String>>();
	}

	@Override
	public void setTraining(String text) {
		myMap = new HashMap<String , ArrayList<String>>();
		myText = text;

		for (int i = 0; i < text.length()-myOrder; i++)
		{
			String key = text.substring(i, i+myOrder);
			if (!myMap.containsKey(key))
			{
				ArrayList<String> list = new ArrayList<String>();
				if (i+myOrder+1 < text.length())
				{
					list.add(text.substring(i+myOrder, i+myOrder+1));
					myMap.put(key, list);
				} else {
					list.add(PSEUDO_EOS);
					myMap.put(key, list);
					break;
				}
			} else {
				if (i+myOrder+1 < text.length())
				{
					((ArrayList<String>) myMap.get(key)).add(text.substring(i+myOrder, i+myOrder+1));
				} else {
					((ArrayList<String>) myMap.get(key)).add(PSEUDO_EOS);
				}

			}
			/*System.out.print(key + ": ");
			for (String s : ((ArrayList<String>) myMap.get(key)))
			{
				System.out.print(s + " ");
			}
			System.out.println();*/
		}
	}

	@Override
	public ArrayList<String> getFollows(String key){
		if (!myMap.containsKey(key))
		{
			throw new NoSuchElementException(key+" not in map");
		}
		return (ArrayList<String>) myMap.get(key);
	}

}
