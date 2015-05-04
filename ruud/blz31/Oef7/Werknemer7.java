/**
* Deze klasse is een java programma
* @author Ruud Luijten
* @version 1.5
*/

import java.lang. *;

public class Werknemer7{

	public String voornaam;
	public String achternaam;
	public int werknemerNummer;
	protected float salaris;
	private float rszPercentage = 33;

	public Werknemer7(String voornaam, String achternaam, int wNummer, float salaris)
	{
		this.voornaam = voornaam;
		this.achternaam = achternaam;
		this.werknemerNummer = wNummer;
		this.salaris = salaris;
	}

	public void salarisVerhogen(int percentage)
	{
		float verhogingsfactor = (float)percentage/100;
		salaris += salaris * verhogingsfactor;
	}

	public float getSalaris()
	{
		return salaris;
	}

	public float getRSZ()
	{
		return this.rszPercentage;
	}

	public void setRSZ(float rsz)
	{
		this.rszPercentage = rsz;
	}
}