import comp1110.lib.*;
import static comp1110.lib.Functions.*;



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