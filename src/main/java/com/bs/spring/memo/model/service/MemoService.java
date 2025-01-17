package com.bs.spring.memo.model.service;

import com.bs.spring.memo.model.dto.Memo;

import java.util.List;

public interface MemoService {
    int saveMemo(Memo memo);
    List<Memo> selectMemoList();
}
