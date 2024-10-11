package ait.colorscoverage.dto;

import lombok.Getter;

@Getter
public class ColorData {
    private String closest_palette_color;
    private String closest_palette_color_parent;
    private double percent;
}
