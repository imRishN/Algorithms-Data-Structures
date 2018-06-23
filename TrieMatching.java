import java.io.*;
import java.util.*;
public class TrieMatching implements Runnable {

	List <Integer> solve (String text, int n, String patterns[]) {
		List <Integer> result = new ArrayList <Integer> ();
		//trie construction
		List<Map<Character, Integer>> trie = new ArrayList<Map<Character, Integer>>();
		trie.add(new HashMap<Character,Integer>());
		Map<Character,Integer> currentNode;
		for(String pattern:patterns)
		{
			currentNode=trie.get(0);
			for(int i=0;i<pattern.length();i++)
			{
				char c=pattern.charAt(i);
				if(currentNode.containsKey(c))
				{
					currentNode=trie.get(currentNode.get(c));
				}
				else{
					trie.add(new HashMap<Character,Integer>());
					currentNode.put(c,trie.size()-1);
					currentNode=trie.get(trie.size()-1);
				}
			}
		}
		int i=0;
		while(i<text.length())
		{
			boolean isMatching = false;
			isMatching = prefixTrieMatching(text, trie, i);
			if(isMatching) result.add(i);
			i++;
		}
		return result;
	}

	boolean prefixTrieMatching(String text, List<Map<Character, Integer>> trie, int i)
	{
		char symbol=text.charAt(i);
		Map<Character, Integer> v=trie.get(0);
		while(true)
		{
			if(v.size()==0)
			{
				return true;
			}
			else if(v.containsKey(symbol) && i<text.length())
			{
				v=trie.get(v.get(symbol));
				if(++i<text.length())
					symbol=text.charAt(i);
			}
			else
				return false;
		}
	}
	public void run () {
		try {
			BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
			String text = in.readLine ();
			int n = Integer.parseInt (in.readLine ());
			String []patterns = new String[n];
			for (int i = 0; i < n; i++) {
				patterns[i]=in.readLine();
			}

			List <Integer> ans = solve (text, n, patterns);

			for (int j = 0; j < ans.size (); j++) {
				System.out.print ("" + ans.get (j));
				System.out.print (j + 1 < ans.size () ? " " : "\n");
			}
		}
		catch (Throwable e) {
			e.printStackTrace ();
			System.exit (1);
		}
	}

	public static void main (String [] args) {
		new Thread (new TrieMatching ()).start ();
	}
}
