package com.sj.repository;

import com.sj.entity.CancelCause;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CanCelCauseRepository {
    public CancelCause findById(Integer causeId);
    public List<CancelCause> findAll();

}
