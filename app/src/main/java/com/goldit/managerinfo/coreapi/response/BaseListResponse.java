package com.goldit.managerinfo.coreapi.response;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hex0r on 10/8/15.
 */
public class BaseListResponse<T> extends BaseResponse {
    public List<T> data = new ArrayList<>();
}
