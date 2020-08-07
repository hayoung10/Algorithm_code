class QS{ // QuickSort ����� �ϴ� Ŭ����
	int n, order = 0; // n = �迭�� ����, order = ������ Ƚ��
	int[] qs_array; // ���� ���� �迭
	int[] sort_array; // �����Ͽ� ���� ������ �迭
	
	public QS(int size, int[] arr) { // �迭�� �ʱⰪ ����
		n = size;
		qs_array = new int[n];
		sort_array = new int[n];
		
		for(int i = 0; i < n; i++) // qs_array�� ó�� �Է��� �迭 ����
			qs_array[i] = arr[i];
		
		print(qs_array);
		quickSort(0,n-1);
	}
	void quickSort(int low, int high) { // QuickSort ���
		int pivotPoint;
		
		if(high>low) {
			pivotPoint = partition(low, high);
			quickSort(low,pivotPoint-1);
			quickSort(pivotPoint+1,high);
		}
	}
	int partition(int low, int high) { // local array�� ����� partition
		int i = low + 1, j = low, k = high;
		// i = qs_array�� �ε���, j = sort_array�� �տ��� �����ϴ� �ε���
		// k = sort_array�� �ڿ��� �����ϴ� �ε���
		int pivotPoint, pivotItem;
		// pivotPoint = �Ǻ��� �ε���, pivotItem = �Ǻ��� ��
		
		pivotItem = qs_array[low]; // pivotItem�� �迭�� ���� ��
		
		while(j<k) {
			if(pivotItem > qs_array[i]) {
				sort_array[j] = qs_array[i];
				j++;
			}
			if(pivotItem < qs_array[i]) {
				sort_array[k] = qs_array[i];
				k--;
			}
			i++;
		}
		pivotPoint = j;
		sort_array[pivotPoint] = pivotItem;
		print(sort_array); // ������ �迭 ���
		change(qs_array,sort_array);
		// qs_array�� ������ sort_array�� �ٲ��ֱ�

		return pivotPoint;
	}
	void change(int[] q_arr, int[] s_arr) { // ������ �迭�� �ٲٱ�
		for (int i = 0; i < n; i++)
			q_arr[i] = s_arr[i];
	}
	void print(int[] array) { // �迭 ���
		System.out.printf("%2d: [", order);
		for(int i = 0; i < 30; i++) {
			System.out.printf("%4d",array[i]);
		}
		System.out.println(" ]");
		order++;
	}
}

public class Quicksort {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long start = System.currentTimeMillis(); // ������ ������ �ð�
		long end; // ������ ���� �ð�
		
		System.out.println("���а�          3�г�          2016603009          �� �� ��");
		System.out.println();

		int arr[] = {4,9,12,13,6,10,20,14,15,2,7,26,28,17,24,1,19,3,8,16,21,29,22,11,30,27,25,23,18,5};	
		QS qs = new QS(30,arr);
		
		end = System.currentTimeMillis(); // ������ ���� �ð� ����
		
		System.out.println();
		System.out.println((end-start)+" ms");
		// ������ ���� �ð����� ������ �ð��� ���� �������� �ð� ���ϱ�
	}
}
