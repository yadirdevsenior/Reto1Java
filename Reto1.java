import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Reto1 {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcionMenu;
        String planetaDestinoUsuario = "";
        String naveEspacialUsuario   = "";
        boolean mostrarMenuPrincipal = true;

        // Agregamos información de los planetas (nombre, descripción, distancia)
        Map<String, String[]> planetasDestino = new HashMap<>();        
        planetasDestino.put("Mercurio", new String[]{"Es el planeta más cercano al Sol, con temperaturas extremadamente altas.", "91.7"});
        planetasDestino.put("Venus", new String[]{"Conocido como el gemelo de la Tierra, con una atmósfera densa y cálida.", "41.4"});
        planetasDestino.put("Marte", new String[]{"Conocido como el planeta rojo debido a su superficie oxidada.", "78.3"});
        planetasDestino.put("Júpiter", new String[]{"El planeta más grande del sistema solar, con una atmósfera de gas.", "628.7"});
        planetasDestino.put("Saturno", new String[]{"Famoso por sus impresionantes anillos, es el segundo planeta más grande.", "1200"});
        planetasDestino.put("Urano", new String[]{"Un gigante helado con una atmósfera rica en hidrógeno y helio.", "2660"});
        planetasDestino.put("Neptuno", new String[]{"El planeta más distante del sistema solar, con fuertes vientos.", "4300"});

        // Creamos un mapa para almacenar la información de las naves espaciales
        Map<String, String[]> navesEspaciales = new HashMap<>();
        navesEspaciales.put("Nave Espacial 1", new String[]{"50000", "80", "10000", "5000"});
        navesEspaciales.put("Nave Espacial 2", new String[]{"80000", "50", "12000", "6000"});
        navesEspaciales.put("Mars Rover", new String[]{"50000", "4", "3000", "1500"});
        navesEspaciales.put("Juno", new String[]{"40000", "7", "4000", "2000"});
        navesEspaciales.put("Cassini", new String[]{"30000", "10", "3500", "1500"});
        navesEspaciales.put("Voyager 2", new String[]{"20000", "3", "2000", "1000"});
        
        
        while (mostrarMenuPrincipal) {

            mostrarMenuPrincipal();
            opcionMenu = scanner.nextInt();

            switch (opcionMenu) {
                case 1:

                    if (!planetaDestinoUsuario.isEmpty()) {
                        System.out.println("\nHas seleccionado un planeta destino. Si eliges otro, el actual(" + planetaDestinoUsuario + ") será reemplazado.");
                    }

                    planetaDestinoUsuario = obtenerPlanetaDestinoUsuario(scanner, planetasDestino);                    
                    break;

                case 2:

                    if (planetaDestinoUsuario.isEmpty()) {
                        System.out.println("\nNo has seleccionado el planeta destino. Selecciona un planeta destino: ");
                        planetaDestinoUsuario = obtenerPlanetaDestinoUsuario(scanner, planetasDestino);  
                    } else {
                        naveEspacialUsuario = obtenerNaveEspacialUsuario(scanner, navesEspaciales);    
                    }
                    break;

                case 3:

                    if (planetaDestinoUsuario.isEmpty()) {
                        System.out.println("Debes elegir un planeta como destino!!");
                    } else if (naveEspacialUsuario.isEmpty()) {
                        System.out.println("Debes elegir una nave espacial para llegar a " + planetaDestinoUsuario + "!!");
                    } else {
                        iniciarSimulacionViajeEspacial(scanner, planetaDestinoUsuario, naveEspacialUsuario, planetasDestino, navesEspaciales);
                    }

                    break;

                case 4:

                    System.out.println("\nSaliendo del programa");
                    mostrarMenuPrincipal = false;
                    break;

                default:
                    System.out.println("\nOpción Inválida");
            }

        }

        scanner.close();
    }

    
    public static void mostrarMenuPrincipal() {
        System.out.println("\n------------  MENU  ------------");
        System.out.println(" 1. Seleccionar un planeta destino");
        System.out.println(" 2. Seleccionar una nave espacial");
        System.out.println(" 3. Iniciar una simulación espacial");
        System.out.println(" 4. Salir del programa");
        System.out.println("\nDigite una opción (1-4):");
    }

    public static String obtenerPlanetaDestinoUsuario(Scanner scanner, Map<String, String[]> planetasDestino) {
        int index = 1;
        
        // Mostramos la lista de planetas
        System.out.println("\n------------  PLANETAS  ------------");
        for (String planeta : planetasDestino.keySet()) {
            System.out.println(index + ". " + planeta);
            index++;
        }

        System.out.println("\nSeleccione un planeta:");
        int planetaSeleccionado = scanner.nextInt();

        // Si el usuario selecciona un planeta qeu no es valido le mostramos un mensaje y se le da la opción de elegir nuevamente
        while (planetaSeleccionado < 1 || planetaSeleccionado > planetasDestino.size()) {
            System.out.println("\nOpción no válida, elija un destino válido (1 a " + planetasDestino.size() + "): ");
            planetaSeleccionado = scanner.nextInt();
        }

        // Mostramos al usuario el planeta seleccionado, descripción y la distancia
        String planeta = (String) planetasDestino.keySet().toArray()[planetaSeleccionado - 1];
        System.out.println("\nHas seleccionado: " + planeta);
        System.out.println("Descripción: " + planetasDestino.get(planeta)[0]);
        System.out.println("Distancia desde la Tierra: " + planetasDestino.get(planeta)[1] + " millones de Km");

        return planeta;
    }

    public static String obtenerNaveEspacialUsuario(Scanner scanner, Map<String, String[]> navesEspaciales) {
        int index = 1;

        // Mostramos la lista de naves espaciales con su velocidad y cantidad de pasajeros
        System.out.println("\n------------  NAVES ESPACIALES  ------------");
        for (String nave : navesEspaciales.keySet()) {
            String[] infoNave = navesEspaciales.get(nave);
            System.out.println(index + ". " + nave + " - Velocidad: " + infoNave[0] + " km/h, Pasajeros: " + infoNave[1] + "-" + " Combustible: " + infoNave[2] + " - Oxigeno: " + infoNave[3]);
            index++;
        }

        System.out.println("\nSeleccione una nave espacial:");
        int naveSeleccionada = scanner.nextInt();

        // Si el ususario digita una opción no valida de nave espacial, se le indica y se da la opción para que vuelva a escribir
        while (naveSeleccionada < 1 || naveSeleccionada > navesEspaciales.size()) {
            System.out.println("\nOpción no válida, elija una nave válida (1 a " + navesEspaciales.size() + "): ");
            naveSeleccionada = scanner.nextInt();
        }

        String nave = (String) navesEspaciales.keySet().toArray()[naveSeleccionada - 1];
        String[] infoNave = navesEspaciales.get(nave);

        // Se muestra la info de la nave seleccionada
        System.out.println("\nHas seleccionado: " + nave);
        System.out.println("Velocidad máxima: " + infoNave[0] + " km/h");
        System.out.println("Capacidad de pasajeros: " + infoNave[1]);
        return nave;
    }

    // Método para modificar el combustible y oxígeno de la nave
    public static void modificarRecursosNave(Scanner scanner, Map<String, String[]> navesEspaciales, String naveSeleccionada) {
        String[] datosNave = navesEspaciales.get(naveSeleccionada);
        System.out.println("\n ----- MODIFICANDO RECURSOS DE LA NAVE: " + naveSeleccionada + " -----");

        // Modificar combustible
        System.out.println("Combustible actual: " + datosNave[2] + " litros");
        System.out.print("Ingrese el nuevo valor de combustible (en litros): ");
        int nuevoCombustible = scanner.nextInt();
        datosNave[2]         = String.valueOf(nuevoCombustible); // Convertimos el valor que digito el usuario a String

        // Modificar oxígeno
        System.out.println("\nOxígeno actual: " + datosNave[3] + " litros");
        System.out.print("Ingrese el nuevo valor de oxígeno (en litros): ");
        int nuevoOxigeno = scanner.nextInt();
        datosNave[3]     = String.valueOf(nuevoOxigeno);  // Convertimos el valor que digito el usuario a String

        // Guardamos los cambios en el mapa
        navesEspaciales.put(naveSeleccionada, datosNave);
        System.out.println("\nRecursos modificados exitosamente.");
    }

    public static void iniciarSimulacionViajeEspacial(Scanner scanner, String planetaDestino, String naveEspacial, Map<String, String[]> planetasDestino, Map<String, String[]> navesEspaciales) {

        // Obtenemos los datos del planeta seleccionado por el usuario
        String[] datosPlaneta     = planetasDestino.get(planetaDestino);
        double distancia          = Double.parseDouble(datosPlaneta[1]);  // Distancia desde la tierra en millones de km al planeta
        String descripcionPlaneta = datosPlaneta[0]; // Descripción del planeta
    
        // Obtenemos los datos de la nave seleccionada
        String[] datosNave        = navesEspaciales.get(naveEspacial);
        int velocidad             = Integer.parseInt(datosNave[0]);  // Velocidad máxima de la nave en km/h
        int pasajeros             = Integer.parseInt(datosNave[1]);  // Capacidad de pasajeros de la nave
        int combustibleDisponible = Integer.parseInt(datosNave[2]);  // Combustible disponible en litros
        int oxigenoDisponible     = Integer.parseInt(datosNave[3]);  // Oxígeno disponible en litros
    
        System.out.println("\n------- INICIANDO SIMULACIÓN -------");
        System.out.println("Planeta destino: " + planetaDestino);
        System.out.println("Descripción: " + descripcionPlaneta);
        System.out.println("Nave espacial seleccionada: " + naveEspacial);
        System.out.println("Velocidad de la nave: " + velocidad + " km/h");
        System.out.println("Capacidad de pasajeros: " + pasajeros);
        System.out.println("Distancia a recorrer: " + distancia + " millones de km\n");
        System.out.println("Combustible disponible: " + combustibleDisponible + " litros");
        System.out.println("Oxígeno disponible: " + oxigenoDisponible + " litros");

    
        // Calculamos el tiempo estimado en horas para llegar al destino(planeta seleccionado)
        double tiempoEstimadoHoras = distancia * 1000000 / velocidad; // Convertimos la distancia a km y dividimos por la velocidad

        int consumoCombustible = (int)(distancia * 10);  // 10 litros de combustible por millón de km
        int consumoOxigeno     = (int)(distancia * 5);  // 5 litros de oxígeno por millón de km

        // Verificamos si hay suficiente combustible y oxígeno
        if (combustibleDisponible < consumoCombustible || oxigenoDisponible < consumoOxigeno) {
            System.out.println("\nNo tienes suficientes recursos para completar el viaje. Combustible disponible: " + combustibleDisponible + ", Oxigeno Disponible " + oxigenoDisponible + " de la nave espacial" + naveEspacial);

            scanner.nextLine();
            System.out.println("Desea cambiar la cantidad de Combustible y Oxigeno? (S/N).");
            char opcionUsuario = Character.toLowerCase(scanner.nextLine().charAt(0));

            while (opcionUsuario != 's' && opcionUsuario != 'n') {
                System.out.println("\nOpción no válida. Desea cambiar la cantidad de Combustible y Oxigeno? (S/N).");
                opcionUsuario = Character.toLowerCase(scanner.nextLine().charAt(0));
            }

            if (Character.toLowerCase(opcionUsuario) == 's') {
                modificarRecursosNave(scanner, navesEspaciales, naveEspacial);
            } else {
                System.out.println("\nNo puedes continuar con el viaje.");
            }
            

            return;
        }

        // Simulamos la disminución de recursos durante el viaje
        combustibleDisponible -= consumoCombustible;
        oxigenoDisponible     -= consumoOxigeno;

        System.out.println("\nEl tiempo estimado de viaje es de " + tiempoEstimadoHoras + " horas.");
        System.out.println("Recursos restantes: Combustible - " + combustibleDisponible + " litros, Oxígeno - " + oxigenoDisponible + " litros.\n");

            
        // Mostramos el tiempo total estimado para llegar al planeta
        System.out.println("\nEl tiempo estimado de viaje es de " + tiempoEstimadoHoras + " horas.\n");
    
        // Simulamos el viaje con eventos aleatorios
        Random rand = new Random();
        for (int porcentaje = 0; porcentaje <= 100; porcentaje += 10) {
            // Progreso del viaje
            if (porcentaje == 0) {
                System.out.println("Inicio del viaje...");
            } else if (porcentaje == 50) {
                System.out.println("Mitad del camino...");
            } else if (porcentaje == 100) {
                System.out.println("Llegada al destino: ¡Has llegado a " + planetaDestino + "!");
            } else {
                System.out.println("Progreso del viaje: " + porcentaje + "%");
            }

            // Evento aleatorio: fallo o mejora
            if (rand.nextInt(100) < 5) {  // 5% de probabilidad de fallo
                System.out.println("¡Advertencia! Fallo en el sistema. Se ha perdido 10 litros de combustible.");
                combustibleDisponible -= 10;
            } else if (rand.nextInt(100) < 10) {  // 10% de probabilidad de mejora
                System.out.println("¡Mejora en la eficiencia! Has ganado 5 litros de oxígeno.");
                oxigenoDisponible += 5;
            }

            // Verificación de recursos
            if (combustibleDisponible <= 0 || oxigenoDisponible <= 0) {
                System.out.println("\nNo tienes suficientes recursos para continuar el viaje.");
                break;
            }

            // Hacemos una pusa entre cada actualización para simular el paso del tiempo
            try {
                Thread.sleep(1000);   // Pausa de 1 segundo (1000 ms) para simular el avance
            } catch (InterruptedException e) {
                System.out.println("\nError en la simulación del viaje.");
            }
        }

    }

}