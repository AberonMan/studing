package studing.reactive.rxjava;

import rx.Observable;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * https://www.infoq.com/articles/rxjava-by-example
 *
 * @author mash0916
 */
public class RxJavaExample {

    private static final List<String> WORDS = Arrays.asList(
            "the",
            "quick",
            "brown",
            "fox",
            "jumped",
            "over",
            "the",
            "lazy",
            "dogs"
    );

    public static class ObservableExample {

        public static void main(String[] args) {
            example_1();
            example_2();
            example_3();
            example_4();


        }

        private static void example_4() {
            System.out.println("example 4");
            Observable.from(WORDS)
                    .subscribe(System.out::println);
            System.out.println();
        }

        private static void example_3() {
            // example 3
            System.out.println("example 3");
            Observable.just(WORDS)
                    .subscribe(System.out::println);
            System.out.println();
        }

        private static void example_2() {
            //example 2
            System.out.println("example 2");
            Observable.just("Hello", "World")
                    .subscribe(System.out::println);
            System.out.println();
        }

        private static void example_1() {
            // example 1
            System.out.println("example 1");
            Observable<String> observable = Observable.just("Hello");
            observable.subscribe(System.out::println);
            System.out.println();
        }
    }

    public static class ZipExample {

        public static void main(String[] args) {
            Observable.from(WORDS)
                    .zipWith(Observable.range(1, Integer.MAX_VALUE),
                            (word, count) -> String.format(String.format("%2d. %s", count, word)))
                    .subscribe(System.out::println);
        }
    }

    public static class FlatmapExample {
        public static void main(String[] args) {
            //example 1 chars in word
            Observable.from(WORDS)
                    .flatMap(word -> Observable.from(word.split("")))
                    .zipWith(Observable.range(1, Integer.MAX_VALUE),
                            (string, count) -> String.format("%2d. %s", count, string))
                    .subscribe(System.out::println);
            System.out.println();

            //example 2 distinct in word
            Observable.from(WORDS)
                    .flatMap(word -> Observable.from(word.split("")))
                    .distinct()
                    .zipWith(Observable.range(1, Integer.MAX_VALUE),
                            (string, count) -> String.format("%2d. %s", count, string))
                    .subscribe(System.out::println);
            System.out.println();

            //sorted
            Observable.from(WORDS)
                    .flatMap(word -> Observable.from(word.split("")))
                    .sorted()
                    .distinct()
                    .zipWith(Observable.range(1, Integer.MAX_VALUE),
                            (string, count) -> String.format("%2d. %s", count, string))
                    .subscribe(System.out::println);
            System.out.println();
        }

    }

    public static class OtherFunctionExample{

        public static void main(String[] args) {
            //merge
            Observable.from(WORDS)
                    .mergeWith(Observable.just("Cat the cat from cat"))
                    .subscribe(System.out::println);
            System.out.println();

            Observable.just("Cat the cat from cat")
                    .mergeWith(Observable.from(WORDS))
                    .subscribe(System.out::println);
            System.out.println();
           //debounce
            Observable.just(0, 400, 800)
                    .flatMap(v -> Observable.timer(v, TimeUnit.MILLISECONDS).map(w -> v))
                    .doOnNext(v -> System.out.println("T=" + v))
                    .debounce(300, TimeUnit.MILLISECONDS)
                    .toBlocking()
                    .subscribe(v -> System.out.println("Debounced: " + v));
        }
    }


}
