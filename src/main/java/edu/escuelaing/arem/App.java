package edu.escuelaing.arem;
import static spark.Spark.*;

import java.util.Arrays;

import edu.escuelaing.arem.calculadora.CalculadoraProductoInterno;
import spark.Request;
import spark.Response;

/**
 * Hello world!
 *
 */
public class App 
{
	/**
     * Este metodo main inicia el servidor de la calculadora de producto interno de matrices define algunas peticiones y respuestas haciendo uso 
     * de algunas funciones lambda
     */
    public static void main( String[] args )
    {
    	port(getPort());
    	get("/", (req, res) ->  inputView(req, res));
    	get("/results", (req, res) -> resultsView(req, res));
    }
    /**
     *Este metodo contruye la vista inputView apartir del string html view que retorna  
     *
     * @param req Tiene la informacion de la petición que llega al servidor.
     * @param res Tiene la información con la respuesta del servidor.
     * @return String con la informacion html de la vista de entrada.
     */
    private static String  inputView(Request req, Response res) {
        String view = "<!DOCTYPE html>"
                + "<html>"
                + "<body style=\"background-color:#CCCC00;\">"
                +"<center>"
                + "<h2>Calculadora de producto interno matrices</h2>"
                + "  <br><br>"
				+ "<h2>Intrucciones : Porfavor ingrese los vectores de las matrices dentro de corchetes y separados por ; y los valores dentro de los corchetes separelos por , de la siguiente forma</h2>"
				+ "<h2>Ejemplo : {{1,2,3};{3,4,5};{7,8,9}}</h2>"
				+ "<form action=\"/results\">"
                + "  Matriz1  = {"
                + "  <input type=\"text\" name=\"datos\">"
                +"    }"
                + "  <br><br>"
                + "  Matriz2  = {"
                + "  <input type=\"text\" name=\"datos2\">"
                +"    }"
                + "  <br><br>"
                + "  <input type=\"submit\" value=\"Calcular\">"
                + "</form>"
                +"</center>"
                + "</body>"
                + "</html>";
        return view;
    }
    /**
     *Este metodo contruye la vista resultView apartir un req que le envia inputView y asi retorna
     *un res html con la informacion de la view  
     *
     * @param req Tiene la informacion de la petición que llega al servidor.
     * @param res Tiene la información con la respuesta del servidor.
     * @return String con la informacion html de la vista de entrada.
     */
    private static String resultsView(Request req, Response res) {  	
     int[][] m1 = getMatriz(req.queryParams("datos"));
     int[][] m2 = getMatriz(req.queryParams("datos2"));
     CalculadoraProductoInterno calculadora = new CalculadoraProductoInterno();
 	 int[][] tempo = calculadora.calcularProductoInterno(m1,m2);
   	 String view = "<!DOCTYPE html>"
                + "<html>"
                + "<body style=\"background-color:#32CD32;\">"
                + "<style>"
                + "table, th, td {"
                + "border: 1px solid black;"
                + "border-collapse: collapse;"
                + "}"
                + "</style>"
                +"<center>"
                + "<h2>Calculadora de producto interno matrices</h2>"
                + "  <br><br>"
				+ "<h3>Al multiplicar la matriz 1: "+Arrays.deepToString(m1)+"</h3>"
				+ "  <br><br>"
				+ "<h3>Por la matriz 2: "+Arrays.deepToString(m2)+"</h3>"
				+ "  <br><br>"
				+ "<h3>La matriz resultante es: "+Arrays.deepToString(tempo)+"</h3>"
                +"</center>"
                + "</body>"
                + "</html>";
        return view;
   }
    /**
     * este metodo apartir de un string que recibe construye una matriz de enteros y la retorna
     * @param cadena matriz en string
     * @return matriz de enteros
     */
    static int[][] getMatriz(String cadena){
    	String[] t1 = cadena.split(";");
    	String[] t2 = t1[0].split(",");
    	int[][] matriz= new int[t1.length][t2.length];
    	for(int i=0;i<t1.length;i++) {
    		String[] t3 = t1[i].split(",");
    		for(int j=0;j<t3.length;j++) {
    			String var = t3[j];
    			var=var.replace("{","");
    			var=var.replace("}","");
    			matriz[i][j] = Integer.parseInt(var);
    		}
    	}

    	return matriz;
    }
    /**
     *Este metodo se encarga de retonar el puerto por defecto que esta definido en una variable de entorno 
     *para correr el servidor web sobre ese puerto.
     */
    static int getPort() {
	   	 if (System.getenv("PORT") != null) {
	   		 return Integer.parseInt(System.getenv("PORT"));
	   	 }
	   	 return 5000; //returns default port if heroku-port isn't set
   }

}
