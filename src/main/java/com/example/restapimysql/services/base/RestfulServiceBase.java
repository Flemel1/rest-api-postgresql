package com.example.restapimysql.services.base;

import java.util.List;

public interface RestfulServiceBase {
    public List<?> fetchAll();
    public <T> T update(T id);
    public <T> T delete(Integer id);
    public <T> T create(T model);
    public <T> T show(T id);
}
