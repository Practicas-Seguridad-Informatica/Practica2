package p2ma;

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
	
	private void recorrerArbol()
	{
		if(izquierda.hasRama())
		{
			izquierda.setCode("0");
			izquierda.recorrerArbol();
		}

		if(derecha.hasRama())
		{
			derecha.setCode("1");
			derecha.recorrerArbol();
		}
	}

	private void setCode(String num)
	{
		this.code = this.code + num;
	}
}
