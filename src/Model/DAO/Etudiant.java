package Model.DAO;

public class Etudiant {
	private int id;
	private String nom;
	private String ra;
	private String rg;

	

	public Etudiant(int id, String nom, String ra, String rg) {
		super();
		this.id = id;
		this.nom = nom;
		this.ra = ra;
		this.rg = rg;
	}

	public Etudiant(int id) {
		super();
		this.id = id;
	}

	public Etudiant(String nom, String ra, String rg) {
		super();
		this.nom = nom;
		this.ra = ra;
		this.rg = rg;	
	}

	public Etudiant() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getRa() {
		return ra;
	}

	public void setRa(String ra) {
		this.ra = ra;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	@Override
	public String toString() {
		return "Etudiant [id=" + id + ", nom=" + nom + ", ra=" + ra + ", rg=" + rg + "]";
	}	
}
