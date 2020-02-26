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
		if(this.hasRama()) //Si tiene hijos, seguiremos avanzando por el árbol
		{
			izquierda.recorrerArbol(code + "0");
			derecha.recorrerArbol(code + "1");
		}
		else //Si no tiene hijos, significará que es un nodo hoja, por lo que le asignaremos el código
		{
			this.setCode(code);
			System.out.println(code);
		}
	}

	public void imprimirArbol()
	{
		if(this.hasRama())
		{
			System.out.println(this.num + "{" + this.izquierda.getNum() + ", " + this.derecha.getNum() + "}");
			izquierda.imprimirArbol();
			derecha.imprimirArbol();
		}		
	}

	private void setCode(String num)
	{
		this.code = this.code + num;
	}
}
