import java.io.*;
import java.util.*;
import java.util.concurrent.CountDownLatch;

public class Main {
    private static final BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.print("Please enter a number of values to be sorted: ");
        int numValues = Integer.parseInt(stdin.readLine().trim());
        int[] list = new int[numValues];

        System.out.print("Would you like random generation? (Y/N): ");
        if(stdin.readLine().trim().charAt(0) == 'Y') {
            Random random = new Random();
            for(int i = 0; i < numValues; i++) {
                list[i] = random.nextInt(2 * numValues);
            }
            showData(list);
        } else {
            for(int i = 0; i < numValues; i++) {
                System.out.print("Enter value " + (i + 1) + ": ");
                list[i] = Integer.parseInt(stdin.readLine().trim());
            }
        }

        System.out.println("\n\nPlease select an option");
        System.out.println("0. Exit");
        System.out.println("1. Bubble sort");
        System.out.println("2. Selection sort");
        System.out.println("3. Insertion sort");
        System.out.println("4. Merge sort");
        System.out.println("5. Quick sort");
        System.out.println("6. Tree sort");
        System.out.println("7. Heap sort");
        System.out.println("8. Stalin sort");
        System.out.println("9. Bogo sort");
        System.out.println("10. Bozo sort");
        System.out.println("11. Sleep sort");
        System.out.println("12. Human sort");
        System.out.println("13. Gangster sort");

        int option;
        boolean done = false;

        while(!done) {
            System.out.print("Make a menu selection now: ");
            option = Integer.parseInt(stdin.readLine().trim());

            switch(option) {
                case 0 -> done = true;
                case 1 -> BubbleSort(list);
                case 2 -> SelectionSort(list);
                case 3 -> InsertionSort(list);
                case 4 -> MergeSort(list);
                case 5 -> QuickSort(list);
                case 6 -> TreeSort(list);
                case 7 -> HeapSort(list);
                case 8 -> StalinSort(list);
                case 9 -> BogoSort(list);
                case 10 -> BozoSort(list);
                case 11 -> SleepSort(list);
                case 12 -> HumanSort(list);
                case 13 -> GangsterSort(list);
            }
        }

        System.out.println("Quitting...");
    }

    private static void showData(int[] list) throws InterruptedException {
        for(int i: list) {
            System.out.print(i + "\t");
        }
        Thread.sleep(1000);
        System.out.println();
    }

    private static void showData(ArrayList<Integer> list) throws InterruptedException {
        for(int i: list) {
            System.out.print(i + "\t");
        }
        Thread.sleep(1000);
        System.out.println();
    }

    private static void BubbleSort(int[] oldlist) throws InterruptedException {
        int size = oldlist.length;
        int[] list = new int[oldlist.length];

        for(int i = 0; i < oldlist.length; i++) {
            list[i] = oldlist[i];
        }

        int temp;
        boolean done = false;

        showData(list);

        for(int i = size - 1; i > 0 && !done; i--) {
            done = true;
            for(int j = 0; j < i; j++) {
                if(list[j] > list[j + 1]) {
                    temp = list[j];
                    list[j] = list[j + 1];
                    list[j + 1] = temp;
                    showData(list);
                    done = false;
                }
            }
        }
    }

    private static void SelectionSort(int[] oldlist) throws InterruptedException {
        int size = oldlist.length;
        int[] list = new int[oldlist.length];

        for(int i = 0; i < oldlist.length; i++) {
            list[i] = oldlist[i];
        }

        int temp;
        boolean done = false;
        int high;

        showData(list);

        for(int i = size - 1; i > 0 && !done; i--) {
            done = true;
            high = 0;

            for(int j = 0; j < i + 1; j++) {
                if(list[j] > list[high]) {
                    high = j;
                } else {
                    done = false;
                }
            }

            if(high != i) {
                temp = list[i];
                list[i] = list[high];
                list[high] = temp;
                showData(list);
            }
        }
    }

    private static void InsertionSort(int[] oldlist) throws InterruptedException {
        int size = oldlist.length;
        int[] list = new int[size];
        for(int i = 0; i < size; i++) {
            list[i] = oldlist[i];
        }
        int key, high, mid, low;

        showData(list);

        for(int i = 1; i < size; i++) {
            key = list[i];
            low = 0;
            high = i - 1;
            mid = (low + high) / 2;

            while(low <= high) {
                if(key > list[mid]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
                mid = (low + high) / 2;
            }

            for(int j = i - 1; j >= low; j--) {
                list[j + 1] = list[j];
            }

            list[low] = key;

            showData(list);
        }
    }

    private static void MergeSort(int[] oldlist) throws InterruptedException {
        int size = oldlist.length;
        int[][] list = new int[2][size];
        for(int i = 0; i < size; i++) {
            list[0][i] = oldlist[i];
        }
        int currArray = 0;

        showData(list[currArray]);

        for(int i = 1; i < size; i *= 2) {
            for(int j = 0; j < size; j += 2 * i) {
                merge(list, currArray, j, i);
            }
            currArray = currArray^1;
            showData(list[currArray]);
        }
    }

    private static void merge(int[][] data, int sourceindex, int begin, int siz) {
        int dest = sourceindex ^ 1;
        int b1 = begin;
        int e1 = b1 + siz; //End of first array
        int e2 = e1 + siz > data[sourceindex].length? data[sourceindex].length : e1 + siz;

        int i = b1;
        int j = e1;

        if(e1 > data[sourceindex].length) {
            int len = data[sourceindex].length;
            while(i < len) {
                data[dest][begin++] = data[sourceindex][i++];
            }
            return;
        }

        while(i < e1 && j < e2) { //both sub-arrays still have items
            if(data[sourceindex][i] > data[sourceindex][j]) {
                data[dest][begin++] = data[sourceindex][j++];
            } else {
                data[dest][begin++] = data[sourceindex][i++];
            }
        }

        while(i < e1) { //items in first sub-array
            data[dest][begin++] = data[sourceindex][i++];
        }

        while(j < e2) { //items in second sub-array
            data[dest][begin++] = data[sourceindex][j++];
        }
        //First while loop will execute until one is empty, the while loop for the correct array will then execute
    }

    private static void QuickSort(int[] oldlist) throws InterruptedException {
        int size = oldlist.length;
        int[] list = new int[size];
        for(int i = 0; i < size; i++) {
            list[i] = oldlist[i];
        }

        qSort(list, 0, size);
    }

    private static void qSort(int[] data, int low, int high) throws InterruptedException {
        if(low + 1 >= high) {
            return;
        }

        if(low + 2 == high) {
            if(data[low] > data[low + 1]) {
                int temp = data[low];
                data[low] = data[low + 1];
                data[low + 1] = temp;
            }
            return;
        }

        Random random = new Random();
        //Randomly chooses an item which is not the first item in the list for the pivot
        int pivotIndex = random.nextInt(high - low - 1) + low + 1;

        int temp = data[low];
        data[low] = data[pivotIndex];
        data[pivotIndex] = temp;

        int i, j;
        i = j = low;

        for(int k = low + 1; k < high; k++) {
            if (data[k] <= data[low]) {
                if (i != j) {
                    //Swap with i + 1
                    temp = data[i + 1];
                    data[i + 1] = data[k];
                    data[k] = temp;
                }
                i++;
            }
            j++;
        }

        if(i != low) {
            temp = data[low];
            data[low] = data[i];
            data[i] = temp;
        }

        showData(data);
        qSort(data, low, i);
        qSort(data, i + 1, j + 1);
    }

    private static void TreeSort(int[] oldlist) throws InterruptedException {
        int size = oldlist.length;
        int[] list = new int[size];
        for(int i = 0; i < size; i++) {
            list[i] = oldlist[i];
        }

        showData(list);

        TreeNode<Integer> root = new TreeNode<>(list[0]);

        for(int i = 1; i < size; i++) {
            insert(root, list[i]);
        }

        System.out.println(inOrder(root));
    }

    private static String inOrder(TreeNode<Integer> tNode) {
        if(tNode == null) {
            return "";
        } else {
            return inOrder(tNode.getLeftChild()) + tNode + "\t" + inOrder(tNode.getRightChild());
        }
    }

    private static void insert(TreeNode<Integer> root, int item) throws InputMismatchException {
        TreeNode<Integer> newNode = new TreeNode<>(item);
        TreeNode<Integer> currNode = root;
        boolean done = false;

        while(!done) {
            int comparison = newNode.getItem().compareTo(currNode.getItem());
            if(comparison == 0) {
                throw new InputMismatchException("Whoops");
            } else if (comparison < 0) {
                if(currNode.getLeftChild() != null) {
                    currNode = currNode.getLeftChild();
                } else {
                    currNode.setLeftChild(newNode);
                    done = true;
                }
            } else {
                if(currNode.getRightChild() != null) {
                    currNode = currNode.getRightChild();
                } else {
                    currNode.setRightChild(newNode);
                    done = true;
                }
            }
        }
    }

    private static void HeapSort(int[] oldlist) throws InterruptedException {
        int size = oldlist.length;
        int[] list = new int[size];
        for(int i = 0; i < size; i++) {
            list[i] = oldlist[i];
        }

        int left, right;
        boolean done;
        //1st: Heapify input
        //only heapify 1st (n+1)/2
        for(int i = ((size + 1)/2) - 1; i > -1; i--) {
            showData(list);
            done = false;
            left = (2 * i) + 1;
            right = left + 1;
            int j = i;
            while(!done) {
                if(left >= size) {
                    done = true;
                } else if(right >= size) {
                    if(list[j] < list[left]) {
                        int temp = list[j];
                        list[j] = list[left];
                        list[left] = temp;
                    }
                    done = true;
                } else {
                    int high = list[left] > list[right]? left : right;
                    if(list[j] < list[high]) {
                        int temp = list[j];
                        list[j] = list[high];
                        list[high] = temp;
                        j = high;
                    } else {
                        done = true;
                    }
                }
                left = 2 * j + 1;
                right = left + 1;
            }
        }

        showData(list);

        int temp1;
        showData(list);
        //2nd: Move largest to end, trickle down last item in list
        for(int i = size - 1; i > 0; i--) {
            showData(list);
            done = false;
            temp1 = list[i];
            list[i] = list[0];
            list[0] = temp1;
            left = 1;
            right = 2;
            int j = 0;
            while(!done) {
                if(left >= i) {
                    done = true;
                } else if(right >= i) {
                    if(list[j] < list[left]) {
                        int temp = list[j];
                        list[j] = list[left];
                        list[left] = temp;
                    } else {
                        done = true;
                    }
                } else {
                    int high = list[left] > list[right]? left : right;
                    if(list[j] < list[high]) {
                        int temp = list[j];
                        list[j] = list[high];
                        list[high] = temp;
                        j = high;
                    } else {
                        done = true;
                    }
                }
                left = 2 * j + 1;
                right = left + 1;
            }
        }
        showData(list);
    }

    private static void StalinSort(int[] oldlist) throws InterruptedException {
        int size = oldlist.length;
        int[] list = new int[size];
        for(int i = 0; i < size; i++) {
            list[i] = oldlist[i];
        }

        int[] temp;
        int prevItem = list[0];

        for(int i = 0; i <= size; i++) {
            showData(list);
            if(list[i] < prevItem) {
                temp = new int[size--];
                for(int j = 0; j < i; j++) {
                    temp[j] = list[j];
                }
                for(int j = i; j < size; j++) {
                    temp[j] = list[j + 1];
                }
                list = temp;
                i--;
            } else {
                prevItem = list[i];
            }
        }

        showData(list);
    }

    private static void BogoSort(int[] oldlist) throws InterruptedException {
        int size = oldlist.length;
        ArrayList<Integer> list = new ArrayList<>();
        for (int i : oldlist) {
            list.add(i);
        }

        boolean done = false;

        while(!done) {
            showData(list);
            Collections.shuffle(list);
            done = true;
            for(int i = 1; i < size; i++) {
                if (list.get(i - 1) > list.get(i)) {
                    done = false;
                    break;
                }
            }
        }

        showData(list);
    }

    private static void BozoSort(int[] oldlist) throws InterruptedException {
        int size = oldlist.length;
        int[] list = new int[size];
        for(int i = 0; i < size; i++) {
            list[i] = oldlist[i];
        }

        boolean done = false;
        int loc1, loc2;
        Random random = new Random();

        while(!done) {
            showData(list);
            loc1 = random.nextInt(size);
            loc2 = random.nextInt(size);
            if(loc1 != loc2) {
                int temp = list[loc1];
                list[loc1] = list[loc2];
                list[loc2] = temp;
            }
            done = true;
            for(int i = 1; i < size; i++) {
                if (list[i - 1] > list[i]) {
                    done = false;
                    break;
                }
            }
        }
        showData(list);
    }

    private static void SleepSort(int[] list) {
        final CountDownLatch latch = new CountDownLatch(list.length);

        for (final int num : list) {
            new Thread(() -> {
                try {
                    Thread.sleep(num * 100L); // Sleep proportional to the value
                    System.out.print(num + "\t");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                latch.countDown();
            }).start();
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println();
    }

    private static void HumanSort(int[] oldlist) throws InterruptedException, IOException {
        int size = oldlist.length;
        int[] list = new int[size];
        showData(oldlist);

        boolean done = false;

        while(!done) {
            done = true;
            for(int i = 0; i < size; i++) {
                System.out.print("Please enter number " + (i + 1) + ": ");
                list[i] = Integer.parseInt(stdin.readLine().trim());
            }
            showData(list);
            for(int i = 0; i < size - 1; i++) {
                if(list[i] > list[i + 1]) {
                    done = false;
                    System.out.println("Error! List is not sorted. Try again!");
                    break;
                }
            }
        }
    }

    private static void GangsterSort(int[] oldlist) throws InterruptedException, IOException {
        showData(oldlist);
        System.out.println("*Gun cocking sound*");
        System.out.println("Look buddy, I think we both know the list is sorted, right?");
        System.out.print("(Y/N): ");
        String input = stdin.readLine().trim();
        if(input.equals("Y")) {
            System.out.println("You made the right choice, buddy.");
        } else {
            throw new RuntimeException("You died. R.I.P.");
        }
    }
}