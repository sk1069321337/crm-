package com.bjpowernode.crm.workbench.service;

import com.bjpowernode.crm.settings.domain.DicValue;
import com.bjpowernode.crm.workbench.domain.Clue;

import java.util.List;
import java.util.Map;

public interface ClueService {

    boolean save(Clue clue);

    Clue detail(String id);

    boolean unbund(String id);

    boolean bund(String aid, String[] cids);
}
