package edu.escuelaing.arem.calculadora;

import java.util.Arrays;

public class CalculadoraProductoInterno {
	/**
	 * Esta clase es la clase correspondiente a un calculadora que se encargara del calculo del producto interno de dos matrices
	 */

	public CalculadoraProductoInterno() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Este metodo se encarga del calculos del producto interno de dos matrices m1 y m2 que recibe
	 * 
	 * @param m1 matriz 1
	 * @param m2 matriz 2
	 * @return respuesta matriz resultante
	 */
	public int[][] calcularProductoInterno(int[][] m1,int[][] m2){
		int respuesta[][]=new int[m1.length][m2[0].length];
		for(int i=0;i<m1.length;i++) {
			for(int j=0;j<m2[0].length;j++) {
				int[] fila2= extraerColumna(m2,j);
				respuesta[i][j]=productoEscalar(m1[i],fila2);
			}
		}
		return respuesta;
	}
	/**
	 * Este metodo se encraga de retornar la columna de una matriz dado un indice
	 * 
	 * @param m matriz
	 * @param index indice de la matriz
	 * @return vector correspondiente a la columna de la matriz de cierto indice
	 */
	public int[] extraerColumna(int[][] m,int index) {
		int[] vector = new int[m[0].length];
		for(int i=0;i<m.length;i++) {
			for(int j=0;j<m[0].length;j++) {
				if(j==index) {
					vector[i]=m[i][j];
				}
			}
		}
		return vector;
	}
	/**
	 * Este metodo se  encarga del calculo del producto escalar enter dos vectore v1 y v2
	 * 
	 * @param v1 vector 1
	 * @param v2 vector 2
	 * @return  cont resultado del producto escalar
	 */
	public int productoEscalar(int[] v1,int[] v2) {
		int cont=0;
		for(int i=0;i<v1.length;i++) {
			cont+=v1[i]*v2[i];
		}
		return cont;
	}

}
