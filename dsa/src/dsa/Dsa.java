package dsa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Author: John Wayne Enrique
 */
public class Dsa {

    public static void main(String[] args) {
        float[] array = { (float) 0.42, (float) 0.32, (float) 0.23, (float) 0.52, (float) 0.25, (float) 0.47, (float) 0.51 };

        System.out.println("Original Array:");
        for (float value : array) {
            System.out.print(value + " ");
        }

        BucketSort.bucketSort(array); // Call bucketSort from BucketSort class

        System.out.println("\n\nSorted Array:");
        for (float value : array) {
            System.out.print(value + " ");
        }
    }
}

// Separate class for Bucket Sort
class BucketSort {
    
    // Method to perform Bucket Sort
    public static void bucketSort(float[] array) {
        int n = array.length;
        if (n <= 0)
            return;

        // Create empty buckets
        @SuppressWarnings("unchecked")
        List<Float>[] buckets = new ArrayList[n];

        // Initialize buckets
        for (int i = 0; i < n; i++) {
            buckets[i] = new ArrayList<>();
        }

        // Put elements into different buckets
        for (float element : array) {
            int bucketIndex = (int) (n * element); // bucket index
            buckets[bucketIndex].add(element);
        }

        // Sort individual buckets using Collections.sort
        for (int i = 0; i < n; i++) {
            Collections.sort(buckets[i]);
        }

        // Concatenate all buckets into the original array
        int index = 0;
        for (int i = 0; i < n; i++) {
            for (float element : buckets[i]) {
                array[index++] = element;
            }
        }
    }
}
