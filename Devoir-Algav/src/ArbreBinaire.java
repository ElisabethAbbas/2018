import java.util.ArrayList;

public class ArbreBinaire{
	
	private Key cle;
	private ArbreBinaire gauche;
	private	ArbreBinaire droite;
	private ArbreBinaire pere;
	private int cles=0;
	
	public ArbreBinaire(ArbreBinaire gauche, ArbreBinaire droite, 
			Key c, ArbreBinaire pere) {
		this.cle=c;
		this.gauche=gauche;
		this.droite=droite;
		this.pere=pere;
		this.cles=1;
		if(this.gauche!=null)
			this.cles+=gauche.cles();
		if(this.droite!=null)
			this.cles+=droite.cles();
	}
	
	public ArbreBinaire() {
		this.cle=null;
		this.gauche=null;
		this.droite=null;
		this.cles=0;
	}
	
	
	public Key cle() {
		return cle;
	}
	
	
	public int cles() {
		return cles;
	}
	
	public void ajouterACles(int c) {
		cles+=c;
		if(pere()!=null)
			pere().ajouterACles(c);
	}
	
	public ArbreBinaire ag() {
		return gauche;
	}
	
	
	public ArbreBinaire ad() {
		return droite;
	}

	
	public ArbreBinaire pere() {
		return pere;
	}
	
	
	public void cle(Key c) {
		cle=c;
	}
	
	
	public void filsGauche(ArbreBinaire a) {
		if(!aFilsGauche()&& a!=null)
			ajouterACles(a.cles());
		if(aFilsGauche() && a==null)
			ajouterACles(-(ag().cles()));
		
		if(a!=null)
			a.pere(this);
		this.gauche=a;
	}
	
	
	public void filsDroit(ArbreBinaire a) {
		if(!aFilsDroit()&& a!=null)
			ajouterACles(a.cles());
		if(aFilsDroit() && a==null)
			ajouterACles(-(ad().cles()));
		
		if(a!=null)
			a.pere(this);
		this.droite=a;
	}
	
	
	public void pere(ArbreBinaire a) {
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
		
		if(pere().aFilsDroit())
			if(pere().ad().equals(this))
				return true;
		return false;
	}
	
	public boolean estFilsGauche(){
		if(pere()==null)
			return false;

		if(pere().aFilsGauche())
			if(pere().ag().equals(this))
				return true;
		
		return false;
	}	
	
	public boolean Ajout(Key k) {
		return Ajout(new ArbreBinaire(null, null, k, null));
	}
	
	public boolean Ajout(ArbreBinaire a) {
		if(cles()==0) {
			this.cle=a.cle;
			this.gauche=a.gauche;
			this.droite=a.droite;
			this.cles=a.cles;
			return true;
		}
		ArbreBinaire placeVide=placeVide();
		
		if(!placeVide.aFilsGauche())
			placeVide.filsGauche(a);
		else if(!placeVide.aFilsDroit())
			placeVide.filsDroit(a);
		else
			return false;
		
		/* tri pour avoir un tas min */
		tri(a);
		
		return true;
	}
	
	public void triEnRemontant(ArbreBinaire a) {
		while(a.pere()!=null && Key.inf(a.cle(), a.pere().cle())) {
			swap(a.pere(), a);
			a=a.pere();
		}
	}
	
	public static ArbreBinaire union(ArbreBinaire u, ArbreBinaire v) {
		ArrayList<ArbreBinaire> priorite=new ArrayList<ArbreBinaire>();
		ArrayList<ArbreBinaire> arbres=new ArrayList<ArbreBinaire>();
		ArrayList<ArbreBinaire> parcours=new ArrayList<ArbreBinaire>();
		parcours.add(u);
		parcours.add(v);
		ArbreBinaire a, g, d, tmp;

		tmp=parcours.remove(0);
		a=new ArbreBinaire(null, null, tmp.cle(), null);
		if(tmp.aFilsGauche())
			parcours.add(tmp.ag());
		if(tmp.aFilsDroit())
			parcours.add(tmp.ad());
		
		
		arbres.add(a);
		while(!parcours.isEmpty()) {
			tmp=parcours.remove(0);
			g=new ArbreBinaire(null, null, tmp.cle(), null);
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
				d=new ArbreBinaire(null, null, tmp.cle(), null);
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
		
		/* On récupère la racine. */
		while(a.pere()!=null)
			a=a.pere();
		
		return a;
	}
	
	public static void triEnDescendant(ArbreBinaire a) {
		while(a.aFilsGauche()) { 
			if(a.aFilsDroit() 
					&& Key.inf(a.ad().cle(), a.ag().cle()) 
					&& Key.inf(a.ad().cle(), a.cle())) {
				swap(a.ad(), a);
				a=a.ad();
					
			}
			else if(Key.inf(a.ag().cle(), a.cle())) {
				swap(a.ag(), a);
				a=a.ag();
			}
			else {
				break;
			}
		}
	}
	
	public void tri(ArbreBinaire a){
		triEnRemontant(a);
		triEnDescendant(a);
	}
	
	public ArbreBinaire derniereCle(){
		ArbreBinaire derniereCle=this;
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
	
	public ArbreBinaire placeVide(){
		ArbreBinaire placeVide=this;
		int n=(int)Math.floor(Math.log(this.cles()+1)/Math.log(2));

		// trouver une manière de faire le log2 plus rapidement et remplacer partout
		int i=n;
		int clestmp=cles+1;
		
		while(i>1) {
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
	
	public void Supprime(ArbreBinaire a) {
		ArbreBinaire derniereCle=derniereCle();
		
		swap(a, derniereCle);
		a.tri(a);
		
		if(derniereCle.estFilsGauche())
			derniereCle.pere().filsGauche(null);
		else if(derniereCle.estFilsDroit())
			derniereCle.pere().filsDroit(null);
		
		ajouterACles(-1);
	}
	
	public static ArbreBinaire consIter(ArrayList<Key> liste) {		
		ArrayList<ArbreBinaire> priorite=new ArrayList<ArbreBinaire>();
		ArrayList<ArbreBinaire> arbres=new ArrayList<ArbreBinaire>();
		ArbreBinaire a, g, d;

		a=new ArbreBinaire(null, null, liste.get(0), null);
		arbres.add(a);
		
		for(int i=1; i<liste.size(); i++) {
			g=new ArbreBinaire(null, null, liste.get(i), null);
			priorite.add(g);
			a.filsGauche(g);
			arbres.add(g);
			
			i++;
			d=null;
			if(i<liste.size()) {
				d=new ArbreBinaire(null, null, liste.get(i), null);
				priorite.add(d);
				arbres.add(d);
			}
			a.filsDroit(d);
			a=priorite.remove(0);
		}
		
		// tri à partir de la fin 
		for(int i=arbres.size()-1; i>=0; i--) {
			triEnDescendant(arbres.get(i));
		}
		
		// on récupère la racine 
		// à vérif
		while(a.pere()!=null) 
			a=a.pere();
		
		return a;
	}
	
	public static void swap(ArbreBinaire a, ArbreBinaire b) {
		Key aTmp=a.cle();
		a.cle(b.cle());
		b.cle(aTmp);
	}
	
	public ArbreBinaire cle(int num){
		ArbreBinaire res=this;
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

	
	public void SupprMin() {
		Supprime(this);
	}
	
	@Override
	public String toString() {
		String s = ""+cle.getKey().toString(16) + " fg : " + ((aFilsGauche())?ag().cle().getKey().toString(16):"none") + " fd : "+ ((aFilsDroit())?ad().cle().getKey().toString(16):"none");
		s+="\n";
		
		if(aFilsGauche()) 
			s+=ag();
		
		if(aFilsDroit()) 
			s+=ad();
			
		return s;
	}
}
