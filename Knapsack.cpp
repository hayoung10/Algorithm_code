#include <iostream>
#include <cassert> // assert를 사용하기 위해
#include <queue>
using namespace std;

struct node {
	int level;
	int profit;
	int weight;
	float bound;

	bool operator < (const node& uv) const { // priority queue에서 bound값으로 정렬하기 위해
		return bound < uv.bound;
	}
};

float bound(node u, int n, const int p[], const int w[], int W) { // bound값 계산
	int j, k; int totWeight; float result;

	if (u.weight >= W)
		return 0;
	else {
		result = (float)u.profit;
		j = u.level + 1;
		totWeight = u.weight;
		while (j < n && totWeight + w[j] <= W) {
			totWeight = totWeight + w[j];
			result = result + p[j];
			j++;
		}
		k = j;
		if (k < n)
			result = result + (W - totWeight) * (p[k] / w[k]);
		return result;
	}
}

void knapsack2(int n, const int p[], const int w[], int W) { // breadth-first-search
	queue<node> Q; node u, v, r; int maxProfit;
	queue<node> prQ; // Q에 저장되어 있는 데이터를 보여주기 위해 사용
	int expanded_node = 1; // Start 1 because root node

	assert(Q.empty()); // Initialize(Q)
	v.level = -1; v.profit = 0; v.weight = 0; maxProfit = 0;
	Q.push(v); // enqueue(Q,v)

	while (!Q.empty()) {
		// Show Queue
		prQ = Q;
		cout << "Queue[ ";
		while (!prQ.empty()) {
			r = prQ.front();
			cout << r.profit << " ";
			prQ.pop();
		}
		cout << "]" << endl;

		v = Q.front();
		Q.pop(); // dequeue(Q,v)
		u.level = v.level + 1;

		// take care of the left child
		u.weight = v.weight + w[u.level];
		u.profit = v.profit + p[u.level];
		if (u.weight <= W && u.profit > maxProfit)
			maxProfit = u.profit;
		if (bound(u, n, p, w, W) > maxProfit) {
			Q.push(u); // enqueue(Q,u)
			expanded_node += 1;
		}
		else
			expanded_node += 1;

		// take care of the right child
		u.weight = v.weight;
		u.profit = v.profit;
		if (bound(u, n, p, w, W) > maxProfit) {
			Q.push(u); // enqueue(Q,u)
			expanded_node += 1;
		}
		else
			expanded_node += 1;
	}
	cout << "maxprofit = " << maxProfit << endl;
	cout << "expanded nodes = " << expanded_node << endl;
}

void knapsack3(int n, const int p[], const int w[], int W) { // best-first-search
	priority_queue<node> PQ; node u, v, r; int maxProfit;
	priority_queue<node> prPQ; // Q에 저장되어 있는 데이터를 보여주기 위해 사용
	int expanded_node = 1; // Start 1 because root node

	assert(PQ.empty()); // Initialize(Q)
	v.level = -1; v.profit = 0; v.weight = 0; v.bound = bound(v, n, p, w, W);
	maxProfit = 0;
	PQ.push(v); // enqueue(Q,v)

	while (!PQ.empty()) {
		// Show Queue
		prPQ = PQ;
		cout << "Priority Queue[ ";
		while (!prPQ.empty()) {
			r = prPQ.top();
			printf("%.1f ", r.bound);
			prPQ.pop();
		}
		cout << "]" << endl;

		v = PQ.top();
		PQ.pop(); // dequeue(Q,v)

		if (v.bound > maxProfit) {
			u.level = v.level + 1;

			// take care of the left child
			u.weight = v.weight + w[u.level];
			u.profit = v.profit + p[u.level];
			if (u.weight <= W && u.profit > maxProfit)
				maxProfit = u.profit;
			u.bound = bound(u, n, p, w, W);
			if (u.bound > maxProfit) {
				PQ.push(u); // enqueue(Q,u)
				expanded_node += 1;
			}
			else
				expanded_node += 1;

			// take care of the right child
			u.weight = v.weight;
			u.profit = v.profit;
			u.bound = bound(u, n, p, w, W);
			if (u.bound > maxProfit) {
				PQ.push(u); // enqueue(Q,u)
				expanded_node += 1;
			}
			else
				expanded_node += 1;
		}
	}
	cout << "maxprofit = " << maxProfit << endl;
	cout << "expanded nodes = " << expanded_node << endl;
}

int main(void)
{
	// 초기 조건
	int n = 4;
	int p[4] = { 40,30,50,10 };
	int w[4] = { 2,5,10,5 };
	int W = 16;

	cout << "Breadth First" << endl;
	knapsack2(n, p, w, W); // breadth-first-search
	cout << endl;

	cout << "Best First" << endl;
	knapsack3(n, p, w, W); // best-first-search
	cout << endl;

	return 0;
}