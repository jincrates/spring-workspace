package me.jincrates.reactivebook.domain.items;

import com.mongodb.client.model.geojson.Point;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    private @Id String id;
    private String name;
    private String description;
    private double price;
    private String distributorRegion;
    private Date releaseDate;
    private Point location;
    private boolean active;

    public Item(String name, String description, double v) {
    }
}