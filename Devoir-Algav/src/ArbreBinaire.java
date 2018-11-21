import java.util.ArrayList;

public class ArbreBinaire<Cle extends ICle> implements IArbreBinaire<Cle>{
	
	private Cle cle;
	private IArbreBinaire<Cle> gauche;
	private	IArbreBinaire<Cle> droite;
	private IArbreBinaire<Cle> pere;
	private int cles=0;
	
	public ArbreBinaire(IArbreBinaire<Cle> gauche, IArbreBinaire<Cle> droite, 
			Cle c, IArbreBinaire<Cle> pere) {
		this.cle=c;
		this.gauche=gauche;
		this.droite=droite;
		this.pere=pere;
		this.cles=1+gauche.cles()+droite.cles();
	}
	
	public ArbreBinaire() {
		this.cle=null;
		this.gauche=null;
		this.droite=null;
		this.cles=0;
	}
	
	@Override
	public Cle cle() {
		return cle;
	}
	
	@Override
	public int cles() {
		return cles;
	}
	
	@Override
	public IArbreBinaire<Cle> ag() {
		return gauche;
	}
	
	@Override
	public IArbreBinaire<Cle> ad() {
		return droite;
	}

	@Override
	public IArbreBinaire<Cle> pere() {
		return pere;
	}
	
	@Override
	public void cle(Cle c) {
		cle=c;
	}
	
	@Override
	public void filsGauche(IArbreBinaire<Cle> a) {
		a.pere(this);
		this.gauche=a;
	}
	
	@Override
	public void filsDroit(IArbreBinaire<Cle> a) {
		a.pere(this);
		this.droite=a;
	}
	
	@Override
	public void pere(IArbreBinaire<Cle> a) {
		pere=a;
	}
	
	/*public IArbreBinaire<Cle> min() {
		IArbreBinaire<Cle> min = this;
		IArbreBinaire<Cle> ag = ag();
		IArbreBinaire<Cle> ad = ad();
		if(ag!=null)
			if(ag.min().racine().compareTo(min.racine()) < 0)
				min=ag;
		if(ad!=null)
			if(ad.min().racine().compareTo(min.racine()) < 0)
				min=ad;
		
		return min;
	}*/
	
	public boolean aFilsGauche() {
		return ag()!=null;
	}
	
	public boolean aFilsDroit() {
		return ad()!=null;
	}
	
	public boolean estFilsDroit(){
		if(pere()==null)
			return false;
		if(pere().ag().equals(this))
			return false;
		if(pere().ad().equals(this))
			return true;
		
		return false;
	}
	
	public boolean estFilsGauche(){
		if(pere()==null)
			return false;
		if(pere().ad().equals(this))
			return false;
		if(pere().ag().equals(this))
			return true;
		
		return false;
	}	
	
	public IArbreBinaire<Cle> derniereCle(){
		IArbreBinaire<Cle> place=this;
		int n=(int)Math.floor(Math.log(this.cles())/Math.log(2));
		int i=n;
		int clestmp=cles;
		
		while(i>0) {
			if(clestmp<Math.pow(2, i)+Math.pow(2, i-1)) {
				place=place.ag();
				clestmp-=Math.pow(2, i-1);
			}
			else {
				place=place.ad();
				clestmp-=Math.pow(2, i);
			}
			i--;
		}
		
		return place;
	}
	
	public boolean Ajout(IArbreBinaire<Cle> a) {
		IArbreBinaire<Cle> place=place();
		
		if(!place.aFilsGauche())
			place.filsGauche(a);
		else if(!place.aFilsDroit())
			place.filsDroit(a);
		else
			return false;
		
		cles++;
		/* tri pour avoir un tas min */
		tri(a);
		
		return true;
	}
	
	public void tri(IArbreBinaire<Cle> a){
		while(a.pere()!=null && a.pere().cle().compareTo(a.cle())>0) {
			swap(a.pere(), a);
			a=a.pere();
		}
		while(a.aFilsGauche()) {
			if(a.ag().cle().compareTo(a.ad().cle())>0) {
				swap(a.ag(), a);
				a=a.ag();
			}
			else if(a.aFilsDroit() && a.ad().cle().compareTo(a.cle())>0) {
				swap(a.ad(), a);
				a=a.ad();
			}
			else {
				break;
			}
		}
	}
	
	public IArbreBinaire<Cle> place(){
		IArbreBinaire<Cle> place=this;
		int n=(int)Math.floor(Math.log(this.cles())/Math.log(2));
		// trouver une manière de faire le log2 plus rapidement et remplacer partout
		int i=n;
		int clestmp=cles+1;
		
		while(i>0) {
			if(clestmp<Math.pow(2, i)+Math.pow(2, i-1)) {
				place=place.ag();
				clestmp-=Math.pow(2, i-1);
			}
			else {
				place=place.ad();
				clestmp-=Math.pow(2, i);
			}
			i--;
		}
		return place;
	}
	
	public void Supprime(IArbreBinaire<Cle> a) {
		IArbreBinaire<Cle> derniereCle=derniereCle();
		
		swap(a, derniereCle);
		a.tri(a);
		
		if(derniereCle.estFilsGauche())
			derniereCle.pere().filsGauche(null);
		else if(derniereCle.estFilsDroit())
			derniereCle.pere().filsDroit(null);
		
		cles--;
	}
	
	public void consIter(ArrayList<Cle> liste) {
		int n=liste.size();
		int d=(int) Math.floor(Math.log(n)/Math.log(2));
		//int r=n-(int)Math.pow(2, d);
		
		ArrayList<IArbreBinaire<Cle>> arbres=new ArrayList<IArbreBinaire<Cle>>();
		ArrayList<IArbreBinaire<Cle>> priorite=new ArrayList<IArbreBinaire<Cle>>();
		IArbreBinaire<Cle> a, g, d;
		
		for(Cle c : liste) {
			a= new ArbreBinaire<Cle>(null, null, c, null);
			arbres.add(a);
		}

		a=arbres.remove(0);
		while(!arbres.isEmpty()) {
			g=arbres.remove(0);
			a.filsGauche(g);
			d=arbres.remove(0);
			a.filsDroit(d);
			a=a.ag();
			priorite.add(d);
			priorite.add(g);
			a=priorite.remove(0); 
			// on obtient le fils gauche normalement, à vérifier
		}
	}
	
	public void swap(IArbreBinaire<Cle> a, IArbreBinaire<Cle> b) {
		a.cle(b.cle());
		b.cle(a.cle());
	}
	
	IArbreBinaire<Cle> cle(int num){
		IArbreBinaire<Cle> res=this;
		int n=(int)Math.floor(Math.log(num)/Math.log(2));
		int i=n;
		int numtmp=num;
		
		while(i>0) {
			if(numtmp<Math.pow(2, i)+Math.pow(2, i-1)) {
				res=res.ag();
				numtmp-=Math.pow(2, i-1);
			}
			else {
				res=res.ad();
				numtmp-=Math.pow(2, i);
			}
			i--;
		}
		return res;
	}

	@Override
	public void SupprMin() {
		Supprime(this);
	}
}
