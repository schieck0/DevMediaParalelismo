package devmedia.ex1;

public class ExemploSequencial {

    public static void main(String[] args) {
        long valorInicial = 0;
        long valorFinal = 3000;
        long total = 0;

        for (long i = valorInicial; i <= valorFinal; i++) {
            total += i;
        }

        System.out.println("Total: " + total);
    }
}
