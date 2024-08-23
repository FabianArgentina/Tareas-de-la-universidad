import java.util.Scanner;

class Persona {
    private String nombre;
    private int edad;

    public Persona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }
}

class Empleado extends Persona {
    private double sueldo;

    public Empleado(String nombre, int edad, double sueldo) {
        super(nombre, edad);
        this.sueldo = sueldo;
    }

    public double getSueldo() {
        return sueldo;
    }
}

class Cliente extends Persona {
    private boolean esMayorista;

    public Cliente(String nombre, int edad, boolean esMayorista) {
        super(nombre, edad);
        this.esMayorista = esMayorista;
    }

    public boolean esMayorista() {
        return esMayorista;
    }
}

class Producto {
    private String nombre;
    private double precio;
    private int cantidad;

    public Producto(String nombre, double precio, int cantidad) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void reducirCantidad(int cantidadComprada) {
        this.cantidad -= cantidadComprada;
    }
}

class Caja {
    private Empleado empleado;

    public Caja(Empleado empleado) {
        this.empleado = empleado;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public double procesarCompra(Cliente cliente, Producto[] productos, int[] cantidades) {
        double total = 0;

        for (int i = 0; i < productos.length; i++) {
            Producto producto = productos[i];
            int cantidadComprada = cantidades[i];
            total += producto.getPrecio() * cantidadComprada;
            producto.reducirCantidad(cantidadComprada);
        }

        if (cliente.esMayorista()) {
            total *= 0.8; 
        }

        return total;
    }
}

class Transaccion {
    private Cliente cliente;
    private Producto[] productos;
    private int[] cantidades;
    private double total;
    private Caja caja;

    public Transaccion(Cliente cliente, Producto[] productos, int[] cantidades, double total, Caja caja) {
        this.cliente = cliente;
        this.productos = productos;
        this.cantidades = cantidades;
        this.total = total;
        this.caja = caja;
    }

    public void mostrarDetalles() {
        System.out.println("Cliente: " + cliente.getNombre());
        System.out.println("Atendido por: " + caja.getEmpleado().getNombre());
        System.out.println("Productos comprados:");
        for (int i = 0; i < productos.length; i++) {
            Producto producto = productos[i];
            int cantidadComprada = cantidades[i];
            System.out.println("- " + producto.getNombre() + " x" + cantidadComprada + " - $" + producto.getPrecio() * cantidadComprada);
        }
        System.out.println("Total a pagar: $" + total);
    }
}

public class Supermercado {
    public static void main(String[] args) {
        Scanner pregunta = new Scanner(System.in);
        Empleado empleado1 = new Empleado("Ana", 30, 80000);

        System.out.println();
        System.out.println("Es usted un cliente mayorista? seleccione 1:Si o 2:No");
        String respuesta = pregunta.nextLine();

        boolean mayorista = false;
        if (respuesta.equals("1") || respuesta.equalsIgnoreCase("Si")) {
            mayorista = true;
            System.out.println("Muy bien, lo tendremos registrado como mayorista");
        }

        System.out.println("Cual es su nombre para registrarlo?");
        String clienteNombre = pregunta.next();

        Cliente cliente1 = new Cliente(clienteNombre, 45, mayorista);

        Producto[] productos1 = {
            new Producto("Leche", 1.1, 1000),
            new Producto("Pan", 1800, 543),
            new Producto("Fernet", 3000.50, 504380)
        };

        int[] cantidades = new int[productos1.length];
        for (int i = 0; i < productos1.length; i++) {
            System.out.println("¿Cuánta cantidad de " + productos1[i].getNombre() + " desea comprar?");
            cantidades[i] = pregunta.nextInt();
        }

        Caja caja1 = new Caja(empleado1);
        double total = caja1.procesarCompra(cliente1, productos1, cantidades);

        Transaccion transaccion1 = new Transaccion(cliente1, productos1, cantidades, total, caja1);
        transaccion1.mostrarDetalles();

        pregunta.close();
    }
}
