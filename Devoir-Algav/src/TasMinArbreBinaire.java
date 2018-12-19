import java.util.ArrayList;
import java.util.HashSet;

public class TasMinArbreBinaire{

	private Key key;
	private TasMinArbreBinaire left;
	private	TasMinArbreBinaire right;
	private TasMinArbreBinaire parent;
	private int numberOfKeys=0;


	/* Crée un arbre binaire à partir du fils gauche, du fils droit, 
	 * d'une clé et du père. */
	public TasMinArbreBinaire(TasMinArbreBinaire left, TasMinArbreBinaire right, 
			Key c, TasMinArbreBinaire parent) {
		this.key=c;
		this.left=left;
		this.right=right;
		this.parent=parent;
		/* Calcul du nombre de clés. */
		this.numberOfKeys=1;
		if(this.left!=null)
			this.numberOfKeys+=left.getNumberOfKeys();
		if(this.right!=null)
			this.numberOfKeys+=right.getNumberOfKeys();
	}

	/*Crée un arbre complètement vide. */
	public TasMinArbreBinaire() {
		this.key=null;
		this.left=null;
		this.right=null;
		this.numberOfKeys=0;
	}

	/* Retourne la clé de l'arbre courant. */
	public Key getKey() {
		return key;
	}

	/* renvoie le nombre de clés de l'arbre courant.*/
	public int getNumberOfKeys() {
		return numberOfKeys;
	}

	/* Ajoute ou retire un nombre au nombre de clés. */
	private void addToNumberOfKeys(int c) {
		numberOfKeys+=c;
		if(getParent()!=null)
			getParent().addToNumberOfKeys(c);
	}

	/* Renvoie le fils gauche de l'arbre courant. */
	public TasMinArbreBinaire getLeftChild() {
		return left;
	}

	/* Renvoie le fils droit de l'arbre courant. */
	public TasMinArbreBinaire getRightChild() {
		return right;
	}

	/* Renvoie le père de l'arbre courant. */
	public TasMinArbreBinaire getParent() {
		return parent;
	}

	/* Met à jour le nombre de clés. */	
	public void setKey(Key c) {
		key=c;
	}

	/* Ajoute un fils gauche a à l'arbre binaire courant, 
	 * ou bien le retire si a est null et que l'objet courant possédait 
	 * déjà un fils gauche.
	 * Met à jour le nombre de clés de l'arbre.
	 * Met à jour le père du nouveau fils gauche. 
	 *  */
	public void setLeftChild(TasMinArbreBinaire a) {
		/* S'il avait déjà un fils gauche à la base, 
		 * retirer le nombre de clés de ce fils au nombre de clés de l'arbre.*/
		if(haveLeftChild())
			addToNumberOfKeys(-(getLeftChild().getNumberOfKeys()));
		/* Si a n'est pas null, ajouter le nouveau nombre de clés 
		 * à l'arbre courant. */
		if(a!=null)
			addToNumberOfKeys(a.getNumberOfKeys());

		/* Met à jour le père de a. */
		if(a!=null)
			a.setParent(this);

		/* Ajoute le fils gauche. */
		this.left=a;
	}

	/* Ajoute un fils droit a à l'arbre binaire courant, 
	 * ou bien le retire si a est null et que l'objet courant possédait 
	 * déjà un fils droit.
	 * Met à jour le nombre de clés de l'arbre.
	 * Met à jour le père du nouveau fils droit. 
	 *  */
	public void setRightChild(TasMinArbreBinaire a) {
		/* S'il avait déjà un fils droit à la base, 
		 * retirer le nombre de clés de ce fils au nombre de clés de l'arbre.*/
		if(haveRightChild())
			addToNumberOfKeys(-(getRightChild().getNumberOfKeys()));
		/* Si a n'est pas null, ajouter le nouveau nombre de clés 
		 * à l'arbre courant. */
		if(a!=null)
			addToNumberOfKeys(a.getNumberOfKeys());

		/* Met à jour le père de a. */
		if(a!=null)
			a.setParent(this);

		/* Ajoute le fils gauche. */
		this.right=a;
	}

	/* Met à jour le père de l'arbre courant. */
	public void setParent(TasMinArbreBinaire a) {
		parent=a;
	}

	/* Renvoie vrai si l'arbre courant a un fils gauche. */
	public boolean haveLeftChild() {
		return getLeftChild()!=null;
	}

	/* Renvoie vrai si l'arbre courant a un fils droit. */
	public boolean haveRightChild() {
		return getRightChild()!=null;
	}

	/* Renvoie vrai si l'arbre courant est le fils droit de son père. */
	public boolean isRightChild(){
		/* S'il n'a pas de père, c'est la racine.
		 * On renvoie faux. */
		if(getParent()==null)
			return false;

		/* Si le père a pour fils droit l'arbre courant, on renvoie vrai. */
		if(getParent().haveRightChild())
			if(getParent().getRightChild().equals(this))
				return true;

		/* Dans les autres cas on renvoie faux. */
		return false;
	}

	/* Renvoie vrai si l'arbre courant est le fils gauche de son père. */
	public boolean isLeftChild(){
		/* S'il n'a pas de père, c'est la racine.
		 * On renvoie faux. */
		if(getParent()==null)
			return false;

		/* Si le père a pour fils droit l'arbre courant, on renvoie vrai. */
		if(getParent().haveLeftChild())
			if(getParent().getLeftChild().equals(this))
				return true;

		/* Dans les autres cas on renvoie faux. */
		return false;
	}	

	/* Ajoute la clé k à l'arbre courant. */
	public boolean Ajout(Key k) {
		return Ajout(new TasMinArbreBinaire(null, null, k, null));
	}

	/* Ajoute l'arbre binaire a à l'arbre courant. */
	public boolean Ajout(TasMinArbreBinaire a) {
		/* Si l'arbre courant est complètement vide :*/
		if(getNumberOfKeys()==0) {
			this.key=a.key;
			this.left=a.left;
			this.right=a.right;
			this.numberOfKeys=a.numberOfKeys;
			return true;
		}
		/* Sinon, on cherche le père du premier emplacement vide dans l'arbre. */
		TasMinArbreBinaire placeVide=parentOfFirstEmptyNode();

		/* On cherche si l'emplacement vide est le fils gauche 
		 * ou le fils droit de son père. */
		if(!placeVide.haveLeftChild())
			placeVide.setLeftChild(a);
		else if(!placeVide.haveRightChild())
			placeVide.setRightChild(a);
		else
			return false;

		/* tri pour avoir un tas min */
		percolateUp(a);
		percolateDown(a);

		return true;
	}

	/* Fait un tri en remontant la branche vers le haut. */
	public void percolateUp(TasMinArbreBinaire a) {
		/* Tant qu'on est pas la racine et qu'on a une clé supérieure au père :*/
		while(a.getParent()!=null && Key.inf(a.getKey(), a.getParent().getKey())) {
			/* On échange les clés. */
			swap(a.getParent(), a);
			a=a.getParent();
		}
	}

	/* On trie la branche à partir de a vers le bas. */
	public static void percolateDown(TasMinArbreBinaire a) {
		/*Tant qu'il a des fils : */
		while(a.haveLeftChild()) {
			/* s'il a un fils droit et que c'est le plus petit de tous : */
			if(a.haveRightChild() 
					&& Key.inf(a.getRightChild().getKey(), a.getLeftChild().getKey()) 
					&& Key.inf(a.getRightChild().getKey(), a.getKey())) {

				/* On échange les clés entre le fils droit et a.*/
				swap(a.getRightChild(), a);
				/* On trie à partir du fils droit maintenant. */
				a=a.getRightChild();

			}
			/* SI le fils gauche est plus petit que a : */
			else if(Key.inf(a.getLeftChild().getKey(), a.getKey())) {
				/* On échange les clés entre le fils gauche et a.*/
				swap(a.getLeftChild(), a);
				/* On trie à partir du fils gauche maintenant. */
				a=a.getLeftChild();
			}
			/* S'il n'y a plus de fils on s'arrête.*/
			else {
				break;
			}
		}
	}

	public static TasMinArbreBinaire Union(TasMinArbreBinaire u, TasMinArbreBinaire v) {
		/* Noeuds du nouvel arbre. */
		ArrayList<TasMinArbreBinaire> trees=new ArrayList<TasMinArbreBinaire>();
		HashSet<TasMinArbreBinaire> treesHashed=new HashSet<TasMinArbreBinaire>();
		/* Noeuds à ajouter à l'arbre. */
		ArrayList<TasMinArbreBinaire> priority=new ArrayList<TasMinArbreBinaire>();

		/* On ajoute u et v à la liste des noeuds à ajouter au nouvel arbre. */
		priority.add(u);
		priority.add(v);
		TasMinArbreBinaire a, g, d, tmp;

		/* On crée la racine a avec la première clé. */
		tmp=priority.remove(0);
		a=new TasMinArbreBinaire(null, null, tmp.getKey(), null);

		/* On ajoute les fils de l'élément enlevé à la liste des noeuds à 
		 * ajouter au nouvel arbre. */
		if(tmp.haveLeftChild())
			priority.add(tmp.getLeftChild());
		if(tmp.haveRightChild())
			priority.add(tmp.getRightChild());

		/* On ajoute la racine à la liste des noeuds du nouvel arbre. */
		trees.add(a);
		treesHashed.add(a);

		/* Tant qu'il y a des noeuds à ajouter au nouvel arbre. */
		int i=0;
		while(!priority.isEmpty()) {

			do {
				tmp=priority.remove(0);

				/* On ajoute les fils du noeuds enlevés à la liste des noeuds à 
				 * ajouter au nouvel arbre : */
				if(tmp.haveLeftChild())
					priority.add(tmp.getLeftChild());
				if(tmp.haveRightChild())
					priority.add(tmp.getRightChild());

				/* On ne veut pas ajouter de clé déjà présente dans l'arbre.  */
			}while(treesHashed.contains(tmp) && !priority.isEmpty());

			/* g sera le fils gauche de a. */
			g=new TasMinArbreBinaire(null, null, tmp.getKey(), null);

			/* g est maintenant le fils gauche de a. */
			a.setLeftChild(g);
			/* On ajoute g à la liste des noeuds du nouvel arbre. */
			trees.add(g);
			treesHashed.add(g);

			d=null;
			/* S'il y a encore des noeuds à ajouter au nouvel arbre : */
			if(!priority.isEmpty()) {

				do {
					tmp=priority.remove(0);

					/* On ajoute les fils du noeuds enlevés à la liste des 
					 * noeuds à ajouter au nouvel arbre : */
					if(tmp.haveLeftChild())
						priority.add(tmp.getLeftChild());
					if(tmp.haveRightChild())
						priority.add(tmp.getRightChild());

					/* On ne veut pas ajouter de clé déjà présente dans l'arbre. */	
				}while(treesHashed.contains(tmp) && !priority.isEmpty());

				/* d sera le fils droit de a. */
				d=new TasMinArbreBinaire(null, null, tmp.getKey(), null);

				/* On ajoute d à la liste des noeuds du nouvel arbre. */
				trees.add(d);
				treesHashed.add(d);
			}

			/* d est maintenant le fils droit de a. */
			a.setRightChild(d);

			a=trees.get(i++);
		}

		// tri à partir de la fin 
		for(int j=trees.size()-1; j>=0; j--) 
			percolateDown(trees.get(j));

		/* On récupère la racine. */
		while(a.getParent()!=null)
			a=a.getParent();

		//System.out.println(trees);
		return a;
	}

	/* Renvoie le dernier noeud de l'arbre binaire : */
	public TasMinArbreBinaire lastNode(){
		return getTree(numberOfKeys);
	}

	public TasMinArbreBinaire parentOfFirstEmptyNode(){
		TasMinArbreBinaire parentOfFirstEmptyNode=this;

		/* On calcule le log2 du nombre de clés. */
		int i=(int)Math.floor(Math.log(this.getNumberOfKeys()+1)/Math.log(2));

		int tmp=numberOfKeys+1;

		/* On s'arrête au père (i>1 et non i>0). */
		while(i>1) {
			/* cas où il faut aller vers le sous-arbre gauche */
			if(tmp<Math.pow(2, i)+Math.pow(2, i-1)) {
				parentOfFirstEmptyNode=parentOfFirstEmptyNode.getLeftChild();
				tmp-=Math.pow(2, i-1);
			}
			/* cas où il faut aller vers le sous-arbre droit */
			else {
				parentOfFirstEmptyNode=parentOfFirstEmptyNode.getRightChild();
				tmp-=Math.pow(2, i);
			}
			i--;
		}

		return parentOfFirstEmptyNode;
	}

	/* Renvoie le num-ième arbre binaire de l'arbre courant */
	public TasMinArbreBinaire getTree(int num){
		TasMinArbreBinaire res=this;
		int i=(int)Math.floor(Math.log(num)/Math.log(2));
		int tmp=num;

		while(i>0) {
			/* cas où il faut aller vers le sous-arbre gauche */
			if(tmp<Math.pow(2, i)+Math.pow(2, i-1)) {
				res=res.getLeftChild();
				tmp-=Math.pow(2, i-1);
			}
			/* cas où il faut aller vers le sous-arbre droit */
			else {
				res=res.getRightChild();
				tmp-=Math.pow(2, i);
			}
			i--;
		}
		return res;
	}

	/* Supprime l'arbre binaire a de l'arbre dans lequel il est. */
	public void delete(TasMinArbreBinaire a) {
		/* on récupère le dernier noeud */
		TasMinArbreBinaire lastNode=lastNode();

		/* on échange les contenus de l'arbre binaire a 
		 * que l'on veut supprimer avec le dernier noeud. */
		swap(a, lastNode);

		/* On trie la clé échangée qui va rester dans l'arbre. */
		percolateUp(a);
		percolateDown(a);

		/* Si le noeud qu'on veut supprimer est un fils gauche,
		 * on supprime le lien entre le père et ce fils gauche. */
		if(lastNode.isLeftChild())
			lastNode.getParent().setLeftChild(null);
		/* Si le noeud qu'on veut supprimer est un fils droit,
		 * on supprime le lien entre le père et ce fils droit. */
		else if(lastNode.isRightChild())
			lastNode.getParent().setRightChild(null);

		/* On enlève 1 au nombre de clés. */
		addToNumberOfKeys(-1);
	}

	/* Renvoie un arbre créé à partir d'une liste de clés. */
	public static TasMinArbreBinaire consIter(ArrayList<Key> liste) {
		/* liste servant à savori quel noeud est prioritaire pour recevoir 
		 * des fils. */
		ArrayList<TasMinArbreBinaire> priority=new ArrayList<TasMinArbreBinaire>();
		/* liste servant au tri final des noeuds. */
		ArrayList<TasMinArbreBinaire> trees=new ArrayList<TasMinArbreBinaire>();
		TasMinArbreBinaire a, g, d;

		/* On crée la racine de l'arbre. */
		a=new TasMinArbreBinaire(null, null, liste.get(0), null);
		/* On l'ajoute à la liste des noeuds du nouvel arbre. */
		trees.add(a);

		for(int i=1; i<liste.size(); i++) {
			/* g sera le fils gauche de a. */
			g=new TasMinArbreBinaire(null, null, liste.get(i), null);

			/* On ajoute g à la fin de la liste des noeuds qui veulent des fils. */
			priority.add(g);
			/* Ajout du fils gauche g à a. */
			a.setLeftChild(g);
			/* Ajout de g à la liste des noeuds de l'arbre. */
			trees.add(g);

			i++;
			d=null;

			/* S'il reste des clés dans la liste à ajouter : */
			if(i<liste.size()) {
				/* d sera le fils droit de a. */
				d=new TasMinArbreBinaire(null, null, liste.get(i), null);
				/* On ajoute d à la fin de la liste des noeuds qui veulent des fils. */
				priority.add(d);
				/* Ajout de d à la liste des noeuds de l'arbre. */
				trees.add(d);
			}

			/* Ajout du fils droit d à a. */
			a.setRightChild(d);

			/* On ajoutera au prochain tour de boucle des fils 
			 * au premier élément de la liste priority : */
			a=priority.remove(0);
		}

		/* On trie à partir de la fin et vers le bas. */ 
		for(int i=trees.size()-1; i>=0; i--) 
			percolateDown(trees.get(i));

		/* On remonte pour récupérer la racine, après le tri. */
		while(a.getParent()!=null) 
			a=a.getParent();

		return a;
	}

	/* Méthode moins efficace en O(n log n), 
	 * utilisée en comparaison dans le rapport.
	 * Utilise une boucle avec Ajout(). */
	public static TasMinArbreBinaire consIter2(ArrayList<Key> liste) {
		TasMinArbreBinaire a = new TasMinArbreBinaire();
		for(Key k : liste)
			a.Ajout(k);

		return a;
	}

	/* Echange les clés de deux noeuds a et b. */
	private static void swap(TasMinArbreBinaire a, TasMinArbreBinaire b) {
		Key tmp=a.getKey();
		a.setKey(b.getKey());
		b.setKey(tmp);
	}

	/* Supprime le minimum de l'arbre (la racine). */
	public void SupprMin() {
		delete(this);
	}

	/* Permet l'affichage d'un arbre binaire. 
	 * Liste les clés des noeuds avec leurs fils. */
	@Override
	public String toString() {
		String s = ""+key + 
				" fg : " + 
				((haveLeftChild())?getLeftChild().getKey():"none") + 
				" fd : "+ 
				((haveRightChild())?getRightChild().getKey():"none");
		s+="\n";

		/* Récursion sur les fils gauche et droit : */
		if(haveLeftChild()) 
			s+=getLeftChild();

		if(haveRightChild()) 
			s+=getRightChild();

		return s;
	}

	@Override
	public int hashCode() {
		return getKey().hashCode();
	}

	@Override
	public boolean equals(Object k) {
		if(k instanceof TasMinArbreBinaire)
			return Key.eg(this.getKey(), ((TasMinArbreBinaire)k).getKey());

		return false;
	}
}
