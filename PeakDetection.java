package transform_artigo2;

 /* #################*/
/* Desenvolvedor: Alex Barros dos Santos
 * Finalidade: Ser utilizado pelo grupo GERCOM_UFPA para extração de pontos fiduciais de um sinal 
 * de ECG diponível na plataforma Physionet.
 * Data:04/07/2018
 * Obs: Necessário adotar boas práticas de orientação a objeto para facilitar a adapação do código
 * */
 /* #################*/
import java.awt.List;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class PeakDetection {

	 public static void main(String args[]) throws IOException{
	        
		 
		 
		 /* #################*/
		 //Esse arquivo aqui terá todos os "picos" detectados pela subida de montanha
		 FileWriter arq = new FileWriter("/Users/alexsantos/Documents/IWMC2019/artigo/resultados/picosDetectados.csv");
	     PrintWriter gravarArq = new PrintWriter(arq);
	     /* #################*/
	     /* Esse arquivo tem picos ECG com o respectivo EMG junto*/
	     FileWriter arqEMG = new FileWriter("/Users/alexsantos/Documents/IWMC2019/artigo/resultados/picos_ECG_EMG.csv");
	     PrintWriter gravarArqEMG = new PrintWriter(arqEMG);
	     
	     /* #################*/
	     /* #################*/
	   //Esse arquivo aqui terá todos os QRS, são detectados em virtude do threshold escolhido para cada segmento/arquivo de input
	     FileWriter arqReS = new FileWriter("/Users/alexsantos/Documents/IWMC2019/artigo/resultados/picos_threshold.csv"
	       		+ ".csv");
	       PrintWriter gravarArqReS = new PrintWriter(arqReS);
	       /* #################*/
	       /* #################*/
	       //Esse arquivo já terá as informações estatisticas calculadas
	       FileWriter arqPerson = new FileWriter("/Users/alexsantos/Documents/IWMC2019/artigo/resultados/features_extratecd.csv");
	       PrintWriter gravarPerson = new PrintWriter(arqPerson);
	       /* #################*/
	       /* #################*/
	       //Esse for é usado para a leitura de diversos arquivos
	       //Imagine que cada arquivo de leitura representa uma tentativa de autenticação
	       //É necessário um pré-processamento dos arquivos disponíveis no physionet para que eles 
	       //possam ser usados nesse programa
	       //Esse pré-processamento é feito na Classe Transfor2
		 
	       
	       int quantArquivos=1;
	  //     int[] driver_inicial= {1,2,4,5,6,7,8,9,10,11,12,13,14,15,16};
	       int[] driver_inicial= {2};
	       int d_count=0;
	       for(int i=1; i<=quantArquivos;i++) {
			 /* #################*/ 	
	        
	        PeakDetection gerar = new PeakDetection();
	        
	        /* #################*/
	        //Esses são os arquivos de entrada
	        Scanner scanner1 = new Scanner(new FileReader("/Users/alexsantos/Documents/IWMC2019/artigo/driver"+driver_inicial[d_count]+"_3/driver"+driver_inicial[d_count]+"_ecg_emg_"+i+".csv"));
	        /* #################*/
	     //   d_count++;
	        
	         String [] array;            
	         String leitorDados;
	         /* #################*/
	         //Alternativa técnica para pular o cabeçalho
	         //Depois será dada uma solução mais sofisticada
	         leitorDados = scanner1.nextLine();
	 		 leitorDados = scanner1.nextLine();
	 		/* #################*/
	 		ArrayList<String[]> dados = new ArrayList<String[]>();
	 		ArrayList<String[]> gravar = new ArrayList<String[]>();
	 	
	 		/* #################*/
	 		//Bloco utilizado para realizar a leitura dos dados
	 		//Essa é a etapa de data acquisition
	 		//Todos os pontos vão para o ArrayList dados
	 		while (scanner1.hasNext() ) {
	            if(scanner1.hasNextLine()) {
	            	leitorDados = scanner1.nextLine();
	            	array= leitorDados.split(",");
	            	dados.add(array);
	            }      
	      //     System.out.println("Lendo"); 
	        }
	     
	 		/* #################*/
	 		//Esta função (PontosR) irá realizar a subida de montanha e detectar todos os máximos locais
	 		ArrayList<String[]> gravarTodosQRS = new ArrayList<String[]>();
	 		gravar=PeakDetection.pontosR(dados);
	 		//Esse laço grava o arquivo com todos os picos
	 		//Importante para plotar e demonstrar a aplicação
	 		//fins didáticos
	 		for(int j=0; j< gravar.size(); j++) {
	 			String [] xy = gravar.get(j);
	 			gravarArq.println(xy[0] +","+ xy[1]);
	 			//System.out.println(gravar.get(j).toString()); 
	 		}
	 		/* #################*/
	 		//Esta função (extractReS) irá aplicar o threshold para escolher somente os picos R, após isso irá buscar o Q e o S e devolver no Objeto
	 		gravarTodosQRS=PeakDetection.extractReS(gravar);
	 		
	 		
	 		
	 		
	 		//Esse laço grava o arquivo com somente os pontos Q,R e S devido ao limiar escolhido
	 		//Utilizar o arquivo para imagens em artigos
	 		for(int j=0; j< gravarTodosQRS.size(); j++) {
	 			String [] xy = gravarTodosQRS.get(j);
	 		//	System.out.println(xy[1]);
	 		//	System.out.println(xy[0] +","+ xy[1]);
	 			gravarArqReS.println(xy[0] +","+ xy[1]);
	 			System.out.println(gravarTodosQRS.get(j).toString()); 
	 		//	System.out.println("entrei");
	 		}
	 	
	 		//Esse laço grava o arquivo com somente os pontos Q,R e S devido ao limiar escolhido
	 		//Aqui estao as informações somente do EMG na mesma linha do tempo do ECG
	 		for(int j=0; j< gravarTodosQRS.size(); j++) {
	 			String [] xy = gravarTodosQRS.get(j);
	 		//	System.out.println(xy[1]);
	 		//	System.out.println(xy[0] +","+ xy[1]);
	 			gravarArqEMG.println(xy[0] +","+ xy[2]);
	 			System.out.println(gravarTodosQRS.get(j).toString()); 
	 		//	System.out.println("entrei");
	 		}
	 		
	 		
	 		
	 		
	 		
	 		/* #################*/
	 		//Finalmente, realizamos o cálculo de médias e desvio para cada ponto Q,R e S
	 		//Teremos então 6 features para análise
	 		//O código será adaptado no futuro para identificar outras features
	 		FeatureECG features = PeakDetection.statisticsECG(gravarTodosQRS);
	 		/* #################*/
	 		
	 		/* #################*/
	 		//Nesse trecho é gravado o arquivo com os dados estatisticos/features extraídas
	 		//Esse arquivo pode ser utilizado em um software de Aprendizado de Máquina
	 		gravarPerson.println("driver13"+","+features.meanQ+","+ features.meanR+","
	 		+ features.meanS+ ","+features.desvioQ+","+features.desvioR+","+features.desvioS
	 		+","+features.durationR+","+features.timeRR+","+features.rqAmp);
	 		/* #################*/ 
	 			//System.out.println("entrei");
	 		
	 		
	 		
	 		arqReS.close();
	 		arq.close();
	        scanner1.close();
	        }//final do for
		 arqPerson.close();
	    }//Final do main
	        
	 /* #################*/ 
	 /* #################*/ 
	 /* #################*/ 
	 /* #################*/ 
	public static ArrayList<String[]> pontosR(ArrayList<String[]> entrada){
		
		ArrayList<String[]> picosR = new ArrayList<String[]>();
		String [] inicio;
		String [] marcador;
		int controller = 0;
		//varre o array de entrada e compara os pontos
		for( int i=0; i< entrada.size()-1; i++) {
			inicio = entrada.get(i);
			marcador = entrada.get(i+1);
			if( inicio[1].compareTo(marcador[1]) <0) {
				inicio = marcador;	//subida
				controller=1;
			}
			else {
				//descida
	//		System.out.println("ponteiro"+ i);
			if((i+10) >= entrada.size()) {
			marcador = entrada.get(i);
			}
			else{
			marcador = entrada.get(i+2);
			}
			//em caso de continuar sendo maior, adionar ao picosR
			if(inicio[1].compareTo(marcador[1])>0 && controller ==1) {
					picosR.add(inicio);
					controller =0;
		}
			}
		}
		return picosR;
		
		
		
		
		
	}//final do pontosR
 	
	public static ArrayList<String[]> extractReS(ArrayList<String[]> entrada){

		
		ArrayList b = new ArrayList();
		ArrayList<String[]> R_e_S = new ArrayList<String[]>();
		for(int i=0; i< entrada.size(); i++) {
			
			String [] a= entrada.get(i);
			b.add(a[1]);//acrescenta no array b todos os dados do ECG
		}
		if(!b.isEmpty()) {
		String max = Collections.max(b).toString();//salva o maior valor de ECG, pico R
		System.out.println("maximo" + max);
        String [] a_onlypeaks = new String [entrada.size()];
        Double b_all = 0.0;
        int peaks_count = 0;
		for(int j=0; j < entrada.size(); j++) {
			b_all = Double.parseDouble(b.get(j).toString());//salva o valor do ECG
			if(b_all >= Double.parseDouble(max)/(1.5)) {//caso o valor esteja no limiar é salvo.
			a_onlypeaks[peaks_count++] = b.get(j).toString();
		//	System.out.println("qnt picos" + peaks_count);
			}
		}
	
		
		
		int k=0;
		for(int j=1; j < entrada.size()-1; j++) {
		    String [] valor = entrada.get(j);
		    //System.out.println("teste" + valor[0] +"," + valor[1]);
			if( valor[1].equals(a_onlypeaks[k])) {
				R_e_S.add(entrada.get(j-1));//adicionando Q
				R_e_S.add(entrada.get(j));//adicionando R
				R_e_S.add(entrada.get(j+1));//adicionando S
				k++;
			}
		
		}
		}
		else {
			
			
		}
			
		
		return R_e_S;
	}

    public static FeatureECG statisticsECG(ArrayList<String[]> entrada) {
    	FeatureECG features;
    	double mediaQ =0.0;
    	double mediaR =0.0;
    	double mediaS =0.0;
    	double desvioQ =0.0;
    	double desvioR =0.0;
    	double desvioS =0.0;
   	 /* #################*/ 
   	 /* #################*/ 
    	double durationR =0.0;
    	double qrsAmp =0.0;
    	
    	
   	 /* #################*/ 
   	 /* #################*/ 	
    	String[] q;
    	String[] r;
    	String[] s;
    	int distancia = entrada.size();
    	String[] r_to_r = new String[distancia/3];
    	int count_r=0;
    	double r_to_r_Media= 0.0;
    //	distancia=12;
    	for(int i=0; i< distancia-2; i=i+3) {
    		 q= entrada.get(i);
    		 r= entrada.get(i+1);
    		 s= entrada.get(i+2);
    		
    		
    				 
    				 
    		 System.out.println("pico Q"+ q[1]);
    		 System.out.println("pico R"+ r[1]);
    		 System.out.println("pico S"+ s[1]);
    		 
    		 /* #################*/ 
    		 /* #################*/ 
    		 //Novas Features
    		 durationR+=Double.parseDouble(s[0]) - Double.parseDouble(q[0]);
    		 System.out.println("tamanho do vetor" +distancia);
    		 r_to_r[count_r++]=r[0];
    		 
    		   		 
    		 /* #################*/ 
    		 /* #################*/ 
    		 
    		 mediaQ+=Double.parseDouble(q[1]);
    		 mediaR+=Double.parseDouble(r[1]);
    		 mediaS+=Double.parseDouble(s[1]);
    	}
    	System.out.println("distancia"+ distancia);
    	


    	mediaQ= mediaQ/(distancia/3);
    	mediaR= mediaR/(distancia/3);
    	mediaS= mediaS/(distancia/3);
    	System.out.println("mediaQ" + mediaQ);
    	System.out.println("mediaR" + mediaR);
    	System.out.println("mediaS" + mediaS);
		 /* #################*/ 
		 /* #################*/ 
    	durationR=durationR/(distancia/3);
    	qrsAmp=mediaR-mediaQ;
    	
    	for(int f=0; f<(distancia/3-1);f++) {
    		r_to_r_Media+= Double.parseDouble(r_to_r[f+1])-Double.parseDouble(r_to_r[f]);
    	}
    	r_to_r_Media=r_to_r_Media/(distancia/3-1);
    	//tempo de distancia entre picos R;
		 /* #################*/ 
		 /* #################*/ 
    	
    	
    	for(int i=0; i< distancia-3; i=i+3) {
   		 q= entrada.get(i);
   		 r= entrada.get(i+1);
   		 s= entrada.get(i+2);
   	//	 System.out.println("pico R"+ r[1]);
 //  		System.out.println("desvioR" + desvioR);
   	//	System.out.println("desvioS" + desvioS);
   	//	 System.out.println("pico S"+ s[1]);
   		 desvioQ+=  Math.pow((Double.parseDouble(q[1]) - mediaQ), 2);
   		 desvioR+=  Math.pow((Double.parseDouble(r[1]) - mediaR), 2);
   		 desvioS+=  Math.pow((Double.parseDouble(s[1]) - mediaS), 2);
   	}
    	 desvioQ= Math.sqrt((desvioQ/(distancia/3)));
   	     desvioR= Math.sqrt((desvioR/(distancia/3)));
   	     desvioS= Math.sqrt((desvioS/(distancia/3)));
   	     System.out.println("final");
   	     System.out.println("desvioQ" + desvioQ);
   	     System.out.println("desvioR" + desvioR);
    	 System.out.println("desvioS" + desvioS);
		features = new FeatureECG(mediaQ, mediaR, mediaS, desvioQ, desvioR, desvioS, durationR, r_to_r_Media, qrsAmp);
		System.out.println(features.meanQ+","+features.meanR+","+ features.meanS+","+features.desvioQ+","+features.desvioR+","+features.desvioS);
		return features;
    	
    }
    
   
    
    
    
    
    }




