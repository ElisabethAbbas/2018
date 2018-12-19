import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Key {
	private BigInteger valueHexa;
	private String valueAscii;
	boolean isHexa;
	boolean isAscii;
	
	/* Crée un clé à partir d'un BigIntger. */
	public Key(BigInteger b) {
		isHexa=true;
		isAscii=false;
		valueHexa=b;
	}
	
	/* Crée un clé à partir d'une String. */
	public Key(String s) {
		isHexa=false;
		isAscii=true;
		valueAscii=s;
	}
	
	/* Constructeur par copie. */
	public Key(Key k) {
		if(isHexa) {
			valueHexa = new BigInteger(k.valueHexa.toString());
			isHexa=true;
			isAscii=false;
		}
		if(isAscii) {
			valueAscii= new String(k.valueAscii);
			isHexa=false;
			isAscii=true;
		}
	}

	/* Renvoie la clé. */
	public BigInteger getValueHexa() {
		return valueHexa;
	}

	/* Renvoie la clé. */
	public String getValueAscii() {
		return valueAscii;
	}
	
	/* Met à jour la clé. */
	public void setValue(BigInteger b) {
		this.valueHexa=b;
	}
	
	/* Met à jour la clé. */
	public void setValue(String s) {
		this.valueAscii=s;
	}

	/* Renvoie vrai si key1 est inférieure à key2. */
	public static boolean inf(Key key1, Key key2) {
		
		if(key1.isAscii && key2.isAscii)
			return key1.getValueAscii().compareTo(key2.getValueAscii())<0;
		if(key1.isHexa && key2.isHexa)
			return key1.getValueHexa().compareTo(key2.getValueHexa())<0;
		
		return false;
	}

	/* Renvoie vrai si key1 est égale à key2. */
	public static boolean eg(Key key1, Key key2) {		
		if(key1.isAscii && key2.isAscii)
			return key1.getValueAscii().compareTo(key2.getValueAscii())==0;
		if(key1.isHexa && key2.isHexa)
			return key1.getValueHexa().compareTo(key2.getValueHexa())==0;
		
		return false;
	}
	
	public static ArrayList<Key> getHexaKeysFromFile(String pathKeyFile) throws Exception {
		ArrayList<Key> result = new ArrayList<Key>();
		List<String> lines = Files.readAllLines(Paths.get(pathKeyFile),Charset.defaultCharset());
		BigInteger key;
		Key k;
		
		/* Pour toutes les lignes, on crée un BigInteger à partir des 
		 * nombres hexadécimaux sans le 0x. */
		for (String line : lines) {
			key = new BigInteger(line.substring(2), 16);

			k=new Key(key);
			result.add(k);
		}
		
		return result;
	}
	
	public static ArrayList<Key> getStringKeysFromFile(String pathKeyFile) throws Exception {
		ArrayList<Key> result = new ArrayList<Key>();
		List<String> lines = Files.readAllLines(Paths.get(pathKeyFile),Charset.defaultCharset());
		Key k;
		
		/* Pour toutes les lignes, on crée un BigInteger à partir des 
		 * nombres hexadécimaux sans le 0x. */
		for (String line : lines) {
			k = new Key(line);
			result.add(k);
		}
		
		return result;
	}
	
	@Override
	public boolean equals(Object k) {
		if(k instanceof Key)
			return Key.eg(this, (Key)k);
		
		return false;
	}
	
	@Override
	public String toString() {
		if(isAscii) 
			return valueAscii;
		else if(isHexa)
			return valueHexa.toString(16);

		return "either ascii or hexa, cannot display.";
	}
	
	@Override
	public int hashCode() {
		if(isAscii)
			return getValueAscii().hashCode();
		if(isHexa)
			return getValueHexa().hashCode();
		
		else return super.hashCode();
	}
}
