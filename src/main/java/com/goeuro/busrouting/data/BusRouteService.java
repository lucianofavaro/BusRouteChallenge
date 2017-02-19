package com.goeuro.busrouting.data;

import org.assertj.core.util.VisibleForTesting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Repository
public class BusRouteService implements CommandLineRunner {
    private static final Logger LOG = LoggerFactory.getLogger(BusRouteService.class);

    private Map<Integer, List<Integer>> repo = new HashMap<>();

    public boolean hasDirectRoute(int departure, int arrival) {
        List<Integer> departureList = repo.get(departure);
        List<Integer> arrivalList = repo.get(arrival);

        if (departureList != null && arrivalList != null) {
            return !Collections.disjoint(repo.get(departure), repo.get(arrival));
        } else {
            return false;
        }
    }

    private void loadFile(Path pathToDataFile) throws IOException, URISyntaxException {
        Files.lines(pathToDataFile)
                .parallel()
                .skip(1)
                .forEach(line -> addToMap(line));
    }


    private void addToMap(String line) {
        String[] elements = line.split(" ");

        Integer routeId = null;
        List<Integer> list = new ArrayList<>();

        for (int i=0; i<elements.length; i++) {
            if (i == 0) {
                routeId = Integer.parseInt(elements[i]);
                continue;
            }

            synchronized (repo) {
                Integer sid = Integer.parseInt(elements[i]);
                if (!repo.containsKey(sid)) {
                    List<Integer> routeIds = new ArrayList<>();
                    routeIds.add(routeId);
                    repo.put(sid, routeIds);
                } else {
                    repo.get(sid).add(routeId);
                }
            }
        }
    }

    @Override
    public void run(String... args) throws Exception {
        Path pathToDataFile = null;

        if (args.length == 0) {
            LOG.info("Load default data file.");
            pathToDataFile = Paths.get(ClassLoader.getSystemResource("routes.txt").toURI());
        } else {
            pathToDataFile = Paths.get(args[0]);
        }

        // Load File into memory repo
        loadFile(pathToDataFile);
    }

    @VisibleForTesting
    void setRepo(Map<Integer, List<Integer>> repo) {
        this.repo = repo;
    }
}
