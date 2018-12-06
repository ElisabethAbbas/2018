import java.math.BigInteger;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		/*try {
			//System.out.println(ArbreBinaire.consIter(Key.getKeysFromFile2("cles_alea/jeu_1_nb_cles_100.txt")));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		ArrayList<Key> l = new ArrayList<Key>();
		l.add(new Key(new BigInteger("e28429", 16))); 
		l.add(new Key(new BigInteger("a28429", 16))); 
		l.add(new Key(new BigInteger("128429", 16))); 
		l.add(new Key(new BigInteger("328429", 16))); 
		l.add(new Key(new BigInteger("428429", 16))); 
		l.add(new Key(new BigInteger("428421", 16))); 
		
		ArbreBinaire a = ArbreBinaire.consIter(l);
		
		ArbreBinaire b = new ArbreBinaire();
		b.Ajout(new Key(new BigInteger("528429", 16)));
		b.Ajout(new Key(new BigInteger("538429", 16)));
		b.Ajout(new Key(new BigInteger("8429", 16)));
		b.Ajout(new Key(new BigInteger("54438429", 16)));
		b.SupprMin();
		System.out.println(b);
		System.out.println(a);
		System.out.println(ArbreBinaire.union(a, b));
	}
}
