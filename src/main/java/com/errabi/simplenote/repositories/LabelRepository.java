package com.errabi.simplenote.repositories;

import com.errabi.simplenote.entities.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface LabelRepository extends JpaRepository<Label, Long>, JpaSpecificationExecutor<Label>,
        PagingAndSortingRepository<Label, Long> {
}
