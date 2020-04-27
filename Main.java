/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torontodataset;

import java.util.Scanner;

public class Main {

    static final String Directory = "F:\\Documents\\ITS\\[Akademik]\\Optimasi Kombinatorik dan Heuristik\\dataset toronto\\";

    public static void main(String[] args) {
        String crs = "";
        String stu = "";

        String carf92_stu = Directory + "car-f-92.stu";
        String carf92_crs = Directory + "car-f-92.crs";
        String cars91_stu = Directory + "car-s-91.stu";
        String cars91_crs = Directory + "car-s-91.crs";
        String earf83_stu = Directory + "ear-f-83.stu";
        String earf83_crs = Directory + "ear-f-83.crs";
        String hecs92_stu = Directory + "hec-s-92.stu";
        String hecs92_crs = Directory + "hec-s-92.crs";
        String kfus93_stu = Directory + "kfu-s-93.stu";
        String kfus93_crs = Directory + "kfu-s-93.crs";
        String lsef91_stu = Directory + "lse-f-91.stu";
        String lsef91_crs = Directory + "lse-f-91.crs";
        String purs93_stu = Directory + "pur-s-93.stu";
        String purs93_crs = Directory + "pur-s-93.crs";
        String ryes93_stu = Directory + "rye-s-93.stu";
        String ryes93_crs = Directory + "rye-s-93.crs";
        String staf83_stu = Directory + "sta-f-83.stu";
        String staf83_crs = Directory + "sta-f-83.crs";
        String tres92_stu = Directory + "tre-s-92.stu";
        String tres92_crs = Directory + "tre-s-92.crs";
        String utas92_stu = Directory + "uta-s-92.stu";
        String utas92_crs = Directory + "uta-s-92.crs";
        String utes92_stu = Directory + "ute-s-92.stu";
        String utes92_crs = Directory + "ute-s-92.crs";
        String yorf83_stu = Directory + "yor-f-83.stu";
        String yorf83_crs = Directory + "yor-f-83.crs";

        System.out.println("-- LIST OF DATASETS --");
        System.out.println("1. Car-f-92");
        System.out.println("2. Car-s-91");
        System.out.println("3. Ear-f-83");
        System.out.println("4. Hec-s-92");
        System.out.println("5. Kfu-s-93");
        System.out.println("6. Lse-f-91");
        System.out.println("7. Pur-s-93");
        System.out.println("8. Rye-s-93");
        System.out.println("9. Sta-f-83");
        System.out.println("10. Tre-s-92");
        System.out.println("11. Uta-s-92");
        System.out.println("12. Ute-s-92");
        System.out.println("13. yor-f-83");
        System.out.print("Choose a dataset : \n");

        Scanner input = new Scanner(System.in);
        int dataset = input.nextInt();

        switch (dataset) {
            case 1:
                stu = carf92_stu;
                crs = carf92_crs;
                break;
            case 2:
                stu = cars91_stu;
                crs = cars91_crs;
                break;
            case 3:
                stu = earf83_stu;
                crs = earf83_crs;
                break;
            case 4:
                stu = hecs92_stu;
                crs = hecs92_crs;
                break;
            case 5:
                stu = kfus93_stu;
                crs = kfus93_crs;
                break;
            case 6:
                stu = lsef91_stu;
                crs = lsef91_crs;
                break;
            case 7:
                stu = purs93_stu;
                crs = purs93_crs;
                break;
            case 8:
                stu = ryes93_stu;
                crs = ryes93_crs;
                break;
            case 9:
                stu = staf83_stu;
                crs = staf83_crs;
                break;
            case 10:
                stu = tres92_stu;
                crs = tres92_crs;
                break;
            case 11:
                stu = utas92_stu;
                crs = utas92_crs;
                break;
            case 12:
                stu = utes92_stu;
                crs = utes92_crs;
                break;
            case 13:
                stu = yorf83_stu;
                crs = yorf83_crs;
                break;
        }

        System.out.println("-- Results --");

        CourseSet cs = new CourseSet(crs);
        Conflict cm = new Conflict(stu, cs.getSize());
        int[][] confMat = cm.getConflictMatrix();
        int jumlahSiswa = cm.getJumlahStudent();
        int[][] solution = Schedule.getSaturationSchedule(cs.getSize(), cm.getDegree(), confMat);
        /*Solution bestSolution = new Solution(solution);
		System.out.println("Jumlah Timeslot : " + bestSolution.getJumlahTimeslot());*/

        System.out.println("Fitness : " + Utils.getPenalty(confMat, solution, jumlahSiswa));

        long startTime = System.nanoTime();
        for (int i = 1; i <= 10; i++) {
            Optimizer.hillClimbing(stu, crs, 100, 1000);
        }
        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println((double) totalTime / 1000000000 + " second(s)");
    }
}
