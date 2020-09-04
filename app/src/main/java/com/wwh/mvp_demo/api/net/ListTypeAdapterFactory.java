package com.wwh.mvp_demo.api.net;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.$Gson$Types;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者： 王伟浩 on 2020/5/20
 * 邮箱：382443920@qq.com
 * 微信：18515184197
 * 说明:List并不像整形一样是一个基本类型，List本身在数据解析的时候是要带泛型的
 * 不可能在构建的时候就定好集合里的数据类型。而如果不定泛型里的数据类型，重写适配器就得根据运行时遇到的类型分别进行操作，这无异于把Gson的工作重新做一遍。
 * 源码中Gson对待List也并非当做一个类型去解析的
 * 而是在初始化时带有一个CollectionTypeAdapterFactory，在遇到JsonArray类型的数据就会调用集合类型的解析器，然后再适配集合里的对应数据类型。挺复杂，并且不怎么能扩展。
 * 解决方案：
 * 通过注解方式@JsonAdapter可以指定对应的适配器，优先级是高于默认的CollectionTypeAdapterFactory，和通过GsonBuilder传入的适配器的。
 * 然后还是拷贝CollectionTypeAdapterFactory出来，改一份ListTypeAdapterFactory出来
 */

public class ListTypeAdapterFactory implements TypeAdapterFactory {

    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        Type type = typeToken.getType();

        Class<? super T> rawType = typeToken.getRawType();
        if (!List.class.isAssignableFrom(rawType)) {
            return null;
        }

        Type elementType = $Gson$Types.getCollectionElementType(type, rawType);
        TypeAdapter<?> elementTypeAdapter = gson.getAdapter(TypeToken.get(elementType));

        @SuppressWarnings({"unchecked", "rawtypes"}) // create() doesn't define a type parameter
                TypeAdapter<T> result = new Adapter(gson, elementType, elementTypeAdapter);
        return result;
    }

    private static final class Adapter<E> extends TypeAdapter<List<E>> {
        private final TypeAdapter<E> elementTypeAdapter;

        public Adapter(Gson context, Type elementType,
                       TypeAdapter<E> elementTypeAdapter) {
            this.elementTypeAdapter = new TypeAdapterRuntimeTypeWrapper<E>(
                    context, elementTypeAdapter, elementType);
        }

        //关键部分是这里，重写解析方法
        public List<E> read(JsonReader in) throws IOException {
            //null值返回null
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                return null;
            }
            //新建一个空的列表
            List<E> list = new ArrayList<>();
            try {
                in.beginArray();
                while (in.hasNext()) {
                    E instance = elementTypeAdapter.read(in);
                    list.add(instance);
                }
                in.endArray();
                //正常解析成为列表
            } catch (IllegalStateException e) { //如果是空字符串，会有BEGIN_ARRAY报错
                //此时尝试解析成字符串，如果不是空字符串，则依旧抛出异常
                //如果是空字符串，则不抛出异常，使最终返回一个空的列表
                if (!"".equals(in.nextString())) {
                    throw e;
                }
            }

            return list;
        }

        public void write(JsonWriter out, List<E> list) throws IOException {
            if (list == null) {
                out.nullValue();
                return;
            }

            out.beginArray();
            for (E element : list) {
                elementTypeAdapter.write(out, element);
            }
            out.endArray();
        }
    }
}
