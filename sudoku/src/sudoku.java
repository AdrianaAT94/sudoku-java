import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class sudoku { 
	//función para saber si un valor es numérico
	public static boolean isNumeric(String cadena) {
        boolean resultado;
        try {
            Integer.parseInt(cadena);
            resultado = true;
        } catch (NumberFormatException excepcion) {
            resultado = false;
        }
        return resultado;
    }
	
	public static boolean isDoubleInt(double d) 	{
	       //select a "tolerance range" for being an integer
	       double TOLERANCE = 1E-5;
	       //do not use (int)d, due to weird floating point conversions!
	       return Math.abs(Math.floor(d) - d) < TOLERANCE;
	}
        
    public static void main(String[] args) { 
		hazsudoku sudoku = new hazsudoku(); //Objeto de la clase sudoku para hacer las funciones
		
		//si no hay parametros
		if(args.length == 0){
			String m[][] = { { "5", "3", "-", "-", "7", "-", "-", "-", "-" }, 
                    { "6", "-", "-", "1", "9", "5", "-", "-", "-" }, 
                    { "-", "9", "8", "-", "-", "-", "-", "6", "-" }, 
                    { "8", "-", "-", "-", "6", "-", "-", "-", "3" }, 
                    { "4", "-", "-", "8", "-", "3", "-", "-", "1" }, 
                    { "7", "-", "-", "-", "2", "-", "-", "-", "6" }, 
                    { "-", "6", "-", "-", "-", "-", "2", "8", "-" }, 
                    { "-", "-", "-", "4", "1", "9", "-", "-", "5" }, 
                    { "-", "-", "-", "-", "8", "6", "-", "7", "9" } 
                  };    

		   
			//convertimos la matriz de caracteres a matriz de enteros
		   int matriz[][] = new int[m.length][m.length];
		
			for (int f=0; f<m.length; f++){
				for (int c=0; c<m.length; c++) {
					if(!isNumeric(m[f][c])) 
						matriz[f][c] = 0;
					else 
						matriz[f][c] = Integer.parseInt(m[f][c]);
				}
			}
			
           System.out.println("\n     SUDOKU A RESOLVER"); 
           sudoku.dibujarSudoku(matriz); 

		   if (sudoku.resolverSudoku(matriz)) {
	            System.out.println("\n     SUDOKU RESUELTO"); 
		   		sudoku.dibujarSudoku(matriz); 
		   }
		   else {
		       System.out.println("No existe solución"); 
		   }
		}
		
		//si hay un parámetro
		else if(args.length == 1){
			String valor = args[0];
			File archivo = new File(valor);
			if (valor.equals("-t")) {
				String m[][] = { { "5", "3", "-", "-", "7", "-", "-", "-", "-" }, 
	                    { "6", "-", "-", "1", "9", "5", "-", "-", "-" }, 
	                    { "-", "9", "8", "-", "-", "-", "-", "6", "-" }, 
	                    { "8", "-", "-", "-", "6", "-", "-", "-", "3" }, 
	                    { "4", "-", "-", "8", "-", "3", "-", "-", "1" }, 
	                    { "7", "-", "-", "-", "2", "-", "-", "-", "6" }, 
	                    { "-", "6", "-", "-", "-", "-", "2", "8", "-" }, 
	                    { "-", "-", "-", "4", "1", "9", "-", "-", "5" }, 
	                    { "-", "-", "-", "-", "8", "6", "-", "7", "9" } 
	                  };    

			   
				//convertimos la matriz de caracteres a matriz de enteros
			   int matriz[][] = new int[m.length][m.length];
			
				for (int f=0; f<m.length; f++){
					for (int c=0; c<m.length; c++) {
						if(!isNumeric(m[f][c])) 
							matriz[f][c] = 0;
						else 
							matriz[f][c] = Integer.parseInt(m[f][c]);
					}
				}
				
	           System.out.println("\n     SUDOKU A RESOLVER"); 
	           sudoku.dibujarSudoku(matriz); 

			   if (sudoku.dibujarTraza(matriz)) {
		            System.out.println("\n     SUDOKU RESUELTO"); 
			   		sudoku.dibujarSudoku(matriz); 
			   }
			   else {
			       System.out.println("No existe solución"); 
			   }	
			}
			else if(valor.equals("-h")) {
		    	System.out.println("SINTAXIS: sudoku [-t][-h] [fichero entrada]\n"
		    			+ "-t Traza cada llamada recursiva y sus parámetros.\n"
		    			+ "-h Muestra esta ayuda\n"
		    			+ "[fichero entrada] Tabla inicial del Sudoku");	
		        System.exit(0);			
			}
			//comprobar si existe fichero en la ruta actual
			else if (archivo.exists()) {
			    try {
			       String tipodeArchivo = Files.probeContentType(archivo.toPath());
				   if(tipodeArchivo.equals("text/plain")) {
					   String m[][] = sudoku.leeArchivo(archivo);
						//tiene que haber más de un jugador
					   	if(!isDoubleInt(Math.sqrt(sudoku.lineas)) || Math.sqrt(sudoku.lineas)>10 || Math.sqrt(sudoku.lineas)<2) {
							System.out.println("Solo se admiten sudokus de los cuadrados de 2,3,4,5,6,7,8,9 y 10\n\n");
			            	System.exit(0);
		            	}
					   	else {											   
							//convertimos la matriz de caracteres a matriz de enteros
						   int matriz[][] = new int[sudoku.lineas][sudoku.lineas];
						
							for (int f=0; f<sudoku.lineas; f++){
								for (int c=0; c<sudoku.lineas; c++) {
									if(!isNumeric(m[f][c])) 
										matriz[f][c] = 0;
									else 
										matriz[f][c] = Integer.parseInt(m[f][c]);
								}
							}
							
				           System.out.println("\n     SUDOKU A RESOLVER"); 
				           sudoku.dibujarSudoku(matriz); 

						   if (sudoku.resolverSudoku(matriz)) {
					            System.out.println("\n     SUDOKU RESUELTO"); 
						   		sudoku.dibujarSudoku(matriz); 
						   }
						   else {
						       System.out.println("No existe solución"); 
						   } 
						}
				   }
			    }
			    catch (IOException ioException)	{
			        System.out.println("Error: " + ioException.getMessage());
		            System.exit(0);
			    }
			}
			//parametro incorrecto
			else {
	            System.out.println("Parámetro incorrecto");
	            System.exit(0);
	        }	
        }	
		
		//si hay dos parámetros
		else if(args.length == 2){
			String valor1 = args[0];
			String valor2 = args[1];
			File archivo = new File(valor2);
			if (valor1.equals("-t") && archivo.exists()) {
				try {
			       String tipodeArchivo = Files.probeContentType(archivo.toPath());
				   if(tipodeArchivo.equals("text/plain")) {
					   String m[][] = sudoku.leeArchivo(archivo);
						//tiene que haber más de un jugador
					   	if(!isDoubleInt(Math.sqrt(sudoku.lineas)) || Math.sqrt(sudoku.lineas)>10 || Math.sqrt(sudoku.lineas)<2) {
							System.out.println("Solo se admiten sudokus de los cuadrados de 2,3,4,5,6,7,8,9 y 10\n\n");
			            	System.exit(0);
		            	}
						else {							
							   
							//convertimos la matriz de caracteres a matriz de enteros
						   int matriz[][] = new int[sudoku.lineas][sudoku.lineas];
						
							for (int f=0; f<sudoku.lineas; f++){
								for (int c=0; c<sudoku.lineas; c++) {
									if(!isNumeric(m[f][c])) 
										matriz[f][c] = 0;
									else 
										matriz[f][c] = Integer.parseInt(m[f][c]);
								}
							}
							
				           System.out.println("\n     SUDOKU A RESOLVER"); 
				           sudoku.dibujarSudoku(matriz); 

						   if (sudoku.dibujarTraza(matriz)) {
					            System.out.println("\n     SUDOKU RESUELTO"); 
						   		sudoku.dibujarSudoku(matriz); 
						   }
						   else {
						       System.out.println("No existe solución"); 
						   }
						}
				   }
			    }
			    catch (IOException ioException)	{
			        System.out.println("Error: " + ioException.getMessage());
		            System.exit(0);
			    }
			}
			else {
	            System.out.println("Argumentos no válidos");
	            System.exit(0);						
			}			
		}
		
		//si hay más de dos parámetro
		else {
            System.out.println("Solo se permiten 2 parámetros de entrada como máximo");
            System.exit(0);
        }	
    } 
}