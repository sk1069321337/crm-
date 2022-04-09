package com.bjpowernode.crm.workbench.dao;

import com.bjpowernode.crm.workbench.domain.Clue;

public interface ClueDao {

    Clue getById(String clueId);

    int save(Clue clue);

    Clue detail(String id);

}
