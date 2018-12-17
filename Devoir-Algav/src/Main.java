import java.math.BigInteger;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		/*try {
			System.out.println(ArbreBinaire.consIter(Key.getKeysFromFile2("cles_alea/jeu_1_nb_cles_10000.txt")));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		/*try {
			System.out.println(ArbreBinaire.consIter(Key.getKeysFromFile("cles_alea/jeu_1_nb_cles_10000.txt")));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		String pathKeyFile=System.getProperty("user.dir")+"\\"+"cles_alea"+"\\";
		String pathKeyFile1 = pathKeyFile+ "jeu_1_nb_cles_5000.txt";
		String pathKeyFile2 =pathKeyFile+ "jeu_2_nb_cles_5000.txt";
		//String pathKeyFile3 =pathKeyFile+ "\\test.txt";

		ArrayList<Key> keys;		
		ArrayList<Key> keys2;
		ArrayList<Key> keys3;
		
		try {
			keys=Key.getKeysFromFile(pathKeyFile1);
			keys2=Key.getKeysFromFile(pathKeyFile2);
			//keys3=Key.getKeysFromFile(pathKeyFile3);
			
			/*FileBinomiale f = FileBinomiale.consIter(keys);
			FileBinomiale g = FileBinomiale.consIter(keys2);
			FileBinomiale h = FileBinomiale.consIter(keys3);*/
			
			TasMinArbreBinaire fa;// = ArbreBinaire.consIter(keys);
			/*ArbreBinaire ga = ArbreBinaire.consIter(keys2);
			ArbreBinaire ha = ArbreBinaire.consIter(keys3);*/
			
			
			double res = 0;
			
			/*for(double i=0; i<100; i++) {
				res=res*i/(i+1);
				res += ComplexiteArbreBinaire.union(TasMinArbreBinaire.consIter(keys), TasMinArbreBinaire.consIter(keys2))/(i+1);
			}
			
			System.out.println(res/1000000);
			*/
			System.out.println(TasMinArbreBinaire.consIter(keys));
			
			//System.out.println(FileBinomiale.consIter(keys).toString());
			
			/*System.out.println(ComplexiteFileBinomiale.consIter(keys3)/1000000);
			System.out.println(ComplexiteFileBinomiale.consIter(keys3)/1000000);
			System.out.println(ComplexiteFileBinomiale.consIter(keys3)/1000000);*/

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/*ArrayList<Key> l = new ArrayList<Key>();
		l.add(new Key(new BigInteger("7", 16))); 
		l.add(new Key(new BigInteger("5", 16))); 
		l.add(new Key(new BigInteger("13", 16))); 
		l.add(new Key(new BigInteger("8", 16))); 
		l.add(new Key(new BigInteger("14", 16))); 
		l.add(new Key(new BigInteger("12", 16)));
		l.add(new Key(new BigInteger("10", 16)));
		l.add(new Key(new BigInteger("6", 16)));
		l.add(new Key(new BigInteger("15", 16)));
		l.add(new Key(new BigInteger("2", 16)));
		
		ArbreBinaire a = ArbreBinaire.consIter(l);
	
		System.out.println(a);*/
		
		//Key.displayArrayOfKeys(keys);
		/*ArrayList<Key> l = new ArrayList<Key>();
		l.add(new Key(new BigInteger("e28429", 16))); 
		l.add(new Key(new BigInteger("a28429", 16))); 
		l.add(new Key(new BigInteger("128429", 16))); 
		l.add(new Key(new BigInteger("328429", 16))); 
		l.add(new Key(new BigInteger("428429", 16))); 
		l.add(new Key(new BigInteger("428421", 16)));
		
		FileBinomiale a = FileBinomiale.consIter(l);
		System.out.println(a);
		
		FileBinomiale b = new FileBinomiale();
		b = FileBinomiale.Ajout(b, new Key(new BigInteger("528429", 16)));
		b = FileBinomiale.Ajout(b, new Key(new BigInteger("538429", 16)));
		b = FileBinomiale.Ajout(b, new Key(new BigInteger("8429", 16)));
		b = FileBinomiale.Ajout(b, new Key(new BigInteger("54438429", 16)));
		b=b.SupprMin();
		System.out.println(b);
		
		
		System.out.println(FileBinomiale.Union(a, b));
		
		System.out.println(new Key(new BigInteger("528429", 16)));*/
		
		
		String fff = new BigInteger("528429", 10).toString(16);
		
	}
}
