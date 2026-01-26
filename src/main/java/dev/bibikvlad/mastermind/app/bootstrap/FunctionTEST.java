package dev.bibikvlad.mastermind.app.bootstrap;

import dev.bibikvlad.mastermind.game.RandomAnswerGenerator;
import dev.bibikvlad.mastermind.persistence.player.model.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.function.*;

public class FunctionTEST {
    public static void main(String[] args) {
        Test test = new Test();

        System.out.println(test.test().apply("Kino"));
        System.out.println(test.supTest().get());
        System.out.println(test.testUnFunctions(RandomAnswerGenerator.generate().hashCode()));
        System.out.println(test.biTesting(RandomAnswerGenerator.generate(), RandomAnswerGenerator.generate().hashCode()));
        System.out.println(test.predicateTesting(RandomAnswerGenerator.generate()));
        test.runnableTest();
        test.funcParamTest(string -> {
            char[] charArray = string.toCharArray();
            Random random = new Random();
            int sum = 0;

            for (int i = 0; i < charArray.length; i++) {
                sum += String.valueOf(charArray[i]).hashCode() * random.nextInt(777);
            }

            return sum;
        });

        String heloWorld = "Hello World!";

        test.supTest(() -> {
            int stringHash = heloWorld.hashCode();

            return stringHash + " - Hash Code of the" + heloWorld;
        });

        test.consTest(answer -> {
            if (answer.contains("r")) {
                System.out.println("Answer has the potential to be guess on the first try");
            } else {
                System.out.println("Answer sucks");
            }
        });

        test.predicateTest(answer -> answer.length() > 10);
    }
}

class Test {
    public Function<String, Integer> test() {
        return string -> string.length() + 2;
    }

    public Supplier<String> supTest() {
        return RandomAnswerGenerator::generate;
    }

    public String testUnFunctions(Integer hash) {
        IntFunction<String> stringLongFunction = Integer::toString;

        return stringLongFunction.apply(hash);
    }

    public Double biTesting(String answer, Integer hash) {
        BiFunction<String, Integer, Double> biFunction = (randomAnswer, answerHash) -> {
            if (randomAnswer.length() == 0) {
                return 0D;
            }

            return (double) answerHash / randomAnswer.length();
        };

        return biFunction.apply(answer, hash);
    }

    public Boolean predicateTesting(String string) {
        Predicate<String> stringPredicate = value -> value.hashCode() > 10_000;

        return stringPredicate.test(string);
    }

    public void runnableTest() {
        try (ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {
            Random random = new Random();
            List<Future<String>> futures = new ArrayList<>();

            Callable<String> callable = () -> {
                Thread.sleep(random.nextLong(1000));

                return "Thread: " + Thread.currentThread().getName() + " is done working.";
            };

            for (int i = 0; i < 1_000_000; i++) {
                futures.add(executorService.submit(callable));
            }

            executorService.shutdown();

            futures.forEach(future -> {
                try {
                    System.out.println(future.get());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                }
            });

            if (!executorService.awaitTermination(50000, TimeUnit.MILLISECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void funcParamTest(Function<String, Integer> test) {
        System.out.println(test.apply(RandomAnswerGenerator.generate()));
    }

    public void supTest(Supplier<String> test) {
        System.out.println(test.get());
    }

    public void consTest(Consumer<String> test) {
        test.accept(RandomAnswerGenerator.generate());
    }

    public void compTest() {
        List<Player> players = new ArrayList<>();
        players.sort((fristPlayer, secondPlayer) -> {
            long firstId = fristPlayer.getId();
            long secondId = secondPlayer.getId();

            if (firstId == secondId) {
                return 0;
            } if (firstId > secondId) {
                return -1;
            } else {
                return 1;
            }
        });
    }

    public void predicateTest(Predicate<String> test) {
        boolean result = test.and(string -> string.length() > 0).and(test).test(RandomAnswerGenerator.generate());

        System.out.println(result);
    }
}
