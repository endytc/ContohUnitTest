/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import statistik.Statistik;

/**
 *
 * @author fendi
 */
public class StatistikTest {
    statistik.Statistik statistik;
    
    public StatistikTest() {
    }
    
    AngkaForTest[] angkaForTests;
    @Before
    public void setUp() {
        statistik=new Statistik();
        angkaForTests=new AngkaForTest[5];
        angkaForTests[0]=new AngkaForTest(new int[]{4,3,2,1,5,6,7,8},new int[]{1,2,3,4,5,6,7,8}, "4 3 2 1 5 6 7 8", Double.valueOf(4), 5,true);
        angkaForTests[1]=new AngkaForTest(new int[]{10,99,88,65,76,98,45,76,87,98,109,8765}, new int[] {10,45,65,76,76,87,88,98,98,99,109,8765},
                "10 99 88 65 76 98 45 76 87 98 109 8765", Double.valueOf(9616/12), 88, true);
        
        angkaForTests[2]=new AngkaForTest(new int[]{4,3,2,1,5,6,7,8},new int[]{1,2,3,4,5,6,7,8}, "4 3 2 1    5 6  7 8   ", Double.valueOf(4), 5,true);
        angkaForTests[3]=new AngkaForTest(new int[]{},new int[]{}, "4 3 2 1 u 6 7 8", null, null,false);
        angkaForTests[4]=new AngkaForTest(new int[]{},new int[]{}, "4 3 2 1.6 5.99 6 7 8", null, null,false);
    }
    
    @Test
    public void constructorTest(){
        for(int i=0;i<this.angkaForTests.length;i++){
            if(angkaForTests[i].isValid){
                Statistik s=new Statistik(angkaForTests[i].angkaString);
                Statistik s2=new Statistik(angkaForTests[i].angka);
                Assert.assertArrayEquals(angkaForTests[i].angka,s.getAngka());
                Assert.assertArrayEquals(angkaForTests[i].angka,s2.getAngka());
                Assert.assertArrayEquals(s2.getAngka(), s.getAngka());
            }
        }
        Statistik s3=new Statistik();
        Assert.assertNull(s3.getAngka());
    }
    
    @Test
    public void setValidDataTest(){
        for(int i=0;i<this.angkaForTests.length;i++){
            if(angkaForTests[i].isValid){
                //tes dengan contoh data yang valid
                Assert.assertTrue(statistik.setValidData(angkaForTests[i].angkaString));
            }else{
                //tes dengan contoh data yang tidak valid
                Assert.assertFalse(statistik.setValidData(angkaForTests[i].angkaString));
            }
        }
    }
    
    
    @Test
    public void getRataRataTest(){
        for(int i=0;i<this.angkaForTests.length;i++){
            statistik=new Statistik(angkaForTests[i].angka);
            if(angkaForTests[i].isValid){
                Assert.assertEquals("Expected Result "+angkaForTests[i].rataRata+" Actual Result"+statistik.getRataRata(),
                        angkaForTests[i].rataRata,statistik.getRataRata());
            }else{
                Assert.assertNull(statistik.getRataRata());
            }
        }
    }
    
    @Test
    public void getMedianTest(){
        for(int i=0;i<this.angkaForTests.length;i++){
            statistik=new Statistik(angkaForTests[i].angka);
            if(angkaForTests[i].isValid){
                Assert.assertTrue("Expected Result "+angkaForTests[i].median+" Actual Result"+statistik.getMedian(),
                        angkaForTests[i].median==statistik.getMedian());
            }else{
                Assert.assertNull(statistik.getMedian());
            }
        }
    }
    
    @Test
    public void getUrutanTest(){
        for(int i=0;i<this.angkaForTests.length;i++){
            statistik=new Statistik(angkaForTests[i].angka);
                Assert.assertArrayEquals("Data ke "+i+" belum urut",statistik.getUrutan(),angkaForTests[i].urutan);
        }
    }
    
    @Test
    public void integrationTest(){
        for(int i=0;i<this.angkaForTests.length;i++){
            statistik=new Statistik();
            if(statistik.setValidData(angkaForTests[i].angkaString)){
                Assert.assertEquals("Expected Result "+angkaForTests[i].rataRata+" ; Actual Result "+statistik.getRataRata(),
                        angkaForTests[i].rataRata,statistik.getRataRata());
                Assert.assertTrue("Expected Result "+angkaForTests[i].median+" ; Actual Result "+statistik.getMedian(),
                        angkaForTests[i].median==statistik.getMedian());
            }else{
                Assert.assertNull(statistik.getRataRata());
                Assert.assertNull(statistik.getMedian());
            }
        }
    }
    
    /**
    * Class AngkaForTest digunakan untuk mendeskripsikan berbagai 
    * kemungkinan kombinasi inputan yang dilakukan oleh user
    * @param angka berupa int[] yang digunakan untuk attribute angka di class Statistik
    * @param urutan berupa int[] dari angka yang sudah diurutkan dengan cara manual
    * @param angkaString kumpulan angka berupa string yang dipisahkan dengan spasi
    * @param rataRata rata-rata yang dihasilkan yang sudah dihitung secara manual
    * @param median median yang dihasilkan yang sudah dihitung secara manual
    * @param isValid merepresentasikan data yang digunakan untuk test apakah 
    *               data yang dipakai sudah valid atau belum. 
     */
    class AngkaForTest{
        int[] angka,urutan;
        String angkaString;
        Double rataRata;
        Integer median;
        boolean isValid;
        public AngkaForTest(int[] angka,int[] urutan, String angkaString, Double rataRata, 
                Integer median,boolean setValidData) {
            this.angkaString = angkaString;
            this.angka = angka;
            this.rataRata = rataRata;
            this.median = median;
            this.isValid=setValidData;
            this.urutan=urutan;
        }
    }
}
