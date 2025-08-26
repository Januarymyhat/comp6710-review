import comp1110.lib.*;
import static comp1110.lib.Functions.*;



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
//// n叉树：
//sealed interface NTree<T> permits NNode<T>, NLeaf<T> {}
//// 叶子结点：只保存一个值
//record NLeaf<T>(T value) implements NTree<T> {}
//// 内部结点：保存一组子树（List）
//record NNode<T>(ConsList<NTree<T>> children) implements NTree<T> {}
