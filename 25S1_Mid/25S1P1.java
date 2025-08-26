import comp1110.lib.*;
import static comp1110.lib.Functions.*;


int totallLength(ConsList<String> strings) {
    return switch (strings) {
        case Nil<String>() -> 0;
        case Cons<String>(String head, ConsList<String> rest) -> Length(head) + totallLength(rest);
    };
}

void main(){}

void test(){}