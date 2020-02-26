package Practica2;

import java.util.ArrayList;

public class Arbol 
{
	private int num;
	private String code;
	private Arbol izquierda;
	private Arbol derecha;
	private Arbol root;
	private int pos;
	
	public Arbol(int num, int pos)
	{
		this.num = num;
		code = "";
		izquierda = null;
		derecha = null;
		root = null;
		this.pos = pos;
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
	
	public void recorrerArbol(String code, ArrayList<Integer> productoLiPi)
	{
		if(this.hasRama()) //Si tiene hijos, seguiremos avanzando por el árbol
		{
			izquierda.recorrerArbol(code + "0", productoLiPi);
			derecha.recorrerArbol(code + "1", productoLiPi);
		}
		else //Si no tiene hijos, significará que es un nodo hoja, por lo que le asignaremos el código
		{
			this.setCode(code);
			System.out.println(code);
			productoLiPi.add(this.num * code.length()); //Hacemos el producto y lo guardamos en el arrayList para poder calcular la longitud del código
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
