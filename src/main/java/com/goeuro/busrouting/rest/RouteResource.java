package com.goeuro.busrouting.rest;

import com.goeuro.busrouting.data.BusRouteService;
import com.goeuro.busrouting.model.RouteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;

@Controller
@EnableAutoConfiguration
public class RouteResource {

    @Autowired
    private BusRouteService busRouteService;

    @RequestMapping(method= RequestMethod.GET, path="/direct")
    public @ResponseBody
    RouteResult getResult(@RequestParam(value="dep_sid") int departureId,
                          @RequestParam(value="arr_sid") int arrivalId) throws IOException, URISyntaxException {
        return new RouteResult(departureId, arrivalId, busRouteService.hasDirectRoute(departureId, arrivalId));
    }
}
