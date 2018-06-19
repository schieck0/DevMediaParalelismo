package devmedia.ex1;

import java.util.ArrayList;
import java.util.Collection;

public class ExemploUso4 {

    public static void main(String[] args) {
        //armazena o tempo inicial
        long ti = System.currentTimeMillis();
        
        //armazena a quantidade de nucleos de processamento disponiveis
        int numThreads = Runtime.getRuntime().availableProcessors();

        //intervalo de busca predeterminado 
        int valorInicial = 1;
        int valorFinal = 1000000;

        //lista para armazenar os numeros primos encontrados pelas threads
        Collection<Long> primos = new ArrayList<>();
        
        //lista de threads
        Collection<Thread> threads = new ArrayList<>();
        
        int trabalho = valorFinal/valorInicial;
        
        //cria threads conforme a quantidade de nucleos
        for (int i = 1; i <= numThreads; i++) {
            //trab é a quantidade de valores que cada thread irá calcular
            int trab = Math.round(trabalho / numThreads);
            
            //calcula o valor inicial e final do intervalo de cada thread
            int fim = trab * i;
            int ini = (fim - trab) + 1;
            
            //cria a thread passando por parametro um objeto da classe CalculaPrimos2 que implementa Runnable
            Thread thread = new Thread(new CalculaPrimos2(ini, fim, primos));
            //define um nome para a thread
            thread.setName("Thread "+i);
            threads.add(thread);
        }
        
        //percorre as threads criadas iniciando-as
        for (Thread th : threads) {
            th.start();
        }
        
        //aguarda todas as threads finalizarem o processamento
        for (Thread th : threads) {
            try {
                th.join();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        
        //imprime os numeros primos encontrados por todas as threads
        for (Long primo : primos) {
            System.out.println(primo);
        }
        
        //calcula e imprime o tempo total gasto
        System.out.println("tempo: "+(System.currentTimeMillis()-ti));
    }
}
