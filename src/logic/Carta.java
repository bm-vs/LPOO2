package logic;

public class Carta  implements Cloneable{

	String naipe;
	int valor;
	
	public Carta(String naipe, int valor)
	{
		this.naipe = naipe;
		this.valor = valor;		
	}
	
	public String getCarta()
	{
		return naipe+ " " + valor;
	}
	
	public Carta clone()
	{
		return new Carta(naipe, valor);
	}
	
	
	
}
