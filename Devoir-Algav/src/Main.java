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
		String pathKeyFile1 = pathKeyFile+ "jeu_1_nb_cles_100.txt";
		String pathKeyFile2 = pathKeyFile+ "jeu_2_nb_cles_100.txt";
		
		ArrayList<Key> keys;		
		ArrayList<Key> keys2;
		
		try {
			keys=Key.getHexaKeysFromFile(pathKeyFile1);
			keys2=Key.getHexaKeysFromFile(pathKeyFile2);
			double res = 0;
			//String[] nb_cles = {"100", "1000", "10000", "200", "20000", "500", "5000", "50000"};
			pathKeyFile=System.getProperty("user.dir")+"\\"+"cles_alea"+"\\";
			//int k=0;

			String n=args[0];
			String f1=args[1];
			String f2=args[2];
			
			if(args[1].equals(args[2]))
				return;
			
			pathKeyFile1 = pathKeyFile+ "jeu_"+f1+"_nb_cles_"+n+".txt";
			pathKeyFile2 = pathKeyFile+ "jeu_"+f2+"_nb_cles_"+n+".txt";
			keys=Key.getHexaKeysFromFile(pathKeyFile1);
			keys2=Key.getHexaKeysFromFile(pathKeyFile2);
				
	
			//res = ComplexiteTasMinArbreBinaire.Union(TasMinArbreBinaire.consIter(keys), TasMinArbreBinaire.consIter(keys2));

			
			res = ComplexiteTasMinArbreBinaire.consIter(keys);

			System.out.print((res/1000000)+",");
			
			
			//System.out.println(new Key(new String("bla")).equals(new Key((new String("bla")))));
			//System.out.println(res/1000000);
			//TasMinArbreBinaire r=TasMinArbreBinaire.Union(TasMinArbreBinaire.consIter(keys), TasMinArbreBinaire.consIter(keys2));
			//System.out.println(r);
			/*System.out.println(r.getLeftChild().getLeftChild().getKey().getValueAscii());
			System.out.println(r.getLeftChild().getLeftChild().getLeftChild().getKey().getValueAscii());
			System.out.println(r.getLeftChild().getLeftChild().getKey().equals(r.getLeftChild().getLeftChild().getLeftChild().getKey()));
			*/
			//HashSet<Key> h = new HashSet<Key>();
			
			//h.add(r.getLeftChild().getLeftChild().getKey());
			
			
			//System.out.println(TasMinArbreBinaire.consIter(keys));
			
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
		
	}
}
