package studing.algoritms.sort.greed_algoritms.priorityqueue;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author mash0916
 */
public class PriorityQueueTask {


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int operationCount = scanner.nextInt();

        SimplePriorityQueue queue = new SimplePriorityQueue(10);

        while (operationCount > 0) {

            String next = scanner.next();
            if (next.equalsIgnoreCase("Insert")) {
                queue.add(scanner.nextInt());
            } else if (next.equalsIgnoreCase("ExtractMax")) {
                System.out.println(queue.poll());
            }
            operationCount--;
        }

    }

    public static class SimplePriorityQueue {

        private int[] elements;

        private int size;

        public SimplePriorityQueue(int size) {
            this.elements = new int[size];
        }

        public void add(int element) {
            if (isArrayEnd()) {
                resize();
            }
            int currentIndex = this.size;
            elements[currentIndex] = element;
            shiftUp(currentIndex);
            size++;
        }

        public int poll() {
            int lastElement = elements[size - 1];
            int maxElement = elements[0];
            elements[0] = lastElement;
            shiftDown(0);
            size--;
            return maxElement;
        }

        private void shiftDown(int currentIndex) {
            int leftChildIndex = currentIndex * 2 + 1;
            int leftChild = 0;
            if (leftChildIndex < elements.length && leftChildIndex < size -1) {
                leftChild = elements[leftChildIndex];
            }
            int rightChild = 0;
            int rightChildIndex = currentIndex * 2 + 2;
            if (rightChildIndex < elements.length && rightChildIndex < size -1) {
                rightChild = elements[rightChildIndex];
            }

            int maxChild = Math.max(leftChild, rightChild);
            int currentElement = elements[currentIndex];
            int maxChildIndex = maxChild == rightChild ? rightChildIndex : leftChildIndex;
            if (currentElement < maxChild) {
                swap(maxChildIndex, currentIndex);
                currentIndex = maxChildIndex;
                shiftDown(currentIndex);
            }


        }

        private void shiftUp(int currentIndex) {
            int parent = getParent(currentIndex);
            int parentElement = elements[parent];
            int currentElement = elements[currentIndex];
            if (parentElement < currentElement) {
                swap(parent, currentIndex);
                currentIndex = parent;
                shiftUp(currentIndex);
            }
        }

        private void swap(int to, int from) {
            int tmp = elements[to];
            elements[to] = elements[from];
            elements[from] = tmp;

        }

        private int getParent(int childIndex) {
            return (childIndex - 1)/ 2;
        }

        private boolean isArrayEnd() {
            return size == elements.length;
        }

        private void resize() {
            int[] newArray = new int[size * 2];
            System.arraycopy(elements, 0, newArray, 0, elements.length);
            elements = newArray;
        }

        @Override
        public String toString() {
            return "SimplePriorityQueue{" +
                    "elements=" + Arrays.toString(elements) +
                    '}';
        }


    }
}

