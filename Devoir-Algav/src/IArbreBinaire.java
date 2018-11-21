
public interface IArbreBinaire<Cle> {
	IArbreBinaire<Cle> ag();
	IArbreBinaire<Cle> ad();
	IArbreBinaire<Cle> pere();
	Cle cle();
	
	void cle(Cle c);
	void filsGauche(IArbreBinaire<Cle> a);
	void filsDroit(IArbreBinaire<Cle> a);
	void pere(IArbreBinaire<Cle> a);
	
	boolean estFilsGauche();
	boolean estFilsDroit();
	boolean aFilsDroit();	
	boolean aFilsGauche();
	
	void tri(IArbreBinaire<Cle> a);
	void swap(IArbreBinaire<Cle> a, IArbreBinaire<Cle> b);
	
	IArbreBinaire<Cle> place();
	IArbreBinaire<Cle> derniereCle();
	
	boolean Ajout(IArbreBinaire<Cle> a);
	void Supprime(IArbreBinaire<Cle> a);
	void SupprMin();
	int cles();
}
