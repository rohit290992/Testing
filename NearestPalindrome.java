package snippet;

import java.util.Arrays;
import java.util.Scanner;

public class NearestPalindrome {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String line = sc.nextLine();
    String[] inputs = line.split(" ");
    int testScn = 0;
    try {
      testScn = Integer.parseInt(inputs[0]);
    } catch (Exception e) {
      System.out.println("Please insert integer");
    }

    for (int i = 0; i < testScn; i++) {
      String noNearestPalindrom = inputs[i+1];
      int noOFDigits = noNearestPalindrom.toCharArray().length;
      int[] num = new int[noOFDigits];
      int j = 0;
      for (char c : noNearestPalindrom.toCharArray()) {
        num[j++] = Integer.parseInt(String.valueOf(c));
      }
      if (isNoContainsOnly9(Integer.parseInt(noNearestPalindrom))) {
        System.out.print(Integer.parseInt(noNearestPalindrom) + 2);
        System.out.print(" ");
      } else {
        getNextPalindromeIfNot9(num);
      }
    }



  }

  static boolean isNoContainsOnly9(int num) {
    boolean isNoContainOnly9 = true;
    int temp = num;
    while (temp > 0) {
      if (temp % 10 != 9) {
        return false;
      }
      temp /= 10;
    }
    return isNoContainOnly9;
  }

  static void getNextPalindromeIfNot9(int[] num) {
    int noOfDigits = num.length;
    int mid = noOfDigits / 2;
    int leftPtr = mid - 1;
    int rightPtr;
    if (noOfDigits % 2 == 0) {
      rightPtr = mid;
    } else {
      rightPtr = mid + 1;
    }
    while (leftPtr >= 0 && num[leftPtr] == num[rightPtr]) {
      leftPtr--;
      rightPtr++;
    }
    boolean isLeftDigitSmaller = false;
    if (leftPtr < 0 || num[leftPtr] < num[rightPtr]) {
      isLeftDigitSmaller = true;
    }
    while (leftPtr >= 0) {
      num[rightPtr] = num[leftPtr];
      rightPtr++;
      leftPtr--;
    }
    if (isLeftDigitSmaller) {
      int carry = 1;

      if (noOfDigits % 2 == 1) {
        num[mid] += 1;
        carry = num[mid] / 10;
        num[mid] %= 10;
      }
      leftPtr = mid - 1;
      rightPtr = (noOfDigits % 2 == 0 ? mid : mid + 1);
      while (leftPtr >= 0) {
        num[leftPtr] = num[leftPtr] + carry;
        carry = num[leftPtr] / 10;
        num[leftPtr] = num[leftPtr] % 10;
        num[rightPtr] = num[leftPtr];
        leftPtr--;
        rightPtr++;
      }
    }
    for (int a : num) {
      System.out.print(a);
    }
    System.out.print(" ");

  }

}
