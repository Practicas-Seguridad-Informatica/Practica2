package Practica2;

public class Arbol 
{
	private int num;
	private String code;
	private Arbol izquierda;
	private Arbol derecha;
	private Arbol root;
	
	public Arbol(int num)
	{
		this.num = num;
		code = "";
		izquierda = null;
		derecha = null;
		root = null;
	}
	
	public int getNum()
	{
		return this.num;
	}
	
	public void meterDerecha(Arbol arb)
	{
		derecha = arb;
	}
	
	public void meterIzquierda(Arbol arb)
	{
		izquierda = arb;
	}
	
	public void setRoot(Arbol root)
	{
		this.root = root;
	}

	private boolean hasRama()
	{
		if(izquierda == null)
		{
			return false;
		}

		return true;
	}
	
	public void recorrerArbol(String code)
	{
		if(izquierda.hasRama())
		{
			izquierda.recorrerArbol(code + "0");
		}
		else
		{
			izquierda.setCode(code);
			System.out.println(code);
		}

		if(derecha.hasRama())
		{
			derecha.recorrerArbol(code + "1");
		}
		else
		{
			derecha.setCode(code);
			System.out.println(code);
		}
		
	}

	private void setCode(String num)
	{
		this.code = this.code + num;
	}
}
