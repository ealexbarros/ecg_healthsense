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


public class TransformHora {

    public static void main(String args[]) throws IOException{
    	//new Scanner(new FileReader(
        
        Scanner scanner1 = new Scanner(new FileReader("/Users/alexsantos/Documents/IWMC2019/artigo/driver17b.csv"));
    //	Scanner scanner1 = new Scanner(new FileReader("/transform_artigo2/src/transform_artigo2/driver1.csv"));
    	String [] array;            
        String leitorDados; 
        leitorDados = scanner1.nextLine();
 		leitorDados = scanner1.nextLine();
 		int k =1;
 		//o numero magico 501 representa a quantidade de arquivos que desejo gerar menos um
 		//while (scanner1.hasNext() && k < 501) {
 			
 			FileWriter arq = new FileWriter("/Users/alexsantos/Documents/IWMC2019/artigo/driver17b_ecg_emg.csv");
 	        PrintWriter gravarArq = new PrintWriter(arq);
 	        k++;
            gravarArq.printf("'Elapsed time','ECG','EMG\n" + 
            		"'seconds','mV','mV'\n");
            System.out.println("entrou aqui");
            	
            //o numero magico 2480 representa 5 segundos de coleta.
          //  for(int i=0; i<2480; i++) {
            while(scanner1.hasNextLine()) {
     
            	leitorDados = scanner1.nextLine();
            	String [] dados=leitorDados.split(",");
            	gravarArq.println(dados[0]+","+ dados[1]+"," + dados[2]);
            //	gravarArq.println(dados[0]+","+ dados[1]+"," + "0.00");
            }
                    
            
          
            arq.close();
      //  }
         
         scanner1.close();
        
         
        
    }
        
      
    
    public double calculaTempo(String linhaLida) {
    	
    	String[] arrayLinha = linhaLida.split(",");
    	ArrayList<String[]> arrayLinha2 = null;
    	arrayLinha2.add(arrayLinha);
    	
    	
    	return 0;
 		
 		
    	
    	
    }
    
    }

    
