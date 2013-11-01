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
    
    public void setAngka(int[] angka){
        this.angka=angka;
    }
    public void inputkanAngka() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Masukkan beberapa angka dengan spasi sebagai pemisah: ");
        try {
            String angkaStr = br.readLine();
            if (!this.isValid(angkaStr) && !angkaStr.equals("exit")) {
                System.out.println("inputan masih salah, ketik exit untuk keluar");
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
    public boolean isValid(String angkaString) {
        while (!angkaString.equals(angkaString.replace("  ", " "))) {
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

//    
    public int[] getUrutan() {
        int[] a = angka;
        int out, in;
        for(out=a.length-1; out>1; out--)
            for (in = 0; in < out; in++) {
                if (a[in] > a[in + 1]) {
                    int temp=a[in];
                    a[in]=a[in+1];
                    a[in+1]=temp;
                }
         }
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
