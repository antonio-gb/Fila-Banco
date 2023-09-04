import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class BancoSimulacion {
    public static void main(String[] args) {
        //Define el valor lambda (clientes por hora)
        double lambda = 5.0; // Por ejemplo, 5 clientes por hora

        //Simula la llegada de clientes durante 8 horas
        int horasSimuladas = 8;
        int clientesAtendidos = 0;
        int totalClientesLlegados = 0;

        for (int hora = 1; hora <= horasSimuladas; hora++) {
            int clientesLlegados = generarClientes(lambda);
            totalClientesLlegados += clientesLlegados;

            System.out.println("Hora " + hora + ": Llegaron " + clientesLlegados + " clientes.");

            //Implementa una cola para gestionar la atención de los clientes
            Queue<Integer> cola = new LinkedList<>();
            for (int i = 1; i <= clientesLlegados; i++) {
                cola.offer(i);
            }

            //Simula la atención de los clientes en las cajas
            int cajas = 4;
            int[] tiempoAtencion = new int[cajas];
            int clientesAtendidosEnHora = 0;
            int minutos=0;
            int segundos=0;
            int tiempototal=0;
            int tiempomin = 0;
            
  
            for(minutos=0;minutos<3;minutos++){
                for(segundos=0;segundos<3;segundos++){
            while (!cola.isEmpty()) {
                for (int i = 0; i < cajas; i++) {             
                    if (!cola.isEmpty() && (tiempoAtencion[i] <= tiempomin) && tiempototal < 60) {
                        int cliente = cola.poll();
                       int tiempoAtencionActual = generarTiempoAtencion();
                        
                       tiempototal = tiempototal + tiempoAtencionActual;
                        tiempoAtencion[i] = tiempoAtencionActual;
                        tiempomin = Math.min(tiempoAtencion[i], 6);
                        System.out.println("Caja " + (i + 1) + ": El cliente " + cliente + " fue atendido en " + tiempoAtencionActual + " segundos.");
                        clientesAtendidos++;
                        clientesAtendidosEnHora++;
                    }
                }

                // Simula el paso del tiempo
                for (int i = 0; i < cajas; i++) {
                    if (tiempoAtencion[i] > 0) {
                        tiempoAtencion[i]--;
                    }
                }
            }
            delaySegundo();
        }
        }

            System.out.println("Clientes atendidos en la hora " + hora + ": " + clientesAtendidosEnHora);
        }

        System.out.println("Clientes totales atendidos: " + clientesAtendidos);
        System.out.println("Clientes totales en el banco en las 8 horas: " + totalClientesLlegados);
    }

    // Genera una cantidad aleatoria de clientes llegados en una hora según la distribución de Poisson
    public static int generarClientes(double lambda) {
        Random rand = new Random();
        double L = Math.exp(-lambda);
        double p = 1.0;
        int k = 0;

        do {
            k++;
            p *= rand.nextDouble();
        } while (p > L);

        return k - 1;
    }

    // Genera un tiempo aleatorio de atención en el rango de 1 a 10 segundos
    public static int generarTiempoAtencion() {
        Random rand = new Random();
        return rand.nextInt(10) + 1;
    }

    private static void delaySegundo(){
        try{
            Thread.sleep(1000);
        }catch(InterruptedException e){}
    }
}