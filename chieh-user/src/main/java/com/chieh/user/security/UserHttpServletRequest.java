package com.chieh.user.security;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chieh.entity.Request;
import com.chieh.user.entity.model.UserEntity;
import com.nimbusds.jose.util.IOUtils;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.Charset;

public class UserHttpServletRequest extends HttpServletRequestWrapper {

    private JSONObject requestBody;
    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request The request to wrap
     * @throws IllegalArgumentException if the request is null
     */
    public UserHttpServletRequest(HttpServletRequest request, ChiehUserDetails userDetails) throws IOException {
        super(request);
        String requestBodyString = IOUtils.readInputStreamToString(request.getInputStream());
        this.requestBody = JSONObject.parseObject(requestBodyString);
        this.requestBody.put("userDetails", userDetails);
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {

        ByteArrayInputStream byteArrayInputStream =
                new ByteArrayInputStream(requestBody.toJSONString().getBytes());
        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener listener) {

            }

            @Override
            public int read() throws IOException {
                return byteArrayInputStream.read();
            }
        };
    }
}
