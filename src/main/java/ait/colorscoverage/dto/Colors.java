package ait.colorscoverage.dto;

import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public class Colors {
    private List<ColorData> background_colors;
    private List<ColorData> foreground_colors;
    private List<ColorData> image_colors;
}
