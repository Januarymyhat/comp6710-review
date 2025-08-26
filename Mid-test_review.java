import comp1110.lib.*;
import static comp1110.lib.Functions.*;


// ===================================== Part 1 ===================================
int totallLength(ConsList<String> strings) {
		return switch (strings) {
				case Nil<String>() -> 0;
				case Cons<String>(String head, ConsList<String> rest) -> Length(head) + totallLength(rest);
		};
}

void main(){}

void test(){}



// ===================================== Part 2 ===================================
float average(ConsList<Integer> numbers){
		if (Length(numbers) == 0) return 0;
		
		int sum = averageHelper(numbers);
		return (float)sum / Length(numbers);
}

int averageHelper(ConsList<Integer> numbers){
		return switch (numbers) {
				case Nil<Integer>() -> 0;
				case Cons<Integer>(Integer head, ConsList<Integer> rest) -> head + averageHelper(rest);
		};
}

void main(){}

void test(){}


// ===================================== Part 3 ===================================

ConsList<Pair<Integer, Integer>> allPair(ConsList<Integer> list1, ConsList<Integer>list2){
		ConsList<Integer> sortedList1 = Sort(list1);
		ConsList<Integer> sortedList2 = Sort(list2);
				
		return switch (sortedList1) {
				// case Nil<Integer>() -> ?;
				case Nil<Integer>() -> new Nil<>();
				case Cons<Integer>(Integer head1, ConsList<Integer> rest1) -> 
						Append(allPairHelper(head1, sortedList2), allPair(rest1, sortedList2));
		};
}


ConsList<Pair<Integer, Integer>> allPairHelper(Integer head, ConsList<Integer> list){
		return switch (list) {
				case Nil<Integer>() -> new Nil<>();
				case Cons<Integer>(Integer h, ConsList<Integer> r) -> 
						new Cons<>(new Pair<>(head, h), allPairHelper(head, r));		
		};
		
}

void main(){}

void test(){}

// ===================================== Part 4 - 1 ===================================

sealed interface BinTree<T> permits Node<T>, Leaf<T> {}
// record Leaf<T>(int left, int right) implements BinTree<T> {}
record Leaf<T>(T value) implements BinTree<T> {}
record Node<T>(BinTree<T> left, BinTree<T> right) implements BinTree<T> {}


T BinTreeFold(BiFunction<T,T,T> agg, BinTree<T> tree){
		return switch (tree) {
				case Leaf<T>(T v) -> v;
				// case Node<T>(BinTree<T> l, BinTree<T> r) -> agg.apply(l, r); 
				case Node<T>(BinTree<T> l, BinTree<T> r) -> {
						T leftResult = BinTreeFold(agg, l);
						T rightResult = BinTreeFold(agg, r);
						yield agg.apply(leftResult, rightResult);
				}
		};
}

// agg 无需在这里定义，它是外部传入的 BiFunction<T,T,T>
// 调用例子：int result = BinTreeFold((a,b) -> a - b, tree);


// ===================================== Part 4 - 2 ===================================

BinTree<T> BinTreeMap(Function<T,T> mapper, BinTree<T> tree) {
		return switch(tree) {
				case Leaf<T>(T v) -> new Leaf<>(mapper.apply(v));
				case Node<T>(BinTree<T> l, BinTree<T> r) -> new Node<>(BinTreeMap(mapper, l), BinTreeMap(mapper, r));
		};
}


void main(){}

void test(){}

// ===================================== 补充 ===================================
// n叉树：
sealed interface NTree<T> permits NNode<T>, NLeaf<T> {}
// 叶子结点：只保存一个值
record NLeaf<T>(T value) implements NTree<T> {}
// 内部结点：保存一组子树（List）
record NNode<T>(ConsList<NTree<T>> children) implements NTree<T> {}



