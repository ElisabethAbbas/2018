import java.util.ArrayList;

public class FileBinomiale<Cle extends ICle> {
	ArrayList<TournoiBinomial<Cle>> tournois;

	public FileBinomiale() {
		tournois=new ArrayList<TournoiBinomial<Cle>>();
	}
	
	public FileBinomiale(TournoiBinomial<Cle> t) {
		tournois=new ArrayList<TournoiBinomial<Cle>>();
		tournois.add(t);
	}
	
	public FileBinomiale(ArrayList<TournoiBinomial<Cle>> liste) {
		tournois=new ArrayList<TournoiBinomial<Cle>>();
		tournois.addAll(liste);
	}
	
	public boolean EstVide() {
		return tournois.isEmpty();
	}

	public TournoiBinomial<Cle> MinDeg() {
		return tournois.get(0);
	}

	public void Reste() {
		tournois.remove(0);
	}

	public void AjoutMin(TournoiBinomial<Cle> t) {
		tournois.add(0, t);
	}
	
}
