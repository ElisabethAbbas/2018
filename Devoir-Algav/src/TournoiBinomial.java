import java.util.ArrayList;

public class TournoiBinomial {

	Key cle;
	ArrayList<TournoiBinomial> fils;
	
	
	public TournoiBinomial(Key cle){
		this.cle=cle;
		fils = new ArrayList<TournoiBinomial>();
	}
	
	public TournoiBinomial(TournoiBinomial T) {
		this.cle= new Key(cle);
		fils=new ArrayList<TournoiBinomial>();
		for(TournoiBinomial t : T.fils)
			fils.add(new TournoiBinomial(t));
	}
	
	public boolean EstVide() {
		return fils.isEmpty();
	}

	public int Degre() {
		return fils.size();
	}

	/* On suppose que les deux tournois ont le même degré. 
	 * Renvoie une erreur dans le cas contraire. */
	public static TournoiBinomial Union2Tid(TournoiBinomial t1, TournoiBinomial t2) {
		TournoiBinomial res=null;
		if(t1.Degre()==t2.Degre()) {
			if(Key.inf(t1.cle, t2.cle)) {
				res = new TournoiBinomial(t1.cle);
				res.fils.add(t2);
			}
			else {
				res = new TournoiBinomial(t2.cle);
				res.fils.add(t1);
			}
		}		
		else 
			System.err.println("Pas de la même taille");
		
		return res;
	}

	public FileBinomiale Decapite() {
		FileBinomiale res = new FileBinomiale();
		for(TournoiBinomial t : fils)
			res=FileBinomiale.Union(res, t.File());
		
		return res;
	}

	public FileBinomiale File() {
		return new FileBinomiale(this);
	}
}
