import books.model.Books;
import org.junit.jupiter.api.Test;
import org.loadtest4j.LoadTester;
import org.loadtest4j.Request;
import org.loadtest4j.Result;
import org.loadtest4j.factory.LoadTesterFactory;

import java.time.Duration;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class StressTest {

    private static final LoadTester loadTester = LoadTesterFactory.getLoadTester();

    @Test
    public void accessUrl() {

        int random1 = (int)(Math.random()*((99999-1000)+1))+1000;
        int random2 = (int)(Math.random()*((99999-1000)+1))+1000;
        int random3 = (int)(Math.random()*((99999-1000)+1))+1000;

        List<Request> requests = List.of(Request.get("/books/find")
                .withHeader("Accept", "application/json")
                .withQueryParam("name", "Book"+random1+" by AuthorName"+random2+" AuthorSurname"+random3));

        Result result = loadTester.run(requests);

        System.out.println("Median request time: "+
                result.getResponseTime().getMedian());
        System.out.println("Max request time: "+
                result.getResponseTime().getMax());
        System.out.println("OK %: "+
                result.getPercentOk());
        System.out.println("OK request number: "+
                result.getDiagnostics().getRequestCount().getOk());
        System.out.println("Total request number: "+
                result.getDiagnostics().getRequestCount().getTotal());
        System.out.println("Throughput (requests/sec): "+
                result.getDiagnostics().getRequestsPerSecond());
        System.out.println("Percentile 90: "+
                result.getResponseTime().getPercentile(90));
        System.out.println("Percentile 95: "+
                result.getResponseTime().getPercentile(95));
        System.out.println("Percentile 99: "+
                result.getResponseTime().getPercentile(99));

        assertThat(result.getResponseTime().getPercentile(90))
                .isLessThanOrEqualTo(Duration.ofMillis(8000));
    }
}
