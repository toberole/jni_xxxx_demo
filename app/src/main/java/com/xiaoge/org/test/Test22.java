package com.xiaoge.org.test;

public class Test22 {
    public static void main(String[] args) {
        int arr[] = {1, 2, 3, 4, 0, 5, 0, 6, 0, 7, 0};
        move(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "  ");
        }
    }

    /**
     * 非0元素前移
     */
    private static void move(int[] arr) {
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                if (index != i) {
                    arr[index] = arr[i];
                    arr[i] = 0;
                }

                index++;
            }
        }
    }
}
















