package GameOfLife;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import GameOfLife.World.CellStatus;

public class WorldTest {
	World f1 = new World(20, 20);

	@Test
	public void testTransfrom() {
		int n=0;
		f1.currentGeneration[0][0]=CellStatus.Active;
		f1.currentGeneration[0][1]=CellStatus.Dead;
		f1.currentGeneration[1][0]=CellStatus.Dead;
		f1.currentGeneration[1][1]=CellStatus.Active;
		f1.transfrom(f1.currentGeneration,f1.pauseshape);
		if(f1.pauseshape[0][0]==1&&f1.pauseshape[0][1]==0&&f1.pauseshape[1][0]==0&&f1.pauseshape[1][1]==1) {
			n=1;
		}
		assertEquals(1,n);
	}

	@Test
	public void testChangeSpeedSlow() {
		f1.changeSpeedSlow();
		assertEquals(8,f1.speed);
	}

	@Test
	public void testChangeSpeedFast() {
		f1.changeSpeedFast();
		assertEquals(3,f1.speed);
	}

	@Test
	public void testChangeSpeedHyper() {
		f1.changeSpeedHyper();
		assertEquals(1,f1.speed);
	}

	@Test
	public void testSetRandom() {
		f1.setRandom();
		int i,j,n=0,m=0;
		for(i=0;i<21;i++) {
			for(j=0;j<21;j++) {
				if(f1.pauseshape[i][j]==1)
					n++;
				else
					m++;
			}
		}
		if(n==0||m==0)
			fail("随机生成失败");
	}

	@Test
	public void testSetZero() {
		f1.setZero();
		int i,j,n=0;
		for(i=0;i<20;i++) {
			for(j=0;j<20;j++) {
				if(f1.zero[i][j]!=0) {
					n++;
				}
			}
		}
		assertEquals(0,n);
	}

	@Test
	public void testSetStop() {
		f1.setStop();
		int i,j,n=0;
		for(i=0;i<21;i++) {
			for(j=0;j<21;j++) {
				if(f1.pauseshape[i][j]!=0) {
					n++;
				}
			}
		}
		assertEquals(0,n);
	}

	@Test
	public void testEvolve() {
		f1.setStop();
		f1.currentGeneration[3][3]=CellStatus.Dead;f1.currentGeneration[3][2]=CellStatus.Active;
		f1.currentGeneration[3][4]=CellStatus.Active;f1.currentGeneration[2][3]=CellStatus.Active;
		f1.evolve(3,3);
		if(f1.nextGeneration[3][3]!=CellStatus.Active){
			fail("第一条规则失败。");
		}
		f1.currentGeneration[6][6]=CellStatus.Active;f1.currentGeneration[6][5]=CellStatus.Active;
		f1.evolve(6,6);
		if(f1.nextGeneration[6][6]!=CellStatus.Dead){
			fail("第二条规则失败。");
		}
		f1.currentGeneration[9][9]=CellStatus.Active;f1.currentGeneration[9][10]=CellStatus.Active;
		f1.currentGeneration[9][8]=CellStatus.Active;f1.currentGeneration[8][9]=CellStatus.Active;
		f1.evolve(9,9);
		if(f1.nextGeneration[9][9]!=CellStatus.Active){
			fail("第三条规则失败。");
		}
		f1.currentGeneration[12][12]=CellStatus.Active;f1.currentGeneration[12][13]=CellStatus.Active;
		f1.currentGeneration[12][11]=CellStatus.Active;f1.currentGeneration[11][12]=CellStatus.Active;
		f1.currentGeneration[13][12]=CellStatus.Active;
		f1.evolve(12,12);
		if(f1.nextGeneration[12][12]!=CellStatus.Dead){
			fail("第四条规则失败。");
		}
	}

}
