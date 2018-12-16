import java.util.ArrayList;
import java.util.List;

public class FileBinomiale {
	ArrayList<TournoiBinomial> tournois;

	public FileBinomiale() {
		tournois=new ArrayList<TournoiBinomial>();
	}

	public FileBinomiale(FileBinomiale F) {
		tournois= new ArrayList<TournoiBinomial>();
		for(TournoiBinomial t : F.tournois) 
			tournois.add(new TournoiBinomial(t));
	}

	public FileBinomiale(TournoiBinomial t) {
		tournois=new ArrayList<TournoiBinomial>();
		tournois.add(t);
	}

	private void TriFusion(List<TournoiBinomial> liste) {
		if(liste.size()==0)
			return;
		else if(liste.size()==1)
			return;
		else if(liste.size()==2) {
			if(liste.get(0).Degre()>liste.get(1).Degre())
				liste.add(1, liste.remove(0));
		}
		else {
			TriFusion(liste.subList(0, (liste.size()-1)/2));
			TriFusion(liste.subList((liste.size()-1)/2+1, liste.size()-1));
		}
	}

	public FileBinomiale(ArrayList<TournoiBinomial> liste) {
		tournois=new ArrayList<TournoiBinomial>();
		TriFusion(tournois);
		tournois.addAll(liste);
	}

	public boolean EstVide() {
		return tournois.isEmpty();
	}

	public TournoiBinomial MinDeg() {
		return tournois.get(0);
	}

	public FileBinomiale Reste() {
		FileBinomiale f = new FileBinomiale(this);
		f.tournois.remove(0);
		return f;
	}

	public FileBinomiale AjoutMin(TournoiBinomial t) {
		FileBinomiale res =new FileBinomiale(this);
		res.tournois.add(0, t);
		return res;
	}

	public static FileBinomiale Union(FileBinomiale F1, FileBinomiale F2) {
		return UnionFret(F1, F2, null);
	}

	private static FileBinomiale UnionFret(FileBinomiale F1, FileBinomiale F2, TournoiBinomial T) {
		if(T==null) {
			if(F1.EstVide())
				return F2;
			if(F2.EstVide())
				return F1;

			TournoiBinomial T1 = F1.MinDeg();
			TournoiBinomial T2 = F2.MinDeg();

			FileBinomiale res;

			if(T1.Degre()<T2.Degre()) {
				res=Union(F1.Reste(), F2);
				res=res.AjoutMin(T1);
				return res;
			}

			else if(T1.Degre()>T2.Degre()) {
				res=Union(F2.Reste(), F1);
				res=res.AjoutMin(T2);
				return res;
			}

			// Les degrés de F1 et F2 sont égaux
			else 
				return UnionFret(F1.Reste(), F2.Reste(), TournoiBinomial.Union2Tid(T1, T2));
		}
		else {
			if(F1==null || F1.EstVide())
				return Union(T.File(), F2);
			if(F2==null || F2.EstVide())
				return Union(T.File(), F1);

			TournoiBinomial T1 = F1.MinDeg();
			TournoiBinomial T2 = F2.MinDeg();
			if(T.Degre() < T1.Degre() && T.Degre() < T2.Degre()) {
				FileBinomiale res = Union(F1, F2);
				res=res.AjoutMin(T);
				return res;
			}
			if(T.Degre() == T1.Degre() && T.Degre() == T2.Degre()) {
				FileBinomiale res = UnionFret(F1.Reste(), F2.Reste(), TournoiBinomial.Union2Tid(T1, T2));
				res=res.AjoutMin(T);
				return res;
			}
			if(T.Degre() == T1.Degre() && T.Degre() < T2.Degre()) {
				FileBinomiale res = UnionFret(F1.Reste(), F2, TournoiBinomial.Union2Tid(T1 , T));
				return res;
			}
			if(T.Degre() == T2.Degre() && T.Degre() < T1.Degre()) {
				FileBinomiale res = UnionFret(F2.Reste(), F1, TournoiBinomial.Union2Tid(T2 , T));
				return res;
			}
		}
		return null;
	}

	public static FileBinomiale Ajout(FileBinomiale f, Key k) {
		return Union((new TournoiBinomial(k)).File(), f);
	}

	public static FileBinomiale consIter(ArrayList<Key> elements) {
		long startTime = System.nanoTime(); 
		FileBinomiale f = new FileBinomiale();
		for(Key k : elements)
			f = Ajout(f, k);
		
		long endTime = System.nanoTime(); 

		System.out.println("temps d'execution: " + (endTime - startTime) + " NanoSecond"); 
		System.out.println("temps d'execution en moy: " + ((endTime - startTime)/elements.size()) + " NanoSecond");
		System.out.println("temps d'execution en moy: " + (((endTime - startTime)/elements.size())/1000000) + " ms");
		
		return f;
	}

	public FileBinomiale SupprMin() {
		TournoiBinomial tMin=null;
		FileBinomiale res=null;
		// on cherche la clé la plus faible
		for(TournoiBinomial t : tournois)
			if(tMin==null || Key.inf(t.cle, tMin.cle))
				tMin=t;
		
		// on ajoute le tournoi décapité de sa racine à la file binomiale
		res=tMin.Decapite();
		for(TournoiBinomial t : tournois) {
			if(tMin==t)
				continue;
			res=Union(res, t.File());
		}

		return res;
	}
	
	@Override
	public String toString() {
		String s = "";
		for(TournoiBinomial t : tournois) 
			s+="* tournoi degré "+t.Degre()+" : "+t.toString()+"\n";
		
		return "FB={{{\n"+s+"}}}";
	}
}
