/*
 * Algorithm for array sorting
 */
public class InsertSort {

	public static int binarySearchForInsertSort(int arr[], int key, int low, int high) {
		if(high <= low)
			return (key >= arr[low]) ? (low + 1) : low;
		
		int mid = (low + high)/2;
		
		if( key == arr[mid])
			return mid + 1;
		
		if(key > arr[mid])
			return binarySearchForInsertSort(arr, key, mid + 1, high);
		else
			return binarySearchForInsertSort(arr, key, low, mid-1);
	}
	public static void insertSort(int arr[]) {
		//idea is to pick each element and find right position for it
		for(int i=1; i<arr.length; i++) {
			int j = i - 1;
			int key = arr[i];
			int pos = binarySearchForInsertSort(arr, key, 0, j);
			while(j>=pos) {
				arr[j+1] = arr[j];
				j--;
			}
			arr[j+1] = key;
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[]= {10,8,1,2,6,5,3};
		
		insertSort(arr);
		
		for(int i=0; i<arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
	}

}
