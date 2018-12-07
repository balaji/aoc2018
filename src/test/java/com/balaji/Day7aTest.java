package com.balaji;

import org.junit.Test;

public class Day7aTest {

  @Test
  public void testSample() {
    final Day7a.Search search = new Day7a.Search();
    search.insert('C','A');
    search.insert('C','F');
    search.insert('A','B');
    search.insert('A','D');
    search.insert('B','E');
    search.insert('D','E');
    search.insert('F','E');
    search.traverse();
  }

  @Test
  public void testInput() {
    Day7a.Search search = new Day7a.Search();
    search.insert('F','P');
    search.insert('R','J');
    search.insert('X','H');
    search.insert('L','N');
    search.insert('U','Z');
    search.insert('B','C');
    search.insert('S','C');
    search.insert('N','Y');
    search.insert('I','J');
    search.insert('H','K');
    search.insert('G','Z');
    search.insert('Q','V');
    search.insert('E','P');
    search.insert('P','W');
    search.insert('J','D');
    search.insert('V','W');
    search.insert('T','D');
    search.insert('Z','A');
    search.insert('K','A');
    search.insert('Y','O');
    search.insert('O','W');
    search.insert('C','M');
    search.insert('D','A');
    search.insert('W','M');
    search.insert('M','A');
    search.insert('C','A');
    search.insert('F','Z');
    search.insert('I','A');
    search.insert('W','A');
    search.insert('T','C');
    search.insert('S','K');
    search.insert('B','J');
    search.insert('O','A');
    search.insert('Q','P');
    search.insert('G','M');
    search.insert('R','T');
    search.insert('B','G');
    search.insert('J','O');
    search.insert('X','E');
    search.insert('X','C');
    search.insert('H','Y');
    search.insert('Y','A');
    search.insert('X','W');
    search.insert('H','A');
    search.insert('X','A');
    search.insert('I','M');
    search.insert('G','J');
    search.insert('N','G');
    search.insert('D','M');
    search.insert('L','D');
    search.insert('V','T');
    search.insert('I','Y');
    search.insert('S','J');
    search.insert('K','Y');
    search.insert('F','R');
    search.insert('U','T');
    search.insert('Z','M');
    search.insert('T','Z');
    search.insert('B','I');
    search.insert('E','K');
    search.insert('N','J');
    search.insert('X','Q');
    search.insert('F','Y');
    search.insert('H','P');
    search.insert('Z','D');
    search.insert('V','O');
    search.insert('E','C');
    search.insert('V','C');
    search.insert('P','A');
    search.insert('B','N');
    search.insert('S','W');
    search.insert('P','D');
    search.insert('L','W');
    search.insert('D','W');
    search.insert('K','C');
    search.insert('L','M');
    search.insert('R','O');
    search.insert('F','L');
    search.insert('R','H');
    search.insert('K','O');
    search.insert('T','W');
    search.insert('R','K');
    search.insert('C','W');
    search.insert('N','T');
    search.insert('R','P');
    search.insert('E','M');
    search.insert('G','T');
    search.insert('U','K');
    search.insert('Q','D');
    search.insert('U','S');
    search.insert('J','V');
    search.insert('P','Y');
    search.insert('X','Z');
    search.insert('U','H');
    search.insert('H','M');
    search.insert('I','C');
    search.insert('V','M');
    search.insert('N','I');
    search.insert('B','K');
    search.insert('R','Q');
    search.insert('O','C');

    search.traverse();
  }

}