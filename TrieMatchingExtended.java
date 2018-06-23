import java.io.*;
import java.util.*;

class Node
{
	public static final int Letters =  4;
	public static final int NA      = -1;
	public int next [];
	public boolean patternEnd;
	Node ()
	{
		next = new int [Letters];
		Arrays.fill (next, NA);
		patternEnd = false;
	}
}

public class TrieMatchingExtended implements Runnable {
	int letterToIndex (char letter)
	{
		switch (letter)
		{
			case 'A': return 0;
			case 'C': return 1;
			case 'G': return 2;
			case 'T': return 3;
			default: assert (false); return Node.NA;
		}
	}

	List <Integer> solve (String text, int n, List <String> patterns) {
		List <Integer> result = new ArrayList <Integer> ();

		// Extended Trie for prefix strings
		List<Node> trie = new ArrayList<Node>();
		trie.add(new Node());
		Node currentNode;
		for(String pattern:patterns)
		{
			currentNode=trie.get(0);
			for(int i=0; i<pattern.length(); i++)
			{
				int letterIndex=letterToIndex(pattern.charAt(i));
				if(currentNode.next[letterIndex]!=-1)
				{
					currentNode=trie.get(currentNode.next[letterIndex]);
				}
				else {				
					Node nextNode= new Node();
					trie.add(nextNode);
					currentNode.next[letterIndex]=trie.size()-1;
					currentNode=nextNode;
				}
				if(i==pattern.length()-1)
				{
					currentNode.patternEnd=true;
				}
			}
		}

		int i=0;
		while(i<text.length())
		{
			boolean isMatching = false;
			isMatching = trieMatching(text, trie, i);
			if(isMatching) result.add(i);
			i++;
		}
		return result;
	}

	boolean trieMatching(String text, List<Node> trie, int i)
	{
		char symbol = text.charAt(i);
		int symbolIndex=letterToIndex(symbol);
		Node v = trie.get(0);
		while(true)
		{
			if((v.next[0]==-1&&v.next[1]==-1&v.next[2]==-1&&v.next[3]==-1) || v.patternEnd==true){
				return true;
			}
			else if(v.next[symbolIndex]!=-1 && i<text.length())
			{
				v = trie.get(v.next[symbolIndex]);
				if(++i<text.length())
				{
					symbolIndex=letterToIndex(text.charAt(i));
				}
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
			List <String> patterns = new ArrayList <String> ();
			for (int i = 0; i < n; i++) {
				patterns.add (in.readLine ());
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
		new Thread (new TrieMatchingExtended ()).start ();
	}
}
