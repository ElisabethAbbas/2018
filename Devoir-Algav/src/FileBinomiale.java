import java.util.ArrayList;
import java.util.List;

public class FileBinomiale {
	ArrayList<TournoiBinomial> tournois;

	/* Crée une file binomiale vide. */
	public FileBinomiale() {
		tournois=new ArrayList<TournoiBinomial>();
	}

	/* Constructeur par copie. */
	public FileBinomiale(FileBinomiale F) {
		tournois= new ArrayList<TournoiBinomial>();
		for(TournoiBinomial t : F.tournois) 
			tournois.add(new TournoiBinomial(t));
	}

	/* Construit une file binomiale à partir d'un tournoi. */
	public FileBinomiale(TournoiBinomial t) {
		tournois=new ArrayList<TournoiBinomial>();
		tournois.add(t);
	}

	/* Trie une liste de tournois binomiaux, du plus grand au plus petit. */
	private void TriFusion(List<TournoiBinomial> liste) {
		/* Si la liste est vide. */
		if(liste.size()==0)
			return;
		/* Si la liste contient un seul élément. */
		else if(liste.size()==1)
			return;
		/* Si la liste contient 2 éléments */
		else if(liste.size()==2) {
			if(liste.get(0).Degre()<liste.get(1).Degre())
				liste.add(liste.remove(0));
		}
		/* Sinon on divise la liste en 2 et on applique le tri fusion sur chacune 
		 * des sous-listes. */
		else {
			TriFusion(liste.subList(0, (liste.size()-1)/2));
			TriFusion(liste.subList((liste.size()-1)/2+1, liste.size()-1));
		}
	}

	/* Crée une file binomiale à partir d'une liste de tournois binomiaux. */
	public FileBinomiale(ArrayList<TournoiBinomial> liste) {
		tournois=new ArrayList<TournoiBinomial>();
		TriFusion(tournois);
		tournois.addAll(liste);
	}

	/* Renvoie vrai si la file binomiale est vide. */
	public boolean EstVide() {
		return tournois.isEmpty();
	}

	/* Renvoie le tournoi de degré minimum. */
	public TournoiBinomial MinDeg() {
		return tournois.get(tournois.size()-1);
	}

	/* On supprime l'élément le plus petit. */
	public FileBinomiale Reste() {
		this.tournois.remove(tournois.size()-1);
		return this;
	}

	/* On ajoute le tournoi binomial t, considéré comme plus petit que les 
	 * autres déjà présents. */
	public FileBinomiale AjoutMin(TournoiBinomial t) {
		this.tournois.add(t);
		return this;
	}

	/* Renvoie la file binomiale qui est l'union de la file binomiale F1 et
	 * de la file binomiale F2. */
	public static FileBinomiale Union(FileBinomiale F1, FileBinomiale F2) {
		return UnionFret(F1, F2, null);
	}

	/* Renvoie la file binomiale qui est l'union de la file binomiale F1 et
	 * de la file binomiale F2, avec le tournoi binomial T en retenu. */
	private static FileBinomiale UnionFret(FileBinomiale F1, FileBinomiale F2, TournoiBinomial T) {
		/* S'il n'y a pas de retenue. */
		if(T==null) {
			/*Si une des deux files est vide, on renvoie l'autre. */
			if(F1.EstVide())
				return F2;
			if(F2.EstVide())
				return F1;

			TournoiBinomial T1 = F1.MinDeg();
			TournoiBinomial T2 = F2.MinDeg();

			FileBinomiale res;

			/* Si le degré de tournois minimum de F1 est plus petit que le 
			 * degré du tournoi minimum de F2 : */
			if(T1.Degre()<T2.Degre()) {
				res=Union(F1.Reste(), F2);
				res.AjoutMin(T1);
				return res;
			}
			
			/* Si le degré de tournois minimum de F2 est plus petit que le 
			 * degré du tournoi minimum de F1 : */
			else if(T1.Degre()>T2.Degre()) {
				res=Union(F2.Reste(), F1);
				res.AjoutMin(T2);
				return res;
			}

			/* Les degrés de T1 et T2 sont égaux, on les fusionne. */
			else 
				return UnionFret(F1.Reste(), F2.Reste(), TournoiBinomial.Union2Tid(T1, T2));
		}
		/* S'il y a une retenue. */
		else {
			/* Si une des deux files est vide. */
			if(F1==null || F1.EstVide())
				return Union(T.File(), F2);
			if(F2==null || F2.EstVide())
				return Union(T.File(), F1);

			TournoiBinomial T1 = F1.MinDeg();
			TournoiBinomial T2 = F2.MinDeg();
			
			/* Si T est plus petit que les deux tournois minimaux,   
			 * On On fait l'union des deux files et on ajoute T au résultat. */
			if(T.Degre() < T1.Degre() && T.Degre() < T2.Degre()) {
				FileBinomiale res = Union(F1, F2);
				res=res.AjoutMin(T);
				return res;
			}
			/* Si les deux tournois minimaux et la retenue ont le même degré.  
			 * On en fusionne deux et on ajoute au résultat le dernier. */
			if(T.Degre() == T1.Degre() && T.Degre() == T2.Degre()) {
				FileBinomiale res = UnionFret(F1.Reste(), F2.Reste(), TournoiBinomial.Union2Tid(T1, T2));
				res=res.AjoutMin(T);
				return res;
			}
			
			/* Si T est de même degré que T1, on les fusionne et on met le 
			 * résultat en retenue. */
			if(T.Degre() == T1.Degre() && T.Degre() < T2.Degre()) {
				FileBinomiale res = UnionFret(F1.Reste(), F2, TournoiBinomial.Union2Tid(T1 , T));
				return res;
			}
			
			/* Si T est de même degré que T2, on les fusionne et on met le 
			 * résultat en retenue. */
			if(T.Degre() == T2.Degre() && T.Degre() < T1.Degre()) {
				FileBinomiale res = UnionFret(F2.Reste(), F1, TournoiBinomial.Union2Tid(T2 , T));
				return res;
			}
		}
		return null;
	}

	/* Un ajout est l'union avec une file binomiale contenant le tournoi à 
	 * ajouter. */
	public static FileBinomiale Ajout(FileBinomiale f, Key k) {
		return Union((new TournoiBinomial(k)).File(), f);
	}

	/* Crée une file binomiale à partir d'une liste de clés. */
	public static FileBinomiale consIter(ArrayList<Key> elements) {
		FileBinomiale f = new FileBinomiale();
		for(Key k : elements)
			f = Ajout(f, k);
		
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
