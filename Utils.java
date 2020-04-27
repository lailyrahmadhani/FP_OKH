/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torontodataset;

import java.util.Arrays;
//import java.util.Random;

public class Utils {
	public static int[][] copyArray(final int[][] arr) {
		final int[][] arrcop = new int[arr.length][arr.length];

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				arrcop[i][j] = arr[i][j];
			}
		}

		return arrcop;

	}

	public static int[][] copySolution(final int[][] arr) {
		final int[][] copySolution = new int[arr.length][2];

		for (int i = 0; i < arr.length; i++) {
			copySolution[i][0] = arr[i][0];
			copySolution[i][1] = arr[i][1];
		}

		return copySolution;
	}

	public static boolean isNotTabrakan(final int[][] matrix, final int[][] jadwal) {
		for (int course = 0; course < matrix.length - 1; course++) {
			for (int i = course + 1; i < matrix.length; i++)
				if (matrix[course][i] != 0 && jadwal[course][1] == jadwal[i][1])
					return false;
		}

		return true;
	}

	public static double getPenalty(final int[][] matrix, final int[][] jadwal, final int jumlah) {
		double penalty = 0;

		for (int i = 0; i < matrix.length - 1; i++) {
			for (int j = i + 1; j < matrix.length; j++) {
				if (matrix[i][j] != 0) {
					if (Math.abs(jadwal[j][1] - jadwal[i][1]) >= 1 && Math.abs(jadwal[j][1] - jadwal[i][1]) <= 5) {
						penalty = penalty + (matrix[i][j] * (Math.pow(2, 5 - (Math.abs(jadwal[j][1] - jadwal[i][1])))));
					}
				}
			}
		}

		return penalty / jumlah;
	}

	public static int getRandomNumber(final int min, final int max) {
		// Random random = new Random();
		// return random.nextInt((max - min) + 1) + min;
		return (int) (Math.random() * ((max - min) + 1)) + min;
	}

	public static int[][] move(final int[][] solution, final int step) {
		final int[][] temp = solution;
		final int[] timeslot = new int[temp.length];

		for (int i = 0; i < temp.length; i++) {
			timeslot[i] = temp[i][1];
		}

		for (int i = 0; i < step; i++) {
			final int randomExam = getRandomNumber(1, solution.length - 1);
			final int randomTimeslot = getRandomNumber(1, Arrays.stream(timeslot).max().getAsInt() - 1);

			temp[randomExam][1] = randomTimeslot;
		}

		return temp;
	}

	public static int[][] swap(final int[][] solution, final int numSwap) {
		final int[][] temp = solution;

		for (int i = 0; i < numSwap; i++) {
			final int exam1 = getRandomNumber(0, solution.length - 1);
			final int exam2 = getRandomNumber(0, solution.length - 1);

			final int timeslot1 = solution[exam1][1];
			final int timeslot2 = solution[exam2][1];

			temp[exam1][1] = timeslot2;
			temp[exam2][1] = timeslot1;
		}

		return temp;
	}

	private static int calculateSaturation(final int[][] sat, final int batas) {
		int min = 10000;
		int index = 0;
		for (int i = 0; i < sat.length; i++) {
			if (sat[i][1] < min) {
				index = i;
				min = sat[i][1];
			}
		}
		return index;
	}

	public static int[][] getSaturationSchedule(final int size, final int[][] largestDegree, final int[][] matrix) {
		final int[][] schedule = new int[size][2];
		final int[][] saturation = new int[size][2];
		int timeslot = 1;

		for (int i = 0; i < schedule.length; i++) {
			schedule[i][0] = i + 1;
			schedule[i][1] = -1;
			saturation[i][0] = largestDegree[i][0];
			saturation[i][1] = size;
		}

		for (int i = 0; i < size; i++) {
			final int index = calculateSaturation(saturation, size);
			for (int j = 0; j <= timeslot; j++) {
				if (isOk(saturation[index][0] - 1, j, matrix, schedule, saturation)) {
					schedule[saturation[index][0] - 1][1] = j;
					saturation[index][1] = 100000;
					int ind = 0;
					for (int k = 0; k < matrix.length; k++) {
						if (matrix[saturation[index][0] - 1][k] != 0) {
							ind = k;
							for (int l = 0; l < saturation.length; l++) {
								if (saturation[l][0] == k + 1) {
									saturation[l][1] = saturation[l][1] - 1;
								}
							}
						}
					}
					break;
				} else {
					timeslot++;
				}
			}
		}
		return schedule;
	}

	private static boolean isOk(final int ex, final int jad, final int[][] cm, final int[][] timeslot,
			final int[][] largest) {
		for(int i=0; i<cm.length; i++)
			if(cm[ex][i]!=0 && timeslot[i][1]==jad)
				return false;
		return true;
	}
	
}
