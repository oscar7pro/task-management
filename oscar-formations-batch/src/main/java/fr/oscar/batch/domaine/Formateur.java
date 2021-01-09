package fr.oscar.batch.domaine;

public class Formateur {
	private Integer id;
	private String nom;
	private String prenom;
	private String adresseEmail;

	

	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getNom() {
		return nom;
	}



	public void setNom(String nom) {
		this.nom = nom;
	}



	public String getPrenom() {
		return prenom;
	}



	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}



	public String getAdresseEmail() {
		return adresseEmail;
	}



	public void setAdresseEmail(String adresseEmail) {
		this.adresseEmail = adresseEmail;
	}



	public Formateur() {
		super();
	}



	public Formateur(Integer id, String nom, String prenom, String adresseEmail) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.adresseEmail = adresseEmail;
	}
	

}
