package transform_artigo2;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class Transfor2 {

    public static void main(String args[]) throws IOException{
    	//new Scanner(new FileReader(
        for(int i=1; i<3;i++) {
        Scanner scanner1 = new Scanner(new FileReader("/Users/alexsantos/Documents/IWMC2019/artigo/driver"+i+".csv"));
    //	Scanner scanner1 = new Scanner(new FileReader("/transform_artigo2/src/transform_artigo2/driver1.csv"));
    	String [] array;            
        String leitorDados; 
        leitorDados = scanner1.nextLine();
 		leitorDados = scanner1.nextLine();
 		Double k =5.0;
 		Double comparador;
 		int contador_arquivos=;
 		//o numero magico 501 representa a quantidade de arquivos que desejo gerar menos um
 		while (scanner1.hasNextLine()) {
 			contador_arquivos++;
 			FileWriter arq = new FileWriter("/Users/alexsantos/Documents/IWMC2019/artigo/driver"+i+"_5/driver"+i+"_ecg_emg_"+contador_arquivos+".csv");
 	        PrintWriter gravarArq = new PrintWriter(arq);
 	     //   k++;
            gravarArq.printf("'Elapsed time','ECG','EMG\n" + 
            		"'seconds','mV','mV'\n");
            System.out.println("entrou aqui, arquivo numero: "+contador_arquivos );
            	
            //o numero magico 2480 representa 5 segundos de coleta.
          //  for(int i=0; i<2480; i++) {
            leitorDados = scanner1.nextLine();
            String [] dados=leitorDados.split(",");
            comparador=Double.parseDouble(dados[0]);
            
            System.out.println("comparador: "+comparador);
            System.out.println("k controle dos segundos: "+k);
            
            
            while(scanner1.hasNextLine() && comparador<=k) {	
          	gravarArq.println(dados[0]+","+ dados[1]+"," + dados[2]);
         //  	gravarArq.println(dados[0]+","+ dados[1]+"," + "0.00");
            	leitorDados = scanner1.nextLine();
            	dados=leitorDados.split(",");
            	comparador=Double.parseDouble(dados[0]);
            	System.out.println("comparador: "+comparador);
                System.out.println("k controle dos segundos: "+k);
                
            }
           // System.out.println("comparador: "+comparador);
          //  System.out.println("k controle dos segundos: "+k);
            
            k=k+5.0;
            arq.close();
      //  }
 		}  
 		scanner1.close();
    }//fim do for
        
    }
        
      
    
    public double calculaTempo(String linhaLida) {
    	
    	String[] arrayLinha = linhaLida.split(",");
    	ArrayList<String[]> arrayLinha2 = null;
    	arrayLinha2.add(arrayLinha);
    	
    	
    	return 0;
 		
 		
    	
    	
    }
    
 
    }

    
