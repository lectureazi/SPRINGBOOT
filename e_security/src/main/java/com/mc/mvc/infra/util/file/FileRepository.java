package com.mc.mvc.infra.util.file;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mc.mvc.infra.util.file.FilePath;

@Repository
public interface FileRepository extends JpaRepository<FilePath, Long>{

}
