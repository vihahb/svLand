package com.goldit.managerinfo.coreapi.request;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import java.util.Map;

/**
 * Created by hex0r on 10/8/15.
 */
public class BinaryRequest extends BaseTypeRequest<byte[]> {
    public BinaryRequest(int method, String url, Response.ErrorListener errorListener, Class<byte[]> clazz, Response.Listener<byte[]> listener, Map<String, String> header, Map<String, String> params) {
        super(method, url, errorListener, clazz, listener, header, params);
    }

    @Override
    protected Response<byte[]> parseNetworkResponse(NetworkResponse response) {
        super.parseNetworkResponse(response);
        return Response.success(
                response.data,
                HttpHeaderParser.parseCacheHeaders(response));
    }

    @Override
    protected void deliverResponse(byte[] response) {
        listener.onResponse(response);
    }
}
