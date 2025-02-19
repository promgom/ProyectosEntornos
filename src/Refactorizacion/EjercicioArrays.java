package Refactorizacion;

import java.util.Arrays;
import java.util.List;

public class EjercicioArrays {

    public static void main(String[] args) {
        int numAlumnos = 40;
        //vector con las notas generadas
        Integer[] control = new Integer[numAlumnos];
        int[] listaClase;
        int[] practicas;
        float[] calificaciones;
        int[] aprobados;
        int[] suspensos;
        int maxNota = 0;
        int minNota = 0;
        int indMaxNota, indMinNota;
        double[] calif;
        //Genera notas random entre 1 y 10
        for(int i=0; i < control.length; i++){
            control[i] = (int)(Math.random()*11);
        }
        //buscamos al mayor
        minNota = buscarNotaMenor(control, minNota);
        //buscamos al menor
        maxNota = buscarNotaMayor(control, maxNota);
        //creamos una lista de los alumnos de la clase
        listaClase = new int[numAlumnos];
        for (int i = 0; i < numAlumnos; i++){
            listaClase[i] = i+1;
        }
        //Empezamos el uso de listas para facilitar la tarea de índices.
        List notas = Arrays.asList(control);
        indMinNota = notas.indexOf(minNota) + 1;
        indMaxNota = notas.indexOf(maxNota) + 1;

        //Comprobamos el resultado del ejercicio
        //System.out.println("Resultado de notas ----------------------");
        mostrarResultado(minNota, maxNota, indMinNota, indMaxNota, listaClase, notas);

        //creamos el array de notas "practicas"
        practicas = new int[numAlumnos];
        for(int i=0; i < practicas.length; i++){
            practicas[i] = (int)(Math.random()*11);
        }
        //System.out.println("Prácticas y calificaciones --------------------");
        //Creamos el vector calificaciones
        calificaciones = obtenerCalificaciones(numAlumnos, control, practicas);
        //System.out.println("Estadísticas -------------------------");
        //Sacamos la estadística de calificaciones
        //hacemos un array de 10 para la estadística.
        estadisticaCalificaciones(control, calificaciones, numAlumnos);
        //System.out.println("Aprobados y suspensos ---------------");
        //Aprobados y suspensos
        aprobados = new int[numAlumnos];
        suspensos = new int[numAlumnos];
        int countAprobados = 0;
        int countSuspensos = 0;
        for (int i=0; i<numAlumnos; i++){
            if (calificaciones[i] < 5){
                aprobados[i] = i;
                countAprobados += 1;
            }else{
                suspensos[i] = i;
                countSuspensos += 1;
            }
        }
        System.out.println("Relación de aprobados por nº de lista: "
                + Arrays.toString(aprobados));
        System.out.println("Relación de suspensos por nº de lista: "
                + Arrays.toString(suspensos));

        //System.out.println("Resumen de aprobados y suspensos -------------");
        //Resumen de aprobados y suspensos
        resumenAprobadosSuspensos(countAprobados, countSuspensos, aprobados, suspensos);

        /*6. Suponer un vector de Calificaciones de tamaño 40
        (máximo de alumnos por clase), pero que solo almacena las
        notas de 31 alumnos. Realizar un programa que permita insertar en
        la posición 4 del vector la calificación de un nuevo
        alumno en clase al que supuestamente le corresponde como nota un 6.*/
        calif = new double[40];
        for (int j=0; j<31; j++){
            calif[j] = (int)(Math.random()*11);
        }
        System.out.println("Nota antigua alumno nº4: " + calif[3]);
        calif[3] = 6;
        System.out.println("Nota nueva alumno nº4: " + calif[3]);
    }

    //Metodo para buscar la nota menor
    private static int buscarNotaMenor(Integer[] control, int minNota) {
        int postEval;
        postEval = 11;
        for(int i = 0; i< control.length; i++){
            int preEval = control[i];
            if (preEval < postEval){
                minNota = preEval;
                postEval = control[i];
            }
        }
        return minNota;
    }

    //Metodo para buscar la nota mayor
    private static int buscarNotaMayor(Integer[] control, int maxNota) {
        int postEval;
        postEval = 0;
        for(int i = 0; i< control.length; i++){
            int preEval = control[i];
            if (preEval > postEval){
                maxNota = preEval;
                postEval = control[i];
            }
        }
        return maxNota;
    }

    //Metodo para mostrar por consola la nota máxima, mínima, los índices de ambas notas y las listas con las notas
    private static void mostrarResultado(int minNota, int maxNota, int indMinNota, int indMaxNota, int[] listaClase, List notas) {
        System.out.println("Mínimo es: " + minNota);
        System.out.println("Máximo es: " + maxNota);
        System.out.println("Indice del mínimo es : " + indMinNota);
        System.out.println("Indice del máximo es : " + indMaxNota);
        System.out.println("Lista de clase :" + Arrays.toString(listaClase));
        System.out.println("Array de Notas :" + notas);
    }

    //Metodo para crear un array de calificaciones y mostrarlas por consola
    private static float[] obtenerCalificaciones(int numAlumnos, Integer[] control, int[] practicas) {
        float[] calificaciones;
        calificaciones = new float[numAlumnos];
        for(int i = 0; i< control.length; i++){
            calificaciones[i] =
                    (((float) control[i]
                            + (float) practicas[i])
                            / 2);
        }
        System.out.println("Prácticas      :" + Arrays.toString(practicas));
        System.out.println("Calificaciones :" + Arrays.toString(calificaciones));
        return calificaciones;
    }

    //Metodo que saca la estadistica de la media de las calificaciones
    private static void estadisticaCalificaciones(Integer[] control, float[] calificaciones, int numAlumnos) {
        float[] estadistica;
        estadistica = new float[10];

        for (int i=0; i<10; i++){
            float count = 0;
            for (int j = 0; j< control.length; j++){
                if ((i < calificaciones[j]) && ((i+1) >= calificaciones[j] )) {
                    count += 1;
                }
            }
            if (count != 0){
                estadistica[i] = (count / numAlumnos);
            }else{ estadistica[i] = 0;}
            double sol = (Math.round(estadistica[i] * 10000.0)) / 100.0;
            System.out.println("Estadística nota tramo <="
                    + (i+1) + " = "
                    + sol + "%");
        }
    }

    //Metodo para mostrar por consola el resumen de los aprobados y los suspensos por números de lista
    private static void resumenAprobadosSuspensos(int countAprobados, int countSuspensos, int[] aprobados, int[] suspensos) {
        int i = 0;
        int x = 0;
        int[] a = new int[countAprobados];
        int[] s = new int[countSuspensos];
        while(i < aprobados.length){
            if(aprobados[i] != 0){
                a[x] = aprobados[i];
                i++;
                x++;
            }else{ i++; }
        }

        i = x = 0;
        while(i < suspensos.length){
            if(suspensos[i] != 0){
                s[x] = suspensos[i];
                i++;
                x++;
            }else{ i++; }
        }
        System.out.println("Resumen  de aprobados por nº de lista: "
                + Arrays.toString(a));
        //He cambiado el "aprobados" por "suspensos"
        System.out.println("Resumen  de suspensos por nº de lista: "
                + Arrays.toString(s));
    }
}
