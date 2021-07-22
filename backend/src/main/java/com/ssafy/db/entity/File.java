package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class File extends BaseEntity{
    Integer product_id;
    String origin_file_name;
    String file_name;
    String file_path;
    Integer file_size;
}
