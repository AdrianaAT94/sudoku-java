import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class hazsudoku { 
      
    int lineas = 0; //lineas que se leen del archivo en caso de haberlo
  
	//función para leer archivo
	public String[][] leeArchivo (File archivo) throws FileNotFoundException, IOException {
		String cadena;
		int a=0,c=0;
        FileReader f = new FileReader(archivo);
        BufferedReader b = new BufferedReader(f);
        //primero contamos las lineas
        while((cadena = b.readLine())!=null) {
        	if (!cadena.isEmpty()) {
        			lineas = lineas+1;
        	}
        }
        b.close();
        String matriz[][] = new String[lineas][lineas];
        try {
	        //volvemos a leer el archivo
	        FileReader f2 = new FileReader(archivo);
	        BufferedReader b2 = new BufferedReader(f2);
	        while((cadena = b2.readLine())!=null) {
	        	if (!cadena.isEmpty()) {
		            String[] values = cadena.split(" ");
		            //recorremos el array de string
		            for (int i=0; i<values.length; i++) {
		            	if (!values[i].isEmpty()) {
		            		matriz[a][c] = values[i];
		    	            c++;
		            	}
		            }
		            c=0;
		            a++;
	        	}
	        }
	    } catch (IOException | NumberFormatException e) {
	        e.printStackTrace();
	    }
        
		return matriz;
	}
    
    /*función que coge la cuadricula medio completa e intenta
     * asignar valores en todos los huecos sin duplicar números
     * en filas, columnas y cuadros.*/
    public boolean resolverSudoku(int[][] matriz) { 
    	
    	int fila = -1; 
        int col = -1; 
        boolean vacio = true; 
        for (int i=0; i<matriz.length; i++) { 
            for (int j=0; j<matriz.length; j++) { 
                if (matriz[i][j] == 0) { 
                	fila = i; 
                    col = j; 
                    /*todavia quedan valores*/
                    vacio = false; 
                    break; 
                } 
            } 
            if (!vacio) { 
                break; 
            } 
        } 
  
        /*no hay ningun espacio vacio*/
        if (vacio) { 
            return true; 
        } 
  
        /*vuelta atrás para cada fila*/
        for (int num=1; num<=matriz.length; num++) { 
            if (esSeguro(matriz, fila, col, num)) { 
            	matriz[fila][col] = num; 
                if (resolverSudoku(matriz)) { 
                    return true; 
                } 
                else { 
                    /*reemplazamos*/
                	matriz[fila][col] = 0; 
                } 
            } 
        } 
        return false; 
    } 
  
    /*funcion para dibujar el sudoku*/
    public void dibujarSudoku(int[][] matriz) { 
    	int i, j, grado = (int) Math.sqrt(lineas);
    	String rayas = "";
    	if (grado==2) {
    		rayas="  ______________";
    	}
    	else if(grado==3) {
    		rayas="  _______________________________";    		
    	}
    	else if(grado==4) {
    		rayas="  _______________________________________________________"; 
    	}
    	else if(grado==5) {
    		rayas="  ____________________________________________________________________________________"; 
    	}
    	else if(grado==6) {
    		rayas="  ___________"; 
    	}
    	else if(grado==7) {
    		rayas="  ___________"; 
    	}
    	else if(grado==8) {
    		rayas="  ___________"; 
    	}
    	else if(grado==9) {
    		rayas="  ___________"; 
    	}
    	else if(grado==10) {
    		rayas="  ___________"; 
    	}
    	
    	System.out.println(rayas); 
    	for (i=0; i<matriz.length; i++) { 
            for (j=0; j<matriz.length; j++) {
            	if (matriz[i][j]==0) {
            		if (j==0) {
	            		System.out.print(" | ");
	            	}
            		System.out.print("-");	            		
	            	if (j==grado-1) {
	            		System.out.print("  | ");
	            	}
	            	else if (grado>2 && j==(grado*2)-1) {
		        		System.out.print("  | ");
	            	}
	            	else if (grado>3 && j==(grado*3)-1) {
		        		System.out.print("  | ");
	            	}
	            	else if (grado>4 && j==(grado*4)-1) {
		        		System.out.print("  | ");
	            	}
	            	else if (grado>5 && j==(grado*5)-1) {
		        		System.out.print("  | ");
	            	}
	            	else if (grado>6 && j==(grado*6)-1) {
		        		System.out.print("  | ");
	            	}
	            	else if (grado>7 && j==(grado*7)-1) {
		        		System.out.print("  | ");
	            	}
	            	else if (grado>8 && j==(grado*8)-1) {
		        		System.out.print("  | ");
	            	}
	            	else if (grado>9 && j==(grado*9)-1) {
		        		System.out.print("  | ");
	            	}
	            	else if (grado>10 && j==(grado*10)-1) {
		        		System.out.print("  | ");
	            	}
	            	else if (j==matriz.length-1) {
		        		System.out.print(" | ");
	            	}
	            	else {
	            		System.out.print("  "); 
	            	}
            	}
            	else if (matriz[i][j]>9) {
            		if (j==0) {
	            		System.out.print(" | ");
	            	}
            		System.out.print(matriz[i][j]);	            		
	            	if (j==grado-1) {
	            		System.out.print(" | ");
	            	}
	            	else if (grado>2 && j==(grado*2)-1) {
	            		System.out.print(" | ");
	            	}
	            	else if (grado>3 && j==(grado*3)-1) {
	            		System.out.print(" | ");
	            	}
	            	else if (grado>4 && j==(grado*4)-1) {
	            		System.out.print(" | ");
	            	}
	            	else if (grado>5 && j==(grado*5)-1) {
	            		System.out.print(" | ");
	            	}
	            	else if (grado>6 && j==(grado*6)-1) {
	            		System.out.print(" | ");
	            	}
	            	else if (grado>7 && j==(grado*7)-1) {
	            		System.out.print(" | ");
	            	}
	            	else if (grado>8 && j==(grado*8)-1) {
	            		System.out.print(" | ");
	            	}
	            	else if (grado>9 && j==(grado*9)-1) {
	            		System.out.print(" | ");
	            	}
	            	else if (grado>10 && j==(grado*10)-1) {
	            		System.out.print(" | ");
	            	}
	            	else if (j==matriz.length-1) {
		        		System.out.print("| ");
	            	}
	            	else {
	            		System.out.print(" "); 
	            	}
            	}
            	else {
            		if (j==0) {
	            		System.out.print(" | ");
	            	}
            		System.out.print(matriz[i][j]);            		
	            	if (j==grado-1) {
	            		System.out.print("  | ");
	            	}
	            	else if (grado>2 && j==(grado*2)-1) {
		        		System.out.print("  | ");
	            	}
	            	else if (grado>3 && j==(grado*3)-1) {
		        		System.out.print("  | ");
	            	}
	            	else if (grado>4 && j==(grado*4)-1) {
		        		System.out.print("  | ");
	            	}
	            	else if (grado>5 && j==(grado*5)-1) {
		        		System.out.print("  | ");
	            	}
	            	else if (grado>6 && j==(grado*6)-1) {
		        		System.out.print("  | ");
	            	}
	            	else if (grado>7 && j==(grado*7)-1) {
		        		System.out.print("  | ");
	            	}
	            	else if (grado>8 && j==(grado*8)-1) {
		        		System.out.print("  | ");
	            	}
	            	else if (grado>9 && j==(grado*9)-1) {
		        		System.out.print("  | ");
	            	}
	            	else if (grado>10 && j==(grado*10)-1) {
		        		System.out.print("  | ");
	            	}
	            	else if (j==matriz.length-1) {
		        		System.out.print(" | ");
	            	}
	            	else {
	            		System.out.print("  "); 
	            	}
            	}	            	
            }
            if (i==grado-1) {
            	System.out.println("\n"+rayas); 
            }
        	else if (grado>2 && i==(grado*2)-1) {
            	System.out.println("\n"+rayas);
        	}
        	else if (grado>3 && i==(grado*3)-1) {
            	System.out.println("\n"+rayas);
        	}
        	else if (grado>4 && i==(grado*4)-1) {
            	System.out.println("\n"+rayas);
        	}
        	else if (grado>5 && i==(grado*5)-1) {
            	System.out.println("\n"+rayas);
        	}
        	else if (grado>6 && i==(grado*6)-1) {
            	System.out.println("\n"+rayas);
        	}
        	else if (grado>7 && i==(grado*7)-1) {
            	System.out.println("\n"+rayas);
        	}
        	else if (grado>8 && i==(grado*8)-1) {
            	System.out.println("\n"+rayas);
        	}
        	else if (grado>9 && i==(grado*9)-1) {
            	System.out.println("\n"+rayas);
        	}
        	else if (grado>10 && i==(grado*10)-1) {
            	System.out.println("\n"+rayas);
        	}
        	else if (i==matriz.length-1) {
            	System.out.println("\n"+rayas);
        	}
            else {
            	System.out.println(); 
            }
        }   	
    } 
    
    /*comprobamos si es seguro asignar un número a la fila, columna dada*/
    public boolean esSeguro(int[][] matriz, int fila, int col, int num) { 
    	int grado = (int) Math.sqrt(lineas);
          
        /*comprobamos si encontramos el mismo número en la fila similar y devolvemos false */
        for (int x=0; x<=matriz.length-1; x++) 
            if (matriz[fila][x] == num) 
                return false; 

        /*comprobamos si encontramos el mismo número en la columna similar y devolvemos false */
        for (int x=0; x<=matriz.length-1; x++) 
            if (matriz[x][col] == num) 
                return false; 
  
        /*comprobamos si encontramos el mismo número en la matriz 3*3 y devolvemos false */
        int filaInicio = fila-fila%grado, columnaInicio = col-col%grado; 
        for (int i=0; i<grado; i++) 
            for (int j=0; j<grado; j++) 
                if (matriz[i+filaInicio][j+columnaInicio] == num) 
                    return false; 
        return true; 
    }   
    
    public boolean dibujarTraza(int[][] matriz) { 
    	dibujarSudoku(matriz);
    	int fila = -1; 
        int col = -1; 
        boolean vacio = true; 
        for (int i=0; i<matriz.length; i++) { 
            for (int j=0; j<matriz.length; j++) { 
                if (matriz[i][j] == 0) { 
                	fila = i; 
                    col = j; 
                    /*todavia quedan valores*/
                    vacio = false; 
                    break; 
                } 
            } 
            if (!vacio) { 
                break; 
            } 
        } 
  
        /*no hay ningun espacio vacio*/
        if (vacio) { 
            return true; 
        } 
  
        /*vuelta atrás para cada fila*/
        for (int num=1; num<=matriz.length; num++) { 
        	System.out.print(num+": ");
            if (esSeguroTraza(matriz, fila, col, num)) { 
            	matriz[fila][col] = num; 
                if (dibujarTraza(matriz)) { 
                    return true; 
                } 
                else { 
                    /*reemplazamos*/
                	matriz[fila][col] = 0; 
                } 
            } 
            else {
            	System.out.println("no valido");
            }
        } 
        return false; 
    }
    
    /*comprobamos si es seguro asignar un número a la fila, columna dada*/
    public boolean esSeguroTraza(int[][] matriz, int fila, int col, int num) { 
    	int grado = (int) Math.sqrt(lineas);
          
        /*comprobamos si encontramos el mismo número en la fila similar y devolvemos false */
        for (int x=0; x<=matriz.length-1; x++) 
            if (matriz[fila][x] == num) 
                return false; 

        /*comprobamos si encontramos el mismo número en la columna similar y devolvemos false */
        for (int x=0; x<=matriz.length-1; x++) 
            if (matriz[x][col] == num) 
                return false; 
         
        /*comprobamos si encontramos el mismo número en la matriz 3*3 y devolvemos false */
        int filaInicio = fila-fila%grado, columnaInicio = col-col%grado; 
        for (int i=0; i<grado; i++) 
            for (int j=0; j<grado; j++) 
                if (matriz[i+filaInicio][j+columnaInicio] == num) 
                    return false; 
    	System.out.println("valido"); 
        return true; 
    } 
}