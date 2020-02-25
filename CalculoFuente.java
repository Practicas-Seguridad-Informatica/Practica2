package Practica2;

import java.util.ArrayList;
import java.lang.Math;

public class CalculoFuente
{
    private String txt;
    private ArrayList<String> arrSimbolos;
    private int cantidadSimbolos;

    //Constructor de la clase. Dentro inicializamos la variable global txt donde estara el texto a resolver
    //Tambien se inicializaron los ArrayList necesarios para el programa
    public CalculoFuente(int cantidadSimbolos)
    {
        txt = "Existe una cosa muy misteriosa, pero muy cotidiana. Todo el mundo participa de ella,  " + 
        		"todo el mundo la conoce, pero muy pocos se paran a pensar en ella.  " + 
        		"Casi todos se limitan a tomarla como viene, sin hacer preguntas.  " + 
        		"Esta cosa es el tiempo.";

        //En este arryList guardaremos los simbolos que componen el texto
        arrSimbolos = new ArrayList<String>();
        
        //Podremos pasar al programa un parametro para sabe cada cuantas letras hay un simbolo. (Asi tenemos en cuenta el caso del ejercicio 2)
        this.cantidadSimbolos = cantidadSimbolos; 
        
        llenarArraySimbolos();
        
        System.out.println("El tamano es: " + txt.length());
    }
   
    
    //Metodo utilizado para meter el string txt en el arrSimbolos
    public void llenarArraySimbolos()
    {
        int cont = 0;

        while(cont < txt.length()) //Vamos a recorrer cada uno de los caracteres que forman el texto.
        {
            String aux = "";

            for(int j = 0; j < cantidadSimbolos; j++) //En funcion de cada cuantos caracteres se componga un simbolo...
            {
                if(cont < txt.length()) //Comprobamos que no hayamos llegado al final del texto (en caso de que este no tenga una longitud tal que pueda dividirse con decimales)
                {
                    aux = aux + String.valueOf(txt.charAt(cont)); //iremos formando Strings para cada simbolo (en funcion de la longitud que nos hayan dicho que tienen los simbolos)
                    cont++;
                }
            }

            arrSimbolos.add(aux);  //Una vez formado el simbolo lo aniadimos al arraylist y asi hasta que terminemos el texto
        }
    }

    //Con este metodo calcularemos cuantas veces se repite cada simbolo.
    public void resolver() 
    {
        ArrayList<String> cadaSimbolo = new ArrayList<String>(); //Aqui guardaremos de forma individual cada uno de los simbolos existentes en el texto.
        ArrayList<Integer> arrCounter = new ArrayList<Integer>(); //En este arrayList guardarmeos un contador para cada posicion de los simbolos del anterior arraylist que aumentara para cada simbolo cuando estos se repitan

        for(int i = 0; i < arrSimbolos.size(); i++) //Recorremos el array que contiene todos los simbolos
        {
            if(cadaSimbolo.size() == 0) //Si es el primer simbolo de todos lo aniadimos directamente porque no puede estar ya dentro (el array esta vacio aun)
            {
                cadaSimbolo.add(arrSimbolos.get(i));
                arrCounter.add(1);
            }
            else  //Si no es el primer simbolo del texto
            {
                boolean comprobacion = false; //Flag para saber si un simbolo esta ya dentro de nuestro array de unicos o no

                for(int j = 0; j < cadaSimbolo.size(); j++) //Recorremmos nuestro array de simbolos unicos para comprobar si ha sido aniadido ya o no
                {
                    if(cadaSimbolo.get(j).equals(arrSimbolos.get(i))) //Si el simbolo ya esta dentro del array
                    {
                        int aux = arrCounter.get(j) + 1; //Aumentamos el contador de esa posicion para ese simbolo
                        arrCounter.set(j, aux); //Y sustituimos el valor (vamos, como hacer counter++);
                        comprobacion = true; //Cambiamos el flag para indicar que el s�mbolo estaba
                    }
                }

                if(!comprobacion) //Si al terminar de comprobar ese simbolo para cada uno de los que ya tenemos guardados, vemos que no ha coincidido, lo aniadiremos
                {
                    cadaSimbolo.add(arrSimbolos.get(i));
                    arrCounter.add(1); //Y le creamos su contador
                }
            }
        }
        
        for(int i = 0; i < cadaSimbolo.size(); i++)
        {
        	System.out.print(cadaSimbolo.get(i));
        }
        
        System.out.println("\n");
        
        
        for(int i = 0; i < cadaSimbolo.size(); i++)
        {
        	System.out.print(arrCounter.get(i) + " ");
        }

        //Vamos a ordenar las frecuencias de mayor a menor para aplicar el algoritmo de Huffman

        boolean terminado; //Flag que utilizaremos para saber si ha habido algún intercambio. Si no lo ha habido significará que ya está completamente ordenado

        while(true)
        {
            terminado = true;

            for(int i = 0; i < cadaSimbolo.size() - 1; i++)
            {
                if(arrCounter.get(i) < arrCounter.get(i + 1)) //Si el numero que está a la derecha es mayor...
                {
                    terminado = false;  //Cambiamos el terminado a false porque hemos encontrado un intercambio posible, así que no hemos terminado
                    int auxInt = arrCounter.get(i);
                    String auxString = cadaSimbolo.get(i);

                    arrCounter.set(i, arrCounter.get(i + 1)); //Intercambiamos posiciones en el array de contadores
                    arrCounter.set(i + 1, auxInt);
                    
                    cadaSimbolo.set(i, cadaSimbolo.get(i + 1)); //Intercambiamos posiciones en el array de símbolos
                    cadaSimbolo.set(i + 1, auxString);
                }
            }

            if(terminado) //Si no se ha cambiado el flag de atendido a false significa que no ha habido cambios y salimos del bucle.
            {
                break;
            }
        }

        System.out.println("\n--------------------\n");

        for(int i = 0; i < cadaSimbolo.size(); i++)
        {
        	System.out.print(arrCounter.get(i) + " ");
        }
        
        //Calculamos la entropía de la Fuente 
        
        double entropia = 0;
        
        for(int i = 0; i < arrCounter.size(); i++)
        {
        	entropia += ((double)arrCounter.get(i) / (double)txt.length()) * (double)(Math.log((double)txt.length() / (double)arrCounter.get(i))) / (double)Math.log(2);
        }
        
        
        System.out.println("---------" + entropia);

        /*arrCounter = new ArrayList<Integer>();

        arrCounter.add(3);
        arrCounter.add(2);
        arrCounter.add(2);
        arrCounter.add(1);
        arrCounter.add(1);
        arrCounter.add(1);*/
        
        //Calculamos Huffman
        //----------------------------------------------------
        ArrayList<Arbol> ramas = new ArrayList<Arbol>();
        
    	//Convertimos cada frecuencia en una rama del arbol
        for(int i = 0; i < arrCounter.size(); i++)
        {
        	Arbol nuevaRama = new Arbol(arrCounter.get(i));
        	
        	ramas.add(nuevaRama);
        }
        
        while(ramas.size() != 1)
        {   
	    	//Buscamos los dos más pequeños
	    	int pos1 = 1000;
	    	int pos2 = 1000;
	    	int posNumMasPequenio = 0;
	    	int posSegNumMasPequenio = 0;
	        
	    	//Encontramos el número más pequenio
	    	for(int i = 0; i < ramas.size(); i++)
	    	{
	    		if(pos1 > ramas.get(i).getNum())
	    		{
	    			pos1 = ramas.get(i).getNum();
	    			posNumMasPequenio = i;
	    		}
	    	}
	    	
	    	//Encontramos el segundo número más pequenio
	    	for(int i = 0; i < ramas.size(); i++)
	    	{
	    		if(i != posNumMasPequenio)
	    		{
		    		if(pos2 > ramas.get(i).getNum())
		    		{
		    			pos2 = ramas.get(i).getNum();
		    			posSegNumMasPequenio = i;
		    		}
	    		}
	    	}
	    	
	    	//Una vez encontrados los dos más pequenios los sumamos
	    	int res = pos1 + pos2;
	    	
	    	//Construimos un nuevo array<
	    	
	    	ArrayList<Arbol> arrAux = new ArrayList<Arbol>();
	    	
	    	//Añadimos todos los que no han variado (los que no han sido sumados)
	    	for(int i = 0; i < ramas.size(); i++)
	    	{
                if(i != posNumMasPequenio && i != posSegNumMasPequenio) //En el nuevo array metemos todos menos los dos más pequeños
                {
	    		    arrAux.add(ramas.get(i));
                }
	    	}
	    	//Por último aniadimos el resultado de la suma de esos dos que no hemos metido
	    	Arbol arbolAux = new Arbol(res);
	    	arrAux.add(arbolAux);
	    	
	    	//Referenciamos las dos ramas del arbol
	    	arbolAux.meterDerecha(ramas.get(posNumMasPequenio));
	    	arbolAux.meterIzquierda(ramas.get(posSegNumMasPequenio));
	    	
	    	//Convertimos el array de arboles auxiliar en el array ramas
	    	
	    	ramas = new ArrayList<Arbol>();
	    	
	    	//Lo rellenamos de nuevo   	
	    	for(int i = 0; i < arrAux.size(); i++)
	    	{
	    		ramas.add(arrAux.get(i));
	    	}
	    	
	    	System.out.println("a----------------a");
	    	
	    	for(int i = 0; i < ramas.size(); i++)
	    	{
	    		System.out.println(ramas.get(i).getNum());
	    	}
	    	
        }
        
        System.out.println("El root tiene un valor de: " + ramas.get(0).getNum());
        	
        ramas.get(0).recorrerArbol("");
    }   
    
}