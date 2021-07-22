package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.sql.Date;

@Entity
@Getter
@Setter
public class Product extends BaseEntity{
    Integer live_id;
    Integer category_id;
    String p_title;
    String p_description;
    Integer p_price;
    Date p_created_at;
    Integer p_view_count;
    String p_is_sold;
    String p_live_status;
    String user_id;

}
