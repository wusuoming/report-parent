/**
 * @version: 2015年12月3日 | 0.0.1
 * @author: panda
 */
package com.appc.report.common;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonValue;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * TODO 这里用一句话描述这个类的作用
 *
 * @author panda
 * @version 2015年12月3日 | 0.0.1
 */

public class SimpleHttpMessageConverter extends MappingJackson2HttpMessageConverter {

    @Autowired
    private HttpServletRequest request;

    @Override
    protected Object readInternal(Class<? extends Object> clazz, HttpInputMessage inputMessage)
            throws IOException, HttpMessageNotReadableException {
        return super.readInternal(clazz, inputMessage);
    }

    @Override
    protected void writeInternal(Object object, HttpOutputMessage outputMessage)
            throws IOException, HttpMessageNotWritableException {
        writeInternal(object, null, outputMessage);
    }

    @Override
    protected void writeInternal(Object object, Type type, HttpOutputMessage outputMessage)
            throws IOException, HttpMessageNotWritableException {

        if (request.getRequestURI().contains("swagger-resources") || request.getRequestURI().contains("api-docs")
                || object.getClass().getName().equals("ApiListing")
                || MediaType.TEXT_HTML.equals(outputMessage.getHeaders().getContentType())
                || MediaType.TEXT_XML.equals(outputMessage.getHeaders().getContentType())) {
            super.writeInternal(object, type, outputMessage);
        } else {
            Map<String, Object> returnValue = new HashMap<>();
            returnValue.put("code", "0");
            returnValue.put("message", "操作成功");
            if (object != null) {
                BeanMap beanMap = BeanMap.create(object);
                for (Object key : beanMap.keySet()) {
                    returnValue.put(key + "", beanMap.get(key));
                }
            }
            object = returnValue;
            JsonEncoding encoding = getJsonEncoding(outputMessage.getHeaders().getContentType());
            JsonGenerator generator = this.objectMapper.getFactory().createGenerator(outputMessage.getBody(), encoding);
            try {
                writePrefix(generator, object);
                Class<?> serializationView = null;
                Object value = object;
                if (value instanceof MappingJacksonValue) {
                    MappingJacksonValue container = (MappingJacksonValue) object;
                    value = container.getValue();
                    serializationView = container.getSerializationView();
                }
                if (serializationView != null) {
                    this.objectMapper.writerWithView(serializationView).writeValue(generator, value);
                } else {
                    this.objectMapper.writeValue(generator, value);
                }
                writeSuffix(generator, object);
                generator.flush();

            } catch (JsonProcessingException ex) {
                throw new HttpMessageNotWritableException("Could not write content: " + ex.getMessage(), ex);
            }
        }
    }
}
