package io.hyperfoil.tools.horreum.entity;

import java.time.Instant;

public class BannerDTO {

    public Integer id;

    public Instant created;

    public boolean active;
    public String severity;

    public String title;

    public String message;

    public BannerDTO() {
    }
}
