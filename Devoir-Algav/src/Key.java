import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Key {
	BigInteger key;
	
	public Key(BigInteger b) {
		key=b;
	}
	
	public Key(Key k) {
		key=new BigInteger(k.key.toString());
	}

	public BigInteger getKey() {
		return key;
	}

	public void setKey(BigInteger b) {
		this.key=b;
	}

	public static boolean inf(Key key1, Key key2) {
		BigInteger keyRep1 = key1.getKey();
		BigInteger keyRep2 = key2.getKey();
		
		if(keyRep1.compareTo(keyRep2)<0) {
				return true;
		}
		return false;
	}

	public static boolean eg(Key key1, Key key2) {
		BigInteger keyRep1 = key1.getKey();
		BigInteger keyRep2 = key2.getKey();
		
		if(keyRep1.compareTo(keyRep2)==0)
			return false;
		
		return true;
	}
	
	public static ArrayList<Key> getKeysFromFile(String pathKeyFile) throws Exception {
		ArrayList<Key> result = new ArrayList<Key>();
		List<String> lines = Files.readAllLines(Paths.get(pathKeyFile),Charset.defaultCharset());

		for (String line : lines) {
			BigInteger key = new BigInteger(line.substring(2), 16);

			result.add(new Key(key));
		}
		
		return result;
	}
	
	/*public static ArrayList<Key> getStringKeysFromFile(String pathKeyFile) throws Exception {
		ArrayList<Key> result = new ArrayList<Key>();
		List<String> lines = Files.readAllLines(Paths.get(pathKeyFile),Charset.defaultCharset());

		for (String line : lines) {
			BigInteger key = new BigInteger(line);

			result.add(new Key(key));
		}
		
		return result;
	}*/
}
