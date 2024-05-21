package Model.DAO;

public class Professeur {
	private int id;
	private String nome;
	private String rgf;
	private String rg;


	public Professeur(String nome, String rgf, String rg) {
		super();
		this.nome = nome;
		this.rgf = rgf;
		this.rg = rg;
	}

	public Professeur() {
		// TODO Auto-generated constructor stub
	}

	public Professeur(int id, String nome, String rgf, String rg) {
		super();
		this.id = id;
		this.nome = nome;
		this.rgf = rgf;
		this.rg = rg;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRgf() {
		return rgf;
	}

	public void setRgf(String rgf) {
		this.rgf = rgf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	@Override
	public String toString() {
		return "Professor [id=" + id + ", nome=" + nome + ", rgf=" + rgf + ", rg=" + rg + "]";
	}
}
