import java.util.ArrayList;

public class TournoiBinomial {

	Key cle;
	ArrayList<TournoiBinomial> fils;
	int degre;
	
	
	public TournoiBinomial(Key cle){
		this.cle=cle;
		fils = new ArrayList<TournoiBinomial>();
		degre=0;
	}
	
	public TournoiBinomial(TournoiBinomial T) {
		this.cle= new Key(T.cle);
		fils=new ArrayList<TournoiBinomial>();
		for(TournoiBinomial t : T.fils)
			fils.add(new TournoiBinomial(t));
		degre=T.degre;
	}
	
	public boolean EstVide() {
		return fils.isEmpty();
	}

	public int Degre() {
		/*double d = 1;
		TournoiBinomial parcours=null;
		if(!fils.isEmpty())
			parcours = fils.get(fils.size()-1);
		else
			return 0;
		while(!parcours.EstVide()) {
			System.out.println(parcours.fils.get(0).fils);
			 parcours = parcours.fils.get(parcours.fils.size()-1);
			 d++;
		}
		
		System.out.println(d+" "+fils);
		return (int)(Math.log(d)/Math.log(2));*/
		return degre;
	}

	/* On suppose que les deux tournois ont le même degré. 
	 * Renvoie une erreur dans le cas contraire. */
	public static TournoiBinomial Union2Tid(TournoiBinomial t1, TournoiBinomial t2) {
		TournoiBinomial res=null;
		if(t1.Degre()==t2.Degre()) {
			if(Key.inf(t1.cle, t2.cle)) {
				res = new TournoiBinomial(t1);
				res.fils.add(t2);
			}
			else {
				res = new TournoiBinomial(t2);
				res.fils.add(t1);
			}
			//System.out.println("deg "+res.degre);
			res.degre++;
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
	
	@Override
	public String toString() {
		String s = "clé="+cle.getValueHexa().toString(16)+" ";
		for(TournoiBinomial t : fils)
			s+=t.toString();
		
		return s;
	}
}
