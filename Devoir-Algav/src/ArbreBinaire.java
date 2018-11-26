import java.util.ArrayList;

public class ArbreBinaire<Cle extends ICle> implements IArbreBinaire<Cle>{
	
	private Cle cle;
	private IArbreBinaire<Cle> gauche;
	private	IArbreBinaire<Cle> droite;
	private IArbreBinaire<Cle> pere;
	private int cles=0;
	/*private ArrayList<IArbreBinaire<Cle>> places;
	private IArbreBinaire<Cle> derniereCle;*/
	
	public ArbreBinaire(IArbreBinaire<Cle> gauche, IArbreBinaire<Cle> droite, 
			Cle c, IArbreBinaire<Cle> pere) {
		this.cle=c;
		this.gauche=gauche;
		this.droite=droite;
		this.pere=pere;
		this.cles=1+gauche.cles()+droite.cles();
		/*this.places=new ArrayList<IArbreBinaire<Cle>>();
		this.places.add(gauche);
		this.places.add(droite);
		this.derniereCle=this;*/
	}
	
	public ArbreBinaire() {
		this.cle=null;
		this.gauche=null;
		this.droite=null;
		this.cles=0;
		/*this.places=new ArrayList<IArbreBinaire<Cle>>();
		this.places.add(this);
		this.derniereCle=null;*/
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
		IArbreBinaire<Cle> derniereCle=this;
		int n=(int)Math.floor(Math.log(this.cles())/Math.log(2));
		int i=n;
		int clestmp=cles;
		
		while(i>0) {
			if(clestmp<Math.pow(2, i)+Math.pow(2, i-1)) {
				derniereCle=derniereCle.ag();
				clestmp-=Math.pow(2, i-1);
			}
			else {
				derniereCle=derniereCle.ad();
				clestmp-=Math.pow(2, i);
			}
			i--;
		}
		
		return derniereCle;
	}
	
	/*public boolean Ajout(IArbreBinaire<Cle> a) {
		IArbreBinaire<Cle> placeVide=placeVide();
		
		if(!placeVide.aFilsGauche())
			placeVide.filsGauche(a);
		else if(!placeVide.aFilsDroit())
			placeVide.filsDroit(a);
		else
			return false;
		
		cles++;*/
		/* tri pour avoir un tas min */
		/*tri(a);
		
		return true;
	}*/
	
	public boolean Ajout(IArbreBinaire<Cle> a) {
		IArbreBinaire<Cle> placeVide=placeVide();//places.remove(0);
		
		if(!placeVide.aFilsGauche())
			placeVide.filsGauche(a);
		else if(!placeVide.aFilsDroit())
			placeVide.filsDroit(a);
		else
			return false;
		
		cles++;
		/* tri pour avoir un tas min */
		tri(a);
		
		return true;
	}
	
	public void triEnRemontant(IArbreBinaire<Cle> a) {
		while(a.pere()!=null && a.pere().cle().compareTo(a.cle())>0) {
			swap(a.pere(), a);
			a=a.pere();
		}
	}
	
	/*private void unionRec(IArbreBinaire<Cle> res, ArrayList<IArbreBinaire<Cle>> reste) {
		IArbreBinaire<Cle> min=null;

		if(reste.isEmpty())
			return;
		
		for(IArbreBinaire<Cle> e : reste)
			if(e.cle().compareTo(min.cle())<0 || min==null)
				min=e;
		
		reste.remove(min);
		// On veut que le fils gauche soit traité avant le fils droit :
		if(min.aFilsGauche())
			reste.add(min.ag());
		if(min.aFilsDroit())
			reste.add(min.ad());
		
		
		res.filsGauche(new ArbreBinaire<Cle>(null, null, min.cle(), res));
		
		
		min=null;
		if(reste.isEmpty())
			return;
		
		for(IArbreBinaire<Cle> e : reste)
			if(e.cle().compareTo(min.cle())<0 || min==null)
				min=e;
		
		reste.remove(min);
		// On veut que le fils gauche soit traité avant le fils droit :
		if(min.aFilsGauche())
			reste.add(min.ag());
		if(min.aFilsDroit())
			reste.add(min.ad());
		
		
		res.filsDroit(new ArbreBinaire<Cle>(null, null, min.cle(), res));
		
		unionRec(res.ag(), reste);
		unionRec(res.ad(), reste);
	}
	
	public IArbreBinaire<Cle> union(IArbreBinaire<Cle> a, IArbreBinaire<Cle> b) {
		ArrayList<IArbreBinaire<Cle>> reste=new ArrayList<IArbreBinaire<Cle>>();
		IArbreBinaire<Cle> res;
		
		if(a.cle().compareTo(b.cle())<0) {
			res = new ArbreBinaire<Cle>(null, null, a.cle(), null);
			if(a.aFilsGauche())
				reste.add(a.ag());
			if(a.aFilsDroit())
				reste.add(a.ad());
			reste.add(b);
		}
		else {
			res = new ArbreBinaire<Cle>(null, null, b.cle(), null);
			if(b.aFilsGauche())
				reste.add(b.ag());
			if(a.aFilsDroit())
				reste.add(b.ad());
			reste.add(a);
		}
		
		unionRec(res, reste);
		
		return res;
	}*/
	
	public IArbreBinaire<Cle> union(IArbreBinaire<Cle> u, IArbreBinaire<Cle> v) {
		ArrayList<IArbreBinaire<Cle>> priorite=new ArrayList<IArbreBinaire<Cle>>();
		ArrayList<IArbreBinaire<Cle>> arbres=new ArrayList<IArbreBinaire<Cle>>();
		ArrayList<IArbreBinaire<Cle>> parcours=new ArrayList<IArbreBinaire<Cle>>();
		parcours.add(u);
		parcours.add(v);
		IArbreBinaire<Cle> a, g, d, tmp;

		tmp=parcours.remove(0);
		a=new ArbreBinaire<Cle>(null, null, tmp.cle(), null);
		if(tmp.aFilsGauche())
			parcours.add(tmp.ag());
		if(tmp.aFilsDroit())
			parcours.add(tmp.ad());
		
		
		arbres.add(a);
		while(!parcours.isEmpty()) {
			tmp=parcours.remove(0);
			g=new ArbreBinaire<Cle>(null, null, tmp.cle(), null);
			if(tmp.aFilsGauche())
				parcours.add(tmp.ag());
			if(tmp.aFilsDroit())
				parcours.add(tmp.ad());
			priorite.add(g);
			a.filsGauche(g);
			arbres.add(g);
			
			d=null;
			if(!parcours.isEmpty()) {
				tmp=parcours.remove(0);
				d=new ArbreBinaire<Cle>(null, null, tmp.cle(), null);
				if(tmp.aFilsGauche())
					parcours.add(tmp.ag());
				if(tmp.aFilsDroit())
					parcours.add(tmp.ad());
				priorite.add(d);
				arbres.add(d);
			}
			a.filsDroit(d);
			
			a=priorite.remove(0); 
		}
		
		// tri à partir de la fin 
		for(int i=arbres.size()-1; i>=0; i--)
			triEnDescendant(arbres.get(i));
		
		return a;
	}
	
	public void triEnDescendant(IArbreBinaire<Cle> a) {
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
	
	public void tri(IArbreBinaire<Cle> a){
		triEnRemontant(a);
		triEnDescendant(a);
	}
	
	public IArbreBinaire<Cle> placeVide(){
		IArbreBinaire<Cle> placeVide=this;
		int n=(int)Math.floor(Math.log(this.cles())/Math.log(2));
		// trouver une manière de faire le log2 plus rapidement et remplacer partout
		int i=n;
		int clestmp=cles+1;
		
		while(i>0) {
			if(clestmp<Math.pow(2, i)+Math.pow(2, i-1)) {
				placeVide=placeVide.ag();
				clestmp-=Math.pow(2, i-1);
			}
			else {
				placeVide=placeVide.ad();
				clestmp-=Math.pow(2, i);
			}
			i--;
		}
		return placeVide;
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
	
	public IArbreBinaire<Cle> consIter(ArrayList<Cle> liste) {		
		ArrayList<IArbreBinaire<Cle>> priorite=new ArrayList<IArbreBinaire<Cle>>();
		ArrayList<IArbreBinaire<Cle>> arbres=new ArrayList<IArbreBinaire<Cle>>();
		IArbreBinaire<Cle> a, g, d;

		a=new ArbreBinaire<Cle>(null, null, liste.get(0), null);
		arbres.add(a);
		for(int i=1; i<liste.size(); i++) {
			g=new ArbreBinaire<Cle>(null, null, liste.get(i), null);
			priorite.add(g);
			a.filsGauche(g);
			arbres.add(g);
			
			i++;
			d=null;
			if(i<liste.size()) {
				d=new ArbreBinaire<Cle>(null, null, liste.get(i), null);
				priorite.add(d);
				arbres.add(d);
			}
			a.filsDroit(d);
			
			a=priorite.remove(0); 
		}
		
		// tri à partir de la fin 
		for(int i=arbres.size()-1; i>=0; i--)
			triEnDescendant(arbres.get(i));
		
		return a;
	}
	
	public void swap(IArbreBinaire<Cle> a, IArbreBinaire<Cle> b) {
		a.cle(b.cle());
		b.cle(a.cle());
	}
	
	public IArbreBinaire<Cle> cle(int num){
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
