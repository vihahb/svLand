package com.goldit.managerinfo.coreapi.request;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by hex0r on 10/8/15.
 */
public class StringRequest extends BaseTypeRequest<String> {
    public StringRequest(int method, String url, Response.ErrorListener errorListener, Class<String> clazz, Response.Listener<String> listener, Map<String, String> header, Map<String, String> params) {
        super(method, url, errorListener, clazz, listener, header, params);
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        super.parseNetworkResponse(response);
        try {
            String responseString = new String(
                    response.data,
                    HttpHeaderParser.parseCharset(response.headers));

            Response<String> success = Response.success(
                    responseString,
                    HttpHeaderParser.parseCacheHeaders(response));

            return success;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(String response) {
        listener.onResponse(response);
    }
}
