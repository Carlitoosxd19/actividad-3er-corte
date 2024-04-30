// Clase base Paquete
abstract class Paquete {
    protected double peso;
    protected double[] dimensiones;
    protected String direccionOrigen;
    protected String direccionDestino;

    public Paquete(double peso, double[] dimensiones, String direccionOrigen, String direccionDestino) {
        this.peso = peso;
        this.dimensiones = dimensiones;
        this.direccionOrigen = direccionOrigen;
        this.direccionDestino = direccionDestino;
    }

    public abstract double calcularCostoBase();
}

// Clase PaqueteEstandar
class PaqueteEstandar extends Paquete {
    public PaqueteEstandar(double peso, double[] dimensiones, String direccionOrigen, String direccionDestino) {
        super(peso, dimensiones, direccionOrigen, direccionDestino);
    }

    @Override
    public double calcularCostoBase() {
        return 10; // Implementación de cálculo base simplificada
    }
}

// Clase DocumentoUrgente
class DocumentoUrgente extends Paquete {
    private int tiempoEntrega; // Por ejemplo, 24 horas, 48 horas

    public DocumentoUrgente(double peso, double[] dimensiones, String direccionOrigen, String direccionDestino, int tiempoEntrega) {
        super(peso, dimensiones, direccionOrigen, direccionDestino);
        this.tiempoEntrega = tiempoEntrega;
    }

    @Override
    public double calcularCostoBase() {
        return 15; // Implementación de cálculo base simplificada para documentos urgentes
    }
}

// Clase ArticuloFragil
class ArticuloFragil extends Paquete {
    private boolean esFragil;

    public ArticuloFragil(double peso, double[] dimensiones, String direccionOrigen, String direccionDestino, boolean esFragil) {
        super(peso, dimensiones, direccionOrigen, direccionDestino);
        this.esFragil = esFragil;
    }

    @Override
    public double calcularCostoBase() {
        return 20; // Implementación de cálculo base simplificada para artículos frágiles
    }
}

// Clase ServicioEnvio
class ServicioEnvio {
    private double tarifaBasePorPeso;
    private double tarifaBasePorDimensiones;
    private double tarifaFragil;
    private double tarifaUrgente;

    public ServicioEnvio(double tarifaBasePorPeso, double tarifaBasePorDimensiones, double tarifaFragil, double tarifaUrgente) {
        this.tarifaBasePorPeso = tarifaBasePorPeso;
        this.tarifaBasePorDimensiones = tarifaBasePorDimensiones;
        this.tarifaFragil = tarifaFragil;
        this.tarifaUrgente = tarifaUrgente;
    }

    public double calcularCostoEnvio(Paquete paquete) {
        double costoBase = paquete.calcularCostoBase();
        double costo = costoBase + tarifaBasePorPeso * paquete.peso + tarifaBasePorDimensiones * sumarDimensiones(paquete.dimensiones);

        if (paquete instanceof DocumentoUrgente) {
            costo += tarifaUrgente;
        } else if (paquete instanceof ArticuloFragil) {
            costo += tarifaFragil;
        }

        return costo;
    }

    private double sumarDimensiones(double[] dimensiones) {
        double suma = 0;
        for (double dimension : dimensiones) {
            suma += dimension;
        }
        return suma;
    }
}

// Ejemplo de uso
public class Main {
    public static void main(String[] args) {
        double[] dimensionesPaquete = {10.0, 15.0, 20.0};
        Paquete paqueteEstandar = new PaqueteEstandar(5.0, dimensionesPaquete, "Ciudad A", "Ciudad B");
        DocumentoUrgente documentoUrgente = new DocumentoUrgente(2.0, dimensionesPaquete, "Ciudad C", "Ciudad D", 24);
        ArticuloFragil articuloFragil = new ArticuloFragil(8.0, dimensionesPaquete, "Ciudad E", "Ciudad F", true);

        ServicioEnvio servicioEnvio = new ServicioEnvio(0.5, 1.0, 5.0, 10.0);

        double costoPaqueteEstandar = servicioEnvio.calcularCostoEnvio(paqueteEstandar);
        double costoDocumentoUrgente = servicioEnvio.calcularCostoEnvio(documentoUrgente);
        double costoArticuloFragil = servicioEnvio.calcularCostoEnvio(articuloFragil);

        System.out.println("Costo de envío de paquete estándar: " + costoPaqueteEstandar);
        System.out.println("Costo de envío de documento urgente: " + costoDocumentoUrgente);
        System.out.println("Costo de envío de artículo frágil: " + costoArticuloFragil);
    }
}