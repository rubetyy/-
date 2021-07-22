package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name="file")
public class Image extends BaseEntity{
    Integer product_id;
    String origin_file_name;
    String file_name;
    String file_path;
    Integer file_size;
}
