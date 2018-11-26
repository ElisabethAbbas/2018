import java.util.ArrayList;

public class TournoiBinomial<Cle extends ICle> {

	Cle cle;
	ArrayList<TournoiBinomial<Cle>> fils;
	
	
	public TournoiBinomial(Cle cle){
		this.cle=cle;
		fils = new ArrayList<TournoiBinomial<Cle>>();
	}
	
	public TournoiBinomial(TournoiBinomial<Cle> t){
		this.cle=t.cle;
		this.fils=new ArrayList<TournoiBinomial<Cle>>();
		for(TournoiBinomial<Cle> e : fils)
			this.fils.add(new TournoiBinomial<Cle>((TournoiBinomial<Cle>)e));
	}
	
	public boolean EstVide() {
		return fils.isEmpty();
	}

	public int Degre() {
		return fils.size();
	}

	public void Union2Tid(TournoiBinomial<Cle> t) {
		if(t.Degre()==Degre())
			fils.add(t);
	}

	public FileBinomiale<Cle> Decapite() {
		TournoiBinomial<Cle> t =(TournoiBinomial<Cle>) fils.remove(fils.size()-1);		
		FileBinomiale<Cle> f = new FileBinomiale<Cle>(fils);
		fils.add(t);
		return f;
	}

	public FileBinomiale<Cle> File() {
		return new FileBinomiale<Cle>(this);
	}
}
