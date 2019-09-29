package com.hage.sort;


import java.util.Arrays;

public class InsertSort {
    public static void main(String[] args) {
        int[] array = {12, 23, 55, 35, 9, 18};
        for (int i = 1; i < array.length; i++) {
            int target = array[i];
            int j = i - 1;
            //这里注意判断条件j>0要先写，否则数组下标索引会到-1
            while (j>=0&&array[j]>target) {
                array[j + 1] = array[j];
                j--;
            }
            //这里的j+1是因为j--
            array[j+1] = target;
        }
       System.out.println(Arrays.toString(array));
    }
}
