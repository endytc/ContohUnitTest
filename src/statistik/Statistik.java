/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package statistik;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * 
 * @author fendi
 */
public class Statistik {

    private int[] angka;
    public Statistik(){
        
    }
    public Statistik(int[] angka){
        this.setAngka(angka);
    }
    public Statistik(String angka){
        this.setValidData(angka);
    }
    
    public void setAngka(int[] angka){
        this.angka=angka;
    }

    public int[] getAngka() {
        return angka;
    }
    
    public void inputkanAngka() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Masukkan beberapa angka dengan spasi sebagai pemisah: ");
        try {
            String angkaStr = br.readLine();
            if (!this.setValidData(angkaStr) && !angkaStr.equals("exit")) {
                System.err.println("inputan salah, harus berupa angka bulat, ketik exit untuk keluar");
                this.inputkanAngka();
            } else if (!angkaStr.equals("exit")) {
                System.out.println("Rata-rata   : "+this.getRataRata());
                System.out.println("Median      : "+this.getMedian());
            }

        } catch (IOException ex) {
            Logger.getLogger(Statistik.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    /**
     * jika inputan valid, maka terdapat inisialisasi var angka berdasarkan inputan
     * @param angkaString
     * @return 
     */
    public boolean setValidData(String angkaString) {
        while (!angkaString.equals(angkaString.replace("  ", " "))) {
            angkaString=angkaString.replaceFirst("  ", " ");
//            System.out.println(angkaString);
        };

        String[] tampung = angkaString.split(" ");
        angka = new int[tampung.length];
        for (int i = 0; i < tampung.length; i++) {
            try {
                angka[i] = Integer.valueOf(tampung[i]);
            } catch (Exception e) {
                angka = new int[0];
                return false;
            }
        }
        return true;
    }

    public Double getRataRata() {
        int jumlah = 0;
        for (int i = 0; i < angka.length; i++) {
            jumlah += angka[i];
        }
        try{
            return Double.valueOf(jumlah / this.angka.length);
        }catch(Exception e){
            return null;
        }
    }

    public int[] getUrutan() {
        int[] a = angka.clone();
        int in, out;
        for (out = 1; out < a.length; out++) // out is dividing line
        {
            int temp = a[out];// remove marked item
            in = out;// start shifts at out
            while (in > 0 && a[in - 1] >= temp) // until one is smaller,
            {
                a[in] = a[in - 1];
                --in;
            }
            a[in] = temp;
        } // end for
        return a;
    }
    
    public Integer getMedian(){
        int urutan[]=getUrutan();
        try{
            return urutan[urutan.length / 2];
        }catch(Exception e){
            return null;
        }
    }
    public static void main(String args[]) {
        Statistik statistik = new Statistik();
        statistik.inputkanAngka();
    }
}
