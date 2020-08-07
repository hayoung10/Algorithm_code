class QS{ // QuickSort 기능을 하는 클래스
	int n, order = 0; // n = 배열의 길이, order = 정렬한 횟수
	int[] qs_array; // 정렬 전의 배열
	int[] sort_array; // 정렬하여 따로 저장한 배열
	
	public QS(int size, int[] arr) { // 배열의 초기값 설정
		n = size;
		qs_array = new int[n];
		sort_array = new int[n];
		
		for(int i = 0; i < n; i++) // qs_array에 처음 입력한 배열 저장
			qs_array[i] = arr[i];
		
		print(qs_array);
		quickSort(0,n-1);
	}
	void quickSort(int low, int high) { // QuickSort 기능
		int pivotPoint;
		
		if(high>low) {
			pivotPoint = partition(low, high);
			quickSort(low,pivotPoint-1);
			quickSort(pivotPoint+1,high);
		}
	}
	int partition(int low, int high) { // local array를 사용한 partition
		int i = low + 1, j = low, k = high;
		// i = qs_array의 인덱스, j = sort_array의 앞에서 시작하는 인덱스
		// k = sort_array의 뒤에서 시작하는 인덱스
		int pivotPoint, pivotItem;
		// pivotPoint = 피봇의 인덱스, pivotItem = 피봇의 값
		
		pivotItem = qs_array[low]; // pivotItem은 배열의 가장 앞
		
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
		print(sort_array); // 정렬한 배열 출력
		change(qs_array,sort_array);
		// qs_array의 값들을 sort_array로 바꿔주기

		return pivotPoint;
	}
	void change(int[] q_arr, int[] s_arr) { // 정렬한 배열로 바꾸기
		for (int i = 0; i < n; i++)
			q_arr[i] = s_arr[i];
	}
	void print(int[] array) { // 배열 출력
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
		long start = System.currentTimeMillis(); // 컴파일 시작한 시간
		long end; // 컴파일 끝난 시간
		
		System.out.println("수학과          3학년          2016603009          문 하 영");
		System.out.println();

		int arr[] = {4,9,12,13,6,10,20,14,15,2,7,26,28,17,24,1,19,3,8,16,21,29,22,11,30,27,25,23,18,5};	
		QS qs = new QS(30,arr);
		
		end = System.currentTimeMillis(); // 컴파일 끝난 시간 측정
		
		System.out.println();
		System.out.println((end-start)+" ms");
		// 컴파일 끝난 시간에서 시작한 시간을 빼서 컴파일한 시간 구하기
	}
}
