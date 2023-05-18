package full.mypostgresql.demo.service;

import full.mypostgresql.demo.model.TVMazeShowResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// Spring magic dwarf
@FeignClient(name = "tvMazeService", url = "${tvmaze.url}") // Replace with your actual API URL
public interface UserServiceClient {

    @GetMapping("/shows/{tvShowId}")
    TVMazeShowResponse getShow(@PathVariable Integer tvShowId);
}

