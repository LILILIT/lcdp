package org.example;

import java.util.Arrays;
import java.util.Random;

/**
 * 常见排序算法的 Java 实现。
 *
 * <p>包含冒泡排序、插入排序、归并排序和快速排序，并提供 {@link #main(String[])}
 * 用于验证各算法结果与 {@link Arrays#sort(int[])} 一致。</p>
 */
public final class SortAlgorithm {

    private SortAlgorithm() {
    }

    /**
     * 冒泡排序：相邻元素两两比较，将较大元素逐步“冒泡”到末尾。
     * 时间复杂度 O(n^2)，空间复杂度 O(1)，稳定。
     *
     * @param arr 待排序数组，原地排序
     */
    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }

    /**
     * 插入排序：将每个元素插入到前面已排好序的子数组中的正确位置。
     * 时间复杂度 O(n^2)，空间复杂度 O(1)，稳定，对小规模或基本有序数据效率高。
     *
     * @param arr 待排序数组，原地排序
     */
    public static void insertionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    /**
     * 归并排序：分治地将数组拆分为两半，分别排序后再合并。
     * 时间复杂度 O(n log n)，空间复杂度 O(n)，稳定。
     *
     * @param arr 待排序数组，原地排序
     */
    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        mergeSort(arr, 0, arr.length - 1);
    }

    private static void mergeSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= right) {
            temp[k++] = arr[i] <= arr[j] ? arr[i++] : arr[j++];
        }
        while (i <= mid) {
            temp[k++] = arr[i++];
        }
        while (j <= right) {
            temp[k++] = arr[j++];
        }
        System.arraycopy(temp, 0, arr, left, temp.length);
    }

    /**
     * 快速排序：选取基准元素，将数组划分为小于基准和大于基准的两部分后递归排序。
     * 平均时间复杂度 O(n log n)，最坏 O(n^2)，空间复杂度 O(log n)，不稳定。
     *
     * @param arr 待排序数组，原地排序
     */
    public static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int low, int high) {
        if (low >= high) {
            return;
        }
        int pivotIndex = partition(arr, low, high);
        quickSort(arr, low, pivotIndex - 1);
        quickSort(arr, pivotIndex + 1, high);
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**
     * 演示入口：生成随机数组，分别用四种算法排序并校验结果。
     */
    public static void main(String[] args) {
        int size = 20;
        int[] original = new Random().ints(size, 0, 100).toArray();

        int[] byBubble = original.clone();
        bubbleSort(byBubble);
        int[] byInsertion = original.clone();
        insertionSort(byInsertion);
        int[] byMerge = original.clone();
        mergeSort(byMerge);
        int[] byQuick = original.clone();
        quickSort(byQuick);
        int[] expected = original.clone();
        Arrays.sort(expected);

        System.out.println("原始数组: " + Arrays.toString(original));
        System.out.println("冒泡排序: " + Arrays.toString(byBubble));
        System.out.println("插入排序: " + Arrays.toString(byInsertion));
        System.out.println("归并排序: " + Arrays.toString(byMerge));
        System.out.println("快速排序: " + Arrays.toString(byQuick));

        boolean allMatch = Arrays.equals(expected, byBubble)
                && Arrays.equals(expected, byInsertion)
                && Arrays.equals(expected, byMerge)
                && Arrays.equals(expected, byQuick);
        if (allMatch) {
            System.out.println("校验通过：四种算法结果均与 Arrays.sort 一致。");
        } else {
            System.err.println("校验失败：存在算法结果不一致。");
            System.exit(1);
        }
    }
}
