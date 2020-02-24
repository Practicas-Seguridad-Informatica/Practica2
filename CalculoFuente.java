package Practica2;

import java.util.ArrayList;

public class CalculoFuente
{
    private String txt;
    private ArrayList<String> arrSimbolos;
    private int cantidadSimbolos;

    //Constructor de la clase. Dentro inicializamos la variable global txt donde estar� el texto a resolver
    //Tambi�n se inicializar�n los ArrayList necesarios para el programa
    public CalculoFuente(int cantidadSimbolos)
    {
        txt = "La noche cae, brumosa ya y morada.  "
				+ "Vagas claridades malvas y verdes perduran tras la torre de la iglesia.  "
				+ "El camino sube, lleno de sombras, de campanillas, de fragancia de hierba,  "
				+ "de canciones, de cansancio y de anhelo.";

        //En este arryList guardaremos los s�mbolos que componen el texto
        arrSimbolos = new ArrayList<String>();
        
        //Podremos pasar al programa un par�metro para sabe cada cu�ntas letras hay un s�mbolo. (As� tenemos en cuenta el caso del ejercicio 2)
        this.cantidadSimbolos = cantidadSimbolos; 
        
        llenarArraySimbolos();
    }

    //M�todo utilizado para meter el string txt en el arrSimbolos
    public void llenarArraySimbolos()
    {
        int cont = 0;

        while(cont < txt.length()) //Vamos a recorrer cada uno de los caracteres que forman el texto.
        {
            String aux = "";

            for(int j = 0; j < cantidadSimbolos; j++) //En funci�n de cada cu�ntos caracteres se componga un s�mbolo...
            {
                if(cont < txt.length()) //Comprobamos que no hayamos llegado al final del texto (en caso de que este no tenga una longitud tal que pueda dividirse con decimales)
                {
                    aux = aux + String.valueOf(txt.charAt(cont)); //iremos formando Strings para cada s�mbolo (en funci�n de la longitud que nos hayan dicho que tienen los s�mbolos)
                    cont++;
                }
            }

            arrSimbolos.add(aux);  //Una vez formado el s�mbolo lo a�adimos al arraylist y as� hasta que terminemos el texto
        }
    }

    //Con este m�todo calcularemos cu�ntas veces se repite cada s�mbolo.
    public void resolver() 
    {
        ArrayList<String> cadaSimbolo = new ArrayList<String>(); //Aqu� guardaremos de forma individual cada uno de los s�mbolos existentes en el texto.
        ArrayList<Integer> arrCounter = new ArrayList<Integer>(); //En este arrayList guardarmeos un contador para cada posici�n de los s�mbolos del anterior arraylist que aumentar� para cada s�mbolo cuando estos se repitan

        for(int i = 0; i < arrSimbolos.size(); i++) //Recorremos el array que contiene todos los s�mbolos
        {
            if(cadaSimbolo.size() == 0) //Si es el primer s�mbolo de todos lo a�adimos directamente porque no puede estar ya dentro (el array est� vac�o a�n)
            {
                cadaSimbolo.add(arrSimbolos.get(i));
                arrCounter.add(1);
            }
            else  //Si no es el primer s�mbolo del texto
            {
                boolean comprobacion = false; //Flag para saber si un s�mbolo est� ya dentro de nuestro array de �nicos o no

                for(int j = 0; j < cadaSimbolo.size(); j++) //Recorremmos nuestro array de s�mbolos �nicos para comprobar si ha sido a�adido ya o no
                {
                    if(cadaSimbolo.get(j).equals(arrSimbolos.get(i))) //Si el s�mbolo ya est� dentro del array
                    {
                        int aux = arrCounter.get(j) + 1; //Aumentamos el contador de esa posici�n para ese s�mbolo
                        arrCounter.set(j, aux); //Y sustituimos el valor (vamos, como hacer counter++);
                        comprobacion = true; //Cambiamos el flag para indicar que el s�mbolo estaba
                    }
                }

                if(!comprobacion) //Si al terminar de comprobar ese s�mbolo para cada uno de los que ya tenemos guardados, vemos que no ha coincidido, lo a�adiremos
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
                if(arrCounter.get(i) < arrCounter.get(i + 1)) //Si el número que está a la derecha es mayor...
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
    }
}