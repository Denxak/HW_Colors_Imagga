package ait.colorscoverage;

import ait.colorscoverage.dto.ColorData;
import ait.colorscoverage.dto.ColorResponse;
import ait.colorscoverage.dto.Colors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Stream;

public class ColorsCoverageImageAppl {
    static final String BASIC = "YOU BASIC";
    static RestTemplate restTemplate = new RestTemplate();
    static String url = "https://api.imagga.com/v2/colors";
    static HttpHeaders headers = new HttpHeaders();
    static String imgUrl = "https://static.insales-cdn.com/images/products/1/2006/258369494/fa4a474aa7f6b013fb7d3791ccbda094.jpg";

    public static void main(String[] args) throws Exception {
        headers.add("Authorization", BASIC);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("image_url", imgUrl);
        URI uri = builder.build().toUri();
        RequestEntity<String> request = new RequestEntity<>(headers, HttpMethod.GET, uri);
        ResponseEntity<ColorResponse> response = restTemplate.exchange(request, ColorResponse.class);

        Colors colors = response.getBody().getResult().getColors();
        List<ColorData> allColors = Stream.of(
                        colors.getBackground_colors(),
                        colors.getForeground_colors(),
                        colors.getImage_colors()
                )
                .filter(colorList -> colorList != null)
                .flatMap(List::stream)
                .toList();

        System.out.println("Color Name\t\t\tParent Color Name\t\t\tCoverage Percent");
        System.out.println("--------------------------------------------------------------");
        allColors.forEach(colorData -> {
            System.out.println(
                    colorData.getClosest_palette_color() + "\t\t\t" +
                            colorData.getClosest_palette_color_parent() + "\t\t\t" +
                            colorData.getPercent()
            );
        });
    }
}
