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
		System.out.println("Num: " + this.num);

		if(izquierda.hasRama())
		{
			izquierda.recorrerArbol(code + "0");
		}
		else
		{
			//code = code + "0";
			izquierda.setCode(code);
		}

		if(derecha.hasRama())
		{
			derecha.recorrerArbol(code + "1");
		}
		else
		{
			//code = code + "1";
			derecha.setCode(code);
		}
		
	}

	private void setCode(String num)
	{
		this.code = this.code + num;
	}
}
