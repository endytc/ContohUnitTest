/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
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
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        statistik=new Statistik();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void isValidTest(){
        Assert.assertTrue(statistik.isValid("9 8 7 6 4654 7 5 0"));
        Assert.assertFalse(statistik.isValid("9 8 7 6 u 7 5 0"));
    }
    @Test
    public void getRataRataTest(){
        statistik.setAngka(new int[]{4,3,2,1,5,6,7,8});
        Assert.assertTrue("hasilnya adalah 4 dan "+statistik.getRataRata(),4==statistik.getRataRata());
        
        statistik.setAngka(new int[]{10,99,88,65,76,98,45,76,87,98,109,8765});
        Assert.assertTrue((double)(9616/12)==statistik.getRataRata());
        
        statistik.setAngka(new int[]{});
        Assert.assertNull(statistik.getRataRata());
    }
    @Test
    public void getMedianTest(){
        statistik.setAngka(new int[]{4,3,2,1,5,6,7,8});
        Assert.assertTrue("hasilnya adalah 5 dan "+statistik.getMedian(),5==statistik.getMedian());
        
        statistik.setAngka(new int[]{10,99,88,65,76,98,45,76,87,98,109,8765});
        Assert.assertTrue(88==statistik.getMedian());
        
        statistik.setAngka(new int[]{});
        Assert.assertNull(statistik.getMedian());
    }
    @Test
    public void getUrutanTest(){
        statistik.setAngka(new int[]{4,3,2,1,5,6,7,8});
        Assert.assertArrayEquals(statistik.getUrutan(),new int[] {1,2,3,4,5,6,7,8});
        
        statistik.setAngka(new int[]{10,99,88,65,76,98,45,76,87,98,109,8765});
        Assert.assertArrayEquals(statistik.getUrutan(),new int[] {10,45,65,76,76,87,88,98,98,99,109,8765});
        
        statistik.setAngka(new int[]{});
        Assert.assertArrayEquals(statistik.getUrutan(),new int[]{});
    }
    @Test
    public void integrationTest(){
        statistik=new Statistik();
        statistik.isValid("4 3 2 1 5 6 7 8");
        Assert.assertTrue("yang daharapkan 4 dan hasilnya "+statistik.getRataRata(),4==statistik.getRataRata());
        Assert.assertTrue(5==statistik.getMedian());
        
        statistik=new Statistik();
        statistik.isValid("4 3 2 1 u 6 7 8");
        Assert.assertNull(statistik.getRataRata());
        Assert.assertNull(statistik.getMedian());
        
        statistik=new Statistik();
        statistik.isValid("10 99 88 65 76 98 45 76 87 98 109 8765");
        Assert.assertTrue((double)(9616/12)==statistik.getRataRata());
        Assert.assertTrue(88==statistik.getMedian());
        
        statistik=new Statistik();
        statistik.isValid("");
        Assert.assertNull(statistik.getRataRata());
        Assert.assertNull(statistik.getMedian());
    }
}