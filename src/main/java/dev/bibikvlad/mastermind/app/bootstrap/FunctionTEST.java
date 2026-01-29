package dev.bibikvlad.mastermind.app.bootstrap;

import dev.bibikvlad.mastermind.game.RandomAnswerGenerator;
import dev.bibikvlad.mastermind.localization.config.LocaleType;
import dev.bibikvlad.mastermind.model.enums.ConsoleColor;
import dev.bibikvlad.mastermind.model.logo.LogoColorsBundle;
import dev.bibikvlad.mastermind.persistence.database.DatabaseContext;
import dev.bibikvlad.mastermind.persistence.database.TransactionManager;
import dev.bibikvlad.mastermind.persistence.player.dao.PlayerConfigDAO;
import dev.bibikvlad.mastermind.persistence.player.dao.PlayerDAO;
import dev.bibikvlad.mastermind.persistence.player.dao.PlayerLastSelectedDAO;
import dev.bibikvlad.mastermind.persistence.player.dao.jdbc.PlayerConfigJdbcDAO;
import dev.bibikvlad.mastermind.persistence.player.dao.jdbc.PlayerJdbcDAO;
import dev.bibikvlad.mastermind.persistence.player.dao.jdbc.PlayerLastSelectedJdbcDAO;
import dev.bibikvlad.mastermind.persistence.player.model.Player;
import dev.bibikvlad.mastermind.persistence.player.model.PlayerConfig;
import dev.bibikvlad.mastermind.persistence.player.repository.PlayerConfigRepository;
import dev.bibikvlad.mastermind.persistence.player.repository.PlayerLastSelectedRepository;
import dev.bibikvlad.mastermind.persistence.player.repository.PlayerRepository;
import dev.bibikvlad.mastermind.persistence.player.repository.sql.PlayerConfigSQLRepository;
import dev.bibikvlad.mastermind.persistence.player.repository.sql.PlayerLastSelectedSQLRepository;
import dev.bibikvlad.mastermind.persistence.player.repository.sql.PlayerSQLRepository;
import dev.bibikvlad.mastermind.services.PlayerService;

import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.util.*;
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
        //test.runnableTest();
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
        test.runnableTest(() -> {
            String answer = RandomAnswerGenerator.generate();
            char[] charArray = answer.toCharArray();
            Set<Character> characters = new HashSet<>();

            for (int i = 0; i < charArray.length; i++) {
                characters.add(charArray[i]);
            }

            if (characters.size() == 1) {
                System.out.println("All elements of the answer were the same, and the answer was: "
                        + Arrays.toString(charArray));
            }
        });

        test.callableTest(() -> {
            Connection connection = DatabaseContext.getConnection();

            PlayerDAO playerDAO = new PlayerJdbcDAO(connection);
            TransactionManager transactionManager = new TransactionManager(connection);
            PlayerRepository playerRepository = new PlayerSQLRepository(playerDAO, transactionManager);
            PlayerConfigDAO playerConfigDAO = new PlayerConfigJdbcDAO(connection);
            PlayerConfigRepository playerConfigRepository =
                    new PlayerConfigSQLRepository(playerConfigDAO, transactionManager);
            PlayerLastSelectedDAO playerLastSelectedDAO = new PlayerLastSelectedJdbcDAO(connection);
            PlayerLastSelectedRepository playerLastSelectedRepository =
                    new PlayerLastSelectedSQLRepository(playerLastSelectedDAO, transactionManager);
            PlayerService playerService =
                    new PlayerService(playerRepository, playerConfigRepository, playerLastSelectedRepository);

            return playerService.loadLastSelectedPlayer().orElse(
                    new Player("John",
                            new PlayerConfig(LocaleType.ENGLISH,
                                    new LogoColorsBundle(ConsoleColor.GREY,
                                            ConsoleColor.GREY,
                                            ConsoleColor.GREY,
                                            ConsoleColor.BACKGROUND_BLACK))));
        });

        test.biPredicateTest((String::equals));

        test.biFunctionTest((firstAnswer, secondAnswer) -> {
            byte[] firstAnswerBytes = firstAnswer.getBytes(StandardCharsets.UTF_8);
            byte[] secondAnswerBytes = secondAnswer.getBytes(StandardCharsets.UTF_8);
            byte firstBytesSum = 0;
            byte secondBytesSum = 0;

            for (int i = 0; i < firstAnswerBytes.length; i++) {
                firstBytesSum += firstAnswerBytes[i];
            }
            for (int i = 0; i < secondAnswerBytes.length; i++) {
                secondBytesSum += secondAnswerBytes[i];
            }

            return firstBytesSum + secondBytesSum;
        });
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
            }
            if (firstId > secondId) {
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

    public void biPredicateTest(BiPredicate<String, String> test) {
        for (int i = 0; i < 1_000; i++) {
            if (test.test(RandomAnswerGenerator.generate(), RandomAnswerGenerator.generate())) {
                System.out.println("Both answers are the same!");
            }
        }
    }

    public void biFunctionTest(BiFunction<String, String , Integer> test) {
        int result = test.apply(RandomAnswerGenerator.generate(), RandomAnswerGenerator.generate());
        System.out.println(result);
    }

    public void runnableTest(Runnable test) {
        ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor();

        for (int i = 0; i < 100; i++) {
            executorService.submit(test);
        }
    }

    public void callableTest(Callable<Player> test) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Future<Player> future = executorService.submit(test);

        executorService.shutdown();

        try {
            Player player = future.get();

            System.out.println(player);
        } catch (InterruptedException | ExecutionException exception) {
            throw new RuntimeException(exception);
        }
    }
}
