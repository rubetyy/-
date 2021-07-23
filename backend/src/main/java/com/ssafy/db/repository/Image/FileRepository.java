package com.ssafy.db.repository.Image;

import com.ssafy.db.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<Image, Long> {
}
