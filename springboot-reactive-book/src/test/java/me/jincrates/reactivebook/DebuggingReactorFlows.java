package me.jincrates.reactivebook;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Hooks;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DebuggingReactorFlows {
    static class SimpleExample {
        public static void main(String[] args) {
            ExecutorService executor = Executors.newSingleThreadScheduledExecutor();

            List<Integer> source;
            if (new Random().nextBoolean()) {
                source = IntStream.range(1, 11).boxed() //
                        .collect(Collectors.toList());
            } else {
                source = Arrays.asList(1, 2, 3, 4);
            }

            try {
                executor.submit(() -> source.get(5)).get(); // line 52
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            } finally {
                executor.shutdown();
            }
        }
    }
    // end::simple-example[]

    // tag::reactor-example[]
    static class ReactorExample {
        public static void main(String[] args) {
            Mono<Integer> source;
            if (new Random().nextBoolean()) {
                source = Flux.range(1, 10).elementAt(5);
            } else {
                source = Flux.just(1, 2, 3, 4).elementAt(5);
            }

            source //
                    .subscribeOn(Schedulers.parallel()) //
                    .block(); // line 74
        }
    }
    // end::reactor-example[]

    // tag::reactor-example2[]
    static class ReactorDebuggingExample {
        public static void main(String[] args) {

            Hooks.onOperatorDebug();

            Mono<Integer> source;
            if (new Random().nextBoolean()) {
                source = Flux.range(1, 10).elementAt(5);
            } else {
                source = Flux.just(1, 2, 3, 4).elementAt(5); // line 89
            }
            source //
                    .subscribeOn(Schedulers.parallel()) //
                    .block(); // line 93
        }
    }
    // end::reactor-example2[]
}
