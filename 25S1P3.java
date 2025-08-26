import comp1110.lib.*;
import static comp1110.lib.Functions.*;



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