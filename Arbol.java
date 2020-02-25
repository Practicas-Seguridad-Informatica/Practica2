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
	
	private void recorrerArbol()
	{
		
	}
}
