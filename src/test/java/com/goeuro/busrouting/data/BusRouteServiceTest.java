package com.goeuro.busrouting.data;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class BusRouteServiceTest {
    private Map<Integer, List<Integer>> repo = new HashMap<>();

    // Populates map
    {
        // data
        // 1 1 2 3
        // 3 4 2 6
        repo.put(1, Arrays.asList(new Integer[] {1}));
        repo.put(2, Arrays.asList(new Integer[] {1}));
        repo.put(3, Arrays.asList(new Integer[] {1}));

        repo.put(4, Arrays.asList(new Integer[] {3}));
        repo.put(2, Arrays.asList(new Integer[] {1, 3}));
        repo.put(6, Arrays.asList(new Integer[] {3}));
    }

    private BusRouteService busRouteService;

    @Before
    public void setup() {
        busRouteService = new BusRouteService();
        busRouteService.setRepo(repo);
    }

    @Test
    public void shouldHaveDirectRoute() throws Exception {
        assertThat(busRouteService.hasDirectRoute(1, 2)).isTrue();
        assertThat(busRouteService.hasDirectRoute(2, 3)).isTrue();
        assertThat(busRouteService.hasDirectRoute(4, 2)).isTrue();
        assertThat(busRouteService.hasDirectRoute(2, 6)).isTrue();
    }

    @Test
    public void shouldNotHaveDirectRoute() throws Exception {
        assertThat(busRouteService.hasDirectRoute(1, 6)).isFalse();
        assertThat(busRouteService.hasDirectRoute(2, 7)).isFalse();
        assertThat(busRouteService.hasDirectRoute(4, 8)).isFalse();
        assertThat(busRouteService.hasDirectRoute(4, 1)).isFalse();
    }
}